package com.example.controller;

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
import com.example.domain.ReceiveCredit;
import com.example.form.OrderForm;
import com.example.service.CheckService;
import com.example.service.OrderService;
import com.example.service.ShoppingCartService;

/**
 * 注文を受け付けて注文完了処理を行うコントローラクラス.
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
	 * @return 空のOrderFormオブジェクト
	 */
	@ModelAttribute
	public OrderForm setUpOrderForm() {
		return new OrderForm();
	}

	/**
	 * 注文確認画面を表示するメソッド.
	 * @return 注文確認画面
	 */
	@RequestMapping("/showOrderConfirm")
	public String showOrderConfirm(@AuthenticationPrincipal LoginUser loginUser, Model model) {
		//カートの中身を表示する
		int userId = loginUser.getUser().getId();
		Order order = shoppingCartService.showCartList(userId);
		model.addAttribute("order", order);
		return "order_confirm";
	}
	
	/**
	 * 注文完了画面に遷移するメソッド.
	 * @return
	 */
	@RequestMapping("/finished")
	public String finished() {
		return "order_finished.html";
	}

	/**
	 * 注文処理を行うメソッド.
	 * @param form リクエストパラメータ
	 * @return 注文完了画面.
	 */
	@RequestMapping("/order")
	public String order(
			@Validated 
			OrderForm form,
			@AuthenticationPrincipal LoginUser loginUser,
			BindingResult result,
			Model model) {
		System.err.println("OrderControllerの中身"+form);
		if (result.hasErrors()) {
			System.err.println("バリデーションエラー出すよ");
			System.err.println(result);
			return showOrderConfirm(loginUser,model);
		} 
		if (Integer.parseInt(form.getPaymentMethod())==2) {
			Credit credit = new Credit();
//			credit.setUserId(Integer.parseInt(form.getUserId()));
//			credit.setOrderNumber(Integer.parseInt(form.getOrderNumber()));
//			credit.setOrderAmount(Integer.parseInt(form.getOrderAmount()));
			credit.setCardNumber(Integer.parseInt(form.getCardNumber()));
			credit.setCardExpYear(Integer.parseInt(form.getCardExpYear()));
			credit.setCardExpMonth(Integer.parseInt(form.getCardExpMonth()));
			credit.setCardName(form.getCardName());
			credit.setCardCvv(Integer.parseInt(form.getCardCvv()));

			ReceiveCredit receiveCredit = new ReceiveCredit();
			Order order = orderService.registerOrder(form);
			System.out.println("orderControllerの中身"+order);
			model.addAttribute("order", order);
			checkService.service(credit, receiveCredit);
			
			// クレジットカード情報がtrueなら注文完了画面にリダイレクト.
			if (checkService.service(credit, receiveCredit) == true) {
				return "redirect:/finished";
				
			// falseだったらエラーを追加して注文確認画面に戻る
			} else if (checkService.service(credit, receiveCredit) == false) {
				result.rejectValue("error", null, "クレジットカード情報が不正です");
				return showOrderConfirm(loginUser,model);
			}
		}
		Order order = orderService.registerOrder(form);
		model.addAttribute("order", order);
		return "redirect:/finished";
	}
}
