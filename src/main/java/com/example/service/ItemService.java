package com.example.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
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
	
	/**
	 * ページング用メソッド.
	 * @param page 表示させたいページ数
	 * @param size １ページに表示させる従業員数
	 * @param itemList 絞り込み対象リスト
	 * @return １ページに表示されるサイズ分の従業員一覧情報
	 */
	public Page<Item> showListPaging(int page, int size, List<Item> itemList) {
	    // 表示させたいページ数を-1しなければうまく動かない
	    page--;
	    // どの従業員から表示させるかと言うカウント値
	    int startItemCount = page * size;
	    // 絞り込んだ後の従業員リストが入る変数
	    List<Item> list;

	    if (itemList.size() < startItemCount) {
	    	// (ありえないが)もし表示させたい従業員カウントがサイズよりも大きい場合は空のリストを返す
	        list = Collections.emptyList();
	    } else {
	    	// 該当ページに表示させる従業員一覧を作成
	        int toIndex = Math.min(startItemCount + size, itemList.size());
	        list = itemList.subList(startItemCount, toIndex);
	    }

	    // 上記で作成した該当ページに表示させる従業員一覧をページングできる形に変換して返す
	    Page<Item> itemPage
	      = new PageImpl<Item>(list, PageRequest.of(page, size), itemList.size());
	    return itemPage;
	}
	
	/**
	 * オートコンプリート用にJavaScriptの配列の中身を文字列で作ります.
	 * 
	 * @param itemList 商品一覧
	 * @return　オートコンプリート用JavaScriptの配列の文字列
	 * 　　　　　(例) "渡辺三郎","佐藤次郎","山本八郎","小林九子"
	 */
	public StringBuilder getItemListForAutocomplete(List<Item> itemList) {
		StringBuilder itemListForAutocomplete = new StringBuilder();
		for (int i = 0; i < itemList.size(); i++) {
			if (i != 0) {
				itemListForAutocomplete.append(",");
			}
			Item item = itemList.get(i);
			itemListForAutocomplete.append("\"");
			itemListForAutocomplete.append(item.getName());
			itemListForAutocomplete.append("\"");
		}
		return itemListForAutocomplete;
	}

}
