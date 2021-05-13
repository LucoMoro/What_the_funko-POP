package it.unisa.model.DS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import it.unisa.model.DriverManagerConnectionPool;
import it.unisa.model.UserBean;
import it.unisa.model.Model.UserModel;

public class UserModelDS implements UserModel {
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

	private static final String TABLE_NAME = "cliente";
	
	//@Override
	public synchronized void doSave (UserBean user) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + UserModelDS.TABLE_NAME
				+ " (ID, ID_PASSWORD, EMAIL) VALUES (?, ?, ?)";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, user.getID());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setString(3, user.getEmail());
			
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
	

	public synchronized UserBean doRetrieveByKey(String ID) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		UserBean bean = new UserBean();

		String selectSQL = "SELECT * FROM " + UserModelDS.TABLE_NAME + " WHERE ID = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, ID);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setID(rs.getString("ID"));
				bean.setPassword(rs.getString("ID_PASSWORD"));
				bean.setEmail(rs.getString("EMAIL"));
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


	public synchronized ArrayList<UserBean> doRetrieveAll() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		ArrayList <UserBean> users= new ArrayList<UserBean>();

		String selectSQL = "SELECT * FROM " + UserModelDS.TABLE_NAME;


		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				UserBean bean = new UserBean();
				bean.setID(rs.getString("ID"));
				bean.setPassword(rs.getString("ID_PASSWORD"));
				bean.setEmail(rs.getString("EMAIL"));
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
	
		
}
