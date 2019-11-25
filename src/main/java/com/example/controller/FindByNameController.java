package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Item;
import com.example.service.ItemService;

/**
 * 曖昧検索を行うコントローラー.
 * 
 * @author hashimotoshinya
 *
 */
@Controller
@RequestMapping("")
public class FindByNameController{

	@Autowired
	private ItemService service;

	/**
	 * 入力された文字列で曖昧検索をします.
	 * 
	 * @param findName 曖昧検索をしたい文字列
	 * @param model    ヒットした商品情報を格納
	 * @return 検索ヒットした商品情報
	 */
	@RequestMapping("/find-by-name")
	public String findByName(String findName, Model model) {
		List<Item> itemList = null;
		if (findName.equals("")) {
			// 入力フィールドが空文字の場合、全商品を表示します。
			itemList = service.showList();
		} else {
			// 入力フィールドの文字列で曖昧検索を行います。
			itemList = service.findByName(findName);
			// ページングの数字からも検索できるように検索文字列をスコープに格納しておく
			model.addAttribute("findName", findName);
		}

		model.addAttribute("itemList", itemList);
		return "item_list";
	}

}
