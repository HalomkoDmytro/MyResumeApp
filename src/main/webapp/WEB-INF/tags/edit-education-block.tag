<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c"        uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form"    uri="http://www.springframework.org/tags/form"%>

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
                <input class="form-control" type="text" value="${education.university}">
                <br>
                <b>Start Year</b><br>
                <input class="form-control" type="text" value="${education.beginYear}">
            </div>

            <div class="col">
                <b>School name/Platform</b><br>
                <input class="form-control" type="text" value="${education.faculty}">
                <br>
                <b>Finish Year</b><br>
                <input class="form-control" type="text" value="${education.finishYear}">
            </div>
        </div>
    </div>
</div>