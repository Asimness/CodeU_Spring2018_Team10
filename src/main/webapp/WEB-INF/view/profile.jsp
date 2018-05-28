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
       String user = url.substring(url.lastIndexOf('/') + 1); %>
    <h1><%= user + "'s" %> Profile Page</h1>
    <hr/>

    <h2>About <%= user %></h2>
    <p>Temporary About Me</p>
  
    <form action="/profile" method="POST">
        <label for="username">Edit Your About Me: </label>
        <br/>
        <textarea rows="8" cols="50" type="text" name="AboutMe" id="AboutMe"></textarea>
        <br/>
        <button type="submit">Submit</button>
    </form>
    <hr/>

    <h3><%= user + "'s"%> Sent Messages</h3>
    <div id="messages">
      <ul>
       <li>Demo Message</li>
      </ul>
    </div>
  </div>
</body>
</html>
