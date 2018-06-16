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
  <title>GEAR Chat App</title>
  <link rel="stylesheet" href="/css/main.css">
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

  <footer>
    <nav>
      <a href="/adminPage">Administration</a>
      <a href="/about.jsp">About</a>
    </nav>
  </footer>
</body>
</html>
