package com.example.repository;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.example.domain.Item;
import com.example.domain.Order;
import com.example.domain.OrderItem;
import com.example.domain.OrderTopping;
import com.example.domain.Topping;

/**
 * ordersテーブルを操作するリポジトリ.
 * 
 * @author hiraokayuri
 *
 */
@Repository
public class OrderRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	private SimpleJdbcInsert insert;

	@PostConstruct
	public void init() {
		SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert((JdbcTemplate) template.getJdbcOperations());
		SimpleJdbcInsert withTableName = simpleJdbcInsert.withTableName("orders");
		insert = withTableName.usingGeneratedKeyColumns("id");

	}

	public static final ResultSetExtractor<List<Order>> ORDER_EXTRACTOR = (rs) -> {
		List<Order> orderList = new LinkedList<Order>();
		int preId = 0;
		Order order = null;
		List<OrderItem> orderItemList = null;
		List<OrderTopping> orderToppingList = null;
		Topping topping = null;
		Item item = null;
		while (rs.next()) {
			if (rs.getInt("o_id") != preId) {
				order = new Order();
				order.setId(rs.getInt("o_id"));
				order.setUserId(rs.getInt("o_user_id"));
				order.setStatus(rs.getInt("o_status"));
				order.setTotalPrice(rs.getInt("o_total_price"));
				order.setOrderDate(rs.getDate("o_order_date"));
				order.setDestinationName(rs.getString("o_destination_name"));
				order.setDestinationEmail(rs.getString("o_destination_email"));
				order.setDestinationZipcode(rs.getString("o_destination_zipcode"));
				order.setDestinationAddress(rs.getString("o_destination_address"));
				order.setDestinationTel(rs.getString("o_destination_tel"));
				order.setDeliveryTime(rs.getTimestamp("o_delivery_time"));
				order.setPaymentMethod(rs.getInt("o_payment_method"));

				orderItemList = new ArrayList<>();
				order.setOrderItemList(orderItemList);
				orderList.add(order);

				preId = rs.getInt("o_id");
			}
			if (rs.getInt("oi_id") != 0) {
				OrderItem orderitem = new OrderItem();
				orderitem.setId(rs.getInt("oi_id"));
				orderitem.setOrderId(rs.getInt("oi_order_id"));
				orderitem.setQuantity(rs.getInt("oi_quantity"));
				orderitem.setItemId(rs.getInt("oi_item_id"));
				// Stringに変換するために一度Charを入れてから配列で受け取る。
				char[] str = (rs.getString("oi_size").toCharArray());
				orderitem.setSize(str[0]);
				orderToppingList = new ArrayList<>();
				orderitem.setItem(item);
				orderitem.setOrderToppingList(orderToppingList);
				orderItemList.add(orderitem);

			}
			if (rs.getInt("ot_id") != 0) {
				OrderTopping orderTopping = new OrderTopping();
				orderTopping.setId(rs.getInt("ot_id"));
				orderTopping.setOrderItemId(rs.getInt("ot_order_item_id"));
				orderTopping.setToppingId(rs.getInt("ot_topping_id"));
				orderTopping.setTopping(topping);
				orderToppingList.add(orderTopping);
			}
			if (rs.getInt("t_id") != 0) {
				topping = new Topping();
				topping.setId(rs.getInt("t_id"));
				topping.setName(rs.getString("t_name"));
				topping.setPriceL(rs.getInt("t_price_m"));
				topping.setPriceM(rs.getInt("t_price_l"));

			}
			if (rs.getInt("i_id") != 0) {
				item = new Item();
				item.setId(rs.getInt("i_id"));
				item.setName(rs.getString("i_description"));
				item.setPriceM(rs.getInt("i_price_m"));
				item.setPriceL(rs.getInt("i_price_l"));
				item.setImagePath(rs.getString("i_image_path"));

			}
			preId = order.getId();

		}
		return orderList;

	};

	/**
	 * 注文内容を検索する.
	 * 
	 * @param userId 値が無い場合はsessionIdを格納する.
	 * @param status 注文前なので０を入れる.
	 * @return 注文内容 Orderオブジェクト.
	 */
	public Order findByUserIdAndStatus(Integer userId, Integer status) {

		String sql = "SELECT o.id o_id,o.user_id o_user_id,o.status o_status,o.total_price o_total_price,o.order_date o_order_date,o.destination_name o_destination_name,o.destination_email o_destination_email,o.destination_zipcode o_destination_zipcode,o.destination_address o_destination_address,o.destination_tel o_destination_tel,o.delivery_time o_delivery_time,o.payment_method o_payment_method,"
				+ " oi.id oi_id, oi.order_id oi_order_id,oi.quantity oi_quantity, oi.item_id oi_item_id, oi.size oi_size, ot.id ot_id, ot.order_item_id ot_order_item_id ,ot.topping_id ot_topping_id,t.id t_id, t.name t_name, t.price_m t_price_m,t.price_l t_price_l, i.id i_id,i.name i_name, i.description i_description, i.price_m i_price_m, i.price_l i_price_l,i.image_path i_image_path, i.deleted i_deleted"
				+ " FROM orders o LEFT OUTER JOIN order_items oi on o.id = oi.order_id"
				+ " LEFT OUTER JOIN order_toppings ot on oi.id = ot.order_item_id"
				+ " LEFT OUTER JOIN items i on i.id = oi.item_id"
				+ " LEFT OUTER JOIN toppings t on t.id = ot.topping_id WHERE o.user_id =:userId AND o.status =:status";
		SqlParameterSource param = new MapSqlParameterSource().addValue("userId", 1).addValue("status", status);

		List<Order> orderList = template.query(sql, param, ORDER_EXTRACTOR);


		if (orderList.size() == 0) {
			return null;
		}

		return orderList.get(0);
	}

	/**
	 * 注文内容検索する.
	 * 
	 * @return 注文内容
	 */
	public List<Order> findAll() {
		String sql = "SELECT o.id o_id,o.user_id o_user_id,o.status o_status,o.total_price o_total_price,o.order_date o_order_date,o.destination_name o_destination_name,o.destination_email o_destination_email,o.destination_zipcode o_destination_zipcode,o.destination_address o_destination_address,o.destination_tel o_destination_tel,o.delivery_time o_delivery_time,o.payment_method o_payment_method,"
				+ " oi.id oi_id, oi.order_id oi_order_id,oi.quantity oi_quantity, oi.item_id oi_item_id, oi.size oi_size, ot.id ot_id, ot.order_item_id ot_order_item_id ,ot.topping_id ot_topping_id,t.id t_id, t.name t_name, t.price_m t_price_m,t.price_l t_price_l, i.id i_id,i.name i_name, i.description i_description, i.price_m i_price_m, i.price_l i_price_l,i.image_path i_image_path, i.deleted i_deleted"
				+ " FROM orders o" + " LEFT OUTER JOIN order_items oi on o.id = oi.order_id"
				+ " LEFT OUTER JOIN order_toppings ot on oi.id = ot.order_item_id"
				+ " LEFT OUTER JOIN items i on i.id = oi.item_id"
				+ " LEFT OUTER JOIN toppings t on t.id = ot.topping_id ";
		List<Order> orderList = template.query(sql, ORDER_EXTRACTOR);
		return orderList;

	}

	/**
	 * 注文内容とユーザ情報を登録する.
	 * 
	 * @param order リクエストパラメーター.
	 * 
	 * @return 注文内容とユーザ情報を登録する.
	 */
	public Order insert(Order order) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(order);
		Number key = insert.executeAndReturnKey(param);
		order.setId(key.intValue());

//		String sql = "INSERT INTO orders(user_id,status,total_price,order_date,destination_name,destination_email,destination_zipcode,destination_address,destination_tel,delivery_time,payment_method)"
//				+ " Values(:user_id, :status, :total_price, :order_date, :destination_name, :destination_email, :destination_zipcode, :destination_address, :destination_tel, :delivery_time, :payment_method)";
//		if (order.getId() != null) {
//			throw new NullPointerException();
//		}
//		template.update(sql, param);

		return order;
	}

	public Order load(Integer id) {
		String sql = "SELECT o.id o_id,o.user_id o_user_id,o.status o_status,o.total_price o_total_price,o.order_date o_order_date,o.destination_name o_destination_name,o.destination_email o_destination_email,o.destination_zipcode o_destination_zipcode,o.destination_address o_destination_address,o.destination_tel o_destination_tel,o.delivery_time o_delivery_time,o.payment_method o_payment_method,"
				+ " oi.id oi_id, oi.order_id oi_order_id,oi.quantity oi_quantity, oi.item_id oi_item_id, oi.size oi_size, ot.id ot_id, ot.order_item_id ot_order_item_id ,ot.topping_id ot_topping_id,t.id t_id, t.name t_name, t.price_m t_price_m,t.price_l t_price_l, i.id i_id,i.name i_name, i.description i_description, i.price_m i_price_m, i.price_l i_price_l,i.image_path i_image_path, i.deleted i_deleted"
				+ " FROM orders o LEFT OUTER JOIN order_items oi on o.id = oi.order_id"
				+ " LEFT OUTER JOIN order_toppings ot on oi.id = ot.order_item_id"
				+ " LEFT OUTER JOIN items i on i.id = oi.item_id"
				+ " LEFT OUTER JOIN toppings t on t.id = ot.topping_id "
				+ " LEFT OUTER JOIN users u on o.user_id = u.id where o.id=:id ";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		List<Order> orderList = template.query(sql, param, ORDER_EXTRACTOR);
		Order order = orderList.get(0);
		return order;
	}

	public void update(Order order) {
		String sql = "update o.id o_id=:id,o.user_id o_user_id=:userId,o.status o_status=status,o.total_price o_total_price=totalPrice,o.order_date o_order_date=:orderDate,o.destination_name o_destination_name=destinationName,o.destination_email o_destination_email=:destinationEmail,o.destination_zipcode o_destination_zipcode=destinationZipcode,o.destination_address o_destination_address=destinationAddress,o.destination_tel o_destination_tel=:destinationTel,o.delivery_time o_delivery_time=deliveryTime,o.payment_method o_payment_method=paymentMethod";
		SqlParameterSource param = new BeanPropertySqlParameterSource(order);
		template.update(sql, param);
	}

	@Override
	public String toString() {
		return "OrderRepository [template=" + template + "]";
	}

}
