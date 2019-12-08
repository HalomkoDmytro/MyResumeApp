<%@ tag language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="card border-primary mb-3">

    <div class=" text-white bg-primary card-header">Additional info
        <c:if test="${pageContext.request.userPrincipal.name != null}">
            <a href="/edit/info" class="float-right" style="color: white">[Edit]</a>
        </c:if>
    </div>
    <div class="card-body ">
        <p class="card-text">${profile.info}</p>
    </div>
</div>