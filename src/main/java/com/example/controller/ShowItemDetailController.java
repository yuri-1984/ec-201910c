package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Item;
import com.example.domain.Topping;
import com.example.service.ShowItemDetailService;

/**
 * 商品の詳細を表示するコントローラクラス.
 * @author yuichi
 *
 */
@Controller
public class ShowItemDetailController {

	@Autowired
	private ShowItemDetailService showItemDetailService;
	
	/**
	 * リクエストパラメータで受け取った商品IDを基に
	 * 商品情報を取得して商品詳細ページを表示するメソッド.
	 * @param id 商品ID
	 * @param model リクエストスコープ
	 * @return 商品詳細ページ
	 */
	@RequestMapping("/showItemDetail")
	public String showItemDetail(Integer id, Model model) {
		Item item = showItemDetailService.showItemDetail(id);
		List<Topping> toppingList = showItemDetailService.showToppingList();
		item.setToppingList(toppingList);
		model.addAttribute("item", item);
		return "item_detail.html";
	}
	
	
}
