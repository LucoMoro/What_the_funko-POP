package it.unisa.model.DS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
//import java.util.Collection;
//import java.util.LinkedList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import it.unisa.model.DriverManagerConnectionPool;
import it.unisa.model.OrderItem;
import it.unisa.model.Model.OrderItemModel;

public class OrderItemModelDS implements OrderItemModel {
private static DataSource ds;
	
	static {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");

			ds = (DataSource) envCtx.lookup("jdbc/storage");

		} catch (NamingException e) {
			System.out.println("Error:" + e.getMessage());
		}
	}
	
	private static final String TABLE_NAME = "compone";

	@Override
	public void doSave(OrderItem product, int orderCode) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL= "INSERT INTO" + OrderItemModelDS.TABLE_NAME + 
				"( N_OGGETTO, PRODUCT_CODE) VALUES (?, ?)";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			//preparedStatement.setString(1, product.getOrderCode());
			preparedStatement.setInt(2, product.getOrderQuantity());
			preparedStatement.setInt(3, product.getProductCode());
			preparedStatement.executeUpdate();

			connection.commit();
		}

		finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}

	}

	@Override
	public OrderItem doRetrieveByKey(int orderCode) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		OrderItem orderItem= new OrderItem();

		String selectSQL = "SELECT * FROM " + OrderItemModelDS.TABLE_NAME + " WHERE CODICE_ORDINE = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, orderCode);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				orderItem.setOrderCode(rs.getString("CODICE_ORDINE"));
				orderItem.setOrderQuantity(rs.getInt("N_OGGETTO"));
				orderItem.setProductCode(rs.getInt("PRODUCT_CODE"));
			}
			
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		return orderItem;
	
	}


	@Override
	public ArrayList<OrderItem> doRetrieveAll() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		ArrayList <OrderItem> order= new ArrayList<OrderItem>();
		
		OrderItem ordered = new OrderItem();

		String selectSQL = "SELECT * FROM " + OrderItemModelDS.TABLE_NAME;


		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) { //vado ad inserire i valori
				ordered.setOrderCode(rs.getString("CODICE_ORDINE"));
				ordered.setOrderQuantity(rs.getInt("N_OGGETTO"));
				ordered.setProductCode(rs.getInt("PRODUCT_CODE"));
				order.add(ordered);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return order;
	}

}
