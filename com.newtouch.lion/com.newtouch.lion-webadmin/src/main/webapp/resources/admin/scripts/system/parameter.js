$(document).ready(function() {
	//加载bootstrap
	Metronic.init(); // init metronic core componets
	Layout.init(); // init layout
	Tasks.initDashboardWidget(); // init tash dashboard widget

	$("#sys_parameter_lists_tb").datagrid({
		onLoadSuccess : function(data) {
			//Metronic.init(); // init metronic core componets
		}
	});
	
	 //重新加载DataGrid
	 function dataGridReload(dataGridId){
		$("#"+dataGridId).datagrid("reload");
	 }
	 
	 $("#btnRefresh").on("click",function(){
		 $("#sys_parameter_lists_tb").datagrid("reload");
	 });
});