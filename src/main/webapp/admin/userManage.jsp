<%--
  Created by IntelliJ IDEA.
  User: jax
  Date: 2020/3/6
  Time: 00:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>用户管理页面</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/icon.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>

    <script type="text/javascript">


        function searchEmail() {
            $("#dg").datagrid("load", {
                "email":$("#s_email").val()
            });
        }

        function deleteUser() {
            var selectedRows = $("#dg").datagrid("getSelections");
            if(selectedRows.length == 0) {
                $.messager.alert("系统提示", "请选择要删除的数据");
                return;
            }
            var idsStr = [];
            for(var i = 0; i < selectedRows.length; i++) {
                idsStr.push(selectedRows[i].id);
            }
            var ids = idsStr.join(","); //1,2,3,4
            $.messager.confirm("系统提示", "<font color=red>您确定要删除选中的"+selectedRows.length+"条数据么？</font>", function(r) {
                if(r) {
                    $.post("${pageContext.request.contextPath}/admin/user/delete.do",
                        {ids: ids}, function(result){
                            if(result.success) {
                                $.messager.alert("系统提示", "数据删除成功！");
                                $("#dg").datagrid("reload");
                            } else {
                                $.messager.alert("系统提示", "数据删除失败！");
                            }
                        }, "json");
                }
            });
        }
        
        function openUserModifyTab() {

            var selectedRows = $("#dg").datagrid("getSelections");
            if(selectedRows.length != 1) {
                $.messager.alert("系统提示", "请选择一个要修改的用户");
                return;
            }
            var row = selectedRows[0];
            $("#dlg").dialog("open").dialog("setTitle", "修改用户信息");
            $("#fm").form("load", row);//会自动识别name属性，将row中对应的数据，填充到form表单对应的name属性中
            url = "${pageContext.request.contextPath}/admin/user/save.do?id=" + row.id;
        }


        function saveUserPassword() {
            $("#fm").form("submit",{
                url: url,
                onSubmit: function() {
                    return $(this).form("validate");
                }, //进行验证，通过才让提交
                success: function(result) {
                    var result = eval("(" + result + ")"); //将json格式的result转换成js对象
                    if(result.success) {
                        $.messager.alert("系统提示", "用户保存成功");
                        $("email").val(""); //保存成功后将内容置空
                        $("password").val("");
                        $("#dlg").dialog("close"); //关闭对话框
                        $("#dg").datagrid("reload"); //刷新一下
                    } else {
                        $.messager.alert("系统提示", "用户保存失败");
                        return;
                    }
                }
            });
        }

        function closeUserDialog() {
            $("email").val(""); //保存成功后将内容置空
            $("password").val("");
            $("#dlg").dialog("close"); //关闭对话框
        }

    </script>

</head>

<body style="margin: 1px; font-family: microsoft yahei">
<table id="dg" title="博客管理" class="easyui-datagrid" fitColumns="true" pagination="true"
       url="${pageContext.request.contextPath}/admin/user/listUser.do" toolbar="#tb">
    <thead>
    <tr>
        <th field="cb" checkbox="true" align="center"></th>
        <th field="id" width="20" align="center">编号</th>
        <th field="name" width="100" align="center">昵称</th>
        <th field="email" width="100" align="center">邮箱</th>
        <th field="password" width="100" align="center">密码</th>
<%--        <th field="releaseDate" width="100" align="center">发布日期</th>--%>
<%--        <th field="blogType" width="100" align="center" formatter="formatBlogType">博客类型</th>--%>
    </tr>
    </thead>
</table>
<div id="tb">
    <div>
        &nbsp;邮箱&nbsp;<input type="text" id="s_email" size="20" onkeydown="if(event.keyCode==13) searchEmail()">
        <a href="javascript:searchEmail()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
        <a href="javascript:deleteUser()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除用户</a>
        <a href="javascript:openUserModifyTab()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改用户密码</a>
        <a href="javascript:reload()" class="easyui-linkbutton" iconCls="icon-reload" plain="true">刷新</a>
    </div>
</div>


<div id="dlg" class="easyui-dialog" style="width:500px; height:180px; padding:10px 20px"
     closed="true" buttons="#dlg-buttons">
    <form id="fm" method="post">
        <table cellspacing="8px">
            <tr>
                <td>用户邮箱</td>
                <td>
                    <input type="text" id="email" name="email" class="easyui-validatebox"  readonly="readonly">
                </td>
            </tr>
            <tr>
                <td>新密码</td>
                <td>
                    <input type="password" id="password" name="password" class="validatebox" required="true">
                </td>
            </tr>
        </table>
    </form>
</div>


<div id="dlg-buttons">
    <div>
        <a href="javascript:saveUserPassword()" class="easyui-linkbutton" iconCls="icon-ok" plain="true">保存</a>
        <a href="javascript:closeUserDialog()" class="easyui-linkbutton" iconCls="icon-cancel" plain="true">关闭</a>
    </div>
</div>
</body>
</html>