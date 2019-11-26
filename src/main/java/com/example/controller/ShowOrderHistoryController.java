package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 注文履歴を表示するコントローラクラス.
 * @author yuichi
 *
 */
@Controller
public class ShowOrderHistoryController {

	/**
	 * 注文履歴を表示するメソッド.
	 * ※仮作成しているため、ユーザーIDを引数でとってきて
	 * 　ユーザーごとの個別の情報を表示できるようにする
	 * @return 注文履歴ページ
	 */
	@RequestMapping("/showOrderHistory")
	public String showOrderHistory() {
		return "order_history.html";
	}
	
}
