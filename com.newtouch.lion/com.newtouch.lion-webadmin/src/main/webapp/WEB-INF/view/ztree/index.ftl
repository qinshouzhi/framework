<#assign base = request.contextPath/>
<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
<title>ZTree Demo</title>
<!--zTree css Start-->
<link href="${base}/resources/global/plugins/ztree/css/metro.css" rel="stylesheet" type="text/css"/>
<!--zTree css End--> 
<!--lion UI JS Start-->
<script src="${base}/resources/global/plugins/ztree/js/jquery.ztree.all-3.5.min.js" type="text/javascript"></script>
<script src="${base}/resources/admin/scripts/ztree/ztree.js" type="text/javascript"></script>
</head>
<body class="page-header-fixed page-sidebar-closed-hide-logo page-sidebar-closed-hide-logo">
<!-- BEGIN PAGE CONTENT INNER -->
<div class="portlet light">
	<div class="portlet-body">
		<div class="row">
			<div class="col-md-12 margin-bottom-10">
				 <ul id="ztree" class="ztree" style="width:560px; overflow:auto;"></ul>
			</div> 
			
			<div class="col-md-12 margin-bottom-10">
				<ul id="treeDemo" class="ztree"></ul> 
			</div> 
		</div>
		</div>
	</div>
</div>
</body>
</html>
