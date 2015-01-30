/**
 * Newtouch Lion UI 0.1
 * 
 * Copyright (c) 2014 www.newtouch.com. All rights reserved.
 *
 * Licensed under the GPL license: http://www.gnu.org/licenses/gpl.txt
 * To use it on other terms please contact us at lijun.wang1@newtouch.cn
 */
/**
 * @author wanglijun
 * @date 2015-01-19
 * lion combotree lib - jQuery Lion UI
 * 下列树型列表
 * Dependencies js:
 * jQuery.js bootstrap.js lion.js jquery.ztree.all-3.5.min.js
 * Depedencies css
 * bootstrap.css bootstrap-select.css  lion.css,combotree.css (Bootstrap v3.3.1)
 */
;(function($){
	 'use strict';// js hint ;_;
	 this.ui = this.ui || {}; //定义ui对象。为避免覆盖如果存在ui对象则使用，不存在则新建
	 var util = this.util, //用变量存储util.js中的方法，方便调用
	       exports = this.ui, //用变量存储ui下的方法，可直接使用此变量追加组件。如不这样需要this.ui.add追加
	       _version = this.version,//组件版本号
	       _id = 0,//id
	       _catchPrefix = 'ui-combotree-',//组件缓存对象前缀
	       _idPrefix = this.namespace + '-combotree-',//自定义id前缀
	       events = {};//按钮事件缓存区
	   //默认HTML模板
	   var templates={
	   		container:'<div class="btn-group lion-combotree bootstrap-select form-control  input-small"><div>',
	   		button:'<button class="btn  input-small form-control dropdown-toggle">'+
					    '<span class="pull-left"></span>'+
						'<i class="caret"></i>'+
					'</button>',
			bodyTree:'<div class="content"><ul  class="ztree"><li>ddd<li></ul></div>'
	   };
	   //临时参数
	   var zNodesSelect =[
            {id:1, pId:0, name:"北京"},
            {id:2, pId:0, name:"天津"},
            {id:3, pId:0, name:"上海"},
            {id:6, pId:0, name:"重庆"},
            {id:4, pId:0, name:"河北省", open:true},
            {id:41, pId:4, name:"石家庄"},
            {id:42, pId:4, name:"保定"},
            {id:43, pId:4, name:"邯郸"},
            {id:44, pId:4, name:"承德"},
            {id:5, pId:0, name:"广东省", open:true},
            {id:51, pId:5, name:"广州"},
            {id:52, pId:5, name:"深圳"},
            {id:53, pId:5, name:"东莞"},
            {id:54, pId:5, name:"佛山"},
            {id:6, pId:0, name:"福建省", open:true},
            {id:61, pId:6, name:"福州"},
            {id:62, pId:6, name:"厦门"},
            {id:63, pId:6, name:"泉州"},
            {id:64, pId:6, name:"三明"}
         ];
	   //默认参数
	   var defaults={
	       //控件ID
	       id:'',
	       //版本
	       version:_version,
	       //提示信息
	       title:'请选择...',
	       btnClass:'',
	       //zTree参数设置
	       setting:{view: {dblClickExpand: false},
		       			data: {
		                	simpleData: {
		                    enable: true
		               		}
	            		},
			            callback: {
			                //beforeClick:beforeClick,
			                //onClick: onClick
			            }
		        	},
	       //zTree的数据
	       dateNodes:zNodesSelect,
	       //下拉列表的高度 auto||300px;
	       height:'300px',
	       //下拉列表宽度
	       width:'auto',
	       //单项选择
	       singleSelect:true,
	       //远程加载的URL
           loadurl:'',
           datakey:'selectednodetid'

	   };

	   //组件函数
	   var Combotree=function combotree(element,options,e){

	   	  //参数选项数据
          this.options = options;
           //节点
          this.$element = $(element);
          //ID
          this.id=this.$element.attr('id');
          //隐藏原控件
          this.$element.hide();
           //新的点
          this.$newElement =null;
          //按钮
          this.$button = null;
          //下拉列表
          this.$content=null;
          //树数据
          this.$tree=null;
          //树对象
          this.$treeObj=null;
          //初始化          
          this.init();         
	   };	
	   //版本数据
	   Combotree.version=defaults.version;

	   //创建Combox对象
	   Combotree.prototype = {
	        //设置构造函数
	        constructor: Combotree,
	        //设置初始化方法
	        init:function(){
	        	//创建下拉列表选择列表(ul li)
	            this.$newElement = this.buildView();
	            //下拉列表
	            this.$element.after(this.$newElement);
	            //下拉列表DIV
	            this.$content=this.$newElement.find('.content');
	            //布局
	            this.layout();
	            //绑定事件
	            this.clickListener();
	            //设置标题
	            this.setTitle();
	            //设置Tree树型结构
	            this.setTree();
	            //初始化默认选择
	            this.defalutVal();
	        },
	        buildView:function(){
	        	var btnClass=this.options.btnClass,
	        		container=$(templates.container),
	        		btnSize=this.$element.parents().hasClass('form-group-lg') ? ' btn-lg' : (this.$element.parents().hasClass('form-group-sm') ? ' btn-sm' : '');
	        	if(!container.hasClass(btnSize)){
	        		container.addClass(btnSize);
	        	}
	        	//下拉按钮
	        	container.append(templates.button);
	        	this.$button=container.find('button');
	        	if(btnClass&&!this.$button.hasClass(btnClass)){
	        		 this.$button.addClass(btnClass);
	        	}
	        	this.$button.attr('id','btn'+this.id);
	        	//树型菜单下拉列表
	        	container.append(templates.bodyTree);
	        	this.$tree=$(container.find('ul'));
	        	this.$tree.attr('btn','btn'+this.id);
	        	this.$tree.attr('combotree',this.id);
	        	this.$tree.attr('id','tree'+this.id);
	        	return container;
	        },
	        setTitle:function(){
	        	var title=this.$element.attr('placeholder')||this.options.title;
	        	this.$button.find('span').text(title);
	        },
	        clickListener:function(){
	        	var $btn=this.$button,that=this;
	        	$btn.click(function(){
	        		 that.show();
	        	});
	        },
	        show:function(){
	   	       this.$content.slideDown('fast');
	        },
	        hide:function(){
			  this.$content.fadeOut('fast');
	        },
	        //默认选中值
	        defalutVal:function(){
	        	var id=this.$element.val();
	        	if(util.isNotEmpty(id))
	        		this.val(id);
	        },
	        //获取数据或设置选中的数据
	        val:function(id){
	        	//当ID等于空的时候，可以
	        	if(this.$treeObj===null){
	        		return;
	        	}
	        	 if(util.isEmpty(id)){
	        		var valueId='',nodes=this.$treeObj.getSelectedNodes();
	        		for (var i=0, l=nodes.length; i<l; i++) {
		                v += nodes[i].name + ',';
		                valueId=nodes[i].id;
		            }
		            
		            return valueId;
	        	}else{	        		 
	        		var node =this.$treeObj.getNodeByParam('id',id);
					this.$treeObj.selectNode(node,true);
					$('#'+node.tId+'_a').click();
	        	}	
	        },
	        setTree:function(){
	        	var tree=this.$tree,setting=this.options.setting,nodes=zNodesSelect,that=this;
	        	setting.callback={
	        		beforeClick:that.treeBeforeClick,
	        		onClick:that.treeOnClick
	        	};
	        	$.fn.zTree.init(tree,setting,nodes);
	        	var treeId=this.$tree.attr('id');
	        	this.$treeObj=$.fn.zTree.getZTreeObj(treeId);
	        },
	        treeOnClick:function(e, treeId, treeNode){
	        	var zTree = $.fn.zTree.getZTreeObj(treeId);
	        	if(!zTree){
	        		return;
	        	}
	        	var tree=$('#'+treeId),
        				ipnutId=tree.attr('combotree'),
        				btnId=tree.attr('btn'),
        				$btn=$('#'+btnId),
	        	 		$inputText=$('#'+ipnutId),
	        	  		nodes = zTree.getSelectedNodes(),
	        	  		v = '',valueId='';
            	 	
		            nodes.sort(function compare(a,b){return a.id-b.id;});
		            for (var i=0, l=nodes.length; i<l; i++) {
		                v += nodes[i].name + ',';
		                valueId=nodes[i].id;
		            }
		            if (v.length > 0 ) v = v.substring(0, v.length-1);
		            $btn.find('span').text(v);		          
		            $inputText.val(valueId);		            

	        },
	        treeBeforeClick:function(treeId,treeNode){
	        
	        	 var $tree=$('#'+treeId),
	        	 			ipnutId=$tree.attr('combotree'),
	        	 			btnId=$tree.attr('btn'),
	        	 			$btn=$('#'+btnId),
	        	 			$inputText=$('#'+ipnutId),
	        	 			tid=treeNode.tId||'';
	        	 var selectedTid=$btn.attr('selectedTid')||'0';
	        	 if(tid===selectedTid){
	        	 	var zTree = $.fn.zTree.getZTreeObj(treeId);
	        	 	zTree.cancelSelectedNode(treeNode);
	        	 	$btn.attr('selectedTid','0');	        	  
	        	 	$btn.find('span').html($inputText.attr('placeholder'));
	        	 	$inputText.val('');
	        	 	return false;
	        	 }else{
	        	 	$btn.attr("selectedTid",tid);
	        	 }
	        	  
	        },
	        //设置位置及布局
	       	layout:function(){
	       		var that = this,pos=this.$element.offset(),actualHeight=this.$button.outerHeight(),thatpos=this.$newElement.offset();
	       		this.$newElement.css({
                'top':'0px',
                'width':this.$element[0].offsetWidth,
                'height':actualHeight+'px',
                'position':'absolute'
                });
                actualHeight=this.$button.outerHeight();
                var outerWidth=this.$button.outerWidth();
                if(this.$content.outerWidth()>this.$button.outerWidth()){
                	outerWidth=this.$content.outerWidth();
                }
                this.$content.css({top:actualHeight,width:outerWidth});

                $(window).resize(function () {
                      //getPlacement(that.$newElement);
                });

                $(window).on('scroll', function () {
                     //getPlacement(that.$newElement);
                });

                $('html').on('click', function (e) {
                  if ($(e.target).closest(that.$newElement).length < 1) {
                      that.hide();
                  }
                });               
	       	}

	   };

    function Plugin(option, event){
        //获取参数
        var args = arguments;
        //定义参数和事件，并赋值给并变量
        var _option = option;
        option= args[0];
        event = args[1]|{};
        [].shift.apply(args);
        // This fixes a bug in the js implementation on android 2.3 #715
        if (typeof option =='undefined') {
            option = _option;
        }
        var value;
        var chain = this.each(function () {
             var $this = $(this);
             if ($this.is('input')) {
                  var data = $this.data('combotree'),
                  options = typeof option == 'object' && option;

                  if (!data) {
                    var config = $.extend({},defaults,$.fn.combotree.defaults || {},$this.data(),options);
                    $this.data('combotree', (data = new Combotree(this, config, event)));
                  } else if (options) {
                    for (var i in options) {
                      if (options.hasOwnProperty(i)) {
                        data.options[i] = options[i];
                      }
                    }
                  }
                  if (typeof option == 'string') {
                    if (data[option] instanceof Function) {
                        value = data[option].apply(data, args);
                    } else {
                        value = data.options[option];
                    }
                  }
             }
        });

        if (typeof value !== 'undefined') {
          return value;
        } else {
          return chain;
        }
    }
 	var old = $.fn.combotree;
    $.fn.combotree = Plugin;
    $.fn.combotree.Constructor = Combotree;

    // SELECTPICKER NO CONFLICT
    // ========================
    $.fn.combotree.noConflict = function () {
       $.fn.selectpicker = old;
       return this;
    }; 
	//加载combotree组件
    $(function () {
      $('.lion-combotree').each(function () {
          var $combotree = $(this);
          Plugin.call($combotree, $combotree.data());
      });
    });
}).call(lion,jQuery);