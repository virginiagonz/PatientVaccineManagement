package finalProject.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import finalProject.service.UserManager;

@WebServlet("/Create")
public class Create extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Create() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/Create.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String phoneNum = request.getParameter("phoneNum");
		
		UserManager userManager = new UserManager();
		userManager.addUser(firstName, lastName, phoneNum);
		userManager.close();

		response.sendRedirect("Get");
	}

}
