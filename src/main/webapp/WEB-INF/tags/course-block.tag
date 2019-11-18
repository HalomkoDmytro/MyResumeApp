<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c"        uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form"    uri="http://www.springframework.org/tags/form"%>

<%@ attribute name="course" required="false" type="com.myresume.entity.Course"%>

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
                <input class="form-control" type="text" value="${course.name}">
            </div>

            <div class="col">
                <b>School name/Platform</b><br>
                <input class="form-control" type="text" value="${course.school}">
            </div>

            <div class="col">
                <b>Finish Date</b><br>

                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="">Month and Year</span>
                    </div>
                    <input type="text" class="form-control" value="${course.finishDate != null ? course.finishDateMonth : 'not finished'}">
                    <input type="text" class="form-control" value="${course.finishDate != null ? course.finishDateYear : 'not finished'}">
                </div>

            </div>
        </div>
    </div>

</div>