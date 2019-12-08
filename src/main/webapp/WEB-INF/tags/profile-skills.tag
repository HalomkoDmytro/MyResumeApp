<%@ tag language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="card border-primary mb-3">
    <div class=" text-white bg-primary card-header"><i class="fa fa-code" aria-hidden="true"></i> Skills
        <c:if test="${pageContext.request.userPrincipal.name != null}">
            <a href="/edit/skills" class="float-right" style="color: white">[Edit]</a>
        </c:if></div>
    <div class="card-body ">
        <table class="table table-bordered">
            <thead>
            <tr>
                <th scope="col" >Category</th>
                <th scope="col" class="col-lg-2">First</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="skill" items="${profile.skills}">
                <tr>
                    <th scope="row">${skill.category}</th>
                    <td>${skill.value}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>