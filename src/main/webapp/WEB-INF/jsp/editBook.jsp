<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="common/tag.jsp" %>
<%  
    String path = request.getContextPath();  
    String basePath = request.getScheme() + "://"  
            + request.getServerName() + ":" + request.getServerPort()  
            + path + "/";  
%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>
	<title>编辑图书</title>
	<%@include file="common/head.jsp" %>

	<!-- form.action = "/booksystem/books/updateBook"; -->
	<script type="text/javascript">
    	function updateBook() {
        	var form = document.forms[0];
        	form.action = "<%=basePath%>books/updateBook";
        	form.method = "post";
        	form.submit();
    	}
	</script>
</head>

<body>
<div class="container">
    <div class="panel panel-default">
        <div class="panel-heading text-center">
            <h2>修改图书</h2>
        </div>
        <form>
            <div class="form-group">
                <label for="bookIdKey">图书ID</label>
                <input type="text" class="form-control" name="bookId" value="${sk.bookId}" 
                placeholder="请注意与原图书ID保持一致" /> 
            </div>
            <div class="form-group">
                <label for="bookNameKey">图书名称</label>
                <input type="text" class="form-control" name="name" value="${sk.name}" 
                placeholder="非空" /> 
            </div>
            <div class="form-group">
                <label for="bookIntrodKey">图书介绍</label>
                <input type="text" class="form-control" name="introd" value="${sk.introd}" 
                placeholder="非空" />
            </div>
            <div class="form-group">
                <label for="bookNumberKey">图书数量</label>
                <input type="text"  class="form-control" name="number" value="${sk.number}" 
                placeholder="非空" /> 
            </div>
            <span id="addMessage" class="glyphicon"> </span>
                <input type="button" value="修改" onclick="updateBook()" />
        </form>


    </div>    
</div>

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="http://apps.bdimg.com/libs/jquery/2.0.0/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="http://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
</body>

</html>