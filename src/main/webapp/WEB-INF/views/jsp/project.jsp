<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Project | infoAnalytica</title>
  <%@include file="include/common_css.jsp" %> 
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
      <h1>Project</h1> 
      <ol class="breadcrumb">  
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li class="active">Project</li>
      </ol> 
    </section>

    <!-- Main content -->
    <section class="content">
    <div class="row">
        <!-- left column -->
        <div class="col-md-12">
		      <div class="box box-primary">
		            <div class="box-header with-border">
		              <h3 class="box-title">Project</h3>
		            </div>
		            <!-- /.box-header -->
		            <!-- form start -->
		            <form role="form">
		              <div class="box-body">
		                <div class="form-group">
		                  <label for="exampleInputEmail1">Project Name</label>
		                  <input type="text" class="form-control" id="projectName"  name="projectName" placeholder="Project Name" required style="width: 300px;">
		                </div>
		                <div class="form-group">
		                  <label for="exampleInputEmail1">Description</label>
		                  <input type="text" class="form-control" id="desc" name="desc" placeholder="desc" style="width: 300px;">
		                </div> 
		              </div> 
		              <!-- /.box-body -->
		 
		              <div class="box-footer">  
		                <a href="#" class="btn btn-primary" onclick="insertProject()">Submit</a>
		                <a href="manageProject" class="btn btn-danger">Back</a> 
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
	<script src="<%=request.getContextPath() %>/resources/panel/js/manage_project.js"></script>
</body>
</html>