var ajax = function(url, data, successFunc, errFunc, beforeSendFunc, completeFunc){
	$.ajax({
	    url:url,
	    type:'POST', //GET
	    async:true,    //或false,是否异步
	    data:data,
	    timeout:5000,    //超时时间
	    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
	    beforeSend:function(xhr){
	        if(!!beforeSendFunc) beforeSendFunc();
	    },
	    success:function(data,textStatus,jqXHR){
	        if(!!successFunc) successFunc(data);
	    },
	    error:function(xhr,textStatus){
	        if(!!errFunc) errFunc();
	    },
	    complete:function(){
	        if(!!completeFunc) completeFunc();
	    }
	});
};


$(function(){
	//左侧蔡菜单栏选中状态
	var liid = $("#asideLi");
	$("li[liid='"+liid.val()+"']").addClass("active");
	if($("li[liid='"+liid.val()+"']").attr("lv") === "1"){
		//do nothing
	} else if($("li[liid='"+liid.val()+"']").attr("lv") === "2") {
		$("li[liid='"+liid.val()+"']").parents("[lv='1']").addClass("active");
	}
});