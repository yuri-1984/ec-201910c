package com.example.repository;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Item;
import com.example.domain.Order;
import com.example.domain.OrderItem;
import com.example.domain.OrderTopping;
import com.example.domain.Topping;

@Repository
public class OrderRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;

	public static final ResultSetExtractor<Order> ORDER_EXTRACTOR = (rs) -> {
		int preId = 0;
		Order order = null;
		List<OrderItem> orderItemList = null;
		List<OrderTopping> orderToppingList = null;
		Topping topping = null;
		Item item = null;
		while (rs.next()) {
			if (rs.getInt("o_id") !=preId) {
				order = new Order();
				order.setId(rs.getInt("o_id"));
				order.setUserId(rs.getInt("o_userid"));
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

				;

				preId = rs.getInt("o_id");
			}
			if (rs.getInt("oi_id") != 0) {
				OrderItem orderitem = new OrderItem();
				orderitem.setId(rs.getInt("oi_id"));
				orderitem.setOrderId(rs.getInt("oi_order_id"));
				orderitem.setQuantity(rs.getInt("oi_quantity"));
				orderitem.setItemId(rs.getInt("oi_item_id"));
				//Stringに変換するために一度Charを入れてから配列で受け取る。
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
			if(rs.getInt("t_id") !=0) {
				topping = new Topping();
				topping.setId(rs.getInt("t_id"));
				topping.setName(rs.getString("t_mame"));
				topping.setPriceL(rs.getInt("t_price_m"));
				topping.setPriceM(rs.getInt("t_price_l"));
				
				
			}
			if(rs.getInt("i_id")!=0) {
				item = new Item();
				item.setId(rs.getInt("i_id"));
				item.setName(rs.getString("i_descroption"));
				item.setPriceM(rs.getInt("i_price_m"));
				item.setPriceL(rs.getInt("i_price_l"));
				item.setImagePath(rs.getString("i_image_path"));

			}
			
	}
		return order;

	};
	
	

/**
 * 注文内容を検索する.
 * @param userId
 * @param status
 * @return 注文内容 
 */
public Order findByUserIdAndStatus(Integer userId,Integer status) {
	
	String sql="select id,user_id,status,total_price,order_date,destination_name,destination_email,destination_zipcode,destination_address,destination_tell,delivery_time,payment_method from orders where user_id=:userId && status=:status";
	
	SqlParameterSource param=new MapSqlParameterSource().addValue("userId", userId).addValue("status",status);
	
	Order order = template.query(sql,param,ORDER_EXTRACTOR);
	
	return order;
	
	

}
}

