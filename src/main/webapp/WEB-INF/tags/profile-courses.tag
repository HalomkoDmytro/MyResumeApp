<%@ tag language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="card border-primary mb-3">
    <div class=" text-white bg-primary card-header"><i class="fa fa-rocket" aria-hidden="true"></i> Courses
        <c:if test="${pageContext.request.userPrincipal.name != null}">
            <a href="/edit/courses" class="float-right" style="color: white">[Edit]</a>
        </c:if></div>
    <div class="card-body ">
        <ul class="timeline">
            <c:forEach var="course" items="${profile.courses}">
                <li>
                    <p><b>${course.name}</b> <a href="#" class="float-right">${course.finishDate}&nbsp;</a></p>
                    <p>${course.school} </p>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>