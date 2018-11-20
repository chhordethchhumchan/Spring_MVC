 $(document).ready(function() {
    	    var row =[];
    	    $('#dataTable').DataTable( {
    	       
    	             ajax:{
    	                   url : "/spring-mvc-mcnc/api/user/userlist",
    	                   type : "get",
    	                   dataType: 'json',
    	                   contentType: "application/json",
    	                   dataSrc:'',
    	                   dataType: "json",
    	                   error: function(data){
    	                       alert();
    	                   },
    	                  
    	             
    	              },
    	               columns: [
    	                   
    	                   {
    	                       title:'<input type="checkbox" id="checkAll"/>',
    	                      "data": null,
    	                      "searchable": false,
    	                      "sortable": false,
    	                      "order": false,
    	                      "class": "dt-body-center",
    	                      "render": function (data, type, row) {
    	                          return "<input name='m-chk-row' data-toggle='modal' data-target='#userEdit' class='activate' value='" + row.id+ "' type='checkbox'/>";
    	                      },
    	                  },
    	                  { 
    	                      title:"ID",
    	                      "data": "id",
    	                      "render": function (data, type, row) {
    	                          data = '<a class="edit-user" data-toggle="modal" data-target="#editUser" attr=' + row.id + ' ><u>' + data + '</u></a>';
    	                           return data;;
    	                      },
    	                   },
    	                  { 
    	                       title:"Name",
    	                       "data": "name"
    	                       
    	                  },
    	                 
    	                ]
    	              
    	               });
    	          
 			/*$("#btnDelete").click(function(e){
 				var users =[];
 				 e.preventDefault();
 				 $("input[name=m-chk-row]:checked").each(function() {
 					
 					 users.push($(this).val());
 				
 				 });
 				 var id =$("input[name=m-chk-row]:checked").val();
 				 console.log(users);
 				if (id.length > 0) {
  			   		
  				   $.ajax({     
  				          type: "post",
  				          url: "/spring-mvc-mcnc/api/user/delete?id="+id+"",
  				          async: false,
  				          contentType: "application/json",
  				          dataType: "json",
  				          success: function(data){
  				        	  alert("Data deleted!")
  				        	  location.reload();
  				          },
  				         
  				 });
 				}
 				
 				
 			});*/
    	    $("#btnDelete").click(function(e){
 				var users =[];
 				 e.preventDefault();
 				 $("input[name=m-chk-row]:checked").each(function() {
 					
 					 users.push($(this).val());
 				
 				 });
 				 console.log(users);
 				if (id.length > 0) {
  			   		
  				   $.ajax({     
  				          type: "POST",
  				          data	: JSON.stringify(users),
  				          url: "/spring-mvc-mcnc/api/user/delete_lists",
  				          async: false,
  				          contentType: "application/json",
  				          dataType: "json",
  				          success: function(data){
  				        	  alert("Data deleted!")
  				        	  location.reload();
  				        	 
  				          },
  				         
  				 });
 				}
 				
 				
 			});
 			$("#dataTable").on('click','#checkAll', function(e){
 				
 				$("input[name=m-chk-row]").not(this).prop('checked', this.checked);
 				
			});
 			
    		$('#updateUser').click(function(e) {
    		
    		    e.preventDefault();
    		             var user = {
    		             id: $("#editUser #id").val(),
    		             name:$("#editUser #name").val(),
    		          
    		         }
    		         var id =  $("#editUser #id").val();
    		         var data = JSON.stringify(user);
    		         $.ajax({
    		                data: data,
    		                type: "post",
    		                url: "/spring-mvc-mcnc/api/user/user/"+id+"",
    		                async: false,
    		                contentType: "application/json",
    		                dataType: "json",
    		                success: function(data){
    		                     alert("Update Success");                          
    		               	 location.reload();
    		                       
    		              }
    		       });
    		});
    	   $('#dataTable').on('click','a.edit-user', function (e) {
    	        e.preventDefault();
    	       
    	       var id = $(e.target).text();
    	              $.ajax({
    	                    
    	                     type: "get",
    	                     url: "/spring-mvc-mcnc/api/user/getUserByID?id="+id+"",
    	                     async: false,
    	                     contentType: "application/json",
    	                     dataType: "json",
    	                     success: function(data){
    	                       $('#editUser #id').val(data.id); 	
    	                       $('#editUser #name').val(data.name); 			 
    	                       
    	                   }
    	            });
    	           
    	              
    	    }); 
    	   
    		
    		
    				   
    			  
     }); 
    		   
    	    


    	   