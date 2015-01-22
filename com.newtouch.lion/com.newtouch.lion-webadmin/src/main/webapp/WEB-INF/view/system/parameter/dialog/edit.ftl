 
 	<div class="row">
		 	<div class="col-md-12 portlet-body form">
		 		<!-- BEGIN FORM-->
				<form action="#" class="form-horizontal">
					<div class="form-body">
						<div class="form-group">
							<label class="col-md-3 control-label">参数类型</label>
							<div class="col-md-5">
								 <select  id="editParameterCodeList"  name="type" data-size="8" 
								 	data-maxoptions="1"   multiple placeholder="请选择参数列表..."  
								 	class="lion-combo bootstrap-select form-control input-small" data-valueField='codeValue' 
								 	data-textField='nameZh' data-loadURL="${base}/system/code/combox.htm?nameEn=SystemParamter">
								 </select>
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
<script type="text/javascript">
</script>