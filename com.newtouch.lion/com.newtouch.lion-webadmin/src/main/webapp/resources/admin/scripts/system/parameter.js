$(document).ready(function() {
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
	
	handleBootstrapSelect();

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
		 dataGridReload("sys_parameter_lists_tb");
	 });
	 var data = [];
	 $("#sysParameterCodeList option").each(function () {
		 var row = {};
         var codeText = $(this).text(); //获取单个text
         var codeValue = $(this).val(); //获取单个value
         row.codeText=codeText;
         row.codeValue=codeValue;
         data.push(row);
     });
});


/**sys_code_type 加载列表*/
function formatterCodeList(val,row) {
	var nameZh = "中文";
	return nameZh;
}
