<%@ tag language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="card border-primary mb-3">
    <div class=" text-white bg-primary card-header"><i class="fa fa-graduation-cap" aria-hidden="true"></i> Education
        <c:if test="${pageContext.request.userPrincipal.name != null}">
            <a href="/edit/education" class="float-right" style="color: white">[Edit]</a>
        </c:if></div>
    <div class="card-body ">
        <ul class="timeline">
            <c:forEach var="education" items="${profile.educations}">
                <li>
                    <p><b>${education.faculty}</b></p>
                    <p>${education.university}
                        <a href="#" class="float-right">${education.beginYear}&nbsp;<c:if test="${not empty education.finishYear}">-&nbsp;${education.finishYear}
                        </c:if></a>
                    </p>
                    <p>${education.summary}</p>

                </li>
            </c:forEach>
        </ul>
    </div>
</div>