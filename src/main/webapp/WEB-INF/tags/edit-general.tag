<%@ tag language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<form:form action="/edit/edit-personal-info" method="post" modelAttribute="profile">

<div>

    <div class="container">

        <div>
            <h2 class="text-center">${profile.firstName}&emsp;${profile.lastName}</h2>
        </div>
        <hr>

        <div class="row">
            <div class="col-2">
                <p class="text-right"><b>Photo</b></p>
            </div>

            <div class="col-7">
                <div class="ml-2 col-sm-6">
                    <img src="${profile.largePhoto == null || profile.largePhoto == '' ? 'https://placehold.it/80x80' : profile.largePhoto}"
                         id="preview" class="img-thumbnail"> <!-- TODO get image placeholder -->
                </div>
                <div id="msg"></div>
                <form method="post" id="image-form">
                    <input type="file" name="img[]" class="file" style="display: none" accept="image/*">
                    <div class="input-group my-3">
                        <input type="text" class="form-control" disabled placeholder="Upload File" id="file">
                        <div class="input-group-append">
                            <button type="button" class="browse btn btn-primary">Browse...</button>
                        </div>
                    </div>
                </form>

            </div>
            <div class="col-3">
                Please use real photo.<br>
                Use photo large then 400x400.<br>
                For preventing problem use JPG format image.<br>
            </div>
        </div>
    </div>

    <hr>
    <div>
        <div class="row">
            <div class="col-2">
                <p class="text-right"><b>Birthday</b></p>
            </div>

            <div class="col-7">
                <form:input path="birthDay" class="form-control datepicker" data-date-format="yyyy-mm-dd" id="inputBirthDay" placeholder="Example: 1990-12-31" required="required"/>
                <br>
                <form:errors path="birthDay" cssClass="alert alert-danger" element="div"/>
            </div>
            <div class="col-3">
                Birth day date in format: yyyy-mm-dd
            </div>
        </div>
    </div>

    <hr>
    <div>
        <div class="row">
            <div class="col-2">
                <p class="text-right"><b>Country</b></p>
            </div>

            <div class="col-7">
                <form:input path="country" class="form-control" type="text" id="inputCountry" placeholder="Example: Ukraine" required="required"/>
                <br>
                <form:errors path="country" cssClass="alert alert-danger" element="div"/>
            </div>
        </div>
    </div>
    <div>
        <div class="row">
            <div class="col-2">
                <p class="text-right"><b>City</b></p>
            </div>

            <div class="col-7">
                <form:input path="city" class="form-control" type="text" />
                <br>
                <form:errors path="city" cssClass="alert alert-danger" element="div"/>
            </div>
        </div>
    </div>

    <hr>
    <div>
        <div class="row">
            <div class="col-2">
                <p class="text-right"><b>Email</b></p>
            </div>

            <div class="col-7">
                <form:input path="email" class="form-control" type="text" />
                <br>
                <form:errors path="email" cssClass="alert alert-danger" element="div"/>
            </div>
            <div class="col-3">
                Preferred domain is @gamil.com.<br>
                Recommended use real name in email address.<br>
                Not recommended use domain of your current employer.<br>
            </div>
        </div>
    </div>

    <hr>
    <div>
        <div class="row">
            <div class="col-2">
                <p class="text-right"><b>Phone number</b></p>
            </div>

            <div class="col-7">
                <form:input path="phone" class="form-control" type="text" />
                <br>
                <form:errors path="phone" cssClass="alert alert-danger" element="div"/>
            </div>
            <div class="col-3">
                Should be valid phone number.<br>
                You should be reade answer for unknown number call.<br>
            </div>
        </div>
    </div>

    <hr>
    <div>
        <div class="row">
            <div class="col-2">
                <p class="text-right"><b>Info</b></p>
            </div>

            <div class="col-7">
                <form:input path="info" class="form-control" type="text" />
                <br>
                <form:errors path="info" cssClass="alert alert-danger" element="div"/>
            </div>
            <div class="col-3">
                Looking position.<br>
            </div>
        </div>
    </div>

    <hr>
    <div>
        <div class="row">
            <div class="col-2">
                <p class="text-right"><b>Experience</b></p>
            </div>
            <div class="col-7">
                <form:textarea path="summary" rows="10" class="form-control"/>
                <br>
                <form:errors path="summary" cssClass="alert alert-danger" element="div"/>
            </div>
            <div class="col-3">
                Briefly describe you experience in this area.<br>
            </div>
        </div>
    </div>

    <hr>
    <div class="text-center">
        <input type="submit" class="btn btn-primary" value="Save">
    </div>
</div>
</form:form>
