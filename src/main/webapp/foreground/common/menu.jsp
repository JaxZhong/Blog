<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript">
    function checkData() {

        var q = document.getElementById("q").value.trim();
        if (q == null || q == "") {
            alert("请输入您要查询的关键字！");
            return false;
        } else {
            return true;
        }
    }
    function loginOut() {
        $.get(
            "${pageContext.request.contextPath}/user/loginOut.do",
            function(data) {
                if(data.success) {
                    alert("成功退出登录");
                    window.location.reload();
                }
            },"json");
    }
</script>

<div class="col-md-12">
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li><a class="navbar-brand" href="${pageContext.request.contextPath}/index.html" onclick="changeClass(this)">博客首页</a></li>
                    <li><a class="navbar-brand" href="${pageContext.request.contextPath}/blogger/aboutme.html" onclick="changeClass(this)">关于博主</a></li>
<%--                    <li><a class="navbar-brand" href="${pageContext.request.contextPath}/blogger/myalbum.html" onclick="changeClass(this)">我的相册</a></li>--%>
                    <li><a class="navbar-brand" href="${pageContext.request.contextPath}/blogger/resource.html" onclick="changeClass(this)">资源小站</a></li>
                    <li><a class="navbar-brand" href="https://github.com/JaxZhong" target="blank">我的GitHub</a></li>
                </ul>
                <c:if test="${CurrentUser==null}">
                    <ul class="nav navbar-nav navbar-right">
                        <button type="button" class="btn btn-default navbar-btn" onclick="location.href='${pageContext.request.contextPath}/userLogin.jsp'"></a>登录</button>
                        <li><a class="navbar-brand" href="${pageContext.request.contextPath}/userLogin.jsp">注册</a></li>
                    </ul>
                </c:if>
                <c:if test="${CurrentUser!=null }">
                    <ul class="nav navbar-nav navbar-right">
                        <p class="navbar-text" id="user">${CurrentUser.email}&nbsp;&nbsp;</p>
                        <button type="button" class="btn btn-default navbar-btn" onclick="loginOut()"></a>注销</button>
                    </ul>
                </c:if>
                <form action="${pageContext.request.contextPath}/blog/search.html" class="navbar-form"
                      role="search" method="post" onsubmit="return checkData()">
                    <div class="form-group">
                        <input type="text" id="q" name="q" value="${q }" class="form-control" placeholder="请输入要查询的关键字">
                    </div>
                    <button type="submit" class="btn btn-default">搜索</button>
                </form>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>
</div>

