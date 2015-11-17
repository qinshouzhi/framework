$(function(){
	$('#button').click(function(){
		console.dir("heh");
		alert('heh');
	});
	
$('#go').click(function(){
	$.get("/angular/user/index.json",function(data){
		console.dir('data:'+data);
	},
	"json");
});
}
);