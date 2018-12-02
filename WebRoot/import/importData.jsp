<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>数据导入</title>
  <jsp:include  page="../includes/linkPage.jsp" />
  <style type="text/css">
  .row-margin-top {
    margin-top: 10px;
  }
  </style>
</head>
<body class="skin-blue sidebar-mini wysihtml5-supported">
<div id="mainTest">

  	<section class="content" >
	    <div class="row" >
	    <form id="file_form" enctype="multipart/form-data" class="form-horizontal">
			<input type="hidden" value="" name="appId" id="hf_appId" />
			<input type="hidden" value="" name="appName" id="hf_appName" />
			<input type="hidden" value="" name="userId" id="hf_userId" />
			<div class="box-body">
				<div class="form-group">
					<label for="radioEnv" class="col-sm-2 control-label">更新环境</label>
					
					<div class="col-sm-10">
						<div class="row">
							
							<div class="col-xs-3">
								<label id="lbl_msg" class="control-label"></label>
								<div class="radio">
									<label>
										<input type="radio" name="radioEnv" id="checkBoxEnv1" value="DEV" checked="">
										DEV
									</label>
								</div>
							</div>
							
						</div>
						
					</div>
				</div>
				<div class="form-group">
					<label for="warVersion" class="col-sm-2 control-label">文件版本</label>
		
					<div class="col-sm-10">
					<input type="text" class="form-control" id="warVersion" name="warVersion" disabled>
					</div>
				</div>
				<div class="form-group">
					<label for="warFilePath" class="col-sm-2 control-label">文件路径</label>
					<div class="col-sm-8">
						<input type="file" name="file_war" id="file_war" />
					</div>
					<!--  -->
					<div class="col-sm-2">
						<button type="button" class="btn btn-block btn-success" onclick="addWar();">上传</button>
					</div>
					
				</div>
				
			</div>
			<!-- /.box-body -->

		</form>
	    	
	    </div>

  	</section>
</div>
<jsp:include  page="../includes/footScript.jsp" />	


<!-- AdminLTE for demo purposes -->
<script src="<%=request.getContextPath()%>/resources/lte/js/demo.js"></script>
<!---->
<script src="<%=request.getContextPath()%>/javascripts/deployTool.js"></script>
<script src="<%=request.getContextPath()%>/javascripts/dataTable.js"></script>
<script>

function addWar(config){
	
	var formData = new FormData(document.getElementById('file_form'));
	formData.append('warVersion',"123");
	//added by Chunhui Chen 20180727

	blockUI();
	$.ajax({
		url : '<%=request.getContextPath()%>/test/addWar.do',
		type : 'post',
		data : formData,
	    cache: false,  
	    processData: false,  
	    contentType: false,
		success : function(data){
			console.log(data);
			unBlockUI();
			if(data.errorCode=='0'){
				$('#modal-add-war').modal('hide');
				toastr["success"]('启动成功!');
			}else{
				toastr["error"]('操作失败!'+data.errorMsg);
			}
			
			
			
			
		},
		error : function(data){
			console.log(data);
			unBlockUI();
			toastr["error"]('操作失败!');
		}
	});
}

</script>
</body>
</html>