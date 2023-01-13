package finalProject.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import finalProject.service.UserManager;

@WebServlet("/Update")
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Update() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("userId"));

		UserManager userManager = new UserManager();
		request.setAttribute("userEntry", userManager.getUserEntry(id));
		userManager.close();

		request.getRequestDispatcher("/WEB-INF/Update.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("userId"));
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");

		UserManager userManager = new UserManager();
		userManager.updateUser(id, firstName, lastName);
		userManager.close();

		response.sendRedirect("Get");
	}

}
