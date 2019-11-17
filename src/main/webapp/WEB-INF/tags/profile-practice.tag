<%@ tag language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="card border-primary mb-3">
    <div class=" text-white bg-primary card-header"><i class="fa fa-road" aria-hidden="true"></i> Practice
    </div>
    <div class="card-body ">

        <ul class="timeline">
            <c:forEach var="practic" items="${profile.practices}">
                <li>
                    <p><b>${practic.company}</b></p>
                    <p>${practic.position}
                        <a href="#" class="float-right">${practic.beginDate}&nbsp
                            <c:if test="${not empty practic.finishDate}">
                                -&nbsp;${practic.finishDate}
                            </c:if>
                        </a>

                    </p>

                    <p>${practic.responsibilities}</p>
                    <p><b>Demo: </b><a href="${practic.demo}">${practic.demo}</a></p>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>