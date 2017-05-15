(function ($){

    $.fn.extend({
    	
		//值改变
		"valueChange": function(func){
			var thiz = $(this);
			var value = $(this).val();
			setInterval(function(){
				var value2 = thiz.val();
				if(value != value2) {
					value = value2;
					if(!!func) func();
				}
			}, 500);
			
		}
    
    });
})(jQuery);