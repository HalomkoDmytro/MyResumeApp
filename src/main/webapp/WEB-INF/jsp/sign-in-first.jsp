<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" 		uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" 		uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" 	uri="http://www.springframework.org/security/tags" %>
<%--<%@ taglib prefix="resume" 	tagdir="/WEB-INF/tags"%>--%>

<div class="card border-primary mb-3 mx-auto" style="width: 40rem;">
    <div class=" text-white bg-primary card-header"><i class="fa fa-user-o" aria-hidden="true"></i>First Sign in</div>
    <div class="card-body ">
        <form action="/first-sign-in/sign-in-first-handler" method="post">
            <input type="hidden"
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>
            <c:if test="${sessionScope.SPRING_SECURITY_LAST_EXCEPTION != null}">
                <div class="alert alert-danger" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                        ${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message }
                    <c:remove var="SPRING_SECURITY_LAST_EXCEPTION" scope="session" />
                </div>
            </c:if>
            <div class="form-group">
                <label for="uid">Login</label>
                <input type="text" class="form-control" id="uid" name="uid" placeholder="UID or Email or Phone" required>
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" name="password" class="form-control" id="password" placeholder="Password" required>
            </div>
            <div class="text-center">
                <button type="submit" class="btn btn-success">Sign In</button>
            </div>
        </form>
    </div>
</div>
