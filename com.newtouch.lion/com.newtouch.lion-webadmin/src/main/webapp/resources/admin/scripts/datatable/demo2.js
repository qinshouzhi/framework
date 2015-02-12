$(function () {

	Metronic.init(); // init metronic core componets
	Layout.init(); // init layout
	Tasks.initDashboardWidget(); // init tash dashboard widget

	var demoDt=$("#sample_1");

	var queryForm=$('#queryform');

	$("#btnQuery").click(function(){
		demoDt.datagrids({querydata:queryForm.serializeObject()});
		var queryparam=demoDt.datagrids('queryparams'); 
		demoDt.datagrids('reload');
	});

	$("#btnRefresh").click(function(){
		demoDt.datagrids({querydata:{userid:1}});
        demoDt.datagrids('reload');
        //var settings=demoDt.datagrids('settings');
        //console.dir(settings);
    });

    $("#btnAdd").click(function(){
        row=demoDt.datagrids('getSelected');
        console.dir(row);
    });

    //获取多行数据
    $("#btnEdit").click(function(){
        var rows=demoDt.datagrids('getSelections');
       
        console.dir(rows);
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