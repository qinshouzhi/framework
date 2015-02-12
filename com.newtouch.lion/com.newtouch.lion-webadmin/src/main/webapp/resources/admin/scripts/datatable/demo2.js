$(function () {

	var demoDt=$("#sample_1");

	 var queryForm=$('#queryform');

	$("#btnQuery").click(function(){
		demoDt.datagrids({querydata:queryForm.serializeObject()});
		var queryparam=demoDt.datagrids('queryparams'); 
		demoDt.datagrids('reload');
	});
});

//部门显示方法
function formatterDarptment(data,type,full){
	//console.dir(data);
	if(data){
		return data.nameZh;
	}
	return '';
}