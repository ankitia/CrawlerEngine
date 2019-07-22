

function downloadFileFromServer(dataSetId){
	window.open(enrichURL+"/get_nlp_file?data_set_id="+dataSetId, '_blank'); 
} 
  
function downloadFileWebScrap(dataSetId){
	window.open(enrichURL+"/get_meta_file?data_set_id="+dataSetId, '_blank'); 
}

function webScraping(dataSetId,scrapCount){
	
	$.ajax({ 
		type : "GET",
		datatype: "json",
		/*async: false,   */    
		url : enrichURL+"/get_meta", 		 
		data : {
			"data_set_id" : dataSetId,
		},
		success : function(data){
			 
			 
		},
		error : function(e){ 
			console.log("Error webScraping:::"+e.status);
		}
		
	});
	
	updateDataSet(dataSetId,"Processing",scrapCount);
	
}

function sendRequest(dataSetId,taskId,scrapField,scrapCount){
	
	var finalUrl = enrichURL+"/get_industry";
	$.ajax({ 
		url: finalUrl,
	    type: "GET",  
	    //dataType: "text",     
	    data: {
	    		"task_id": taskId, 
	    		"scrape_field" : scrapField,
	    		"data_set_id" : dataSetId, 
	    	},
	    success: function(data){
	    //    alert(data);
	    },
	    error: function(error){
	         console.log("Error:");
	         console.log(error);
	    }
	});
	updateNLPDataset(dataSetId);
}


function updateNLPDataset(dataSetId){
	$.ajax({ 
		url : contextPath+"/updateNLPDataset",
		type : "POST",
		data : {
			dataSetId : dataSetId,
		},
		success : function(data){ 
			
			/*alert(data);*/
			
			document.getElementById("updatenlp"+dataSetId).innerHTML = "<a href='#' class='mr-1'  title='Process Data'><font color='yellow'> <i class='fa fa-spinner'></i></font></a>";
			document.getElementById("data"+dataSetId).innerHTML = "Processing";
	 	},
		error : function(e){
			console.log("Error :::"+e);
		}
	});
}
     
function updateDataSet(dataSetId,status,scrapCount){
	$.ajax({ 
		url : contextPath+"/updateDataset",
		type : "POST",
		data : {
			dataSetId : dataSetId,
			status : status,
			scrapCount : scrapCount,
		},
		success : function(data){ 
			
			document.getElementById("update"+dataSetId).innerHTML = "<a href='#' class='mr-1'  title='Process Data'><font color='yellow'> <i class='fa fa-spinner'></i></font></a>";
			document.getElementById("data"+dataSetId).innerHTML = "Processing";
	 	},
		error : function(e){
			console.log("Error :::"+e);
		}
	});
}

/*function downloadReport(taskId,scrapField){
	$("#taskId").val(taskId);
	$("#scrapField").val(scrapField); 
	document.getElementById("downloadCSV").submit();
    
}*/

function downloadDeactiveURL(dataSetId,action){
	$("#dataSetId").val(dataSetId);
	$("#action").val(action);
	document.getElementById("downloadCSV").submit();
	
}

function downloadFinalLists(){
	document.getElementById("downloadFinalList").submit();
}