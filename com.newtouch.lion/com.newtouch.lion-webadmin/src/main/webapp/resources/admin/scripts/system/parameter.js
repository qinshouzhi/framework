$(function() {
	//加载bootstrap
	Metronic.init(); // init metronic core componets
	Layout.init(); // init layout
	Tasks.initDashboardWidget(); // init tash dashboard widget
	
	var datagridId='#sys_parameter_lists_tb';

	var addForm=$('#sysParameterForm');
	var addDialog=$('#basic');
	
	
	handleVForm(addForm,submitForm);
	//选择DataGrid单行
	function getSelectedRow(){return $(datagridId).datagrid('getSelected');}
	 
	$(datagridId).datagrid({
		 onLoadSuccess : function(data) {
		}
	});
		
	//重新加载DataGrid
  function dataGridReload(dataGridId){
     $(datagridId).datagrid('reload');
  }
	 //刷新
	 $('#btnRefresh').on('click',function(){
		   dataGridReload(datagridId);
	 });
	 //新增
	 $('#btnAdd').on('click',function(){
		  addForm[0].reset();
		  addForm.find('.form-group').removeClass('has-error');
		  addForm.find('.help-block').remove();
		  $('.lion-combo').combo('reloadLi');
	 });

	 addForm.on('show.bs.modal',function(){
	 	 addForm[0].reset(); 
	 }); 

	 $('#btnSave').click(function(){
	 		addForm.submit();
	 });

	 //编辑
	 $('#btnEdit').on('click',function(){
		 var row=getSelectedRow();
		 if(!row){
			 lion.util.info('提示','请选择要编辑记录');
			 return;
		 }
		 $('#basic').modal('toggle');
		 addForm.fill(row);
	 });
	 //删除
	 $('#btnDelete').on('click',function(){
		 var row=getSelectedRow();
		 if(!row){
			 lion.util.info('提示','请选择要删除记录');
			 return;
		 }
		 bootbox.confirm('确认要删除此记录？', function(result) {
              if(result){
            	 
            	  var ps = '?id='+row.id;
					//$.post('delete.htm' + ps, function(data) {
					//	var dataJson=eval('(' + data + ')');
					//	//parent.$.topCenterMsgBox(dataJson.message);
					//	dataGridReload();
					//	dataGridReload($.sys.parameter.datagridId);
					//});
            	  lion.util.success('提示!', '已删除成功');
              }
          }); 
	 });
	 //导出Excel
	 $('#btnExport').on('click',function(){
		 alert('dd');
	 });
});
/**新增或编辑的提交代码*/
function submitForm(frm){
	var param=frm.serialize(),id=($('#id').val());
  //ID为空时，为添加动作
  if(lion.util.isEmpty(id)){
 	    lion.util.post('add.json',param,successAddFrm,errorRequest);
  }else{
      lion.util.post('edit.json',param,successAddFrm,errorRequest,param.id);
  }
}

//添加后&编辑后提交
function successAddFrm(data,arg,id){
  
  if(data!==null&&!(data.hasError)){
  	lion.util.success('提示',data.message);
  	$('#basic').modal('toggle');
    $('#sys_parameter_lists_tb').datagrid('reload');
  }else if(data!==null&&data.hasError){
  	var gmsg='';
  	for(var msg in data.errorMessage){
  		gmsg+=data.errorMessage[msg];
  	}
  	if(lion.util.isEmpty(gmsg)){
  		gmsg='添加参数出错';
  	}
  	lion.util.error('提示',gmsg);
  }else{
  	lion.util.error('提示','添加参数失败');
  }
}
//请求失败后信息
function errorRequest(data,arg){
	lion.util.error('提示','网络连接异常');
}

//验证表单
handleVForm=function(vForm,submitCallBackfn){
	var addError = $('.alert-danger', vForm);
    var addSuccess = $('.alert-success',vForm);
	vForm.validate({
        errorElement: 'span',
        errorClass: 'help-block help-block-error', 
        focusInvalid: false, 
        onkeyup:false,
        ignore: '', 
        messages: {
        	type:{
        		required:'请选择参数类型'
        	},
        	nameZh:{
        		required:'请输入参数名称(中文)',
        		rangelength:jQuery.validator.format('参数名称(中文)长度为{0}和{1}字符之间')
        	},
            nameEn:{
        		required:'请输入参数名称(英文)',
        		rangelength:jQuery.validator.format('参数名称(英文)长度为{0}和{1}字符之间'),
        		remote:'该参数名称已存在，请输入其它参数名称'
        	},
        	value:{
        		required:'请输入参数值',
        	 	rangelength:jQuery.validator.format('参数值必须介于{0}和{1}字符之间')
        	},
        	description:{
        		required:'请输入参数的描述',
        		maxlength:jQuery.validator.format('参数的的最大长度为:{0}'),
        	}
        },
        rules: {
            type:{
                required:true
            },
            nameZh: {
                required:true,
                rangelength:[4,128]
            },
            nameEn:{
            	required: true,
              	rangelength:[4,128],
              	remote:{
              			url:'checkisexitnameen.htm', //后台处理程序
      					    type: 'post',               //数据发送方式
      					    dataType: 'json',           //接受数据格式   
      					    data: {                     //要传递的数据
  					           nameEn: function() {
  					            return $('#nameEn').val();
  					           },
                       id:function(){
                         var id=($('#id').val());
                         if(lion.util.isNotEmpty(id)){
                           return id;
                         }
                         return '';
                       }
					         	}
             }
            },
            value:{
            	required: true,
                rangelength:[4,128]
            },
            description:{
            	required:false,
            	maxlength:512
            }
        },
        invalidHandler: function (event, validator) {             
            addSuccess.hide();
            addError.show();
            Metronic.scrollTo(addError, -200);
        },

        highlight: function (element) {
            $(element).closest('.form-group').addClass('has-error'); 
        },

        unhighlight: function (element) {
            $(element).closest('.form-group').removeClass('has-error'); 
        },
        success: function (label) {
            label.closest('.form-group').removeClass('has-error'); 
        },
        errorPlacement:function(error,element){
        	//当遇到combo的对话框架的时，修改它的显示位置
        	if (element.hasClass('lion-combo')){        	 
        		error.insertAfter(element.parent().find('div.btn-group'));
        	}else{
        		error.insertAfter(element);
        	}
        },
        submitHandler: function (form) {
            addSuccess.show();
            addError.hide();
            submitCallBackfn(vForm);
        }
    });
};
//获取下拉列表数据
/**sys_code_type 加载列表*/
function formatterCodeList(val,row) {
	var codeText='',data=$('#parameterCodeList').combo('getData');
	for (var i in data) {
		if (data[i].codeValue ==val) {
			codeText = data[i].nameZh;
			break;
		}
	}
	return codeText;
}
//判断是否编辑
function formatterEidtable(val,row) {
	var name =$.loin.lang.editable.n;
	if (val) {
		name = $.loin.lang.editable.y;
	}
	return name;
}