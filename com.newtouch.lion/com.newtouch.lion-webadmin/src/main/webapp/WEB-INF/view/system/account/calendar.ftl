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
<script src="${base}/resources/admin/scripts/system/account.js" type="text/javascript"></script>
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
						<div class="col-md-3 col-sm-12">
							<!-- BEGIN DRAGGABLE EVENTS PORTLET-->
							<h3 class="event-form-title">Draggable Events</h3>
							<div id="external-events">
								<form class="inline-form">
									<input type="text" value="" class="form-control" placeholder="Event Title..." id="event_title"/><br/>
									<a href="javascript:;" id="event_add" class="btn default">
									Add Event </a>
								</form>
								<hr/>
								<div id="event_box">
								</div>
								<label for="drop-remove">
								<input type="checkbox" id="drop-remove"/>remove after drop </label>
								<hr class="visible-xs"/>
							</div>
							<!-- END DRAGGABLE EVENTS PORTLET-->
						</div>
						<div class="col-md-9 col-sm-12">
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
</body>
</html>
