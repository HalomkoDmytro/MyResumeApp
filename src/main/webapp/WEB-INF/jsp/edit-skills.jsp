<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>

<div class="card border-primary mb-3 mx-auto" style="width: 40rem;">
    <div class=" text-white bg-primary card-header">Edit skills</div>
    <div class="card-body ">
        <select name="category" class="form-control">
            <c:forEach var="category" items="${skillCategories}">
                <option value="${category.id}">${category.category}</option>
            </c:forEach>
        </select>
    </div>
</div>