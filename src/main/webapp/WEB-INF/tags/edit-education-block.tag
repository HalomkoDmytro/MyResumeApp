<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c"        uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form"    uri="http://www.springframework.org/tags/form"%>

<%@ attribute name="index"   required="true" type="java.lang.Object"%>
<%@ attribute name="education" required="false" type="com.myresume.entity.Education"%>

<div id="ui-item-${index}" class="container ui-item skill-item border m-1">
    <div class="value-container">
        <button type="button" class="close" onclick="$('#ui-item-${index }').remove();">
            <span aria-hidden="true">&times;</span>
        </button>
    </div>

    <div class="form-group">
        <div class="row">
            <div class="col">
                <b>Name course</b><br>
                <input name="items[${index}].university" class="form-control" type="text" value="${education.university}">
                <br>
                <form:errors path="items[${index}].university" cssClass="alert alert-danger" element="div"/>
                <br>
                <b>Start Year</b><br>
                <input name="items[${index}].beginYear" min="2000" class="form-control" type="number" value="${education.beginYear}">
            </div>

            <div class="col">
                <b>School name/Platform</b><br>
                <input name="items[${index}].faculty" class="form-control" type="text" value="${education.faculty}">
                <br>
                <form:errors path="items[${index}].faculty" cssClass="alert alert-danger" element="div"/>
                <br>
                <b>Finish Year</b><br>
                <input name="items[${index}].finishYear" min="2000" class="form-control" type="number" value="${education.finishYear}">
            </div>
        </div>

        <div class="row">
            <dic class="coll" style="width: 100%; padding: 0.375rem 0.75rem;">
                <form:errors path="items[${index}]" cssClass="alert alert-danger" element="div"/>
            </dic>
        </div>
    </div>
</div>