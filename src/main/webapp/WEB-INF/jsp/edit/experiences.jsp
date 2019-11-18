<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="edit-profile" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="resume" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="container">
    <div class="card border-primary mb-3 mx-auto">
        <div class="card-header border-primary">
            <edit-profile:edit-nav-tabs/>
        </div>

        <form:form action="/edit/practical-experience" method="post" modelAttribute="practicForm">
            <div class="card-body">
                <div>
                    <h2 class="text-center">Practical experience</h2>
                </div>

                <hr>

                <div id="ui-block-container">
                    <c:forEach var="practic" items="${practicForm.items}" varStatus="status">
                        <resume:edit-practical-exp-block index="${status.index}" practic="${practic}"/>
                    </c:forEach>
                </div>

                <hr>

                <div>
                    <a href="javascript:void(0);">+Add</a>
                </div>

                <hr>
                <div class="text-center">
                    <input type="submit" class="btn btn-primary" value="Save">
                </div>
            </div>
        </form:form>

    </div>

</div>