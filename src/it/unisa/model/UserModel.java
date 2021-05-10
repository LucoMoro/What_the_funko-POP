package it.unisa.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public interface UserModel {
	public void doSave(UserBean user) throws SQLException;

	public UserBean doRetrieveByKey(String ID) throws SQLException;
	
	public ArrayList<UserBean> doRetrieveAll() throws SQLException;
}
