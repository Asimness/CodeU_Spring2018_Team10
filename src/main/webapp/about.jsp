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
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css" integrity="sha384-Smlep5jCw/wG7hdkwQ/Z5nLIefveQRIY9nfy6xoR1uRYBtpZgI6339F5dgvm/e9B" crossorigin="anonymous">
  <title>CodeU Chat App</title>
  <link rel="stylesheet" href="/css/aboutStyle.css">
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
        

          <h1>About the CodeU Chat App</h1>
          <p>
            Welcome to our chat app! This chap application allows users to interact
            through chat and contains a few great features. These features include:
          </p>

          <ul>
            <li><strong>Profiles:</strong> This app gives user their own profile page.
            This page contains an "About me" section that users can edit based on their
            own preferences, it also allows users to set their own profile picture, and
            add friends!
             </li>
            <li><strong>Admin page:</strong> The Admin page allows the administrators to view
            the site with its data and statistics and be able to monitor chats. To access the admin
            features, an admin login is required (It has been unlocked for everyone for this demo).
            The admin page allows admins to check the age of user accounts and user the social degree
            graph.    
           </li>
            <li><strong>Activity Feed:</strong> This allows the user to see the activity on the site.
            It displays chat messages that users have sent as well as the time and the date of each
            message.</li>
            <li><strong>Customization:</strong> Users are able to chat and customize what they write.
            They can customize their messages by using BBCode. For example: [b] bold [/b] would display
            <b> bold </b> . Users have the option to add color, fonts, and other cool features with the
            use of BBCode. For some examples, users may check <a href="https://en.wikipedia.org/wiki/BBCode"> this</a> page out and test some styles out. </li>
          </ul>
          </div>

          <navbar class="navbar navbar-dark bg-primary footer"> 
            <span class="navbar-text">
                Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. 
                Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. 
                Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. 
                Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
            </span>
        </navbar>
  <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/js/bootstrap.min.js" integrity="sha384-o+RDsa0aLu++PJvFqy8fFScvbHFLtbvScb8AjopnFD+iEQ7wo/CG0xlczd+2O/em" crossorigin="anonymous"></script>
</body>
</html>
