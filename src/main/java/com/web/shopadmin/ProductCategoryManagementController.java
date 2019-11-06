package com.web.shopadmin;

import com.dto.ProductCatetgoryExecution;
import com.dto.Result;
import com.entity.ProductCategory;
import com.entity.Shop;
import com.enums.ProductCategoryStateEnum;
import com.exceptions.ProductCategoryOperationException;
import com.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/shopadmin")
@Controller
public class ProductCategoryManagementController {
    @Autowired
    private ProductCategoryService productCategoryService;

    @RequestMapping(value = "/getproductcategorylist", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<ProductCategory>> getProductCategoryList(HttpServletRequest request) {
        Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
        List<ProductCategory> productCategoryList = null;
        if (currentShop != null && currentShop.getShopId() > 0) {
            productCategoryList = productCategoryService.getProductCategoryList(currentShop.getShopId());
            return new Result<List<ProductCategory>>(true, productCategoryList);

        } else {
            ProductCategoryStateEnum ps = ProductCategoryStateEnum.INNER_ERROR;
            return new Result<List<ProductCategory>>(false, ps.getStateInfo(), ps.getState());
        }
    }


    @RequestMapping(value = "/addproductcategorys", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addproductcategory(@RequestBody List<ProductCategory> productCategoryList, HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
        for (ProductCategory p : productCategoryList) {
            p.setShopId(currentShop.getShopId());
        }
        if (productCategoryList != null & productCategoryList.size() > 0) {
            try {
                ProductCatetgoryExecution ps = productCategoryService.batchInsertProductCategory(productCategoryList);
                if (ps.getState() == ProductCategoryStateEnum.SUCCESS.getState()) {
                    modelMap.put("success", true);
                } else {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", ps.getStateInfo());
                }
            } catch (Exception e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.toString());
             }
        } else {
            modelMap.put("success", true);
            modelMap.put("errMsg", "没有输入商品");

        }
        return modelMap;
    }


    @RequestMapping(value = "/removeproductcategory", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> removeProductCategory(HttpServletRequest request,Long productCategoryId){
        Map<String ,Object> modleMap =new HashMap<>();
        if (productCategoryId !=null && productCategoryId>0){
            try {
                Shop currentShop= (Shop) request.getSession().getAttribute("currentShop");
                Long shopId=currentShop.getShopId();
                ProductCatetgoryExecution ps=productCategoryService.delectProductCategory(productCategoryId,shopId);
                if (ps.getState()==ProductCategoryStateEnum.SUCCESS.getState()){
                        modleMap.put("success",true);

                }else {
                    modleMap.put("success",false);
                    modleMap.put("errMsg",ps.getStateInfo());

                }
            }catch (RuntimeException e){
                modleMap.put("success",false);
                modleMap.put("errMsg",e.toString());
                return modleMap;
            }
        }else {  modleMap.put("success",false);
            modleMap.put("errMsg","至少选择一个");
        }


        return modleMap;
    }

}
