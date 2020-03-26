<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Manage Search Engine | infoAnalytica</title>
  
  <%@include file="include/common_css.jsp" %>
  <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
</head>
<body class="hold-transition skin-blue fixed sidebar-mini">
<div class="wrapper">
  <%@include file="include/header.jsp" %>
  <!-- =============================================== -->
  <%@include file="include/sidebar.jsp" %>
  <!-- =============================================== -->

  <div class="content-wrapper">
    <section class="content-header">
      <h1>
        Crowler 
        <small>List</small>  
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li class="active">Search Engine</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-xs-12">
          <div class="box">
            <div class="box-header">
              <h3 class="box-title">Search List</h3>
              <div class="box-tools">  
              	<a href="searchEngine" class="btn btn-default"><i class="fa fa-plus-square" aria-hidden="true"></i></a>
              </div>
            </div>   
            <!-- /.box-header -->
            <div class="box-body table-responsive no-padding">
              <table class="table table-hover">
                <tbody><tr>
                  <th width="3%">ID</th>
                  <th width="17%">Project Name</th>
                  <th width="15%">Dataset Name</th> 
                  <th width="25%">Name</th>
                  <th width="20%">Date</th> 
                  <th width="20%">Status</th> 
                </tr>
                <c:forEach items="${getSearchData }" var="getSearchData" varStatus="start">
	                <tr> 
	                  <td>${start.count }</td>
	                  <td>${getSearchData.projectName }</td>
	                  <td>${getSearchData.datasetName }</td>
	                  <td>${getSearchData.name }</td>
	                  <td>
	                  		<fmt:parseDate value="${getSearchData.createdDate }" var="parsedDate"  pattern="yyyy-MM-dd" />  
	                  		<fmt:formatDate  type="date"   value = "${parsedDate}" /> 
	                  </td>
	                  <td>
	                  		<c:if test="${getSearchData.status == 0 }">
	                  			<span class="label label-warning">Pending</span> 
	                  		</c:if>	
	                  </td>	                  
	                </tr>	
                </c:forEach>
                
                <tr>
                  <td>219</td>
                  <td>Alexander Pierce</td>
                  <td>11-7-2014</td>
                  <td></td>
                  <td></td>
                  <td><span class="label label-warning">Pending</span></td>
                </tr>
                <tr>
                  <td>657</td>
                  <td>Bob Doe</td>
                  <td>11-7-2014</td>
                  <td></td>
                  <td></td>
                  <td><span class="label label-primary">Approved</span></td>
                </tr>
                <tr>
                  <td>175</td>
                  <td>Mike Doe</td>
                  <td>11-7-2014</td>
                  <td></td>
                  <td></td>
                  <td><span class="label label-danger">Denied</span></td>
                </tr>
              </tbody></table>
            </div>
          </div>
        </div>
      </div>
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->

  	<%@include file="include/footer.jsp" %>
</div>
	<%@include file="include/common_js.jsp" %>
 
</body>
</html>
