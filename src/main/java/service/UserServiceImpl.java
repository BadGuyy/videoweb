package service;

import java.sql.SQLException;

import dao.UserDao;
import dao.UserDaoImpl;
import entity.User;

public class UserServiceImpl implements UserService 
{
	private static UserDao userDao = new UserDaoImpl();

	@Override
	public String userLogin(String account, String password) throws SQLException
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
	public void userRegister(String account, String password) throws SQLException
	{
		if(account == null || password == null || account.length() < 8 || password.length() < 8)
			throw new SQLException();
		User user = new User(account, password, "default.png");
		userDao.insert(user);
	}

	@Override
	public void cancelAccount(String account) throws SQLException
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
}
