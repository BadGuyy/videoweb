package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import entity.Anime;

public class AnimeDaoImpl implements AnimeDao 
{
	private static DataSource ds = new ComboPooledDataSource("videoweb");

	@Override
	public void insert(String name) throws SQLException
	{
		Connection conn = ds.getConnection();

		int size = selectAll().size() + 1;

		String sql = "insert into anime value(?, ?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, size);
		ps.setString(2, name);
		ps.execute();

		ps.close();
		conn.close();
	}

	@Override
	public void delete(String name) throws SQLException
	{
		Connection conn = ds.getConnection();

		String sql = "delete from anime where name = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, name);
		ps.execute();

		ps.close();
		conn.close();
	}

	@Override
	public void modify(String tar_Conlumn, Object res, int id) throws SQLException
	{
		Connection conn = ds.getConnection();

		String sql = "update anime set " + tar_Conlumn + " = ? where id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setObject(1, res);
		ps.setInt(2, id);
		ps.execute();

		ps.close();
		conn.close();
	}

	@Override
	public Anime select(int id) throws SQLException
	{
		Connection conn = ds.getConnection();

		String sql = "select * from anime where id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		Anime anime = null;
		while (rs.next()) 
		{
			String name = rs.getString(2);
			anime = new Anime(id, name);
		}

		ps.close();
		conn.close();

		return anime;
	}
	
	@Override
	public ArrayList<Anime> selectPart(int begin, int offset) throws SQLException
	{
		Connection conn = ds.getConnection();

		String sql = "select * from anime limit " + begin + "," + offset;
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		ArrayList<Anime> animes = new ArrayList<Anime>();
		while (rs.next())
		{
			int id = rs.getInt(1);
			String name = rs.getString(2);
			Anime anime = new Anime(id, name);
			animes.add(anime);
		}

		ps.close();
		conn.close();

		return animes;
	}

	@Override
	public ArrayList<Anime> selectAll() throws SQLException
	{
		Connection conn = ds.getConnection();

		String sql = "select * from anime";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		ArrayList<Anime> animes = new ArrayList<Anime>();
		while (rs.next())
		{
			int id = rs.getInt(1);
			String name = rs.getString(2);
			Anime anime = new Anime(id, name);
			animes.add(anime);
		}

		ps.close();
		conn.close();

		return animes;
	}

	@Override
	public ArrayList<Anime> searchAnimes(String[] text) throws SQLException
	{
		Connection conn = ds.getConnection();

		String sql = "";
		for(int i = 0; i < text.length; ++i)
		{
			if(i != text.length - 1)
				sql += "select * from anime where name like '%" + text[i] + "%' union ";
			else
				sql += "select * from anime where name like '%" + text[i] + "%'";
		}

		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		ArrayList<Anime> animes = new ArrayList<Anime>();
		while (rs.next())
		{
			int id = rs.getInt(1);
			String name = rs.getString(2);
			Anime anime = new Anime(id, name);
			animes.add(anime);
		}

		return animes;
	}
}
