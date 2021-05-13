package it.unisa.model.Model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import it.unisa.model.UserBean;

public interface UserModel {
	public void doSave(UserBean user) throws SQLException;

	public UserBean doRetrieveByKey(String ID) throws SQLException;
	
	public ArrayList<UserBean> doRetrieveAll() throws SQLException;
}
