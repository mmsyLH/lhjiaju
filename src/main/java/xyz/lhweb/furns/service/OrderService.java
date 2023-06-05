package xyz.lhweb.furns.service;

import xyz.lhweb.furns.bean.Cart;
import xyz.lhweb.furns.bean.CartItem;
import xyz.lhweb.furns.bean.Order;

import java.util.List;

/**
 * 订单服务
 *
 * @author 罗汉
 * @date 2023/04/08
 */
public interface OrderService {

	/**
	 * 保存订单 1 生成订单 2 订单是根据cart来生成的，cart在session中，通过web层，传入saveOrder 3 订单是和
	 *
	 * @param cart     车
	 * @param memberId 会员id
	 * @return {@link String}
	 */
	String saveOrder(Cart cart, int memberId);
	/**
	 * 通过oid查询订单
	 *
	 * @param oid oid
	 * @return {@link Order}
	 */
	Order queryOrderByOid(String oid);

	/**
	 * 通过id获取订单信息
	 * 根据订单id查询订单详情
	 *
	 * @param begin    开始
	 * @param pageSize 页面大小
	 * @param oid      项id
	 * @return {@link List}<{@link CartItem}>
	 */
	List<CartItem> getOrderInfoById(int begin, int pageSize, String oid);
}
