package service;

import java.sql.SQLException;

import entity.User;

public interface UserService {

    String userLogin(String account, String password) throws SQLException;

    void userRegister(String account, String password) throws SQLException;

    void cancelAccount(String account) throws SQLException;

    void modifyUser(String tar_Column, Object res, String account) throws SQLException;

    User queryUser(String account) throws SQLException;

}