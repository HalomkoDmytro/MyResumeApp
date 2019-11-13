<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="edit-profile" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container">
    <div class="card border-primary mb-3 mx-auto">
        <div class="card-header border-primary">
            <edit-profile:edit-nav-tabs/>
        </div>

        <div class="card-body">
            <div>
                <h2 class="text-center">Certificates</h2>
            </div>
            <hr>

            <c:forEach var="certificate" items="${certificates}">
                <div class="row">
                    <img class="mx-auto" src="${certificate.smallUrl}" alt="${certificate.name}">
                </div>
                <hr>
            </c:forEach>



            <div class="row">
                <div class="col-xs-12">
                    <a href="javascript:void(0);">+Add</a>
                </div>
            </div>

            <hr>

            <div class="text-center">
                <input type="submit" class="btn btn-primary" value="Save">
            </div>
        </div>

    </div>
</div>