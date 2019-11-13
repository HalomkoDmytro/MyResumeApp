<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c"        uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form"    uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="resume"    tagdir="/WEB-INF/tags"%>

<%@ attribute name="language" required="false" type="com.myresume.entity.Language"%>

<div id="ui-item-${index}" class="container ui-item skill-item border m-1">
    <div class="value-container ui-ite">
        <button type="button" class="close" onclick="$('#ui-item-${index }').remove();">
            <span aria-hidden="true">&times;</span>
        </button>
    </div>

    <div class="form-group">
        <div class="row">
            <div class="col">
                <b>Language</b><br>
                <input type="text" value="${language.name}">
            </div>
            <div class="col">
                <b>Type</b><br>
                <select>
                    <c:forEach var="languageType" items="${languageTypes}">
                        <option value="${languageType}" ${languageType== language.languageType ? 'selected' : ' '}>${languageType}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="col">
                <b>Level(From Beginner to Proficiency)</b><br>
                <div class="range">
                    <input class="form-control" type="range" min="0" max="6" steps="1" value="3">
                </div>

            </div>
        </div>
    </div>
</div>