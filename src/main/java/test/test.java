package test;

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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml"})
public class test {
    @Autowired
    private ShopDao shopDao;
    @Autowired
    private ShopService shopService;
    @Test
    public void test1() throws FileNotFoundException {

     Shop shop=new Shop();
     ShopCategory shopCategory=new ShopCategory();
     shopCategory.setShopCategoryId(1L);
     PersonInfo owner=new PersonInfo();
     owner.setUserId(1l);
     Area area=new Area();
     area.setAreaId(1);
     shop.setArea(area);
     shop.setOwner(owner);
     shop.setShopCategory(shopCategory);
     shop.setShopName("1");
     shop.setShopDesc("3");
     shop.setPhone("3");
     shop.setAdvice("3");
     shop.setEnableStatus(ShopStateEnum.CHECK.getState());
     shop.setShopImg("3");
     shop.setShopAddr("3");
     shop.setShopId(1l);
     shop.setCreateTime(new Date());
     shop.setLasgEditTime(new Date());
     File file=new File("C:\\Users\\啦啦啦\\Pictures\\Camera Roll\\wallhaven-764517.jpg");

          InputStream inputStream=new FileInputStream(file);

          ShopExecution shopExecution=shopService.addShop(shop,inputStream,file.getName());

          System.out.println(shopExecution.getState());
    }






//    @Test
//    @Ignore
//     public void test1(){
//
//     Shop shop=new Shop();
//
//     ShopCategory shopCategory=new ShopCategory();
//     shopCategory.setShopCategoryId(1L);
//     PersonInfo owner=new PersonInfo();
//     owner.setUserId(1l);
//     Area area=new Area();
//     area.setAreaId(1);
//     shop.setArea(area);
//     shop.setOwner(owner);
//     shop.setShopCategory(shopCategory);
//     shop.setShopName("1");
//     shop.setShopDesc("2");
//     shop.setPhone("1");
//     shop.setAdvice("1");
//     shop.setEnableStatus(1);
//     shop.setShopImg("1");
//     shop.setShopAddr("1");
//     shop.setShopId(2l);
//     shop.setCreateTime(new Date());
//     shop.setLasgEditTime(new Date());
//     shopDao.updateShop(shop);
//}
//
//@Test
// public void test(){
//     String path=Thread.currentThread().getContextClassLoader().getResource("").getPath();
//  System.out.println(path);
// System.out.println(System.getProperty("os.name"));
//}
 }


