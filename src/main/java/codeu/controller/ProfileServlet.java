package codeu.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

import codeu.model.data.User;
import codeu.model.store.basic.UserStore;

public class ProfileServlet extends HttpServlet{
	
  /** Store class that gives access to Users. */
  private UserStore userStore;

  /**
   * Set up state for handling login-related requests. This method is only called when running in a
   * server, not when running in a test.
   */
  @Override
  public void init() throws ServletException {
    super.init();
    setUserStore(UserStore.getInstance());
  }

  /**
   * Sets the UserStore used by this servlet. This function provides a common setup method for use
   * by the test framework or the servlet's init() function.
   */
  void setUserStore(UserStore userStore) {
    this.userStore = userStore;
  }

  /**
   * This function fires when a user requests the /user/* URL. It simply forwards the request to
   * profile.jsp.
   */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
	  request.getRequestDispatcher("/WEB-INF/view/profile.jsp").forward(request, response);
	}
	
  /**
   * This function fires when a user submits about me form. It gets the about me text from the 
   * submitted form data. User should only be able to edit their about me when viewing their
   * personal profile page.
   */
  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

	// Gets the name of the user that is currently logged in  
	String username = (String) request.getSession().getAttribute("user");
	String action = (String) request.getParameter("EditProfilePage");
	
	if (action.equals("EditAboutMe")) {
		System.out.println(action);
	// Gets the text submitted by the user in the aboutme form
    String aboutMe = request.getParameter("aboutme");
    
    // Changes to the about me of the user 
    userStore.getUser(username).setAboutMe(aboutMe);
    
    // Updates the user in the persistent data
    userStore.updateUser(userStore.getUser(username));
    response.sendRedirect("/user/" + username);
	} else if (action.equals("EditProfilePicture")) {
		System.out.println(action);
		LocalDate endofCentury = LocalDate.of(2014, 01, 01);
		LocalDate now = LocalDate.now();
		 
		Period diff = Period.between(endofCentury, now);
		 
		String s = String.format("Difference is %d years, %d months and %d days old",
		                    diff.getYears(), diff.getMonths(), diff.getDays());
		System.out.println(s);
		
		response.sendRedirect("/user/" + username);
	}
  }
}
