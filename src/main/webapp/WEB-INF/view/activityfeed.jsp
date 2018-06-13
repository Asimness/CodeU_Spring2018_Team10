
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
<%@ page import="codeu.model.data.Activity" %>
<%@ page import="codeu.model.store.basic.ActivityStore" %>
<%
List<Activity> activities = (List<Activity>) request.getAttribute("activities");
%>

<!DOCTYPE html>
<html>
<head>
  <title>ActivityFeed</title>
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
    <a id="navTitle" href="/">Gear Chat App</a>
    <a href="/conversations">Conversations</a>
    <% if(request.getSession().getAttribute("user") != null){ 
      String userProfileName = request.getSession().getAttribute("user").toString();
    %>
      <a href="/user/<%= userProfileName %>">Hello <%= request.getSession().getAttribute("user") %>!</a>
    <% } else{ %>
      <a href="/login">Login</a>
    <% } %>
    <a href="/activityfeed">Activity Feed</a>
  </nav>
  
  <div id="container">
    <h1>ACTIVITY FEED</h1>
    <p>A list of everything that has happened on the site so far!</p>
    <hr/>

    <div id="messages">
      <ul>
    <% if(activities == null || activities.isEmpty()){ %>
    <li>No Activities To Dispay</li>
    <% } else { 
      for (Activity activity : activities) {
        String content = activity.getContent();
    %>
      <li><%= content %></li>
    <%
      }
    }
    %>
      </ul>
    </div>
  </div>
 
  <style>
  .footer {
    position: fixed;
    left: 0;
    bottom: 0;
    width: 100%;
    text-align: left;
  }
  </style>

  <div class="footer">
    <nav>
      <a href="/adminPage">Administration</a>
      <a href="/about.jsp">About</a>
    </nav>
  </div>
</body>
</html>
