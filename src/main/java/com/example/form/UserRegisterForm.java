package com.example.form;

import javax.validation.constraints.NotEmpty;

/**
 * ユーザー登録のリクエストパラメータを受け取るフォームクラス.
 * @author yuichi
 *
 */
public class UserRegisterForm {
	/**	ID */
	@NotEmpty(message="*入力は必須です")
	private Integer id;
	/**	氏名 */
	@NotEmpty(message="*入力は必須です")
	private String name;
	/**	メールアドレス */
	@NotEmpty(message="*入力は必須です")
	private String email;
	/**	パスワード */
	@NotEmpty(message="*入力は必須です")
	private String password;
	/**	郵便番号 */
	@NotEmpty(message="*入力は必須です")
	private String zipcode;
	/**	住所 */
	@NotEmpty(message="*入力は必須です")
	private String address;
	/**	電話番号 */
	@NotEmpty(message="*入力は必須です")
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
		return "UserRegisterForm [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password
				+ ", zipcode=" + zipcode + ", address=" + address + ", telephone=" + telephone + "]";
	}
	
	
	
}
