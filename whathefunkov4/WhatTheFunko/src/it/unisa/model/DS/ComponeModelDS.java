package it.unisa.model.DS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import it.unisa.model.DriverManagerConnectionPool;
import it.unisa.model.OrderBean;
import it.unisa.model.Model.ComponeModel;

public class ComponeModelDS implements ComponeModel {
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

	static boolean isDataSource = true;
	
	private static final String TABLE_NAME = "compone";
	

	@Override
	public void doSave(OrderBean order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQLCompone = " INSERT INTO " + ComponeModelDS.TABLE_NAME 
				+ " (CODICE_ORDINE, N_OGGETTO, PRODUCT_CODE) VALUES (?, ?, ?)";
		try {
			
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQLCompone);
			preparedStatement.setInt(1, order.getOrderCode());
			preparedStatement.setInt(2, order.getOrderQuantity());
			preparedStatement.setInt(3, order.getProductCode());
			preparedStatement.executeUpdate();

			connection.commit();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
	}
	
	

	@Override
	public OrderBean doRetrieveByKey(int orderCode, OrderBean bean) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String selectSQL2 = "SELECT * FROM " + ComponeModelDS.TABLE_NAME + " WHERE CODICE_ORDINE = ? ";
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL2);
			preparedStatement.setInt(1, orderCode);

			ResultSet rs1 = preparedStatement.executeQuery();

			while (rs1.next()) {
				bean.setOrderCode(rs1.getInt("CODICE_ORDINE"));
				bean.setOrderQuantity(rs1.getInt("N_OGGETTO"));
				bean.setProductCode(rs1.getInt("PRODUCT_CODE"));
				//bean.add(orderItem);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}

		return bean;
	}
	
	

	/*@Override
	public ArrayList<OrderBean> doRetrieveAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}*/


}
