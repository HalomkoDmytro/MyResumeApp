<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<header style="margin-bottom: 20px;">
    <nav class="navbar navbar-light bg-light">
        <a class="navbar-brand" href="/welcome">My Resume</a>
        <form action="/profile/search" method="post" class="form-inline">
            <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search" name="query">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
            <c:if test="${pageContext.request.userPrincipal.name == null}">
                <a href="/sign-in">Sign in</a>|<a href="/sign-up">Registration</a>
            </c:if>&nbsp;
            <c:if test="${pageContext.request.userPrincipal.name != null}">
                <a href="/sign-out">Sign out</a>
                <input type="hidden"
                       name="${_csrf.parameterName}"
                       value="${_csrf.token}"/>
            </c:if>
        </form>

    </nav>
</header>