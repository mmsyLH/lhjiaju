package xyz.lhweb.furns.bean;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Set;

/**
 * 购物车
 * 包含多个CartItem
 *
 * @author 罗汉
 * @date 2023/04/07
 */
public class Cart {
    // 用hashMap来保存
    private HashMap<Integer, CartItem> items = new HashMap<>();

    /**
     * 删除项目
     *  根据传入的id删除指定购物车里的CartItem
     * @param id id
     */
    public void deleteItem(int id) {
        items.remove(id);
    }

    public void clear() {
        items.clear();
    }

    /**
     * 修改指定CartItem的数量和价格 根据传入的id和count
     * CartItem
     *
     * @param id    id
     * @param count 数
     */
    public void updateCount(int id, int count) {
        CartItem cartItem = items.get(id);
        if (null != cartItem) {
            // !!!!!!先更新数量，再更新总价 这个总价是指的当前对应的总价，不是整个购物车的总价
            // cartItem.setCount(cartItem.getCount() + count);
            cartItem.setCount(count);
            // 更新总结
            cartItem.setTotalPrice(cartItem.getPrice().multiply(new BigDecimal(cartItem.getCount())));
        }
    }
    public boolean isEmpty(){
        return items.size()==0;
    }

    /**
     * 得到项目
     * // 购物车商品的总数量
     *
     * @return {@link HashMap}<{@link Integer}, {@link CartItem}>
     */
    public HashMap<Integer, CartItem> getItems() {
        return items;
    }

    /**
     * 购物车所有总价
     *
     * @return {@link BigDecimal}
     */
    public BigDecimal getCartTotalPrice() {
        BigDecimal cartTotalPrice = new BigDecimal(0);
        // 遍历items
        Set<Integer> keys = items.keySet();
        for (Integer id : keys) {
            CartItem cartItem = items.get(id);
            // 一定要吧add后的金额重新赋值 这才是总金额
            cartTotalPrice = cartTotalPrice.add(cartItem.getTotalPrice());
        }
        return cartTotalPrice;
    }

    /**
     * 得到总数量
     *
     * @return {@link Integer}
     */
    public Integer getTotalCount() {
        // 购物车商品的总数量
        int totalCount = 0;
        // 遍历items统计购物车商品总数量
        Set<Integer> keys = items.keySet();
        for (Integer key : keys) {// key 就是items里的id（Integer）
            totalCount += items.get(key).getCount();
        }
        return totalCount;
    }


    /**
     * 添加物品
     *
     * @param cartItem 车项目
     */
    public void addItem(CartItem cartItem) {
        CartItem item = items.get(cartItem.getId());
        if (item == null) {// 当前购物车还没有这个CarItem
            items.put(cartItem.getId(), cartItem);
        } else {
            // 修改总数量
            item.setCount(item.getCount() + 1);
            // 修改总价
            item.setTotalPrice(item.getTotalPrice().add(item.getPrice()));
        }
    }

    public Cart() {
    }

    @Override
    public String toString() {
        return "Cart{" +
                "Items=" + items +
                '}';
    }
}
