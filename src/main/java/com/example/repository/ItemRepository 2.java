package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Item;

/**
 * itemsデーブルを操作するレポジトリクラス.
 * 
 * @author hashimotoshinya
 *
 */
@Repository
public class ItemRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	private static final RowMapper<Item> ITEM_ROW_MAPPER = (rs, i) -> {
		Item item = new Item();
//		Topping topping = new Topping();
//		List<Topping> toppingList = new ArrayList<>();

//		topping.setId(rs.getInt("t_id"));
//		topping.setName(rs.getString("t_name"));
//		topping.setPriceL(rs.getInt("t_price_m"));
//		topping.setPriceL(rs.getInt("t_price_m"));
//		toppingList.add(topping);

		item.setId(rs.getInt("id"));
		item.setName(rs.getString("name"));
		item.setDescription(rs.getString("description"));
		item.setPriceM(rs.getInt("price_m"));
		item.setPriceL(rs.getInt("price_l"));
		item.setImagePath(rs.getString("image_path"));
		item.setDeleted(rs.getBoolean("deleted"));
//		item.setToppingList(toppingList);

		return item;
	};

	/**
	 * IDで検索した商品情報を取得するメソッド.
	 * @param id 商品ID
	 * @return 商品情報
	 */
	public Item load(Integer id) {
		String sql = "SELECT id, name, description, price_m, price_l, image_path, deleted FROM items WHERE id=:id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		Item item = template.queryForObject(sql, param, ITEM_ROW_MAPPER);
		return item;
	}
	
	/**
	 * 入力された文字列で曖昧検索をします.
	 * 
	 * @param name 入力された文字列
	 * @return 曖昧検索でヒットした商品情報
	 */
	public List<Item> findByName(String name) {
//		String sql="SELECT i.id, i.name, i.description, i.price_m, i.price_l, i.image_path, i.deleted, t.id i_id, t.name t_name, t.price_m t_price_m, t.price_l t_price_l from items i join toppings on ";
		String sql = "SELECT id, name, description, price_m, price_l, image_path, deleted FROM items WHERE name ILIKE :name ORDER BY price_m";
		SqlParameterSource param = new MapSqlParameterSource().addValue("name", '%' + name + '%');
		return template.query(sql, param, ITEM_ROW_MAPPER);
		
	}

	/**
	 * 全商品を検索します.
	 * 
	 * @return 全商品情報
	 */
	public List<Item> findAll() {
		String sql = "SELECT id, name, description, price_m, price_l, image_path, deleted FROM items ORDER BY price_m";
		return template.query(sql, ITEM_ROW_MAPPER);
	}
}
