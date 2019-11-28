/**
 * 
 * 
 */

$(function(){
	
	$("#card").on("change",function(){
		if($("#card").val == 1){
			$("#creditcard").hide();
			
		}else if($("#card").val== 2){
			$("#creditcard").show();
		}
		
	});
	
)};