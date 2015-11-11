<#assign base = request.contextPath/>
<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
<title>个人待办事项</title>
<link href="${base}/resources/global/plugins/bootstrap-select/bootstrap-select.min.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/global/plugins/bootstrap-toastr/toastr.css" rel="stylesheet" type="text/css">
<link href="${base}/resources/global/plugins/ztree/css/metro.css" rel="stylesheet" type="text/css"/>
<!--DataTable css Start-->
<link rel="stylesheet" type="text/css" href="${base}/resources/global/plugins/select2/select2.css"/>
<link rel="stylesheet" type="text/css" href="${base}/resources/global/plugins/datatables/extensions/Scroller/css/dataTables.scroller.min.css"/>
<link rel="stylesheet" type="text/css" href="${base}/resources/global/plugins/datatables/extensions/ColReorder/css/dataTables.colReorder.min.css"/>
<link rel="stylesheet" type="text/css" href="${base}/resources/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css"/>
<!--DataTable css End-->
<!--calendar css start-->
<link rel="stylesheet" type="text/css" src="${base}/resources/global/plugins/fullcalendar/fullcalendar.min.css"></script>
<!--calendar css end-->
<link href="${base}/resources/global/plugins/bootstrap-select/bootstrap-select.min.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/global/css/lion.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/global/css/combotree/combotree.css" rel="stylesheet" type="text/css" />
<!-- DataTables js Start -->
<script type="text/javascript" src="${base}/resources/global/plugins/select2/select2.min.js"></script>
<script type="text/javascript" src="${base}/resources/global/plugins/datatables/media/js/jquery.dataTables.js"></script>
<script type="text/javascript" src="${base}/resources/global/plugins/datatables/extensions/TableTools/js/dataTables.tableTools.js"></script>
<script type="text/javascript" src="${base}/resources/global/plugins/datatables/extensions/ColReorder/js/dataTables.colReorder.js"></script>
<script type="text/javascript" src="${base}/resources/global/plugins/datatables/extensions/Scroller/js/dataTables.scroller.js"></script>
<script type="text/javascript" src="${base}/resources/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js"></script>
<!-- DataTables js End -->
<script src="${base}/resources/global/plugins/bootbox/bootbox.min.js" type="text/javascript"></script>
<script src="${base}/resources/global/plugins/bootstrap-toastr/toastr.min.js"></script>
<script src="${base}/resources/admin/pages/scripts/ui-toastr.js"></script>
<script src="${base}/resources/global/plugins/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>
<!--EasyUI JavaScript End-->
<!--ztree js-->
<script src="${base}/resources/global/plugins/ztree/js/jquery.ztree.all-3.5.min.js" type="text/javascript"></script>
<!--lion UI JS Start-->
<script src="${base}/resources/global/js/lion.js" type="text/javascript"></script>
<script src="${base}/resources/global/js/form/form.fill.js" type="text/javascript"></script>
<script src="${base}/resources/global/js/dialog/dialog.js" type="text/javascript"></script>
<script src="${base}/resources/global/js/combo/combo.js" type="text/javascript"></script>
<script src="${base}/resources/global/js/combotree/combotree.js" type="text/javascript"></script>
<script src="${base}/resources/global/js/datagrid/datagrids.js" type="text/javascript"></script>
<!--lion UI JS End-->
<script src="${base}/resources/global/js/local/lion-lang-zh_CN.js" type="text/javascript"></script>
<!--calendar Js start-->
<script src="${base}/resources/global/plugins/moment.min.js" type="text/javascript"></script>
<script src="${base}/resources/global/plugins/fullcalendar/fullcalendar.min.js" type="text/javascript"></script>
<script src="${base}/resources/admin/pages/scripts/calendar.js" type="text/javascript"></script>
<!--calendar Js end-->
<script src="${base}/resources/admin/scripts/system/calendar.js" type="text/javascript"></script>

</head>
<body class="page-header-fixed page-sidebar-closed-hide-logo page-sidebar-closed-hide-logo">
<!-- BEGIN PAGE CONTENT-->
	<div class="row">
		<div class="col-md-12">
			<div class="portlet box green-meadow calendar">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-gift"></i>待办事项日程
					</div>
				</div>
				<div class="portlet-body">
					<div class="row">
						<div class="col-md-12 col-sm-12">
							<div id="calendar" class="has-toolbar">
							</div>
						</div>
					</div>
					<!-- END CALENDAR PORTLET-->
				</div>
			</div>
		</div>
	</div>
<!-- END PAGE CONTENT-->
<!--Edit Dialog Start -->
<div class="modal fade" id="basic" tabindex="-1" role="basic" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
				<h4 class="modal-title"><i class="fa fa-plus"></i> <span><@spring.message "sys.codeList.form.adddialog.text"/> </span></h4>
			</div>
			<div class="modal-body">
				 	<div class="row">
				 	<div class="col-md-12 portlet-body form">
				 		<!-- BEGIN FORM-->
						<form action="#" class="form-horizontal" name="sysCodeListForm" id="sysCodeListForm" method="post">
							<input type="hidden" id='id' name='id' value="">
							<div class="form-body">
								<div class="form-group">
									<label class="col-md-3 control-label"><@spring.message "sys.codeList.form.namezh.text"/></label>
									<div class="col-md-5">
										<div class="input-group">
											<input type="text"  name="nameZh"  maxlength="100" class="form-control" placeholder="<@spring.message "sys.codeList.form.namezh.missing.message"/>" size="30"/>
										</div>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label"><@spring.message "sys.codeList.form.nameen.text"/></label>
									<div class="col-md-5">
										<div class="input-group">
											<input type="text"  id="nameEn" name="nameEn" maxlength="100" class="form-control" placeholder="<@spring.message "sys.codeList.form.nameen.missing.message"/>" size="30"/>
										</div>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label"><@spring.message "sys.codeList.form.value.text"/></label>
									<div class="col-md-5">
										<div class="input-group">
											<input type="text" name="codeValue" class="form-control" placeholder="<@spring.message "sys.codeList.form.value.missing.message"/>" maxlength="255" size="30"/>
										</div>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label"><@spring.message "sys.codeList.form.sort.text"/></label>
									<div class="col-md-5">
										<div class="input-group ">
											<input type="text" name="sortNo" class="form-control" placeholder="<@spring.message "sys.codeList.form.sort.missing.message"/>" maxlength="255" size="30"/>
										</div>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label"><@spring.message "sys.codeList.form.editable.text"/></label>
									<div class="col-md-5 control-label">
										<div class="input-group">
											<input type="checkbox" class="form-control "  name="editable" checked="true" />
										</div>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label"><@spring.message "sys.codeList.form.selected.text"/></label>
									<div class="col-md-5 control-label">
										<div class="input-group">
											<input type="checkbox" class="form-control "  name="selected" checked="true" />
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
				<button type="button" id="btnCancel" class="btn default" data-dismiss="modal"><i class="fa  fa-arrow-left"></i> <@spring.message "common.diaglog.btn.cancel"/> </button>
				<button type="button" id="btnSave" class="btn blue"><i class="fa fa-save"></i> <@spring.message "common.diaglog.btn.save"/></button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</body>
</html>
