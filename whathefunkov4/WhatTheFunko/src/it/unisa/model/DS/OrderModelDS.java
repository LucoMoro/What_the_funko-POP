package it.unisa.model.DS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import it.unisa.model.DriverManagerConnectionPool;
import it.unisa.model.OrderBean;
import it.unisa.model.Model.ComponeModel;
import it.unisa.model.Model.OrderModel;

public class OrderModelDS implements OrderModel {
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

	static ComponeModelDS model= new ComponeModelDS();

	private static final String TABLE_NAME = "ordine";
	
	//@Override
	public synchronized void doSave(OrderBean order) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertSQLOrder = "INSERT INTO " + OrderModelDS.TABLE_NAME
				+ " (ID, DATA_CONSEGNA, IVA, DATA_ORDINE) VALUES (?, ?, ?, ?)";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQLOrder);
			preparedStatement.setString(1, order.getID());
			preparedStatement.setString(2, order.getDataConsegna());
			preparedStatement.setDouble(3, order.getIva());
			preparedStatement.setString(4, order.getDataOrdine());
			preparedStatement.executeUpdate();
			
			connection.commit();
			
			for (i=0; i<)
			model.doSave(order);
			
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		
		
	}
	
	


	//@Override
	public synchronized OrderBean doRetrieveByKey(String ID, int orderCode) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
	
		OrderBean bean = new OrderBean();

		String selectSQL = "SELECT * FROM " + OrderModelDS.TABLE_NAME + " WHERE ID = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, ID);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				if (rs.getInt("ORDER_CODE")==orderCode) {
				bean.setID(rs.getString("ID"));
				bean.setDataConsegna(rs.getString("DATA_CONSEGNA"));
				bean.setOrderCode(rs.getInt("CODICE_ORDINE"));
				bean.setDataConsegna(rs.getString("DATA_ORDINE"));

				model.doRetrieveByKey(orderCode, bean);
				
				}
				
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

	
	

	//@Override
	public synchronized ArrayList<OrderBean> doRetrieveAll(String ID) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList <OrderBean> users= new ArrayList<OrderBean>();

		String selectSQL = "SELECT * FROM " + OrderModelDS.TABLE_NAME + " WHERE ID = ?";


		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, ID);
			System.out.println(preparedStatement.toString());
			
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				OrderBean bean = new OrderBean();
				bean.setID(rs.getString("ID"));
				bean.setDataConsegna(rs.getString("DATA_CONSEGNA"));
				bean.setOrderCode(rs.getInt("CODICE_ORDINE"));
				bean.setDataOrdine(rs.getString("DATA_ORDINE"));
				
				model.doRetrieveByKey(bean.getOrderCode(), bean);

				users.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return users;
	}


	//////////////////////////////////////////////////////////////////////////

	/*public ArrayList<OrderBean> getOrini(String ID) throws SQLException {
		ArrayList <OrderBean> Orders= new ArrayList<OrderBean>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		OrderBean bean = new OrderBean();

		String selectSQL = "SELECT * FROM " + OrderModelDS.TABLE_NAME + " WHERE ID = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, ID);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) { bean= doRetrieveByKey(ID);
			Orders.add(bean);

			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return Orders;
	}*/

}








