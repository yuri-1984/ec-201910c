package com.example.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.OrderItem;
import com.fasterxml.jackson.databind.util.BeanUtil;

/**
 * order_itemsテーブルを操作するレポジトリークラス.
 * @author hiraokayuri
 *
 */
@Repository
public class OrderItemRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	

	/**
	 * 注文商品を追加するメソッド.
	 * 
	 * @param orderitem
	 */
	public void insert(OrderItem orderitem) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(orderitem);
		String sql = "insert into order_items(item_id,order_id,quantity, size) ";
		template.update(sql, param);	
		
	}
	
	/**
	 * 注文商品を削除するメソッド.
	 * 
	 * @param id
	 */
	public void deleteById(Integer id) {
		String deleteSql ="WITH deleted AS(DELETE FROM order_items WHERE id =:id RETURNING id) DELETE FROM order_toppings WHERE order_item_id IN (SELECT id FROM deleted)";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		template.update(deleteSql, param);
		
		
		
	}
	
	
	

}
