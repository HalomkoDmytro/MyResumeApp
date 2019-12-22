<%@ tag language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="card border-primary mb-3">
    <div class=" text-white bg-primary card-header"><i class="fa fa-arrows" aria-hidden="true"></i> Hobbies
        <c:if test="${pageContext.request.userPrincipal.name != null}">
            <a href="/edit/hobbies" class="float-right" style="color: white">[Edit]</a>
        </c:if>
    </div>
    <div class="card-body ">
        <c:forEach var="profileHobby" items="${profile.hobbies}" varStatus="loop">
            ${profileHobby.name}
            <c:if test="${!loop.last}">,&nbsp;</c:if>
        </c:forEach>
    </div>
</div>