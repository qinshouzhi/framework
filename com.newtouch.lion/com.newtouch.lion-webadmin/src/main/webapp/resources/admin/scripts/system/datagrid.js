$(function() {
	//加载bootstrap
	Metronic.init(); // init metronic core componets
	Layout.init(); // init layout
	Tasks.initDashboardWidget(); // init tash dashboard widget

	$("#datagrid_dt").datagrid({
		onLoadSuccess : function(data) {
 
		}
	});
	
	 //重新加载DataGrid
	 function dataGridReload(dataGridId){
		$("#"+dataGridId).datagrid("reload");
	 }
	 //刷新
	 $("#btnRefresh").on("click",function(){
		 dataGridReload("datagrid_dt");
	 });
	 
	 $("#btnAdd").on("click",function(){
		 alert("dd");
	 });	
});
