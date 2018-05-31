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
<%@ page import="codeu.model.store.basic.UserStore" %>
<%@ page import="codeu.model.data.User" %>

<!DOCTYPE html>
<html>
<head>
  <title>Profile</title>
  <link rel="stylesheet" href="/css/main.css">
  <style>
    #messages {
      background-color: white;
      height: 500px;
      overflow-y: scroll
    }
  </style>
</head>
<body>

  <nav>
    <a id="navTitle" href="/">CodeU Chat App</a>
    <% if(request.getSession().getAttribute("user") != null){ %>
      <a>Hello <%= request.getSession().getAttribute("user") %>!</a>
    <% } else{ %>
      <a href="/login">Login</a>
    <% } %>
    <a href="/conversations">Conversations</a>
    <a href="/about.jsp">About</a>
    <a href="/activityfeed">Activity Feed</a>
    <a href="/adminPage">Administration</a>
  </nav>

  <div id="container">
    <% String url = request.getAttribute("javax.servlet.forward.request_uri").toString();
       User user = UserStore.getInstance().getUser(url.substring(url.lastIndexOf('/') + 1));
      %>
    <% if(user != null) { %>
    <h1><%= user.getName() + "'s" %> Profile Page</h1>
    <hr/>

    <h2>About <%= user.getName() %></h2>
    <% if (user.getAboutMe() != null && !user.getAboutMe().equals("")) { %>
    <p><%= user.getAboutMe() %></p>
    <% } else { %>
    <p>Oops, Empty About Me!</p>
    <% } %>
     
    <% if (request.getSession().getAttribute("user") != null && 
      user.getName().equals(request.getSession().getAttribute("user"))) { %>
    <form action="" method="POST">
        <label for="EditAboutMe">Edit Your About Me: </label>
        <br/>
        <textarea rows="8" cols="50" type="text" name="aboutme" id="aboutme"></textarea>
        <br/>
        <button type="submit">Submit</button>
    </form>
    <% } else {} %>
    <hr/>

    <h3><%= user.getName() + "'s"%> Sent Messages</h3>
    <div id="messages">
      <ul>
       <li>Demo Message</li>
      </ul>
    </div>
    <% } else { %>
      <p>User Does Not Exist</p>
    <% } %>
  </div>
</body>
</html>