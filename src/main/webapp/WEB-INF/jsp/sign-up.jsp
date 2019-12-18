<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="edit-profile" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="card border-primary mb-3 mx-auto" style="width: 40rem;">
    <div class=" text-white bg-primary card-header"><i class="fa fa-user-o" aria-hidden="true"></i> Set your personal data</div>
    <div class="card-body ">
        <p>Please be aware, you can't change your name in future. So provide real name.</p>
        <form:form action="/sign-up" method="post" modelAttribute="signUpForm">
            <div class="form-group">
                <label for="firstName">First Name</label>
                <form:input path="firstName" type="text" class="form-control" id="firstName" aria-describedby="emailHelp" placeholder="Enter first name"  required="required"/>
                <br>
                <form:errors path="firstName" cssClass="alert alert-danger" element="div"/>
            </div>
            <div class="form-group">
                <label for="lastName">Second name</label>
                <form:input path="lastName" type="text" class="form-control" id="lastName" aria-describedby="emailHelp" placeholder="Enter second name"  required="required"/>
                <br>
                <form:errors path="lastName" cssClass="alert alert-danger" element="div"/>
            </div>
            <div class="form-group">
                <label for="email">Email</label>
                <form:input path="email" type="email" maxlength="100" class="form-control" id="email" aria-describedby="emailHelp" placeholder="Enter valid email" required="required"/>
                <br>
                <form:errors path="email" cssClass="alert alert-danger" element="div"/>
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <form:input path="password" type="password" class="form-control" id="password" placeholder="Password" required="required"/>
            </div>
            <div class="form-group">
                <label for="confirmPassword">Password Confirmation</label>
                <form:input path="confirmPassword" type="password" class="form-control" id="confirmPassword" placeholder="Repeat password" required="required"/>
                <br>
                <form:errors path="password" cssClass="alert alert-danger" element="div"/>
            </div>
            <button type="submit" class="btn btn-success">Registration</button>
        </form:form>
    </div>
</div>
