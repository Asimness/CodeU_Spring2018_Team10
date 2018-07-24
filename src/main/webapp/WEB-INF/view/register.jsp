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
  <title>Register</title>
  <link rel="stylesheet" href="/css/registerStyle.css">
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
    <h1>Register</h1>

    <% if(request.getAttribute("error") != null){ %>
        <h2 style="color:red"><%= request.getAttribute("error") %></h2>
    <% } %>

    <script type="text/javascript">
      function validateForm() {
        console.log(document.getElementById("gender").value + "");
        console.log(document.getElementById("age").value + "");
        console.log(document.getElementById("ethnicity").value + "");
        if (document.getElementById("gender").value == "Select Gender") {
          alert("Please select a gender.");
          return false;
        }
        if (document.getElementById("age").value == "Select Age") {
          alert("Please select an age.");
          return false;
        }
        if(document.getElementById("ethnicity").value == "Select One") {
          alert("Please select an ethnicity");
          return false;
        }
      }
    </script>

    <form action="/register" onsubmit="return validateForm()" method="POST">
      <div class="form-group mb-2">
        <label for="username">Username: </label>
        <br/>
        <input type="text" class="form-control" name="username" id="username" placeholder="Username">
      </div>
      <br/>
      <div class="form-group mb-2">
        <label for="password">Password: </label>
        <br/>
        <input type="password" class="form-control" name="password" id="password" placeholder="Password">
      </div>
      <br/>
      <label for="gender">Gender: </label>
      <br/>
      <select name="gender" class="form-control dropdown" id="gender">
        <option style="display:none;" selected value="Select Gender">Select Gender</option>
        <option value="Male">Male</option>
        <option value="Female">Female</option>
        <option value="Other">Other</option>
        <option value="Prefer not to say">Prefer not to say</option>
      </select>
      <br/>

      <label for="age">Age: </label>
      <br/>
      <select name="age" class="form-control dropdown" id="age">
        <option style="display:none;" selected value="Select Age">Select Age</option>
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
        <option style="display:none;" selected="selected" value="Select One">Select One</option>
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
      <button type="submit" class="btn btn-primary">Submit</button>
    </form>
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
