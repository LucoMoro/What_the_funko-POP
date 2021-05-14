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
import it.unisa.model.DS.ProductModelDS;
import it.unisa.model.Model.OrderItemModel;
import it.unisa.model.Model.OrderModel;


@WebServlet("/CancellaCarrello")
public class Checkout extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static OrderModel model = new OrderModelDS();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int gg, mm, yy;
		
		Cart cart= (Cart) request.getSession().getAttribute("cart");
		
		ArrayList<CartItem> cartList= cart.getProducts();
		
		ArrayList<OrderItem> orderItems = new ArrayList<OrderItem>();
		
		for(int i=0; i<cartList.size(); i++) {
			OrderItem item = new OrderItem(cartList.get(i));
			orderItems.add(item);
		}
		
		OrderBean orderBean = new OrderBean();
		
		orderBean.setList(orderItems);
		
		UserBean user= (UserBean) request.getSession().getAttribute("utente");
		orderBean.setID(user.getID());
		
		GregorianCalendar data= new GregorianCalendar();
		
		gg=data.get(Calendar.DATE);
		mm=data.get(Calendar.MONTH)+1;
		yy=data.get(Calendar.YEAR);
		
		orderBean.setDataOrdine("" + gg + "/" + mm + "/" + yy);
		
		try {
			model.doSave(orderBean);
			System.out.println("Okay");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(" Errore");
		}
		
				
		cart.deleteAll();
		request.getSession().removeAttribute("cart");
		
		ProductModelDS model2 = new ProductModelDS();
		
		ArrayList<OrderBean> orders = null;
		 
		try {
			 orders = model.doRetrieveAll(user.getID());
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(" Errore 2");
		}
		
		//orders -> ArrayList<OrderItem> -> product_code -> doRetrieveByKey(product_code) ->  ProductBean -> ArrayList<CartItem>
		
		ArrayList<ProductBean> prodotti = new ArrayList<ProductBean> ();
		ProductBean tmp = null;
		CartItem item = null;
		Cart miniCart = new Cart();
		ArrayList<Cart> bigCart = new ArrayList<Cart> ();
		
		ArrayList<OrderItem> order;
		for(int i = 0 ; i<orders.size(); i++)
		{
			//scorriamo i vari OrderItems che presentano al loro interno i vari CartItems
			order = orders.get(i).getOrderItems();
			for(int j = 0; j<order.size(); j++)
			{
				//per un determinato OrderItem dell' arrayList <OrderItems> prendiamo tutti i CartItem di cui è costituito
				int code = order.get(j).getOrderCode();
				miniCart = new Cart();
				try 
				{
					
					tmp = model2.doRetrieveByKey(code);
					item = new CartItem(tmp);
					item.setQuantity(order.get(j).getOrderQuantity());
					miniCart.addItem(item);
				}
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
			//una volta finito di salvare tutti i vari CartItem di uno specifico OrderItem j, vado ad inserire il Cart nell' arrayList di Cart
			bigCart.add(miniCart);
		}
		
		request.getSession().setAttribute("ordini", orders);
		request.getSession().setAttribute("bigCart", bigCart);
		
		
		
		RequestDispatcher Out= getServletContext().getRequestDispatcher("/Checkout.jsp");
		Out.forward(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{	
		doGet(request, response);
	}

}
