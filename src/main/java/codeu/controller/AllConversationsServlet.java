package codeu.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import codeu.model.data.Activity;
import codeu.model.data.Conversation;
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
}
