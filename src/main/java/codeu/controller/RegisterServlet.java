package codeu.controller;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Locale;
import java.util.UUID;
import java.time.format.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

import codeu.model.data.Activity;
import codeu.model.data.User;
import codeu.model.store.basic.UserStore;
import codeu.model.store.basic.ActivityStore;

public class RegisterServlet extends HttpServlet {

  /** Store class that gives access to Users. */
  private UserStore userStore;
  
  /** Store class that gives access to Activities. */
  private ActivityStore activityStore;

  /**
   * Set up state for handling registration-related requests. This method is only called when
   * running in a server, not when running in a test.
   */
  @Override
  public void init() throws ServletException {
    super.init();
    setUserStore(UserStore.getInstance());
    setActivityStore(ActivityStore.getInstance());
  }

  /**
   * Sets the UserStore used by this servlet. This function provides a common setup method for use
   * by the test framework or the servlet's init() function.
   */
  void setUserStore(UserStore userStore) {
    this.userStore = userStore;
  }
  
  /**
   * Sets the ActivityStore used by this servlet. This function provides a common setup method for use
   * by the test framework or the servlet's init() function.
   */
  void setActivityStore(ActivityStore activityStore) {
    this.activityStore = activityStore;
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    request.getRequestDispatcher("/WEB-INF/view/register.jsp").forward(request, response);
  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {

    String username = request.getParameter("username");

    if (!username.matches("[\\w*\\s*]*")) {
      request.setAttribute("error", "Please enter only letters, numbers, and spaces.");
      request.getRequestDispatcher("/WEB-INF/view/register.jsp").forward(request, response);
      return;
    }

    if (userStore.isUserRegistered(username)) {
      request.setAttribute("error", "That username is already taken.");
      request.getRequestDispatcher("/WEB-INF/view/register.jsp").forward(request, response);
      return;
    }
    
    String password = request.getParameter("password");    
    String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
    String aboutme = "";

    User user = new User(UUID.randomUUID(), username, hashed, aboutme, Instant.now());
    userStore.addUser(user);
    
    DateTimeFormatter formatter = DateTimeFormatter.RFC_1123_DATE_TIME;
    String activityContent = user.getCreationTime().atOffset(ZoneOffset.of("Z")).format(formatter) + ": " + user.getName() + " joined!";
    
    Activity activity = new Activity(UUID.randomUUID(), activityContent, user.getCreationTime());
    activityStore.addActivity(activity);
    
    response.sendRedirect("/login");
  }
}
