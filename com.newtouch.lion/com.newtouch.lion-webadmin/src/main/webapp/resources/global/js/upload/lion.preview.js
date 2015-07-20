/*
*插件介绍:图片上传本地预览插件 兼容浏览器(IE 谷歌 火狐) 不支持safari 当然如果是使用这些内核的浏览器基本都兼容
*插件网站:http://jquery.decadework.com
*使用方法:
*界面构造(IMG标签外必须拥有DIV 而且必须给予DIV控件ID)
* <div id='imgdiv'><img id='imgShow' width='120' height='120' /></div>
* <input type='file' id='up_img' />
*调用代码:
* new uploadPreview({ UpBtn: 'up_img', DivShow: 'imgdiv', ImgShow: 'imgShow' });
*参数说明:
*UpBtn:选择文件控件ID;
*DivShow:DIV控件ID;
*ImgShow:图片控件ID;
*Width:预览宽度;
*Height:预览高度;
*ImgType:支持文件类型 格式:['jpg','png'];
*callback:选择文件后回调方法;
*依赖：jquery Jcrop 
*/

/*
*work:图片预览插件
*/
;(function($) {
	'use strict';
    jQuery.fn.extend({
        uploadPreview: function(setting) {
            /*
            *work:this(当前对象)
            */
            var _self = this;
            /*
            *work:判断为null或者空值
            */
            _self.IsNull = function(value) {
                if (value === undefined || value === null || value === '' || value.length === 0) {
                    return true;
                }
                return false;
            };
            /*
            *work:默认配置
            */
            _self.DefautlSetting = {
                DivShow: '',
                ImgShow: 'imgShow',
                ImgType: ['gif', 'jpeg', 'jpg','bmp', 'png'],
                ErrMsg: '选择文件错误,图片类型必须是(gif,jpeg,jpg,bmp,png)中的一种',
                callback: function() { }
            };
            /*
            *work:获取文本控件URL
            */
            _self.getObjectURL = function(file) {
                var url = null;
                if (window.createObjectURL != undefined) {
                    url = window.createObjectURL(file);
                } else if (window.URL != undefined) {
                    url = window.URL.createObjectURL(file);
                } else if (window.webkitURL != undefined) {
                    url = window.webkitURL.createObjectURL(file);
                }
                return url;
            };
            /*
            *work:绑定事件
            */
            _self.change(function() {
            	var DivShow=$('#'+setting.DivShow),
            	imgShow=_self.IsNull(DivShow.find('img').eq(0).attr('id')) ? _self.DefautlSetting.ImgShow : DivShow.find('img').eq(0).attr('id'),
    			Width=_self.IsNull(setting.Width) ? DivShow.width() : setting.Width,
            	Height=_self.IsNull(setting.Height) ? DivShow.height() : setting.Height,
            	ImgType=_self.IsNull(setting.ImgType) ? _self.DefautlSetting.ImgType : setting.ImgType,
            	ErrMsg=_self.IsNull(setting.ErrMsg) ? _self.DefautlSetting.ErrMsg : setting.ErrMsg,
            	callback=_self.IsNull(setting.callback) ? _self.DefautlSetting.callback : setting.callback;
                if (this.value) {
                    if (!RegExp('\.(' + ImgType.join('|') + ')$', 'i').test(this.value.toLowerCase())) {
                        alert(_self.Setting.ErrMsg);
                        this.value = '';
                        return false;
                    }
                    //清空数据
                    DivShow.empty();
                    var imgHtml = '<img id="'+imgShow+'" />';
                    DivShow.html(imgHtml);
                    var ImgShow = $('#'+imgShow);
                    //设置图片url
                    ImgShow.width(100 + 'px');
                    ImgShow.height(100 + 'px');
                    ImgShow.attr('src',_self.getObjectURL(this.files[0]));
                    //回调方法
                    callback();
                    
                } 
            });
           
        }
    });
})(jQuery);