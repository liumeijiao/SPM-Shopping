<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<link href="${pageContext.request.contextPath }/user/css/style.css" rel='stylesheet' type='text/css' />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<!--webfonts-->
		<!--//webfonts-->
		<script src="js/setDate.js" type="text/javascript"></script>
		<script LANGUAGE="JavaScript">
			function check(){
                    var username = document.getElementById("username");
                    //发送ajax异步请求
                    // 1、创建XMLHttpRequest对象
                    var xhr = new XMLHttpRequest();
                    //2、注册状态监控回调函数
                    xhr.onreadystatechange = function () {
                        // alert()

                        if (xhr.readyState == 4&& xhr.status == 200 ) {
                            var context = xhr.responseText;

                          var isOk=  document.getElementById("isOk");
                             if("no"==context){
                                    isOk.innerText="该用户已被占用";
                             }else{
                                 isOk.innerText="该用户可用"
                             }
                         }
                        }

                     //3、建立与服务器的异步连接
                        xhr.open("post", "/project1/UserServlet?op=checkIsExit&username="
                            + username.value);
                        //4、发出异步请求
                        xhr.send(null);
                }

                function reg(){
					var email = document.getElementById("email");
					var ret=/^(\w[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+))$/.test(email.value);
					var ex= document.getElementById("isright");
					if(ret){
					    ex.innerText="邮箱合法";
					}else {
					    ex.innerText="邮箱不合法";
					}
                }

		</script>
	</head>

	<body>
		<div class="main" align="center">
			<div class="header">
				<h1>创建一个免费的新帐户！</h1>
			</div>
			<p></p>
			<form method="post" action="${pageContext.request.contextPath }/UserServlet">
				<input type="hidden" name="op" value="regist" />
				<ul class="left-form">
                    <li>
                        <h3 id="isOk"></h3>
                    </li>
					<li>
						${msg.error.username }<br/>
						<input type="text" name="username" placeholder="用户名" value="${msg.username}" id="username"
							   required="required" onblur="check()"/>
						<a href="#" class="icon ticker"> </a>
						<div class="clear"> </div>
					</li>
					<li>
						${msg.error.nickname }<br/>
						<input type="text" name="nickname" placeholder="昵称" value="${msg.nickname}" required="required"/>
						<a href="#" class="icon ticker"> </a>
						<div class="clear"> </div>
					</li>
					<li>
						<span id="isright"></span>
					</li>
					<li>
						${msg.error.email }<br/>
						<input type="text" name="email" id="email" placeholder="邮箱" value="${msg.email}" required="required" onblur="reg()

						"/>

						<a href="#" class="icon ticker"> </a>
						<div class="clear"> </div>
					</li>

					<li>
						${msg.error.password }<br/>
						<input type="password" name="password" placeholder="密码" value="${msg.password}" required="required"/>
						<a href="#" class="icon into"> </a>
						<div class="clear"> </div>
					</li>
					<li>
						${msg.error.birthday }<br/>
						<input type="text" placeholder="出生日期" name="birthday" value="${msg.birthday}" size="15" />
						<div class="clear"> </div>
					</li>
					<li>
						<input type="submit" value="创建账户">
						<div class="clear"> </div>
					</li>
			</ul>

			<div class="clear"> </div>

			</form>

		</div>
		<!-----start-copyright---->

		<!-----//end-copyright---->

	</body>

</html>