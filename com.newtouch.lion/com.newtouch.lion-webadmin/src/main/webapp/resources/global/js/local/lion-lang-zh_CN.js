var Eidtable_Y="是";
var Eidtable_N="否";
var gender_male="男";
var gender_female="女";
;(function($){
	'use strict';// js hint ;_;
	this.lang = this.lang || {}; //定义ui对象。为避免覆盖如果存在ui对象则使用，不存在则新建
	var exports = this.lang;
	var editable={y:'是',n:'否'};

	//exports.editable=editable;

  	exports.datatables={
  			 language: {
                "aria": {
                    "sortAscending": ": activate to sort column ascending",
                    "sortDescending": ": activate to sort column descending"
                },
                "emptyTable": "No data available in table",
                "info": "Showing _START_ to _END_ of _TOTAL_ entries",
                "infoEmpty": "No entries found",
                "infoFiltered": "(filtered1 from _MAX_ total entries)",
                "lengthMenu": "Show _MENU_ entries",
                "search": "Search:",
                "zeroRecords": "No matching records found"
            }
  	};

	$.lion={lang:'lang',common:'common',datatables:'datatables'};
	$.lion.lang={
		editable:{ y:"是",n:"否"},
		gender:{m:"男",f:"女"},
		common:{
			messager:{info:"提示",error:"错误",warn:"警告"},
			locked:"窗口已经锁定，是否需要重新登录？",
			againlogin:"您确定要重新登录吗？",
			exit:"您确定要退出系统吗？",
			user:{
				myinfo:"我的信息",
				mpwd:"修改密码"
			}
		}
	};
	 
}).call(lion,jQuery);