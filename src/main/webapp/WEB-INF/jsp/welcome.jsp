<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
    <div class="card mb-3" style="max-width: 840px; padding: 10px;">
        <h1>Create resume in few clicks</h1>
        <img src="" class="card-img" alt="welcome img">
    </div>
    <div id="profileContainer" data-profile-total="${page.totalPages }" data-profile-number="${page.number }">
        <jsp:include page="fragment/profile-items.jsp"/>
    </div>

    <c:if test="${page.number < page.totalPages - 1}">
        <div id="loadMoreContainer" class="card mb-3" style="max-width: 840px;">
            <a href="javascript:resume.moreProfiles();" class="btn btn-primary pull-center form-control">Load more</a>
        </div>
        <div id="loadMoreIndicator" class="text-center" style="display: none">
            <img src="/imgages/large-loading.gif" alt="loading..."/>
        </div>
    </c:if>
</div>
