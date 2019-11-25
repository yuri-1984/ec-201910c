package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.User;
import com.example.repository.UserRepository;

@Service
@Transactional
public class RegisterUserService {

	@Autowired
	private UserRepository userRepository;
	
	/**
	 * ユーザー情報を登録するメソッド.
	 * @param user 登録するユーザー情報
	 */
	public void registerUser(User user) {
		userRepository.insert(user);
	}
	
}
