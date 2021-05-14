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

public class OrderModelDS implements OrderModel 
{
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

	
	private static final String TABLE_NAME = "ordine";
	private static final String TABLE_NAME2 = "compone";

	//salviamo nel DB l'ordine order
	public synchronized void doSave(OrderBean order) throws SQLException {
		int orderCode=0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertSQL = "INSERT INTO " + OrderModelDS.TABLE_NAME
				+ " (ID, DATA_CONSEGNA, IVA, DATA_ORDINE) VALUES (?, ?, ?, ?)";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, order.getID());
			preparedStatement.setString(2, order.getDataConsegna());
			preparedStatement.setDouble(3, order.getIva());
			preparedStatement.setString(4, order.getDataOrdine());
			
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
		ArrayList <OrderItem> ordered= order.getOrderItems();
		for(OrderItem o : ordered)
		{
			o.setOrderCode(orderCode);
		}
		model.doSaveAll(ordered);
	}


	//@Override
	public synchronized OrderBean doRetrieveByKey(String ID, int orderCode) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;


		OrderBean bean =  new OrderBean();
		ArrayList<OrderItem> arrayOrder = new ArrayList <OrderItem>();

		String selectSQL = "SELECT * FROM " + OrderModelDS.TABLE_NAME + " WHERE ID = ?";

		try 
		{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, ID);

			ResultSet rs = preparedStatement.executeQuery();

			while(rs.next())
			{
				if(rs.getInt("CODICE_ORDINE") == orderCode)
				{
					bean.setID(rs.getString("ID"));
					bean.setDataConsegna(rs.getString("DATA_CONSEGNA"));
					bean.setOrderCode(rs.getInt("CODICE_ORDINE"));
					bean.setDataConsegna(rs.getString("DATA_ORDINE"));
					
					arrayOrder = model.doRetrieveAll(orderCode);
					bean.setList(arrayOrder);
				}	
			}
		} 
		finally{
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}

		
		return bean;
	}


	//preleviamo tutti gli ordini di un determinato utente ID
	public synchronized ArrayList<OrderBean> doRetrieveAll(String ID) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList <OrderBean> orders= new ArrayList<OrderBean>();

		String selectSQL = "SELECT * FROM " + OrderModelDS.TABLE_NAME + "WHERE ID = ?";

		try
		{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, ID);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) 
			{
				OrderBean bean = new OrderBean();
				bean.setID(rs.getString("ID"));
				bean.setDataConsegna(rs.getString("DATA_CONSEGNA"));
				bean.setOrderCode(rs.getInt("CODICE_ORDINE"));
				bean.setDataOrdine(rs.getString("DATA_ORDINE"));
				bean.setList(model.doRetrieveAll(bean.getOrderCode()));
				// abbiamo completato il prelievo dei parametri del bean singolo
				orders.add(bean); // aggiungiamo l'order item all' arrayList
			} 
		}finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return orders;
		}
	}











