package service;

import java.sql.SQLException;
import java.util.ArrayList;

import entity.Anime;

public interface AnimeService {

    void addAnime(String name) throws SQLException;

    void deleteAnime(String name) throws SQLException;

    void modifyAnime(String tar_Comlumn, Object res, int id) throws SQLException;

    Anime queryAnime(int id) throws SQLException;

    ArrayList<Anime> queryPartAnimes(int begin, int offset) throws SQLException;

    ArrayList<Anime> queryAnimes() throws SQLException;

    ArrayList<Anime> searchAnimes(String[] text) throws SQLException;
}