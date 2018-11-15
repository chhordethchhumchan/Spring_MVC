$(function() {
	
	
	  $('button[type=submit]').click(function(e) {
		   e.preventDefault();
		   		var user = {
		           id: $("#id").val(),
		           name:$("#name").val(),
		        
		       }
			   var data = JSON.stringify(user);
			   $.ajax({
			          data: data,
			          type: "post",
			          url: "/spring-mvc-mcnc/api/user/save",
			          async: false,
			          contentType: "application/json",
			          dataType: "json",
			          success: function(data){
			               alert("Data Save: ");
			          },
			         
			 });
		   
	 }); 
	    
   
});
 

   


