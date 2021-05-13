package it.unisa.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.model.UserBean;
import it.unisa.model.DS.UserModelDS;
import it.unisa.model.Model.UserModel;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
		
	static boolean isDataSource = true;

	static UserModel model= new UserModelDS();


public RegisterServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ID= request.getParameter("ID");
		String password= request.getParameter("password");
		String email=request.getParameter("email");
		RequestDispatcher out;
		request.getSession().setAttribute("Controllo", "vuoto");
		
		if (exists(ID, email)) {
			/*request.setAttribute("ID", ID);
			request.setAttribute("password", password);
			request.setAttribute("email", email);*/
			request.getSession().setAttribute("Controllo", "register");
			out=getServletContext().getRequestDispatcher("/Error.jsp");
			out.forward(request, response);
		}
		
		else { UserBean user= new UserBean();
		user.setID(ID);
		user.setPassword(password);
		user.setEmail(email);
		try {
			model.doSave(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		out =getServletContext().getRequestDispatcher("/Login.jsp");
		out.forward(request, response);
		}
		
	}
	

	private static boolean exists(String ID, String email) {
		ArrayList <UserBean>  users= null;
		UserBean user= new UserBean();
		try {
			users = model.doRetrieveAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (int i=0; i<users.size(); i++) {
			user= users.get(i);
			
			if (ID.contentEquals(user.getID()) || email.contentEquals(user.getEmail())){
				return true;
			}
		}
		
		return false;
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
