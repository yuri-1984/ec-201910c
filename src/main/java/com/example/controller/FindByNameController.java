package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Item;
import com.example.service.ItemService;

@Controller
@RequestMapping("")
public class FindByNameController {
	
	@Autowired
	private ItemService service;
	
	
	@RequestMapping("")
	public String findByName(String name, Model model) {
		List<Item> itemList = null;
		if(name.equals("")) {
			itemList = service.showList();
		}else {
			itemList = service.findByName(name);
			model.addAttribute("name", name);
		}
		
		model.addAttribute("itemList", itemList);
		return "item_list";
	}

}
