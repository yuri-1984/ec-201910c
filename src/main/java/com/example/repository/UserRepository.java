package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
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

	private final static RowMapper<User> USER_ROW_MAPPER = (rs,i)->{
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setName(rs.getString("name"));
		user.setEmail(rs.getString("email"));
		user.setPassword(rs.getString("password"));
		user.setZipcode(rs.getString("zipcode"));
		user.setTelephone(rs.getString("telephone"));
		return user;
	};
	
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
	
	/**
	 * メールアドレスでユーザー情報を検索するメソッド.
	 * @param email メールアドレス
	 * @return 検索されたユーザー情報
	 */
	public List<User> findByEmail(String email) {
		String sql = "SELECT id,name,email,password,zipcode,address,telephone "
				+ "FROM users WHERE email=:email";
		SqlParameterSource param = new MapSqlParameterSource().addValue("email", email);
		List<User> userList = template.query(sql, param, USER_ROW_MAPPER); 
		if(userList.size() == 0) {
			return null;
		}
		return userList;
	}
	public User load(Integer id) {
		String sql = "SELECT id,name,email,password,zipcode,address,telephone "
				+ "FROM users WHERE id = :id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		User user = template.queryForObject(sql, param,USER_ROW_MAPPER );
		return user;
	}
	
}
