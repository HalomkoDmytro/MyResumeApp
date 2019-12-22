<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c"        uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form"    uri="http://www.springframework.org/tags/form"%>

<%@ attribute name="index"   required="true" type="java.lang.Object"%>
<%@ attribute name="course" required="false" type="com.myresume.entity.Course"%>

<div id="ui-item-${index}" class="container ui-item skill-item border m-1">
    <div class="value-container">
        <button type="button" class="close" onclick="location.href='javascript:resume.ui.removeBlock(${index})'">
            <span aria-hidden="true">&times;</span>
        </button>
    </div>

    <div class="form-group">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <div class="row">
            <div class="col">
                <b>Name course</b><br>
                <input name="items[${index}].name" class="form-control" type="text" value="${course.name}"/>
                <br>
                <form:errors path="items[${index}].name" cssClass="alert alert-danger" element="div"/>
            </div>

            <div class="col">
                <b>School name/Platform</b><br>
                <input name="items[${index}].school" class="form-control" type="text" value="${course.school}"/>
                <br>
                <form:errors path="items[${index}].school" cssClass="alert alert-danger" element="div"/>
            </div>

            <div class="col">
                <b>Finish Date</b><br>

                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="">Month and Year</span>
                    </div>
                    <input name="items[${index}].finishDateMonth" type="number" min="1" max="12" class="form-control" value="${course.finishDate != null ? course.finishDateMonth : ''}">
                    <input name="items[${index}].finishDateYear" type="number" min="2000" class="form-control" value="${course.finishDate != null ? course.finishDateYear : 'not finished'}">
                </div>

            </div>
        </div>
    </div>

</div>