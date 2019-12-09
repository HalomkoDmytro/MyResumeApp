<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="edit-profile" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="resume" tagdir="/WEB-INF/tags" %>


<%--<%@ attribute name="hobby" required="false" type="com.myresume.entity.Hobby"%>--%>

<div class="container">
    <div class="card border-primary mb-3 mx-auto">
        <div class="card-header border-primary">
            <edit-profile:edit-nav-tabs/>
        </div>
        <form:form action="/edit/hobbies" method="post" modelAttribute="hobbyForm">
            <div class="card-body">
                <div>
                    <h2 class="text-center">Hobbies</h2>
                </div>

                <div id="ui-block-container">
                    <c:forEach var="hobby" items="${hobbyForm.items}" varStatus="status">
                        <resume:edit-hobby index="${status.index}" hobby="${hobby}"/>
                    </c:forEach>
                </div>
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
    <resume:edit-hobby index="{{blockindex}}" />
</script>