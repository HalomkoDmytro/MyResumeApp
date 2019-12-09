<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="edit-profile" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="resume" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="container">
    <div class="card border-primary mb-3 mx-auto">
        <div class="card-header border-primary">
            <edit-profile:edit-nav-tabs/>
        </div>

        <form:form action="/edit/education" method="post" modelAttribute="educationForm">
            <div class="card-body">
                <div>
                    <h2 class="text-center">Professional courses </h2>
                </div>

                <hr>

                <div id="ui-block-container">
                    <c:forEach var="education" items="${educationForm.items}" varStatus="status">
                        <resume:edit-education-block index="${status.index}" education="${education}"/>
                    </c:forEach>
                </div>

                <hr>

                <div>
                    <a href="javascript:resume.ui.addBlock();">+Add</a>
                </div>

                <hr>
                <div class="text-center">
                    <input type="submit" class="btn btn-primary" value="Save">
                </div>
            </div>
        </form:form>

    </div>
</div>

<script id="ui-block-template" type="text/x-handlebars-template">
    <resume:edit-education-block index="{{blockindex}}" />
</script>