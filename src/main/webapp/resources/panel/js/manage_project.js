function insertProject(){
	$.ajax({
		type : "GET", 
		url  : "insertProject",
		data : {
			name : $("#projectName").val(),
			desc : $("#desc").val(),
		},
		success : function(data){
			$("#projectList").empty();
			for (var i = 0; i < data.length; i++) { 
				var projectStatus = "";
				if(data[i].status==0)
					projectStatus = "<input type='checkbox' name='projectStatus' id='projectStatus_"+data[i].projectId+"' onclick='updateProjectStatus('"+data[i].projectId+"')'>";	
				else	
					projectStatus = "<input type='checkbox' name='projectStatus' id='projectStatus_"+data[i].projectId+"' onclick='updateProjectStatus('"+data[i].projectId+"')' checked='checked'>";
				
				 
				$("#projectList").append("<tr> <td width='3%'>"+ (i+1) +"</td><td width='17%'>"+data[i].name+"</td><td width='65%'>"+data[i].desc+"</td><td width='15%' style='text-align: center;'>"+projectStatus+"</td> </tr>"); 
			} 
			$("#login-alert").show(); 
			$("#login-alert").html("Project added successfully.");	
			hideMessage();
			$('#myModal').modal('hide');
			window.location = "manageProject";

		},
		error : function(e){
			console.log("Error insertProject -->"+e);
		}
	});
}

function updateProjectStatus(projectId){
	var projectStatus = "0";
	if($("#projectStatus_"+projectId).is(":checked")){
			projectStatus = "1";	
	}else{
		    projectStatus = "0";	
	}
	$.ajax({
		type : "GET",  
		url  : "updateProjectStatus",
		data : { 
			status : projectStatus,
			projectId : projectId,
		},
		success : function(data){
			if(data){
				$("#login-alert").show(); 
				$("#login-alert").html("Status changed successfully.");	
				hideMessage(); 
			}
		},
		error : function(e){
			console.log("Error insertProject -->"+e);
		}
	});
}

function hideMessage(){ 
	setTimeout(function(){ $("#login-alert").hide(); }, 3000);
}