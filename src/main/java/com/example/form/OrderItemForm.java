package com.example.form;

import java.util.List;

import com.example.domain.Item;
import com.example.domain.Topping;

/**
 * item_detail.htmlから注文された商品の
 * リクエストパラメータを受け取るフォームクラス.
 * @author yuichi
 */
public class OrderItemForm {
	/** 注文された商品のID */
	private Integer id;
	/** 商品ID */
	private Integer Itemid;
	/** 注文ID */
	private Integer orderId;
	/** 数量 */
	private Integer quantity;
	/** サイズ */
	private Character size;
	/** 商品 */
	private Item item;
	/** トッピングリスト */
	private List<Topping> orderTopingList;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getItemid() {
		return Itemid;
	}
	public void setItemid(Integer itemid) {
		Itemid = itemid;
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
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public List<Topping> getOrderTopingList() {
		return orderTopingList;
	}
	public void setOrderTopingList(List<Topping> orderTopingList) {
		this.orderTopingList = orderTopingList;
	}
	@Override
	public String toString() {
		return "OrderItemForm [id=" + id + ", Itemid=" + Itemid + ", orderId=" + orderId + ", quantity=" + quantity
				+ ", size=" + size + ", item=" + item + ", orderTopingList=" + orderTopingList + "]";
	}
}
