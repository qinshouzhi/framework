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
<script src="${base}/resources/global/scripts/framework.js" type="text/javascript"></script>
<script src="${base}/resources/global/local/framework-lang-zh_CN.js" type="text/javascript"></script>
<script src="${base}/resources/admin/scripts/system/parameter.js" type="text/javascript"></script>
</head>
<body class="page-header-fixed page-sidebar-closed-hide-logo page-sidebar-closed-hide-logo">
<!-- BEGIN PAGE CONTENT INNER -->
<div class="portlet light">
	<div class="portlet-body">
		<div class="row inbox">
		<div class="col-md-12 margin-bottom-10" id="toolbar">
			<form id="queryParameterform">
				<table border="0" width="100%" height="40px;">
					<tr>
						<td width="20%" height="100%" align="right" valign="middle">
							 名称
						</td>
						<td width="30%" valign="middle">
						 	<input class="easyui-combobox" type="text" name="type" id="sys_parameter_type" data-options="url:'${base}/system/code/combox.htm?nameEn=SystemParamter',valueField:'nameEn',textField:'nameZh',panelHeight:'auto'">
						</td>
						<td width="50%" align="left" valign="middle">
							 <a  href="javascript:void(0)" class="easyui-linkbutton"  id="sys_parameter_queryBtn" >
								查询
							</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div class="col-md-12 margin-bottom-10" id="toolbar">
			<a href="javascript:void(0" id="btnAdd" class="btn btn-sm yellow"><i class="fa fa-plus"></i> 新增  </a>
			<a href="javascript:void(0)"id="btnEdit" class="btn btn-sm red"><i class="fa fa-edit"></i> 编辑 </a>
			<a href="javascript:void(0" id="btnDelete" class="btn btn-sm purple"><i class="fa fa-times"></i> 删除 </a>
			<a href="javascript:void(0" id="btnRefresh" class="btn btn-sm blue"><i class="fa fa-refresh"></i> 刷新  </a>
			<a href="javascript:void(0" id="btnExport"  class="btn btn-sm green"><i class="fa  fa-file-excel-o"></i> Excel </a>
		</div>
		<div class="col-md-12">
			  <@lion.datagrids name="sys_parameter_lists_tb" tableClass="easyui-datagrid" toolbar=""  load="true" url="${base}/system/parameter/list.htm" dataOptions="" style="height:400px;"/>
		</div>
		</div>
	</div>
</div>
<!-- END PAGE CONTENT INNER -->
</body>
</html>
