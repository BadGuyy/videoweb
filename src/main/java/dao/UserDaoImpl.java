package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import entity.User;

public class UserDaoImpl implements UserDao 
{
	private static DataSource ds = new ComboPooledDataSource("videoweb");
	
	@Override
	public void insert(User user) throws SQLException
	{
		Connection conn = ds.getConnection();

		String sql = "insert into user value(?, ?, ?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, user.getAccount());
		ps.setString(2, user.getPassword());
		ps.setString(3, user.getIcon());
		ps.execute();

		ps.close();
		conn.close();
	}

	@Override
	public void delete(String account) throws SQLException
	{
		Connection conn = ds.getConnection();

		String sql = "delete from user where account = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, account);
		ps.execute();

		ps.close();
		conn.close();
	}

	@Override
	public void modify(String tar_Conlumn, Object res, String account) throws SQLException
	{
		Connection conn = ds.getConnection();

		String sql = "update user set " + tar_Conlumn + " = ? where account = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setObject(1, res);
		ps.setString(2, account);
		ps.execute();

		ps.close();
		conn.close();
	}

	@Override
	public User select(String account) throws SQLException
	{
		Connection conn = ds.getConnection();

		String sql = "select * from user where account = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, account);
		ResultSet rs = ps.executeQuery();
		User user = null;
		while (rs.next()) 
		{
			String password = rs.getString(2);
			String icon = rs.getString(3);
			user = new User(account, password, icon);
		}

		ps.close();
		conn.close();

		return user;
	}
	
	@Override
	public ArrayList<User> selectAll() throws SQLException
	{
		Connection conn = ds.getConnection();

		String sql = "select * from user";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		ArrayList<User> users = new ArrayList<User>();
		while (rs.next())
		{
			String account = rs.getString(1);
			String password = rs.getString(2);
			String icon = rs.getString(3);
			User user = new User(account, password, icon);
			users.add(user);
		}

		ps.close();
		conn.close();

		return users;
	}
}
