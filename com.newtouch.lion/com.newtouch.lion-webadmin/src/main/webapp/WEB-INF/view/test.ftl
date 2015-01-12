<#assign  base = request.contextPath/>
<#import  "/tags/lion.ftl" as lions/>
<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
<title>控件测试－应用管理系统</title>
<link rel="stylesheet" type="text/css" href="${base}/resources/global/plugins/bootstrap-select/bootstrap-select.min.css"/>
<link rel="stylesheet" type="text/css" href="${base}/resources/global/plugins/select2/select2.css"/>
<link rel="stylesheet" type="text/css" href="${base}/resources/global/plugins/jquery-multi-select/css/multi-select.css"/>
<link   href="${base}/resources/global/plugins/bootstrap-select/bootstrap-select.css" rel="stylesheet" type="text/css"/>
<script src="${base}/resources/global/plugins/bootstrap-select/bootstrap-select.js" type="text/javascript"></script>
<script type="text/javascript" src="${base}/resources/global/plugins/bootstrap-select/bootstrap-select.min.js"></script>
<script type="text/javascript" src="${base}/resources/global/plugins/select2/select2.min.js"></script>
<script type="text/javascript" src="${base}/resources/global/plugins/jquery-multi-select/js/jquery.multi-select.js"></script>
<script type="text/javascript" src="${base}/resources/admin/pages/scripts/components-dropdowns.js"></script>


<script src="${base}/resources/admin/scripts/test.js" type="text/javascript"></script>

</head>
<body class="page-header-fixed page-sidebar-closed-hide-logo page-sidebar-closed-hide-logo">
<!-- BEGIN CONTAINER -->
 <!-- BEGIN PAGE CONTENT INNER -->
 <div class="portlet light">
	<div class="portlet-body">
		<div class="row">
			<div class="col-md-4">
				<select class="bootstrap-select bs-select form-control input-small" title='请选择参数列表' data-size="8" multiple data-max-options="1">
				  <option>1</option>
				  <option>2</option>
				  <option>3</option>
				  <option>4</option>
				  <option>5</option>
				  <option>6</option>
				  <option>7</option>
				  <option>8</option>
				  <option>9</option>
				  <option>10</option>
				</select>
			</div>
			<div class="col-md-4">
				<@lion.combobox id="parameterCodeList" codeName="SystemParamter" dataClass="bootstrap-select bs-select form-control input-small" title="请选择参数列表" dataSize="8" multipleDataMaxOptions="1"/>
				 
			</div>
		</div>
 	</div>
</div>
<!-- END JAVASCRIPTS -->
</body>
</html>