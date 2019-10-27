package test;

import com.dao.ShopCategoryDao;
import com.dao.ShopDao;
import com.dto.ShopExecution;
import com.entity.Area;
import com.entity.PersonInfo;
import com.entity.Shop;
import com.entity.ShopCategory;
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
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml"})
public class test {
    @Autowired
    private ShopCategoryDao shopCategoryDao;
    @Autowired
    private ShopService shopService;
    @Autowired
    private  ShopDao shopDao;
    @Test
    public void test1() {
        Shop shop = new Shop();
        Area a = new Area();
        a.setAreaId(3);
        shop.setArea(a);
       List<Shop>shopList=shopDao.queryShopList(shop,1,2);
       shopList.toString();
        System.out.println(shopDao.queryShopCount(shop));

    }}