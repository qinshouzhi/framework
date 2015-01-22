$(function() {
	//加载bootstrap
	Metronic.init(); // init metronic core componets
	Layout.init(); // init layout
	Tasks.initDashboardWidget(); // init tash dashboard widget
	
	var datagridId='#sys_parameter_lists_tb';

	var addForm=$("#sysParameterForm");
	var addDialog=$("#basic");
	handleVForm(addForm);

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
		 dataGridReload($.sys.parameter.datagridId);
	 });
	 //新增
	 $('#btnAdd').on('click',function(){
		 addForm.reset();
		 return;
	 });

	 addForm.on('show.bs.modal',function(){

	 });
	 
	 $('#editDialog').on('hidden.bs.modal', function() {
		    $(this).removeData('bs.modal');
	 });


	 $('#btnSave').click(function(){

	 });
	 //编辑
	 $('#btnEdit').on('click',function(){
		 console.log('进入BtnEdit function');
		 var  loadPageUrl='/admin/system/parameter/dialogedit.htm?timestamp=' +(new Date()).getTime();
		 modalDialog=new lion.ui.dialog(
		 {id:'addDialog',
			title:"编辑系统参数",
			titleIcon:'fa-plus bule',
			btnalign:'center',
			loadurl:loadPageUrl,
			backdrop:'true',
			buttons:[
	    			{id:'btnCancel',value:' 取 消 ',dismiss:'true',icon:'fa fa-save'},
	    			{id:'btnSave',headler:function(){ },icon:'fa fa-save',className:'btn blue',value:' 确 认 ',dismiss:false},
		 			]
		  });
		  
	 });
	 //删除
	 $('#btnDelete').on('click',function(){
		 var row=getSelectedRow();
		 if(!row){
			 $.lionui.notice.info('提示','请选择要删除记录');
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
            	  $.lionui.notice.success('提示!', '已删除成功');
              }
          }); 
	 });
	 //导出Excel
	 $('#btnExport').on('click',function(){
		 alert('dd');
	 });
});
//验证表单
handleVForm=function(vForm){
	vForm.validate({
        errorElement: 'span', //default input error message container
        errorClass: 'help-block help-block-error', // default input error message class
        focusInvalid: false, // do not focus the last invalid input
        ignore: "",  // validate all fields including form hidden input
        messages: {
        	addParameterCodeList:{
        		required:'请选择参数类型'
        	},
        	nameZh:{
        		required:'请输入参数名称(中文)',
        		range:jQuery.validator.format("参数名称(中文)长度为{0}和{1}字符之间")
        	},
            nameEn:{
        		required:'请输入参数名称(英文)',
        		range:jQuery.validator.format("参数名称(英文)长度为{0}和{1}字符之间")
        	},
        	value:{
        		required:'请输入参数值',
        	 	range:jQuery.validator.format("参数值必须介于{0}和{1}字符之间")
        	},
        	description:{
        		required:'请输入参数的描述',
        		maxlength: jQuery.validator.format("参数的的最大长度为:{0}"),
        	}
        },
        rules: {
            addParameterCodeList: {
                required: true
            },
            nameZh: {
                required: true,
                range:[4,128]
            },
            nameEn:{
            	required: true,
              	range:[4,128]
            },
            value:{
            	required: true,
                range:[4,128]
            },
            description:{
            	required:false,
            	maxlength:512
            }
        },
        invalidHandler: function (event, validator) {             
            success1.hide();
            error1.show();
            Metronic.scrollTo(error1, -200);
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
        submitHandler: function (form) {
            success1.show();
            error1.hide();
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