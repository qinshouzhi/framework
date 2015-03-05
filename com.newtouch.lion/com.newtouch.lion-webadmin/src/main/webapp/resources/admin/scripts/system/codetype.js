var codetypedg=$('#sys_codetype_lists_tb'); //datagrids
var addForm=$('#sysCodeTypeForm');  //编辑或添加表单
var addDialog=$('#basic'); //编辑或添加对话框
var user_perms={unlogin_code:'998',unauth_code:'999',loginurl:'/login.htm'};
$(function () {
  //加载bootstrap
  Metronic.init(); // init metronic core componets
  Layout.init(); // init layout
  Tasks.initDashboardWidget(); // init tash dashboard widget
  lion.util.menu();//加载导航栏

  codetypedg=$("#sys_codetype_lists_tb");
  addForm=$('#sysCodeTypeForm');
  addDialog=$('#basic');
  var queryForm=$('#queryform');

  //验证表单
  handleVForm(addForm,submitForm);
  
  //查询
  $('#btnQuery').click(function(){
    codetypedg.datagrids({querydata:queryForm.serializeObject()});
    var queryparam=codetypedg.datagrids('queryparams'); 
    codetypedg.datagrids('reload');
  });
  //刷新
  $('#btnRefresh').click(function(){     
        codetypedg.datagrids('reload');
    });
  //添加
    $('#btnAdd').click(function(){
      addForm.reset();
      addDialog.find('.modal-header h4 span').text('添加编码类型');
      return;
    });

    //编辑
    $('#btnEdit').click(function(){
        var row=codetypedg.datagrids('getSelected');
        if(!row){
       lion.util.info('提示','请选择要编辑记录');
       return;
    }
    addForm.reset();
    addDialog.find('.modal-header h4 span').text('编辑编码类型');
    addDialog.modal('toggle');
    addForm.fill(row);
    });

     //删除
   $('#btnDelete').on('click',function(){
     var row=codetypedg.datagrids('getSelected');
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
     var params=queryForm.serialize(),url='export.json?tableId='+codetypedg.attr('id');
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
        codetypedg.datagrids('reload');
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
    codetypedg.datagrids('reload');
  }else if(data!==null&&data.hasError){
    var gmsg='';
    for(var msg in data.errorMessage){
      gmsg+=data.errorMessage[msg];
    }
    if(lion.util.isEmpty(gmsg)){
      gmsg='添加编码类型出错';
    }
    lion.util.error('提示',gmsg);
  }else{
    lion.util.error('提示','添加编码类型失败');
  }
}
//请求失败后信息
function errorRequest(xhr,status,error){  
  //未登录成功处理方法
  if(error===user_perms.unlogin_code){
      bootbox.alert("您未登录到信息，点击“确定”后，将进入用户登录页面", function() {
          lion.util.reload();
      });
  }else if(error===user_perms.unauth_code){
      lion.util.warning('提示','你的访问功能未授权');
  }else{
      lion.util.error('提示','网络连接异常');
  }
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
            required:'请输入通用编码类型',
            maxlength:jQuery.validator.format('通用编码类型的最大长度为:{0}'),
          },
          nameZh:{
            required:'请输入通用编码类型名称(中文)',
            rangelength:jQuery.validator.format('通用编码类型名称(中文)长度为{0}和{1}字符之间')
          },
            nameEn:{
            required:'请输入通用编码类型名称(英文)',
            rangelength:jQuery.validator.format('通用编码类型名称(英文)长度为{0}和{1}字符之间'),
            remote:'该通用编码类型名称已存在，请输入其它参数名称'
          },
          codeLenLimit:{
            required:'请输入编码参数值长度',
            number:jQuery.validator.format('编码参数值长度必须为数字')
          }
        },
        rules: {
            type:{
                required:true,
                maxlength:20
            },
            nameZh: {
                required:true,
                rangelength:[1,128]
            },
            nameEn:{
              required: true,
                rangelength:[1,128],
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
            codeLenLimit:{
              required: true,
              number:true 
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
return data;
}

//获取下拉列表数据
/**sys_code_type 加载列表*/
function formatterCodeList(val,row) {
  var codeText='',data=$('#CodeTypeList').combo('getData');
  for (var i in data) {
    if (data[i].id ==val) {
      codeText = data[i].nameZh;
      break;
    }
  }
  return codeText;
}
//判断是否编辑
function formatterEidtable(data,type,full) {
  var name =$.lion.lang.editable.n;
  if (data) {
    name = $.lion.lang.editable.y;
  }
  return name;
}

//将JSON复杂对象显示到DataGird中
function formatterName(val, row) {
	var name = "";
	if (val !==null) {
		name = val.nameZh;
	}
	return name;
}
