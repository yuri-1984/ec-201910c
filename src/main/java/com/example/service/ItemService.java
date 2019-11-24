package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Item;
import com.example.repository.ItemRepository;

/**
 * 商品情報を操作するサービスクラス.
 * 
 * @author hashimotoshinya
 *
 */
@Service
@Transactional
public class ItemService {

	@Autowired
	private ItemRepository repository;

	/**
	 * 入力した文字列で曖昧検索をします.
	 * 
	 * @param name 入力された文字列
	 * @return 曖昧検索にヒットした商品情報
	 */
	public List<Item> findByName(String name) {
		return repository.findByName(name);
	}

	/**
	 * 全商品情報を検索します.
	 * 
	 * @return 全商品の情報
	 */
	public List<Item> showList() {
		return repository.findAll();
	}

}
