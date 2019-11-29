package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.User;
import com.example.repository.UserRepository;

/**
 * ユーザー登録処理を行うサービスクラス.
 * @author yuichi
 *
 */
@Service
@Transactional
public class RegisterUserService {

	@Autowired
	private UserRepository userRepository;
//	@Autowired
//	private PasswordEncoder passwordEncoder;
	
	/**
	 * ユーザー情報を登録するメソッド.
	 * @param user 登録するユーザー情報
	 */
	public void registerUser(User user) {
		// パスワードをハッシュ化してセット
//		user.setPassword(user.getPassword());		
		userRepository.insert(user);
	}
	
	/**
	 * メールアドレスでユーザー情報を検索するメソッド.
	 * @param email メールアドレス
	 * @return 検索されたユーザー情報
	 */
	public List<User> findByEmail(String email) {
		List<User> userList = userRepository.findByEmail(email);
		return userList;
	}
}
