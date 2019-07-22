<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Manage Project | infoAnalytica</title>
  
  	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  	<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
  	<%@include file="include/common_css.jsp" %>
 
</head>

<body class="hold-transition skin-blue fixed sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

 
  <%@include file="include/header.jsp" %>
  <!-- =============================================== -->
  <%@include file="include/sidebar.jsp" %>
  <!-- =============================================== -->

  
  <div class="content-wrapper">
    <section class="content-header">
      <h1>Manage Project</h1> 
      <ol class="breadcrumb">  
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li class="active">Project</li>
      </ol> 
    </section>

   

    <!-- Main content -->
    <section class="content"> 
      <div class="row">
        <div class="col-xs-12">
          <div class="box">
            <div class="box-header">
              <h3 class="box-title">Project</h3>
              <div class="box-tools">
                <a href="project" class="btn btn-default"><i class="fa fa-plus-square" aria-hidden="true"></i></a>
              </div>
              <!-- <div style="display: none;" id="login-alert" class="alert alert-danger col-sm-12">No project available.</div> --> 
            </div>  
            <!-- /.box-header -->
            <div class="box-body table-responsive no-padding">
              <table class="table table-hover">
                <thead>
			      <tr>
			        <th width="3%">#</th>
			        <th width="17%">Name</th>
			        <th width="65%">Description</th>
			        <th width="15%" style="text-align: center;">Active/DeActive</th>
			      </tr>
			    </thead>
                <tbody id="projectList">
	    	<c:if test="${fn:length(projectList) == 0 }">
    			<tr>
    				<td colspan="4">
    					<div id="login-alert" class="alert alert-danger col-sm-12">No project available.</div>
    				</td>
    			</tr> 
	    	</c:if>
	    	<c:forEach items="${projectList }" var="projectList" varStatus="status">
		    	  <tr>
			        <td >${status.count }</td>
			        <td >${projectList.name }</td>
			        <td>${projectList.desc }</td> 
			        <td align="center">
			        	<c:choose>
			        		<c:when test="${projectList.status eq '1' }">
			        			<input type="checkbox" name="projectStatus" id="projectStatus_${projectList.projectId}" onclick="updateProjectStatus('${projectList.projectId}')" checked="checked">
			        		</c:when>
			        		<c:otherwise>
			        			<input type="checkbox" name="projectStatus"  onclick="updateProjectStatus('${projectList.projectId}')" id="projectStatus_${projectList.projectId}" >
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
     <script src="<%=request.getContextPath() %>/resources/panel/js/manage_project.js"></script>
</body>
</html>
