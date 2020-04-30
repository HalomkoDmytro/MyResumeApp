<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:forEach var="profile" items="${profiles}">
    <div class="card mb-3" style="max-width: 840px;">
        <div class="row no-gutters" style="padding: 10px">
            <div class="col-3">
                <img
                        src="/imgages/profile-placeholder.png"
<%--                        src="${profile.smallPhoto}" --%>
                        class="card-img" alt="profile img">
            </div>
            <div class="col-8">
                <div class="card-body">
                    <div class="card-title" style="width: 100%;">
                        <a href="/profile/${profile.uid}" class="btn btn-primary pull-right">Details</a>
                        <h5 class=" text-primary text-left"> ${profile.firstName}&nbsp;${profile.lastName}</h5>
                    </div>
                    <p class="card-text">${profile.city}&nbsp;${profile.country}</p>
                    <p class="card-text"> ${profile.objective} </p>
                </div>
            </div>
        </div>
    </div>
</c:forEach>