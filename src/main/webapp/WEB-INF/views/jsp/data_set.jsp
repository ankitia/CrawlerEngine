<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Crawler Set | infoAnalytica</title>
  <%@include file="include/common_css.jsp" %>
  <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>  
  
  <script type="text/javascript">
  
  function setAddress(address){
	       
	  if(address.checked){
		  $("#addressDetails").show();
	  }else{
		  $("#addressDetails").hide();
	  }
	  
  }
  
  function chaeckValidations(){
	  
  }
  
  </script>
  
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
		            <form role="form" action="insertDataset" method="post" enctype="multipart/form-data"> 
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
		                <div class="checkbox">
		                  <label title="B2B/B2C, Product/Services, Manufacturer/Retailer">
		                    <input type="checkbox" name="marketVariables" > Market variables &nbsp;&nbsp; <i  class="fa fa-info" aria-hidden="true"></i>
		                  </label>
		                </div>
		                <div class="checkbox">
		                  <label>
		                    <input type="checkbox" name="address" id="address" onclick="setAddress(this)"> Address 
		                  </label>
		                </div> 
		                <div id="addressDetails" style="margin-left: 25px;display: none;">
			                <div class="checkbox">
			                  <label>
			                    <input type="checkbox" name="addressEmail"> Email 
			                  </label>
			                </div>
			                <div class="checkbox">
			                  <label>
			                    <input type="checkbox" name="addressTelephone"> Telephone 
			                  </label>
			                </div>
			                <div class="checkbox">
			                  <label>
			                    <input type="checkbox" name="addressForm"> Form & Labels 
			                  </label>
			                </div>
			                <div class="checkbox">
			                  <label>
			                    <input type="checkbox" name="addressAddress"> Address 
			                  </label>
			                </div>
		                
		                </div>
		                <div class="checkbox">
		                  <label>
		                    <input type="checkbox" name="keyContacts"> Key Contacts
		                  </label>
		                </div>
		                <div class="checkbox">  
		                  <label title="Traces for Ecom, ShoppingCart, Payment, GiftCrads, Donation, Subscription">
		                    <input type="checkbox" name="techInstall"> Tech install &nbsp;&nbsp; <i  class="fa fa-info" aria-hidden="true"></i>
		                  </label>
		                </div>
		                <div class="checkbox">
		                  <label> 
		                    <input type="checkbox" disabled="disabled"  name="productCount"> Product Count
		                  </label> 
		                </div> 
		                <div class="checkbox">
		                  <label> 
		                    <input type="checkbox" name="urlStatus"> URL Status
		                  </label> 
		                </div> 
		                <div class="form-group" title="Enter values between 0 -* meaning if you feed it a starting url of http://somedomain.com/start/here, that is depth zero, and any link found on that response would be depth one and so on. If you feed * then it will extract the whole website.">
		                  <label for="exampleInputEmail1">Max Depth &nbsp;&nbsp; <i  class="fa fa-info" aria-hidden="true"></i> </label>
		                  <!-- <input type="text" class="form-control" name="maxDepth" id="maxDepth" value="0" placeholder="Max Depth" style="width: 300px;" /> -->
		                  <select name="maxDepth" id="maxDepth"  class="form-control"  style="width: 300px;"> 
		                  	<option value="0">1</option> 
		                  	<option value="1">2</option>
		                  	<option value="2">3</option>
		                  </select>
		                   
		                </div>
		                 
		                
		                <div class="form-group">
		                  <label for="exampleInputFile">File input</label> 
		                  <input type="file" id="exampleInputFile" name="exampleInputFile">
		
		                  <p class="help-block">Upload url csv file.</p>
		                </div>		
		                
		                <div class="form-group">
			                <button type="submit" class="btn btn-primary" name="save"  onclick="chaeckValidations()" value="Submit">Submit</button>
			                <a href="manageDataset" class="btn btn-danger">Back</a>
			            </div>                    
		              </div>
		             </div>  
		             <div class="col-md-6" style="display: none;"> 
		             	<div class="form-group">
		                  <label for="exampleInputEmail1">Rules</label>
		                </div> 
		             	<div class="checkbox">
		                  <label>
		                    <input type="checkbox" name="b2b"> B2B 
		                  </label>
		                </div>
		                <div class="checkbox">
		                  <label>
		                    <input type="checkbox" name="b2c"> B2C
		                  </label>
		                </div>
		                <div class="checkbox">
		                  <label>
		                    <input type="checkbox" name="product"> Product
		                  </label>
		                </div>
		                <div class="checkbox">
		                  <label>
		                    <input type="checkbox" name="services"> Services
		                  </label>
		                </div>
		                <div class="checkbox">
		                  <label>
		                    <input type="checkbox" name="manufacturer"> Manufacturer
		                  </label>
		                </div>
		                <div class="checkbox">
		                  <label> 
		                    <input type="checkbox" name="retail"> Retail
		                  </label>
		                </div>
		                <div class="checkbox">
		                  <label>  
		                    <input type="checkbox" checked="checked" name="all"> All
		                  </label>
		                </div>
		                <div class="form-group">
		                  <label for="exampleInputEmail1">Product Count</label> 
		                  <input type="text" class="form-control" name="productCount" id="productCount" value="1" placeholder="Product Count" style="width: 300px;">
		                </div>   
		                 
		                
		             </div>
		              <!-- /.box-body -->
		
					
					<!-- <div class="col-md-6"> -->
		               
		                <!-- <a href="manageDataset" class="btn btn-primary">Submit</a> -->  
		                <!-- <button type="submit" class="btn btn-primary" name="save"  onclick="chaeckValidations()" value="Submit">Submit</button>
		                <a href="manageDataset" class="btn btn-danger">Back</a>   --> 
		             <!--  </div> -->
		             
		              
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
