package com.example.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.OrderTopping;

/**
 * order_toppingテーブルを操作するレポジトリークラス.
 * 
 * @author yuichi
 *
 */
@Repository
public class OrderToppingRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * 注文トッピングをDBへ追加するメソッド.
	 * 
	 * @param orderTopping 注文トッピング情報
	 */
	public void insert(OrderTopping orderTopping) {

//		SqlParameterSource param = new BeanPropertySqlParameterSource(orderTopping);
		System.out.println("大変だ" + orderTopping);
		String sql = "INSERT INTO order_toppings(topping_id, order_item_id) VALUES(:toppingId, :orderItemId)";
		SqlParameterSource param = new MapSqlParameterSource().addValue("toppingId", orderTopping.getToppingId())
				.addValue("orderItemId", orderTopping.getOrderItemId());
		template.update(sql, param);
	}
}
