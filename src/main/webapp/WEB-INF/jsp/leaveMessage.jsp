<%--
  Created by IntelliJ IDEA.
  User: 17535
  Date: 2017/10/11
  Time: 8:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>留言板</title>
    <link href="/css/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/flat-ui.min.css" rel="stylesheet">

    <script src="https://cdn.bootcss.com/jquery/3.1.1/jquery.min.js"></script>
    <script src="/js/flat-ui.min.js"></script>
<script type="text/javascript">
    function getMsg() {
        var url = "${pageContext.request.contextPath}/api/msg";
        $.ajax({
            url:url,
            type:"post",
            dataType:"json",
            success:function (result) {
                var html1 = "<thead><tr><th>留言内容</th><th>留言人</th><th>留言时间</th></tr></thead>";
                var innHtml = "";
                for (var i=0;i<result.length;i++){
                    var jsonObject = result[i];
                    innHtml = innHtml + "<tr><td>"+jsonObject.context+"</td><td>"+jsonObject.name+"</td><td>"+jsonObject.createDate+"</td></tr>";
                }
                $("#msgtable").html(html1 + innHtml);
            },
            error:function () {
                alert("请求失败");
            }
        });
    }
</script>

</head>
<body onload="getMsg()">
<div class="main">
    <h2>请输入留言</h2>
    <div class="addMsg">
        <form action="/addMsg" method="post" class="navbar-form navbar-left">
            <div class="form-group">
                <input class="form-control" type="text" name="context" placeholder="请输入留言" />&nbsp;&nbsp;&nbsp;&nbsp;
                <input class="form-control" type="text" name="name" placeholder="请输入姓名" />&nbsp;&nbsp;&nbsp;&nbsp;
                <button class="btn btn-primary btn-small" type="submit">留言</button>&nbsp;&nbsp;
                <button class="btn btn-warning btn-small" type="reset">清空</button>
            </div>
        </form>
    </div>

    <div class="show">
        <table class="table table-bordered table-hover" id="msgtable">
        </table>
    </div>

</div>
</body>
</html>
