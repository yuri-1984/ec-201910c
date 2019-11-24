package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Item;
import com.example.service.ItemService;

/**
 * 全商品情報を表示します.
 * 
 * @author hashimotoshinya
 *
 */
@Controller
@RequestMapping("")
public class ShowItemListController {

	@Autowired
	private ItemService service;

	/**
	 * 全商品情報を表示します.
	 * 
	 * @param model 全商品情報を格納
	 * @return 全商品情報
	 */
	@RequestMapping("/show-item-list")
	public String showItemList(Model model) {
		List<Item> itemList = service.showList();
		model.addAttribute("itemList", itemList);

		return "item_list";
	}
}
