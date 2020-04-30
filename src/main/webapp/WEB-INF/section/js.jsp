<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
    <c:when test="${production}">
        <script src="/static/js/minify/resume-script.js?v=${jsCommonVersion}"></script>
    </c:when>
    <c:otherwise>
        <script src="/static/js/app.js"></script>
        <script src="/static/js/jQuery.js"></script>

        <script src="/static/js/handlebars-v4.5.3.js"></script>
        <script src="/static/js/messages.jsp"></script>
    </c:otherwise>
</c:choose>
<%--<script src="../static/js/bootstrap.bundle.js"></script>--%>
<%--<script src="../static/js/bootstrap.js"></script>--%>
<%--<script src="webjars/jquery/2.2.4/jquery.min.js"></script>--%>
