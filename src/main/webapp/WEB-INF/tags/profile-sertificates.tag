<%@ tag language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="card border-primary mb-3">
    <div class=" text-white bg-primary card-header"><i class="fa fa-file-o" aria-hidden="true"></i> Sertificates
        <c:if test="${pageContext.request.userPrincipal.name != null}">
            <a href="/edit/certificates" class="float-right" style="color: white">[Edit]</a>
        </c:if></div>
    <div class="card-body ">
        <p class="card-text">Some quick example text to build on the card title and make up the bulk of the
            card's content.</p>
    </div>
</div>