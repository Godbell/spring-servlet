<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<%@ taglib uri="jakarta.tags.functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Bean Validation</title>
</head>
<body>
<h1>Bean Validation</h1>
<%--@elvariable id="user" type="validation.User"--%>
<form:form
        modelAttribute="user"
        action="${pageContext.request.contextPath }/ex02" method="post">
    <label for="name">name:</label>
    <form:input path="name"/>
    <form:errors path="name"/>
    <br><br>

    <label for="email">email:</label>
    <form:input path="email"/>
    <form:errors path="email"/>
    <br><br>

    <label for="password">password:</label>
    <form:password path="password"/>
    <form:errors path="password"/>
    <br><br>

    <input type="submit" value="sign up">
</form:form>
</body>
</html>