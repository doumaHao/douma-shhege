$(function(){
	var href = window.location.href;
	if(href.indexOf("product")>0){
		$(".navbar-nav").find("li").removeClass("active");
		$(".navbar-nav").find("li").eq(2).addClass("active");
	} else if(href.indexOf("sample")>0){
		$(".navbar-nav").find("li").removeClass("active");
		$(".navbar-nav").find("li").eq(3).addClass("active");
	} else if(href.indexOf("invite")>0){
		$(".navbar-nav").find("li").removeClass("active");
		$(".navbar-nav").find("li").eq(4).addClass("active");
	} else if(href.indexOf("contact")>0){
		$(".navbar-nav").find("li").removeClass("active");
		$(".navbar-nav").find("li").eq(5).addClass("active");
	}
	
});