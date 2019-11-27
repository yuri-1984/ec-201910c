package com.example.form;

import java.util.Arrays;

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
	private Integer[]toppingId;
	
	public Integer getItemid() {
		return itemId;
	}
	public void setItemid(Integer itemid) {
		itemId = itemid;
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
	public Integer[] getToppingId() {
		return toppingId;
	}
	public void setToppingId(Integer[] toppingId) {
		this.toppingId = toppingId;
	}
	@Override
	public String toString() {
		return "OrderItemForm [itemId=" + itemId + ", orderId=" + orderId + ", quantity=" + quantity
				+ ", size=" + size + ", toppingId=" + Arrays.toString(toppingId) + "]";
	}
}
