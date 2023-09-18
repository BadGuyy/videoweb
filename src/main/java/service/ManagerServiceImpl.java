package service;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.UserDao;
import dao.UserDaoImpl;
import entity.User;

public class ManagerServiceImpl implements ManagerService 
{
	private static UserDao userDao = new UserDaoImpl();
	
	@Override
	public String managerLogin(String account, String password) throws SQLException 
	{
		User user = userDao.select(account);
		if(user == null)
			return "账号不存在";
		if (!password.equals(user.getPassword()))
			return "密码错误";
		else
			return "登录成功";
	}

	@Override
	public void deleteUser(String account) throws SQLException
	{
		userDao.delete(account);
	}

	@Override
	public void modifyUser(String tar_Column, Object res, String account) throws SQLException
	{
		userDao.modify(tar_Column, res, account);
	}
	
	@Override
	public User queryUser(String account) throws SQLException
	{
		User user = userDao.select(account);
		return user;
	}

	@Override
	public ArrayList<User> queryUsers() throws SQLException
	{
		ArrayList<User> users = userDao.selectAll();
		return users;
	}
}
