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
                       title:'<input type="checkbox"/>',
                      "data": null,
                      "searchable": false,
                      "sortable": false,
                      "orderable": false,
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
          
   });
   $('#dataTable').on('click','a.edit-user', function (e) {
         e.preventDefault();
       var id = $(e.target).text();
       console.log(id);			 
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
   
	
	  $('#save').click(function(e) {
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
			        	  alert("Data insert succes!")
			        	  location.reload();
			          },
			         
			 });
		   
			   
		  
	 }); 
	   
    


   


