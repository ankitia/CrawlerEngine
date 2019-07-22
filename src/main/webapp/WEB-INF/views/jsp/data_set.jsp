<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Crawler Set | infoAnalytica</title>
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
      <h1>Crawler</h1>  
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li class="active">Crawler</li>
      </ol> 
    </section>

    <!-- Main content -->
    <section class="content">
    <div class="row">
        <!-- left column -->
        <div class="col-md-12">
		      <div class="box box-primary">
		            <div class="box-header with-border">
		              <h3 class="box-title">Crawler</h3>
		            </div>
		            <!-- /.box-header -->
		            <!-- form start --> 
		            <form role="form">
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
		                  <input type="text" class="form-control" id="exampleInputDataset" placeholder="Dataset" style="width: 300px;">
		                </div>
		                <div class="checkbox">
		                  <label title="B2B/B2C, Product/Services, Manufacturer/Retailer">
		                    <input type="checkbox"> Market variables &nbsp;&nbsp; <i  class="fa fa-info" aria-hidden="true"></i>
		                  </label>
		                </div>
		                <div class="checkbox">
		                  <label>
		                    <input type="checkbox"> Address 
		                  </label>
		                </div>
		                <div class="checkbox">
		                  <label>
		                    <input type="checkbox"> Key Contacts
		                  </label>
		                </div>
		                <div class="checkbox">  
		                  <label title="Traces for Ecom, ShoppingCart, Payment, GiftCrads, Donation, Subscription">
		                    <input type="checkbox"> Tech install &nbsp;&nbsp; <i  class="fa fa-info" aria-hidden="true"></i>
		                  </label>
		                </div>
		                <div class="checkbox">
		                  <label>
		                    <input type="checkbox"> Product Count
		                  </label>
		                </div>
		                <div class="form-group">
		                  <label for="exampleInputFile">File input</label>
		                  <input type="file" id="exampleInputFile">
		
		                  <p class="help-block">Upload url csv file.</p>
		                </div>		                
		              </div>
		              <!-- /.box-body -->
		
		              <div class="box-footer">
		                <a href="manageDataset" class="btn btn-primary">Submit</a>
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
</body>
</html>
