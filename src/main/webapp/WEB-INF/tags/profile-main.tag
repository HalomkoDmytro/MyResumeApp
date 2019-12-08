<%@ tag language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="card border-primary mb-3">
    <img src="../imgages/TrevorEvans.jpg" class="card-img-top"/>
    <div class="card-body ">
        <h2 class="text-center">${profile.firstName}&nbsp;${profile.lastName}</h2>
        <h6 class="text-center">Location</h6>
        <h6 class="text-center">
            <strong>Age: </strong>28, <strong>Birthday: </strong> ${profile.birthDay}
        </h6>
    </div>

    <ul class="list-group list-group-flush" >
        <c:if test="${not empty profile.email}">
            <li class="list-group-item"><i class="fa fa-envelope" aria-hidden="false"></i> <i class="fab fa-affiliatetheme"></i>email&nbsp;<a href="tel:${profile.email}">${profile.email}</a></li>
        </c:if>
        <c:if test="${not empty contacts.skype}">
            <li class="list-group-item"><i class="fa fa-skype" aria-hidden="true"></i> skype&nbsp;${contacts.skype}</li>
        </c:if>
        <c:if test="${not empty profile.phone}">
            <li class="list-group-item"><i class="fa fa-phone" aria-hidden="true"></i> phone<a href="tel:${profile.phone}">&nbsp;${profile.phone}</a></li>
        </c:if>
        <c:if test="${not empty contacts.github}">
            <li class="list-group-item"><i class="fa fa-github" aria-hidden="true"></i> git<a href="${contacts.github}">&nbsp;${contacts.github}</a></li>
        </c:if>
        <c:if test="${not empty contacts.telegram}">
            <li class="list-group-item"><i class="fa fa-paper-plane" aria-hidden="true"></i> telegram&nbsp;${contacts.telegram}</li>
        </c:if>
        <c:if test="${not empty contacts.facebook}">
            <li class="list-group-item"><i class="fa fa-facebook-official" aria-hidden="true"></i> facebook&nbsp;${contacts.facebook}</li>
        </c:if>
    </ul>
</div>