package com.example.form;

import java.util.List;

import com.example.domain.Item;

public class OrderItemForm {
	
	/**	注文された商品のID */
	private Integer id;
	/**	商品ID */
	private Integer itemId;
	/**	注文ID */
	private Integer orderId;
	/**	数量 */
	private Integer quantity;
	/**	サイズ */
	private Character size;
	/**	商品 */
	private Item item;
	/**	トッピングリスト */
	private List<Integer> toppingList;



	

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


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


	public Item getItem() {
		return item;
	}


	public void setItem(Item item) {
		this.item = item;
	}




	public List<Integer> getToppingList() {
		return toppingList;
	}


	public void setToppingList(List<Integer> toppingList) {
		this.toppingList = toppingList;
	}


	@Override
	public String toString() {
		return "OrderItemForm [id=" + id + ", itemId=" + itemId + ", orderId=" + orderId + ", quantity=" + quantity
				+ ", size=" + size + ", item=" + item + ", toppingList=" + toppingList + "]";
	}
	
}
