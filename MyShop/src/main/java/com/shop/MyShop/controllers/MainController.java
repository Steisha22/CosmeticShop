package com.shop.MyShop.controllers;

import com.shop.MyShop.dao.DAOFactory;
import com.shop.MyShop.dao.IDAO;
import com.shop.MyShop.dao.TypeDAO;
import com.shop.MyShop.entity.*;
import com.shop.MyShop.forms.AddOrderForm;
import com.shop.MyShop.forms.ClientRequestForm;
import com.shop.MyShop.forms.SearchForm;
import com.shop.MyShop.forms.UpdateStatusForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Controller
public class MainController {

    private static int ROLE = 0;
    //private static long BASKET_ID = 1;
    private static final long USER_ID = 1;
    private static final long ADMIN_ID = 2;

    private IDAO dao = DAOFactory.getDAOInstance(TypeDAO.MySQL);

    @GetMapping("/")
    public String home(Model model /*, HttpSession httpSession*/) {
        List<CosmeticProduct> list = dao.getAllProducts();
        model.addAttribute("allProducts", list);
        model.addAttribute("ref", "/id_desc");
        model.addAttribute("ref1", "/id_asc");
        model.addAttribute("ref2", "/name_id_desc");
        model.addAttribute("ref3", "/name_id_asc");
        model.addAttribute("ref4", "/price_id_desc");
        model.addAttribute("ref5", "/price_id_asc");
        model.addAttribute("ref6", "/amount_id_desc");
        model.addAttribute("ref7", "/amount_id_asc");
        model.addAttribute("ref8", "/marka_id_desc");
        model.addAttribute("ref9", "/marka_id_asc");
        ROLE = 0;
        return "home";
    }

    @PostMapping("/")
    public String search(@RequestParam ("value") String value, Model model, SearchForm searchForm) {
        List<CosmeticProduct> list = dao.getAllProductsByValue(searchForm.getValue());
        model.addAttribute("allProducts", list);
        switch (ROLE){
            case 0:
                return "home";
            case 1:
                return "user_home";
            case 2:
                return "admin_home";
            default:
                return "home";
        }
    }

    @GetMapping("/user")
    public String user(Model model /*, HttpSession httpSession*/) {
        /*String ses = httpSession.getId();
        long user_id = dao.getIdUserBySession(httpSession.getId());
        if(user_id == 0){
            //dao.CreateSession(httpSession.getId(), )
        }*/

        List<CosmeticProduct> list = dao.getAllProducts();
        model.addAttribute("allProducts", list);
        ROLE = 1;
        return "user_home";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        List<CosmeticProduct> list = dao.getAllProducts();
        model.addAttribute("allProducts", list);
        ROLE = 2;
        return "admin_home";
    }

    @GetMapping("/eyes")
    public String eyes(Model model) {
        List<CosmeticProduct> list = dao.getAllProductsByCatalog(1);
        model.addAttribute("allProducts", list);
        model = InitEyes(model);
        switch (ROLE){
            case 0:
                return "eyes";
            case 1:
                return "user_eyes";
            case 2:
                return "admin_eyes";
            default:
                return "eyes";
        }
    }

    @GetMapping("/eyes_id_desc")
    public String eyes_id_desc(Model model) {
        List<CosmeticProduct> list = dao.getAllProductsByCatalogOrderByDesc(1, "id");
        model.addAttribute("allProducts", list);
        model = InitEyes(model);
        switch (ROLE){
            case 0:
                return "eyes";
            case 1:
                return "user_eyes";
            case 2:
                return "admin_eyes";
            default:
                return "eyes";
        }
    }

    @GetMapping("/eyes_id_asc")
    public String eyes_id_asc(Model model) {
        List<CosmeticProduct> list = dao.getAllProductsByCatalogOrderByAsc(1, "id");
        model.addAttribute("allProducts", list);
        model = InitEyes(model);
        switch (ROLE){
            case 0:
                return "eyes";
            case 1:
                return "user_eyes";
            case 2:
                return "admin_eyes";
            default:
                return "eyes";
        }
    }

    @GetMapping("/eyes_name_desc")
    public String eyes_name_desc(Model model) {
        List<CosmeticProduct> list = dao.getAllProductsByCatalogOrderByDesc(1, "name");
        model.addAttribute("allProducts", list);
        model = InitEyes(model);
        switch (ROLE){
            case 0:
                return "eyes";
            case 1:
                return "user_eyes";
            case 2:
                return "admin_eyes";
            default:
                return "eyes";
        }
    }

    @GetMapping("/eyes_name_asc")
    public String eyes_name_asc(Model model) {
        List<CosmeticProduct> list = dao.getAllProductsByCatalogOrderByAsc(1, "name");
        model.addAttribute("allProducts", list);
        model = InitEyes(model);
        switch (ROLE){
            case 0:
                return "eyes";
            case 1:
                return "user_eyes";
            case 2:
                return "admin_eyes";
            default:
                return "eyes";
        }
    }

    @GetMapping("/eyes_price_desc")
    public String eyes_price_desc(Model model) {
        List<CosmeticProduct> list = dao.getAllProductsByCatalogOrderByDesc(1, "price");
        model.addAttribute("allProducts", list);
        model = InitEyes(model);
        switch (ROLE){
            case 0:
                return "eyes";
            case 1:
                return "user_eyes";
            case 2:
                return "admin_eyes";
            default:
                return "eyes";
        }
    }

    @GetMapping("/eyes_price_asc")
    public String eyes_price_asc(Model model) {
        List<CosmeticProduct> list = dao.getAllProductsByCatalogOrderByAsc(1, "price");
        model.addAttribute("allProducts", list);
        model = InitEyes(model);
        switch (ROLE){
            case 0:
                return "eyes";
            case 1:
                return "user_eyes";
            case 2:
                return "admin_eyes";
            default:
                return "eyes";
        }
    }

    @GetMapping("/eyes_amount_desc")
    public String eyes_amount_desc(Model model) {
        List<CosmeticProduct> list = dao.getAllProductsByCatalogOrderByDesc(1, "amount");
        model.addAttribute("allProducts", list);
        model = InitEyes(model);
        switch (ROLE){
            case 0:
                return "eyes";
            case 1:
                return "user_eyes";
            case 2:
                return "admin_eyes";
            default:
                return "eyes";
        }
    }

    @GetMapping("/eyes_amount_asc")
    public String eyes_amount_asc(Model model) {
        List<CosmeticProduct> list = dao.getAllProductsByCatalogOrderByAsc(1, "amount");
        model.addAttribute("allProducts", list);
        model = InitEyes(model);
        switch (ROLE){
            case 0:
                return "eyes";
            case 1:
                return "user_eyes";
            case 2:
                return "admin_eyes";
            default:
                return "eyes";
        }
    }

    @GetMapping("/eyes_marka_desc")
    public String eyes_marka_desc(Model model) {
        List<CosmeticProduct> list = dao.getAllProductsByCatalogOrderByDesc(1, "marka");
        model.addAttribute("allProducts", list);
        model = InitEyes(model);
        switch (ROLE){
            case 0:
                return "eyes";
            case 1:
                return "user_eyes";
            case 2:
                return "admin_eyes";
            default:
                return "eyes";
        }
    }

    @GetMapping("/eyes_marka_asc")
    public String eyes_marka_asc(Model model) {
        List<CosmeticProduct> list = dao.getAllProductsByCatalogOrderByAsc(1, "marka");
        model.addAttribute("allProducts", list);
        model = InitEyes(model);
        switch (ROLE){
            case 0:
                return "body";
            case 1:
                return "user_body";
            case 2:
                return "admin_body";
            default:
                return "body";
        }
    }

    @GetMapping("/face")
    public String face(Model model) {
        List<CosmeticProduct> list = dao.getAllProductsByCatalog(2);
        model.addAttribute("allProducts", list);
        model = InitFace(model);
        switch (ROLE){
            case 0:
                return "face";
            case 1:
                return "user_face";
            case 2:
                return "admin_face";
            default:
                return "face";
        }
    }

    @GetMapping("/face_id_desc")
    public String face_id_desc(Model model) {
        List<CosmeticProduct> list = dao.getAllProductsByCatalogOrderByDesc(2, "id");
        model.addAttribute("allProducts", list);
        model = InitEyes(model);
        switch (ROLE){
            case 0:
                return "face";
            case 1:
                return "user_face";
            case 2:
                return "admin_face";
            default:
                return "face";
        }
    }

    @GetMapping("/face_id_asc")
    public String face_id_asc(Model model) {
        List<CosmeticProduct> list = dao.getAllProductsByCatalogOrderByAsc(2, "id");
        model.addAttribute("allProducts", list);
        model = InitEyes(model);
        switch (ROLE){
            case 0:
                return "face";
            case 1:
                return "user_face";
            case 2:
                return "admin_face";
            default:
                return "face";
        }
    }

    @GetMapping("/face_name_desc")
    public String face_name_desc(Model model) {
        List<CosmeticProduct> list = dao.getAllProductsByCatalogOrderByDesc(2, "name");
        model.addAttribute("allProducts", list);
        model = InitEyes(model);
        switch (ROLE){
            case 0:
                return "face";
            case 1:
                return "user_face";
            case 2:
                return "admin_face";
            default:
                return "face";
        }
    }

    @GetMapping("/face_name_asc")
    public String face_name_asc(Model model) {
        List<CosmeticProduct> list = dao.getAllProductsByCatalogOrderByAsc(2, "name");
        model.addAttribute("allProducts", list);
        model = InitEyes(model);
        switch (ROLE){
            case 0:
                return "face";
            case 1:
                return "user_face";
            case 2:
                return "admin_face";
            default:
                return "face";
        }
    }

    @GetMapping("/face_price_desc")
    public String face_price_desc(Model model) {
        List<CosmeticProduct> list = dao.getAllProductsByCatalogOrderByDesc(2, "price");
        model.addAttribute("allProducts", list);
        model = InitEyes(model);
        switch (ROLE){
            case 0:
                return "face";
            case 1:
                return "user_face";
            case 2:
                return "admin_face";
            default:
                return "face";
        }
    }

    @GetMapping("/face_price_asc")
    public String face_price_asc(Model model) {
        List<CosmeticProduct> list = dao.getAllProductsByCatalogOrderByAsc(2, "price");
        model.addAttribute("allProducts", list);
        model = InitEyes(model);
        switch (ROLE){
            case 0:
                return "face";
            case 1:
                return "user_face";
            case 2:
                return "admin_face";
            default:
                return "face";
        }
    }

    @GetMapping("/face_amount_desc")
    public String face_amount_desc(Model model) {
        List<CosmeticProduct> list = dao.getAllProductsByCatalogOrderByDesc(2, "amount");
        model.addAttribute("allProducts", list);
        model = InitEyes(model);
        switch (ROLE){
            case 0:
                return "face";
            case 1:
                return "user_face";
            case 2:
                return "admin_face";
            default:
                return "face";
        }
    }

    @GetMapping("/face_amount_asc")
    public String face_amount_asc(Model model) {
        List<CosmeticProduct> list = dao.getAllProductsByCatalogOrderByAsc(2, "amount");
        model.addAttribute("allProducts", list);
        model = InitEyes(model);
        switch (ROLE){
            case 0:
                return "face";
            case 1:
                return "user_face";
            case 2:
                return "admin_face";
            default:
                return "face";
        }
    }

    @GetMapping("/face_marka_desc")
    public String face_marka_desc(Model model) {
        List<CosmeticProduct> list = dao.getAllProductsByCatalogOrderByDesc(2, "marka");
        model.addAttribute("allProducts", list);
        model = InitEyes(model);
        switch (ROLE){
            case 0:
                return "face";
            case 1:
                return "user_face";
            case 2:
                return "admin_face";
            default:
                return "face";
        }
    }

    @GetMapping("/face_marka_asc")
    public String face_marka_asc(Model model) {
        List<CosmeticProduct> list = dao.getAllProductsByCatalogOrderByAsc(2, "marka");
        model.addAttribute("allProducts", list);
        model = InitEyes(model);
        switch (ROLE){
            case 0:
                return "face";
            case 1:
                return "user_face";
            case 2:
                return "admin_face";
            default:
                return "face";
        }
    }

    @GetMapping("/lips")
    public String lips(Model model) {
        List<CosmeticProduct> list = dao.getAllProductsByCatalog(3);
        model.addAttribute("allProducts", list);
        model = InitLips(model);
        switch (ROLE){
            case 0:
                return "lips";
            case 1:
                return "user_lips";
            case 2:
                return "admin_lips";
            default:
                return "lips";
        }
    }

    @GetMapping("/lips_id_desc")
    public String lips_id_desc(Model model) {
        List<CosmeticProduct> list = dao.getAllProductsByCatalogOrderByDesc(3, "id");
        model.addAttribute("allProducts", list);
        model = InitEyes(model);
        switch (ROLE){
            case 0:
                return "lips";
            case 1:
                return "user_lips";
            case 2:
                return "admin_lips";
            default:
                return "lips";
        }
    }

    @GetMapping("/lips_id_asc")
    public String lips_id_asc(Model model) {
        List<CosmeticProduct> list = dao.getAllProductsByCatalogOrderByAsc(3, "id");
        model.addAttribute("allProducts", list);
        model = InitEyes(model);
        switch (ROLE){
            case 0:
                return "lips";
            case 1:
                return "user_lips";
            case 2:
                return "admin_lips";
            default:
                return "lips";
        }
    }

    @GetMapping("/lips_name_desc")
    public String lips_name_desc(Model model) {
        List<CosmeticProduct> list = dao.getAllProductsByCatalogOrderByDesc(3, "name");
        model.addAttribute("allProducts", list);
        model = InitEyes(model);
        switch (ROLE){
            case 0:
                return "lips";
            case 1:
                return "user_lips";
            case 2:
                return "admin_lips";
            default:
                return "lips";
        }
    }

    @GetMapping("/lips_name_asc")
    public String lips_name_asc(Model model) {
        List<CosmeticProduct> list = dao.getAllProductsByCatalogOrderByAsc(3, "name");
        model.addAttribute("allProducts", list);
        model = InitEyes(model);
        switch (ROLE){
            case 0:
                return "lips";
            case 1:
                return "user_lips";
            case 2:
                return "admin_lips";
            default:
                return "lips";
        }
    }

    @GetMapping("/lips_price_desc")
    public String lips_price_desc(Model model) {
        List<CosmeticProduct> list = dao.getAllProductsByCatalogOrderByDesc(3, "price");
        model.addAttribute("allProducts", list);
        model = InitEyes(model);
        switch (ROLE){
            case 0:
                return "lips";
            case 1:
                return "user_lips";
            case 2:
                return "admin_lips";
            default:
                return "lips";
        }
    }

    @GetMapping("/lips_price_asc")
    public String lips_price_asc(Model model) {
        List<CosmeticProduct> list = dao.getAllProductsByCatalogOrderByAsc(3, "price");
        model.addAttribute("allProducts", list);
        model = InitEyes(model);
        switch (ROLE){
            case 0:
                return "lips";
            case 1:
                return "user_lips";
            case 2:
                return "admin_lips";
            default:
                return "lips";
        }
    }

    @GetMapping("/lips_amount_desc")
    public String lips_amount_desc(Model model) {
        List<CosmeticProduct> list = dao.getAllProductsByCatalogOrderByDesc(3, "amount");
        model.addAttribute("allProducts", list);
        model = InitEyes(model);
        switch (ROLE){
            case 0:
                return "lips";
            case 1:
                return "user_lips";
            case 2:
                return "admin_lips";
            default:
                return "lips";
        }
    }

    @GetMapping("/lips_amount_asc")
    public String lips_amount_asc(Model model) {
        List<CosmeticProduct> list = dao.getAllProductsByCatalogOrderByAsc(3, "amount");
        model.addAttribute("allProducts", list);
        model = InitEyes(model);
        switch (ROLE){
            case 0:
                return "lips";
            case 1:
                return "user_lips";
            case 2:
                return "admin_lips";
            default:
                return "lips";
        }
    }

    @GetMapping("/lips_marka_desc")
    public String lips_marka_desc(Model model) {
        List<CosmeticProduct> list = dao.getAllProductsByCatalogOrderByDesc(3, "marka");
        model.addAttribute("allProducts", list);
        model = InitEyes(model);
        switch (ROLE){
            case 0:
                return "lips";
            case 1:
                return "user_lips";
            case 2:
                return "admin_lips";
            default:
                return "lips";
        }
    }

    @GetMapping("/lips_marka_asc")
    public String lips_marka_asc(Model model) {
        List<CosmeticProduct> list = dao.getAllProductsByCatalogOrderByAsc(3, "marka");
        model.addAttribute("allProducts", list);
        model = InitEyes(model);
        switch (ROLE){
            case 0:
                return "lips";
            case 1:
                return "user_lips";
            case 2:
                return "admin_lips";
            default:
                return "lips";
        }
    }

    @GetMapping("/body")
    public String body(Model model) {
        List<CosmeticProduct> list = dao.getAllProductsByCatalog(4);
        model.addAttribute("allProducts", list);
        model = InitBody(model);
        switch (ROLE){
            case 0:
                return "body";
            case 1:
                return "user_body";
            case 2:
                return "admin_body";
            default:
                return "body";
        }
    }

    @GetMapping("/body_id_desc")
    public String body_id_desc(Model model) {
        List<CosmeticProduct> list = dao.getAllProductsByCatalogOrderByDesc(4, "id");
        model.addAttribute("allProducts", list);
        model = InitEyes(model);
        switch (ROLE){
            case 0:
                return "body";
            case 1:
                return "user_body";
            case 2:
                return "admin_body";
            default:
                return "body";
        }
    }

    @GetMapping("/body_id_asc")
    public String body_id_asc(Model model) {
        List<CosmeticProduct> list = dao.getAllProductsByCatalogOrderByAsc(4, "id");
        model.addAttribute("allProducts", list);
        model = InitEyes(model);
        switch (ROLE){
            case 0:
                return "body";
            case 1:
                return "user_body";
            case 2:
                return "admin_body";
            default:
                return "body";
        }
    }

    @GetMapping("/body_name_desc")
    public String body_name_desc(Model model) {
        List<CosmeticProduct> list = dao.getAllProductsByCatalogOrderByDesc(4, "name");
        model.addAttribute("allProducts", list);
        model = InitEyes(model);
        switch (ROLE){
            case 0:
                return "body";
            case 1:
                return "user_body";
            case 2:
                return "admin_body";
            default:
                return "body";
        }
    }

    @GetMapping("/body_name_asc")
    public String body_name_asc(Model model) {
        List<CosmeticProduct> list = dao.getAllProductsByCatalogOrderByAsc(4, "name");
        model.addAttribute("allProducts", list);
        model = InitEyes(model);
        switch (ROLE){
            case 0:
                return "body";
            case 1:
                return "user_body";
            case 2:
                return "admin_body";
            default:
                return "body";
        }
    }

    @GetMapping("/body_price_desc")
    public String body_price_desc(Model model) {
        List<CosmeticProduct> list = dao.getAllProductsByCatalogOrderByDesc(4, "price");
        model.addAttribute("allProducts", list);
        model = InitEyes(model);
        switch (ROLE){
            case 0:
                return "body";
            case 1:
                return "user_body";
            case 2:
                return "admin_body";
            default:
                return "body";
        }
    }

    @GetMapping("/body_price_asc")
    public String body_price_asc(Model model) {
        List<CosmeticProduct> list = dao.getAllProductsByCatalogOrderByAsc(4, "price");
        model.addAttribute("allProducts", list);
        model = InitEyes(model);
        switch (ROLE){
            case 0:
                return "body";
            case 1:
                return "user_body";
            case 2:
                return "admin_body";
            default:
                return "body";
        }
    }

    @GetMapping("/body_amount_desc")
    public String body_amount_desc(Model model) {
        List<CosmeticProduct> list = dao.getAllProductsByCatalogOrderByDesc(4, "amount");
        model.addAttribute("allProducts", list);
        model = InitEyes(model);
        switch (ROLE){
            case 0:
                return "body";
            case 1:
                return "user_body";
            case 2:
                return "admin_body";
            default:
                return "body";
        }
    }

    @GetMapping("/body_amount_asc")
    public String body_amount_asc(Model model) {
        List<CosmeticProduct> list = dao.getAllProductsByCatalogOrderByAsc(4, "amount");
        model.addAttribute("allProducts", list);
        model = InitEyes(model);
        switch (ROLE){
            case 0:
                return "body";
            case 1:
                return "user_body";
            case 2:
                return "admin_body";
            default:
                return "body";
        }
    }

    @GetMapping("/body_marka_desc")
    public String body_marka_desc(Model model) {
        List<CosmeticProduct> list = dao.getAllProductsByCatalogOrderByDesc(4, "marka");
        model.addAttribute("allProducts", list);
        model = InitEyes(model);
        switch (ROLE){
            case 0:
                return "body";
            case 1:
                return "user_body";
            case 2:
                return "admin_body";
            default:
                return "body";
        }
    }

    @GetMapping("/body_marka_asc")
    public String body_marka_asc(Model model) {
        List<CosmeticProduct> list = dao.getAllProductsByCatalogOrderByAsc(4, "marka");
        model.addAttribute("allProducts", list);
        model = InitEyes(model);
        switch (ROLE){
            case 0:
                return "body";
            case 1:
                return "user_body";
            case 2:
                return "admin_body";
            default:
                return "body";
        }
    }

    @GetMapping("/basket")
    public String basket(Model model) {
        List<CosmeticProduct> list = dao.getAllProductsFromBasket();
        model.addAttribute("allProducts", list);
        return "basket";
    }

    @PostMapping("/basket")
    public String basketDelete(@RequestParam ("id") long id, @RequestParam ("amount") int amount, Model model) {
        long basket_id = dao.getBasketIdByUser(USER_ID);
        dao.DeleteProductFromBasket(id, basket_id);

        int amount_products = dao.getAmountFromProduct(id);
        amount_products += amount;
        dao.UpdateAmountProducts(id, amount_products);

        List<CosmeticProduct> list = dao.getAllProductsFromBasket();
        model.addAttribute("allProducts", list);
        ROLE = 1;
        return "basket";
    }

    @PostMapping("/user")
    public String buy(@RequestParam ("id") long id, @RequestParam ("price") double price,  Model model) {
        int amount_products = dao.getAmountFromProduct(id);
        long basket_id = dao.getBasketIdByUser(USER_ID);
        if(basket_id == 0){
            dao.createTable(USER_ID);
            basket_id = dao.getBasketIdByUser(USER_ID);
        }

        int amount = dao.getAmountFromBasket(id, basket_id);
        if(amount == 0){
            dao.InsertToBasket(id, basket_id, price, 1);
            amount_products--;
            dao.UpdateAmountProducts(id, amount_products);
        }
        else{
            amount++;
            double price_db = amount * price;
            dao.UpdateAmountInBasket(id, basket_id, price_db, amount);
            amount_products--;
            dao.UpdateAmountProducts(id, amount_products);
        }
        List<CosmeticProduct> list = dao.getAllProducts();
        model.addAttribute("allProducts", list);
        ROLE = 1;
        return "user_home";
    }

    @GetMapping("/order")
    public String order(Model model) {
        List<CosmeticProduct> list = dao.getAllProductsFromBasket();
        long basket_id = dao.getBasketIdByUser(USER_ID);
        int amount = dao.getAmountOfProductsFromBasket(basket_id);
        model.addAttribute("allProducts",  list);
        model.addAttribute("amount",  amount);
        String first_name = dao.getFirstName(USER_ID);
        String last_name = dao.getLasttName(USER_ID);
        String username = dao.getUsername(USER_ID);
        String email = dao.getEmail(USER_ID);
        String phone = dao.getPhone(USER_ID);
        String city = dao.getCity(USER_ID);
        String street = dao.getStreet(USER_ID);
        int house_number = dao.getHouseNumber(USER_ID);
        int post_number = dao.getPostNumber(USER_ID);
        model.addAttribute("first_name",  first_name);
        model.addAttribute("last_name",  last_name);
        model.addAttribute("username",  username);
        model.addAttribute("email",  email);
        model.addAttribute("phone",  phone);
        model.addAttribute("city",  city);
        model.addAttribute("street",  street);
        model.addAttribute("house_number",  house_number);
        model.addAttribute("post_number",  post_number);

        double total_sum = 0.0;

        for(int i = 0; i < list.size(); i++){
            total_sum += list.get(i).getPrice();
        }
        model.addAttribute("total_sum", total_sum);
        return "order_page";
    }

    @PostMapping("/order")
    public String makeOrder(@RequestParam ("city") String city,
                            @RequestParam ("street") String street, @RequestParam ("house_number") int house_number,
                            @RequestParam ("post_number") int post_number, Model model) {
        Calendar calendar = Calendar.getInstance();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String message = formatter.format(calendar.getTime());

        List<CosmeticProduct> list = dao.getAllProductsFromBasket();

        int total_amount = 0;
        double total_sum = 0.0;

        for(int i = 0; i < list.size(); i++){
            total_amount += list.get(i).getAmount();
            total_sum += list.get(i).getPrice();
        }

        AddOrderForm addOrderForm = new AddOrderForm(city, street, house_number, post_number, message,
                message, 1, USER_ID, 2);

        long basket_id = dao.getBasketIdByUser(USER_ID);
        dao.AddOrder(addOrderForm, list, total_amount, total_sum, basket_id);
        dao.DeleteProductsFromBasket(basket_id);
        int amount_of_orders = dao.getAmountOfOrders(USER_ID) + 1;
        dao.UpdateAmountOfOrders(amount_of_orders, USER_ID);
        return "redirect:/history_order";
    }

    /*@GetMapping("/user_profile")
    public String user_profile(Model model) {
        return "user_profile";
    }*/

    @GetMapping("/contact_info")
    public String contact_info(Model model) {
        String first_name = dao.getFirstName(USER_ID);
        String last_name = dao.getLasttName(USER_ID);
        String username = dao.getUsername(USER_ID);
        String email = dao.getEmail(USER_ID);
        String phone = dao.getPhone(USER_ID);
        int amount = dao.getAmountOfOrders(USER_ID);
        model.addAttribute("first_name",  first_name);
        model.addAttribute("last_name",  last_name);
        model.addAttribute("username",  username);
        model.addAttribute("email",  email);
        model.addAttribute("phone",  phone);
        model.addAttribute("amount",  amount);
        return "contact_info";
    }

    @GetMapping("/history_order")
    public String historyOrder(Model model) {
        List<Order> list = dao.getAllOrdersByUserId(USER_ID);
        model.addAttribute("allOrders",  list);
        return "history_order";
    }

    @PostMapping("/history_order")
    public String sent_request(@RequestParam ("client_id") long client_id, @RequestParam ("order_id") long order_id,
                               Model model) {
        Calendar calendar = Calendar.getInstance();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String message = formatter.format(calendar.getTime());
        ClientRequestForm clientRequestForm = new ClientRequestForm(client_id, order_id, 1,
                message, message, 2);
        dao.AddClientRequest(clientRequestForm);
        UpdateStatusForm updateStatusForm = new UpdateStatusForm(10, order_id, message);
        dao.UpdateOrderStatus(updateStatusForm);
        return "redirect:/history_order";
    }

    @GetMapping("/history_order/order/{id}")
    public String orderDetails(@PathVariable (value = "id") long id, Model model) {
        Order order = dao.getOrderById(id);
        model.addAttribute("order",  order);
        return "orderDetails";
    }

    @GetMapping("/my_request")
    public String my_request(Model model) {
        List<ClientRequest> list = dao.getAllRequests(USER_ID);
        model.addAttribute("allRequests",  list);
        return "my_request";
    }
    @GetMapping("/likes")
    public String likes(Model model) {
        List<CosmeticProduct> list = dao.getAllLikesProducts();
        model.addAttribute("allProducts",  list);
        return "likes";
    }

    @GetMapping("/admin_profile")
    public String admin_profile(Model model) {
        String first_name = dao.getFirstName(ADMIN_ID);
        String last_name = dao.getLasttName(ADMIN_ID);
        String username = dao.getUsername(ADMIN_ID);
        String email = dao.getEmail(ADMIN_ID);
        String phone = dao.getPhone(ADMIN_ID);
        int amount = dao.getAmountOfOrders(ADMIN_ID);
        model.addAttribute("first_name",  first_name);
        model.addAttribute("last_name",  last_name);
        model.addAttribute("username",  username);
        model.addAttribute("email",  email);
        model.addAttribute("phone",  phone);
        model.addAttribute("amount",  amount);
        return "admin_profile";
    }

    @GetMapping("/view_all_clients_orders")
    public String view_all_clients_orders(Model model) {
        UpdateStatusForm updateStatusForm = new UpdateStatusForm();
        List<Order> list = dao.getAllOrders();
        List<Status> status_list = dao.getAllStatuses();
        model.addAttribute("allOrders",  list);
        model.addAttribute("allStatuses",  status_list);
        model.addAttribute("updateStatusForm", updateStatusForm);
        return "all_clients_orders";
    }

    @PostMapping("/view_all_clients_orders")
    public String change_orders_status(@RequestParam ("order_id") long order_id,
    @RequestParam ("statusId") long status_id, Model model) {
        Calendar calendar = Calendar.getInstance();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String data = formatter.format(calendar.getTime());
        UpdateStatusForm updateStatusForm = new UpdateStatusForm(status_id, order_id, data);
        dao.UpdateOrderStatus(updateStatusForm);
        return "redirect:/view_all_clients_orders";
    }

    @GetMapping("/view_all_clients_requests")
    public String view_all_clients_requests(Model model) {
        UpdateStatusForm updateStatusForm = new UpdateStatusForm();
        List<ClientRequest> list = dao.getAllRequests();
        List<RequestStatus> status_list = dao.getAllRequestStatuses();
        model.addAttribute("allRequests",  list);
        model.addAttribute("allStatuses",  status_list);
        model.addAttribute("updateStatusForm", updateStatusForm);
        return "all_clients_requests";
    }

    @PostMapping("/view_all_clients_requests")
    public String change_request_status(@RequestParam ("request_id") long request_id,
                                        @RequestParam ("statusId") long status_id,
                                        @RequestParam ("order_id") long order_id, Model model) {
        Calendar calendar = Calendar.getInstance();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String data = formatter.format(calendar.getTime());
        UpdateStatusForm updateStatusForm = new UpdateStatusForm(status_id, request_id, data);
        dao.UpdateRequestStatus(updateStatusForm);
        if(updateStatusForm.getStatusId() == 3){
            UpdateStatusForm updateStatusForm2 = new UpdateStatusForm(dao.getPrevCanceledStatus(order_id), order_id, data);
            dao.UpdateOrderStatus(updateStatusForm2);
        }
        if(updateStatusForm.getStatusId() == 2){
            UpdateStatusForm updateStatusForm2 = new UpdateStatusForm(9, order_id, data);
            dao.UpdateOrderStatus(updateStatusForm2);
        }
        return "redirect:/view_all_clients_requests";
    }

    public static Model InitEyes(Model model){
        model.addAttribute("ref", "/eyes_id_desc");
        model.addAttribute("ref1", "/eyes_id_asc");
        model.addAttribute("ref2", "/eyes_name_id_desc");
        model.addAttribute("ref3", "/eyes_name_id_asc");
        model.addAttribute("ref4", "/eyes_price_id_desc");
        model.addAttribute("ref5", "/eyes_price_id_asc");
        model.addAttribute("ref6", "/eyes_amount_id_desc");
        model.addAttribute("ref7", "/eyes_amount_id_asc");
        model.addAttribute("ref8", "/eyes_marka_id_desc");
        model.addAttribute("ref9", "/eyes_marka_id_asc");
        return model;
    }

    public static Model InitFace(Model model){
        model.addAttribute("ref", "/eyes_id_desc");
        model.addAttribute("ref", "/face_id_desc");
        model.addAttribute("ref1", "/face_id_asc");
        model.addAttribute("ref2", "/face_name_id_desc");
        model.addAttribute("ref3", "/face_name_id_asc");
        model.addAttribute("ref4", "/face_price_id_desc");
        model.addAttribute("ref5", "/face_price_id_asc");
        model.addAttribute("ref6", "/face_amount_id_desc");
        model.addAttribute("ref7", "/face_amount_id_asc");
        model.addAttribute("ref8", "/face_marka_id_desc");
        model.addAttribute("ref9", "/face_marka_id_asc");
        return model;
    }

    public static Model InitLips(Model model){
        model.addAttribute("ref", "/lips_id_desc");
        model.addAttribute("ref1", "/lips_id_asc");
        model.addAttribute("ref2", "/lips_name_id_desc");
        model.addAttribute("ref3", "/lips_name_id_asc");
        model.addAttribute("ref4", "/lips_price_id_desc");
        model.addAttribute("ref5", "/lips_price_id_asc");
        model.addAttribute("ref6", "/lips_amount_id_desc");
        model.addAttribute("ref7", "/lips_amount_id_asc");
        model.addAttribute("ref8", "/lips_marka_id_desc");
        model.addAttribute("ref9", "/lips_marka_id_asc");
        return model;
    }

    public static Model InitBody(Model model){
        model.addAttribute("ref", "/body_id_desc");
        model.addAttribute("ref1", "/body_id_asc");
        model.addAttribute("ref2", "/body_name_id_desc");
        model.addAttribute("ref3", "/body_name_id_asc");
        model.addAttribute("ref4", "/body_price_id_desc");
        model.addAttribute("ref5", "/body_price_id_asc");
        model.addAttribute("ref6", "/body_amount_id_desc");
        model.addAttribute("ref7", "/body_amount_id_asc");
        model.addAttribute("ref8", "/body_marka_id_desc");
        model.addAttribute("ref9", "/body_marka_id_asc");
        return model;
    }
}