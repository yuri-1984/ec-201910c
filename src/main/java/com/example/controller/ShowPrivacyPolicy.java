package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * フッターの中の個人情報関連のページを表示するクラス.
 * @author yuichi
 *
 */
@Controller
@RequestMapping("/privacy")
public class ShowPrivacyPolicy {

	/**
	 * 個人情報保護方針の画面を表示.
	 * @return 個人情報保護方針の画面
	 */
	@RequestMapping("/")
	public String privacyPolicy() {
		return "privacy/privacy_policy.html";
	}
	
	/**
	 * 個人情報の取り扱いについての表示.
	 * @return 個人情報の取り扱いについて画面
	 */
	@RequestMapping("/handle")
	public String handle() {
		return "privacy/handle.html";
	}
	
	/**
	 * 情報セキュリティ基本方針の表示.
	 * @return 情報セキュリティ基本方針の画面
	 */
	@RequestMapping("/security")
	public String security() {
		return "privacy/security_policy.html";
	}
	
}
