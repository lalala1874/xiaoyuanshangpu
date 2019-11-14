package test;

import com.dao.ProductCategoryDao;
import com.dao.ProductDao;
import com.dao.ShopCategoryDao;
import com.dao.ShopDao;
import com.dto.ShopExecution;
import com.entity.*;
import com.enums.ShopStateEnum;
import com.service.ShopService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml"})
public class test {
    @Autowired
    private ProductDao productDao;
    @Autowired
    private ShopService shopService;
    @Autowired
    private  ShopDao shopDao;
    private  Shop shop=new Shop();
    Product product=new Product();
    @Test
    public void test1() {
    shop=shopDao.queryByShopId(2l);
        System.out.println(shop.getOwner());
    }}