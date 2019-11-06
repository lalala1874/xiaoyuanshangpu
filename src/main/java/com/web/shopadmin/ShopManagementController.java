package com.web.shopadmin;

import com.dto.ImageHolder;
import com.dto.ShopExecution;
import com.entity.Area;
import com.entity.PersonInfo;
import com.entity.Shop;
import com.entity.ShopCategory;
import com.enums.ShopStateEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.AreaService;
import com.service.ShopCategoryService;
import com.service.ShopService;
import com.util.CodeUtil;
import com.util.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(path = "shopadmin")
public class ShopManagementController {
    @Autowired
    private ShopService shopService;
    @Autowired
    private ShopCategoryService shopCategoryService;
    @Autowired
    private AreaService areaService;


    @RequestMapping(value = ("/getshopmanagementinfo"), method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> getShopManagementInfo(HttpServletRequest request){
        Map<String,Object> modelMap=new HashMap<>();
        Long shopId=HttpServletRequestUtil.getLong(request,"shopId");
        if (shopId<0){
            Object currentShopObj=request.getSession().getAttribute("currentShop");
            if (currentShopObj==null){
                modelMap.put("redirect",true);
                modelMap.put("url", "/xiaoyuanshangpu/shopadmin/shoplist");
            }else {
                Shop currentShop= (Shop) currentShopObj;
                modelMap.put("redirect",false);
                modelMap.put("shopId",currentShop.getShopId());
            }
        }else {
            Shop currentShop=new Shop();
            currentShop.setShopId(shopId);
            request.getSession().setAttribute("currentShop",currentShop);
            modelMap.put("redirect",false);
        }

     return  modelMap;
    }

    @RequestMapping(value = ("/getshoplist"), method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> getShopList(HttpServletRequest request){
        Map<String,Object> modelMap=new HashMap<>();
        PersonInfo user=new PersonInfo();

        user.setUserId(2l);
        user.setName("test2l");

        request.getSession().setAttribute("user",user);
        user= (PersonInfo) request.getSession().getAttribute("user");
        List<Shop> shopList=new ArrayList<>();
        try {
           Shop shopCondition=new Shop();
           shopCondition.setOwner(user);
           ShopExecution se=shopService.getShopList(shopCondition,0,100);
           shopList=se.getShopList();
           modelMap.put("shopList",shopList);
           modelMap.put("user",user);
           modelMap.put("success",true);
        }catch (Exception e){
            modelMap.put("success",false);
            modelMap.put("errMsg",e.getMessage());


        }
        return  modelMap;
    }



    @RequestMapping(path = "/getshopbyid", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> getShopById(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        Long shopId = HttpServletRequestUtil.getLong(request, "shopId");
        if (shopId > -1) {
            try {
                Shop shop = shopService.getByShopId(shopId);
                List<Area> areaList = areaService.getAreaList();
                modelMap.put("shop", shop);
                modelMap.put("areaList", areaList);
                modelMap.put("success", true);
            } catch (Exception e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.toString());
            }
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "empty shopId");
        }


        return modelMap;

    }


    @RequestMapping(value = ("/getshopinitinfo"), method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> getshopinitinfo() {
        Map<String, Object> modelMap = new HashMap<>();
        List<ShopCategory> shopCategoryList = new ArrayList<>();
        List<Area> areaList = new ArrayList<>();
        try {
            shopCategoryList = shopCategoryService.getShopCategoryList(new ShopCategory());
            areaList = areaService.getAreaList();
            modelMap.put("success", true);
            modelMap.put("shopCategoryList", shopCategoryList);
            modelMap.put("areaList", areaList);
        } catch (Exception e) {
            modelMap.put("seccess", false);
            modelMap.put("errMsg", e.getMessage());
        }
        return modelMap;
    }


    @RequestMapping(value = ("/registershop"), method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> registerShop(HttpServletRequest request) throws IOException {

        Map<String, Object> modleMap = new HashMap<>();
        if (!CodeUtil.checkVerifyCode(request)) {
            modleMap.put("success", false);
            modleMap.put("errMsg", "验证码错误");
            return modleMap;
        }

        //1.接受并转化相应的参数，包括店铺信息以及图片信息
        String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
        ObjectMapper objectMapper = new ObjectMapper();
        Shop shop = null;
        ImageHolder imageHolder=null;

        try {
            shop = objectMapper.readValue(shopStr, Shop.class);
        } catch (Exception e) {
            modleMap.put("success", false);
            modleMap.put("errMsg", e.getMessage() + "处理shopStr失败");
            return modleMap;
        }
        CommonsMultipartFile shopImg = null;
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if (commonsMultipartResolver.isMultipart(request)) {
            MultipartRequest multipartRequest = (MultipartRequest) request;
            shopImg = (CommonsMultipartFile) multipartRequest.getFile("shopImg");

        } else {
            modleMap.put("success", false);
            modleMap.put("errMsg", "上传图片不能为空");
            return modleMap;
        }
        //2.注册店铺
        if (shop != null && shopImg != null) {
            PersonInfo owner = (PersonInfo) request.getSession().getAttribute("user");
            shop.setOwner(owner);
            ShopExecution shopExecution;
            try {
                imageHolder=new ImageHolder(shopImg.getName(),shopImg.getInputStream());
                shopExecution = shopService.addShop(shop,imageHolder);
                if (shopExecution.getState() == ShopStateEnum.CHECK.getState()) {
                    modleMap.put("success", true);
                    List<Shop> shopList = (List<Shop>) request.getSession().getAttribute("shopList");
                    if (shopList == null || shopList.size() == 0) {
                        shopList = new ArrayList<>();
                    }
                    shopList.add(shop);
                    request.getSession().setAttribute("shopList", shopList);
                } else {
                    modleMap.put("success", false);
                    modleMap.put("errMsg", shopExecution.getStateInfo());
                }
            } catch (IOException e) {
                modleMap.put("success", false);
                modleMap.put("errMsg", e.getMessage());
            }
            return modleMap;
        } else {
            modleMap.put("success", false);
            modleMap.put("errMsg", "请输入店铺信息");
            return modleMap;

        }

    }


    @RequestMapping(path = "/modifyshop", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> modifyShop(HttpServletRequest request) {

        Map<String, Object> modleMap = new HashMap<>();
        if (!CodeUtil.checkVerifyCode(request)) {
            modleMap.put("success", false);
            modleMap.put("errMsg", "验证码错误");
            return modleMap;
        }
        //1.接受并转化相应的参数，包括店铺信息以及图片信息
        String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
        ObjectMapper objectMapper = new ObjectMapper();
        ImageHolder imageHolder=null;
        Shop shop = null;
        try {
            shop = objectMapper.readValue(shopStr, Shop.class);
        } catch (Exception e) {
            modleMap.put("success", false);
            modleMap.put("errMsg", e.getMessage() + "处理shopStr失败");
            return modleMap;
        }
        CommonsMultipartFile shopImg = null;
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if (commonsMultipartResolver.isMultipart(request)) {
            MultipartRequest multipartRequest = (MultipartRequest) request;
            shopImg = (CommonsMultipartFile) multipartRequest.getFile("shopImg");

        }
        //2.修改店铺
        if (shop != null && shop.getShopId() != null) {

            ShopExecution shopExecution = null;
            try {
                if (shopImg == null) {
                    shopExecution = shopService.modifyShop(shop, null);

                } else {
                    imageHolder=new ImageHolder(shopImg.getName(),shopImg.getInputStream());
                    shopExecution = shopService.modifyShop(shop,imageHolder);
                }

                if (shopExecution.getState() == ShopStateEnum.SUCCESS.getState()) {
                    modleMap.put("success", true);
                } else {
                    modleMap.put("success", false);
                    modleMap.put("errMsg", shopExecution.getStateInfo());
                }
            } catch (IOException e) {
                modleMap.put("success", false);
                modleMap.put("errMsg", e.getMessage());
            }
            return modleMap;
        } else {
            modleMap.put("success", false);
            modleMap.put("errMsg", "请输入店铺ID");
            return modleMap;

        }

    }

}
