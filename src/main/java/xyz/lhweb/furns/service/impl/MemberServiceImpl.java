package xyz.lhweb.furns.service.impl;

import xyz.lhweb.furns.bean.Member;
import xyz.lhweb.furns.bean.Page;
import xyz.lhweb.furns.dao.BasicDAO;
import xyz.lhweb.furns.dao.MemberDAO;
import xyz.lhweb.furns.factory.DaoFactory;
import xyz.lhweb.furns.service.MemberService;
import xyz.lhweb.furns.utils.SendEmail;

import java.util.List;

/**
 * 会员服务实现
 *
 * @author 罗汉
 * @date 2023/04/01
 */
public class MemberServiceImpl extends BasicDAO<Member> implements MemberService {
    private MemberDAO memberDAO;

    public MemberServiceImpl() {
        memberDAO=DaoFactory.getInstance().getMemberDAO();
    }

    /**
     * 注册会员
     *
     * @param member 会员
     * @return boolean
     */
    @Override
    public boolean registerMember(Member member) {
        int i = memberDAO.saveMember(member);
        try {
            SendEmail.sendMail(member.getEmail(), member.getCode());
        } catch (Exception e) {
            System.out.println("发送邮件异常:"+getClass().getName());
            throw new RuntimeException(e);
        }
        return i==1;
    }

    /**
     * 判断用户名存在
     *
     * @param username 用户名
     * @return boolean
     */
    @Override
    public boolean isExistsUsername(String username) {
        //ctrl + b   =》编译类的方法
        //ctrl + alt +b =》 的方法
        return memberDAO.queryMemberByUsername(username) != null;
    }

    /**
     * 登录
     *
     * @param member 会员
     * @return {@link Member}
     */
    @Override
    public Member login(Member member) {
        return memberDAO.queryMemberByUsernameAndPassword(member.getUsername(), member.getPassword());
    }

    /**
     * @param username
     * @return
     */
    @Override
    public Member queryMemberByUsername(String username) {
        return memberDAO.queryMemberByUsername(username);
    }

    /**
     * @param code
     * @return
     */
    @Override
    public Member findByCode(String code) {
        return memberDAO.findByCode(code);
    }

    /**
     * 更新会员
     *
     * @param member 会员
     * @return {@link Boolean}
     */
    @Override
    public Boolean updateMember(Member member) {
        return memberDAO.updateMember(member)==1;
    }

    /**
     * 页面名字
     *
     * @param pageNo   页面没有
     * @param pageSize 页面大小
     * @param name     名字
     * @return {@link Page}<{@link Member}>
     */
    @Override
    public Page<Member> pageByName(int pageNo, int pageSize, String name) {
        // 先创建一个page对象，然后根据实际情况填充属性
        Page<Member> page = new Page<>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        int totalRow = memberDAO.getTotalRowByName(name);
        page.setTotalRow(totalRow);
        // 比如 6 2 =》 6 / 2 = 3
        // 比如 5 2 =》 5 / 2 = 2
        int pageTotalCount = totalRow / pageSize;
        if (totalRow % pageSize > 0) {
            pageTotalCount += 1;
        }
        page.setPageTotalCount(pageTotalCount);
        // private List<T> items;
        // 验证: pageNo = 1 pageSize = 3 => begin =0
        // 验证: pageNo = 3 pageSize = 2 => begin =4
        // OK => 但是注意这里隐藏一个坑, 现在你看不到, 后面会暴露
        int begin = (pageNo - 1) * pageSize;
        List<Member> pageItems = memberDAO.getPageItemsByname(begin, pageSize, name);
        // 这里获取图片路绝对径将图片转换为base64
        // for (Furn fun:pageItems){
        // //遍历明细 获取明细中图片路径并转换为文件流
        // //直接将路径写死了 这是绝对路径 其实可以上网上找 获取服务器目录的代码
        // String strImg = fun.getImgPath();//图片路径 我写了一份的 没事算了 后期你再找 你自己可以改造
        // String StrSystemUrl =
        // "F:\\JavaWorksparce\\ecilpseWorkspace\\2203840110_luohan\\src\\main\\webapp\\";
        // //转base64的代码我就不写了 直接上网上找这种代码 没必要写 网上大把
        // //其实 图片可以压缩的 还可以用代码裁剪 具体可以网上搜索资料
        // try {
        // String strBase64 = encodeBase64File(StrSystemUrl+strImg );
        // fun.setImgPath(strBase64);
        // }catch (Exception exception){
        // //这里不能反回null 了 不然一张图片有问题会影响其他的数据
        // //直接跳出当前循环即可
        // continue;
        // }
        // //File file = new File(StrSystemUrl+strImg);这是根据绝对路径找到文件并转换成文件流
        // }
        page.setItems(pageItems);
        return page;
    }

    /**
     * @return
     */
    @Override
    public Boolean delById(int id) {
        return memberDAO.delById(id)==1;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Member queryMemberById(int id) {
        return memberDAO.queryMemberById(id);
    }
}
