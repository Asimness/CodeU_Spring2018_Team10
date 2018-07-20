
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
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css" integrity="sha384-Smlep5jCw/wG7hdkwQ/Z5nLIefveQRIY9nfy6xoR1uRYBtpZgI6339F5dgvm/e9B" crossorigin="anonymous">
  <title>ActivityFeed</title>
  <link rel="stylesheet" href="/css/activityfeedStyle.css">
  <style>
    #messages {
      background-color: white;
      height: 500px;
      overflow-y: scroll
    }
  </style>
</head>
<body>

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
    <h1>ACTIVITY FEED</h1>
    <p>A list of everything that has happened on the site so far!</p> 
    <hr/>

    <div id="messages">
      <ul>
    <% if(activities == null || activities.isEmpty()){ %>
    <li>No Activities To Display</li>
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

<footer>
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
