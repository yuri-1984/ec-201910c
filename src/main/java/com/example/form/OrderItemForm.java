package com.example.form;

import java.util.List;

/**
 * item_detail.htmlから注文された商品の
 * リクエストパラメータを受け取るフォームクラス.
 * @author yuichi
 */
public class OrderItemForm {
	/** 商品ID */
	private Integer itemId;
	/** 注文ID */
	private Integer orderId;				//どこからとってくる??
	/** 数量 */
	private Integer quantity;
	/** サイズ */
	private Character size;
	/** トッピングリスト */
	private List<Integer>toppingList;
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Character getSize() {
		return size;
	}
	public void setSize(Character size) {
		this.size = size;
	}
	public List<Integer> getToppingList() {
		return toppingList;
	}
	public void setToppingList(List<Integer> toppingList) {
		this.toppingList = toppingList;
	}
	@Override
	public String toString() {
		return "OrderItemForm [itemId=" + itemId + ", orderId=" + orderId + ", quantity=" + quantity + ", size=" + size
				+ ", toppingList=" + toppingList + "]";
	}
	

}
	
	