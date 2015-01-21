<#assign base = request.contextPath/>
<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
<title>用户组管理</title>
<!--EasyUI css Start-->
<link href="${base}/resources/global/plugins/easyui/themes/metro/panel.css" rel="stylesheet" type="text/css"/>
<link href="${base}/resources/global/plugins/easyui/themes/metro/linkbutton.css" rel="stylesheet" type="text/css"/>
<link href="${base}/resources/global/plugins/easyui/themes/metro/datagrid.css" rel="stylesheet" type="text/css"/>
<link href="${base}/resources/global/plugins/easyui/themes/metro/window.css" rel="stylesheet" type="text/css"/>
<link href="${base}/resources/global/plugins/easyui/themes/metro/pagination.css" rel="stylesheet" type="text/css"/>
<link href="${base}/resources/global/plugins/easyui/themes/metro/combo.css" rel="stylesheet" type="text/css"/>
<link href="${base}/resources/global/plugins/easyui/themes/metro/combobox.css" rel="stylesheet" type="text/css"/>
<link href="${base}/resources/global/plugins/easyui/themes/icon.css" rel="stylesheet" type="text/css"/>
<link href="${base}/resources/global/plugins/bootstrap-select/bootstrap-select.min.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/global/plugins/bootstrap-toastr/toastr.css" rel="stylesheet" type="text/css">

<!--EasyUI css End-->
<script src="${base}/resources/global/plugins/bootstrap-select/bootstrap-select.min.js" type="text/javascript" ></script>
<script src="${base}/resources/global/plugins/bootbox/bootbox.min.js" type="text/javascript"></script>
<script src="${base}/resources/global/plugins/bootstrap-toastr/toastr.min.js"></script>
<script src="${base}/resources/admin/pages/scripts/ui-toastr.js"></script>

<!--EasyUI JavaScript Start-->
<script src="${base}/resources/global/plugins/easyui/jquery.easyui.min.js" type="text/javascript"></script>
<script src="${base}/resources/global/plugins/easyui/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
<!--EasyUI JavaScript End-->
<script src="${base}/resources/global/scripts/framework.js" type="text/javascript"></script>
<script src="${base}/resources/global/local/framework-lang-zh_CN.js" type="text/javascript"></script>
<script src="${base}/resources/admin/scripts/system/group.js" type="text/javascript"></script>
</head>
<body class="page-header-fixed page-sidebar-closed-hide-logo page-sidebar-closed-hide-logo">
<!-- BEGIN PAGE CONTENT INNER -->
<div class="portlet light">
	<div class="portlet-body">
		<div class="row">
			<div class="col-md-12 margin-bottom-10">
				<form id="queryParameterform" class="form-horizontal">
					<label class="control-label col-md-2" for="nameZh" >用户组名称</label>
					<div class="col-md-3">
						<input class="form-control input-small" type="text" size="30" name="nameZh" id="nameZh"  placeholder="请输入用户组名称"/>					
					</div>
					<div class="col-md-5">

					</div>
					<div class="col-md-2">
						<a href="javascript:void(0)" class="btn blue"><i class="fa fa-search"></i>查 询 </a>
					</div>
				</form>
			</div>
		
			<div class="col-md-12 margin-bottom-10" id="toolbar">
				<a id="btnAdd" class="btn btn-sm yellow" data-toggle="modal" href="#basic"><i class="fa fa-plus"></i> 新增  </a>
				<a id="btnEdit" class="btn btn-sm red" role="button" data-toggle="modal" data-target="#editDialog"><i class="fa fa-edit"></i> 编辑
					<div class="modal" id="editDialog" tabindex="-1"></div>
				</a>
				<a href="javascript:void(0)" id="btnDelete" class="btn btn-sm purple"><i class="fa fa-times"></i> 删除 </a>
				<a href="javascript:void(0)" id="btnRefresh" class="btn btn-sm blue"><i class="fa fa-refresh"></i> 刷新  </a>
				<a href="javascript:void(0)" id="btnExport"  class="btn btn-sm green"><i class="fa  fa-file-excel-o"></i> Excel </a>
			</div>
			<div class="col-md-12">
				 <@lion.datagrids name="sys_group_list_tb" tableClass="easyui-datagrid" toolbar=""  load="true" url="${base}/system/group/list.htm" dataOptions="" style="height:400px;"/>	
			</div>
		</div>
	</div>
</div>
<!-- END PAGE CONTENT INNER -->
<!--Edit Dialog Start -->
<div class="modal fade" id="basic" tabindex="-1" role="basic" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
				<h4 class="modal-title"><i class="fa fa-plus"></i> 角色组添加</h4>
			</div>
			<div class="modal-body">
				 	<div class="row">
				 	<div class="col-md-12 portlet-body form">
				 		<!-- BEGIN FORM-->
										<form action="#" class="form-horizontal">
											<div class="form-body">
												<div class="form-group">
													<label class="col-md-3 control-label">角色组名称(中文)</label>
													<div class="col-md-5">
														<div class="input-group">
															<input type="text"  name="nameZh"  maxlength="100" class="form-control" placeholder="请输入参数名称（中文）" size="30"/>
														</div>
													</div>
												</div>
												<div class="form-group">
													<label class="col-md-3 control-label">角色组名称(英文)</label>
													<div class="col-md-5">
														<div class="input-group">
															<input type="text"  id="nameEn" name="nameEn" maxlength="100" class="form-control" placeholder="请输入参数名称（中文）" size="30"/>
														</div>
													</div>
												</div>
												<div class="form-group">
													<label class="col-md-3 control-label">角色组描述</label>
													<div class="col-md-5">
														<div class="input-group">
															<input type="text" name="value" class="form-control" placeholder="请输入参数值" maxlength="255" size="30"/>
														</div>
													</div>
												</div>
												<div class="form-group">
													<label class="col-md-3 control-label">是否可编辑</label>
													<div class="col-md-5 control-label">
														<div class="input-group">
															<input type="checkbox" class="form-control "  name="editable" checked="true" />
														</div>
													</div>
												</div>
											</div>
										</form>
									<!-- END FORM-->
							</div>
				 	</div>
			</div>
			<div class="modal-footer">
				<button type="button" id="btnCancel" class="btn default" data-dismiss="modal"><i class="fa  fa-arrow-left"></i> 取 消 </button>
				<button type="button" id="btnSave" class="btn blue"><i class="fa fa-save"></i> 保 存</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	
	<!-- /.modal-dialog -->
<!--Edit Dialog End -->
</body>
</html>
