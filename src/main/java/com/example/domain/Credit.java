package com.example.domain;

public class Credit {
	
	private Integer userId;
	private Integer orderNumber;
	private Integer orderAmount;
	private Integer cardNumber;
	private Integer cardExpYear;
	private Integer cardExpMonth;
	private String cardName;
	private Integer cardCvv;


public Integer getUserId() {
	return userId;
}
public void setUserId(Integer userId) {
	this.userId = userId;
}
public Integer getOrderNumber() {
	return orderNumber;
}
public void setOrderNumber(Integer orderNumber) {
	this.orderNumber = orderNumber;
}
public Integer getOrderAmount() {
	return orderAmount;
}
public void setOrderAmount(Integer orderAmount) {
	this.orderAmount = orderAmount;
}
public Integer getCardNumber() {
	return cardNumber;
}
public void setCardNumber(Integer cardNumber) {
	this.cardNumber = cardNumber;
}
public Integer getCardExpYear() {
	return cardExpYear;
}
public void setCardExpYear(Integer cardExpYear) {
	this.cardExpYear = cardExpYear;
}
public Integer getCardExpMonth() {
	return cardExpMonth;
}
public void setCardExpMonth(Integer cardExpMonth) {
	this.cardExpMonth = cardExpMonth;
}
public String getCardName() {
	return cardName;
}
public void setCardName(String cardName) {
	this.cardName = cardName;
}
public Integer getCardCvv() {
	return cardCvv;
}
public void setCardCvv(Integer cardCvv) {
	this.cardCvv = cardCvv;
}
}