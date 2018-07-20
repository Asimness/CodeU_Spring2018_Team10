package codeu.controller;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
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

import codeu.model.data.Activity;
import codeu.model.data.Conversation;
import codeu.model.data.Message;
import codeu.model.data.User;
import codeu.model.store.basic.ActivityStore;
import codeu.model.store.basic.ConversationStore;
import codeu.model.store.basic.UserStore;

public class AllConversationsServlet extends HttpServlet{

	/** Store class that gives access to Activities. */
	   private ConversationStore conversationStore;

	   @Override
	   public void init() throws ServletException {
	     super.init();
	     setConversationStore(ConversationStore.getInstance());
	   }
	   
	   void setConversationStore(ConversationStore conversationStore) {
		    this.conversationStore = conversationStore;
		  }
	   
	   
	   @Override
	   public void doGet(HttpServletRequest request, HttpServletResponse response)
	      throws IOException, ServletException {
		 List<Conversation> conversations = conversationStore.getAllConversations();
		 request.setAttribute("conversations", conversations);
	     request.getRequestDispatcher("/WEB-INF/view/allConversations.jsp").forward(request, response);
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
	     
	     String messageContent = request.getParameter("search");

	     Conversation conversation = conversationStore.getConversationWithTitle(messageContent);
	     Conversation searchedConvo = null;
	     if (conversation == null) {
	       // couldn't find conversation, redirect to conversation list
	       response.sendRedirect("/allConversations");
	       return;
	     }else {
	    	 searchedConvo = conversation;
		     request.setAttribute("results", searchedConvo);

	     }
	     
	     //response.setContentType("/allConversations");
	     

	     // redirect to a GET request
	     doGet(request, response);

	   }
}
