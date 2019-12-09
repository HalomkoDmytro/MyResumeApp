<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="resume" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="edit-profile" tagdir="/WEB-INF/tags" %>

<div class="container">
    <div class="card border-primary mb-3 mx-auto">
        <div class="card-header border-primary">
            <edit-profile:edit-nav-tabs/>
        </div>
        <div class="card-body">
            <h4 class="text-center">Technical skill</h4>
            <hr>
            <%--        <resume:form-display-error-if-invalid formName="skillForm" />--%>
            <div class="row" style="margin-bottom: 20px">
                <div class="col-xs-5 col-sm-4 col-md-2 text-center"><strong>Category</strong></div>
                <div class="col-xs-7 col-sm-8 col-md-10 text-center"><strong>Framework and technology</strong>
                </div>
            </div>
            <form:form action="/edit/skills" method="post" modelAttribute="skillForm">
                <div id="ui-block-container">
                    <c:forEach var="skill" items="${skillForm.items}" varStatus="status">
                        <resume:edit-skill-block index="${status.index}" skill="${skill}"/>
                    </c:forEach>
                </div>
                <div class="row">
                    <div class="col-xs-12">
                        <a href="javascript:resume.ui.addBlock()">+Add skill</a>
                    </div>
                </div>
                <hr>
                <div class="row">
                    <div class="col-xs-12 text-center" style="width: 100%;">
                        <input type="submit" class="btn btn-primary" value="Save">
                    </div>
                </div>
            </form:form>
        </div>
    </div>
</div>
<script id="ui-block-template" type="text/x-handlebars-template">
    <resume:edit-skill-block index="{{blockindex}}" />
</script>


