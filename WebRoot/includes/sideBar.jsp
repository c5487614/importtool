<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
      
      
      <!-- sidebar menu: : style can be found in sidebar.less -->
      <ul class="sidebar-menu">
        <li class="header">功能菜单V1.0.20180809</li>
        
        <li class="active">
          <a target="test" href="<%=request.getContextPath()%>/application/manageApp.do">
            <i class="fa fa-th"></i> <span>应用管理</span>
            <span class="pull-right-container">
              <small class="label pull-right bg-green"></small>
            </span>
          </a>
        </li>
        
        <li class="treeview">
          <a href="#">
            <i class="glyphicon glyphicon-cog"></i> <span>系统配置</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
          <c:if test="${currentUser.userId=='chenchunhui'}">
          <li><a target="test" href="<%=request.getContextPath()%>/systemConfig/hardServerConfig.jsp"><i class="fa fa-circle-o"></i> 硬件服务器配置</a></li>
          </c:if>
          
          <li><a target="test" href="<%=request.getContextPath()%>/systemConfig/manageServer.do"><i class="fa fa-circle-o"></i> TOMCAT配置</a></li>	
          <li class="active"><a target="test" href="<%=request.getContextPath()%>/application/listApp.do"><i class="fa fa-circle-o"></i> 应用配置</a></li>
            
          </ul>
        </li>
        
        <c:if test="${listUserHServer!=null}">
        	<li class="treeview">
	          <a href="#">
	            <i class="glyphicon glyphicon-home"></i> <span>我的服务器</span>
	            <span class="pull-right-container">
	              <i class="fa fa-angle-left pull-right"></i>
	            </span>
	          </a>
	          <ul class="treeview-menu">
	          	<c:forEach var="model" items="${listUserHServer}">
					<li><a target="test" href="<%=request.getContextPath()%>/application/manageAppEx.do?hServerId=<c:out value="${model.HServerId}"/>"><i class="fa fa-circle-o"></i> <c:out value="${model.HServerName}"/></a></li>
				</c:forEach>
	          </ul>
	        </li>
        </c:if>
        
        <!-- 
        <li class="treeview">
          <a href="#">
            <i class="fa fa-files-o"></i>
            <span>系统配置</span>
            <span class="pull-right-container">
              <span class="label label-primary pull-right"></span>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="pages/layout/top-nav.html"><i class="fa fa-circle-o"></i> 应用管理</a></li>
            <li><a href="pages/layout/boxed.html"><i class="fa fa-circle-o"></i> 服务器管理</a></li>
          </ul>
        </li>
        
        <li>
          <a href="pages/widgets.html">
            <i class="fa fa-th"></i> <span>测试</span>
            <span class="pull-right-container">
              <small class="label pull-right bg-green">new</small>
            </span>
          </a>
        </li>
        -->
        <!-- 
        <li><a href="documentation/index.html"><i class="fa fa-book"></i> <span>Documentation</span></a></li>
         -->
        <li class="header">DPTOOL</li>
      </ul>
    </section>
    <!-- /.sidebar -->
  </aside>