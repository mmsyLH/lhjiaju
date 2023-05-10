package xyz.lhweb.furns.service.impl;

import xyz.lhweb.furns.bean.Furn;
import xyz.lhweb.furns.bean.Page;
import xyz.lhweb.furns.dao.FurnDao;
import xyz.lhweb.furns.factory.DaoFactory;
import xyz.lhweb.furns.service.FurnService;

import java.util.List;

/**
 * 家具服务实现
 *
 * @author 罗汉
 * @date 2023/04/01
 */
public class FurnServiceImpl implements FurnService {
    private FurnDao furnDao= DaoFactory.getFurnDao();
    /**
     * 查询家具
     *
     * @return {@link List}<{@link Furn}>
     */
    @Override
    public List<Furn> queryFurns() {
        return furnDao.queryFurns();
    }

    /**
     * 添加家具
     *
     * @param furn 家具
     * @return int
     */
    @Override
    public int addFurns(Furn furn) {
        return furnDao.addFurns(furn);
    }

    /**
     * 删除家具通过id
     *
     * @param id id
     * @return int 是受影响的行数
     */
    @Override
    public int deleteFurnById(int id) {
        return furnDao.deleteFurnById(id);
    }

    /**
     * 通过id查询家具
     *
     * @return {@link Furn}
     */
    @Override
    public Furn queryFurnById(int id) {
        return furnDao.queryFurnById(id);
    }

    /**
     * 更新家具
     * 将传入的furn对象，更新到数据库，根据id  id是唯一标识
     *
     * @param furn 家具
     * @return int
     */
    @Override
    public int updateFurn(Furn furn) {
        return furnDao.updateFurn(furn);
    }

    /**
     * 根据传入的begin和pageSize返回传入对应page对象
     *
     * @param pageNo    开始
     * @param pageSize 页面大小
     * @return {@link Page}<{@link Furn}>
     */
    @Override
    public Page<Furn> page(int pageNo, int pageSize) {
        //先创建一个page对象，然后根据实际情况填充属性
        Page<Furn> page = new Page<>();
        /**
         *
         ////因为每页显示多少条记录，是其它地方也可以使用
         ////ctrl+shift+u => 切换大小写
         //public static  final  Integer PAGE_SIZE = 3;
         //
         ////表示显示当前页[显示第几页]
         ////前端页面来的
         //private Integer pageNo;
         ////表示每页显示几条记录
         //private Integer pageSize = PAGE_SIZE;
         ////表示共有多少页, 他是计算得到
         //private Integer pageTotalCount;
         ////表示的是共有多少条记录 , 通过totalRow和pageSize
         ////计算得到pageTotalCount
         ////是可以同数据库DB来的->DAO
         //private Integer totalRow;
         ////表示当前页，要显示的数据
         ////从DB来的->DAO
         //private List<T> items;
         ////分页导航的字符串
         //private String url;
         */
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        int totalRow = furnDao.getTotalRow();
        page.setTotalRow(totalRow);
        //比如 6 2  =》  6 / 2 = 3
        //比如 5 2  =》  5 / 2 = 2
        //验证 7 3 =>
        //验证 0 2 =>
        int pageTotalCount=totalRow/pageSize;
        if (totalRow%pageSize>0){
            pageTotalCount+=1;
        }
        page.setPageTotalCount(pageTotalCount);
        // private List<T> items;
        //验证: pageNo = 1 pageSize = 3 => begin =0
        //验证: pageNo = 3 pageSize = 2 => begin =4
        //OK => 但是注意这里隐藏一个坑, 现在你看不到, 后面会暴露
        int begin=(pageNo-1)*pageSize;
        List<Furn> pageItems = furnDao.getPageItems(begin, pageSize);
        page.setItems(pageItems);
        return page;
    }

    /**
     * 根据传入的pageNo,pageSize,name返回传入对应的page对象
     *
     * @param pageNo   页面没有
     * @param pageSize 页面大小
     * @param name     名字
     * @return {@link Page}<{@link Furn}>
     */
    @Override
    public Page<Furn> pageByName(int pageNo, int pageSize, String name) {
        //先创建一个page对象，然后根据实际情况填充属性
        Page<Furn> page = new Page<>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        int totalRow = furnDao.getTotalRowByName(name);
        page.setTotalRow(totalRow);
        //比如 6 2  =》  6 / 2 = 3
        //比如 5 2  =》  5 / 2 = 2
        int pageTotalCount=totalRow/pageSize;
        if (totalRow%pageSize>0){
            pageTotalCount+=1;
        }
        page.setPageTotalCount(pageTotalCount);
        // private List<T> items;
        //验证: pageNo = 1 pageSize = 3 => begin =0
        //验证: pageNo = 3 pageSize = 2 => begin =4
        //OK => 但是注意这里隐藏一个坑, 现在你看不到, 后面会暴露
        int begin=(pageNo-1)*pageSize;
        List<Furn> pageItems = furnDao.getPageItemsByname(begin, pageSize,name);
        page.setItems(pageItems);
        return page;
    }

}
