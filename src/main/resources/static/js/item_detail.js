
// コードが長いため時間に余裕がある時に省略できるところは省略する

$(function() {
	 //動的な値段変更:ラジオボタン
	 $('input[name="size"]:radio').change(function() {
		 //サイズ情報
		 size = $("input[name='size']:checked").val();
		 if(size == 'M'){
			 //Mサイズ商品の価格
			 price_M = +$('#price_M').text();
			 //トッピング数
			 check_count = +$("input[type='checkbox']:checked").length;
			 //数量
			 quantity = +$("#quantity").val();
			 //合計金額
			 total_price = 0;
			 total_price = ( price_M + (check_count * 200)) * quantity;
			 $("#total_price").text(total_price);
		 }else{
			 //サイズ情報
			 size = +$("input[name='size']:checked").val();
			 //Lサイズ商品の価格
			 price_L = +$('#price_L').text();
			 //トッピング数
			 check_count = +$("input[type='checkbox']:checked").length;
			 //数量
			 quantity = +$("#quantity").val();
			 //合計金額
			 total_price = 0;
			 total_price = ( price_L + (check_count * 300)) * quantity;
			 $("#total_price").text(total_price);			
		 }	 
	 }).trigger('change');
	 
	//動的な値段変更:チェックボックス
	$("input:checkbox").change(function() {
		//サイズ情報
		size = $("input[name='size']:checked").val();
		if(size == 'M'){
			//Mサイズ商品の価格
			price_M = +$('#price_M').text();
			//トッピング数
			check_count = +$("input[type='checkbox']:checked").length;
			//数量
			quantity = +$("#quantity").val();
			//合計金額
			total_price = 0;
			total_price = ( price_M + (check_count * 200)) * quantity;
			$("#total_price").text(total_price);
		}else{
			//サイズ情報
			size = +$("input[name='size']:checked").val();
			//Lサイズ商品の価格
			price_L = +$('#price_L').text();
			//トッピング数
			check_count = +$("input[type='checkbox']:checked").length;
			//数量
			quantity = +$("#quantity").val();
			//合計金額
			total_price = 0;
			total_price = ( price_L + (check_count * 300)) * quantity;
			$("#total_price").text(total_price);			
		}
	}).trigger('change');
	
	//動的な値段変更:セレクトボックス
	$("#quantity").change(function() {
		//サイズ情報
		size = $("input[name='size']:checked").val();
		if(size == 'M'){
			//Mサイズ商品の価格
			price_M = +$('#price_M').text();
			//トッピング数
			check_count = +$("input[type='checkbox']:checked").length;
			//数量
			quantity = +$("#quantity").val();
			//合計金額
			total_price = 0;
			total_price = ( price_M + (check_count * 200)) * quantity;
			$("#total_price").text(total_price);
		}else{
			//サイズ情報
			size = +$("input[name='size']:checked").val();
			//Lサイズ商品の価格
			price_L = +$('#price_L').text();
			//トッピング数
			check_count = +$("input[type='checkbox']:checked").length;
			//数量
			quantity = +$("#quantity").val();
			//合計金額
			total_price = 0;
			total_price = ( price_L + (check_count * 300)) * quantity;
			$("#total_price").text(total_price);			
		}
	}).trigger('change');

});