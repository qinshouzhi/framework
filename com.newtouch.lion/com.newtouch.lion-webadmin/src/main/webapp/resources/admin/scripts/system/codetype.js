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
	$.sys.codetype={
			querybtn:"queryBtn",
			datagridId:"sys_codetype_lists_tb",
			dialogId:"sys_codetype_dialogData",
			formId:"sys_codetype_data_form",
			id:"sys_codetype_data_form_id",
			type:"sys_codetype_form_type",
			btn:{add:"btnAdd",save:"#btnSave",edit:"#btnEdit",remove:"#btnDelete",reload:"#btnRefresh",exportbtn:"#btnExport"}
		};
	})(jQuery);
	//选择DataGrid单行
	function getSelectedRow(){return $("#"+$.sys.codetype.datagridId).datagrid("getSelected");}
	
	$("#"+$.sys.codetype.datagridId).datagrid({
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
		 dataGridReload("sys_codetype_lists_tb");
	 });
	 
	 $("#btnAdd").on("click",function(){
		 alert("dd");
	 });
	
});
//获取下拉列表数据
var data = [];
$("#sysCodeTypeList option").each(function () {
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
