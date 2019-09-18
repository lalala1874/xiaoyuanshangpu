package com.web.shopadmin;

import com.dto.ShopExecution;
import com.entity.PersonInfo;
import com.entity.Shop;
import com.enums.ShopStateEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.ShopService;
import com.util.HttpServletRequestUtil;
import com.util.ImageUtil;
import com.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
@Controller
@RequestMapping(path = "/shopadmin")
public class ShopManagementController {
    @Autowired
    private ShopService shopService;


    @RequestMapping(value = ("/registershop"),method = RequestMethod.POST)
    @ResponseBody
    private Map<String ,Object> registerShop(HttpServletRequest request){
        Map<String ,Object> modleMap=new HashMap<>();
        //1.接受并转化相应的参数，包括店铺信息以及图片信息
        String shopStr= HttpServletRequestUtil.getString(request,"shopStr");
        ObjectMapper objectMapper=new ObjectMapper();
        Shop shop=null;
        try {
                shop=objectMapper.readValue(shopStr,Shop.class);
        }catch (Exception e){
            modleMap.put("success",false);
            modleMap.put("errMsg",e.getMessage());
            return  modleMap;
        }
        CommonsMultipartFile shopImg=null;
        CommonsMultipartResolver commonsMultipartResolver=new CommonsMultipartResolver(request.getSession().getServletContext());
        if (commonsMultipartResolver.isMultipart(request)){
            MultipartRequest multipartRequest= (MultipartRequest) request;
            shopImg= (CommonsMultipartFile) multipartRequest.getFile("shopImg");
        }else
        {
            modleMap.put("success",false);
            modleMap.put("errMsg","上传图片不能为空");
            return  modleMap;
        }
        //2.注册店铺
        if (shop!=null&&shopImg!=null){
            PersonInfo owner=new PersonInfo();
            owner.setUserId(1l);
            shop.setOwner(owner);

//            File shopImgFile=new File(PathUtil.getImgBasePath()+ ImageUtil.getRandomFileName());
//            try {
//                shopImgFile.createNewFile();
//            } catch (IOException e) {
//                modleMap.put("success",false);
//                modleMap.put("errMsg",e.getMessage());
//                return  modleMap;
//            }
//            try {
//                inputStreamToFile(shopImg.getInputStream(),shopImgFile);
//            } catch (IOException e) {
//                modleMap.put("success",false);
//                modleMap.put("errMsg",e.getMessage());
//                return  modleMap;
//            }

            ShopExecution shopExecution= null;
            try {
                shopExecution = shopService.addShop(shop,shopImg.getInputStream(),shopImg.getOriginalFilename());
                if (shopExecution.getState()== ShopStateEnum.CHECK.getState()){
                    modleMap.put("success",true);
                }else {
                    modleMap.put("success",false);
                    modleMap.put("errMsg",shopExecution.getStateInfo());
                }
            } catch (IOException e) {
                modleMap.put("success",false);
                modleMap.put("errMsg",e.getMessage());
            }

            return modleMap;
        }else {
            modleMap.put("success",false);
            modleMap.put("errMsg","请输入店铺信息");
            return  modleMap;

        }



        //3.返回结果

    }
//    private  static void inputStreamToFile(InputStream in, File file){
//        FileOutputStream os;
//        try {
//            os=new FileOutputStream(file);
//            int bytesRead=0;
//            byte [] bytes=new byte[1024];
//            while((bytesRead=in.read(bytes))!=-1){
//                 os.write(bytes);
//            }
//        }catch (Exception e){
//        throw new RuntimeException("调用inputStreamTofile 发生异常"+e.getMessage());
//        }
//
//    }
}
