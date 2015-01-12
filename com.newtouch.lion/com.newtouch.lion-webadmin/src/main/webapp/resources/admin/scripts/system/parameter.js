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
	//初始化下拉框
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
	 //刷新
	 $("#btnRefresh").on("click",function(){
		 dataGridReload("sys_parameter_lists_tb");
	 });
	 //新增
	 $("#btnAdd").on("click",function(){
		 alert("dd");
	 });
	 //编辑
	 $("#btnEdit").on("click",function(){
		 alert("dd");
	 });
	 //删除
	 $("#btnDelete").on("click",function(){
		  bootbox.setLocale("zh_CN");  
		  bootbox.confirm("确认要删除此记录？", function(result) {
              if(result){
            	  toastr.options = {
            			  "closeButton": true,
            			  "debug": false,
            			  "positionClass": "toast-top-center",
            			  "onclick": null,
            			  "showDuration": "1000",
            			  "hideDuration": "1000",
            			  "timeOut": "5000",
            			  "extendedTimeOut": "1000",
            			  "showEasing": "swing",
            			  "hideEasing": "linear",
            			  "showMethod": "fadeIn",
            			  "hideMethod": "fadeOut"
            			};
            	  toastr.success('Have fun storming the castle!', 'Miracle Max Says');
              }
              else{
            	  toastr.error('I do not think that word means what you think it means.', 'Inconceivable!')
              }
              toastr.warning('My name is Inigo Montoya. You killed my father, prepare to die!')
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
