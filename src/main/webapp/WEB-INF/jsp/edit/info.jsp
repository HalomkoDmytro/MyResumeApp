<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="edit-profile" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<div class="container">
    <div class="card border-primary mb-3 mx-auto">
        <div class="card-header border-primary">
            <edit-profile:edit-nav-tabs/>
        </div>
        <form:form action="/edit/info" method="post" modelAttribute="infoForm">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

            <div class="card-body">
                <div>
                    <h2 class="text-center">Info</h2>
                </div>

                <hr>

                <div id="ui-block-container">
                    <div class="col">
                        <form:textarea path="info" rows="10" class="form-control"/>
                        <br>
                        <form:errors path="info" cssClass="alert alert-danger" element="div"/>
                    </div>
                </div>

                <hr>
                <div class="text-center">
                    <input type="submit" class="btn btn-primary" value="Save">
                </div>
            </div>
        </form:form>
    </div>
</div>