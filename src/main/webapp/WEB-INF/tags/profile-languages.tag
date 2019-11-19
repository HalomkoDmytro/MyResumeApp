<%@ tag language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="card border-primary mb-3">
    <div class=" text-white bg-primary card-header"><i class="fa fa-language" aria-hidden="true"></i> Languages
    </div>
    <div class="card-body ">
        <c:forEach var="profileLanguage" items="${profile.languages}">
            <p><b>${profileLanguage.name} :</b>&nbsp;
                ${profileLanguage.languageLevel.name().toLowerCase()}&nbsp;
                (<i>${profileLanguage.languageTypes.name().toLowerCase()}</i>)</p>
        </c:forEach>
    </div>
</div>