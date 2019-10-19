<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="resume" tagdir="/WEB-INF/tags" %>

<div class="container">
    <div class="row">
        <div class="col-lg-4 col-sm-6">
            <resume:profile-main/>
            <div class="d-none d-sm-block">
                <resume:profile-languages/>
                <resume:profile-hobbies/>
                <resume:profile-additional-info/>
            </div>
        </div>

        <div class="col-lg-8 col-sm-6">
            <resume:profile-objective/>
            <resume:profile-skills/>
            <resume:profile-practice/>
            <resume:profile-sertificates/>
            <resume:profile-courses/>
            <resume:profile-education/>
        </div>
    </div>
</div>