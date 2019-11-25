package com.example.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.example.domain.Order;

/**
 * 注文内容を操作するリポジトリ
 * @author shun053012
 *
 */
public class OrderRepository {
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	/**
	 * データベースから取ってきた値をオブジェクトにセット.
	 * 
	 */
	public static final RowMapper<Order> ORDER_ROW_MAPPER = (rs,i)->{
		
		Order order = new Order();
		order.setId(rs.getInt("id"));
		order.setUserId(rs.getInt("user_id"));
		order.setStatus(rs.getInt("status"));
		order.setTotalPrice(rs.getInt("total_price"));
		order.setOrderDate(rs.getDate("order_date"));
		order.setDestinationName(rs.getString("destination_name"));
		order.setDestinationEmail(rs.getString("destination_email"));
		order.setDestinationZipcode(rs.getString("destination_zipcode"));
		order.setDestinationAddress(rs.getString("destination_address"));
		order.setDestinationTel(rs.getString("destination_tel"));
		order.setDeliveryTime(rs.getTimestamp("delivery_time"));
		order.setPaymentMethod(rs.getInt("payment_method"));
		
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
		
		Order order = template.queryForObject(sql,param,ORDER_ROW_MAPPER);
		
		return order;
		
		
	}

}
