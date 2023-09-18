package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import entity.Anime;

public interface AnimeDao {

	void insert(String name) throws SQLException;

	void delete(String name) throws SQLException;

	void modify(String tar_Conlumn, Object res, int id) throws SQLException;

	Anime select(int id) throws SQLException;

	ArrayList<Anime> selectPart(int begin, int offset) throws SQLException;

	ArrayList<Anime> selectAll() throws SQLException;

	ArrayList<Anime> searchAnimes(String[] text) throws SQLException;
}