package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Item;
import com.example.domain.Topping;
import com.example.repository.ItemRepository;
import com.example.repository.ToppingRepository;

/**
 * 商品詳細を取得するサービスクラス.
 * @author yuichi
 *
 */
@Service
@Transactional
public class ShowItemDetailService {

	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private ToppingRepository toppingRepository;
	
	/**
	 * 商品情報を取得するメソッド.
	 * @param id 商品ID
	 * @return 商品情報
	 */
	public Item showItemDetail(Integer id) {
		return itemRepository.load(id);
	}
	
	/**
	 * トッピングリストを取得するメソッド.
	 * @return
	 */
	public List<Topping> showToppingList(){
		return toppingRepository.findAll();
	}
	
}
