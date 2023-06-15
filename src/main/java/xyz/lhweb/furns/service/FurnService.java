package xyz.lhweb.furns.service;

import xyz.lhweb.furns.bean.Furn;
import xyz.lhweb.furns.bean.Page;

import java.util.List;

/**
 * 家具服务
 *
 * @author 罗汉
 * @date 2023/04/01
 */
public interface FurnService {
    /**
     * 查询家具
     *
     * @return {@link List}<{@link Furn}>
     */
    List<Furn> queryFurns();

    /**
     * 添加家具
     *
     * @param furn 家具
     * @return int
     */
    int addFurns(Furn furn);
    /**
     * 删除家具通过id
     *
     * @param id id
     * @return int 是受影响的行数
     */
    int deleteFurnById(int id);
    int deleteFurnByIds(String[] id);
    /**
     * 通过id查询家具
     *
     * @return {@link Furn}
     */
    Furn queryFurnById(int id);
    /**
     * 更新家具
     * 将传入的furn对象，更新到数据库，根据id  id是唯一标识
     *
     * @param furn 家具
     * @return int
     */
    int updateFurn(Furn furn);

    /**
     * 根据传入的pageNo和pageSize返回传入对应page对象
     *
     * @param pageNo    开始
     * @param pageSize 页面大小
     * @return {@link Page}<{@link Furn}>
     */
    Page<Furn> page(int pageNo,int pageSize);

    /**
     * 根据传入的pageNo,pageSize,name返回传入对应的page对象
     *
     * @param pageNo   页面没有
     * @param pageSize 页面大小
     * @param name     名字
     * @return {@link Page}<{@link Furn}>
     */
    Page<Furn> pageByName(int pageNo,int pageSize,String name);
}
