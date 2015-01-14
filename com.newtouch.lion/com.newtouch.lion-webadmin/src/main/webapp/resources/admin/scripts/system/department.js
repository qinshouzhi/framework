$(function() {
	//加载bootstrap
	Metronic.init(); // init metronic core componets
	Layout.init(); // init layout
	Tasks.initDashboardWidget(); // init tash dashboard widget

	$("#sys_department_lists").datagrid({
		onLoadSuccess : function(data) {
 
		}
	});
	
	 //重新加载DataGrid
	 function dataGridReload(dataGridId){
		$("#"+dataGridId).datagrid("reload");
	 }
	 //刷新
	 $("#btnRefresh").on("click",function(){
		 dataGridReload("sys_department_lists");
	 });
	 
	 $("#btnAdd").on("click",function(){
		 alert("dd");
	 });	
});
