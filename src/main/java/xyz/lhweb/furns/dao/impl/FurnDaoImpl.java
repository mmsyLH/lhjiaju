package xyz.lhweb.furns.dao.impl;

import xyz.lhweb.furns.bean.Furn;
import xyz.lhweb.furns.dao.BasicDAO;
import xyz.lhweb.furns.dao.FurnDao;

import java.util.List;

/**
 * 家具DAO实现
 *
 * @author 罗汉
 * @date 2023/04/01
 */
public class FurnDaoImpl extends BasicDAO<Furn> implements FurnDao {
    @Override
    public List<Furn> queryFurns() {
        String sql = "SELECT `id`,`name`,`maker`,`price`,`sales`,`stock`,`img_path` as imgPath from furn;";
        return queryMulti(sql, Furn.class);
    }

    @Override
    public int addFurns(Furn furn) {
        // 图片先给一个默认值 后面再改
        String sql = "INSERT INTO furn(`id` , `name` , `maker` , `price` , `sales` , `stock` , `img_path`) " +
                "VALUES(NULL , ? , ? , ? , ? , ? , ?);";
        return update(sql, furn.getName(), furn.getMaker(), furn.getPrice(), furn.getSales(), furn.getStock(), furn.getImgPath());
    }

    /**
     * 删除家具通过id
     *
     * @param id id
     * @return int 是受影响的行数
     */
    @Override
    public int deleteFurnById(int id) {
        String sql = "delete from `furn` where id=?";
        return update(sql, id);
    }

    /**
     * 通过id查询家具
     *
     * @return {@link Furn}
     */
    @Override
    public Furn queryFurnById(int id) {
        String sql = "SELECT `id`,`name`,`maker`,`price`,`sales`,`stock`,`img_path` as imgPath from furn where id=?;";
        return querySingle(sql, Furn.class, id);
    }

    /**
     * 更新家具
     *
     * @param furn 家具
     * @return int
     */
    @Override
    public int updateFurn(Furn furn) {
        String sql = "UPDATE `furn` SET `name` = ? , `maker` = ?, `price` = ? , " +
                "`sales` = ? , `stock` = ? , `img_path` = ? " +
                "WHERE id = ? ";
        return update(sql, furn.getName(), furn.getMaker(), furn.getPrice(),
                furn.getSales(), furn.getStock(), furn.getImgPath(), furn.getId());
    }

    /**
     * 得到总行
     * //Page的哪些属性是可以直接从数据库中获取
     * //就把这个填充任务防在DAO层.
     *
     * @return int
     */
    @Override
    public int getTotalRow() {
        String sql = "select count(*) from `furn`";
        /**
         * (Integer) queryScalar(sql);=》cast异常
         */
        return ((Number) queryScalar(sql)).intValue();
    }

    /**
     * 获取页面项目
     * 获取当前页要显示的数据
     * begin : 表示从第几条记录开始获取， 从0开始计算
     * pageSize : 表示取出多少条记录
     * Mysql基础..
     *
     * @param begin    开始
     * @param pageSize 页面大小
     * @return {@link List}<{@link Furn}>
     */
    @Override
    public List<Furn> getPageItems(int begin, int pageSize) {

        String sql = "SELECT `id`, `name` , `maker`, `price`, `sales`, `stock`, " +
                "`img_path` imgPath FROM furn LIMIT ?, ?";

        return queryMulti(sql, Furn.class, begin, pageSize);
    }

    /**
     * 根据begin、pageSize、name来获取要显示的家具信息
     *
     * @param begin    开始
     * @param pageSize 页面大小
     * @param name     名字
     * @return {@link List}<{@link Furn}>
     */
    @Override
    public List<Furn> getPageItemsByname(int begin, int pageSize, String name) {
        String sql = "SELECT `id`, `name` , `maker`, `price`, `sales`, `stock`, " +
                "`img_path` imgPath FROM furn where `name` like ? LIMIT ?, ?";
        return queryMulti(sql, Furn.class,"%"+name+"%" ,begin, pageSize);
    }

    /**
     * 根据名字来获取总记录数
     *
     * @param name
     * @return
     */
    @Override
    public int getTotalRowByName(String name) {
        String sql = "SELECT COUNT(*) FROM `furn` WHERE `name` LIKE ?";
        return ((Number)queryScalar(sql, "%" + name + "%")).intValue();
    }
}
