<%@ tag language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="card border-primary mb-3">
    <div class=" text-white bg-primary card-header">
        <i class="fa fa-crosshairs" aria-hidden="true"></i> Objective
        <c:if test="${pageContext.request.userPrincipal.name != null}">
            <a href="/edit/edit-personal-info" class="float-right" style="color: white">[Edit]</a>
        </c:if>
    </div>
    <div class="card-body ">
<!--        <h5 class="card-title">Primary card title</h5>-->
        <p class="card-text">${profile.summary}</p>
    </div>
</div>