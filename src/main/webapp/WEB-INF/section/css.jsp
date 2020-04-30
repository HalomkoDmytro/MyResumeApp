<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
    <c:when test="${production}">
        <link rel="stylesheet" href="/static/css/minify/resume-style.css?v=${cssCommonVersion}">
    </c:when>
    <c:otherwise>
        <link rel="stylesheet" href="/static/css/bootstrap.css">
        <link rel="stylesheet" href="/static/css/bootstrap-grid.css">
        <link rel="stylesheet" href="/static/css/bootstrap-reboot.css">
        <link rel="stylesheet" href="/static/css/timeline.css">
        <link rel="stylesheet" href="/static/css/font-awesome.css">
        <link rel="stylesheet" href="../static/css/app.css">
    </c:otherwise>
</c:choose>
