
package codeu.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import codeu.model.data.Activity;
import codeu.model.store.basic.ActivityStore;
import java.util.List;

public class ActivityFeedServlet extends HttpServlet {
	
   /** Store class that gives access to Activities. */
   private ActivityStore activityStore;

   /** Set up state for handling Activity requests. */
   @Override
   public void init() throws ServletException {
     super.init();
     setActivityStore(ActivityStore.getInstance());
   }

   /**
    * Sets the ActivityStore used by this servlet. This function provides a common setup method
    * for use by the test framework or the servlet's init() function.
    */
   void setActivityStore(ActivityStore activityStore) {
     this.activityStore = activityStore;
   }
	
   /**
    * This function fires when a user requests the /activityfeed URL. It simply forwards the request to
    * activityfeed.jsp.
    */
   @Override
   public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
	 List<Activity> activities = activityStore.getActivityFeedContent();
	 request.setAttribute("activities", activities);
     request.getRequestDispatcher("/WEB-INF/view/activityfeed.jsp").forward(request, response);
   }
}
