/***
Lion AngularJS App Main Script
***/

/* Metronic App */
var MetronicApp = angular.module("LionApp", [
    "ui.router", 
    "ui.bootstrap", 
    "oc.lazyLoad",  
    "ngSanitize"
]); 

/* Configure ocLazyLoader(refer: https://github.com/ocombe/ocLazyLoad) */
MetronicApp.config(['$ocLazyLoadProvider', function($ocLazyLoadProvider) {
    $ocLazyLoadProvider.config({
        cssFilesInsertBefore: 'ng_load_plugins_before' // load the above css files before a LINK element with this ID. Dynamic CSS files must be loaded between core and theme css files
    });
}]);

/* Setup global settings */
MetronicApp.factory('settings', ['$rootScope', function($rootScope) {
    // supported languages
    var settings = {
        layout: {
            pageSidebarClosed: false, // sidebar state
            pageAutoScrollOnLoad: 1000 // auto scroll to top on page load
        },
        layoutImgPath: Metronic.getAssetsPath() + 'admin/layout/img/',
        layoutCssPath: Metronic.getAssetsPath() + 'admin/layout/css/'
    };

    $rootScope.settings = settings;

    return settings;
}]);

/* Setup App Main Controller */
MetronicApp.controller('AppController', ['$scope', '$rootScope', function($scope, $rootScope) {
   $scope.first = 'nini';
}]);

/***
Layout Partials.
By default the partials are loaded through AngularJS ng-include directive. In case they loaded in server side(e.g: PHP include function) then below partial 
initialization can be disabled and Layout.init() should be called on page load complete as explained above.
***/

/* Setup Layout Part - Header */
MetronicApp.controller('HeaderController', ['$scope', function($scope) {
    $scope.$on('$includeContentLoaded', function() {
        Layout.initHeader(); // init header
    });
}]);

/* Setup Layout Part - Sidebar */
MetronicApp.controller('SidebarController', ['$scope', function($scope) {
    $scope.$on('$includeContentLoaded', function() {
        Layout.initSidebar(); // init sidebar
    });
}]);

/* Setup Layout Part - Sidebar */
MetronicApp.controller('PageHeadController', ['$scope', function($scope) {
    $scope.$on('$includeContentLoaded', function() {        
        Demo.init(); // init theme panel
    });
}]);

/* Setup Layout Part - Footer */
MetronicApp.controller('FooterController', ['$scope', function($scope) {
    $scope.$on('$includeContentLoaded', function() {
        Layout.initFooter(); // init footer
    });
}]);

/* Setup Layout Part - Role */
MetronicApp.controller('RoleController', ['$scope', function($scope) {
    $scope.names = [
       {
    	   "id":"0",
    	   "eName":"test12",
    	   "cName":"测试12",
    	   "description":"测试用的专属单号",
    	   "isEdit":"是",
    	   "createTime":"2015-11-18 14:09:19",
    	   "endTime":"2015-12-18 18:26:20"
       },
       {
    	   "id":"1",
    	   "eName":"wqdw1",
    	   "cName":"嗯嗯1",
    	   "description":"说好的是测试",
    	   "isEdit":"否",
    	   "createTime":"2015-6-18 7:14:36",
    	   "endTime":"2015-8-12 18:32:10"
       },
       {
    	   "id":"2",
    	   "eName":"ythty",
    	   "cName":"呵呵了",
    	   "description":"呢斯蒂法诺地",
    	   "isEdit":"否",
    	   "createTime":"2015-6-18 7:14:36",
    	   "endTime":"2015-8-12 18:32:10"
       }
    ];   
    
    
    //测试代码
//    $scope.tesarry=['1','2','3','4','5'];//初始化数据
//    $scope.choseArr=[];//定义数组用于存放前端显示
//    var str="";//
//    var flag='';//是否点击了全选，是为a
//    $scope.x=false;//默认未选中
//
//    $scope.all= function (c,v) {//全选
//        if(c==true){
//            $scope.x=true;
//            $scope.choseArr=v;
//        }else{
//            $scope.x=false;
//            $scope.choseArr=[""];
//        }
//
//        flag='a';
//
//    };
//    $scope.chk= function (z,x) {//单选或者多选
//        if(flag=='a') {//在全选的基础上操作
//            str = $scope.choseArr.join(',') + ',';
//        }
//        if (x == true) {//选中
//            str = str + z + ',';
//        } else {
//            str = str.replace(z + ',','');//取消选中
//        }
//
//        $scope.choseArr=(str.substr(0,str.length-1)).split(',');
//
//    };
//    $scope.delete= function () {// 操作CURD
//
//        if($scope.choseArr[0]==""||$scope.choseArr.length==0){//没有选择一个的时候提示
//            alert("请至少选中一条数据在操作！")
//            return;
//        };
//
//        for(var i=0;i<$scope.choseArr.length;i++){
//            //alert($scope.choseArr[i]);
//            console.log($scope.choseArr[i]);//遍历选中的id
//        }
//    };
    
}]);

/* Setup Layout Part - Group */
MetronicApp.controller('GroupController', ['$scope', function($scope) {
    $scope.$on('$includeContentLoaded', function() {
        Layout.initFooter(); // init footer
    });
}]);

/* Setup Layout Part - User */
MetronicApp.controller('UserController', ['$scope', function($scope) {
    $scope.$on('$includeContentLoaded', function() {
        Layout.initFooter(); // init footer
    });
}]);

/* Setup Layout Part - CodeType */
MetronicApp.controller('CodeTypeController', ['$scope', function($scope) {
    $scope.$on('$includeContentLoaded', function() {
        Layout.initFooter(); // init footer
    });
}]);

/* Setup Layout Part - Codelist */
MetronicApp.controller('CodelistController', ['$scope', function($scope) {
    $scope.$on('$includeContentLoaded', function() {
        Layout.initFooter(); // init footer
    });
}]);

/* Setup Layout Part - Department */
MetronicApp.controller('DepartmentController', ['$scope', function($scope) {
    $scope.$on('$includeContentLoaded', function() {
        Layout.initFooter(); // init footer
    });
}]);

/* Setup Layout Part - Application */
MetronicApp.controller('ApplicationController', ['$scope', function($scope) {
    $scope.$on('$includeContentLoaded', function() {
        Layout.initFooter(); // init footer
    });
}]);

/* Setup Layout Part - Resource */
MetronicApp.controller('ResourceController', ['$scope', function($scope) {
    $scope.$on('$includeContentLoaded', function() {
        Layout.initFooter(); // init footer
    });
}]);

/* Setup Layout Part - Parameter */
MetronicApp.controller('ParameterController', ['$scope', function($scope) {
    $scope.$on('$includeContentLoaded', function() {
        Layout.initFooter(); // init footer
    });
}]);

/* Setup Layout Part - Datagrid */
MetronicApp.controller('DatagridController', ['$scope', function($scope) {
    $scope.$on('$includeContentLoaded', function() {
        Layout.initFooter(); // init footer
    });
}]);

/* Setup Layout Part - Datacolumn */
MetronicApp.controller('DatacolumnController', ['$scope', function($scope) {
    $scope.$on('$includeContentLoaded', function() {
        Layout.initFooter(); // init footer
    });
}]);

/* Setup Layout Part - Applicationproperty */
MetronicApp.controller('ApplicationpropertyController', ['$scope', function($scope) {
    $scope.names = [
      {
    	  "id":"0",
    	  "appid":"lion",
    	  "key":"hibernate.cache.provider_class",
    	  "value":"net.sf.ehcache.hibernate.SingletonEhCacheProvider",
    	  "decription":"配置使用二级缓存的类－提供商",
    	  "createtime":"2015-03-30 11:12:12",
    	  "endtime":"2015-03-30 11:25:43"
      },
      {
    	  "id":"1",
    	  "appid":"lion",
    	  "key":"wefwewefew",
    	  "value":"ryjtyehtyehtyhjerft45gf",
    	  "decription":"缓存二级的配置使用类－提供商",
    	  "createtime":"2015-06-30 6:12:12",
    	  "endtime":"2015-10-30 10:25:43"
      },
      {
    	  "appid":"koala",
    	  "key":"rtegerfr",
    	  "value":"iwecbeibudini",
    	  "decription":"的嫩速度非常难闻哦存档－提供商",
    	  "createtime":"2015-3-16 6:12:12",
    	  "endtime":"2015-8-30 7:25:43"
      }
    ];
}]);

/* Setup Layout Part - Icon */
MetronicApp.controller('IconController', ['$scope', function($scope) {
    $scope.$on('$includeContentLoaded', function() {
        Layout.initFooter(); // init footer
    });
}]);



/* Setup Rounting For All Pages */
MetronicApp.config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider) {

    // Redirect any unmatched url
    $urlRouterProvider.otherwise("/dashboard.html");

    $stateProvider

        // Dashboard
        .state('dashboard', {
            url: "/dashboard.html",
            templateUrl: "views/dashboard.html",            
            data: {pageTitle: '应用管理系统', pageSubTitle: 'statistics & reports'},
            controller: "DashboardController"
        })

        // Role
        .state('role', {
            url: "/system/role.html",
            templateUrl: "views/system/role.html",
            data: {pageTitle: '角色', pageSubTitle: 'bootstrap components written in angularjs'},
            controller: "RoleController"
        })
        
        // Group
        .state('group', {
            url: "/system/group.html",
            templateUrl: "views/system/group.html",
            data: {pageTitle: '用户组', pageSubTitle: 'bootstrap components written in angularjs'},
            controller: "GroupController"
        })
        
        // User
        .state('user', {
            url: "/system/user.html",
            templateUrl: "views/system/user.html",
            data: {pageTitle: '用户管理', pageSubTitle: 'bootstrap components written in angularjs'},
            controller: "UserController"
        })
        
        // CodeType
        .state('codeType', {
            url: "/system/codeType.html",
            templateUrl: "views/system/codeType.html",
            data: {pageTitle: '编码类型', pageSubTitle: 'bootstrap components written in angularjs'},
            controller: "CodeTypeController"
        })
        
        // Codelist
        .state('codelist', {
            url: "/system/codelist.html",
            templateUrl: "views/system/codelist.html",
            data: {pageTitle: '编码列表', pageSubTitle: 'bootstrap components written in angularjs'},
            controller: "CodelistController"
        })
        
        // Department
        .state('department', {
            url: "/system/department.html",
            templateUrl: "views/system/department.html",
            data: {pageTitle: '部门信息', pageSubTitle: 'bootstrap components written in angularjs'},
            controller: "DepartmentController"
        })
        
        // Application
        .state('application', {
            url: "/system/application.html",
            templateUrl: "views/system/application.html",
            data: {pageTitle: '系统概要信息', pageSubTitle: 'bootstrap components written in angularjs'},
            controller: "ApplicationController"
        })
        
        // Resource
        .state('resource', {
            url: "/system/resource.html",
            templateUrl: "views/system/resource.html",
            data: {pageTitle: '资源管理', pageSubTitle: 'bootstrap components written in angularjs'},
            controller: "ResourceController"
        })
        
        // Parameter
        .state('parameter', {
            url: "/system/parameter.html",
            templateUrl: "views/system/parameter.html",
            data: {pageTitle: '系统参数管理', pageSubTitle: 'bootstrap components written in angularjs'},
            controller: "ParameterController"
        })
        
         // Datagrid
        .state('datagrid', {
            url: "/system/datagrid.html",
            templateUrl: "views/system/datagrid.html",
            data: {pageTitle: 'DataGrid', pageSubTitle: 'bootstrap components written in angularjs'},
            controller: "DatagridController"
        })
        
        // Datacolumn
        .state('datacolumn', {
            url: "/system/datacolumn.html",
            templateUrl: "views/system/datacolumn.html",
            data: {pageTitle: 'DataColumn', pageSubTitle: 'bootstrap components written in angularjs'},
            controller: "DatacolumnController"
        })
        
        // Applicationproperty
        .state('applicationproperty', {
            url: "/system/applicationproperty.html",
            templateUrl: "views/system/applicationproperty.html",
            data: {pageTitle: '项目属性配置', pageSubTitle: 'bootstrap components written in angularjs'},
            controller: "ApplicationpropertyController"
        })
        
        // Icon
        .state('icon', {
            url: "/system/icon.html",
            templateUrl: "views/system/icon.html",
            data: {pageTitle: '图标', pageSubTitle: 'bootstrap components written in angularjs'},
            controller: "IconController"
        })


}]);

/* Init global settings and run the app */
MetronicApp.run(["$rootScope", "settings", "$state", function($rootScope, settings, $state) {
    $rootScope.$state = $state; // state to be accessed from view
}]);
