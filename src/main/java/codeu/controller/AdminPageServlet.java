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
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Servlet class responsible for the Admin Page
public class AdminPageServlet extends HttpServlet {
	  
	  /*
	   * This function fires when a user navigates to the admin page. It gets all
	   * users, conversations, and messages from the model and forwards them to
	   * adminpage.jsp 
	   */
	  @Override
	  public void doGet(HttpServletRequest request, HttpServletResponse response)
	  		throws IOException, ServletException {
		  request.getRequestDispatcher("/WEB-INF/view/adminPage.jsp").forward(request, response);
	  }
}
