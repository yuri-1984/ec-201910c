package com.example.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * ユーザー登録のリクエストパラメータを受け取るフォームクラス.
 * @author yuichi
 *
 */
public class RegisterUserForm {
	/**	ID */
	private Integer id;
	/**	氏名 */
	@NotBlank(message="*入力は必須です")
	private String name;
	/**	メールアドレス */
	@NotBlank(message="*入力は必須です")
	@Email(message="*メールアドレスの形式が不正です")
	private String email;
	/**	パスワード */
	@NotBlank(message="*入力は必須です")
	private String password;
	@NotBlank(message="*入力は必須です")
	private String confirmationPassword;	
	/**	郵便番号 */
	@Pattern(regexp = "^\\d{3}\\-?\\d{4}$",message="*半角数字7桁で入力してください")
	private String zipcode;
	/**	住所 */
	@NotBlank(message="*入力は必須です")
	private String address;
	/**	電話番号 */
	@Pattern(regexp = "^[0-9]*$",message="*半角数字で入力してください")
	private String telephone;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmationPassword() {
		return confirmationPassword;
	}
	public void setConfirmationPassword(String confirmationPassword) {
		this.confirmationPassword = confirmationPassword;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	@Override
	public String toString() {
		return "RegisterUserForm [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password
				+ ", confirmationPassword=" + confirmationPassword + ", zipcode=" + zipcode + ", address=" + address
				+ ", telephone=" + telephone + "]";
	}
	
}
