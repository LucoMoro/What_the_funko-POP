package it.unisa.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.model.DS.ProductModelDS;
import it.unisa.model.Model.ProductModel;
import it.unisa.model.Cart;
import it.unisa.model.ProductBean;

@WebServlet("/Prodotto")
public class Prodotto extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	static boolean isDataSource = true;
	
	static ProductModel model= new ProductModelDS();

	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		ProductBean prodotto= null;
		
		try {
			prodotto = model.doRetrieveByKey(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	request.setAttribute("prodotto", prodotto);
	
	RequestDispatcher Out = getServletContext().getRequestDispatcher("/Prodotto.jsp");
	Out.forward (request, response);
	
	}
	
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
