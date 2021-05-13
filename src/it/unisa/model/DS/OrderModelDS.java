package it.unisa.model.DS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import it.unisa.model.DriverManagerConnectionPool;
import it.unisa.model.OrderBean;
import it.unisa.model.OrderItem;
import it.unisa.model.Model.OrderItemModel;
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

	static OrderItemModel model;

	/*static {
		if (isDataSource) {
			model = new OrderItemModelDS();
		} else {
			model = new OrderItemModelDM();
		}
	}*/

	
	private static final String TABLE_NAME = "ordine";
	private static final String TABLE_NAME2 = "compone";

	//@Override
	public synchronized void doSave(OrderBean user) throws SQLException {
		int orderCode=0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertSQL = "INSERT INTO " + OrderModelDS.TABLE_NAME
				+ " (ID, DATA_CONSEGNA, IVA, DATA_ORDINE) VALUES (?, ?, ?, ?)";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, user.getID());
			preparedStatement.setString(2, user.getDataConsegna());
			preparedStatement.setDouble(3, user.getIva());
			preparedStatement.setString(4, user.getDataOrdine());
			preparedStatement.executeUpdate();
			
			ResultSet rs= preparedStatement.getGeneratedKeys();
			if (rs.next()) {
				orderCode=rs.getInt(1);
			}
			
			connection.commit();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}

		ArrayList <OrderItem> ordered= user.getOrderItems();
		for(int i=0; i<ordered.size(); i++) {
			model.doSave(ordered.get(i), orderCode);
		} 

	}


	//@Override
	public synchronized OrderBean doRetrieveByKey(String ID, int orderCode) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Connection connection1 = null;
		PreparedStatement preparedStatement1 = null;

		OrderBean bean = new OrderBean();
		OrderItem orderItem = new OrderItem();
		ArrayList<OrderItem> arrayOrder = new ArrayList <OrderItem>();

		String selectSQL = "SELECT * FROM " + OrderModelDS.TABLE_NAME + " WHERE ID = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, ID);

			ResultSet rs = preparedStatement.executeQuery();

			//while (rs.next()) {
				bean.setID(rs.getString("ID"));
				bean.setDataConsegna(rs.getString("DATA_CONSEGNA"));
				bean.setOrderCode(rs.getInt("CODICE_ORDINE"));
			//	bean.setIva(rs.getDouble("iva"));
				bean.setDataConsegna(rs.getString("DATA_ORDINE"));

				String selectSQL2 = "SELECT * FROM " + OrderModelDS.TABLE_NAME2 + " WHERE CODICE_ORDINE = ?";
				try {
					connection1 = DriverManagerConnectionPool.getConnection();
					preparedStatement1 = connection1.prepareStatement(selectSQL2);
					preparedStatement1.setInt(1, orderCode);

					ResultSet rs1 = preparedStatement1.executeQuery();

					while (rs1.next()) {
						orderItem.setOrderCode(rs1.getInt("CODICE_ORDINE"));
						orderItem.setOrderQuantity(rs1.getInt("N_OGGETTO"));
						orderItem.setProductCode(rs1.getInt("PRODUCT_CODE"));
						arrayOrder.add(orderItem);
					}

				} finally {
					try {
						if (preparedStatement1 != null)
							preparedStatement1.close();
					} finally {
						DriverManagerConnectionPool.releaseConnection(connection1);
					}
				}

			//}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		bean.setList(arrayOrder);
		return bean;
	}


	//@Override
	public synchronized ArrayList<OrderBean> doRetrieveAll(String ID) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Connection connection1 = null;
		PreparedStatement preparedStatement1 = null;

		ArrayList <OrderBean> users= new ArrayList<OrderBean>();
		OrderItem orderItem = new OrderItem();
		ArrayList<OrderItem> arrayOrder = new ArrayList <OrderItem>();

		String selectSQL = "SELECT * FROM " + OrderModelDS.TABLE_NAME + "WHERE ID = ?";


		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, ID);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				OrderBean bean = new OrderBean();
				bean.setID(rs.getString("ID"));
				bean.setDataConsegna(rs.getString("DATA_CONSEGNA"));
				bean.setOrderCode(rs.getInt("CODICE_ORDINE"));
				//bean.setIva(rs.getDouble("IVA"));
				bean.setDataOrdine(rs.getString("DATA_ORDINE"));
				
				String selectSQL2 = "SELECT * FROM " + OrderModelDS.TABLE_NAME2 + " WHERE CODICE_ORDINE= ?";
				try {
					connection1 = DriverManagerConnectionPool.getConnection();
					preparedStatement1 = connection1.prepareStatement(selectSQL2);
					preparedStatement1.setInt(1, bean.getOrderCode());

					ResultSet rs1 = preparedStatement1.executeQuery();

					while (rs1.next()) {
						orderItem.setOrderCode(rs1.getInt("CODICE_ORDINE"));
						orderItem.setOrderQuantity(rs1.getInt("N_OGGETTO"));
						orderItem.setProductCode(rs1.getInt("PRODUCT_CODE"));
						arrayOrder.add(orderItem);
					}

				} finally {
					try {
						if (preparedStatement1 != null)
							preparedStatement1.close();
					} finally {
						DriverManagerConnectionPool.releaseConnection(connection1);
					}
				}
				
				bean.setList(arrayOrder);
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








