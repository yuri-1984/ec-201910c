package com.example.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * order_confirm.htmlのお届け先情報を受け取るフォームクラス.
 * @author yuichi
 *
 */
public class OrderForm {
	/**	オーダーID */
	private String id;
	/**	ユーザーID */
	private Integer userId;
	/**	注文日 */
	private String orderDate;
	/**	合計金額 */
	private String totalPrice;
	/**	注文者名 */
	@NotBlank(message="*入力は必須です")
	private String destinationName;
	/**	メールアドレス */
	@NotBlank(message="*入力は必須です")
	@Email(message="*メールアドレスの形式が不正です")
	private String destinationEmail;
	/**	郵便番号 */
	@NotBlank(message="*入力は必須です")
	@Pattern(regexp = "^\\d{3}\\-?\\d{4}$",message="*郵便番号の形式が不正です")
	private String destinationZipcode;
	/**	住所 */
	@NotBlank(message="*入力は必須です")
	private String destinationAddress;
	/**	電話番号 */
	@Pattern(regexp = "^[0-9]*$",message="*半角数字で入力してください")
	@NotBlank(message="*入力は必須です")
	private String destinationTel;
	/**	配達日 */
	@NotBlank(message="配達日時を入力してください")
	private String deliveryDate;
	/**	配達時間 */
	private String deliveryTime;
	/**	決済方法 */
	private String paymentMethod;
	/**	クレジットカード番号 */
	@Pattern(regexp="(?:4[0-9]{12}(?:[0-9]{3})?|5[1-5][0-9]{14}|6011[0-9]{12}|3(?:0[0-5]|[68][0-9])[0-9]{11}|3[47]{13}|(?:2131|1800|35[0-9]{3})[0-9]{11})",
			message="クレジットカード番号の形式が不正です")
	private String cardNumber;
	/**	有効期限年 */
	private String cardExpYear;
	/**	有効期限月 */
	private String cardExpMonth;
	/**	カード名義 */
	private String cardName;
	/**	セキュリティコード */
	private String cardCvv;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
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
	public String getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public String getDeliveryTime() {
		return deliveryTime;
	}
	public void setDeliveryTime(String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getCardExpYear() {
		return cardExpYear;
	}
	public void setCardExpYear(String cardExpYear) {
		this.cardExpYear = cardExpYear;
	}
	public String getCardExpMonth() {
		return cardExpMonth;
	}
	public void setCardExpMonth(String cardExpMonth) {
		this.cardExpMonth = cardExpMonth;
	}
	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	public String getCardCvv() {
		return cardCvv;
	}
	public void setCardCvv(String cardCvv) {
		this.cardCvv = cardCvv;
	}
	@Override
	public String toString() {
		return "OrderForm [id=" + id + ", totalPrice=" + totalPrice
				+ ", orderDate=" + orderDate + ", destinationName=" + destinationName + ", destinationEmail="
				+ destinationEmail + ", destinationZipcode=" + destinationZipcode + ", destinationAddress="
				+ destinationAddress + ", destinationTel=" + destinationTel + ", deliveryTime=" + deliveryTime
				+ ", paymentMethod=" + paymentMethod
				+ ", cardNumber=" + cardNumber
				+ ", cardExpYear=" + cardExpYear + ", cardExpMonth=" + cardExpMonth + ", cardName=" + cardName
				+ ", cardCvv=" + cardCvv + "]";
	}
}
