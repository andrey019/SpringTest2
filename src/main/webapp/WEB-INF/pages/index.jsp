<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Prog.kiev.ua</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <h3>Advertisements List</h3>

    <form class="form-inline" role="form" action="/search" method="post">
        <input type="text" class="form-control" name="pattern" placeholder="Search">
        <input type="submit" class="btn btn-default" value="Search">
    </form>

    <form:form method="post" action="/deletechkbox" modelAttribute="adsModel">
    <div class="table-responsive"/>
    <table class="table table-hover">
        <thead>
        <tr>
            <td><b>Photo</b></td>
            <td><b>Name</b></td>
            <td><b>Short Desc</b></td>
            <td><b>Long Desc</b></td>
            <td><b>Phone</b></td>
            <td><b>Price</b></td>
            <td><b>Action</b></td>
            <td><b>Chkbox</b></td>
        </tr>
        </thead>
        <c:forEach items="${adsModel.ads}" var="adv" varStatus="advLoop">
            <tr>
                <td><img height="40" width="40" src="/image/${adv.photo.id}" /></td>
                <td>${adv.name}</td>
                <td>${adv.shortDesc}</td>
                <td>${adv.longDesc}</td>
                <td>${adv.phone}</td>
                <td>${adv.price}</td>
                <td><a href="/delete?id=${adv.id}">Delete</a></td>
                <td><form:checkbox path="ids" value="${adv.photo.id}"/></td>
            </tr>
        </c:forEach>
    </table>
    </div>
    <input type="submit" class="btn btn-default" value="delete checked">
    </form:form>

    <form class="form-inline" role="form" action="/backet">
        <input type="submit" class="btn btn-default" value="Backet">
    </form>
    <form class="form-inline" role="form" action="/add_page" method="post">
        <input type="submit" class="btn btn-default" value="Add new">
    </form>
</div>
</body>
</body>
</html>