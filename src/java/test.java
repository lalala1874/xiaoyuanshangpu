import dao.AreaDao;
import entity.Area;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class test {
 @Autowired
 private AreaDao areaDao;
 @Test
 public void testQueryArea(){
     System.out.println(areaDao);
     List<Area> areaList=areaDao.queryArea();
     System.out.println(areaList);
 }
}

