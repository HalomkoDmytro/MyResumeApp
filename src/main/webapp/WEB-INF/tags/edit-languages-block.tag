<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c"        uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form"    uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="resume"    tagdir="/WEB-INF/tags"%>

<%@ attribute name="index"   required="true" type="java.lang.Object"%>
<%@ attribute name="language" required="false" type="com.myresume.entity.Language"%>

<div id="ui-item-${index}" class="container ui-item skill-item border m-1">
    <div class="value-container ui-ite">
        <button type="button" class="close" onclick="location.href='javascript:resume.ui.removeBlock(${index})'">
            <span aria-hidden="true">&times;</span>
        </button>
    </div>

    <div class="form-group">
        <div class="row">
            <div class="col">
                <b>Language</b><br>
                <input name="items[${index}].name" type="text" value="${language.name}"/>
                <br>
                <form:errors path="items[${index}].name" cssClass="alert alert-danger" element="div"/>
            </div>
            <div class="col">
                <b>Type</b><br>
                <select  name="items[${index}].languageTypes">
                    <c:forEach var="languageType" items="${languageTypes}">
                        <option value="${languageType}" ${languageType== language.languageTypes ? 'selected' : ' '}>${languageType}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="col">
                <b>Level</b><br>
                <select  name="items[${index}].languageLevel">
                    <c:forEach var="languageLevel" items="${languageLevels}">
                        <option value="${languageLevel}" ${languageLevel== language.languageLevel ? 'selected' : ' '}>${languageLevel}</option>
                    </c:forEach>
                </select>

            </div>
        </div>
    </div>
</div>