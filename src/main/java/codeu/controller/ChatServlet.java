// Copyright 2017 Google Inc.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License..

package codeu.controller;

import codeu.model.data.Activity; 
import codeu.model.data.Conversation;
import codeu.model.data.Edge;
import codeu.model.data.Message;
import codeu.model.data.SA;
import codeu.model.data.TestGraph;
import codeu.model.data.User;
import codeu.model.data.Vertex;
import codeu.model.store.basic.ConversationStore;
import codeu.model.store.basic.MessageStore;
import codeu.model.store.basic.UserStore;
import codeu.model.store.basic.VertexStore;
import codeu.model.store.basic.ActivityStore;
import java.io.IOException;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.kefirsf.bb.BBProcessorFactory;
import org.kefirsf.bb.TextProcessor;
import com.vdurmont.emoji.EmojiParser;

/** Servlet class responsible for the chat page. */
public class ChatServlet extends HttpServlet {

   /** Store class that gives access to Users. */
   private VertexStore vertexStore;
	
   /** Store class that gives access to Activities. */
   private ActivityStore activityStore;	
   
  /** Store class that gives access to Conversations. */
  private ConversationStore conversationStore;

  /** Store class that gives access to Messages. */
  private MessageStore messageStore;

  /** Store class that gives access to Users. */
  private UserStore userStore;

  /** Set up state for handling chat requests. */
  @Override
  public void init() throws ServletException {
    super.init();
    setConversationStore(ConversationStore.getInstance());
    setMessageStore(MessageStore.getInstance());
    setUserStore(UserStore.getInstance());
    setActivityStore(ActivityStore.getInstance());
    setVertexStore(VertexStore.getInstance());
  }
  
  
  /**
   * Sets the ActivityStore used by this servlet. This function provides a common setup method
   * for use by the test framework or the servlet's init() function.
   */
  void setVertexStore(VertexStore vertexStore) {
    this.vertexStore = vertexStore;
  }
  
  /**
   * Sets the ActivityStore used by this servlet. This function provides a common setup method
   * for use by the test framework or the servlet's init() function.
   */
  void setActivityStore(ActivityStore activityStore) {
    this.activityStore = activityStore;
  }

  /**
   * Sets the ConversationStore used by this servlet. This function provides a common setup method
   * for use by the test framework or the servlet's init() function.
   */
  void setConversationStore(ConversationStore conversationStore) {
    this.conversationStore = conversationStore;
  }

  /**
   * Sets the MessageStore used by this servlet. This function provides a common setup method for
   * use by the test framework or the servlet's init() function.
   */
  void setMessageStore(MessageStore messageStore) {
    this.messageStore = messageStore;
  }

  /**
   * Sets the UserStore used by this servlet. This function provides a common setup method for use
   * by the test framework or the servlet's init() function.
   */
  void setUserStore(UserStore userStore) {
    this.userStore = userStore;
  }
 

  /**
   * This function fires when a user navigates to the chat page. It gets the conversation title from
   * the URL, finds the corresponding Conversation, and fetches the messages in that Conversation.
   * It then forwards to chat.jsp for rendering.
   */
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    String requestUrl = request.getRequestURI();
    String conversationTitle = requestUrl.substring("/chat/".length());

    Conversation conversation = conversationStore.getConversationWithTitle(conversationTitle);
    if (conversation == null) {
      // couldn't find conversation, redirect to conversation list
      System.out.println("Conversation was null: " + conversationTitle);
      response.sendRedirect("/conversations");
      return;
    }

    UUID conversationId = conversation.getId();
    List<Message> messages = messageStore.getMessagesInConversation(conversationId);

    request.setAttribute("conversation", conversation);
    request.setAttribute("messages", messages);
    request.getRequestDispatcher("/WEB-INF/view/chat.jsp").forward(request, response);
  }
  
  

  /**
   * This function fires when a user submits the form on the chat page. It gets the logged-in
   * username from the session, the conversation title from the URL, and the chat message from the
   * submitted form data. It creates a new Message from that data, adds it to the model, and then
   * redirects back to the chat page.
   */
  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {

    String username = (String) request.getSession().getAttribute("user");
    if (username == null) {
      // user is not logged in, don't let them add a message
      response.sendRedirect("/login");
      return;
    }

    User user = userStore.getUser(username);
    if (user == null) {
      // user was not found, don't let them add a message
      response.sendRedirect("/login");
      return;
    }

    String requestUrl = request.getRequestURI();
    String conversationTitle = requestUrl.substring("/chat/".length());

    Conversation conversation = conversationStore.getConversationWithTitle(conversationTitle);
    if (conversation == null) {
      // couldn't find conversation, redirect to conversation list
      response.sendRedirect("/conversations");
      return;
    }
    
    response.setContentType("/chat/");
    String publicConvo = request.getParameter("privacy");
    System.out.println(publicConvo);
    if(publicConvo != null) {
    	if(publicConvo.equals("private")) {
    		conversation.setPublicStatus(false);
    	}else {
    		conversation.setPublicStatus(true);
    	}
    }
    
    
    TextProcessor processor = BBProcessorFactory.getInstance().create();
    String messageContent = request.getParameter("message");
    // this removes any HTML from the message content
    String cleanedMessageContent = processor.process(Jsoup.clean(messageContent, Whitelist.none()));
    
    
    
    String emojis = EmojiParser.parseToUnicode(cleanedMessageContent);
    SA sa = new SA();
    
    Message message =
        new Message(
            UUID.randomUUID(),
            conversation.getId(),
            user.getId(),
            //displays the edited message
            emojis,
            Instant.now());


    try {
    	System.out.println(sa.getSentiment(cleanedMessageContent));
		message.setSentiment(sa.getSentiment(cleanedMessageContent));
		message.setEmotion(sa.getEmotion(cleanedMessageContent));
	} catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
    
    
    messageStore.addMessage(message);
    
    DateTimeFormatter formatter = DateTimeFormatter.RFC_1123_DATE_TIME;
    String activityContent = message.getCreationTime().atOffset(ZoneOffset.of("Z")).format(formatter) + ": " + username + " sent a message in " + conversationTitle + ": \"" + message.getContent() + "\"";
    
    Activity activity = new Activity(UUID.randomUUID(), activityContent, user.getCreationTime());
    activityStore.addActivity(activity);
    
    
    UUID ownerUUID = conversation.getOwnerId();
    User userOwner = userStore.getUser(ownerUUID);
    String owner = "";
    try {
    	owner = userOwner.getName();
    }catch(Exception e) {
    	
    }
    
    UUID msgerUUID = message.getAuthorId();
    User userMsger = userStore.getUser(msgerUUID);
    String msger = "";
    try {
    	msger = userMsger.getName();
    }catch(Exception e) {
    	
    }
    
    if(!vertexStore.getVertexList().contains(owner + "|" + msger + "|" + "1" + "|" + "1")) {
    	vertexStore.addVertex(owner + "|" + msger + "|" + "1" + "|" + "1");
    }
    
    if(!vertexStore.getVertexList().contains(msger + "|" + owner + "|" + "1" + "|" + "1")) {
    	vertexStore.addVertex(msger + "|" + owner + "|" + "1" + "|" + "1");
    }
    
    ArrayList<String> s = new ArrayList<>();
    s.add("A|R|T");
    
    for(String w : vertexStore.getVertexList()) {
    	System.out.println(w);
    }
    
    TestGraph tg = new TestGraph(vertexStore.getVertexList(), s);
    System.out.println(tg.setUp());

    // redirect to a GET request
    response.sendRedirect("/chat/" + conversationTitle);
  }
}