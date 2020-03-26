<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>API Dataset | infoAnalytica</title>
  <%@include file="include/common_css.jsp" %>
  <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>  
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
      <h1>API</h1>  
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li class="active">API</li>
      </ol> 
    </section>

    <!-- Main content -->
    <section class="content">
    <div class="row">
        <!-- left column -->
        <div class="col-md-12">
		      <div class="box box-primary">
		            <div class="box-header with-border">
		              <h3 class="box-title">API</h3>
		            </div>
		            <!-- /.box-header -->
		            <!-- form start --> 
		            <form role="form" action="insertAPIDataset" method="post" enctype="multipart/form-data"> 
		              <div class="col-md-6">
		              <div class="box-body">
		               <div class="form-group">
		                  <label>Project</label>
		                  <select class="form-control" name="projectId" id="projectId" style="width: 300px;">
		                        <option value="">Project Select</option>  
		                    <c:forEach items="${projectList }" var="projectList"> 
		                    	<option value="${projectList.projectId }">${projectList.name }</option>
		                    </c:forEach>
		                  </select>
		                </div>  
		                <div class="form-group">
		                  <label for="exampleInputEmail1">Dataset</label>
		                  <input type="text" class="form-control" name="dataSetName" id="exampleInputDataset" placeholder="Dataset" style="width: 300px;">
		                </div>
		                 <div class="radio"> 
							  <label><input type="radio" name="apiType" value="usps_spider" checked>USPS</label>
							</div>
							<div class="radio">
							  <label><input type="radio" name="apiType" value="smartystreet_spider">Smartystreets</label>
							</div>
							
		                <div class="form-group">
		                  <label for="exampleInputFile">File input</label> 
		                  <input type="file" id="exampleInputFile" name="exampleInputFile">
		
		                  <p class="help-block">Upload url csv file.</p>
		                </div>		
		                
		                <div class="form-group">
			                <button type="submit" class="btn btn-primary" name="save"  value="Submit">Submit</button>
			                <a href="manageDataset" class="btn btn-danger">Back</a>
			            </div>                    
		              </div>
		             </div>  
		             <div class="col-md-6" style="display: none;"> 
		             </div>
		              <!-- /.box-body -->
		              <div class="box-footer"> 
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
</body>
</html>
