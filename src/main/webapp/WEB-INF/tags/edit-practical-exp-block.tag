<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c"        uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form"    uri="http://www.springframework.org/tags/form"%>

<%@ attribute name="index"   required="true" type="java.lang.Object"%>
<%@ attribute name="practic" required="false" type="com.myresume.entity.Practic"%>

<div id="ui-item-${index}" class="container ui-item skill-item border m-1">
    <div class="value-container">
        <button type="button" class="close" onclick="$('#ui-item-${index }').remove();">
            <span aria-hidden="true">&times;</span>
        </button>
    </div>
    <div class="form-group">
        <div class="row">
            <div class="col">
                <div style="margin-bottom: 10px;">
                    <p><b>Position</b></p>
                    <input name="items[${index}].position" class="form-control" type="text"
                           value="${practic.position}"/>
                    <br>
                    <form:errors path="items[${index}].position" cssClass="alert alert-danger" element="div"/>
                </div>
                <div class="row">
                    <div class="col">
                        <b>Begin month</b><br>
                        <input name="items[${index}].beginMonth" class="form-control" type="number" min="1" max="12"
                               value="${practic.beginMonth}"/>
                        <br>
                    </div>
                    <div class="col">
                        <b>Begin year</b><br>
                        <input name="items[${index}].beginYear" class="form-control" type="number" min="2000"
                               value="${practic.beginYear}"/>

                    </div>
                </div>
            </div>

            <div class="col">
                <div style="margin-bottom: 10px;">
                    <p><b>Company</b></p>
                    <input name="items[${index}].company" class="form-control" type="text" value="${practic.company}"/>
                    <br>
                    <form:errors path="items[${index}].company" cssClass="alert alert-danger" element="div"/>
                </div>

                <div class="row">
                    <div class="col">
                        <b>Finish month</b><br>
                        <input name="items[${index}].finishDateMonth" class="form-control" type="number" min="1" max="12"
                               value="${practic.finishDateMonth}"/>

                    </div>
                    <div class="col">
                        <b>Finish year</b><br>
                        <input name="items[${index}].finishDateYear" class="form-control" type="number" min="2000"
                               value="${practic.finishDateYear}"/>
                    </div>

                </div>
            </div>
        </div>

        <div class="row">
            <dic class="coll" style="width: 100%; padding: 0.375rem 0.75rem;">
                <form:errors path="items[${index}]" cssClass="alert alert-danger" element="div"/>
            </dic>
        </div>

        <div class="row">
            <div class="col" >
                <b>Responsibility/Achievement</b><br>
                <textarea class="form-control" name="items[${index }].responsibilities"
                          id="items${index }responsibilities" style="margin-bottom: 10px;"
                          required="required" rows="4">${practic.responsibilities }</textarea>
                <br>
                <form:errors path="items[${index}].responsibilities" cssClass="alert alert-danger" element="div"/>
            </div>
        </div>

        <div class="row">
            <div class="col">
                <b>Demo</b><br>
                <input name="items[${index}].demo" class="form-control" type="text" value="${practic.demo}"/>
                <br>
                <form:errors path="items[${index}].demo" cssClass="alert alert-danger" element="div"/>
            </div>
            <div class="col">
                <b>Source code</b><br>
                <input name="items[${index}].src" class="form-control" type="text" value="${practic.src}"/>
                <br>
                <form:errors path="items[${index}].src" cssClass="alert alert-danger" element="div"/>
            </div>
        </div>
    </div>

</div>