package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Item;
import com.example.repository.ItemRepository;

@Service
@Transactional
public class ShowItemDetailService {

	@Autowired
	private ItemRepository itemRepository;
	
	/**
	 * 商品情報を取得するメソッド.
	 * @param id 商品ID
	 * @return 商品情報
	 */
	public Item showItemDetail(Integer id) {
		return itemRepository.load(id);
	}
	
}
