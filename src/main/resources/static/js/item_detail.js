$(function(){
	alert("jQuery動作してます")

//	//サイズ情報
//	var size = $("input[name='size']:checked").val();	
//	//Mサイズ商品の価格
//	var price_M = $('#price_M').text();
//	//Lサイズ商品の価格
//	var price_L = $('#price_L').text();
//	//トッピング数
//	var check_count = $("input[type='checkbox']:checked").length;
//	//数量
//	var quantity = $("#quantity").val();
//	//合計金額
//	var total_price = 0;
//	$("#total_price").text(total_price);
	
	//サイズ情報
	var size = $("input[name='size']:checked").val();
	console.log("サイズ情報：" + size);
	//Mサイズ商品の価格
	var price_M = +$('#price_M').text();
	console.log("Mサイズ商品の価格：" + price_M);
	//Lサイズ商品の価格
	var price_L = +$('#price_L').text();
	console.log("Lサイズ商品の価格：" + price_L);
	//トッピング数
	var check_count = +$("input[type='checkbox']:checked").length;
	console.log("トッピング数：" + check_count);
	//数量
	var quantity = +$("#quantity").val();
	console.log("数量：" + quantity);
	//合計金額
	var total_price = ( price_M + (check_count * 200)) * quantity;
	$("#total_price").text(total_price);		
	
//	if(size == 'M'){
//		//サイズ情報
//		var size = $("input[name='size']:checked").val();
//		//Mサイズ商品の価格
//		var price_M = $('#price_M').text();
//		//Lサイズ商品の価格
//		var price_L = $('#price_L').text();
//		//トッピング数
//		var check_count = $("input[type='checkbox']:checked").length;
//		//数量
//		var quantity = $("#quantity").val();
//		//合計金額
//		var total_price = 0;
//		total_price = ( price_M + (check_count * 200)) * quantity;
//		$("#total_price").text(total_price);		
//	}
//	if(size == 'L'){
//		
//	}
	
	
	//トッピングでの値段変化(動作確認済)
//	$('input:checkbox').change(function() {
//		var topping_count = $('.col-sm-12 input:checkbox:checked').length;        
//        $("#total_price").text(topping_count * 200);
//    }).trigger('change');
	
	//サイズでの値段変化
//	$('input:radio').change(function() {
//		var size_price = $(".item_price").val();        
//		$("#total_price").text(size_price);
//	}).trigger('change');
	

	
	
	
	
	
	
	//	var total_price = parseInt($("input[name='responsibleCompany']:radio").val());;
//	$("#total_price").text(total_price);
//		$("input[name='responsibleCompany']:radio").change(function(){
//			total_price = parseInt($(this).val());			
//			$("#total_price").text(total_price);
//		});
//		
//		$("input[type='checkbox']").change(function(){
//			var check_count = $("input[type='checkbox']:checked").length;
//			alert(check_count * 200);
//		})
//	$function calc_price(){
//	};

});
