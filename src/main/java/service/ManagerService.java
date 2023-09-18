package service;

import java.sql.SQLException;
import java.util.ArrayList;

import entity.User;

public interface ManagerService {
	String managerLogin(String account, String password) throws SQLException;

	void deleteUser(String account) throws SQLException;

	void modifyUser(String tar_Column, Object res, String account) throws SQLException;

	User queryUser(String account) throws SQLException;

	ArrayList<User> queryUsers() throws SQLException;

}