$(function() {
	//加载bootstrap
	Metronic.init(); // init metronic core componets
	Layout.init(); // init layout
	Tasks.initDashboardWidget(); // init tash dashboard widget

	$("#sys_app_property_list_tb").datagrid({
		onLoadSuccess : function(data) {
 
		}
	});
	
	 //重新加载DataGrid
	 function dataGridReload(dataGridId){
		$("#"+dataGridId).datagrid("reload");
	 }
	 //刷新
	 $("#btnRefresh").on("click",function(){
		 dataGridReload("sys_app_property_list_tb");
	 });
	 //新增
	 $("#btnAdd").on("click",function(){
		 return;
	 });
});
