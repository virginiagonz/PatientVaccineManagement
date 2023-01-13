package finalProject.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import finalProject.service.ContactManager;
import finalProject.service.UserManager;

@WebServlet(urlPatterns = "/Get", loadOnStartup = 1)
public class Get extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Get() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		UserManager userManager = new UserManager();
		request.setAttribute("userEntries", userManager.getUserEntries());
		userManager.close();
		
		ContactManager contactManager = new ContactManager();
		request.setAttribute("contactEntries", contactManager.getContactEntries());
		contactManager.close();
		
		request.getRequestDispatcher("/WEB-INF/Get.jsp").forward(request, response);
	}

}
