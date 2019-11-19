<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c"        uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form"    uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="resume"    tagdir="/WEB-INF/tags"%>

<%@ attribute name="index" required="true" type="java.lang.Object"%>
<%@ attribute name="hobby" required="false" type="com.myresume.entity.Hobby"%>

<div id="ui-item-${index}" class="container ui-item skill-item ">
    <div class="value-container">
        <button type="button" class="close" onclick="$('#ui-item-${index }').remove();">
            <span aria-hidden="true">&times;</span>
        </button>
    </div>

    <div class="form-group">
        <div class="row">
            <div class="col">
                <input name="items[${index}].name" type="text" max="30" class="form-control" value="${hobby.name}"/>
                <br>
                <form:errors path="items[${index}].name" cssClass="alert alert-danger" element="div"/>
            </div>
        </div>
    </div>

</div>