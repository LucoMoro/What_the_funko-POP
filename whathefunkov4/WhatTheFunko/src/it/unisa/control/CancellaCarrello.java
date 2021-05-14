package it.unisa.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.model.Cart;
import it.unisa.model.CartItem;
import it.unisa.model.OrderBean;
import it.unisa.model.ProductBean;
import it.unisa.model.UserBean;
import it.unisa.model.DS.OrderModelDS;
import it.unisa.model.Model.OrderModel;


@WebServlet("/CancellaCarrello")
public class CancellaCarrello extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static OrderModelDS model = new OrderModelDS();
	//private CartItem cartItem;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int gg, mm, yy;

		Cart cart= (Cart) request.getSession().getAttribute("cart");
		//necessario per salvare gli ordini prima di rimuoverli completamente dal carrello

		ArrayList<CartItem> cartList= cart.getProducts();

		OrderBean orderBean = new OrderBean();

		UserBean user= (UserBean) request.getSession().getAttribute("utente");

		orderBean.setID(user.getID());

		ProductBean tmp= null;

		GregorianCalendar data= new GregorianCalendar();

		gg=data.get(Calendar.DATE);
		mm=data.get(Calendar.MONTH)+1;
		yy=data.get(Calendar.YEAR);

		orderBean.setDataOrdine("" + gg + "/" + mm + "/" + yy);

		for(int i=0; i<cartList.size(); i++) 
		{
			tmp=cartList.get(i).getProduct(); //vado a mettere il prodotto all'interno di tmp
			orderBean.setProductCode(tmp.getCode()); //vado a mettere il codice del prodotto presente in cartList all'interno di orderBean
			orderBean.setOrderQuantity(cartList.get(i).getQuantity()); //vado a mettere la quantità del prodotto presente in cartList all'interno 
			//di orderBean

			try {
				model.doSave(orderBean);
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("fatto2");
			}
		}
		
		

		cart.deleteAll();

		request.getSession().removeAttribute("cart");

		RequestDispatcher Out= getServletContext().getRequestDispatcher("/CancellaCarrello.jsp");
		Out.forward(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
