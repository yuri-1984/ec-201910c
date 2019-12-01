package com.example.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Order;
import com.example.domain.OrderItem;
import com.example.domain.OrderTopping;
import com.example.form.OrderItemForm;
import com.example.repository.OrderItemRepository;
import com.example.repository.OrderRepository;
import com.example.repository.OrderToppingRepository;

//import com.example.domain.Order;
//import com.example.repository.OrderRepository;

/**
 * レポジトリーを操作するサービスクラス. 操作するレポジトリー： OrderRepository, OrderItemRepository,
 * OrderToppingRepository,
 * 
 * @author yuichi
 */
@Service
@Transactional
public class ShoppingCartService {

	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private OrderToppingRepository orderToppingRepository;
	@Autowired
	private OrderItemRepository orderItemRepository;

	/**
	 * ショッピングカートの中身を表示するメソッド.
	 * 
	 * @param userId ユーザーID
	 * @return オーダー情報
	 */
	public Order showCartList(Integer userId) {
		Order order = orderRepository.findByUserIdAndStatus(userId, 0);
		return order;
	}

	/**
	 * 注文トッピング情報をDBに追加するメソッド.
	 * 
	 * @param orderTopping 注文トッピング情報
	 */
	public void insertOrderTopping(OrderTopping orderTopping) {
		orderToppingRepository.insert(orderTopping);
	}

	/**
	 * 注文商品を追加するメソッド.
	 * 
	 * @param orderItem 注文商品
	 */
	public void insertOrderItem(OrderItem orderItem) {
		orderItemRepository.insert(orderItem);
	}

	public void addItem(Integer userId, OrderItemForm orderItemform) {
		Order order = orderRepository.findByUserIdAndStatus(userId, 0);
		if (order == null) {
			order = new Order();
			order.setUserId(userId);
			order.setStatus(0);
			order.setTotalPrice(0);
			orderRepository.insert(order);
		}

		OrderItem orderItem = new OrderItem();
		BeanUtils.copyProperties(orderItemform, orderItem);
		orderItem.setOrderId(order.getId());
		orderItemRepository.insert(orderItem);

		// itemとorderトッピングリストはテーブル結合でとってきてorderItemにset
		// orderItemをリクエストスコープに追加してフォワード？
		// ※価格の計算などはここまででは行っていない

		if (orderItemform.getToppingList() != null) {

			OrderTopping orderTopping;
			orderTopping = new OrderTopping();
			for (int toppingId : orderItemform.getToppingList()) {
				orderTopping.setOrderItemId(orderItem.getId());
				orderTopping.setToppingId(toppingId);
				System.out.println(orderTopping + "アイテムですよ！");
				orderToppingRepository.insert(orderTopping);

			}
		}

	}

	// order_toppingテーブルへの注文トッピングのINSERT
	// チェックボックスで選択されたトッピングの数だけ繰り返す

//		// order_toppingテーブルへの注文トッピングのINSERT
//		// チェックボックスで選択されたトッピングの数だけ繰り返す
//		for (int i = 0; i < form.getToppingId().length; i++) {
//			OrderTopping orderTopping = new OrderTopping();
//			Integer[] toppingId = form.getToppingId();
//			orderTopping.setToppingId(toppingId[i]);
//			orderTopping.setOrderItemId(form.getItemid());
//			shoppingCartService.insertOrderTopping(orderTopping);
//		}
//		// OrderItemオブジェクトを作る
//		OrderItem orderItem = new OrderItem();
//		orderItem.setItemId(form.getItemid());
//		orderItem.setOrderId(form.getOrderId());
//		orderItem.setQuantity(form.getQuantity());
//		orderItem.setSize(form.getSize());	
//		//itemとorderトッピングリストはテーブル結合でとってきてorderItemにset
//				//orderItemをリクエストスコープに追加してフォワード？
//				//※価格の計算などはここまででは行っていない
//	}

	/**
	 * ショッピングカートの中の注文情報を削除させる.
	 * 
	 * @param orderitemid
	 */
	public void deleteByOrderItemId(Integer orderitemid) {
		orderItemRepository.deleteById(orderitemid);
	}

}
