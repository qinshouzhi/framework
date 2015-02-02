<#assign base = request.contextPath/>
<!DOCTYPE html>
<html lang="en" class="no-js">
<head> 
<title>DataGrid信息</title>
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
<script src="${base}/resources/global/plugins/bootstrap-toastr/toastr.min.js" type="text/javascript" ></script>
<script src="${base}/resources/admin/pages/scripts/ui-toastr.js" type="text/javascript" ></script>
<script src="${base}/resources/global/plugins/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>

<!--EasyUI JavaScript Start-->
<script src="${base}/resources/global/plugins/easyui/jquery.easyui.min.js" type="text/javascript"></script>
<script src="${base}/resources/global/plugins/easyui/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
<!--EasyUI JavaScript End-->
<script src="${base}/resources/global/scripts/framework.js" type="text/javascript"></script>
<script src="${base}/resources/global/local/framework-lang-zh_CN.js" type="text/javascript"></script>
<script src="${base}/resources/admin/scripts/system/datagrid.js" type="text/javascript"></script>
</head>
<body class="page-header-fixed page-sidebar-closed-hide-logo page-sidebar-closed-hide-logo">
<!-- BEGIN PAGE CONTENT INNER -->
<div class="portlet light">
	<div class="portlet-body">
		<div class="row">
			<div class="col-md-12 margin-bottom-10">
				<form id="queryParameterform" class="form-horizontal">
					<label class="control-label col-md-2" for="tableId" ><@spring.message "sys.datagrid.query.tableid.text"/></label>
					<div class="col-md-5">
						<input class="form-control input-small" type="text" size="30" name="tableId" id="tableId"  placeholder="<@spring.message "sys.datagrid.query.tableid.missing.message"/>"/>					
					</div>
					<div class="col-md-3">
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
				 <@lion.datagrids name="datagrid_dt" tableClass="easyui-datagrid" toolbar=""  load="true" url="${base}/system/datagrid/list.htm" dataOptions="" style="height:400px;"/>	
			</div>
		</div>
	</div>
</div>
<!-- END PAGE CONTENT INNER -->
<!--Dialog Start -->
<div class="modal fade bs-modal-lg " id="basic" tabindex="-1" role="basic" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content ">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
				<h4 class="modal-title"><i class="fa fa-plus"></i> <@spring.message "sys.datagrid.form.adddialog.text"/></h4>
			</div>
			<div class="modal-body">
				 	<div class="row">
					 	<div class="col-md-12 portlet-body form">
					 		<!-- BEGIN FORM-->
									<form action="#" class="form-horizontal">
										<div class="form-body">
											<div class="form-group">
												<label class="col-md-1 control-label"><@spring.message "sys.datagrid.form.type.text"/></label>
												<div class="col-md-2">
													<div class="input-group">
														<input type="text"  name="type"  maxlength="100" class="form-control" placeholder="<@spring.message "sys.datagrid.form.type.missing.message"/>" size="30"/>
													</div>
												</div>
												<label class="col-md-1 control-label"><@spring.message "sys.datagrid.form.tableId.text"/></label>
												<div class="col-md-2">
													<div class="input-group">
														<input   type="text"  id="tableId" name="tableId" maxlength="100" class="form-control" placeholder="<@spring.message "sys.datagrid.form.tableId.missing.message"/>" size="30"/>
													</div>
												</div>
												<label class="col-md-1 control-label"><@spring.message "sys.datagrid.form.title.text"/></label>
												<div class="col-md-2">
													<div class="input-group radio-list">	
														<input   type="text"  id="title" name="title" maxlength="100" class="form-control" placeholder="<@spring.message "sys.datagrid.form.title.missing.message"/>" size="30"/>
													</div>
												</div>
												<label class="col-md-1 control-label"><@spring.message "sys.datagrid.form.showTitle.text"/></label>
												<div class="col-md-2">
													<div class="input-group">
														<input type="checkbox" class="form-control "  name="showTitle" checked="true" />
													</div>
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-1 control-label"><@spring.message "sys.datagrid.form.fit.text"/></label>
												<div class="col-md-1">
													<div class="input-group">
														<input type="checkbox" class="form-control "  name="fit" checked="true" />
													</div>
												</div>												
												<label class="col-md-1 control-label"><@spring.message "sys.datagrid.form.frozen.text"/></label>
												<div class="col-md-1">
													<div class="input-group">
														<input type="checkbox" class="form-control "  name="frozen" checked="true" />
													</div>
												</div>												
												<label class="col-md-1 control-label"><@spring.message "sys.datagrid.form.showGroup.text"/></label>
												<div class="col-md-1">
													<div class="input-group">
														<input type="checkbox" class="form-control "  name="showGroup" checked="true" />
													</div>
												</div>												
												<label class="col-md-1 control-label"><@spring.message "sys.datagrid.form.pagination.text"/></label>
												<div class="col-md-1">
													<div class="input-group">
														<input type="checkbox" class="form-control "  name="pagination" checked="true" />
													</div>
												</div>												
												<label class="col-md-1 control-label"><@spring.message "sys.datagrid.form.fitColumns.text"/></label>
												<div class="col-md-1">
													<div class="input-group">
														<input type="checkbox" class="form-control "  name="fitColumns" checked="true" />
													</div>
												</div>												
												<label class="col-md-1 control-label"><@spring.message "sys.datagrid.form.striped.text"/></label>
												<div class="col-md-1">
													<div class="input-group">
														<input type="checkbox" class="form-control "  name="Striped" checked="true" />
													</div>
												</div>	
											</div>
											<div class="form-group">
												<label class="col-md-2 control-label"><@spring.message "sys.datagrid.form.method.text"/></label>
												<div class="col-md-2">
													<div class="input-group">
														<input type="text"  name="method"  maxlength="100" class="form-control" placeholder="<@spring.message "sys.datagrid.form.method.missing.message"/>" size="30"/>
													</div>
												</div>
												<label class="col-md-2 control-label"><@spring.message "sys.datagrid.form.nowrap.text"/></label>
												<div class="col-md-1">
													<div class="input-group">
														<input type="checkbox" class="form-control "  name="nowrap" checked="true" />
													</div>
												</div>
												<label class="col-md-1 control-label"><@spring.message "sys.datagrid.form.url.text"/></label>
												<div class="col-md-4">
													<div class="input-group radio-list">	
														<input   type="text"  id="url" name="url" maxlength="100" class="form-control" placeholder="<@spring.message "sys.datagrid.form.url.missing.message"/>" size="30"/>
													</div>
												</div>
											</div>											
											<div class="form-group">
												<label class="col-md-2 control-label"><@spring.message "sys.datagrid.form.data.text"/></label>
												<div class="col-md-4">
													<div class="input-group">
														<input   type="text"  id="data" name="data" maxlength="100" class="form-control" placeholder="<@spring.message "sys.datagrid.form.data.missing.message"/>" size="30"/>
													</div>
												</div>
												<label class="col-md-2 control-label"><@spring.message "sys.datagrid.form.loadMsg.text"/></label>
												<div class="col-md-4">
													<div class="input-group">
														<input type="text"  name="loadMsg"  maxlength="100" class="form-control" placeholder="<@spring.message "sys.datagrid.form.loadMsg.missing.message"/>" size="30"/>
													</div>
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-2 control-label"><@spring.message "sys.datagrid.form.rownumbers.text"/></label>
												<div class="col-md-1">
													<div class="input-group">
														<input type="checkbox" class="form-control "  name="rownumbers" checked="true" />
													</div>
												</div>
												<label class="col-md-2 control-label"><@spring.message "sys.datagrid.form.singleSelect.text"/></label>
												<div class="col-md-1">
													<div class="input-group radio-list">	
														<input type="checkbox" class="form-control "  name="singleSelect" checked="true" />
													</div>
												</div>
												<label class="col-md-2 control-label"><@spring.message "sys.datagrid.form.checkOnSelect.text"/></label>
												<div class="col-md-1">
													<div class="input-group radio-list">	
														<input type="checkbox" class="form-control "  name="checkOnSelect" checked="true" />
													</div>
												</div>
												<label class="col-md-2 control-label"><@spring.message "sys.datagrid.form.selectOnCheck.text"/></label>
												<div class="col-md-1">
													<div class="input-group radio-list">	
														<input type="checkbox" class="form-control "  name="selectOnCheck" checked="true" />
													</div>
												</div>
											</div>	
											<div class="form-group">
												<label class="col-md-2 control-label"><@spring.message "sys.datagrid.form.pagePosition.text"/></label>
												<div class="col-md-2">
													<div class="input-group">
														<input type="text"  name="pagePosition"  maxlength="100" class="form-control" placeholder="<@spring.message "sys.datagrid.form.pagePosition.missing.message"/>" size="30"/>
													</div>
												</div>
												<label class="col-md-2 control-label"><@spring.message "sys.datagrid.form.pageNumber.text"/></label>
												<div class="col-md-1">
													<div class="input-group radio-list">	
														<input type="text"  name="pageNumber"  maxlength="100" class="form-control" placeholder="<@spring.message "sys.datagrid.form.pageNumber.missing.message"/>" size="30"/>
													</div>
												</div>
												<label class="col-md-1 control-label"><@spring.message "sys.datagrid.form.pageSize.text"/></label>
												<div class="col-md-1">
													<div class="input-group radio-list">	
														<input type="text"  name="pageSize"  maxlength="100" class="form-control" placeholder="<@spring.message "sys.datagrid.form.pageSize.missing.message"/>" size="30"/>
													</div>
												</div>
												<label class="col-md-1 control-label"><@spring.message "sys.datagrid.form.pageList.text"/></label>
												<div class="col-md-2">
													<div class="input-group radio-list">	
														<input type="text"  name="pageList"  maxlength="100" class="form-control" placeholder="<@spring.message "sys.datagrid.form.pageList.missing.message"/>" size="30"/>
													</div>
												</div>
											</div>																		
											<div class="form-group">
												<label class="col-md-1 control-label"><@spring.message "sys.datagrid.form.queryParams.text"/></label>
												<div class="col-md-2">
													<div class="input-group">
														<input type="text"  name="queryParams"  maxlength="100" class="form-control" placeholder="<@spring.message "sys.datagrid.form.queryParams.missing.message"/>" size="30"/>
													</div>
												</div>
												<label class="col-md-1 control-label"><@spring.message "sys.datagrid.form.sortName.text"/></label>
												<div class="col-md-2">
													<div class="input-group radio-list">	
														<input type="text"  name="sortName"  maxlength="100" class="form-control" placeholder="<@spring.message "sys.datagrid.form.sortName.missing.message"/>" size="30"/>
													</div>
												</div>
												<label class="col-md-1 control-label"><@spring.message "sys.datagrid.form.sortOrder.text"/></label>
												<div class="col-md-2">
													<div class="input-group radio-list">	
														<input type="text"  name="sortOrder"  maxlength="100" class="form-control" placeholder="<@spring.message "sys.datagrid.form.sortOrder.missing.message"/>" size="30"/>
													</div>
												</div>
												<label class="col-md-1 control-label"><@spring.message "sys.datagrid.form.remoteSort.text"/></label>
												<div class="col-md-2">
													<div class="input-group radio-list">	
														<input type="checkbox" class="form-control "  name="remoteSort" checked="true" />
													</div>
												</div>
											</div>																		
											<div class="form-group">
												<label class="col-md-2 control-label"><@spring.message "sys.datagrid.form.scrollbarSize.text"/></label>
												<div class="col-md-2">
													<div class="input-group radio-list">	
														<input type="text"  name="scrollbarSize"  maxlength="100" class="form-control" placeholder="<@spring.message "sys.datagrid.form.scrollbarSize.missing.message"/>" size="30"/>
													</div>
												</div>
												<label class="col-md-1 control-label"><@spring.message "sys.datagrid.form.rowStyler.text"/></label>
												<div class="col-md-2">
													<div class="input-group radio-list">	
														<input type="text"  name="rowStyler"  maxlength="100" class="form-control" placeholder="<@spring.message "sys.datagrid.form.rowStyler.missing.message"/>" size="30"/>
													</div>
												</div>
												<label class="col-md-1 control-label"><@spring.message "sys.datagrid.form.showHeader.text"/></label>
												<div class="col-md-1">
													<div class="input-group">
														<input type="checkbox" class="form-control "  name="showHeader" checked="true" />
													</div>
												</div>
												<label class="col-md-2 control-label"><@spring.message "sys.datagrid.form.showFooter.text"/></label>
												<div class="col-md-1">
													<div class="input-group radio-list">	
														<input type="checkbox" class="form-control "  name="showFooter" checked="true" />
													</div>
												</div>
											</div>		
											<div class="form-group">
												<label class="col-md-1 control-label"><@spring.message "sys.datagrid.form.loader.text"/></label>
												<div class="col-md-2">
													<div class="input-group">
														<input type="text"  name="loader"  maxlength="100" class="form-control" placeholder="<@spring.message "sys.datagrid.form.loader.missing.message"/>" size="30"/>
													</div>
												</div>
												<label class="col-md-1 control-label"><@spring.message "sys.datagrid.form.loadFilter.text"/></label>
												<div class="col-md-2">
													<div class="input-group radio-list">	
														<input type="text"  name="loadFilter"  maxlength="100" class="form-control" placeholder="<@spring.message "sys.datagrid.form.loadFilter.missing.message"/>" size="30"/>
													</div>
												</div>
												<label class="col-md-1 control-label"><@spring.message "sys.datagrid.form.editors.text"/></label>
												<div class="col-md-2">
													<div class="input-group radio-list">	
														<input type="text"  name="editors"  maxlength="100" class="form-control" placeholder="<@spring.message "sys.datagrid.form.editors.missing.message"/>" size="30"/>
													</div>
												</div>
												<label class="col-md-1 control-label"><@spring.message "sys.datagrid.form.view.text"/></label>
												<div class="col-md-2">
													<div class="input-group radio-list">	
														<input type="text"  name="view"  maxlength="100" class="form-control" placeholder="<@spring.message "sys.datagrid.form.view.missing.message"/>" size="30"/>
													</div>
												</div>
											</div>																
										</div>								 
									</form>
									<!-- END FORM-->
								</div>
					 	</div>
				 	<div>
			</div>
			<div class="modal-footer">
				<button type="button" id="btnCancel" class="btn default" data-dismiss="modal"><i class="fa  fa-arrow-left"></i> <@spring.message "common.diaglog.btn.cancel"/> </button>
				<button type="button" id="btnSave" class="btn blue"><i class="fa fa-save"></i> <@spring.message "common.diaglog.btn.save"/></button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
</div>
<!--Dialog End -->

</body>
</html>