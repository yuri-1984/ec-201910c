package com.example.form;

import java.util.List;

import com.example.domain.Item;

public class OrderItemForm {
	private Integer id;
	private Integer Itemid;
	private Integer orderId;
	private Integer quantity;
	private Character size;
	private Item item;
	private List orderTopingList;
	
	
	
	
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
	public List getOrderTopingList() {
		return orderTopingList;
	}
	public void setOrderTopingList(List orderTopingList) {
		this.orderTopingList = orderTopingList;
	}
	@Override
	public String toString() {
		return "OrderItemForm [id=" + id + ", Itemid=" + Itemid + ", orderId=" + orderId + ", quantity=" + quantity
				+ ", size=" + size + ", item=" + item + ", orderTopingList=" + orderTopingList + "]";
	}


	}
	


