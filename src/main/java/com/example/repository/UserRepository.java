package com.example.repository;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.User;

/**
 * Usersテーブルを操作するレポジトリー.
 * @author yuichi
 *
 */
@Repository
public class UserRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

//	INSERTだけの場合必要ないためコメントアウト
//	private final static RowMapper<User> USER_ROW_MAPPER = (rs,i)->{
//		User user = new User();
//		user.setId(rs.getInt("id"));
//		user.setName(rs.getString("name"));
//		user.setEmail(rs.getString("email"));
//		user.setPassword(rs.getString("password"));
//		user.setZipcode(rs.getString("zipcode"));
//		user.setTelephone(rs.getString("telephone"));
//		return user;
//	};
	
	/**
	 * ユーザー情報の追加を行うINSERTメソッド.
	 * @param user 登録するユーザー情報
	 */
	public void insert(User user) {
		String sql = "INSERT INTO users(name,email,password,zipcode,address,telephone) "
				+ "VALUES(:name,:email,:password,:zipcode,:address,:telephone)";
		SqlParameterSource param = new MapSqlParameterSource()
				.addValue("name", user.getName())
				.addValue("email", user.getEmail())
				.addValue("password", user.getPassword())
				.addValue("zipcode", user.getZipcode())
				.addValue("address", user.getAddress())
				.addValue("telephone", user.getTelephone());
		template.update(sql, param);
	}
	
}
