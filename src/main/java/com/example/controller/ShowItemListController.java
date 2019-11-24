package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
	
	private static final int VIEW_SIZE = 9;

	/**
	 * 全商品情報を表示します.
	 * 
	 * @param model 全商品情報を格納
	 * @return 全商品情報
	 */
	@RequestMapping("/show-item-list")
	public String showItemList(Model model, Integer page) {
		
		if(page == null) {
			//ページ数の指定がない場合は１ページ目を表示させる
			page = 1;
		}
		
		List<Item> itemList = service.showList();
		model.addAttribute("itemList", itemList);
		
		// 表示させたいページ数、ページサイズ、従業員リストを渡し１ページに表示させる従業員リストを絞り込み
		Page<Item> itemPage = service.showListPaging(page, VIEW_SIZE, itemList);
		model.addAttribute("itemPage", itemPage);

		// ページングのリンクに使うページ数をスコープに格納 (例)28件あり1ページにつき10件表示させる場合→1,2,3がpageNumbersに入る
		List<Integer> pageNumbers = calcPageNumbers(model, itemPage);
		model.addAttribute("pageNumbers", pageNumbers);		
		
		return "item_list";
	}
	
	/**
	 * ページングのリンクに使うページ数をスコープに格納 (例)28件あり1ページにつき10件表示させる場合→1,2,3がpageNumbersに入る
	 * 
	 * @param model        モデル
	 * @param itemPage ページング情報
	 */
	private List<Integer> calcPageNumbers(Model model, Page<Item> itemPage) {
		int totalPages = itemPage.getTotalPages();
		List<Integer> pageNumbers = null;
		if (totalPages > 0) {
			pageNumbers = new ArrayList<Integer>();
			for (int i = 1; i <= totalPages; i++) {
				pageNumbers.add(i);
			}
		}
		return pageNumbers;
	}
	
	
	
	
	
	
	
	
}
