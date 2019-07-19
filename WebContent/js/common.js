


function checkInputEmpty($objs){
	var count=0;
	
	$objs.each(function(i,obj){
		if($(obj).val()==""){
			var $next = $(obj).next();
			$next.css("color","red")
			count++;
		}
	})
	if(count>0)
		return false; //빈칸 존재
	return true; //빈칸존재 x
}