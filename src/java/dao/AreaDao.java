package dao;

import entity.Area;

import java.util.List;

public interface AreaDao {
    /**
     * 列出所有区域
     * @return
     */
    List<Area> queryArea();
}
