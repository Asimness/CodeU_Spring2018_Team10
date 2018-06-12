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
  <title>Register</title>
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
    <a href="/about.jsp">About</a>
    <a href="/activityfeed">Activity Feed</a>
    <a href="/adminPage">Administration</a>
  </nav>

  <div id="container">
    <h1>Register</h1>

    <% if(request.getAttribute("error") != null){ %>
        <h2 style="color:red"><%= request.getAttribute("error") %></h2>
    <% } %>

    <form action="/register" method="POST">
      <label for="username">Username: </label>
      <br/>
      <input type="text" name="username" id="username">
      <br/>
      <label for="password">Password: </label>
      <br/>
      <input type="password" name="password" id="password">
      <br/>
      <label for="gender">Gender: </label>
      <br/>
      <select name="gender">
        <option style="display:none;" selected>Select Gender</option>
        <option value="Male">Male</option>
        <option value="Female">Female</option>
        <option value="Other">Other</option>
        <option value="Prefer not to say">Prefer not to say</option>
      </select>
      <br/>
      <label for="age">Age: </label>
      <br/>
      <select name="age">
        <option style="display:none;" selected>Select Age</option>
      <% int i = 1; 
         while (i < 130) {
      %>
      <option value="<%= i %>"><%= i %></option>
      <% i++; } %>
      </select>
      <br/>
      <label for="ethnicity">Ethnicity: </label>
      <br/>
      <select class="form-control dropdown" id="ethnicity" name="ethnicity">
        <option style="display:none;" selected="selected">Select One</option>
        <optgroup label="White">
          <option value="White English">English</option>
          <option value="White Welsh">Welsh</option>
          <option value="White Scottish">Scottish</option>
          <option value="White Northern Irish">Northern Irish</option>
          <option value="White Irish">Irish</option>
          <option value="White Gypsy or Irish Traveller">Gypsy or Irish Traveller</option>
          <option value="White Other">Any other White background</option>
        </optgroup>
        <optgroup label="Mixed or Multiple ethnic groups">
          <option value="Mixed White and Black Caribbean">White and Black Caribbean</option>
          <option value="Mixed White and Black African">White and Black African</option>
          <option value="Mixed White Other">Any other Mixed or Multiple background</option>
        </optgroup>
        <optgroup label="Asian">
          <option value="Asian Indian">Indian</option>
          <option value="Asian Pakistani">Pakistani</option>
          <option value="Asian Bangladeshi">Bangladeshi</option>
          <option value="Asian Chinese">Chinese</option>
          <option value="Asian Other">Any other Asian background</option>
        </optgroup>
        <optgroup label="Black">
          <option value="Black African">African</option>
          <option value="Black African American">African American</option>
          <option value="Black Caribbean">Caribbean</option>
          <option value="Black Other">Any other Black background</option>
        </optgroup>
        <optgroup label="Other ethnic groups">
          <option value="Arab">Arab</option>
          <option value="Hispanic">Hispanic</option>
          <option value="Latino">Latino</option>
          <option value="Native American">Native American</option>
          <option value="Pacific Islander">Pacific Islander</option>
          <option value="Other">Any other ethnic group</option>
        </optgroup>
      </select>
      <br/><br/>
      <button type="submit">Submit</button>
    </form>
  </div>
</body>
</html>
