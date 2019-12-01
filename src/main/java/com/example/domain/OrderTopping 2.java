package com.example.domain;

/**
 * 注文されたトッピングのドメインクラス.
 * @author yuichi
 *
 */
public class OrderTopping {

	/**	注文されたトッピングのID */
	private Integer id;
	/**	トッピングID */
	private Integer toppingId;
	/**	注文商品のID */
	private Integer orderItemId;
	/**	トッピング情報 */
	private Topping topping;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getToppingId() {
		return toppingId;
	}
	public void setToppingId(Integer toppingId) {
		this.toppingId = toppingId;
	}
	public Integer getOrderItemId() {
		return orderItemId;
	}
	public void setOrderItemId(Integer orderItemId) {
		this.orderItemId = orderItemId;
	}
	public Topping getTopping() {
		return topping;
	}
	public void setTopping(Topping topping) {
		this.topping = topping;
	}
	@Override
	public String toString() {
		return "OrderTopping [id=" + id + ", toppingId=" + toppingId + ", orderItemId=" + orderItemId + ", topping="
				+ topping + "]";
	}
}
