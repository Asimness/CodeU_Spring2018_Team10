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
<%@ page import="codeu.model.data.User" %>
<%@ page import="codeu.model.store.basic.UserStore" %>
<%@ page import="java.util.Map" %>
<% Map<String, String> stats = (Map<String, String>) request.getAttribute("stats"); %>
<!DOCTYPE html>
<html>
<head>
  <title>Admin Page</title>
  <link rel="stylesheet" href="/css/main.css">
  <style>
    #messsages {
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
    <% if (request.getSession().getAttribute("user") != null) { %>
      <% System.out.println();
         String username = (String) request.getSession().getAttribute("user");
         User user = (User) UserStore.getInstance().getUser(username);
         if (user.getName().equals("eden")) {
           user.setAdmin(true);
         }
         System.out.println("The user attribute is NOT empty");
         System.out.println("The user is " + username);
         System.out.println(); %>
      <% if (user.getAdmin()) { %>
        <h1>Administration</h1>
        <h2>Welcome <%= username%>, here are some site statistics</h2>
        <ul>
          <% for (String key : stats.keySet()) { %>
            <li><%= key %>: <%= stats.get(key) %></li>
          <% } %>
        </ul>
      <% } else { %>
        <h1>Administration</h1>
        <h2>Access Denied</h2>
        <p>Log in with an administrator account to view this page</p>
      <% } %>
    <% } else { %>
      <% System.out.println();
         System.out.println("The user attribute IS empty");
         System.out.println(); %>
      <h1>Log In Required</h1>
      <p>Please login with an administrator account to view this page.</p>
    <% } %>
  </div>
</body>
</html>