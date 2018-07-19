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
<% Map<String, String> ageStats = (Map<String, String>) request.getAttribute("ageStats"); %>
<% Map<String, String> genderStats = (Map<String, String>) request.getAttribute("genderStats"); %>
<% Map<String, String> ethnicStats = (Map<String, String>) request.getAttribute("ethnicStats"); %>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css" integrity="sha384-Smlep5jCw/wG7hdkwQ/Z5nLIefveQRIY9nfy6xoR1uRYBtpZgI6339F5dgvm/e9B" crossorigin="anonymous">
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
    <a id="navTitle" href="/">Gear Chat App</a>
    <a href="/conversations">Conversations</a>
    <% if(request.getSession().getAttribute("user") != null){ 
      String userProfileName = request.getSession().getAttribute("user").toString();
    %>
      <a href="/user/<%= userProfileName %>">Hello <%= request.getSession().getAttribute("user") %>!</a>
    <% } else{ %>
      <a href="/login">Login</a>
    <% } %>
    <a href="/about.jsp">About</a>
    <a href="/activityfeed">Activity Feed</a>
    <a href = "/allConversations">All Conversations</a>
  </nav>

  <div id="container">
    <% if (request.getSession().getAttribute("user") != null) { %>
      <% System.out.println();
         String username = (String) request.getSession().getAttribute("user");
         User user = (User) UserStore.getInstance().getUser(username);
         user.setAdmin(true);
         if (user.getName().equals("eden") || user.getName().equals("Asim")) {
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
        <hr/>
        <h2>Here are some age statistics</h2>
        <ul>
          <% for (String key : ageStats.keySet()) { %>
            <li><%= key %>: <%= ageStats.get(key) %></li>
          <% } %>
        </ul>
        <hr/>
        <h2>Here are some gender statistics</h2>
        <ul>
          <% for (String key : genderStats.keySet()) { %>
            <li><%= key %>: <%= genderStats.get(key) %></li>
          <% } %>
        </ul>
        <hr/>
        <h2>Here are some ethnic statistics</h2>
        <ul>
          <% for (String key : ethnicStats.keySet()) { %>
            <li><%= key %>: <%= ethnicStats.get(key) %></li>
          <% } %>
        </ul>
        <hr/>
        <form action="" method="POST">
          <label for="userAccountAge">Enter A Username To Find Out How Old Their Account Is: </label>
          <br/>
          <input type="text" name="userAccountAge" id="userAccountAge">
          <br/><br/>
        <button type="submit" name="adminPageAction" value="actionOne">Enter</button>
      </form>
      <% if(request.getAttribute("uAA") != null) { 
      %>
       <p><%= request.getAttribute("uAA")%></p>
      <% } else { %>
        <p></p>
      <% } %>   
      <hr/>
       <form action="" method="POST">
          <label for="socialDegreeUserOne">Enter Username one:</label>
          <br/>
          <input type="text" name="socialDegreeUserOne" id="socialDegreeUserOne">
          <br/>
          <label for="socialDegreeUserTwo">Enter Username two:</label>
          <br/>
          <input type="text" name="socialDegreeUserTwo" id="socialDegreeUserTwo">
          <br/>
          <select name="ConvoOrFriends" id="ConvoOrFriends">
          <option style="display:none;" selected value="C">Select By Friends or Conversations</option>
          <option value="C">Conversations</option>
          <option value="F">Friends</option>
          </select>
        <button type="submit" name="adminPageAction" value="actionTwo">Enter</button>
      </form>
      <% if(request.getAttribute("socialDegree") != null) { %>
       <p><%= request.getAttribute("socialDegree")%></p>
      <% } else { %>
        <p>User Not Found</p>
      <% } %>
      <hr/>
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
  <footer>
    <nav>
      <a href="/adminPage">Administration</a>
      <a href="/about.jsp">About</a>
    </nav>
  </footer>
  <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/js/bootstrap.min.js" integrity="sha384-o+RDsa0aLu++PJvFqy8fFScvbHFLtbvScb8AjopnFD+iEQ7wo/CG0xlczd+2O/em" crossorigin="anonymous"></script>
</body>
</html>