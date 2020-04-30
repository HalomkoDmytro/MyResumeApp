<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="edit-profile" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<form:form action="/edit/contacts" method="post" modelAttribute="contacts">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

    <div class="container">
        <div class="card border-primary mb-3 mx-auto">
            <div class="card-header border-primary">
                <edit-profile:edit-nav-tabs/>
            </div>

            <div class="card-body">
                <div>
                    <h2 class="text-center">Additional contacts</h2>
                </div>
                <hr>

                <div class="row">
                    <div class="col-9 border-right">
                        <div class="row">
                            <div class="col-2">
                                <p class="text-right"><b>Skype</b></p>
                            </div>

                            <div class="col-7">
                                <form:input path="skype" class="form-control" type="text" />
                                <br>
                                <form:errors path="skype" cssClass="alert alert-danger" element="div"/>
                            </div>
                        </div>
                    </div>


                    <div class="col-3">
                        1.Preferable skype contained your first and last name.<br>
                        2.Not recommended use creative skype name.<br>
                        3.If you haven't propriety skype name it might be the time to create one.<br>
                    </div>
                </div>

                <hr>

                <div class="row">
                    <div class="col-9 border-right">
                        <div class="row">
                            <div class="col-2">
                                <p class="text-right"><b>Facebook</b></p>
                            </div>

                            <div class="col-7">
                                <form:input path="facebook" class="form-control" type="text" />
                                <br>
                                <form:errors path="facebook" cssClass="alert alert-danger" element="div"/>
                            </div>
                        </div>

                        <hr>

                        <div class="row">
                            <div class="col-2">
                                <p class="text-right"><b>Linkedin</b></p>
                            </div>

                            <div class="col-7">
                                <form:input path="linkedin" class="form-control" type="text"/>
                                <br>
                                <form:errors path="linkedin" cssClass="alert alert-danger" element="div"/>
                            </div>
                        </div>

                        <hr>

                        <div class="row">
                            <div class="col-2">
                                <p class="text-right"><b>Github</b></p>
                            </div>

                            <div class="col-7">
                                <form:input path="github" class="form-control" type="text"/>
                                <br>
                                <form:errors path="github" cssClass="alert alert-danger" element="div"/>
                            </div>
                        </div>

                        <hr>

                        <div class="row">
                            <div class="col-2">
                                <p class="text-right"><b>Telegram</b></p>
                            </div>

                            <div class="col-7">
                                <form:input path="telegram" class="form-control" type="text"/>
                                <br>
                                <form:errors path="telegram" cssClass="alert alert-danger" element="div"/>
                            </div>
                        </div>
                    </div>


                    <div class="col-3">
                        1.For getting information about candidate HR could looking on social networks.
                        2.Setting profile check it didn't contained contradicting information.
                    </div>
                </div>

                <hr>
                <div class="text-center">
                    <input type="submit" class="btn btn-primary" value="Save">
                </div>
            </div>

        </div>
    </div>
</form:form>
