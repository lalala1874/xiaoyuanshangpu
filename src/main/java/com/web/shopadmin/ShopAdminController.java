package com.web.shopadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "shopadmin",method = RequestMethod.GET )
public class ShopAdminController {
@RequestMapping("/shopoperation")
    public String shopoperation(){

        return "shop/shopoperation";

    }
}
