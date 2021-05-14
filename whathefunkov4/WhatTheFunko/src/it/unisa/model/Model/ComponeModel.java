package it.unisa.model.Model;

import java.sql.SQLException;

import it.unisa.model.OrderBean;


public interface ComponeModel {
	public void doSave(OrderBean user) throws SQLException;

	public OrderBean doRetrieveByKey(int orderCode, OrderBean bean) throws SQLException;
	
	//public ArrayList<OrderBean> doRetrieveAll() throws SQLException;

}
