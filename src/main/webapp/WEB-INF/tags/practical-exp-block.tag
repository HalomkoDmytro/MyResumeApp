<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c"        uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form"    uri="http://www.springframework.org/tags/form"%>

<%@ attribute name="practic" required="false" type="com.myresume.entity.Practic"%>

<div id="ui-item-${index}" class="container ui-item skill-item border m-1">
    <div class="value-container">
        <button type="button" class="close" onclick="$('#ui-item-${index }').remove();">
            <span aria-hidden="true">&times;</span>
        </button>
        <!--        <textarea name="items[${index }].value" class="form-control pull-right" required="required" rows="2">${skill.value }</textarea>-->
    </div>
    <div class="form-group">
        <div class="row">
            <div class="col">
                <div style="margin-bottom: 10px;">
                    <p><b>Position</b></p>
                    <input class="form-control" type="text" value="${practic.position}">
                </div>
                <div class="row">
                    <div class="col">
                        <b>Begin month</b><br>
                        <input type="text" class="form-control" value="${practic.beginMonth}"/>
                    </div>
                    <div class="col">
                        <b>Begin year</b><br>
                        <input type="text" class="form-control" value="${practic.beginYear}"/>
                    </div>
                </div>
            </div>

            <div class="col">
                <div style="margin-bottom: 10px;">
                    <p><b>Position</b></p>
                    <input class="form-control" type="text" value="${practic.company}">
                </div>

                <div class="row">
                    <div class="col">
                        <b>Finish month</b><br>
                        <input class="form-control" type="text" value="${practic.finishDateMonth}"/>
                    </div>
                    <div class="col">
                        <b>Finish year</b><br>
                        <input class="form-control" type="text" value="${practic.finishDateYear}"/>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col">
                <b>Responsibility/Achievement</b><br>
                <textarea rows="8" class="form-control">${practic.responsibilities}</textarea>
            </div>
        </div>

        <div class="row">
            <div class="col">
                <b>Demo</b><br>
                <input class="form-control" type="text" value="${practic.demo}"/>
            </div>
            <div class="col">
                <b>Source code</b><br>
                <input class="form-control" type="text" value="${practic.src}"/>
            </div>
        </div>
    </div>

</div>