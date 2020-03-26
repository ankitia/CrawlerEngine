<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Search Engine | infoAnalytica</title>
  <%@include file="include/common_css.jsp" %>
  <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  
</head>

<body class="hold-transition skin-blue fixed sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

 
  <%@include file="include/header.jsp" %>
  <!-- =============================================== -->
  <%@include file="include/sidebar.jsp" %>
  <!-- =============================================== -->

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>Search Engine</h1>  
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li class="active">Search Engine</li>
      </ol> 
    </section>

    <!-- Main content -->
    <section class="content">
    <div class="row">
        <!-- left column -->
        <div class="col-md-12">
		      <div class="box box-primary">
		            <div class="box-header with-border">
		              <h3 class="box-title">Search Engine</h3>
		            </div>
		            <!-- /.box-header -->
		            <!-- form start --> 
		            <form role="form" action="insertSearchEngine"  method="post" enctype="multipart/form-data"> 
		              <div class="box-body">
		               <div class="form-group">
		                  <label>Project</label>
		                  <select name="projectId" class="form-control" onchange="getDataSet(this)" style="width: 300px;">
		                        <option value="">Select Project</option>  
		                    <c:forEach items="${projectList }" var="projectList"> 
		                    	<option value="${projectList.projectId }">${projectList.name }</option>
		                    </c:forEach>
		                  </select>
		                </div>  
		                <div class="form-group">
		                  <label for="exampleInputEmail1">Dataset</label>
		                  <select name="dataSetId" class="form-control" id="datasetId2" style="width: 300px;">
		                        <option value="">Select Dataset</option>   
		                  </select>
		                </div>
		                <div class="form-group">
		                  <label for="exampleInputEmail1">Name</label> 
		                  <input type="text" class="form-control" id="name"  name="name" placeholder="Name" required style="width: 300px;">
		                </div> 		                 
		                <div class="form-group">
		                  <label for="exampleInputFile">File input</label> 
		                  <input type="file" id="exampleInputFile" name="exampleInputFile">
		                  <p class="help-block">Upload search Keywords csv file.</p>
		                </div>		                
		              </div>
		              <!-- /.box-body -->
		
		              <div class="box-footer">
		                <button type="submit"  class="btn btn-primary" name="Submit">Submit</button> 
		                <a href="manageDataset" class="btn btn-danger">Back</a>  
		              </div>
		            </form> 
		          </div>
		    </div>
		  </div>  
      <!-- /.box -->

    </section>
    <!-- /.content -->
  </div>
  
  
  <!-- /.content-wrapper -->
   <%@include file="include/footer.jsp" %>
</div>
	<%@include file="include/common_js.jsp" %>
	
 <script type="text/javascript">

function getDataSet(proectId){
 	
	$.ajax({
		url : "getDatasetProjectList",
		type : "POST",
		data :{
			projectId : proectId.value
		},
		success : function(data){
			$('#datasetId2').empty(); 
			$('#datasetId2').append('<option value="" >Select Dataset</option>');
			for (var i = 0; i < data.length; i++) {
				$("#datasetId2").append("<option value='" + data[i].dataSetId+ "'>" + data[i].dataSetName + "</option>");
			}
		}, 
		error: function(e){
			console.log("getDataSet--->"+e);
		}
	});  
}
</script>	
	   
</body>
</html>
