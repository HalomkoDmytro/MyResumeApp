<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c"        uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form"    uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="resume"    tagdir="/WEB-INF/tags"%>

<%@ attribute name="index" required="true" type="java.lang.Object"%>
<%@ attribute name="skill" required="false" type="com.myresume.entity.Skill"%>

<div id="ui-item-${index}" class="row ui-item skill-item">
    <div class="col-xs-5 col-sm-4 col-md-2 form-group">

        <select name="items[${index}].category" class="form-control">
            <c:forEach var="skillCategorie" items="${skillCategories}">
                <option value="${skillCategorie.category }" ${skillCategorie.category== skill.category ? ' selected="selected"' : '' } > ${skillCategorie.category}</option>
            </c:forEach>
        </select>

    </div>
    <div class="col-xs-7 col-sm-8 col-md-10 value-container">
        <button type="button" class="close" onclick="location.href='javascript:resume.ui.removeBlock(${index})'">
            <span aria-hidden="true">&times;</span>
        </button>
        <textarea name="items[${index}].value" class="form-control pull-right" required="required" rows="2">${skill.value }</textarea>
    </div>
</div>
<div class="row">
    <div class="col-xs-5 col-sm-4 col-md-2 form-group">
    </div>
    <div class="col-xs-7 col-sm-8 col-md-10 value-container">
        <form:errors path="items[${index}].value" cssClass="alert alert-danger" element="div"/>
    </div>
</div>