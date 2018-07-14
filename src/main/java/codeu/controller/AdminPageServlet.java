// Copyright 2017 Google Inc.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package codeu.controller;

import codeu.model.data.Conversation;
import codeu.model.data.User;
import codeu.model.data.Message;
import codeu.model.data.TestGraph;
import codeu.model.store.basic.ConversationStore;
import codeu.model.store.basic.MessageStore;
import codeu.model.store.basic.UserStore;
import codeu.model.store.basic.VertexStore;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Servlet class responsible for the Admin Page
public class AdminPageServlet extends HttpServlet {
	
	
	/** Store class that gives access to Users. */
	private VertexStore vertexStore;
	
	// this stores a UserStore
	private UserStore userStore;
	
	// this stores a ConversationStore
	private ConversationStore conversationStore;
	
	// this stores a MessageStore
	private MessageStore messageStore;
	
	// stores site stats
	private Map<String, String> stats;
	private Map<String, String> genderStats;
	private Map<String, String> ageStats;
	private Map<String, String> ethnicStats;
	
	  /**
	   * Sets the UserStore used by this servlet. This function provides a common setup method for use
	   * by the test framework or the servlet's init() function.
	   */
	  void setUserStore(UserStore userStore) {
	    this.userStore = userStore;
	  }
	  
	  /*
	   * Sets the conversationStore used by the servlet
	   */
	  void setConversationStore(ConversationStore conversationStore) {
		  this.conversationStore = conversationStore;
	  }
	  
	  /*
	   * Sets the messageStore used by the servlet
	   */
	  void setMessageStore(MessageStore messageStore) {
		  this.messageStore = messageStore;
	  }
	  
	  /*
	   * Sets the vertexStore used by the servlet
	   */
	  void setVertexStore(VertexStore vertexStore) {
		  this.vertexStore = vertexStore;
	  }
	  
	  void setUpPage() {
		  stats.put("Number of Users", userStore.count());
		  stats.put("Number of Conversations", conversationStore.count());
		  stats.put("Number of Messages", messageStore.count());
		  
		  int mode = 0;
		  int mean = 0;
		  int median = 0;
		  int male = 0;
		  int female = 0;
		  int otherAge = 0;
		  int prefNotSay = 0;
		  int whiteEnglish = 0;
		  int whiteWelsh = 0;
		  int whiteScottish = 0;
		  int whiteNorthernIrish = 0;
		  int whiteIrish = 0;
		  int whiteGypsyOrIrishTraveller = 0;
		  int whiteOther = 0;
		  int mixedOrMultipleEthnicGroups = 0;
		  int mixedWhiteAndBlackCaribbean = 0;
		  int mixedWhiteAndBlackAfrican = 0;
		  int mixedWhiteOther = 0;
		  int asianIndian = 0;
		  int asianPakistani = 0;
		  int asianBangladeshi = 0;
		  int asianChinese = 0;
		  int asianOther = 0;
		  int blackAfrican = 0;
		  int blackAfricanAmerican = 0;
		  int blackCaribbean = 0;
		  int blackOther = 0;
		  int arab = 0;
		  int hispanic = 0;
		  int latino = 0;
		  int nativeAmerican = 0;
		  int pacificIslander = 0;
		  int otherEthnic = 0;
		  
		  List<User> allUsers = userStore.getAllUsers();
		  ArrayList<Integer> ages = new ArrayList<>();
		  ArrayList<String> genders = new ArrayList<>();
		  ArrayList<String> ethnicities = new ArrayList<>();
		  for(User u : allUsers) {
			  ethnicities.add(u.getEthnicity());
		  }
		  for(User u : allUsers) {
			  genders.add(u.getGender());
		  }
		  for(User u : allUsers) {
			 ages.add(u.getAge());
		  }
		  Collections.sort(ages);
		  for(int i = 0; i < ages.size(); i++) {
			  mean += ages.get(i);
		  }
		  
		  if (ages.size() != 0)
			  mean = mean / ages.size();
		  
		  if(ages.size() != 0) {
			  int middle = ages.size() / 2;
			  if (ages.size() % 2 == 1) {
				  median = ages.get(middle);
			  } else {
				  median = (ages.get(middle - 1) + ages.get(middle)) / 2;
			  }
		  }
		  
		  int maxValue = 0;
		  int maxCount = 0;
		  for(int i = 0; i < ages.size(); ++i) {
			  int count = 0;
			  for(int j = 0; j < ages.size(); ++j) {
				  if (ages.get(j) == ages.get(i))
					  ++count;
			  }
			  if (count > maxCount) {
				  maxCount = count;
				  maxValue = ages.get(i);
			  }
		  }
		  
		  mode = maxValue;
		  
		  for(int i = 0; i < genders.size(); i++) {
			  if(genders.get(i).equals("Male")) {
				  male++;
			  } else if (genders.get(i).equals("Female")) {
				  female++;
			  }else if (genders.get(i).equals("Other")) {
				  otherAge++;
			  }else {
				  prefNotSay++;
			  }
		  }
		  
		  for(int i = 0; i < ethnicities.size(); i++) {
			  if(ethnicities.get(i).equals("White English")) {
				  whiteEnglish++;
			  } else if(ethnicities.get(i).equals("White Welsh")) {
				  whiteWelsh++;
			  } else if(ethnicities.get(i).equals("White Scottish")) {
				  whiteScottish++;
			  } else if(ethnicities.get(i).equals("White Northern Irish")) {
				  whiteNorthernIrish++;
			  } else if(ethnicities.get(i).equals("White Irish")) {
				  whiteIrish++;
			  } else if(ethnicities.get(i).equals("White Gypsy or Irish Traveller")) {
				  whiteGypsyOrIrishTraveller++;
			  } else if(ethnicities.get(i).equals("White Other")) {
				  whiteOther++;
			  } else if(ethnicities.get(i).equals("Mixed or Multiple ethnic groups")) {
				  mixedOrMultipleEthnicGroups++;
			  } else if(ethnicities.get(i).equals("Mixed White and Black Caribbean")) {
				  mixedWhiteAndBlackCaribbean++;
			  } else if(ethnicities.get(i).equals("Mixed White and Black African")) {
				  mixedWhiteAndBlackAfrican++;
			  } else if(ethnicities.get(i).equals("Mixed White Other")) {
				  mixedWhiteOther++;
			  } else if(ethnicities.get(i).equals("Asian Indian")) {
				  asianIndian++;
			  } else if(ethnicities.get(i).equals("Asian Pakistani")) {
				  asianPakistani++;
			  } else if(ethnicities.get(i).equals("Asian Bangladeshi")) {
				  asianBangladeshi++;
			  } else if(ethnicities.get(i).equals("Asian Chinese")) {
				  asianChinese++;
			  } else if(ethnicities.get(i).equals("Asian Other")) {
				  asianOther++;
			  } else if(ethnicities.get(i).equals("Black African")) {
				  blackAfrican++;
			  } else if(ethnicities.get(i).equals("Black African American")) {
				  blackAfricanAmerican++;
			  } else if(ethnicities.get(i).equals("Black Caribbean")) {
				  blackCaribbean++;
			  } else if(ethnicities.get(i).equals("Black Other")) {
				  blackOther++;
			  } else if(ethnicities.get(i).equals("Arab")) {
				  arab++;
			  } else if(ethnicities.get(i).equals("Hispanic")) {
				  hispanic++;
			  } else if(ethnicities.get(i).equals("Latino")) {
				  latino++;
			  } else if(ethnicities.get(i).equals("Native American")) {
				  nativeAmerican++;
			  } else if(ethnicities.get(i).equals("Pacific Islander")) {
				  pacificIslander++;
			  } else {
				  otherEthnic++;
			  }
		  }
		  
		  ageStats.put("Mean age of users", mean + "");
		  ageStats.put("Median age of users", median + "");
		  ageStats.put("Mode age of users", mode + "");
		  
		  genderStats.put("The number of male users", male + "");
		  genderStats.put("The number of female users", female + "");
		  genderStats.put("The number of other users", otherAge + "");
		  genderStats.put("The number of users who prefer not to say", prefNotSay + "");
		  
		  ethnicStats.put("The number of White English users is", whiteEnglish + "");
		  ethnicStats.put("The number of White Welsh users is", whiteWelsh + "");
		  ethnicStats.put("The number of White Scottish users is", whiteScottish + "");
		  ethnicStats.put("The number of White Nothern Irish users is", whiteNorthernIrish + "");
		  ethnicStats.put("The number of White Irish users is", whiteIrish + "");
		  ethnicStats.put("The number of White Gypsy or Irish Traveller users is", whiteGypsyOrIrishTraveller + "");
		  ethnicStats.put("The number of White Other users is", whiteOther + "");
		  ethnicStats.put("The number of Mixed or Multiple ethnic groups users is", mixedOrMultipleEthnicGroups + "");
		  ethnicStats.put("The number of Mixed White and Black Caribbean users is", mixedWhiteAndBlackCaribbean + "");
		  ethnicStats.put("The number of Mixed White and Black African users is", mixedWhiteAndBlackAfrican + "");
		  ethnicStats.put("The number of Mixed White Other users is", mixedWhiteOther + "");
		  ethnicStats.put("The number of Asian Indian users is", asianIndian + "");
		  ethnicStats.put("The number of Asian Pakistani users is", asianPakistani + "");
		  ethnicStats.put("The number of Asian Bangladeshi users is", asianBangladeshi + "");
		  ethnicStats.put("The number of Asian Chinese users is", asianChinese + "");
		  ethnicStats.put("The number of Asian Other users is", asianOther + "");
		  ethnicStats.put("The number of Black African users is", blackAfrican + "");
		  ethnicStats.put("The number of Black African American users is", blackAfricanAmerican + "");
		  ethnicStats.put("The number of Black Caribbean users is", blackCaribbean + "");
		  ethnicStats.put("The number of Black Other users is", blackOther + "");
		  ethnicStats.put("The number of Arab users is", arab + "");
		  ethnicStats.put("The number of Hispanic users is", hispanic + "");
		  ethnicStats.put("The number of Latino users is", latino + "");
		  ethnicStats.put("The number of Native American users is", nativeAmerican + "");
		  ethnicStats.put("The number of Pacific Islander users is", pacificIslander + "");
		  ethnicStats.put("The number of Other users is", otherEthnic + "");
	  }
	  
	 /**
	   * Set up state for handling conversation-related requests. This method is only called when
	   * running in a server, not when running in a test.
	   */
	  @Override
	  public void init() throws ServletException {
	    super.init();
	    stats = new LinkedHashMap<String, String>();
	    ageStats = new LinkedHashMap<String, String>();
	    genderStats = new LinkedHashMap<String, String>();
	    ethnicStats = new LinkedHashMap<String, String>();
	    setUserStore(UserStore.getInstance());
	    setConversationStore(ConversationStore.getInstance());
	    setMessageStore(MessageStore.getInstance());
	    setVertexStore(VertexStore.getInstance());
	  }
	  
	  /*
	   * This function fires when a user navigates to the admin page. It gets all
	   * users, conversations, and messages from the model and forwards them to
	   * adminpage.jsp 
	   */
	  @Override
	  public void doGet(HttpServletRequest request, HttpServletResponse response)
	  		throws IOException, ServletException {
		  init();
		  
		  setUpPage();
		  
		  request.setAttribute("stats", stats);
		  request.setAttribute("ageStats", ageStats);
		  request.setAttribute("genderStats", genderStats);
		  request.setAttribute("ethnicStats", ethnicStats);
		  request.getRequestDispatcher("/WEB-INF/view/adminPage.jsp").forward(request, response);
	  }
	  
	  /**
	   * This function fires when a user submits the form on the conversations page. It gets the
	   * logged-in username from the session. It determines if the user is an admin or not
	   */
	  @Override
	  public void doPost(HttpServletRequest request, HttpServletResponse response)
	      throws IOException, ServletException {
		  
		  String username = (String) request.getSession().getAttribute("user");
		  String action = (String) request.getParameter("adminPageAction");
		  System.out.println(action);
		  
		  // the user is not logged in
		  if (username == null) {
			  System.out.println("Not logged in.");
			  response.sendRedirect("/login");
			  return;
		  }
		  
		 // the user is logged in, so get the User
		  User user = userStore.getUser(username);
		  
		  if (user == null) { // the user doesn't exist
			  System.out.println("User doesn't exist.");
			  response.sendRedirect("/login");
			  return;
		  }
		  
		  // set admin attribute
		  request.setAttribute("admin", user.getAdmin());
		  
		  if (user.getAdmin())
			  System.out.println("This user is an admin.");
		  else
			  System.out.println("This user is not an admin.");
		  
		  if(action.equals("actionOne")) {
			  String usernameOfAgeAccount = (String) request.getParameter("userAccountAge");
			  System.out.println(usernameOfAgeAccount);
			  if(userStore.isUserRegistered(usernameOfAgeAccount)) {
				  
				  setUpPage();
				  LocalDate userldt = userStore.getUser(usernameOfAgeAccount).getCreationTime().atZone(ZoneId.systemDefault()).toLocalDate();
				  LocalDate now = LocalDate.now();
				  Period diff = Period.between(userldt, now);
				  String accountAge = String.format("The user '%s' is %d years, %d months and %d days old", usernameOfAgeAccount, diff.getYears(), diff.getMonths(), diff.getDays());
				  System.out.println(accountAge);
				  request.setAttribute("stats", stats);
				  request.setAttribute("ageStats", ageStats);
				  request.setAttribute("genderStats", genderStats);
				  request.setAttribute("ethnicStats", ethnicStats);
				  request.setAttribute("uAA", accountAge);
				  request.getRequestDispatcher("/WEB-INF/view/adminPage.jsp").forward(request, response);
				  return;
			  }else {
				  setUpPage();
				  
				  String accountAge = "User Not Found!";
				  request.setAttribute("stats", stats);
				  request.setAttribute("ageStats", ageStats);
				  request.setAttribute("genderStats", genderStats);
				  request.setAttribute("ethnicStats", ethnicStats);
				  request.setAttribute("uAA", accountAge);
				  request.getRequestDispatcher("/WEB-INF/view/adminPage.jsp").forward(request, response);
				  return;
				  
			  }
		  }else if(action.equals("actionTwo")) {
			  
			  String user1 = (String) request.getParameter("socialDegreeUserOne");
			  String user2 = (String) request.getParameter("socialDegreeUserTwo");
			  String friendOrConvo = (String) request.getParameter("ConvoOrFriends");
			  if(userStore.isUserRegistered(user1) && userStore.isUserRegistered(user2)) {
				  
				  setUpPage();
				  request.setAttribute("stats", stats);
				  request.setAttribute("ageStats", ageStats);
				  request.setAttribute("genderStats", genderStats);
				  request.setAttribute("ethnicStats", ethnicStats);
				  
				  ArrayList<String> query = new ArrayList<>();
				  query.clear();
				  String queryOne = user1 + "|" + user2 + "|" + friendOrConvo;
				  query.add(queryOne);
			  
				  TestGraph tg = new TestGraph(vertexStore.getVertexList(), query);
				  String result = tg.setUp();
				  System.out.println(result);
				  
				  request.setAttribute("socialDegree", result);
				  request.getRequestDispatcher("/WEB-INF/view/adminPage.jsp").forward(request, response);
				  return;
			  }else {
				  setUpPage();
				  request.setAttribute("stats", stats);
				  request.setAttribute("ageStats", ageStats);
				  request.setAttribute("genderStats", genderStats);
				  request.setAttribute("ethnicStats", ethnicStats);
				  
				  String result = "User Not Found";
				  request.setAttribute("socialDegree", result);
				  request.getRequestDispatcher("/WEB-INF/view/adminPage.jsp").forward(request, response);
				  return;
			  }
		  }
		  response.sendRedirect("/adminPage");
	  }
	  
}
