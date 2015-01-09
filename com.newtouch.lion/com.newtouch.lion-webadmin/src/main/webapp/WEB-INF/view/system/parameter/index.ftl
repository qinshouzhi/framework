<#assign base = request.contextPath/>
<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
<title>系统参数管理</title>
<!--EasyUI css Start-->
<link href="${base}/resources/global/plugins/easyui/themes/metro/panel.css" rel="stylesheet" type="text/css"/>
<link href="${base}/resources/global/plugins/easyui/themes/metro/linkbutton.css" rel="stylesheet" type="text/css"/>
<link href="${base}/resources/global/plugins/easyui/themes/metro/datagrid.css" rel="stylesheet" type="text/css"/>
<link href="${base}/resources/global/plugins/easyui/themes/metro/window.css" rel="stylesheet" type="text/css"/>
<link href="${base}/resources/global/plugins/easyui/themes/metro/pagination.css" rel="stylesheet" type="text/css"/>
<link href="${base}/resources/global/plugins/easyui/themes/icon.css" rel="stylesheet" type="text/css"/>
<!--EasyUI css End-->

<!--EasyUI JavaScript Start-->
<script src="${base}/resources/global/plugins/easyui/jquery.easyui.min.js" type="text/javascript"></script>
<script src="${base}/resources/global/plugins/easyui/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
<!--EasyUI JavaScript End-->
<script src="${base}/resources/admin/scripts/system/parameter.js" type="text/javascript"></script>
</head>
<body class="page-header-fixed page-sidebar-closed-hide-logo page-sidebar-closed-hide-logo">
<!-- BEGIN PAGE CONTENT INNER -->
<div class="portlet light">
	<div class="portlet-body">
		<div class="row inbox">
		<div class="col-md-12 margin-bottom-10" id="toolbar">
			<a href="javascript:void(0" id="btnAdd" class="btn btn-sm yellow"><i class="fa fa-plus"></i> 新增  </a>
			<a href="javascript:void(0)"id="btnEdit" class="btn btn-sm red"><i class="fa fa-edit"></i> 编辑 </a>
			<a href="javascript:void(0" id="btnDelete" class="btn btn-sm purple"><i class="fa fa-times"></i> 删除 </a>
			<a href="javascript:void(0" id="btnRefresh" class="btn btn-sm blue"><i class="fa fa-refresh"></i> 刷新  </a>
			<a href="javascript:void(0" id="btnExport"  class="btn btn-sm green"><i class="fa  fa-file-excel-o"></i> Excel </a>
		</div>
		<div class="col-md-12">
			 ddd
			  <@lion.datagrid name="sys_parameter_lists_tb" tableClass="easyui-datagrid" toolbar=""  load="true" url="${base}/system/parameter/lists.htm" dataOptions="" style=""/>
		</div>
		<div class="col-md-12 margin-top-10">
			<table class="easyui-datagrid" style="height:400px;padding-left:10px;padding-right:10px;" data-options="rownumbers:true,singleSelect:true,autoRowHeight:false,pagination:true,pageSize:10,singleSelect:true,url:'${base}/datagrid_data1.json',method:'get'">
				<thead>
					<tr>
						<th data-options="field:'itemid',width:80">Item ID</th>
						<th data-options="field:'productid',width:100">Product</th>
						<th data-options="field:'listprice',width:80,align:'right'">List Price</th>
						<th data-options="field:'unitcost',width:80,align:'right'">Unit Cost</th>
						<th data-options="field:'attr1',width:250">Attribute</th>
						<th data-options="field:'status',width:60,align:'center'">Status</th>
					</tr>
				</thead>
			</table>
		</div>
		</div>
	</div>
</div>
<!-- END PAGE CONTENT INNER -->
</body>
</html>
