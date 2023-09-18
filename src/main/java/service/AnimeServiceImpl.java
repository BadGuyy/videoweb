package service;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.AnimeDao;
import dao.AnimeDaoImpl;
import entity.Anime;

public class AnimeServiceImpl implements AnimeService 
{
	private static AnimeDao animeDao = new AnimeDaoImpl();

	@Override
	public void addAnime(String name) throws SQLException
	{
		animeDao.insert(name);
	}
	
	@Override
	public void deleteAnime(String name) throws SQLException
	{
		animeDao.delete(name);
	}

	@Override
	public void modifyAnime(String tar_Comlumn, Object res, int id) throws SQLException
	{
		animeDao.modify(tar_Comlumn, res, id);
	}

	@Override
	public Anime queryAnime(int id) throws SQLException
	{
		Anime anime = animeDao.select(id);
		return anime;
	}

	@Override
	public ArrayList<Anime> queryPartAnimes(int begin, int offset) throws SQLException
	{
		ArrayList<Anime> animes = animeDao.selectPart((begin - 1) * offset, offset);
		return animes;
	}

	@Override
	public ArrayList<Anime> queryAnimes() throws SQLException
	{
		ArrayList<Anime> animes = animeDao.selectAll();
		return animes;
	}

	@Override
	public ArrayList<Anime> searchAnimes(String[] text) throws SQLException
	{
		ArrayList<Anime> animes = animeDao.searchAnimes(text);
		return animes;
	}
}
