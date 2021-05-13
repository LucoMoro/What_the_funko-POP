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
import it.unisa.model.OrderItem;
import it.unisa.model.ProductBean;
import it.unisa.model.UserBean;
import it.unisa.model.DS.OrderItemModelDS;
import it.unisa.model.DS.OrderModelDS;
import it.unisa.model.Model.OrderItemModel;
import it.unisa.model.Model.OrderModel;


@WebServlet("/CancellaCarrello")
public class CancellaCarrello extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static OrderModel model = new OrderModelDS();
	private static OrderItemModel model2 = new OrderItemModelDS();
	private CartItem cartItem;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int gg, mm, yy;
		
		Cart cart= (Cart) request.getSession().getAttribute("cart");
		//necessario per salvare gli ordini prima di rimuoverli completamente dal carrello
		
		ArrayList<CartItem> cartList= cart.getProducts();
		
		ArrayList<OrderItem> orderItem = new ArrayList<OrderItem>();
		
		for(int i=0; i<cartList.size(); i++) {
			OrderItem oitem = new OrderItem(cartList.get(i));
			orderItem.add(oitem);
		}
		
		OrderBean orderBean = new OrderBean();
		
		orderBean.setList(orderItem);
		
		UserBean user= (UserBean) request.getSession().getAttribute("utente");
		
		orderBean.setID(user.getID());
		
		GregorianCalendar data= new GregorianCalendar();
		
		gg=data.get(Calendar.DATE);
		mm=data.get(Calendar.MONTH)+1;
		yy=data.get(Calendar.YEAR);
		
		orderBean.setDataOrdine("" + gg + "/" + mm + "/" + yy);
		
		try {
			model.doSave(orderBean);
			System.out.println("fatto");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("fatto2");
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
