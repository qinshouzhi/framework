<!-- Freemarker自定义标签 -->
<#assign contextPath = request.contextPath/>
<!--DataGrid 工具栏标签 Start-->

<!--DataGrid 工具栏标签 End-->
<!---->

<!--菜单标签 Start-->
<#macro menuTree menus>
	<#if menus?? && menus?size gt 0>
		<ul class="sub-menu">
			<#list menus as menu>
				<#if  menu.icon??&&menu.icon?length gt 0>
				<li  <#if menu.selected>class="open active" </#if> >
					<a href="${contextPath!}${menu.path!}">
						<i class="${menu.icon!}"></i>
						${menu.nameZh!}
						<#if menu.menus?? && menu.menus?size gt 0>
							<span class="arrow <#if menu.selected>open</#if>"></span>
						</#if>
					</a>					
					<@menuTree menu.menus/>
				</li>
				<#else>
					<li <#if menu.selected>class="active" </#if> >
					<a href="${contextPath!}${menu.path!}">
						<i class="${menu.icon!}"></i>
						${menu.nameZh!}
						<#if menu.menus?? && menu.menus?size gt 0>
							<span class="arrow"></span>
						</#if>
					</a>					
					<@menuTree menu.menus/>
					</li>
				</#if>
			</#list>
		</ul>
	</#if>
</#macro>
<#macro menus>
	<@ui.menu>
		<ul class="page-sidebar-menu " data-keep-expanded="false" data-auto-scroll="true" data-slide-speed="200">
		<#if menus?? && menus?size gt 0>
			<#list menus as menu>
				<!--开始第一个菜单显示样式-->
				<#if menu_index==0>
				<li class="start <#if menu.selected>active</#if>">
					<a href="${contextPath}${menu.path!}">
					<#if  menu.icon??&&menu.icon?length gt 0>
						<i class="${menu.icon!}"></i>
					</#if>
					<span class="title">${menu.nameZh!}</span>
					</a>
				</li>
			    <#else>
				    <li <#if menu.selected>class="active open" </#if> >
						<a href="javascript:;">
							<#if  menu.icon??&&menu.icon?length gt 0>
							<i class="${menu.icon!}"></i>
							</#if>
							<span class="title">${menu.nameZh!}</span>
							<#if menu.menus?? && menu.menus?size gt 0>
								<span class="arrow <#if menu.selected>open</#if>"></span>
							</#if>
						</a>
						<@menuTree menu.menus/>
					</li>
				</#if>
			</#list>
		</#if>
	</ul>	
</@ui.menu>
</#macro>
<!--菜单标签 End-->