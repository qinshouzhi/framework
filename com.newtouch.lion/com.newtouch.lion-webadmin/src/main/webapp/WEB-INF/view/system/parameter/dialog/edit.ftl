<!--Edit Dialog Start -->
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true" data-target="#editDialog"></button>
	<h4 class="modal-title text-left"><i class="fa fa-edit"></i>编辑系统参数添加</h4>
</div>
<div class="modal-body">
 	<div class="row">
		 	<div class="col-md-12 portlet-body form">
		 		<!-- BEGIN FORM-->
				<form action="#" class="form-horizontal">
					<div class="form-body">
						<div class="form-group">
							<label class="col-md-3 control-label">参数类型</label>
							<div class="col-md-5">
								<@lion.combobox id="sysParameterCodeList1"  codeName="SystemParamter"  name="type" dataClass="bootstrap-select bs-select form-control" title="请选择参数列表" dataSize="8" multipleDataMaxOptions="1"/>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">参数名称(中文)</label>
							<div class="col-md-5">
								<div class="input-group">
									<input type="text"  name="nameZh"  maxlength="100" class="form-control" placeholder="请输入参数名称（中文）" size="30"/>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">参数名称(英文)</label>
							<div class="col-md-5">
								<div class="input-group">
									<input type="text"  id="nameEn" name="nameEn" maxlength="100" class="form-control" placeholder="请输入参数名称（中文）" size="30"/>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">参数值</label>
							<div class="col-md-5">
								<div class="input-group">
									<input type="text" name="value" class="form-control" placeholder="请输入参数值" maxlength="255" size="30"/>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">描述</label>
							<div class="col-md-5">
								<div class="input-group ">															 
									<input type="text" class="form-control" name="description" placeholder="请输入参数描述" maxlength="255" size="40"/>
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
	<button type="button" id="btnCancel" class="btn default" data-dismiss="modal"  data-target="#editDialog"><i class="fa fa-arrow-left"></i> 取 消 </button>
	<button type="button" id="btnSave" class="btn blue"><i class="fa fa-save"></i> 保 存</button>
</div>
		<!-- /.modal-content -->
<!--Edit Dialog End -->
<script type="text/javascript">
$(function () {
	var handleBootstrapSelect = function() {
	        $('#sysParameterCodeList1').selectpicker({
	            iconBase: 'fa',
	            tickIcon: 'fa-check'
	        });
	}
	//初始化下拉框
	handleBootstrapSelect();
	//alert("ddd");
});
</script>