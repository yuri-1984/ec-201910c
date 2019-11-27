package com.example.form;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;

import com.example.domain.OrderItem;
import com.example.domain.User;

public class OrderForm {
	
	private Integer id;
	private Integer userId;
	private Integer status;
	private Integer totalPrice;
	private Date orderDate;
	@NotBlank(message="お名前を入力して下さい")
	private String destinationName;
	@NotBlank(message="メールアドレスを入力して下さい")
	private String destinationEmail;
	private String destinationZipcode;
	@NotBlank(message="住所を入力して下さい")
	private String destinationAddress;
	@NotBlank(message="電話番号を入力してください")
	private String destinationTel;
	@NotBlank(message="配達日時を入力してください")
	private Timestamp deliveryTime;
	private Integer paymentMethod;
	private User user;
	private List<OrderItem>orderItemList;
	
	public int getTax() {
		int tax = (int) (totalPrice*0.1);
		return tax;
	}
	
	public int getCalcTotalPrice() {
		return (int) (totalPrice+(totalPrice*0.1));
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getDestinationName() {
		return destinationName;
	}

	public void setDestinationName(String destinationName) {
		this.destinationName = destinationName;
	}

	public String getDestinationEmail() {
		return destinationEmail;
	}

	public void setDestinationEmail(String destinationEmail) {
		this.destinationEmail = destinationEmail;
	}

	public String getDestinationZipcode() {
		return destinationZipcode;
	}

	public void setDestinationZipcode(String destinationZipcode) {
		this.destinationZipcode = destinationZipcode;
	}

	public String getDestinationAddress() {
		return destinationAddress;
	}

	public void setDestinationAddress(String destinationAddress) {
		this.destinationAddress = destinationAddress;
	}

	public String getDestinationTel() {
		return destinationTel;
	}

	public void setDestinationTel(String destinationTel) {
		this.destinationTel = destinationTel;
	}

	public Timestamp getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(Timestamp deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public Integer getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(Integer paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List getOrderItemList() {
		return orderItemList;
	}

	public void setOrderItemList(List orderItemList) {
		this.orderItemList = orderItemList;
	}

}
