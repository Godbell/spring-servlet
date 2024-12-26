<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="id" scope="request" type="java.lang.Long"/>
<c:set var="path" value="${pageContext.servletContext.contextPath}"/>

<!DOCTYPE html>
<html lang="ko">
<head>
    <title>방명록</title>
</head>
<body>
<form method="post" action="${path}/delete/${id}">
    <table>
        <tr>
            <td>비밀번호</td>
            <td><input type="password" name="password"></td>
            <td><input type="submit" value="삭제"></td>
        </tr>
    </table>
</form>
<a href="${path}">메인으로 돌아가기</a>
</body>
</html>
