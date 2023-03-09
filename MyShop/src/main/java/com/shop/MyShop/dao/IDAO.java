package com.shop.MyShop.dao;
import com.shop.MyShop.entity.*;
import com.shop.MyShop.forms.AddOrderForm;
import com.shop.MyShop.forms.ClientRequestForm;
import com.shop.MyShop.forms.UpdateStatusForm;

import java.util.ArrayList;
import java.util.List;

public interface IDAO {
    List<CosmeticProduct> getAllProducts();

    List<CosmeticProduct> getAllProductsByCatalog(long ctalog_id);

    List<CosmeticProduct> getAllProductsByCatalogOrderByDesc(long ctalog_id, String param);

    List<CosmeticProduct> getAllProductsByCatalogOrderByAsc(long ctalog_id, String param);

    /*List<Cosmetic_product> getAllProductsForEyes();
                List<Cosmetic_product> getAllProductsForEyesOrderByDesc();
                List<Cosmetic_product> getAllProductsForEyesOrderByAsc();
                List<Cosmetic_product> getAllProductsForFace();
                List<Cosmetic_product> getAllProductsForLips();
                List<Cosmetic_product> getAllProductsForBody();*/
    List<CosmeticProduct> getAllProductsByValue(String value);
    int getAmountFromBasket(long cosmetic_id, long basket_id);

    int getAmountFromProduct(long cosmetic_id);

    void InsertToBasket(long cosmetic_id, long basket_id, double price, int amount);
    void UpdateAmountInBasket(long cosmetic_id, long basket_id, double price, int amount);
    List<CosmeticProduct> getAllProductsFromBasket();
    void DeleteProductFromBasket(long cosmetic_id, long basket_id);
    void UpdateAmountProducts(long cosmetic_id, int amount);
    int getAmountOfProductsFromBasket(long basket_id);

    String getFirstName(long user_id);
    String getLasttName(long user_id);
    String getUsername(long user_id);
    String getEmail(long user_id);
    String getPhone(long user_id);
    int getAmountOfOrders(long user_id);

    void AddOrder(AddOrderForm addOrderForm, List<CosmeticProduct> list, int total_amount, double total_sum, long basket_id);

    void DeleteProductsFromBasket(long basket_id);

    List<Order> getAllOrdersByUserId(long user_id);

    Status getStatusById(long status_id);
    Delivery getDeliveryById(long delivery_id);

    User getUserById(long user_id);

    Role getRoleById(long role_id);

    void UpdateAmountOfOrders(int amount_of_orders, long user_id);

    String getCity(long user_id);

    String getStreet(long user_id);

    int getHouseNumber(long user_id);

    int getPostNumber(long user_id);

    void AddClientRequest(ClientRequestForm clientRequestForm);

    List<ClientRequest> getAllRequests(long user_id);

    RequestStatus getRequestStatusById(long request_status_id);

    Order getOrderById(long order_id);

    List<Order> getAllOrders();

    List<ClientRequest> getAllRequests();

    List<Status> getAllStatuses();

    List<RequestStatus> getAllRequestStatuses();

    void UpdateOrderStatus(UpdateStatusForm updateStatusForm);

    void UpdateRequestStatus(UpdateStatusForm updateStatusForm);

    long getPrevCanceledStatus(long order_id);

    long getIdUserBySession(String session_id);

    long getBasketIdByUser (long user_id);

    void createTable(long user_id);

    Catalog getCatalogByProduct(long product_id);

    ArrayList<OrderHasProduct> getProductsByOrder(long order_id);

    CosmeticProduct getProductById(long product_id);

    List<CosmeticProduct> getAllLikesProducts();

    //List<Cosmetic_product> getTest(int value);
    /*void addStud(Integer stud);

    List<Integer> getStudentsByGroupName(String nameGroup);
    void deleteStudentsByName(String studentName);*/
}
