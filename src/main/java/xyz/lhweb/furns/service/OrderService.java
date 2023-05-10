package xyz.lhweb.furns.service;

import xyz.lhweb.furns.bean.Cart;

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
}
