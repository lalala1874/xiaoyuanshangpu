package com.web.shopadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "shopadmin",method = RequestMethod.GET )
public class ShopAdminController {

    @RequestMapping("/shopoperation")
    public String shopoperation(){ return "shop/shopoperation"; }

    @RequestMapping("/shoplist")
    public  String shoplist(){
    return  "shop/shoplist";
    }

    @RequestMapping("/shopmanagement")
    public  String shopmanagement(){
        return  "shop/shopmanagement";
    }

    @RequestMapping("/productcategorymanagement")
    public String productcategorymanagement(){
    return "shop/productcategorymanagement";
   }

    @RequestMapping(value = "/productoperation")
    public String productOperation() {return "shop/productoperation"; }

    @RequestMapping(value = "/productmanagement")
    public String productManagement() {return "shop/productmanagement"; }
}
