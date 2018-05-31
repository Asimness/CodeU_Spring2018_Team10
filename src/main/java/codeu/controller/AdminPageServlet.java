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
// limitations under the License.

package codeu.controller;

import codeu.model.data.Conversation;
import codeu.model.data.User;
import codeu.model.data.Message;
import codeu.model.store.basic.ConversationStore;
import codeu.model.store.basic.MessageStore;
import codeu.model.store.basic.UserStore;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Servlet class responsible for the Admin Page
public class AdminPageServlet extends HttpServlet {
	
	// this stores a UserStore
	private UserStore userStore;
	
	// this stores a ConversationStore
	private ConversationStore conversationStore;
	
	// this stores a MessageStore
	private MessageStore messageStore;
	
	// stores site stats
	private Map<String, String> stats;
	
	  /**
	   * Sets the UserStore used by this servlet. This function provides a common setup method for use
	   * by the test framework or the servlet's init() function.
	   */
	  void setUserStore(UserStore userStore) {
	    this.userStore = userStore;
	  }
	  
	  /*
	   * Sets the conversationStore used by the servlet
	   */
	  void setConversationStore(ConversationStore conversationStore) {
		  this.conversationStore = conversationStore;
	  }
	  
	  /*
	   * Sets the messageStore used by the servlet
	   */
	  void setMessageStore(MessageStore messageStore) {
		  this.messageStore = messageStore;
	  }
	  
	 /**
	   * Set up state for handling conversation-related requests. This method is only called when
	   * running in a server, not when running in a test.
	   */
	  @Override
	  public void init() throws ServletException {
	    super.init();
	    stats = new LinkedHashMap<String, String>();
	    setUserStore(UserStore.getInstance());
	    setConversationStore(ConversationStore.getInstance());
	    setMessageStore(MessageStore.getInstance());
	  }
	  
	  /*
	   * This function fires when a user navigates to the admin page. It gets all
	   * users, conversations, and messages from the model and forwards them to
	   * adminpage.jsp 
	   */
	  @Override
	  public void doGet(HttpServletRequest request, HttpServletResponse response)
	  		throws IOException, ServletException {
		  init();
		  stats.put("Number of Users", userStore.count());
		  stats.put("Number of Conversations", conversationStore.count());
		  stats.put("Number of Messages", messageStore.count());
		  request.setAttribute("stats", stats);
		  request.getRequestDispatcher("/WEB-INF/view/adminPage.jsp").forward(request, response);
	  }
	  
	  /**
	   * This function fires when a user submits the form on the conversations page. It gets the
	   * logged-in username from the session. It determines if the user is an admin or not
	   */
	  @Override
	  public void doPost(HttpServletRequest request, HttpServletResponse response)
	      throws IOException, ServletException {
		  
		  String username = (String) request.getSession().getAttribute("username");
		  
		  // the user is not logged in
		  if (username == null) {
			  System.out.println("Not logged in.");
			  response.sendRedirect("/login");
			  return;
		  }
		  
		 // the user is logged in, so get the User
		  User user = userStore.getUser(username);
		  
		  if (user == null) { // the user doesn't exist
			  System.out.println("User doesn't exist.");
			  response.sendRedirect("/login");
			  return;
		  }
		  
		  // set admin attribute
		  request.setAttribute("admin", user.getAdmin());
		  
		  if (user.getAdmin())
			  System.out.println("This user is an admin.");
		  else
			  System.out.println("This user is not an admin.");
	  }
}
