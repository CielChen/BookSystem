<%--
  User: CIEL
  Date: 2018/06
--%>
<%@page contentType="text/html; charset=UTF-8" language="java"%>
<%@include file="common/tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>管理员图书列表</title>
<%@include file="common/head.jsp"%>
</head>

<body>
	<div class="container">
		<div class="panel panel-default">
			<div class="panel-heading text-center">
				<h2>图书列表</h2>
			</div>
			<form name="firstForm"
				action="<%= request.getContextPath()%>/books/search" method="post">
				<div class="panel-heading ">
					<table class="table table-bookName">
						<thead>
							<tr>
								<th width="90" align="lift">图书名称：</th>
								<th width="150" align="lift"><input type="text" name="name"
									class="allInput" value="${name}" placeholder="输入检索书名" /></th>
								<th><input type="submit" id="tabSub" value="检索" />
									<button type="button" class="btn btn-info" id="clearCache">清除缓存</button>
									<a class="btn btn-info" id="returnToList" href="/booksystem/books/adminlist" target="_blank">首页</a>
								</th>
								<th>
									<button type="button" class="btn btn-info" id="addBook"
										style="float: right;" target="_blank">添加图书</button>
								</th>
							</tr>
						</thead>
					</table>
				</div>
			</form>


			<div class="panel-body">
				<table class="table table-hover">
					<thead>
						<tr>
							<th><a id="bookID">图书ID</a>
								<div class="glyphicon glyphicon-arrow-up arrow-up"></div>
								<div class="glyphicon glyphicon-arrow-down arrow-down"></div></th>
							<th><a id="name">图书名称</a>
								<div class="glyphicon glyphicon-arrow-up arrow-up"></div>
								<div class="glyphicon glyphicon-arrow-down arrow-down"></div></th>
							<th><a id="number">馆藏数量</a>
								<div class="glyphicon glyphicon-arrow-up arrow-up"></div>
								<div class="glyphicon glyphicon-arrow-down arrow-down"></div></th>
							<th>详细</th>
							<th>操作</th>
						</tr>

						<!-- <tbody> 标签表格主体（正文） -->
					<tbody>
						<!-- c:forEach：的作用就是迭代输出标签内部的内容 
			       items="要迭代的list"
			       var="每个变量名字"   -->
						<c:forEach items="${list}" var="sk">
							<tr>
								<td>${sk.bookId}</td>
								<td>${sk.name}</td>
								<td>${sk.number}</td>
								<td>
									<!-- <a> 标签定义超链接，用于从一张页面链接到另一张页面。 
				         class属性: 规定元素的类名
				         href属性: 指示链接的目标
				         target: 规定在何处打开链接文档--> <a class="btn btn-info"
									href="/booksystem/books/${sk.bookId}/detail" target="_blank">详细</a>
								</td>

								<td>
								<a type="button"
									href="/booksystem/books/getBook?id=${sk.bookId}"
									class="btn btn-info btn-sm"> 
									<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
										编辑
								</a> 
								<a type="button" href="/booksystem/books/delBook?id=${sk.bookId}"
									class="btn btn-danger btn-sm"> <span
										class="glyphicon glyphicon-trash" aria-hidden="true"></span>
										删除
								</a>
								</td>

							</tr>
						</c:forEach>
					</tbody>

					</thead>


				</table>
			</div>

			<!-- 
			<nav aria-label="Page navigation">
				<ul class="pager pagination-lg">
					<li><a href="#" aria-label="Previous" id="previous"><span
							aria-hidden="true">&laquo;</span></a></li>
					<li><a href="#" id="first-column" class="columnFlag"></a></li>
					<li><a href="#" id="second-column" class="columnFlag"></a></li>
					<li><a href="#" id="third-column" class="columnFlag"></a></li>
					<li><a href="#" id="forth-column" class="columnFlag"></a></li>
					<li><a href="#" id="fifth-column" class="columnFlag"></a></li>
					<li><a href="#" aria-label="Next" id="next"><span
							aria-hidden="true">&raquo;</span></a></li>
					<li><span>输入每页显示的条数</span><input type="text" id="recordNum"
						value="5"></li>
				</ul>
			</nav>
			 -->

		</div>
	</div>



	<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
	<script src="http://apps.bdimg.com/libs/jquery/2.0.0/jquery.min.js"></script>
	<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
	<script
		src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>
	<script
		src="http://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>


	<script src="/booksystem/resources/script/bookappointment.js"
		type="text/javascript"></script>
	<script type="text/javascript">
    $(function () {
        bookappointment.list.init();
        //bookappointment.adminlist.init();
    })
</script>
</body>
</html>

