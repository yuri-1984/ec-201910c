package com.example.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.User;
import com.example.form.RegisterUserForm;
import com.example.service.RegisterUserService;

/**
 * ユーザー登録処理を行うコントローラクラス.
 * @author yuichi
 *
 */
@Controller
public class ResisterUserController {

	@Autowired
	private RegisterUserService userRegisterService;

	/**
	 * エラーチェック用.
	 * @return 空のフォームオブジェクト
	 */
	@ModelAttribute
	public RegisterUserForm setUpUserRegisterForm() {
		return new RegisterUserForm();
	}

	/**
	 * ユーザー登録画面に遷移するメソッド.
	 * @return ユーザー登録画面
	 */
	@RequestMapping("/showRegisterUserPage")
	public String showRegisterUserPage() {
		return "register_user";
	}

	/**
	 * ユーザー登録を行うメソッド.
	 * @param form 入力されたユーザ情報(リクエストパラメータ)
	 * @param result エラーチェック結果
	 * @return ログイン画面(エラーがある場合、入力画面に戻る)
	 */
	@RequestMapping("/registerUser")
	public String registerUser(
			@Validated 
			RegisterUserForm form,
			BindingResult result,
			Model model) {
		if(result.hasErrors()) {			
			return showRegisterUserPage();
		}
		System.out.println(form);
		User user = new User();
		BeanUtils.copyProperties(form, user);
		userRegisterService.registerUser(user);
		return "redirect:/toLoginPage";
	}

}