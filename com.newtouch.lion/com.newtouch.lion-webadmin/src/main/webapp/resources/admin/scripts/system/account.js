$(function () {
	//加载bootstrap
	Metronic.init(); // init metronic core componets
	Layout.init(); // init layout
	Tasks.initDashboardWidget(); // init tash dashboard widget
	lion.util.menu();//加载导航栏

	var $formuser=$('#formuser'),//基本信息
		$formpassword=$('#formpassword');//修改密码

	//基本信息验证表单
	handleVForm($formuser,submitBaseInfoForm);
	//修改密码验证表单
	handlePasswordVForm($formpassword,submitPasswordForm);
	//基本信息保存
	$('#btnBaseInfoSave').click(function(){
		$formuser.submit();
	});
	//基本信息取消-刷新页面
	$('#btnBaseInfoCancel').click(function(){
		location.reload();
	});
	//修改密码
	$('#btnChanagePwdSave').click(function(){
		$formpassword.submit();
	});
	//密码重置
	$('#btnPasswordCancel').click(function(){
		$formpassword.reset();
	});

});
//密码修改
function submitPasswordForm(frm){
	lion.util.post('changepwd.json',frm.serializeObject(),successChangePwdFrm,errorRequest);
}
//修改密码成功回调函数
function successChangePwdFrm(data){
  if(data!==null&&!(data.hasError)){
  	lion.util.success('提示',data.message);
  	$('#formpassword').reset();
  }else if(data!==null&&data.hasError){
  	var gmsg='';
  	for(var msg in data.errorMessage){
  		gmsg+=data.errorMessage[msg];
  	}
  	if(lion.util.isEmpty(gmsg)){
  		gmsg='修改密码失败';
  	}
  	 lion.util.error('提示',gmsg);
  }else{
  	 lion.util.error('提示','修改密码失败');
  }
}
//基本信息修改
function submitBaseInfoForm(frm){
	lion.util.post('changeinfo.json',frm.serializeObject(),successChangeInfoFrm,errorRequest);
}

function successChangeInfoFrm(data){
  if(data!==null&&!(data.hasError)){  
	  //location.reload();
	  lion.util.success('提示',data.message);
	  setInterval(function(){
	  	location.reload();
	  },3000);
  }else if(data!==null&&data.hasError){
  	var gmsg='';
  	for(var msg in data.errorMessage){
  		gmsg+=data.errorMessage[msg];
  	}
  	if(lion.util.isEmpty(gmsg)){
  		gmsg='修改密码失败';
  	}
  	 lion.util.error('提示',gmsg);
  }else{
  	 lion.util.error('提示','修改密码失败');
  }
}
//请求失败后信息
function errorRequest(data,arg){
	lion.util.error('提示','网络连接异常');
}
//修改密码验证表单
handlePasswordVForm=function(vForm,submitCallBackfn){
	var addError = $('.alert-danger', vForm), addSuccess = $('.alert-success',vForm)
	vForm.validate({
        errorElement: 'span',
        errorClass: 'help-block help-block-error', 
        focusInvalid: false, 
        onkeyup:false,
        ignore: '', 
         messages: {
        	oldpassword:{required:'请输入旧密码',rangelength:jQuery.validator.format('旧密码长度为{0}和{1}字符之间'),},
        	password:{required:'请输入新密码',rangelength:jQuery.validator.format('新密码长度为{0}和{1}字符之间')},
        	confirmpassword:{required:'请输入确认密码',rangelength:jQuery.validator.format('确认密码长度为{0}和{1}字符之间'),equalTo:'两次输入的密码不一致,请重新输入'}
        },
        rules: {
        	oldpassword:{required:true,rangelength:[6,30]},
        	password:{required:true,rangelength:[6,30]},
        	confirmpassword:{required:true,rangelength:[6,30],equalTo:'#password'},
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
        	if (element.hasClass('lion-combotree')){
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
//基本信息验证表单
handleVForm=function(vForm,submitCallBackfn){
	vForm.validate({
        errorElement: 'span',
        errorClass: 'help-block help-block-error', 
        focusInvalid: false, 
        onkeyup:false,
        ignore: '', 
      	messages: {
        	realnameZh:{maxlength:'真实姓名(中文)的最大长度为{0}字符'},
        	realnameEn:{maxlength:'真实姓名(中文)的最大长度为{0}字符'},
        },
        rules: {
        	realnameZh:{maxlength:128,},
            realnameEn:{maxlength:128,}
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
        	if (element.hasClass('lion-combotree')){
        		  error.insertAfter(element.parent().find('div.btn-group'));
        	}else{
        		error.insertAfter(element);
        	}
        },
        submitHandler: function (form) {
            submitCallBackfn(vForm);
        }
    });
};