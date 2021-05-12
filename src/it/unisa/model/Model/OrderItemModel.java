package it.unisa.model.Model;

import java.sql.SQLException;
import java.util.ArrayList;
//import java.util.Collection;

import it.unisa.model.OrderItem;

public interface OrderItemModel {
	public void doSave(OrderItem product, int orderCode) throws SQLException;

	public OrderItem doRetrieveByKey(int code) throws SQLException;
	
	public ArrayList<OrderItem> doRetrieveAll() throws SQLException;
}
