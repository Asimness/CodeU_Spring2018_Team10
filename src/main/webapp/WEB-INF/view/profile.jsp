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
<%@ page import="java.util.*" %>
<%@ page import="codeu.model.store.basic.UserStore" %>
<%@ page import="codeu.model.data.User" %>
<%@ page import="com.google.appengine.api.datastore.Text" %>
<% if (request.getSession().getAttribute("user") != null) { %>
     <% String username = (String) request.getSession().getAttribute("user");
        User user = (User) UserStore.getInstance().getUser(username);
        String themeNum = user.getTheme(); %>
<% } %>

<!DOCTYPE html>
<html>
<head>
  <title>Profile</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css" integrity="sha384-Smlep5jCw/wG7hdkwQ/Z5nLIefveQRIY9nfy6xoR1uRYBtpZgI6339F5dgvm/e9B" crossorigin="anonymous">
  <link rel="stylesheet" href="/css/profileStyle.css">
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
    <% String url = request.getAttribute("javax.servlet.forward.request_uri").toString();
       User user = UserStore.getInstance().getUser(url.substring(url.lastIndexOf('/') + 1));
      %>
    <% if(user != null) { %>
    <h1><%= user.getName() + "'s" %> Profile Page</h1>

    <!-- <img src="data:image/jpeg;base64,<%= user.getProfilePic() %>" alt="temp" width="250" /> <-->
    <% if (user.getProfilePic() == null) { %>
      <img src="../images/temp.jpg" alt="temp"/>
    <% } else { %>
      <img src="data:image/jpeg;base64,<%= user.getProfilePic().getValue() %>" alt="temp" width="250" />
    <% } %>

    <% if (request.getSession().getAttribute("user") != null && 
           
      user.getName().equals(request.getSession().getAttribute("user"))) { %>
    <form action="" method="POST" enctype="multipart/form-data">
        <label for="EditProfilePicture">Edit Your Profile Picture: </label>
        <br/>
        <br/>
        <input type="file" name="pic" accept="image/*">
        <button type="submit" name="EditProfilePage" value="EditProfilePicture" class="btn btn-primary">Upload</button>
    </form>
    <% } else {} %>
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
        <button type="submit" name="EditProfilePage" value="EditAboutMe" class="btn btn-primary">Submit</button>
        <param name="test" value="text">
    </form>
    <% } else {} %>
    <hr/>

    <h2>Select A Theme!</h2>
     <% if (request.getSession().getAttribute("user") != null &&
           user.getName().equals(request.getSession().getAttribute("user"))) { %>
     <form action="" method"POST">
         <label for="EditTheme">Theme: </label>
           <br/>
             <select class="form-control dropdown" id="Theme" name="Theme">
               <option style="display:none;" selected="selected" value="Select One">Select One</option>
                   <option value="one">One</option>
                   <option value="two">Two</option>
                   <option value="three">Three</option>
                   <option value="four">Four</option>
             </select>
             <button type="submit" name="EditProfilePage" value="EditTheme">Submit</button>
     </form>
     <% } %>
    </hr>

    <h2><%= user.getName() + "'s" %> Friends</h2>
    <div id="friends">
    <% if (user.getFriends() != null && user.getFriends().size() != 0) { %>
      <% for (String friend : user.getFriends()) { %>
      <ul>
           <li> <%= friend %> </li>
      </ul>
      <% } %>
    <% } else { %>
      <h3>You seem to not have friends (yet!)</h3>
    <% } %>
    </div>
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

    <h3>Ad Toggle</h3>
    <div id="ad slider">
    <label class="switch">
      <input type="checkbox">
      <span class="slider round"></span>
    </label>
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
