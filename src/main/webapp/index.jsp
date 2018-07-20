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
  <title>GEAR Chat App</title>
  <link rel="stylesheet" href="/css/indexStyle.css">
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

  <!--
  <div id="container">
    <div
      style="width:75%; margin-left:auto; margin-right:auto; margin-top: 50px;">

      <h1>Welcome to the GEAR Chat App!</h1>
      
      <p>
      <a href="/login">Login</a> to create an account and get started chatting!
      </p>
      
      <p>
      Check out the <a href="/conversations">conversations</a> page to
            start a conversation or find a conversation you would like to join!
      </p>
      
      <p>
      Want to know the features of this app? Check out the <a href="/about.jsp">about</a> page!
        It contains a list of features and tips to make your conversations more fun and creative.
      </p>

    </div>
  </div>
-->

<div class="jumbotron jumbotron-fluid">
    <div class="container">
      <h1 class="display-4" id="jumboTitle">Welcome to the GEAR Chat App!</h1>
    </div>
</div>

<div class="container">
  <div class="card-deck">
      <div class="card border-primary mb-3" style="max-width: 18rem;">
        <div class="card-body">
          <h5 class="card-title"><b>Login / Register</b></h5>
          <p class="card-text"> Login or Register to create an account and get started chatting!</p>
          </br>
          </br>
          </br>
          <p class="card-text"> <a href="/login">Login</a></p>
        </div>
      </div>
      <div class="card border-primary mb-3" style="max-width: 18rem;">
        <div class="card-body">
          <h5 class="card-title"><b>Conversations</b></h5>
          <p class="card-text">This page is to start a conversation or find a conversation you would like to join!</p>
        </br>
        </br>
          <p class="card-text"><a href="/conversations">conversations</a></p>
        </div>
      </div>
      <div class="card border-primary mb-3" style="max-width: 18rem;">
        <div class="card-body">
          <h5 class="card-title"><b>About</b></h5>
          <p class="card-text">Want to know the features of this app? Check out the About page! It contains a list of features and tips to make your conversations more fun and creative.</p>
          <p class="card-text"><a href="/about.jsp">About</a></p>
        </div>
      </div>
    </div>
  </div>



  
    <nav class="navbar sticky-bottom navbar-dark bg-primary"> 
        <span class="navbar-text">
            Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. 
            Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. 
            Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. 
            Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
        </span>
    </nav>
  
  <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/js/bootstrap.min.js" integrity="sha384-o+RDsa0aLu++PJvFqy8fFScvbHFLtbvScb8AjopnFD+iEQ7wo/CG0xlczd+2O/em" crossorigin="anonymous"></script>
</body>
</html>
