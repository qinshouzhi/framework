var roledg=$('#sys_rolelist_tb'); //datagrids
var addForm=$('#sysRoleForm');  //编辑或添加表单
var addDialog=$('#basic'); //编辑或添加对话框
$(function () {
	//加载bootstrap
	Metronic.init(); // init metronic core componets
	Layout.init(); // init layout
	Tasks.initDashboardWidget(); // init tash dashboard widget

	roledg=$("#sys_rolelist_tb");
	addForm=$('#sysRoleForm');
	addDialog=$('#basic');
	var modalGroupAuth=$('#modalGroupAuth');
	var queryForm=$('#queryform');
  //已关联角色
  var rolegroupdg=$("#rolegroup_list");
  //已关联用户
  var roleuserdg=$("#roleuser_list");
  //授权用户列表
  var authuserdg=$('#authuser_list');
  
  //默认隐藏第一个tab的modal-footer
  modalGroupAuth.find('.modal-footer').hide();
	//绑定tab事件
  modalGroupAuth.find('.nav-tabs a').click(function(){
      var row=roledg.datagrids('getSelected');
      var idObj={'id':row.id};
      var tabHref=$(this).attr('href');
      if(tabHref==='#tab_3_1'){
         modalGroupAuth.find('.modal-footer').hide();
         return;
      }else if(tabHref==='#tab_3_2'){
        modalGroupAuth.find('.modal-footer').show();
      }else if(tabHref==='#tab_3_3'){
        authuserdg.datagrids({querydata:idObj});
        authuserdg.datagrids('reload');
        modalGroupAuth.find('.modal-footer').show();
      }
  });
  
  //用户授权列表创建行调用
  authuserdg.on('datagrids.createdrow',function(row,data,index){
      if(index.hasOwnProperty('groupId')){
           selectedChecked(row,data,index);
      }
  });
   //重新加载数据完成
  authuserdg.on('datagrids.reload',function(){
      rolegroupdg.datagrids('checkselected');
      //roleuserdg.datagrids('checkboxdisabled');
  });
  //创建行回调事件
  roleuserdg.on('datagrids.createdrow',function(row,data,index){
      selectedChecked(row,data,index);
  });
  //创建行内回调事件
  rolegroupdg.on('datagrids.createdrow',function(row,data,index){
      selectedChecked(row,data,index);
  });

  function selectedChecked(row,data,index){
     var $checkbox=$(data).find("td input[type=checkbox]");
     $checkbox.attr('checked',true);
     $checkbox.parent('span').addClass('checked');
  }

	//验证表单
	handleVForm(addForm,submitForm);
	//用户组授权
	$('#btnAuth').click(function(){
      var selectTabId=modalGroupAuth.find('.tab-pane.active').attr('id');
      var row=roledg.datagrids('getSelected');
      if(!row){
        lion.util.info('提示','请选择要授权记录');
        return;
      }
      //打开对话框架
      modalGroupAuth.modal('toggle'); 
      //重新加载 用户组角色数据
      var idObj={'id':row.id};
      rolegroupdg.datagrids({querydata:idObj});
      rolegroupdg.datagrids('reload');
      //重新加载 用户组所关联用户数据 
      roleuserdg.datagrids({querydata:idObj});
      roleuserdg.datagrids('reload');
      
	});
  //重新加载数据完成
  roleuserdg.on('datagrids.reload',function(){
      roleuserdg.datagrids('checkselected');
      //roleuserdg.datagrids('checkboxdisabled');
  });
  //重新加载数据完成
  rolegroupdg.on('datagrids.reload',function(){
      rolegroupdg.datagrids('checkselected');
      //roleuserdg.datagrids('checkboxdisabled');
  });

  //用户组授权保存
  $('#btnAuthSave').click(function(){
      var selectTabId=modalGroupAuth.find('.tab-pane.active').attr('id');
      if(selectTabId==='tab_3_2'){
          console.dir('关联角色');
      }else{
          console.dir('关联用户');
      }
  }); 


	//查询
	$('#btnQuery').click(function(){
		roledg.datagrids({querydata:queryForm.serializeObject()});
		var queryparam=roledg.datagrids('queryparams'); 
		roledg.datagrids('reload');
	});
	//刷新
	$('#btnRefresh').click(function(){		 
        roledg.datagrids('reload');
    });
	//添加
    $('#btnAdd').click(function(){
      	addForm.reset();
	 	    addDialog.find('.modal-header h4 span').text('添加用户组');
		    return;
    });

    //编辑
    $('#btnEdit').click(function(){
        var row=roledg.datagrids('getSelected');
        if(!row){
			 lion.util.info('提示','请选择要编辑记录');
			 return;
		}
		addForm.reset();
		addDialog.find('.modal-header h4 span').text('编辑用户组');
		addDialog.modal('toggle');
		addForm.fill(row);
    });

     //删除
	 $('#btnDelete').on('click',function(){
		 var row=roledg.datagrids('getSelected');
		 if(!row){
			 lion.util.info('提示','请选择要删除记录');
			 return;
		 }
		 bootbox.confirm('确认要删除此记录？', function(result) {
              if(result){            	 
            	  var param={'id':row.id};
                  lion.util.post('delete.json',param,successForDelete,errorRequest);
              }
          }); 
	 });
	 //导出Excel
	 $('#btnExport').on('click',function(){
		 var params=queryForm.serialize(),url='export.json?tableId='+roledg.attr('id');
        if(lion.util.isNotEmpty(params)){
          url+='&'+params;
        }
      	 window.open(url,'_blank');
	 });
	 //保存
	 $('#btnSave').click(function(){
	 		addForm.submit();
	 });

	 //删除成功
	function successForDelete(data,arg){
	   if(data!==null&&!(data.hasError)){
	      lion.util.success('提示',data.message);
	      roledg.datagrids('reload');
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
  	addDialog.modal('toggle');
  	roledg.datagrids('reload');
  }else if(data!==null&&data.hasError){
  	var gmsg='';
  	for(var msg in data.errorMessage){
  		gmsg+=data.errorMessage[msg];
  	}
  	if(lion.util.isEmpty(gmsg)){
  		gmsg='添加用户组出错';
  	}
  	lion.util.error('提示',gmsg);
  }else{
  	lion.util.error('提示','添加用户组失败');
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
          nameZh:{
            required:'请输入角色名称(中文)',
            rangelength:jQuery.validator.format('角色名称(中文)长度为{0}和{1}字符之间')
          },
            nameEn:{
            required:'请输入角色名称(英文)',
            rangelength:jQuery.validator.format('角色名称(英文)长度为{0}和{1}字符之间'),
            remote:'该角色名称已存在，请输入其它角色名称'
          },
          description:{
            required:'请输入角色的描述',
            maxlength:jQuery.validator.format('参数描述的最大长度为:{0}')
          }
        },
        rules: {
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

//测试选择中checkbox
function formatterCheckBox(data,type,full){
  //console.dir(this);
  //console.dir(data);
 // console.dir(type);
  //console.dir(full);
  return data;
}
//判断是否编辑
function formatterEidtable(data,type,full) {
	var name =$.loin.lang.editable.n;
	if (data) {
		name = $.loin.lang.editable.y;
	}
	return name;
}