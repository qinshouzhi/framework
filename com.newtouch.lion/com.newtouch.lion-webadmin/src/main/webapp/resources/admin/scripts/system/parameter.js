$(function() {
	//加载bootstrap
	Metronic.init(); // init metronic core componets
	Layout.init(); // init layout
	Tasks.initDashboardWidget(); // init tash dashboard widget
	var handleBootstrapSelect = function() {
	        $('.bs-select').selectpicker({
	            iconBase: 'fa',
	            tickIcon: 'fa-check'
	        });
	}
	//初始化下拉框
	handleBootstrapSelect();
	(function($){
		$.sys.parameter={
				querybtn:"queryBtn",
				datagridId:"sys_parameter_lists_tb",
				dialogId:"sys_parameter_dialogData",
				formId:"sys_parameter_data_form",
				id:"sys_parameter_data_form_id",
				type:"sys_parameter_form_type",
				btn:{add:"btnAdd",save:"#btnSave",edit:"#btnEdit",remove:"#btnDelete",reload:"#btnRefresh",exportbtn:"#btnExport"}
		};
	})(jQuery);
	//选择DataGrid单行
	function getSelectedRow(){return $("#"+$.sys.parameter.datagridId).datagrid("getSelected");}
	 
	$("#"+$.sys.parameter.datagridId).datagrid({
		onLoadSuccess : function(data) {
			//Metronic.init(); // init metronic core componets
		}
	});
	
	 //重新加载DataGrid
	 function dataGridReload(dataGridId){
		$("#"+dataGridId).datagrid("reload");
	 }
	 //刷新
	 $("#btnRefresh").on("click",function(){
		 dataGridReload($.sys.parameter.datagridId);
	 });
	 //新增
	 $("#btnAdd").on("click",function(){
		 return;
	 });
	 //编辑
	 $("#btnEdit").on("click",function(){
		 var editModal = $("#editDialog");
		 console.log("111");
		 editModal.modal({
				keyboard : false
			});
		 console.log("222");
		 var  url="/admin/system/parameter/dialogedit.htm";
		 console.log("3333");
		 editModal.load(url, function() {
			    editModal.modal("show");
				$("#btnEdit").removeAttr("disabled");
		  });
		 console.log("444");
		 return;
	 });
	 //删除
	 $("#btnDelete").on("click",function(){
		 var row=getSelectedRow();
		 if(!row){
			 $.lionui.notice.info("提示","请选择要删除记录");
			 return;
		 }
		 bootbox.confirm("确认要删除此记录？", function(result) {
              if(result){
            	 
            	  var ps = "?id="+row.id;
					//$.post("delete.htm" + ps, function(data) {
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
	 $("#btnExport").on("click",function(){
		 alert("dd");
	 });
});
//获取下拉列表数据
var data = [];
$("#sysParameterCodeList option").each(function () {
	var row = {};
    var codeText = $(this).text(); //获取单个text
    var codeValue = $(this).val(); //获取单个value
    row.codeText=codeText;
    row.codeValue=codeValue;
    data.push(row);
});
/**sys_code_type 加载列表*/
function formatterCodeList(val,row) {
	var codeText="";
	for ( var obj in data) {
		if (data[obj].codeValue ===val) {
			codeText = data[obj].codeText;
			break;
		}
	}
	return codeText;
}