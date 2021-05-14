package it.unisa.model.Model;

import java.sql.SQLException;
import java.util.ArrayList;
//import java.util.Collection;

import it.unisa.model.OrderItem;

public interface OrderItemModel {
	//public void doSave(OrderItem product, int orderCode) throws SQLException;

	//public OrderItem doRetrieveByKey(int orderCode) throws SQLException;
	
	public ArrayList<OrderItem> doRetrieveAll(int x) throws SQLException;

	public void doSaveAll(ArrayList<OrderItem> ordered) throws SQLException;
}
