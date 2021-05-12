package it.unisa.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.model.Cart;
import it.unisa.model.ProductBean;


@WebServlet("/CancellaCarrello")
public class CancellaCarrello extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Cart cart= (Cart) request.getSession().getAttribute("cart");
		//necessario per salvare gli ordini prima di rimuoverli completamente dal carrello
		
		
		GregorianCalendar data= new GregorianCalendar();
		
		request.getSession().setAttribute("data", data);
		
		request.getSession().setAttribute("orders", orders);
		
		cart.deleteAll();
		
		request.getSession().removeAttribute("cart");
		
		RequestDispatcher Out= getServletContext().getRequestDispatcher("/CancellaCarrello.jsp");
		Out.forward(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
