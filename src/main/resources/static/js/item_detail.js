$(function(){
	var total_price = 0;
//	$("#total_price").text(total_price);
	
	$('input:checkbox').change(function() {
        var topping_count = $('.col-sm-12 input:checkbox:checked').length;
        var item_priceM
        $("#total_price").text(topping_count * 200);
    }).trigger('change');
	
	
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
