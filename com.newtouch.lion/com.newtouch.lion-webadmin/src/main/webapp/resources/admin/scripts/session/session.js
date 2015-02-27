$(function () {
		//加载bootstrap
	Metronic.init(); // init metronic core componets
	Layout.init(); // init layout
	Tasks.initDashboardWidget(); // init tash dashboard widget
  	lion.util.menu();//加载导航栏
});

//判断是否编辑
function formatterEidtable(val,row) {
	var name =$.loin.lang.editable.n;
	if (val) {
		name = $.loin.lang.editable.y;
	}
	return name;
}
//超时时间算法
function formatterTimeout(val,row){
	if(val){
		return (val/1000/60);
	}
	return '';
}