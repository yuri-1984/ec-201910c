package com.example.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Credit;
import com.example.domain.LoginUser;
import com.example.domain.Order;
import com.example.form.OrderForm;
import com.example.service.CheckService;
import com.example.service.OrderService;
import com.example.service.ShoppingCartService;

/**
 * 注文を受け付けて注文完了処理を行うコントローラクラス.
 * 
 * @author yuichi
 */
@Controller
public class OrderController {
	@Autowired
	private ShoppingCartService shoppingCartService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private CheckService checkService;

	/**
	 * エラーチェック用.
	 * 
	 * @return 空のOrderFormオブジェクト
	 */
	@ModelAttribute
	public OrderForm setUpOrderForm() {
		return new OrderForm();
	}

	/**
	 * 注文確認画面を表示するメソッド.
	 * 
	 * @return 注文確認画面
	 */
	@RequestMapping("/showOrderConfirm")
	public String showOrderConfirm(OrderForm form,@AuthenticationPrincipal LoginUser loginUser, Model model) {
		// カートの中身を表示する
		int userId = loginUser.getUser().getId();
		Order order = shoppingCartService.showCartList(userId);
		form.setDestinationName(loginUser.getUser().getName());
		form.setDestinationEmail(loginUser.getUser().getEmail());
		form.setDestinationZipcode(loginUser.getUser().getZipcode());
		form.setDestinationAddress(loginUser.getUser().getAddress());
		form.setDestinationTel(loginUser.getUser().getTelephone());
		
		
		model.addAttribute("orderForm", form);

		model.addAttribute("order", order);
		return "order_confirm";
	}

	/**
	 * 注文完了画面に遷移するメソッド.
	 * 
	 * @return
	 */
	@RequestMapping("/finished")
	public String finished() {
		return "order_finished.html";
	}

	/**
	 * 注文処理を行うメソッド.
	 * 
	 * @param form リクエストパラメータ
	 * @return 注文完了画面.
	 */
	@RequestMapping("/order")
	public String order(@Validated OrderForm form, BindingResult result, @AuthenticationPrincipal LoginUser loginUser,Model model) {
		System.err.println("OrderControllerの中身" + form);
		if (form.getPaymentMethod().equals("2")) {

			if (form.getCardNumber().equals("")) {
				result.rejectValue("cardNumber", null, "クレジットカード番号の形式が不正です");
			} else {
				Pattern pattern = Pattern.compile("[0-9]{16}");
				Matcher matcher = pattern.matcher(form.getCardNumber());
				boolean matchResult = matcher.matches();

				if (matchResult == false) {
					result.rejectValue("cardNumber", null, "クレジットカード番号の形式が不正です");
				}
			}

			if (form.getCardName().equals("")) {
				result.rejectValue("cardName", null, "形式が不正です");
			} else {
				System.out.println("cardName" + form.getCardName());
				Pattern pattern1 = Pattern.compile("^[A-Z]*$");
				Matcher matcher1 = pattern1.matcher(form.getCardName());
				boolean matchResult1 = matcher1.matches();

				if (matchResult1 == false) {
					result.rejectValue("cardName", null, "形式が不正です");
				}
			}

			if (form.getCardCvv().equals("")) {
				result.rejectValue("cardCvv", null, "形式が不正です");
			} else {
				Pattern pattern2 = Pattern.compile("[0-9]{3}");
				Matcher matcher2 = pattern2.matcher(form.getCardCvv());
				boolean matchResult2 = matcher2.matches();

				if (matchResult2 == false) {
					result.rejectValue("cardCvv", null, "形式が不正です");
				}
			}

			System.err.println("error:::::::" + result);
			if (result.hasErrors()) {
				System.err.println("バリデーションエラー出すよ");
				System.err.println(result);
				return showOrderConfirm(form,loginUser, model);
			}
			if (form.getPaymentMethod().equals(2)) {
				Credit credit = new Credit();
//			credit.setUserId(Integer.parseInt(form.getUserId()));
//			credit.setOrderNumber(Integer.parseInt(form.getOrderNumber()));
//			credit.setOrderAmount(Integer.parseInt(form.getOrderAmount()));
				credit.setCardNumber(form.getCardNumber());
				credit.setCardExpYear(form.getCardExpYear());
				credit.setCardExpMonth(form.getCardExpMonth());
				credit.setCardName(form.getCardName());
				credit.setCardCvv(form.getCardCvv());

				System.out.println("aaa:" + form.getCardNumber());

//		if (result.hasErrors()) {
//			System.err.println("バリデーションエラー出すよ");
//			System.err.println(result);
//			return showOrderConfirm(loginUser, model);
//		}
//		
//		if (Integer.parseInt(form.getPaymentMethod()) == 2) {
//			Credit credit = new Credit();
////			credit.setUserId(Integer.parseInt(form.getUserId()));
////			credit.setOrderNumber(Integer.parseInt(form.getOrderNumber()));
////			credit.setOrderAmount(Integer.parseInt(form.getOrderAmount()));
//			credit.setCardNumber(form.getCardNumber());
//			credit.setCardExpYear(form.getCardExpYear());
//			credit.setCardExpMonth(form.getCardExpMonth());
//			credit.setCardName(form.getCardName());
//			credit.setCardCvv(form.getCardCvv());
//
//			ReceiveCredit receiveCredit = new ReceiveCredit();
//			Order order = orderService.registerOrder(form);
//			System.out.println("orderControllerの中身" + order);
//			model.addAttribute("order", order);
//			checkService.service(credit, receiveCredit);
//
				// クレジットカード情報がtrueなら注文完了画面にリダイレクト.
				if (checkService.service(credit) == true) {
					return "redirect:/finished";

					// falseだったらエラーを追加して注文確認画面に戻る
				} else if (checkService.service(credit) == false) {
					result.rejectValue("error", null, "クレジットカード情報が不正です");
					return showOrderConfirm(form,loginUser, model);
				}
			}
		}
		Order order = orderService.registerOrder(form);
		model.addAttribute("order", order);
		return "redirect:/finished";

	}
}
