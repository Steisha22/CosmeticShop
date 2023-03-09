package com.shop.MyShop.dao;

public class DAOFactory {
    public static IDAO getDAOInstance(TypeDAO type) {
        IDAO dao = null;
        if (type == TypeDAO.MySQL) {
            dao = new MySQLDAO();
        }
        return dao;
    }
}
