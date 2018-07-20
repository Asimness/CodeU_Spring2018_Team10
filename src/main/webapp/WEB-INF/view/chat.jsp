<%--
  Copyright 2017 Google Inc.
  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at
     http://www.apache.org/licenses/LICENSE-2.0
  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
--%>
<%@ page import="java.util.List" %>
<%@ page import="codeu.model.data.Conversation" %>
<%@ page import="codeu.model.data.Message" %>
<%@ page import="codeu.model.store.basic.UserStore" %>
<%@ page import="codeu.model.data.User" %>
<%@ page import="codeu.controller.ChatServlet" %>
<%
Conversation conversation = (Conversation) request.getAttribute("conversation");
List<Message> messages = (List<Message>) request.getAttribute("messages");
%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css" integrity="sha384-Smlep5jCw/wG7hdkwQ/Z5nLIefveQRIY9nfy6xoR1uRYBtpZgI6339F5dgvm/e9B" crossorigin="anonymous">
  <title><%= conversation.getTitle() %></title>

  <link rel="stylesheet" href="/css/main.css" type="text/css">

  <style>
    div.card {
        width: 800px;
        background:white;
        text-color:black;
        box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
        text-align: left;
        margin-bottom:10px;
        height:75px;
        padding-left:10px;
    }
  </style>

  <script>
    // scroll the chat div to the bottom
    function scrollChat() {
      var chatDiv = document.getElementById('chat');
      chatDiv.scrollTop = chatDiv.scrollHeight;
    };
  </script>
</head>
<body onload="scrollChat()">

    <nav class="navbar navbar-toggleable-md navbar-dark bg-primary"> 
        <a class="navbar-brand" id="navTitle" href="/">Gear Chat App</a>
        <ul class="navbar-nav ml-auto">
          <li class="nav-item active">
            <a class="nav-link" href="/conversations">Conversations</a>
          </li>
          <% if(request.getSession().getAttribute("user") != null){ 
            String userProfileName = request.getSession().getAttribute("user").toString();
          %>
          <li class="nav-item active" active>
            <a class="nav-link" href="/user/<%= userProfileName %>">Hello <%= request.getSession().getAttribute("user") %>!</a>
          </li>
          <% } else{ %>
          <li class="nav-item active">
            <a class="nav-link" href="/login">Login</a>
          </li>
          <% } %>
          <li class="nav-item active">
            <a class="nav-link" href="/activityfeed">Activity Feed</a>
          </li>
          <li class="nav-item active">
            <a class="nav-link" href = "/allConversations">All Conversations</a>
          </li>
          <li class="nav-item active">
            <a class="nav-link" href="/adminPage">Administration</a>
          </li>
          <li class="nav-item active">
            <a class="nav-link" href="/about.jsp">About</a>
          </li>
        </ul>
    </nav>

  <div class="container">

    <h1><%= conversation.getTitle() %>
      <a href="" style="float: right">&#8635;</a></h1>

    <hr/>

    <div id="chat">
    <%
      if (!messages.isEmpty()) {
        for (Message message : messages) {
            String author = UserStore.getInstance().getUser(message.getAuthorId()).getName();

              String msg = message.getContent();
              String[] msgs = msg.split("\\s+");

              for(String word : msgs) {
                if(word != null && word.length() > 0){
                  if(word.charAt(0) == '@') {
                    String name = word.substring(1);
                    if(UserStore.getInstance().getUser(name) != null) {
                %>
    	<li><strong><a href="/user/<%= name %>"><%= name %></strong></a></li>
      <%
      }
      }
      }
      }
    %>
      <div class="card">
          <div class="card-header">
            <strong><a href="/user/<%= author %>"><%= author %></strong></a>:<p/>
          </div>
          <div class="card-body">
           <p> <%= message.getContent() %> </p>
          </div>
          <div class="card-footer text-muted">
              <p><%= message.getSentiment() %><p>
          </div>
       </div>
    <%
      }
      }
    %>
    </div>

    <hr/>

    <% if (request.getSession().getAttribute("user") != null) { %>
    <form action="/chat/<%= conversation.getTitle() %>" method="POST">
        <labelfor="SendAMessage">Enter A Message:</labelfor>
        <br/>
        <input type="text" name="message" class="form-control" placeholder="Message">
        <br/>
        <button type="submit" class="btn btn-primary">Send</button>
        <br/>
        <input type="radio" name="privacy" value="public" > Public Conversation<br>
  		<input type="radio" name="privacy" value="private"> Private Conversation<br>
  		<br/>
  		<br/>
        <button type="submit" class="btn btn-primary">Send</button>
        <br/>
    </form>
    <% } else { %>
      <p><a href="/login">Login</a> to send a message.</p>
    <% } %>

    <hr/>

  </div>

  <footer class="footer">
  <nav class="navbar sticky-bottom navbar-dark bg-primary"> 
      <span class="navbar-text">
          Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. 
          Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. 
          Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. 
          Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
      </span>
  </nav>
</footer>
  <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/js/bootstrap.min.js" integrity="sha384-o+RDsa0aLu++PJvFqy8fFScvbHFLtbvScb8AjopnFD+iEQ7wo/CG0xlczd+2O/em" crossorigin="anonymous"></script>
</body>
</html>