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
    <% if (request.getSession().getAttribute("user") != null) { %>
      <% String username = (String) request.getSession().getAttribute("user");
         User user = (User) UserStore.getInstance().getUser(username);%>
      <%=user.getTheme() %>
    <% } %>
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
        <button type="submit" name="EditProfilePage" value="EditProfilePicture">Upload</button>
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
        <button type="submit" name="EditProfilePage" value="EditAboutMe">Submit</button>
        <param name="test" value="text">
    </form>
    <% } else {} %>
    <hr/>

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

  <footer>
    <nav>
      <a href="/adminPage">Administration</a>
      <a href="/about.jsp">About</a>
    </nav>
  </footer>
</body>
</html>
