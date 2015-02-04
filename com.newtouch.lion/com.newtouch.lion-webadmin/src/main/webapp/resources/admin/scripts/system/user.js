$(function() {
	//加载bootstrap
	Metronic.init(); // init metronic core componets
	Layout.init(); // init layout
	Tasks.initDashboardWidget(); // init tash dashboard widget


	var datagridId='#userlist_dt';

	var addForm=$('#addform');
  	var queryForm=$('#queryform');
	var addDialog=$('#basic');

	//验证菜单
	handleVForm(addForm,submitForm);


	$(datagridId).datagrid({onLoadSuccess:function(data) {}});
	
	/**
	 * [查询]
	 */
	 $('#btnQuery').click(function(){
	 	  console.dir(queryForm.serializeObject());
	      $(datagridId).datagrid({queryParams:queryForm.serializeObject()});
	      //重新加载数据
	      dataGridReload(datagridId);
	 });
	 //重新加载DataGrid
	 function dataGridReload(dataGridId){
		$(dataGridId).datagrid("reload");
	 }
	 //刷新
	 $("#btnRefresh").on("click",function(){
		 dataGridReload(datagridId);
	 });
	 
	 $("#btnAdd").on("click",function(){
	 	 addForm.reset();
		 return;
	 });
	 //保存方法
	$('#btnSave').click(function(){
	 		addForm.submit();
	 });
	 //导出Excel
	 $('#btnExport').on('click',function(){
	   var params=queryForm.serialize(),url='export.json?tableId='+$(datagridId).attr('id');
       var options=$(datagridId).datagrid('options');       
       if(options.hasOwnProperty('sortName')&&lion.util.isNotEmpty(options.sortName)){
           url+='&sort='+options.sortName;
       }
       if(options.hasOwnProperty('sortOrder')&&lion.util.isNotEmpty(options.sortOrder)){
          url+='&order='+options.sortOrder;
       }
       if(lion.util.isNotEmpty(params)){
          url+='&'+params;
       }
       window.open(url,'_blank');
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
  //TODO
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
	var addError = $('.alert-danger', vForm), addSuccess = $('.alert-success',vForm),context=lion.util.context;
	var checkusernameUrl=context+'/system/useraccount/checkusername.json';
	var checkEmployeeCodeUrl=context+'/system/useraccount/checkemployeecode.json';
	var checkEmailUrl=context+'/system/useraccount/checkemail.json';
	vForm.validate({
        errorElement: 'span',
        errorClass: 'help-block help-block-error', 
        focusInvalid: false, 
        onkeyup:false,
        ignore: '', 
        messages: {
        	username:{
        		required:'请输入用户名',
        		rangelength:jQuery.validator.format('用户名长度为{0}和{1}字符之间'),
        		remote:'该用户名已存在',

        	},
        	employeeCode:{
        		required:'请输入员工号',
        		rangelength:jQuery.validator.format('员工号长度为{0}和{1}字符之间'),
        		remote:'该员工号已存在'
        	},
        	email:{
        		required:'请输入邮箱',
        		email:'请输入正确格式的邮箱',
        		maxlength:jQuery.validator.format('邮箱的长度为{0}字符'),
        		remote:'该邮箱已存在'
        	},
        	departmentId:{required:'请选择部门'}
        },
        rules: {
            username:{
                required:true,
                rangelength:[4,30],
              	remote:{
              			url:checkusernameUrl, //后台处理程序
  					    type:'post',               //数据发送方式
  					    dataType:'json',           //接受数据格式   
  					    data: {                     //要传递的数据
					           nameEn: function() {
					            return $('#usename').val();
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
            employeeCode: {
                required:true,
                rangelength:[4,30],
                remote:{
              			url:checkEmployeeCodeUrl, //后台处理程序
  					    type:'post',               //数据发送方式
  					    dataType:'json',           //接受数据格式   
  					    data: {                     //要传递的数据
					           nameEn: function() {
					            return $('#employeeCode').val();
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
            email:{
            	required:true,
            	email:true,
            	maxlength:128,
            	remote:{
              			url:checkEmailUrl, //后台处理程序
  					    type:'post',               //数据发送方式
  					    dataType:'json',           //接受数据格式   
  					    data: {                     //要传递的数据
					           nameEn: function() {
					            return $('#email').val();
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
            departmentId:{
            	required:true
            },
        },
        invalidHandler: function (event, validator) {             
            addSuccess.hide();
            addError.show();
            Metronic.scrollTo(addError, -200);
        },

        highlight: function (element) {
            $(element).closest('.form-filed').addClass('has-error'); 
        },

        unhighlight: function (element) {
            $(element).closest('.form-filed').removeClass('has-error'); 
        },
        success: function (label) {
            label.closest('.form-filed').removeClass('has-error'); 
        },
        errorPlacement:function(error,element){
        	//当遇到combo的对话框架的时，修改它的显示位置
        	if (element.hasClass('lion-combotree')){
        	    console.dir('dddd'); 
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
//判断是否编辑
function formatterEidtable(val,row) {
	var name =$.loin.lang.editable.n;
	if (val) {
		name = $.loin.lang.editable.y;
	}
	return name;
}
//加载部门
function formatterShowDepartment(val, row) {
	var name = "";
	if (val) {
		name = val.nameZh;
	}
	return name;
}
