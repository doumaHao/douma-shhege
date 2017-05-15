
/**
 * 共通图片上传方法
 */
//var uploadImgUrl = "http://www.shhege.com/imgUpload.json";
//var loadImgUrl = "http://www.shhege.com/loadImgUrl.json";
//var uploadImgUrl = "http://localhost:8080/shhege/imgUpload.json";
//var loadImgUrl = "http://localhost:8080/shhege/loadImgUrl.json";
var uploadImgUrl = "http://60.205.183.218/imgUpload.json";
var loadImgUrl = "http://60.205.183.218/loadImgUrl.json";

var CommonImgUpload = function(fileInputId, showId, func){
	$.ajaxFileUpload({
		url:uploadImgUrl,//图片上传服务器端接口
		fileElementId:fileInputId,//图片域 input type=file 的对象id
		secureuri:false,//是否启用安全提交 默认false
		dataType:'json',//服务器返回的数据类型。可以为xml,script,json,html。如果不填写，jQuery会自动判断。
		success:function(data){
			//var imgUrl = data.imgUrl1+data.imgUrl2+data.imgUrl3;
			var imgUrl = data.imgUrl.split("amp;").join('');
	    	$("#"+showId).attr("src", imgUrl);
	    	$("#"+showId).attr("imgId", data.imgId);
	    	if(!!func) func();
		},
		error:function(){
			alert("上传失败");
		},
		data:{user:'haoxijun'},//自定义参数。这个东西比较有用，当有数据是与上传的图片相关的时候，这个东西就要用到了
		type: 'post'//当要提交自定义参数时，这个参数要设置成post
	});
};