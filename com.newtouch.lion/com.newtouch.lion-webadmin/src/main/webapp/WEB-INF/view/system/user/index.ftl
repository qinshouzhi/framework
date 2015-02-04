<#assign base = request.contextPath/>
<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
<title>用户管理</title>
<!--EasyUI css Start-->
<link href="${base}/resources/global/plugins/easyui/themes/metro/panel.css" rel="stylesheet" type="text/css"/>
<link href="${base}/resources/global/plugins/easyui/themes/metro/linkbutton.css" rel="stylesheet" type="text/css"/>
<link href="${base}/resources/global/plugins/easyui/themes/metro/datagrid.css" rel="stylesheet" type="text/css"/>
<link href="${base}/resources/global/plugins/easyui/themes/metro/window.css" rel="stylesheet" type="text/css"/>
<link href="${base}/resources/global/plugins/easyui/themes/metro/pagination.css" rel="stylesheet" type="text/css"/>
<link href="${base}/resources/global/plugins/easyui/themes/metro/combo.css" rel="stylesheet" type="text/css"/>
<link href="${base}/resources/global/plugins/easyui/themes/metro/combobox.css" rel="stylesheet" type="text/css"/>
<link href="${base}/resources/global/plugins/easyui/themes/icon.css" rel="stylesheet" type="text/css"/>
<!--EasyUI css End-->
<!--bootstrap css Start-->
<link href="${base}/resources/global/plugins/bootstrap-select/bootstrap-select.min.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/global/plugins/bootstrap-select/bootstrap-select.min.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/global/plugins/bootstrap-toastr/toastr.css" rel="stylesheet" type="text/css">
<!--bootstrap css End-->
<!--zTree css Start-->
<link href="${base}/resources/global/plugins/ztree/css/metro.css" rel="stylesheet" type="text/css"/>
<!--lion UI css Start-->
<link href="${base}/resources/global/css/lion.css" rel="stylesheet" type="text/css">
<link href="${base}/resources/global/css/dialog/lion.dialog.css" rel="stylesheet" type="text/css">
<link href="${base}/resources/global/css/combo/lion.combo.css" rel="stylesheet" type="text/css">
<link href="${base}/resources/global/css/combotree/combotree.css" rel="stylesheet" type="text/css" />
<!--lion UI css End-->

<script src="${base}/resources/global/plugins/bootstrap-select/bootstrap-select.min.js" type="text/javascript" ></script>
<script src="${base}/resources/global/plugins/bootbox/bootbox.min.js" type="text/javascript"></script>
<script src="${base}/resources/global/plugins/bootstrap-toastr/toastr.min.js"></script>
<script src="${base}/resources/admin/pages/scripts/ui-toastr.js"></script>
<script src="${base}/resources/global/plugins/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>


<!--EasyUI JavaScript Start-->
<script src="${base}/resources/global/plugins/easyui/jquery.easyui.min.js" type="text/javascript"></script>
<script src="${base}/resources/global/plugins/easyui/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
<!--EasyUI JavaScript End-->
<!--ztree js-->
<script src="${base}/resources/global/plugins/ztree/js/jquery.ztree.all-3.5.min.js" type="text/javascript"></script>
<!--lion UI JS Start-->
<script src="${base}/resources/global/js/lion.js" type="text/javascript"></script>
<script src="${base}/resources/global/js/form/form.fill.js" type="text/javascript"></script>
<script src="${base}/resources/global/js/dialog/dialog.js" type="text/javascript"></script>
<script src="${base}/resources/global/js/combo/combo.js" type="text/javascript"></script>
<script src="${base}/resources/global/js/combotree/combotree.js" type="text/javascript"></script>
<!--lion UI JS End-->
<script src="${base}/resources/global/js/local/lion-lang-zh_CN.js" type="text/javascript"></script>
<script src="${base}/resources/admin/scripts/system/user.js" type="text/javascript"></script>
<!--DatePicker-->
<script src="${base}/resources/global/plugins/datepicker/WdatePicker.js" type="text/javascript" ></script>
</head>
<body class="page-header-fixed page-sidebar-closed-hide-logo page-sidebar-closed-hide-logo">
<!-- BEGIN PAGE CONTENT INNER -->
<div class="portlet light">
	<div class="portlet-body">
		<div class="row">
			<div class="col-md-12 margin-bottom-10">
				<form id="queryform" class="form-horizontal">
					<label class="control-label col-md-1" for="username" >用户名</label>
					<div class="col-md-2">
						<input class="form-control input-small" type="text" size="30" name="username" id="username"  placeholder="请输入用户名称"/>					
					</div>
					<label class="control-label col-md-1" for="queryEmployeeCode" >员工号</label>
					<div class="col-md-2">
						<input class="form-control input-small" type="text" size="30" name="employeeCode" id="queryEmployeeCode"  placeholder="请输入员工号称"/>					
					</div>
					<label class="control-label col-md-1" for="queryemail" >邮箱</label>
					<div class="col-md-2">
						<input class="form-control input-small" type="text" size="30" name="email" id="queryemail"  placeholder="请输入邮箱称"/>					
					</div>	
					<div class="col-md-1">
						<a href="javascript:void(0)" class="btn blue" id="btnQuery"><i class="fa fa-search"></i>
						 <@spring.message "common.query.btn.text"/> 
						</a>
					</div>
				</form>
			</div>
		
			<div class="col-md-12 margin-bottom-10" id="toolbar">
				<a id="btnAdd" class="btn btn-sm yellow" data-toggle="modal" href="#basic">
					<i class="fa fa-plus"></i>  <@spring.message "common.toolbar.btn.add.text"/> </a>
				<a id="btnEdit" class="btn btn-sm red" role="button">
					<i class="fa fa-edit"></i>
					 <@spring.message "common.toolbar.btn.edit.text"/> 				 
				</a>
				<a href="javascript:void(0)" id="btnDelete" class="btn btn-sm purple"><i class="fa fa-times"></i> <@spring.message "common.toolbar.btn.delete.text"/> </a>
				<a href="javascript:void(0)" id="btnRefresh" class="btn btn-sm blue">
					<i class="fa fa-refresh"></i> <@spring.message "common.toolbar.btn.reload.text"/> 
				</a>
				<a href="javascript:void(0)" id="btnExport"  class="btn btn-sm green">
					<i class="fa  fa-file-excel-o"></i> <@spring.message "common.toolbar.btn.export.text"/> 
				</a>
				<a href="javascript:void(0)" id="btnExport"  class="btn btn-sm  btn-primary">
					<i class="fa  fa-gear"></i> 
					<@spring.message "common.toolbar.btn.auth.text"/>  
				</a>
				<a href="javascript:void(0)" class="btn btn-sm red" id="lokcedBtn">
				    <i class="fa   fa-lock"></i> 
					<@spring.message "common.toolbar.btn.locked.text"/>
				</a>
				<a href="javascript:void(0)" class="btn btn-sm blue-hoki" id="unlockBtn">
					<i class="fa  fa-unlock-alt"></i> 
					<@spring.message "common.toolbar.btn.unlock.text"/>
				</a> 
				<a href="javascript:void(0)" class="btn btn-sm default" id="detailsBtn">
					<i class="fa   fa-th"></i> 
					<@spring.message "common.toolbar.btn.details.text"/>
				</a> 
			</div>
			<div class="col-md-12">
				  <@lion.datagrids name="userlist_dt" tableClass="easyui-datagrid" toolbar=""  load="true" url="${base}/system/user/list.json" dataOptions="" style="height:400px;"/>
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
				<h4 class="modal-title"><i class="fa fa-plus"></i><span>添加用户</span></h4>
			</div>
			<div class="modal-body">
				 	<div class="row">
					 	<div class="col-md-12 portlet-body form">
					 		<!-- BEGIN FORM-->
									<form action="#" id="addform" class="form-horizontal">
									    <input type="hidden" id='id' name='id' value="">
										<div class="form-body">
											<div class="form-group">
												<div class="form-filed">
													<label class="col-md-2 control-label" for="username">用户名<i class="fa required">*</i></label>
													<div class="col-md-3">
														<div class="input-group">
															<input type="text"  id="username" name="username"  maxlength="100" class="form-control" placeholder="请输入用户名" size="30"/>
														</div>
													</div>
												</div>
												<div class="form-filed">
													<label class="col-md-2 control-label" for="employeeCode">员工号</label>
													<div class="col-md-2">
														<div class="input-group">
															<input   type="text"  id="employeeCode" name="employeeCode" maxlength="100" class="form-control" placeholder="请输入员工号" size="30"/>
														</div>
													</div>
												</div>
												<div class="form-filed">
												<label class="col-md-1 control-label">性别</label>
													<div class="col-md-2">
														<div class="input-group radio-list">	
																<label class="radio-inline">
																	<input type="radio" name="gender" id="gender0" value="0" checked>
																	男
																</label>
																<label class="radio-inline">
																	<input type="radio" name="gender" id="gender1" value="1">
																	 女 
															    </label>
														</div>
													</div>
												</div>
											</div>
											<div class="form-group">
												<div class="form-filed">
													<label class="col-md-2 control-label" for="email">邮箱</label>
													<div class="col-md-4">
														<div class="input-group">
															<input type="text"  id="email" name="email" maxlength="100" class="form-control" placeholder="请输入邮箱" size="30"/>
														</div>
													</div>
												</div>
												<div class="form-filed">
													<label class="col-md-2 control-label" for="departmentId">所属部门</label>
													<div class="col-md-4">
														<div class="input-group">
															 <input  id="departmentId"  name="departmentId"  
					 	  	  placeholder="请选择部门…"  type="text" 
					 		  class="lion-combotree form-control"   data-loadURL="${base}/system/department/comboxtree.json" data-width="200px" data-height="300px"/>
														</div>
													</div>
												</div>												
											</div>								 
											<!--<div class="form-group">
												<label class="col-md-2 control-label">密码</label>
												<div class="col-md-4">
													<div class="input-group">
														<input type="text"  id="nameEn" name="nameEn" maxlength="100" class="form-control"  placeholder="请输入密码" size="30"/>
													</div>
												</div>
												<label class="col-md-2 control-label">确认密码</label>
												<div class="col-md-4">
													<div class="input-group">
														<input type="text"  id="nameEn" name="nameEn" maxlength="100" class="form-control" placeholder="请输入输入确认密码" size="30"/>
													</div>
												</div>												
											</div>-->
											<div class="form-group">									
												<label class="col-md-2 control-label">密码有效期</label>
												<div class="col-md-2">
													<div class="input-group">
														<input type="text" name="value" class="form-control" placeholder="请选择密码有效期" maxlength="10" size="20" readonly="true" value="${credentialExpiredDate!}" />
													</div>
												</div>
												<label class="col-md-2 control-label">
													密码是否有效 
													<input type="checkbox" class="form-control"  name="editable" checked="true" />						 
												</label>
												<label class="col-md-2 control-label">账户有效期</label>
												<div class="col-md-2">
													<div class="input-group">
														<input id="d11" type="text" class="form-control"  placeholder="请选择账户有效期"  maxlength="10" size="20"  readonly="true"  value="${accountExpiredDate!}" />
													</div>
												</div>
												<label class="col-md-2 control-label">
													账户是否有效
													<input type="checkbox" class="form-control"  name="editable" checked="true" />  
												</label>
											</div>			
											<div class="form-group">
												<label class="col-md-2 control-label">真实姓名(中文)</label>
												<div class="col-md-4">
													<div class="input-group">
														<input type="text"  id="realnameZh" name="realnameZh" maxlength="100" class="form-control" placeholder="请输入真实姓名（中文）" size="30"/>
													</div>
												</div>
												<label class="col-md-2 control-label">真实姓名(英文)</label>
												<div class="col-md-4">
													<div class="input-group">
														<input type="text"  id="realnameEn" name="realnameEn" maxlength="100" class="form-control" placeholder="请输入真实姓名（英文）" size="30"/>
													</div>
												</div>
											</div>										 
											<div class="form-group">
												<label class="col-md-2 control-label">电话</label>
												<div class="col-md-4">
													<div class="input-group">
														<input type="text" name="value" class="form-control" placeholder="请输入电话" maxlength="255" size="30"/>
													</div>
												</div>
												<label class="col-md-2 control-label">手机</label>
												<div class="col-md-4">
													<div class="input-group">
														<input type="text" name="value" class="form-control" placeholder="请输入手机" maxlength="255" size="30"/>
													</div>
												</div>
											</div>
											<div class="form-group">										
												<label class="col-md-2 control-label">办公电话</label>
												<div class="col-md-4">
													<div class="input-group">
														<input type="text" name="value" class="form-control" placeholder="请输入办公电话" maxlength="255" size="30"/>
													</div>
												</div>
												<label class="col-md-2 control-label">传真</label>
												<div class="col-md-4">
													<div class="input-group">
														<input type="text" name="value" class="form-control" placeholder="请输入传真" maxlength="255" size="30"/>
													</div>
												</div>
											</div>
											<div class="form-group">										
												<label class="col-md-2 control-label">地址</label>
												<div class="col-md-4">
													<div class="input-group">
														<input type="text" name="value" class="form-control" placeholder="请输入地址" maxlength="255" size="30"/>
													</div>
												</div>
												<label class="col-md-2 control-label">邮编</label>
												<div class="col-md-2">
													<div class="input-group">
														<input type="text" name="value" class="form-control" placeholder="请输入邮编" maxlength="255" size="30"/>
													</div>
												</div>
												<label class="col-md-2 control-label">
													是否可编辑
													<input type="checkbox" class="form-control"  name="editable" checked="true" />
													</label>
												</div>
											</div>
																			 
											<div class="form-group">
												<label class="col-md-2 control-label">描述</label>
												<div class="col-md-10">
													<div class="input-group">
														<input type="text" name="value" class="form-control input-xlarge" placeholder="请输入描述" maxlength="255" size="30"/>
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
				<button type="button" id="btnCancel" class="btn default" data-dismiss="modal">
					<i class="fa  fa-arrow-left"></i> 取 消 </button>
				<button type="button" id="btnSave" class="btn blue">
					<i class="fa fa-save"></i> 保 存</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
</div>
<!--Dialog End -->
</body>
</html>