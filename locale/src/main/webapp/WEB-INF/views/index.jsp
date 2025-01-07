<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<%@ taglib uri="jakarta.tags.functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
    <title>Locale Example</title>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <link href="${path}/assets/css/index.css" type="text/css" rel="stylesheet">
    <script>
        window.addEventListener('load', function () {
            document
                .querySelectorAll('#languages a')
                .forEach(function (el) {
                    el.addEventListener('click', function (evt) {
                        evt.preventDefault();
                        // console.log(this.getAttribute('data-lang'));
                        document.cookie =
                            "lang=" + this.getAttribute('data-lang') + ";" +        // name=value
                            "path=" + "${path}" + ";" +    // path
                            "max-age=" + (30 * 24 * 60 * 60);                               // max-age
                        // reload
                        location.reload();
                    });
                });
        });
    </script>
</head>
<body>
<h1><spring:message code="index.title"/></h1>
<div id="languages">
    <c:choose>
        <c:when test='${lang == "en"}'>
            <a href="" data-lang="ko">KR</a>
            <a href="" data-lang="en" class="active">EN</a>
        </c:when>
        <c:otherwise>
            <a href="" data-lang="ko" class="active">KR</a>
            <a href="" data-lang="en">EN</a>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>