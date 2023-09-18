package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import entity.User;

public interface UserDao {

	void insert(User user) throws SQLException;

	void delete(String account) throws SQLException;

	void modify(String tar_Conlumn, Object res, String account) throws SQLException;

	User select(String account) throws SQLException;

	ArrayList<User> selectAll() throws SQLException;

}