package xyz.lhweb.furns.test;

import xyz.lhweb.furns.bean.Furn;
import xyz.lhweb.furns.bean.Member;
import xyz.lhweb.furns.bean.Page;
import xyz.lhweb.furns.dao.FurnDao;
import xyz.lhweb.furns.dao.MemberDAO;
import xyz.lhweb.furns.dao.impl.FurnDaoImpl;
import xyz.lhweb.furns.dao.impl.MemberDAOImpl;
import xyz.lhweb.furns.service.FurnService;
import xyz.lhweb.furns.service.MemberService;
import xyz.lhweb.furns.service.impl.FurnServiceImpl;
import xyz.lhweb.furns.service.impl.MemberServiceImpl;

import java.math.BigDecimal;
import java.util.List;

/**
 * 会员测试
 *
 * @author 罗汉
 * @date 2023/04/01
 */
public class FurnTest {
    public static void main(String[] args) {
        FurnDao dao = new FurnDaoImpl();
        FurnService service = new FurnServiceImpl();
        //-------------------------------------DAO---------------------------------------
        // ----------------------测试查询------------------

        // daoQueryAll(dao);
        // daogetRow(dao);
        // daogetPageItems(dao);
        // daogetTotalRowByName(dao);
        // daogetPageItemsByname(dao);
        // ---------------------测试添加-------------------
        // daoAdd(dao);
        //------------------------  ------------------------
        // serviceQueryAll(service);
        // serviceAdd(service);
        // deleteByid(service);
        // queryFurnById(service);
        // updateFurn(service);
        // serviceGetPageItems(service);
        serviceGetPageItemsByname(service);

    }

    private static void serviceGetPageItemsByname(FurnService service) {
        Page<Furn> page = service.pageByName(1, 5,"小桌子");
        System.out.println(page);
        List<Furn> items = page.getItems();
        for (Furn item : items) {
            System.out.println(item);
        }
    }

    private static void daogetPageItemsByname(FurnDao dao) {
        List<Furn> furns = dao.getPageItemsByname(0, 2, "小桌子");
        for (Furn furn : furns) {
            System.out.println(furn);
        }
    }

    private static void daogetTotalRowByName(FurnDao dao) {
        System.out.println(dao.getTotalRowByName("小桌子"));
    }

    private static void serviceGetPageItems(FurnService service) {
        Page<Furn> page = service.page(1, 5);
        System.out.println(page);
        List<Furn> items = page.getItems();
        for (Furn item : items) {
            System.out.println(item);
        }
    }

    private static void daogetPageItems(FurnDao dao) {
        List<Furn> pageItems = dao.getPageItems(0, 5);
        for (Furn pageItem : pageItems) {
            System.out.println(pageItem);
        }
    }

    private static void daogetRow(FurnDao dao) {
        int totalRow = dao.getTotalRow();
        System.out.println(totalRow);
    }

    private static void updateFurn(FurnService service) {
        Furn furn = new Furn(13,"我不是玩具熊","罗汉家具", new BigDecimal("99.99"),555,7,"assets/images/product-image/default.jpg");
        int i = service.updateFurn(furn);
        if (i == 1) {
            System.out.println("修改成功："+furn);
        } else {
            System.out.println("修改失败");
        }
    }

    private static void queryFurnById(FurnService service) {
        Furn furn = service.queryFurnById(1);
        if (furn == null) {
            System.out.println("queryFurnById查询失败不存在");
        } else {
            System.out.println(furn);
        }

    }
    private static void deleteByid(FurnService service) {
        int i = service.deleteFurnById(5);
        if (i == 1) {
            System.out.println("删除成功：");
        } else {
            System.out.println("删除失败");
        }
    }

    private static void serviceAdd(FurnService service) {
        Furn furn = new Furn(null,"卡通风玩具猫4","罗汉家具", new BigDecimal("99.99"),555,7,"assets/images/product-image/default.jpg");
        int i = service.addFurns(furn);
        if (i == 1) {
            System.out.println("插入成功："+furn);
        } else {
            System.out.println("插入失败");
        }
    }

    private static void daoAdd(FurnDao dao) {
        Furn furn = new Furn(null,"卡通风玩具猫3","罗汉家具", new BigDecimal("99.99"),555,7,"assets/images/product-image/default.jpg");
        int i = dao.addFurns(furn);
        if (i == 1) {
            System.out.println("插入成功："+furn);
        } else {
            System.out.println("插入失败");
        }
    }

    /**
     * 服务查询所有
     *
     * @param service 服务
     */
    private static void serviceQueryAll(FurnService service) {
        List<Furn> furns = service.queryFurns();
        if (furns == null) {
            System.out.println("service查询失败不存在");
        } else {
            System.out.println(furns);
        }
    }

    /**
     * DAO查询所有
     *
     * @param memberDAO 会员DAO
     */
    private static void daoQueryAll(FurnDao memberDAO) {
        List<Furn> furns = memberDAO.queryFurns();
        if (furns == null) {
            System.out.println("dao查询失败不存在");
        } else {
            System.out.println(furns);
        }
    }

}
