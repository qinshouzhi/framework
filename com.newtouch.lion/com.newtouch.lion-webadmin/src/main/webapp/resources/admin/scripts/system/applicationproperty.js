$(function() {
	//加载bootstrap
	Metronic.init(); // init metronic core componets
	Layout.init(); // init layout
	Tasks.initDashboardWidget(); // init tash dashboard widget
	
	var datagridId='#sys_app_property_list_tb';
	var addForm=$('#sysAppPropertyForm');
	var addDialog=$('#basic');
	var queryForm=$('#queryform');
	
	handleVForm(addForm,submitForm);
	//选择DataGrid单行
	function getSelectedRow(){return $(datagridId).datagrid('getSelected');}
	 
	$(datagridId).datagrid({
		 onLoadSuccess : function(data) {
		}
	});
	
	/**
	 * [查询]
	 */
	 $('#btnQuery').click(function(){
		 var queryParams = $(datagridId).datagrid('options').queryParams;
		 var params=queryForm.serializeObject();
	      $.extend(queryParams,params);
	      //重新加载数据
	      dataGridReload(datagridId);
	
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
		  addDialog.find('.modal-header h4 span').text('添加项目属性配置');
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
		 addForm[0].reset();
	     addForm.find('.form-group').removeClass('has-error');
	     addForm.find('.help-block').remove();
	     addDialog.find('.modal-header h4 span').text('编辑项目属性配置');
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
            	  var param={'id':row.id};
                lion.util.post('delete.json',param,successForDelete,errorRequest);
            	  //lion.util.success('提示!', '已删除成功');
              }
          }); 
	 });
	//导出Excel
	 $('#btnExport').on('click',function(){
		   var params=queryForm.serialize(),url='export.json?tableId='+$(datagridId).attr('id');
       if(lion.util.isNotEmpty(params)){
          url+='&'+params;
       }
       window.open(url,"_blank");
	 });
});

function successForDelete(data,arg){
   if(data!==null&&!(data.hasError)){
      lion.util.success('提示',data.message);
      $('#sys_rolelist_tb').datagrid('reload');
   }else if(data!==null&&data.hasError){
      var gmsg='';
      for(var msg in data.errorMessage){
        gmsg+=data.errorMessage[msg];
      }
      if(lion.util.isEmpty(gmsg)){
        gmsg='未删除成功';
      }
      lion.util.error('提示',gmsg);
  }
}
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
  //TODO
  if(data!==null&&!(data.hasError)){
  	lion.util.success('提示',data.message);
  	$('#basic').modal('toggle');
  	$('#sys_rolelist_tb').datagrid('reload');
  }else if(data!==null&&data.hasError){
  	var gmsg='';
  	for(var msg in data.errorMessage){
  		gmsg+=data.errorMessage[msg];
  	}
  	if(lion.util.isEmpty(gmsg)){
  		gmsg='添加角色出错';
  	}
  	lion.util.error('提示',gmsg);
  }else{
  	lion.util.error('提示','添加角色失败');
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
        	appId:{
        		required:'请输入appId',
        		maxlength:jQuery.validator.format('appId的最大长度为:{0}')
        	},
        	key:{
        		required:'请输入键',
        		rangelength:jQuery.validator.format('键的长度为{0}和{1}字符之间')
        	},
        	value:{
        		required:'请输入值',
        		rangelength:jQuery.validator.format('值的长度为{0}和{1}字符之间')
        	},
        	description:{
        		required:'请输入描述',
        		maxlength:jQuery.validator.format('描述的最大长度为:{0}')
        	}
        },
        rules: {
            appId: {
                required:true,
                maxlength:20
            },
            key: {
            	required:true,
            	rangelength:[4,120]
            },
            value:{
            	required: true,
              	rangelength:[4,120]
            },
            description:{
            	required:false,
            	maxlength:255
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