<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ssm博客系统</title>
<script
	src="${pageContext.request.contextPath}/static/bootstrap3/js/jquery-1.11.2.min.js"></script>
<style>
body {
	background: #ebebeb;
	font-family: "Helvetica Neue", "Hiragino Sans GB", "Microsoft YaHei",
		"\9ED1\4F53", Arial, sans-serif;
	color: #222;
	font-size: 12px;
}

* {
	padding: 0px;
	margin: 0px;
}

.top_div {
	background: #008ead;
	width: 100%;
	height: 400px;
}

.ipt {
	border: 1px solid #d3d3d3;
	padding: 10px 10px;
	width: 290px;
	border-radius: 4px;
	padding-left: 35px;
	-webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
	box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
	-webkit-transition: border-color ease-in-out .15s, -webkit-box-shadow
		ease-in-out .15s;
	-o-transition: border-color ease-in-out .15s, box-shadow ease-in-out
		.15s;
	transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s
}

.ipt:focus {
	border-color: #66afe9;
	outline: 0;
	-webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075), 0 0 8px
		rgba(102, 175, 233, .6);
	box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075), 0 0 8px
		rgba(102, 175, 233, .6)
}

.u_logo {
	background:
		url("${pageContext.request.contextPath}/static/images/username.png")
		no-repeat;
	padding: 10px 10px;
	position: absolute;
	top: 43px;
	left: 40px;
}

.p_logo {
	background:
		url("${pageContext.request.contextPath}/static/images/password.png")
		no-repeat;
	padding: 10px 10px;
	position: absolute;
	top: 12px;
	left: 40px;
}

a {
	text-decoration: none;
}

.tou {
	background:
		url("${pageContext.request.contextPath}/static/images/tou.png")
		no-repeat;
	width: 97px;
	height: 92px;
	position: absolute;
	top: -87px;
	left: 140px;
}

.left_hand {
	background:
		url("${pageContext.request.contextPath}/static/images/left_hand.png")
		no-repeat;
	width: 32px;
	height: 37px;
	position: absolute;
	top: -38px;
	left: 150px;
}

.right_hand {
	background:
		url("${pageContext.request.contextPath}/static/images/right_hand.png")
		no-repeat;
	width: 32px;
	height: 37px;
	position: absolute;
	top: -38px;
	right: -64px;
}

.initial_left_hand {
	background:
		url("${pageContext.request.contextPath}/static/images/hand.png")
		no-repeat;
	width: 30px;
	height: 20px;
	position: absolute;
	top: -12px;
	left: 100px;
}

.initial_right_hand {
	background:
		url("${pageContext.request.contextPath}/static/images/hand.png")
		no-repeat;
	width: 30px;
	height: 20px;
	position: absolute;
	top: -12px;
	right: -112px;
}

.left_handing {
	background:
		url("${pageContext.request.contextPath}/static/images/left-handing.png")
		no-repeat;
	width: 30px;
	height: 20px;
	position: absolute;
	top: -24px;
	left: 139px;
}

.right_handinging {
	background:
		url("${pageContext.request.contextPath}/static/images/right_handing.png")
		no-repeat;
	width: 30px;
	height: 20px;
	position: absolute;
	top: -21px;
	left: 210px;
}
</style>

<script type="text/javascript">
	$(function() {
		//得到焦点
		$("#password").focus(function() {
			$("#left_hand").animate({
				left : "150",
				top : " -38"
			}, {
				step : function() {
					if (parseInt($("#left_hand").css("left")) > 140) {
						$("#left_hand").attr("class", "left_hand");
					}
				}
			}, 2000);
			$("#right_hand").animate({
				right : "-64",
				top : "-38px"
			}, {
				step : function() {
					if (parseInt($("#right_hand").css("right")) > -70) {
						$("#right_hand").attr("class", "right_hand");
					}
				}
			}, 2000);
			$("#error").html("");
		});
		//失去焦点
		$("#password").blur(function() {
			$("#left_hand").attr("class", "initial_left_hand");
			$("#left_hand").attr("style", "left:100px;top:-12px;");
			$("#right_hand").attr("class", "initial_right_hand");
			$("#right_hand").attr("style", "right:-112px;top:-12px");
			var password = $('#password').val();
			var reg = new RegExp("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$");
			if(!reg.test(password)){
				$("#error").html("密码格式不正确，请重新输入！");
			}
		});
		$("#email").blur(function() {
			var email = $('#email').val();
			var reg = new RegExp("^[a-z0-9A-Z]+[- | a-z0-9A-Z . _]+@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-z]{2,}$");
			if(!reg.test(email)){
				$("#error").html("邮箱格式不正确，请重新输入！");
			}
		});
		$("#email").focus(function() {
			$("#error").html("");
		});
	});
	
	function sendCode() {
        var email = $('#email').val();
        var password = $('#password').val();
        $("#error").html("正在发送，请耐心等待...");
        $.post(
            "${pageContext.request.contextPath}/user/sendCode.do",
            {"email":email,"password":password},
            function(data) {
                if (data.success){
                    alert("发送成功，邮件接收过程时间可能较长，请耐心等待");
                    $('#email').val(email);
                    $('#password').val(password)
                }else {
                    alert("发送失败，邮件服务器可能异常请稍后尝试注册，先去浏览浏览博客吧！！！");
                    window.location.reload();
                    $('#email').val(email);
                }
            },"json");

    };

    function checkForm() {
        var vemail = new RegExp("^[a-z0-9A-Z]+[- | a-z0-9A-Z . _]+@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-z]{2,}$");
        var vpassword = new RegExp("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$");
        var vcode = new RegExp("^\\d{6}$");

        var email = $('#email').val();
        var password = $('#password').val();
        var verCode = $('#verCode').val();
        if (email == null || email == "") {
            return false;
            alter("邮箱不能为空！");
        }
        if(!vemail.test(email)){
            return false;
            alter("邮箱格式不正确，请重新输入！");
        }
        if (password == null || password == "") {
            return false;
            alter("密码不能为空！");
        }
        if(!vpassword.test(password)){
            return false;
            alter("密码格式不正确，请重新输入！");
        }
        if (verCode == null || verCode == "") {
            return false;
            alter("验证码不能为空！");
        }
        if(!vcode.test(verCode)){
            return false;
            alter("验证码格式不正确，请重新输入！");
        }
            return true;

    };
</script>
</head>
<body>
	<div class="top_div"></div>
    <form action="${pageContext.request.contextPath}/user/reg.do"
          method="post" onsubmit="return checkForm()">
		<div
			style="background: rgb(255, 255, 255); margin: -100px auto auto; border: 1px solid rgb(231, 231, 231); border-image: none; width: 400px; height: 200px; text-align: center;">
			<div style="width: 165px; height: 96px; position: absolute;">
				<div class="tou"></div>
				<div class="initial_left_hand" id="left_hand"></div>
				<div class="initial_right_hand" id="right_hand"></div>
			</div>
			<p style="padding: 30px 0px 10px; position: relative;">
				<span class="u_logo"></span>
				<input id="email" name="email" class="ipt" type="text"
					placeholder="请输入邮箱" value="${user.email }">
			</p>
			<p style="position: relative;">
				<span class="p_logo"></span>
				<input id="password" name="password" class="ipt" type="password"
					placeholder="请设置密码：必须为6-21位非纯数字或纯英文" value="${user.password }">
			</p>
            <div>
                <input id="verCode" name="verCode" type="text"
                       placeholder="验证码" value="${verCode}">
                <button class="btn btn-primary" type="button" onclick="sendCode()">发送验证码</button>
            </div>
			<div
				style="height: 50px; line-height: 50px; margin-top: 30px; border-top-color: rgb(231, 231, 231); border-top-width: 1px; border-top-style: solid;">
				<p style="margin: 0px 35px 20px 45px;">
					<span style="float: left;">Jax的博客系统</span>
					<span><font color="red" id="error">${errorInfo }</font></span>
					<span
							style="float: right;">
						<input type="submit"
							   style="background: rgb(0, 142, 173); padding: 7px 10px; border-radius: 4px; border: 1px solid rgb(26, 117, 152); border-image: none; color: rgb(255, 255, 255); font-weight: bold;"
							   value="注册" />
					</span>
					<span style="float: right;">
						<a href="${pageContext.request.contextPath}/user/goLogin.html"
						   style="background: rgb(0, 142, 173); padding: 7px 10px; border-radius: 4px; border: 1px solid rgb(26, 117, 152); border-image: none; color: rgb(255, 255, 255); font-weight: bold;"
						>去登录</a>
					</span>
				</p>
			</div>
		</div>
	</form>
</body>
</html>