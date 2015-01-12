$(document).ready(function() {
	//加载bootstrap
	Metronic.init(); // init metronic core componets
	Layout.init(); // init layout
	Tasks.initDashboardWidget(); // init tash dashboard widget

	$("#sys_role_lists_tb").datagrid({
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
		 dataGridReload("sys_role_lists_tb");
	 });
	 
	 $("#btnAdd").on("click",function(){
		 alert("dd");
	 });	
});
//获取下拉列表数据
var data = [];
$("#sysRoleCodeList option").each(function () {
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
