<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Deploy Index</title>
  <jsp:include  page="includes/linkPage.jsp" />
</head>
<body class="skin-blue sidebar-mini wysihtml5-supported" style="overflow-y: hidden">
<div class="wrapper">
  <jsp:include  page="includes/head.jsp" />
  
  <!-- Left side column. contains the logo and sidebar -->
  
  <jsp:include  page="includes/sideBar.jsp" />
  
  <!-- Content Wrapper. Contains page content -->
  
  <div class="content-wrapper"  >
  <section class="content" style="padding:0 0 0 0" >
	    
	    <iframe id="test" name="test"  style="width:100%;height:95% " src="<%=request.getContextPath()%>/import/importData.jsp" ></iframe>
	    
  	</section>
  	
  	
    
  </div>
	
</div>
<jsp:include  page="includes/footScript.jsp" />	
<!-- AdminLTE dashboard demo (This is only for demo purposes) 
<script src="<%=request.getContextPath()%>/resources/lte/js/pages/dashboard.js"></script>
-->

<!-- AdminLTE for demo purposes -->
<script src="<%=request.getContextPath()%>/resources/lte/js/demo.js"></script>
<script src="<%=request.getContextPath()%>/javascripts/deployTool.js"></script>
<script>
$(document).ready(function(){
	//$("#test").slimScroll({height:'auto'});
	var isIE = /msie/.test(navigator.userAgent.toLowerCase());
	if(isIE){
		$('#test').css("height","");
		var myiframe = document.getElementById("test");
		myiframe.height = myiframe.document.body.scrollHeight;
	}
});



</script>

</body>
</html>