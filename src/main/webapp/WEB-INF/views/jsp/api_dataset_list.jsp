<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Manage API | infoAnalytica</title>
  
  <%@include file="include/common_css.jsp" %>
  <%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
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
        API 
        <small>List</small>  
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li class="active">API</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-xs-12">
          <div class="box">
            <div class="box-header">
              <h3 class="box-title">API List</h3>
              <div class="box-tools">  
              	<a href="apiDataSet" class="btn btn-default"><i class="fa fa-plus-square" aria-hidden="true"></i></a>
              </div>
            </div>
            <!-- /.box-header -->
            <p style="text-align: center;"> <font size="3" color="red">${uploadLimit }</font></p>
            <c:set value="0" var="j"></c:set>
            <div class="box-body table-responsive no-padding">
              <table class="table table-hover">
                <tbody><tr>
                  <th>ID</th>
                  <th>Dataset Name</th>
                  <th>Date</th>
                  <th style="text-align: center;" align="center">Status</th> 
                </tr> 
                
                 
                <c:forEach items="${getAPIDatasetList }" var="apiData" varStatus="i" >
                	<tr> 
	                  <td> ${i.index + 1 }</td>
	                  <td>${apiData.name }</td>
	                  <td>
	                  		<fmt:parseDate value="${apiData.createdDate}" var="parsedDate"  pattern="yyyy-MM-dd" />  
			              	<fmt:formatDate  type="date"   value = "${parsedDate}" />
			          </td>
	                  <td  style="text-align: center;" align="center">
	                  		<c:choose>
	                  			<c:when test="${apiData.status eq 'Done' }">
	                  				<i style="cursor: pointer;" onclick="exportUrlStatus(${apiData.apiDatasetId},'${apiData.apiType}')"  class="fa fa-cloud-download" aria-hidden="true"></i>
	                  			</c:when>
	                  			<c:otherwise>
	                  				<span class="label label-warning">Pending</span>
	                  			</c:otherwise>
	                  		</c:choose>
	                  </td>
	                </tr> 
                </c:forEach>
                
                 
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
	
	<form action="<%=request.getContextPath()%>/exportUSPSAPI" name="downloadFinalList" id="downloadFinalList">
      	<input type="hidden" name="dataSetID" id="dataSetID">
      	<input type="hidden" name="action" id="action">
      	<input type="hidden" name="databaseName" id="databaseName">
	</form> 

<script type="text/javascript">
function exportUrlStatus(dataSetID,action) {
	$("#databaseName").val(dataSetID); 
	$("#action").val(action);
	$("#dataSetID").val(dataSetID); 	
	document.getElementById("downloadFinalList").submit();
}

function exportMongoOutput(dataSetID){
	
	$.ajax({
		type : "POST",
		url : "exportUrlStatus",
		data : {
			dataSetID : dataSetID
		},
		success :function(data){
			alert(data);
		},
		error : function(e){
			console.log("Error ::->"+e)
		}
		
		
	});
	
}

</script>
 
</body>
</html>
