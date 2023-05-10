package xyz.lhweb.furns.dao;

import xyz.lhweb.furns.bean.Furn;

import java.util.List;

/**
 * 家具DAO
 *
 * @author 罗汉
 * @date 2023/04/01
 */
public interface FurnDao {
    /**
     * 查询全部家具
     *
     * @return {@link List}<{@link Furn}>
     */
     List<Furn> queryFurns();

    /**
     * 添加家具
     *
     * @param furn 家具
     * @return int 是受影响的行数
     */
    int addFurns(Furn furn);

    /**
     * 删除家具通过id
     *
     * @param id id
     * @return int 是受影响的行数
     */
    int deleteFurnById(int id);

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
     * 得到总行
     * //Page的哪些属性是可以直接从数据库中获取
     * //就把这个填充任务防在DAO层.
     *
     * @return int
     */
    int getTotalRow();

    /**
     *  获取当前页要显示的数据
     * @param begin    表示从第几条记录开始获取， 从0开始计算
     * @param pageSize 表示取出多少条记录
     * @return {@link List}<{@link Furn}>
     */

    List<Furn> getPageItems(int begin, int pageSize);

    /**
     * 根据begin、pageSize、name来获取要显示的家具信息
     *
     * @param begin    开始
     * @param pageSize 页面大小
     * @param name     名字
     * @return {@link List}<{@link Furn}>
     */
    List<Furn> getPageItemsByname(int begin, int pageSize,String name);

    /**
     * 根据名字来获取总记录数
     * @param name
     * @return
     */
     int getTotalRowByName(String name);

}
