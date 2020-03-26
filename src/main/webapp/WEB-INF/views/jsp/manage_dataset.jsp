<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Manage Crawler | infoAnalytica</title>
  
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
        Crawler 
        <small>List</small>  
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li class="active">Crawler</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-xs-12">
          <div class="box">
            <div class="box-header">
              <h3 class="box-title">Crawler List</h3>
              <div class="box-tools">  
              	<a href="dataSet" class="btn btn-default"><i class="fa fa-plus-square" aria-hidden="true"></i></a>
              </div>
            </div>
            <!-- /.box-header -->
            <p style="text-align: center;"> <font size="3" color="red">${uploadLimit }</font></p>
            <div class="box-body table-responsive no-padding">
              <table class="table table-hover">
                <tbody><tr>
                  <th>ID</th>
                  <th>Dataset Name</th>
                  <th>Date</th> 
                  <th style="text-align: center;" align="center">Status</th> 
                  <th style="text-align: center;" align="center">URL Status</th>
                  <!-- <th style="text-align: center;" align="center">SKU Status</th>
                  <th>Reason</th> -->
                </tr> 
                <c:forEach items="${manageDataset }" var="manageDataset" varStatus="status">
                
                <tr> 
			                  <td >${status.count }</td>
			                  <td >${manageDataset.dataSetName }</td>
			                  <td>
			                  		<fmt:parseDate value="${manageDataset.createdDate}" var="parsedDate"  pattern="yyyy-MM-dd" />  
			                  		<fmt:formatDate  type="date"   value = "${parsedDate}" />
			                  </td>     
			                  <td align="center">
				                 <c:choose>
                					<c:when test="${manageDataset.status eq 'Pending' }">
                							<span class="label label-warning">Pending</span>
                					</c:when>
               						<c:otherwise>
               							<i style="cursor: pointer;" onclick="exportUrlStatus(${manageDataset.dataSetId},'Common')" class="fa fa-cloud-download" aria-hidden="true"></i>
               						</c:otherwise>
               					</c:choose> 	
			                  </td>
			                  <td align="center">
			                  	<c:if test="${manageDataset.urlStatusServer==0 }">
			                  		<span class="label label-warning">Pending</span>
			                  	</c:if>
			                  	<c:if test="${manageDataset.urlStatusServer==1 }">
			                  		<i style="cursor: pointer;" onclick="exportUrlStatus(${manageDataset.dataSetId},'URLStatus')" class="fa fa-cloud-download" aria-hidden="true"></i>
			                  	</c:if>
			                  </td>
			                  <%-- <td align="center">
			                  	<c:if test="${manageDataset.skuStatus==0 }">
			                  		<span class="label label-warning">Pending</span>
			                  	</c:if>
			                  	<c:if test="${manageDataset.skuStatus==1 }">
			                  		<span class="label label-warning">Done</span>
			                  	</c:if>
			                  	
			                  </td>
			                  <td></td> --%> 
			                </tr>	
                
                
                	<%-- <c:choose>
                		<c:when test="${manageDataset.status eq 'Pending' }">
		                	<tr> 
			                  <td >${status.count }</td>
			                  <td >${manageDataset.dataSetName }</td>
			                  <td>
			                  		<fmt:parseDate value="${manageDataset.createdDate}" var="parsedDate"  pattern="yyyy-MM-dd" />  
			                  		<fmt:formatDate  type="date"   value = "${parsedDate}" />
			                  </td>     
			                  <td align="center"><span class="label label-warning">Pending</span></td>
			                  <td align="center">
			                  	<c:if test="${manageDataset.urlStatusServer==0 }">
			                  		<span class="label label-warning">Pending</span>
			                  	</c:if>
			                  	<c:if test="${manageDataset.urlStatusServer==1 }">
			                  		<span class="label label-warning">Done</span>
			                  	</c:if>
			                  	
			                  </td>
			                  <td align="center">
			                  	<c:if test="${manageDataset.skuStatus==0 }">
			                  		<span class="label label-warning">Pending</span>
			                  	</c:if>
			                  	<c:if test="${manageDataset.skuStatus==1 }">
			                  		<span class="label label-warning">Done</span>
			                  	</c:if>
			                  	
			                  </td>
			                  <td></td>
			                </tr>	
                		</c:when>
                		<c:otherwise>
	                		<tr>   
			                  <td >${status.count }</td>
			                  <td >${manageDataset.dataSetName }</td>
			                  <td>
		                  		<fmt:parseDate value="${manageDataset.createdDate}" var="parsedDate"  pattern="yyyy-MM-dd" />  
		                  		<fmt:formatDate  type="date"   value = "${parsedDate}" />
			                  </td>
			                  <td align="center"><i style="cursor: pointer;" onclick="exportUrlStatus(${manageDataset.dataSetId})" class="fa fa-cloud-download" aria-hidden="true"></i></td>
			                  <td align="center">
			                  	<c:if test="${manageDataset.skuStatus==0 }">
			                  		<span class="label label-warning">Pending</span>
			                  	</c:if>
			                  	<c:if test="${manageDataset.skuStatus==1 }">
			                  		<span class="label label-warning">Done</span>
			                  	</c:if> 
			                  	
			                  </td>
 			                  <td></td>
			                </tr>
                		
                		</c:otherwise>
                	</c:choose> --%>
	                
                	
                </c:forEach>
                
               <!--  <tr>
                  <td>219</td>
                  <td>Alexander Pierce</td>
                  <td>11-7-2014</td>
                  <td><span class="label label-warning">Pending</span></td>
                  <td>Bacon ipsum dolor sit amet salami venison chicken flank fatback doner.</td>
                </tr>
                <tr>
                  <td>657</td>
                  <td>Bob Doe</td>
                  <td>11-7-2014</td>
                  <td><span class="label label-primary">Approved</span></td>
                  <td>Bacon ipsum dolor sit amet salami venison chicken flank fatback doner.</td>
                </tr>
                <tr>
                  <td>175</td>
                  <td>Mike Doe</td>
                  <td>11-7-2014</td>
                  <td><span class="label label-danger">Denied</span></td>
                  <td>Bacon ipsum dolor sit amet salami venison chicken flank fatback doner.</td>
                </tr> --> 
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
	
	<form action="<%=request.getContextPath()%>/exportUrlStatus" name="downloadFinalList" id="downloadFinalList">
      	<input type="hidden" name="dataSetID" id="dataSetID">
      	<input type="hidden" name="action" id="action">
	</form>

<script type="text/javascript">
function exportUrlStatus(dataSetID,action) {
	$("#dataSetID").val(dataSetID); 
	$("#action").val(action);
	document.getElementById("downloadFinalList").submit();
	/* exportMongoOutput("addressData","392");
	exportMongoOutput("emailData","392");
	exportMongoOutput("telephoneData","392");
	exportMongoOutput("formData","392");
	*/  
	//exportMongoOutput(dataSetID);   
	 
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
