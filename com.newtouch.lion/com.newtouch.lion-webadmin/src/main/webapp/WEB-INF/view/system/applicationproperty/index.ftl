<#assign base = request.contextPath/>
<!DOCTYPE html>
<html lang="en" class="no-js">
<head> 
<title><@spring.message "sys.applicationProperty.html.title"/></title>
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
<!--lion UI css Start-->
<link href="${base}/resources/global/css/lion.css" rel="stylesheet" type="text/css">
<link href="${base}/resources/global/css/dialog/lion.dialog.css" rel="stylesheet" type="text/css">
<link href="${base}/resources/global/css/combo/lion.combo.css" rel="stylesheet" type="text/css">
<!--lion UI css End-->
<script src="${base}/resources/global/plugins/bootbox/bootbox.min.js" type="text/javascript"></script>
<script src="${base}/resources/global/plugins/bootstrap-toastr/toastr.min.js" type="text/javascript" ></script>
<script src="${base}/resources/global/plugins/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>
<!--EasyUI JavaScript Start-->
<script src="${base}/resources/global/plugins/easyui/jquery.easyui.min.js" type="text/javascript"></script>
<script src="${base}/resources/global/plugins/easyui/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
<!--EasyUI JavaScript End-->
<!--lion UI JS Start-->
<script src="${base}/resources/global/js/lion.js" type="text/javascript"></script>
<script src="${base}/resources/global/js/form/form.fill.js" type="text/javascript"></script>
<script src="${base}/resources/global/js/dialog/dialog.js" type="text/javascript"></script>
<script src="${base}/resources/global/js/combo/combo.js" type="text/javascript"></script>
<!--lion UI JS End-->
<script src="${base}/resources/global/js/local/lion-lang-zh_CN.js" type="text/javascript"></script>
<script src="${base}/resources/admin/scripts/system/applicationproperty.js" type="text/javascript"></script>
</head>
<body class="page-header-fixed page-sidebar-closed-hide-logo page-sidebar-closed-hide-logo">
<!-- BEGIN PAGE CONTENT INNER -->
<div class="portlet light">
	<div class="portlet-body">
		<div class="row">
			<div class="col-md-12 margin-bottom-10">
				<form id="queryform" class="form-horizontal">
					<label class="control-label col-md-2" for="appId" ><@spring.message "sys.applicationProperty.query.appId.text"/></label>
					<div class="col-md-3">
						<input class="form-control input-small" type="text" size="30" name="appId" id="appId"  placeholder="<@spring.message "sys.applicationProperty.query.appId.missing.message"/>"/>					
					</div>
					<label class="control-label col-md-2" for="value" ><@spring.message "sys.applicationProperty.query.value.text"/></label>
					<div class="col-md-3">
						<input class="form-control input-small" type="text" size="30" name="value" id="value"  placeholder="<@spring.message "sys.applicationProperty.query.value.missing.message"/>"/>					
					</div>
					<div class="col-md-2">
						<a href="javascript:void(0)" id="btnQuery" class="btn blue"><i class="fa fa-search"></i> <@spring.message "common.query.btn.text"/> </a>
					</div>
				</form>
			</div>
		
			<div class="col-md-12 margin-bottom-10" id="toolbar">
				<a id="btnAdd" class="btn btn-sm yellow" data-toggle="modal" href="#basic"><i class="fa fa-plus"></i> <@spring.message "common.toolbar.btn.add.text"/>  </a>
				<a id="btnEdit" class="btn btn-sm red"><i class="fa fa-edit"></i> <@spring.message "common.toolbar.btn.edit.text"/></a>
				<a href="javascript:void(0)" id="btnDelete" class="btn btn-sm purple"><i class="fa fa-times"></i> <@spring.message "common.toolbar.btn.delete.text"/> </a>
				<a href="javascript:void(0)" id="btnRefresh" class="btn btn-sm blue"><i class="fa fa-refresh"></i> <@spring.message "common.toolbar.btn.reload.text"/>   </a>
				<a href="javascript:void(0)" id="btnExport"  class="btn btn-sm green"><i class="fa  fa-file-excel-o"></i> <@spring.message "common.toolbar.btn.export.text"/> </a>
			</div>
			<div class="col-md-12">
				 <@lion.datagrids name="sys_app_property_list_tb" tableClass="easyui-datagrid" toolbar=""  load="true" url="${base}/system/applicationproperty/list.htm" dataOptions="" style="height:400px;"/>	
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
				<h4 class="modal-title">
					<i class="fa fa-plus"></i> 
					<span>
						<@spring.message "sys.applicationProperty.form.adddialog.text"/>
					</span>
				</h4>
			</div>
			<div class="modal-body">
				 	<div class="row">
				 	<div class="col-md-12 portlet-body form">
				 		<!-- BEGIN FORM-->
							<form action="#" class="form-horizontal" name="sysAppPropertyForm" id="sysAppPropertyForm" method="post">
								<input type="hidden" id='id' name='id' value="">
								<div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label">
											<@spring.message "sys.applicationProperty.form.appId.text"/>
										</label>
										<div class="col-md-5">
											<input type="text"  name="appId"  maxlength="100" class="form-control" placeholder="<@spring.message "sys.applicationProperty.form.appId.missing.message"/>" size="30"/>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">
											<@spring.message "sys.applicationProperty.form.key.text"/>
										</label>
										<div class="col-md-5">
											<div class="input-group">
												<input type="text"  name="key"  maxlength="100" class="form-control" placeholder="<@spring.message "sys.applicationProperty.form.key.missing.message"/>" size="30"/>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">
											<@spring.message "sys.applicationProperty.form.value.text"/>
										</label>
										<div class="col-md-5">
											<div class="input-group">
												<input type="text"  id="value" name="value" maxlength="100" class="form-control" placeholder="<@spring.message "sys.applicationProperty.form.value.missing.message"/>" size="30"/>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">
											<@spring.message "sys.applicationProperty.form.description.text"/>
										</label>
										<div class="col-md-5">
											<div class="input-group ">															 
												<input type="text" class="form-control" name="description" placeholder="<@spring.message "sys.applicationProperty.form.description.missing.message"/>" maxlength="255" size="40"/>
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
				<button type="button" id="btnCancel" class="btn default" data-dismiss="modal">
					<i class="fa  fa-arrow-left"></i> 
					<@spring.message "common.diaglog.btn.cancel"/>
				</button>
				<button type="button" id="btnSave" class="btn blue">
					<i class="fa fa-save"></i>
					<@spring.message "common.diaglog.btn.save"/>
				</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
</div>
	<!-- /.modal-dialog -->
<!--Edit Dialog End -->
</body>
</html>