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
import javax.servlet.http.HttpSession;

import it.unisa.model.UserBean;
import it.unisa.model.UserModel;
import it.unisa.model.UserModelDM;
import it.unisa.model.UserModelDS;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static boolean isDataSource = true;

	static UserModel model;

	static {
		if (isDataSource) {
			model =  new UserModelDS();
		} else {
			model = new UserModelDM();
		}
	}

    public LoginServlet() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ID= request.getParameter("ID");
		String password= request.getParameter("password");
		UserBean user= new UserBean();
		HttpSession Sessione = request.getSession(true);
		RequestDispatcher out;
		String Action=(String) request.getSession().getAttribute("Action");
		request.getSession().setAttribute("Controllo", "vuoto");
	
		if (exists(ID, password)) {			
			try {
				user= model.doRetrieveByKey(ID);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			Sessione.setAttribute("utente", user);
			request.setAttribute("utente", user);
			
		}
		
		else {
			request.getSession().setAttribute("Controllo", "login");
			out =getServletContext().getRequestDispatcher("/Error.jsp");
			out.forward(request, response);	
			return;
		}
		
		
		
		
		if(Action.contentEquals("buy")) {
			out =getServletContext().getRequestDispatcher("/Carrello.jsp");
			out.forward(request, response);
			return;
		}
		
		else if (Action.contentEquals("view")) {
			out =getServletContext().getRequestDispatcher("/ProductView.jsp");
			out.forward(request, response);
			return;
		}
		
		/*else if (Action.contentEquals("review")) {
			out =getServletContext().getRequestDispatcher("/Review.jsp");
			out.forward(request, response);
			return;
		}*/
		
	}
	
	
	
	private static boolean exists(String ID, String password) {
		ArrayList <UserBean>  users= null;
		UserBean user= new UserBean();
		try {
			users = model.doRetrieveAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (int i=0; i<users.size(); i++) {
			user= users.get(i);
			
			if (ID.contentEquals(user.getID()) || password.contentEquals(user.getPassword())){
				return true;
			}
		}
		
		return false;
	}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
