//注:$为jquery的缩写
//$(function(){}) == $(document).ready(function(){})
//个人信息下拉菜单样式
$(document).ready(function() {
	//首先隐藏个人信息下拉菜单
	$(".user-menu-login").hide();
	$(".user-menu-unlogin").hide();
	//判断是否登录
	let result;
	$.ajax({
		url : "ctrl_isLogin",
		type : "get",
		dataType : "json",
		success : function(data) {
			console.log("用户是否登录：");
			console.log(data);
			result = data.toString();
			if (result !== "0")
				//有用户登录，加载头像
				$(".user-avatar img")[0].setAttribute("src", "../src/user_avatar/" + result + ".png");
			//鼠标悬浮在用户信息区时要根据登录情况选择不同的下拉菜单
			$(".user-avatar").hover(
				function() {
					if (result === "0")
						$(".user-menu-unlogin").show();
					else
						$(".user-menu-login").show();
					$(".caret").css("border-top", "0");
					$(".caret").css("border-bottom", "5px solid #666");
				},
				function() {
					$(".user-menu-unlogin").hide();
					$(".user-menu-login").hide();
					$(".caret").css("border-top", "5px solid #666");
					$(".caret").css("border-bottom", "0");
				}
			);
		},
		error : function(error) {
			alert("----ajax请求回调出错函数！----\n" + error.responseText);
		}
	});
	//鼠标悬浮在下拉菜单时要显示菜单
	$(".user-menu-unlogin").hover(
		function() {
			$(".user-menu-unlogin").show();
		},
		function() {
			$(".user-menu-unlogin").hide();
		}
	);
	$(".user-menu-login").hover(
		function() {
			$(".user-menu-login").show();
		},
		function() {
			$(".user-menu-login").hide();
		}
	);
});
//搜索按钮的点击事件
function searchFilm() {
	let keyword = $("#kw").val();
	window.open("ClientPage_SearchResult.html?keyword=" + keyword);
}
//登陆用户点击退出登录
function logout() {
	$.ajax({
		url : "ctrl_LogOut",
		type : "get",
		dataType : "json",
		async : "false",
		success : function(data) {
			console.log("用户退出结果：");
			console.log(data);
			if (data === "1") {
				alert("您已成功退出！");
				$(window).attr("location", "ClientPage_Login.html");
			}
		},
		error : function(error) {
			alert("----ajax请求回调出错函数！----\n" + error.responseText);
		}
	});
}