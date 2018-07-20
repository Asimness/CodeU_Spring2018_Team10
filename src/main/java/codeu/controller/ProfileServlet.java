package codeu.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Set;

import java.time.LocalDate;
import java.time.Period;


import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.mindrot.jbcrypt.BCrypt;

import com.google.appengine.api.datastore.Text;
import com.google.appengine.repackaged.com.google.common.io.Files;

import codeu.model.data.User;
import codeu.model.store.basic.UserStore;


@MultipartConfig
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
	User u = userStore.getUser(username);
	
	if (action != null && action.equals("EditAboutMe")) {
		
	// Gets the text submitted by the user in the aboutme form
    String aboutMe = request.getParameter("aboutme");
    System.out.println(action);
    // Changes to the about me of the user 
    userStore.getUser(username).setAboutMe(aboutMe);
    
    // Updates the user in the persistent data
    userStore.updateUser(userStore.getUser(username));
    response.sendRedirect("/user/" + username);
	} else if (action != null && action.equals("EditProfilePicture")) {
		System.out.println(action);
    
		Part file = request.getPart("pic");
		
		// System.out.println(filePart.getSize() + "");
		// String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

		InputStream content = file.getInputStream();
		
		byte[] buffer = new byte[content.available()];
		content.read(buffer, 0, content.available());
		
		String base64 = Base64.getEncoder().encodeToString(buffer);
		Text t = new Text(base64);
		u.setProfilePic(t);
		userStore.updateUser(u);
		
		response.sendRedirect("/user/" + username);
	} else if (action != null && action.equals("EditTheme")) {

        System.out.println(action);

        // Get the theme they selected
        String selected = request.getParameter("Theme");
        u.setTheme(selected);

      /*
      // Set the theme variable accordingly
      if (selected.equals("one")) {
          u.setTheme("");
      } else if (selected.equals("two")) {
          u.setTheme("body{color:white;background-color:#4ABDAC;}.navbar{background-color:#FC4A1A;}h1{color:white;}");
          System.out.println("changing to two");
      } else if (selected.equals("three")) {
          u.setTheme("body{color:white;background-color:#9099A2;}.navbar{background-color:#6d7993;}h1{color:white;}");
          System.out.println("changing to three");
      } else if (selected.equals("four")) {
          u.setTheme("body{color:white;background-color:#636B46;}.navbar{background-color:#CDA34F;}h1{color:white;}");
          System.out.println("changing to four");
      } */

        //Send the redirect
        userStore.updateUser(u);
        response.sendRedirect("/user/" + username);
    }
  }
}
