package com.shop.MyShop.dao;

import com.shop.MyShop.entity.*;
import com.shop.MyShop.forms.AddOrderForm;
import com.shop.MyShop.forms.ClientRequestForm;
import com.shop.MyShop.forms.UpdateStatusForm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.sql.*;

public class MySQLDAO implements IDAO {

    private static String server = "localhost";
    private static String db = "myshop";
    private static String login = "root";
    private static String pass = "123456";

    private static String SQL_GET_ALL_PRODUCTS = "select c.id, c.name, c.price, c.amount, m.name as marka\n" +
            "from myshop.cosmetic_product c, myshop.marka m where c.marka_id = m.id";

    private static String SQL_GET_ALL_PRODUCTS_BY_CATALOG = "select c.id, c.name, c.price, c.amount, m.name as marka\n" +
            "from myshop.cosmetic_product c, myshop.marka m where c.marka_id = m.id and c.catalog_id = ?";

    private static String SQL_GET_ALL_PRODUCTS_BY_CATALOG_ORDER_BY_ID_DESC = "select c.id, c.name, c.price, c.amount, m.name as marka\n" +
            "from myshop.cosmetic_product c, myshop.marka m where c.marka_id = m.id and c.catalog_id = ? order by id desc";

    private static String SQL_GET_ALL_PRODUCTS_BY_CATALOG_ORDER_BY_NAME_DESC = "select c.id, c.name, c.price, c.amount, m.name as marka\n" +
            "from myshop.cosmetic_product c, myshop.marka m where c.marka_id = m.id and c.catalog_id = ? order by name desc";

    private static String SQL_GET_ALL_PRODUCTS_BY_CATALOG_ORDER_BY_PRICE_DESC = "select c.id, c.name, c.price, c.amount, m.name as marka\n" +
            "from myshop.cosmetic_product c, myshop.marka m where c.marka_id = m.id and c.catalog_id = ? order by price desc";

    private static String SQL_GET_ALL_PRODUCTS_BY_CATALOG_ORDER_BY_AMOUNT_DESC = "select c.id, c.name, c.price, c.amount, m.name as marka\n" +
            "from myshop.cosmetic_product c, myshop.marka m where c.marka_id = m.id and c.catalog_id = ? order by amount desc";

    private static String SQL_GET_ALL_PRODUCTS_BY_CATALOG_ORDER_BY_MARKA_DESC = "select c.id, c.name, c.price, c.amount, m.name as marka\n" +
            "from myshop.cosmetic_product c, myshop.marka m where c.marka_id = m.id and c.catalog_id = ? order by marka desc";

    private static String SQL_GET_ALL_PRODUCTS_BY_CATALOG_ORDER_BY_ID_ASC = "select c.id, c.name, c.price, c.amount, m.name as marka\n" +
            "from myshop.cosmetic_product c, myshop.marka m where c.marka_id = m.id and c.catalog_id = ? order by id";

    private static String SQL_GET_ALL_PRODUCTS_BY_CATALOG_ORDER_BY_NAME_ASC = "select c.id, c.name, c.price, c.amount, m.name as marka\n" +
            "from myshop.cosmetic_product c, myshop.marka m where c.marka_id = m.id and c.catalog_id = ? order by name";

    private static String SQL_GET_ALL_PRODUCTS_BY_CATALOG_ORDER_BY_PRICE_ASC = "select c.id, c.name, c.price, c.amount, m.name as marka\n" +
            "from myshop.cosmetic_product c, myshop.marka m where c.marka_id = m.id and c.catalog_id = ? order by price";

    private static String SQL_GET_ALL_PRODUCTS_BY_CATALOG_ORDER_BY_AMOUNT_ASC = "select c.id, c.name, c.price, c.amount, m.name as marka\n" +
            "from myshop.cosmetic_product c, myshop.marka m where c.marka_id = m.id and c.catalog_id = ? order by amount";

    private static String SQL_GET_ALL_PRODUCTS_BY_CATALOG_ORDER_BY_MARKA_ASC = "select c.id, c.name, c.price, c.amount, m.name as marka\n" +
            "from myshop.cosmetic_product c, myshop.marka m where c.marka_id = m.id and c.catalog_id = ? order by marka";

    private static String SQL_GET_ALL_PRODUCTS_BY_PRICE = "select c.id, c.name, c.price, c.amount, m.name as marka\n" +
            "from myshop.cosmetic_product c, myshop.marka m where c.marka_id = m.id and c.price = ?";

    private static String SQL_GET_ALL_PRODUCTS_BY_NAME = "select c.id, c.name, c.price, c.amount, m.name as marka\n" +
            "from myshop.cosmetic_product c, myshop.marka m where c.marka_id = m.id and c.name like ?";

    private static String SQL_GET_ALL_PRODUCTS_FROM_BASKET = "select c.id, c.name, b.price, b.amount, m.name as marka\n" +
            "from myshop.basket_has_cosmetic_product b, myshop.cosmetic_product c, myshop.marka m\n" +
            "where b.cosmetic_product_id = c.id and c.marka_id = m.id"; //учесть юзера

    private static String SQL_GET_AMOUNT_BY_ID_FROM_BASKET = "select b.amount from myshop.basket_has_cosmetic_product b" +
            " where b.cosmetic_product_id = ? and b.basket_id = ?";

    private static String SQL_INSERT_PRODUCT_TO_BASKET = "INSERT INTO myshop.basket_has_cosmetic_product " +
            "(cosmetic_product_id, basket_id, price, amount) VALUES (?, ?, ?, ?)";

    private static String SQL_UPDATE_AMOUNT_IN_BASKET = "UPDATE myshop.basket_has_cosmetic_product SET " +
            "amount = ?, price = ? WHERE (cosmetic_product_id = ?) and (basket_id = ?)";

    private static String SQL_UPDATE_AMOUNT_IN_PRODUCT = "UPDATE myshop.cosmetic_product SET " +
            "amount = ? WHERE (cosmetic_product_id = ?) and (basket_id = ?)";

    private static String SQL_DELETE_PRODUCT_FROM_BASKET = "DELETE FROM myshop.basket_has_cosmetic_product" +
            " WHERE (cosmetic_product_id = ?) and (basket_id = ?)";

    private static String SQL_GET_AMOUNT_BY_ID_FROM_PRODUCT = "select c.amount from myshop.cosmetic_product c" +
            " where c.id = ?";

    private static String SQL_UPDATE_AMOUNT_OF_PRODUCTS = "UPDATE myshop.cosmetic_product SET " +
            "amount = ? WHERE id = ?";

    private static String SQL_GET_AMOUNT_OF_PRODUCTS = "SELECT count(c.cosmetic_product_id) as COUNT from " +
            "basket_has_cosmetic_product c where c.basket_id = ?";

    private static String SQL_GET_FIRST_NAME = "SELECT u.first_name from myshop.user u where u.id = ?";
    private static String SQL_GET_LAST_NAME = "SELECT u.last_name from myshop.user u where u.id = ?";
    private static String SQL_GET_USERNAME = "SELECT u.username from myshop.user u where u.id = ?";
    private static String SQL_GET_EMAIL = "SELECT u.e_mail from myshop.user u where u.id = ?";
    private static String SQL_GET_PHONE = "SELECT u.phone from myshop.user u where u.id = ?";
    private static String SQL_GET_AMOUNT_OF_ORDERS = "SELECT u.amount_of_orders from myshop.user u where u.id = ?";
    private static String SQL_GET_STATUS_BY_ID = "SELECT * from myshop.status s where s.id = ?";
    private static String SQL_GET_DELIVERY_BY_ID = "SELECT * from myshop.delivery d where d.id = ?";
    private static String SQL_GET_USER_BY_ID = "SELECT * from myshop.user u where u.id = ?";
    private static String SQL_GET_ROLE_BY_ID = "SELECT * from myshop.role r where r.id = ?";

    private static String SQL_GET_CITY = "select d.city from myshop.delivery d where d.id = " +
            "(select o.delivery_id from myshop.order_t o where o.client_id = ? order by create_data desc LIMIT 1)";
    private static String SQL_GET_STREET = "select d.street from myshop.delivery d where d.id = " +
            "(select o.delivery_id from myshop.order_t o where o.client_id = ? order by create_data desc LIMIT 1)";
    private static String SQL_GET_HOUSE_NUMBER = "select d.house_number from myshop.delivery d where d.id = " +
            "(select o.delivery_id from myshop.order_t o where o.client_id = ? order by create_data desc LIMIT 1)";
    private static String SQL_GET_POST_NUMBER = "select d.post_number from myshop.delivery d where d.id = " +
            "(select o.delivery_id from myshop.order_t o where o.client_id = ? order by create_data desc LIMIT 1)";

    private static String SQL_INSERT_ORDER = "INSERT INTO myshop.order_t (last_update, create_data, status_id, delivery_id, " +
            "client_id, admin_id, total_amount, total_sum) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    private static String SQL_INSERT_CLIENT_REQUEST = "INSERT INTO myshop.client_request (client_id, order_id, " +
            "request_status_id, create_data, last_update_data, admin_id) VALUES (?, ?, ?, ?, ?, ?)";

    private static String SQL_INSERT_IN_ORDER_HAS_PRODUCT = "INSERT INTO myshop.order_has_product (order_id, " +
            "cosmetic_product_id, amount, price) VALUES (?, ?, ?, ?)";

    //private static String SQL_INSERT_USER = "INSERT INTO myshop.user (username, role_id, phone, e_mail, first_name, " +
            //"last_name) VALUES (?, ?, ?, ?, ?, ?)";

    //private static String SQL_UPDATE_USER = "UPDATE myshop.user SET username = ?, phone = ?, e_mail = ?, first_name = ?, " +
    //"last_name = ? where id = ?";

    private static String SQL_INSERT_DELIVERY = "INSERT INTO myshop.delivery (city, street, house_number, " +
            "post_number) VALUES (?, ?, ?, ?)";

    private static String SQL_DELETE_PRODUCTS_FROM_BASKET = "DELETE FROM myshop.basket_has_cosmetic_product" +
            " WHERE basket_id = ?";

    private static String SQL_GET_ALL_USERS_ORDERS = "SELECT * from order_t where client_id = ? ORDER BY create_data desc";

    private static String SQL_GET_ALL_USERS_REQUESTS = "SELECT * from client_request where client_id = ? ORDER BY create_data desc";

    private static String SQL_GET_ALL_REQUESTS = "SELECT * from client_request ORDER BY create_data desc";

    private static String SQL_GET_ALL_STATUSES = "SELECT * from status order by id";

    private static String SQL_GET_ALL_REQUEST_STATUSES = "SELECT * from request_status";

    private static String SQL_UPDATE_AMOUNT_OF_ORDERS = "UPDATE myshop.user SET amount_of_orders = ? WHERE id = ?";

    private static String SQL_GET_REQUEST_STATUS_BY_ID = "SELECT * from myshop.request_status s where s.id = ?";

    //private static String SQL_GET_CATALOG_BY_PRODUCT = "SELECT p.catalog_id from myshop.cosmetic_product p where p.id = ?";
    private static String SQL_GET_CATALOG_BY_PRODUCT = "SELECT c.id, c.name from myshop.catalog c " +
            "join myshop.cosmetic_product p on c.id = p.catalog_id where p.id = ?";

    private static String SQL_GET_ORDER_BY_ID = "SELECT * from myshop.order_t o where o.id = ?";

    private static String SQL_GET_ALL_ORDERS = "SELECT * from order_t ORDER BY create_data";

    private static String SQL_UPDATE_ORDER_STATUS = "UPDATE myshop.order_t SET status_id = ?, last_update = ? WHERE id = ?";

    private static String SQL_UPDATE_REQUEST_STATUS = "UPDATE myshop.client_request SET request_status_id = ?, " +
            "last_update_data = ? WHERE id = ?";

    private static String SQL_GET_PREV_CANCELED_STATUS = "select s.status_id from myshop.status_history s " +
            "where order_id = ? order by s.change_data desc LIMIT 1,1;";

    private static String SQL_GET_USER_ID_BY_SESSION = "SELECT * from myshop.spring_session s where s.session_id = ?";

    private static String SQL_GET_BASKET_ID_BY_USER = "SELECT b.id from basket b where client_id = ?";

    private static String SQL_CREATE_BASKET = "INSERT INTO myshop.basket (client_id, total_amount) VALUES (?, ?)";

    private static String SQL_GET_ALL_PRODUCTS_BY_ORDER = "select * from myshop.order_has_product where order_id = ?";

    private static String SQL_GET_ALL_LIKES_PRODUCTS = "select c.id, c.name, sum(o.amount) as total from myshop.cosmetic_product c\n" +
            "join order_has_product o on c.id = o.cosmetic_product_id\n" +
            "join order_t t on o.order_id = t.id\n" +
            "where t.status_id = 8\n" +
            "group by c.name\n" +
            "order by total desc;";

    private static String SQL_GET_PRODUCT_BY_ID = "select c.id, c.name, c.price, c.amount, m.name as marka " +
            "from myshop.cosmetic_product c join myshop.marka m on c.marka_id = m.id where c.id = ?";

    //getUserById

    public MySQLDAO() {

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<CosmeticProduct> getAllProducts() {
        Connection con = null;
        List<CosmeticProduct> list = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + ":3306/" + db, login, pass);

            PreparedStatement ps = con.prepareStatement(SQL_GET_ALL_PRODUCTS);
            ResultSet res = ps.executeQuery();

            list = new LinkedList<CosmeticProduct>();

            while (res.next()) {

                CosmeticProduct product = new CosmeticProduct();
                product.setId(res.getLong("ID"));
                product.setName(res.getString("Name"));
                product.setPrice(res.getDouble("Price"));
                product.setAmount(res.getInt("Amount"));
                product.setMarka(res.getString("Marka"));
                product.setCatalog(getCatalogByProduct(res.getLong("id")));

                list.add(product);
            }

            res.close();
            ps.close();
            con.close();

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<CosmeticProduct> getAllProductsByCatalog(long catalog_id) {
        Connection con = null;
        List<CosmeticProduct> list = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + ":3306/" + db, login, pass);

            PreparedStatement ps = con.prepareStatement(SQL_GET_ALL_PRODUCTS_BY_CATALOG);
            ps.setLong(1, catalog_id);
            ResultSet res = ps.executeQuery();

            list = new LinkedList<CosmeticProduct>();

            while (res.next()) {

                CosmeticProduct product = new CosmeticProduct();
                product.setId(res.getLong("ID"));
                product.setName(res.getString("Name"));
                product.setPrice(res.getDouble("Price"));
                product.setAmount(res.getInt("Amount"));
                product.setMarka(res.getString("Marka"));
                product.setCatalog(getCatalogByProduct(res.getLong("id")));

                list.add(product);
            }
            res.close();
            ps.close();
            con.close();

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<CosmeticProduct> getAllProductsByCatalogOrderByDesc(long catalog_id, String param) {
        Connection con = null;
        List<CosmeticProduct> list = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + ":3306/" + db, login, pass);

            PreparedStatement ps;

            switch(param){
                case("id"):
                    ps = con.prepareStatement(SQL_GET_ALL_PRODUCTS_BY_CATALOG_ORDER_BY_ID_DESC);
                    ps.setLong(1, catalog_id);
                    break;
                case("name"):
                    ps = con.prepareStatement(SQL_GET_ALL_PRODUCTS_BY_CATALOG_ORDER_BY_NAME_DESC);
                    ps.setLong(1, catalog_id);
                    break;
                case("price"):
                    ps = con.prepareStatement(SQL_GET_ALL_PRODUCTS_BY_CATALOG_ORDER_BY_PRICE_DESC);
                    ps.setLong(1, catalog_id);
                    break;
                case("amount"):
                    ps = con.prepareStatement(SQL_GET_ALL_PRODUCTS_BY_CATALOG_ORDER_BY_AMOUNT_DESC);
                    ps.setLong(1, catalog_id);
                    break;
                case("marka"):
                    ps = con.prepareStatement(SQL_GET_ALL_PRODUCTS_BY_CATALOG_ORDER_BY_MARKA_DESC);
                    ps.setLong(1, catalog_id);
                    break;
                default:
                    ps = con.prepareStatement(SQL_GET_ALL_PRODUCTS_BY_CATALOG);
                    ps.setLong(1, catalog_id);
            }

            ResultSet res = ps.executeQuery();
            list = new LinkedList<CosmeticProduct>();

            while (res.next()) {

                CosmeticProduct product = new CosmeticProduct();
                product.setId(res.getLong("ID"));
                product.setName(res.getString("Name"));
                product.setPrice(res.getDouble("Price"));
                product.setAmount(res.getInt("Amount"));
                product.setMarka(res.getString("Marka"));
                product.setCatalog(getCatalogByProduct(res.getLong("id")));

                list.add(product);
            }
            res.close();
            ps.close();
            con.close();
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<CosmeticProduct> getAllProductsByCatalogOrderByAsc(long catalog_id, String param) {
        Connection con = null;
        List<CosmeticProduct> list = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + ":3306/" + db, login, pass);

            PreparedStatement ps;

            switch(param){
                case("id"):
                    ps = con.prepareStatement(SQL_GET_ALL_PRODUCTS_BY_CATALOG_ORDER_BY_ID_ASC);
                    ps.setLong(1, catalog_id);
                    break;
                case("name"):
                    ps = con.prepareStatement(SQL_GET_ALL_PRODUCTS_BY_CATALOG_ORDER_BY_NAME_ASC);
                    ps.setLong(1, catalog_id);
                    break;
                case("price"):
                    ps = con.prepareStatement(SQL_GET_ALL_PRODUCTS_BY_CATALOG_ORDER_BY_PRICE_ASC);
                    ps.setLong(1, catalog_id);
                    break;
                case("amount"):
                    ps = con.prepareStatement(SQL_GET_ALL_PRODUCTS_BY_CATALOG_ORDER_BY_AMOUNT_ASC);
                    ps.setLong(1, catalog_id);
                    break;
                case("marka"):
                    ps = con.prepareStatement(SQL_GET_ALL_PRODUCTS_BY_CATALOG_ORDER_BY_MARKA_ASC);
                    ps.setLong(1, catalog_id);
                    break;
                default:
                    ps = con.prepareStatement(SQL_GET_ALL_PRODUCTS_BY_CATALOG);
                    ps.setLong(1, catalog_id);
            }

            ResultSet res = ps.executeQuery();

            list = new LinkedList<CosmeticProduct>();

            while (res.next()) {

                CosmeticProduct product = new CosmeticProduct();
                product.setId(res.getLong("ID"));
                product.setName(res.getString("Name"));
                product.setPrice(res.getDouble("Price"));
                product.setAmount(res.getInt("Amount"));
                product.setMarka(res.getString("Marka"));
                product.setCatalog(getCatalogByProduct(res.getLong("id")));

                list.add(product);
            }
            res.close();
            ps.close();
            con.close();
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return list;
    }

/*    @Override
    public List<Cosmetic_product> getAllProductsForEyes() {
        Connection con = null;
        List<Cosmetic_product> list = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + ":3306/" + db, login, pass);

            PreparedStatement ps = con.prepareStatement(SQL_GET_ALL_PRODUCTS_FOR_EYES);
            ResultSet res = ps.executeQuery();

            list = new LinkedList<Cosmetic_product>();

            while (res.next()) {

                Cosmetic_product product = new Cosmetic_product();
                product.setId(res.getLong("ID"));
                product.setName(res.getString("Name"));
                product.setPrice(res.getDouble("Price"));
                product.setAmount(res.getInt("Amount"));
                product.setMarka(res.getString("Marka"));

                list.add(product);
            }
            res.close();
            ps.close();
            con.close();

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<Cosmetic_product> getAllProductsForEyesOrderByDesc() {
        Connection con = null;
        List<Cosmetic_product> list = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + ":3306/" + db, login, pass);

            PreparedStatement ps = con.prepareStatement(SQL_GET_ALL_PRODUCTS_FOR_EYES_ORDER_BY_DESC);
            ResultSet res = ps.executeQuery();

            list = new LinkedList<Cosmetic_product>();

            while (res.next()) {

                Cosmetic_product product = new Cosmetic_product();
                product.setId(res.getLong("ID"));
                product.setName(res.getString("Name"));
                product.setPrice(res.getDouble("Price"));
                product.setAmount(res.getInt("Amount"));
                product.setMarka(res.getString("Marka"));

                list.add(product);
            }
            res.close();
            ps.close();
            con.close();
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Cosmetic_product> getAllProductsForEyesOrderByAsc() {
        Connection con = null;
        List<Cosmetic_product> list = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + ":3306/" + db, login, pass);

            PreparedStatement ps = con.prepareStatement(SQL_GET_ALL_PRODUCTS_FOR_EYES_ORDER_BY_ASC);
            ResultSet res = ps.executeQuery();

            list = new LinkedList<Cosmetic_product>();

            while (res.next()) {

                Cosmetic_product product = new Cosmetic_product();
                product.setId(res.getLong("ID"));
                product.setName(res.getString("Name"));
                product.setPrice(res.getDouble("Price"));
                product.setAmount(res.getInt("Amount"));
                product.setMarka(res.getString("Marka"));

                list.add(product);
            }
            res.close();
            ps.close();
            con.close();
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Cosmetic_product> getAllProductsForFace() {
        Connection con = null;
        List<Cosmetic_product> list = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + ":3306/" + db, login, pass);

            PreparedStatement ps = con.prepareStatement(SQL_GET_ALL_PRODUCTS_FOR_FACE);
            ResultSet res = ps.executeQuery();

            list = new LinkedList<Cosmetic_product>();

            while (res.next()) {

                Cosmetic_product product = new Cosmetic_product();
                product.setId(res.getLong("ID"));
                product.setName(res.getString("Name"));
                product.setPrice(res.getDouble("Price"));
                product.setAmount(res.getInt("Amount"));
                product.setMarka(res.getString("Marka"));

                list.add(product);
            }

            res.close();
            ps.close();
            con.close();

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<Cosmetic_product> getAllProductsForLips() {
        Connection con = null;
        List<Cosmetic_product> list = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + ":3306/" + db, login, pass);

            PreparedStatement ps = con.prepareStatement(SQL_GET_ALL_PRODUCTS_FOR_LIPS);
            ResultSet res = ps.executeQuery();

            list = new LinkedList<Cosmetic_product>();

            while (res.next()) {

                Cosmetic_product product = new Cosmetic_product();
                product.setId(res.getLong("ID"));
                product.setName(res.getString("Name"));
                product.setPrice(res.getDouble("Price"));
                product.setAmount(res.getInt("Amount"));
                product.setMarka(res.getString("Marka"));

                list.add(product);
            }

            res.close();
            ps.close();
            con.close();

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<Cosmetic_product> getAllProductsForBody() {
        Connection con = null;
        List<Cosmetic_product> list = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + ":3306/" + db, login, pass);

            PreparedStatement ps = con.prepareStatement(SQL_GET_ALL_PRODUCTS_FOR_BODY);
            ResultSet res = ps.executeQuery();

            list = new LinkedList<Cosmetic_product>();

            while (res.next()) {

                Cosmetic_product product = new Cosmetic_product();
                product.setId(res.getLong("ID"));
                product.setName(res.getString("Name"));
                product.setPrice(res.getDouble("Price"));
                product.setAmount(res.getInt("Amount"));
                product.setMarka(res.getString("Marka"));

                list.add(product);
            }

            res.close();
            ps.close();
            con.close();

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return list;
    }*/

    @Override
    public List<CosmeticProduct> getAllProductsByValue(String value) {
        Connection con = null;
        List<CosmeticProduct> list = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + ":3306/" + db, login, pass);
            int b = 0;
            PreparedStatement ps = null;
            double i = 0.0;

            try
            {
                i = Double.parseDouble(value.trim());
            }
            catch (NumberFormatException nfe)
            {
                ps = con.prepareStatement(SQL_GET_ALL_PRODUCTS_BY_NAME);
                b = 1;
                ps.setString(1, "%" + value + "%");
            }

            if(ps == null) {
                ps = con.prepareStatement(SQL_GET_ALL_PRODUCTS_BY_PRICE);
                b = 2;
                ps.setDouble(1, i);
            }

            ResultSet res = ps.executeQuery();

            if(b == 1){
                /*if (res.next()) {
                    name = res.getString("name");
                } else {
                    return null;
                }

                PreparedStatement ps2 = con.prepareStatement(SQL_GET_ALL_PRODUCTS_BY_NAME);
                ps2.setString(1, value);
                ResultSet res2 = ps2.executeQuery();*/

                list = new LinkedList<CosmeticProduct>();

                while (res.next()) {

                    CosmeticProduct product = new CosmeticProduct();
                    product.setId(res.getLong("ID"));
                    product.setName(res.getString("Name"));
                    product.setPrice(res.getDouble("Price"));
                    product.setAmount(res.getInt("Amount"));
                    product.setMarka(res.getString("Marka"));
                    product.setCatalog(getCatalogByProduct(res.getLong("id")));

                    list.add(product);
                }

                res.close();
                ps.close();
                con.close();
            }
            else {
                /*if (res.next()) {
                    price = res.getDouble("price");
                } else {
                    return null;
                }

                PreparedStatement ps2 = con.prepareStatement(SQL_GET_ALL_PRODUCTS_BY_PRICE);
                ps2.setDouble(1, i);
                ResultSet res2 = ps2.executeQuery();*/


                list = new LinkedList<CosmeticProduct>();

                while (res.next()) {

                    CosmeticProduct product = new CosmeticProduct();
                    product.setId(res.getLong("ID"));
                    product.setName(res.getString("Name"));
                    product.setPrice(res.getDouble("Price"));
                    product.setAmount(res.getInt("Amount"));
                    product.setMarka(res.getString("Marka"));
                    product.setCatalog(getCatalogByProduct(res.getLong("id")));

                    list.add(product);
                }

                res.close();
                ps.close();
                con.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public int getAmountFromBasket(long cosmetic_id, long basket_id) {
        int amount = 0;
        Connection con = null;
        List<CosmeticProduct> list = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + ":3306/" + db, login, pass);

            PreparedStatement ps = con.prepareStatement(SQL_GET_AMOUNT_BY_ID_FROM_BASKET);
            ps.setLong(1, cosmetic_id);
            ps.setLong(2, basket_id);
            ResultSet res = ps.executeQuery();

            while (res.next()) {
                amount = res.getInt("Amount");
            }

            res.close();
            ps.close();
            con.close();

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return amount;
    }

    @Override
    public int getAmountFromProduct(long cosmetic_id) {
        int amount = 0;
        Connection con = null;
        List<CosmeticProduct> list = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + ":3306/" + db, login, pass);

            PreparedStatement ps = con.prepareStatement(SQL_GET_AMOUNT_BY_ID_FROM_PRODUCT);
            ps.setLong(1, cosmetic_id);
            ResultSet res = ps.executeQuery();

            while (res.next()) {
                amount = res.getInt("Amount");
            }

            res.close();
            ps.close();
            con.close();

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return amount;
    }

    @Override
    public void InsertToBasket(long cosmetic_id, long basket_id, double price, int amount) {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + ":3306/" + db, login, pass);

            con.setAutoCommit(false);

            PreparedStatement ps = con.prepareStatement(SQL_INSERT_PRODUCT_TO_BASKET);
            ps.setLong(1, cosmetic_id);
            ps.setLong(2, basket_id);
            ps.setDouble(3, price);
            ps.setInt(4, amount);

            ps.executeUpdate();
            con.commit();
            con.setAutoCommit(true);
            ps.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void UpdateAmountInBasket(long cosmetic_id, long basket_id, double price, int amount) {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + ":3306/" + db, login, pass);
            con.setAutoCommit(false);

            PreparedStatement ps = con.prepareStatement(SQL_UPDATE_AMOUNT_IN_BASKET);
            ps.setInt(1, amount);
            ps.setDouble(2, price);
            ps.setLong(3, cosmetic_id);
            ps.setLong(4, basket_id);

            ps.executeUpdate();
            con.commit();
            con.setAutoCommit(true);
            ps.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<CosmeticProduct> getAllProductsFromBasket() {
        Connection con = null;
        List<CosmeticProduct> list = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + ":3306/" + db, login, pass);

            PreparedStatement ps = con.prepareStatement(SQL_GET_ALL_PRODUCTS_FROM_BASKET);
            ResultSet res = ps.executeQuery();

            list = new LinkedList<CosmeticProduct>();

            while (res.next()) {

                CosmeticProduct product = new CosmeticProduct();
                product.setId(res.getLong("ID"));
                product.setName(res.getString("Name"));
                product.setPrice(res.getDouble("Price"));
                product.setAmount(res.getInt("Amount"));
                product.setMarka(res.getString("Marka"));
                product.setCatalog(getCatalogByProduct(res.getLong("id")));

                list.add(product);
            }
            res.close();
            ps.close();
            con.close();

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return list;
    }

    @Override
    public void DeleteProductFromBasket(long cosmetic_id, long basket_id) {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + ":3306/" + db, login, pass);
            con.setAutoCommit(false);

            PreparedStatement ps = con.prepareStatement(SQL_DELETE_PRODUCT_FROM_BASKET);
            ps.setLong(1, cosmetic_id);
            ps.setLong(2, basket_id);

            ps.executeUpdate();
            con.commit();
            con.setAutoCommit(true);
            ps.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void UpdateAmountProducts(long cosmetic_id, int amount) {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + ":3306/" + db, login, pass);
            con.setAutoCommit(false);

            PreparedStatement ps = con.prepareStatement(SQL_UPDATE_AMOUNT_OF_PRODUCTS);
            ps.setInt(1, amount);
            ps.setLong(2, cosmetic_id);

            ps.executeUpdate();
            con.commit();
            con.setAutoCommit(true);
            ps.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getAmountOfProductsFromBasket(long basket_id) {
        Connection con = null;
        int amount = 0;
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + ":3306/" + db, login, pass);

            PreparedStatement ps = con.prepareStatement(SQL_GET_AMOUNT_OF_PRODUCTS);
            ps.setLong(1, basket_id);

            ResultSet res = ps.executeQuery();

            while(res.next())
            {
                amount = res.getInt("COUNT");
            }


            ps.close();
            con.close();

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return amount;
    }

    @Override
    public String getFirstName(long user_id) {
        String firstName = "";
        Connection con = null;
        List<CosmeticProduct> list = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + ":3306/" + db, login, pass);

            PreparedStatement ps = con.prepareStatement(SQL_GET_FIRST_NAME);
            ps.setLong(1, user_id);
            ResultSet res = ps.executeQuery();

            while (res.next()) {
                firstName = res.getString("first_name");
            }

            res.close();
            ps.close();
            con.close();

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return firstName;
    }

    @Override
    public String getLasttName(long user_id) {
        String lastName = "";
        Connection con = null;
        List<CosmeticProduct> list = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + ":3306/" + db, login, pass);

            PreparedStatement ps = con.prepareStatement(SQL_GET_LAST_NAME);
            ps.setLong(1, user_id);
            ResultSet res = ps.executeQuery();

            while (res.next()) {
                lastName = res.getString("last_name");
            }

            res.close();
            ps.close();
            con.close();

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return lastName;
    }

    @Override
    public String getUsername(long user_id) {
        String username = "";
        Connection con = null;
        List<CosmeticProduct> list = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + ":3306/" + db, login, pass);

            PreparedStatement ps = con.prepareStatement(SQL_GET_USERNAME);
            ps.setLong(1, user_id);
            ResultSet res = ps.executeQuery();

            while (res.next()) {
                username = res.getString("username");
            }

            res.close();
            ps.close();
            con.close();

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return username;
    }

    @Override
    public String getEmail(long user_id) {
        String email = "";
        Connection con = null;
        List<CosmeticProduct> list = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + ":3306/" + db, login, pass);

            PreparedStatement ps = con.prepareStatement(SQL_GET_EMAIL);
            ps.setLong(1, user_id);
            ResultSet res = ps.executeQuery();

            while (res.next()) {
                email = res.getString("e_mail");
            }

            res.close();
            ps.close();
            con.close();

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return email;
    }

    @Override
    public String getPhone(long user_id) {
        String phone = "";
        Connection con = null;
        List<CosmeticProduct> list = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + ":3306/" + db, login, pass);

            PreparedStatement ps = con.prepareStatement(SQL_GET_PHONE);
            ps.setLong(1, user_id);
            ResultSet res = ps.executeQuery();

            while (res.next()) {
                phone = res.getString("phone");
            }

            res.close();
            ps.close();
            con.close();

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return phone;
    }

    @Override
    public int getAmountOfOrders(long user_id) {
        int amount = 0;
        Connection con = null;
        List<CosmeticProduct> list = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + ":3306/" + db, login, pass);

            PreparedStatement ps = con.prepareStatement(SQL_GET_AMOUNT_OF_ORDERS);
            ps.setLong(1, user_id);
            ResultSet res = ps.executeQuery();

            while (res.next()) {
                amount = res.getInt("amount_of_orders");
            }

            res.close();
            ps.close();
            con.close();

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return amount;
    }

    @Override
    public void AddOrder(AddOrderForm addOrderForm, List<CosmeticProduct> list, int total_amount, double total_sum, long basket_id) {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + ":3306/" + db, login, pass);

            con.setAutoCommit(false);

            PreparedStatement ps = con.prepareStatement(SQL_INSERT_DELIVERY, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, addOrderForm.getCity());
            ps.setString(2, addOrderForm.getStreet());
            ps.setInt(3, addOrderForm.getHouse_number());
            ps.setInt(4, addOrderForm.getPost_number());
            ps.executeUpdate();

            Long delivery_id = null;
            ResultSet keys = ps.getGeneratedKeys();
            keys.next();
            delivery_id = keys.getLong(1);

            /*PreparedStatement ps2 = con.prepareStatement(SQL_UPDATE_USER);
            ps2.setString(1, addOrderForm.getUsername());
            ps2.setString(2, addOrderForm.getPhone());
            ps2.setString(3, addOrderForm.getEmail());
            ps2.setString(4, addOrderForm.getFirst_name());
            ps2.setString(5, addOrderForm.getLast_name());
            ps2.setLong(6, addOrderForm.getClient_id());
            ps2.executeUpdate();*/

            PreparedStatement ps3 = con.prepareStatement(SQL_INSERT_ORDER, Statement.RETURN_GENERATED_KEYS);
            ps3.setString(1, addOrderForm.getLast_update());
            ps3.setString(2, addOrderForm.getCreate_data());
            ps3.setLong(3, addOrderForm.getStatus_id());
            ps3.setLong(4, delivery_id);
            ps3.setLong(5, addOrderForm.getClient_id());
            ps3.setLong(6, addOrderForm.getAdmin_id());
            ps3.setInt(7, total_amount);
            ps3.setDouble(8, total_sum);
            ps3.executeUpdate();

            Long order_id = null;
            ResultSet keys3 = ps3.getGeneratedKeys();
            keys3.next();
            order_id = keys3.getLong(1);

            PreparedStatement ps4 = con.prepareStatement(SQL_INSERT_IN_ORDER_HAS_PRODUCT);
            for(int n = 0; n < getAmountOfProductsFromBasket(basket_id); n++)
            {
                ps4.setLong(1, order_id);
                ps4.setLong(2, list.get(n).getId());
                ps4.setInt(3, list.get(n).getAmount()); //total amount
                ps4.setDouble(4, list.get(n).getPrice()); //total price
                ps4.executeUpdate();
            }

            con.commit();
            con.setAutoCommit(true);
            ps.close();
            ps3.close();
            ps4.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void DeleteProductsFromBasket(long basket_id) {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + ":3306/" + db, login, pass);
            con.setAutoCommit(false);

            PreparedStatement ps = con.prepareStatement(SQL_DELETE_PRODUCTS_FROM_BASKET);
            ps.setLong(1, basket_id);

            ps.executeUpdate();
            con.commit();
            con.setAutoCommit(true);
            ps.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Order> getAllOrdersByUserId(long user_id) {
        Connection con = null;
        List<Order> list = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + ":3306/" + db, login, pass);

            PreparedStatement ps = con.prepareStatement(SQL_GET_ALL_USERS_ORDERS);
            ps.setLong(1, user_id);
            ResultSet res = ps.executeQuery();

            list = new LinkedList<Order>();

            while (res.next()) {

                Order order = new Order();
                order.setId(res.getLong("id"));
                order.setCreate_data(res.getString("create_data"));
                order.setLast_update(res.getString("last_update"));
                order.setStatus(getStatusById(res.getLong("status_id")));
                order.setDelivery(getDeliveryById(res.getLong("delivery_id")));
                order.setClient(getUserById(res.getLong("client_id")));
                order.setAdmin(getUserById(res.getLong("admin_id")));
                order.setTotal_amount(res.getInt("total_amount"));
                order.setTotal_sum(res.getDouble("total_sum"));
                order.setList_of_products(getProductsByOrder(res.getLong("id")));

                list.add(order);
            }

            res.close();
            ps.close();
            con.close();

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return list;
    }

    @Override
    public Status getStatusById(long status_id) {
        Status status = new Status();
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + ":3306/" + db, login, pass);

            PreparedStatement ps = con.prepareStatement(SQL_GET_STATUS_BY_ID);
            ps.setLong(1, status_id);
            ResultSet res = ps.executeQuery();

            while (res.next()) {
                status.setId(res.getLong("id"));
                status.setName(res.getString("status_name"));
            }

            res.close();
            ps.close();
            con.close();

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return status;
    }

    @Override
    public Delivery getDeliveryById(long delivery_id) {
        Delivery delivery = new Delivery();
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + ":3306/" + db, login, pass);

            PreparedStatement ps = con.prepareStatement(SQL_GET_DELIVERY_BY_ID);
            ps.setLong(1, delivery_id);
            ResultSet res = ps.executeQuery();

            while (res.next()) {
                delivery.setId(res.getLong("id"));
                delivery.setCity(res.getString("city"));
                delivery.setStreet(res.getString("street"));
                delivery.setHouse_number(res.getInt("house_number"));
                delivery.setPost_number(res.getInt("post_number"));
            }

            res.close();
            ps.close();
            con.close();

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return delivery;
    }

    @Override
    public User getUserById(long user_id) {
        User user = new User();
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + ":3306/" + db, login, pass);

            PreparedStatement ps = con.prepareStatement(SQL_GET_USER_BY_ID);
            ps.setLong(1, user_id);
            ResultSet res = ps.executeQuery();

            while (res.next()) {
                user.setId(res.getLong("id"));
                user.setUsername(res.getString("username"));
                user.setPassword(res.getString("password"));
                user.setAmount_of_orders(res.getInt("amount_of_orders"));
                user.setRole(getRoleById(res.getInt("role_id")));
                user.setPhone(res.getString("phone"));
                user.setEmail(res.getString("e_mail"));
                user.setFirst_name(res.getString("first_name"));
                user.setLast_name(res.getString("last_name"));
            }

            res.close();
            ps.close();
            con.close();

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return user;
    }

    @Override
    public Role getRoleById(long role_id) {
        Role role = new Role();
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + ":3306/" + db, login, pass);

            PreparedStatement ps = con.prepareStatement(SQL_GET_ROLE_BY_ID);
            ps.setLong(1, role_id);
            ResultSet res = ps.executeQuery();

            while (res.next()) {
                role.setId(res.getLong("id"));
                role.setRolename(res.getString("rolename"));
            }

            res.close();
            ps.close();
            con.close();

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return role;
    }

    @Override
    public void UpdateAmountOfOrders(int amount_of_orders, long user_id) {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + ":3306/" + db, login, pass);
            con.setAutoCommit(false);

            PreparedStatement ps = con.prepareStatement(SQL_UPDATE_AMOUNT_OF_ORDERS);
            ps.setInt(1, amount_of_orders);
            ps.setLong(2, user_id);

            ps.executeUpdate();
            con.commit();
            con.setAutoCommit(true);
            ps.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getCity(long user_id) {
        String city = "";
        Connection con = null;
        List<CosmeticProduct> list = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + ":3306/" + db, login, pass);

            PreparedStatement ps = con.prepareStatement(SQL_GET_CITY);
            ps.setLong(1, user_id);
            ResultSet res = ps.executeQuery();

            while (res.next()) {
                city = res.getString("city");
            }
            res.close();
            ps.close();
            con.close();

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return city;
    }

    @Override
    public String getStreet(long user_id) {
        String street = "";
        Connection con = null;
        List<CosmeticProduct> list = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + ":3306/" + db, login, pass);

            PreparedStatement ps = con.prepareStatement(SQL_GET_STREET);
            ps.setLong(1, user_id);
            ResultSet res = ps.executeQuery();

            while (res.next()) {
                street = res.getString("street");
            }
            res.close();
            ps.close();
            con.close();

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return street;
    }

    @Override
    public int getHouseNumber(long user_id) {
        int house_number = 0;
        Connection con = null;
        List<CosmeticProduct> list = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + ":3306/" + db, login, pass);

            PreparedStatement ps = con.prepareStatement(SQL_GET_HOUSE_NUMBER);
            ps.setLong(1, user_id);
            ResultSet res = ps.executeQuery();

            while (res.next()) {
                house_number = res.getInt("house_number");
            }
            res.close();
            ps.close();
            con.close();

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return house_number;
    }

    @Override
    public int getPostNumber(long user_id) {
        int post_number = 0;
        Connection con = null;
        List<CosmeticProduct> list = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + ":3306/" + db, login, pass);

            PreparedStatement ps = con.prepareStatement(SQL_GET_POST_NUMBER);
            ps.setLong(1, user_id);
            ResultSet res = ps.executeQuery();

            while (res.next()) {
                post_number = res.getInt("post_number");
            }
            res.close();
            ps.close();
            con.close();

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return post_number;
    }

    @Override
    public void AddClientRequest(ClientRequestForm clientRequestForm) {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + ":3306/" + db, login, pass);

            con.setAutoCommit(false);

            PreparedStatement ps = con.prepareStatement(SQL_INSERT_CLIENT_REQUEST);
            ps.setLong(1, clientRequestForm.getClient_id());
            ps.setLong(2, clientRequestForm.getOrder_id());
            ps.setLong(3, clientRequestForm.getRequest_status_id());
            ps.setString(4, clientRequestForm.getCreate_data());
            ps.setString(5, clientRequestForm.getLast_update_data());
            ps.setLong(6, clientRequestForm.getAdmin_id());

            ps.executeUpdate();

            con.commit();
            con.setAutoCommit(true);
            ps.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ClientRequest> getAllRequests(long user_id) {
        Connection con = null;
        List<ClientRequest> list = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + ":3306/" + db, login, pass);

            PreparedStatement ps = con.prepareStatement(SQL_GET_ALL_USERS_REQUESTS);
            ps.setLong(1, user_id);
            ResultSet res = ps.executeQuery();

            list = new LinkedList<ClientRequest>();

            while (res.next()) {

                ClientRequest clientRequest = new ClientRequest();
                clientRequest.setId(res.getLong("id"));

                clientRequest.setClient(getUserById(res.getLong("client_id")));
                clientRequest.setOrder(getOrderById(res.getLong("order_id")));
                clientRequest.setRequest_status(getRequestStatusById(res.getLong("request_status_id")));
                clientRequest.setCreate_data(res.getString("create_data"));
                clientRequest.setLast_update_data(res.getString("last_update_data"));
                clientRequest.setAdmin(getUserById(res.getLong("admin_id")));

                list.add(clientRequest);
            }

            res.close();
            ps.close();
            con.close();

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return list;
    }

    @Override
    public RequestStatus getRequestStatusById(long request_status_id) {
        RequestStatus requestStatus = new RequestStatus();
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + ":3306/" + db, login, pass);

            PreparedStatement ps = con.prepareStatement(SQL_GET_REQUEST_STATUS_BY_ID);
            ps.setLong(1, request_status_id);
            ResultSet res = ps.executeQuery();

            while (res.next()) {
                requestStatus.setId(res.getLong("id"));
                requestStatus.setName(res.getString("name"));
            }

            res.close();
            ps.close();
            con.close();

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return requestStatus;
    }

    @Override
    public Order getOrderById(long order_id) {
        Connection con = null;
        Order order = new Order();
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + ":3306/" + db, login, pass);

            PreparedStatement ps = con.prepareStatement(SQL_GET_ORDER_BY_ID);
            ps.setLong(1, order_id);
            ResultSet res = ps.executeQuery();

            while (res.next()) {
                order.setId(res.getLong("id"));
                order.setCreate_data(res.getString("create_data"));
                order.setLast_update(res.getString("last_update"));
                order.setStatus(getStatusById(res.getLong("status_id")));
                order.setDelivery(getDeliveryById(res.getLong("delivery_id")));
                order.setClient(getUserById(res.getLong("client_id")));
                order.setAdmin(getUserById(res.getLong("admin_id")));
                order.setTotal_amount(res.getInt("total_amount"));
                order.setTotal_sum(res.getDouble("total_sum"));
                order.setList_of_products(getProductsByOrder(res.getLong("id")));
            }

            res.close();
            ps.close();
            con.close();

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return order;
    }

    @Override
    public List<Order> getAllOrders() {
        Connection con = null;
        List<Order> list = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + ":3306/" + db, login, pass);

            PreparedStatement ps = con.prepareStatement(SQL_GET_ALL_ORDERS);
            ResultSet res = ps.executeQuery();

            list = new LinkedList<Order>();

            while (res.next()) {

                Order order = new Order();
                order.setId(res.getLong("id"));
                order.setCreate_data(res.getString("create_data"));
                order.setLast_update(res.getString("last_update"));
                order.setStatus(getStatusById(res.getLong("status_id")));
                order.setDelivery(getDeliveryById(res.getLong("delivery_id")));
                order.setClient(getUserById(res.getLong("client_id")));
                order.setAdmin(getUserById(res.getLong("admin_id")));
                order.setTotal_amount(res.getInt("total_amount"));
                order.setTotal_sum(res.getDouble("total_sum"));
                order.setList_of_products(getProductsByOrder(res.getLong("id")));

                list.add(order);
            }

            res.close();
            ps.close();
            con.close();

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<ClientRequest> getAllRequests() {
        Connection con = null;
        List<ClientRequest> list = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + ":3306/" + db, login, pass);

            PreparedStatement ps = con.prepareStatement(SQL_GET_ALL_REQUESTS);
            ResultSet res = ps.executeQuery();

            list = new LinkedList<ClientRequest>();

            while (res.next()) {

                ClientRequest clientRequest = new ClientRequest();
                clientRequest.setId(res.getLong("id"));
                clientRequest.setClient(getUserById(res.getLong("client_id")));
                clientRequest.setOrder(getOrderById(res.getLong("order_id")));
                clientRequest.setRequest_status(getRequestStatusById(res.getLong("request_status_id")));
                clientRequest.setCreate_data(res.getString("create_data"));
                clientRequest.setLast_update_data(res.getString("last_update_data"));
                clientRequest.setAdmin(getUserById(res.getLong("admin_id")));

                list.add(clientRequest);
            }

            res.close();
            ps.close();
            con.close();

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<Status> getAllStatuses() {
        Connection con = null;
        List<Status> list = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + ":3306/" + db, login, pass);

            PreparedStatement ps = con.prepareStatement(SQL_GET_ALL_STATUSES);
            ResultSet res = ps.executeQuery();

            list = new LinkedList<Status>();

            while (res.next()) {

                Status status = new Status();
                status.setId(res.getLong("id"));
                status.setName(res.getString("status_name"));

                list.add(status);
            }

            res.close();
            ps.close();
            con.close();

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<RequestStatus> getAllRequestStatuses() {
        Connection con = null;
        List<RequestStatus> list = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + ":3306/" + db, login, pass);

            PreparedStatement ps = con.prepareStatement(SQL_GET_ALL_REQUEST_STATUSES);
            ResultSet res = ps.executeQuery();

            list = new LinkedList<RequestStatus>();

            while (res.next()) {

                RequestStatus status = new RequestStatus();
                status.setId(res.getLong("id"));
                status.setName(res.getString("name"));

                list.add(status);
            }

            res.close();
            ps.close();
            con.close();

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return list;
    }

    @Override
    public void UpdateOrderStatus(UpdateStatusForm updateStatusForm) {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + ":3306/" + db, login, pass);
            con.setAutoCommit(false);

            PreparedStatement ps = con.prepareStatement(SQL_UPDATE_ORDER_STATUS);
            ps.setLong(1, updateStatusForm.getStatusId());
            ps.setString(2, updateStatusForm.getData());
            ps.setLong(3, updateStatusForm.getId());

            ps.executeUpdate();
            con.commit();
            con.setAutoCommit(true);
            ps.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void UpdateRequestStatus(UpdateStatusForm updateStatusForm) {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + ":3306/" + db, login, pass);
            con.setAutoCommit(false);

            PreparedStatement ps = con.prepareStatement(SQL_UPDATE_REQUEST_STATUS);
            ps.setLong(1, updateStatusForm.getStatusId());
            ps.setString(2, updateStatusForm.getData());
            ps.setLong(3, updateStatusForm.getId());

            ps.executeUpdate();
            con.commit();
            con.setAutoCommit(true);
            ps.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public long getPrevCanceledStatus(long order_id){
        long status_id = 0;
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + ":3306/" + db, login, pass);

            PreparedStatement ps = con.prepareStatement(SQL_GET_PREV_CANCELED_STATUS);
            ps.setLong(1, order_id);
            ResultSet res = ps.executeQuery();

            while (res.next()) {
                status_id = res.getLong("status_id");
            }
            res.close();
            ps.close();
            con.close();

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return status_id;
    }

    @Override
    public long getIdUserBySession(String session_id) {
        long user_id = 0;
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + ":3306/" + db, login, pass);

            PreparedStatement ps = con.prepareStatement(SQL_GET_USER_ID_BY_SESSION);
            ps.setString(1, session_id);
            ResultSet res = ps.executeQuery();

            while (res.next()) {
                user_id = res.getLong("user_id");
            }

            res.close();
            ps.close();
            con.close();

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return user_id;
    }

    @Override
    public long getBasketIdByUser(long user_id) {
        long basket_id = 0;
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + ":3306/" + db, login, pass);

            PreparedStatement ps = con.prepareStatement(SQL_GET_BASKET_ID_BY_USER);
            ps.setLong(1, user_id);
            ResultSet res = ps.executeQuery();

            while (res.next()) {
                basket_id = res.getLong("id");
            }

            res.close();
            ps.close();
            con.close();

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return basket_id;
    }

    @Override
    public void createTable(long user_id) {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + ":3306/" + db, login, pass);

            con.setAutoCommit(false);

            PreparedStatement ps = con.prepareStatement(SQL_CREATE_BASKET);
            ps.setLong(1, user_id);
            ps.setInt(2, 0);

            ps.executeUpdate();
            con.commit();
            con.setAutoCommit(true);
            ps.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Catalog getCatalogByProduct(long product_id) {
        Catalog catalog = new Catalog();
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + ":3306/" + db, login, pass);

            PreparedStatement ps = con.prepareStatement(SQL_GET_CATALOG_BY_PRODUCT);
            ps.setLong(1, product_id);
            ResultSet res = ps.executeQuery();

            while (res.next()) {
                catalog.setId(res.getLong("id"));
                catalog.setName(res.getString("name"));
            }

            res.close();
            ps.close();
            con.close();

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return catalog;
    }

    @Override
    public ArrayList<OrderHasProduct> getProductsByOrder(long order_id) {
        Connection con = null;
        ArrayList<OrderHasProduct> list = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + ":3306/" + db, login, pass);

            PreparedStatement ps = con.prepareStatement(SQL_GET_ALL_PRODUCTS_BY_ORDER);
            ps.setLong(1,order_id);
            ResultSet res = ps.executeQuery();

            list = new ArrayList<OrderHasProduct>();

            while (res.next()) {

                OrderHasProduct product = new OrderHasProduct();
                product.setCosmeticProduct(getProductById(res.getLong("cosmetic_product_id")));
                product.setPrice(res.getDouble("price"));
                product.setAmount(res.getInt("amount"));

                list.add(product);
            }
            res.close();
            ps.close();
            con.close();

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return list;
    }

    @Override
    public CosmeticProduct getProductById(long product_id) {
        CosmeticProduct cosmeticProduct = new CosmeticProduct();
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + ":3306/" + db, login, pass);

            PreparedStatement ps = con.prepareStatement(SQL_GET_PRODUCT_BY_ID);
            ps.setLong(1, product_id);
            ResultSet res = ps.executeQuery();

            while (res.next()) {
                cosmeticProduct.setId(res.getLong("ID"));
                cosmeticProduct.setName(res.getString("Name"));
                cosmeticProduct.setPrice(res.getDouble("Price"));
                cosmeticProduct.setAmount(res.getInt("Amount"));
                cosmeticProduct.setMarka(res.getString("Marka"));
                cosmeticProduct.setCatalog(getCatalogByProduct(res.getLong("id")));
            }

            res.close();
            ps.close();
            con.close();

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return cosmeticProduct;
    }

    @Override
    public List<CosmeticProduct> getAllLikesProducts() {
        Connection con = null;
        List<CosmeticProduct> list = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + ":3306/" + db, login, pass);

            PreparedStatement ps = con.prepareStatement(SQL_GET_ALL_LIKES_PRODUCTS);
            ResultSet res = ps.executeQuery();

            list = new LinkedList<CosmeticProduct>();

            while (res.next()) {

                CosmeticProduct product = new CosmeticProduct();
                product.setId(res.getLong("ID"));
                product.setName(res.getString("Name"));
                product.setAmount(res.getInt("total"));

                list.add(product);
            }

            res.close();
            ps.close();
            con.close();

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return list;
    }
}

    /*@Override
    public List<Cosmetic_product> getTest(int value) {
        Connection con = null;
        List<Cosmetic_product> list = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + ":3306/" + db, login, pass);
            PreparedStatement ps2;

            switch (value) {
                case 1:
                    ps2 = con.prepareStatement(SQL_GET_ALL_PRODUCTS);
                    break;
                case 2:
                    ps2 = con.prepareStatement(SQL_GET_ALL_PRODUCTS_FOR_EYES);;
                    break;
                case 3:
                    ps2 = con.prepareStatement(SQL_GET_ALL_PRODUCTS_FOR_FACE);;
                    break;
                case 4:
                    ps2 = con.prepareStatement(SQL_GET_ALL_PRODUCTS_FOR_LIPS);;
                    break;
                case 5:
                    ps2 = con.prepareStatement(SQL_GET_ALL_PRODUCTS_FOR_BODY);;
                    break;
                default:
                    ps2 = con.prepareStatement(SQL_GET_ALL_PRODUCTS);;
                    break;
            }


            ResultSet res2 = ps2.executeQuery();

            list = new LinkedList<Cosmetic_product>();

            while (res2.next()) {

                Cosmetic_product product = new Cosmetic_product();
                product.setId(res2.getLong("ID"));
                product.setName(res2.getString("Name"));
                product.setPrice(res2.getDouble("Price"));
                product.setAmount(res2.getInt("Amount"));
                product.setMarka(res2.getString("Marka"));

                list.add(product);
            }

            res2.close();

            ps2.close();

            con.close();

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return list;
    }*/