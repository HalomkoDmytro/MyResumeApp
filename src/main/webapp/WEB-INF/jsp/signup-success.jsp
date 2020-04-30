<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>

<div class="card border-primary mb-3 mx-auto" style="width: 40rem;">
    <div class=" text-white bg-primary card-header"><i class="fa fa-thumbs-o-up" aria-hidden="true"></i> SignUp success
    </div>
    <div class="card-body ">
        <p>Account with UID: ${profile.uid} and EMAIL ${profile.email} was created.</p>
        <p>You may use this UID or EMAIL to enter your personal office.</p>
        <p>We send activation link to your EMAIL. To activate account you should follow the link from this mail</p>
        <hr>
        <%--        <div class="text-center">--%>
        <%--            <form action="/edit/edit-personal-info" method="get">--%>
        <%--                <button type="submit" class="btn btn-success">Complete registration</button>--%>
        <%--            </form>--%>
        <%--        </div>--%>

        <div class="text-center">
            <form action="/welcome" method="get">
                <button type="submit" class="btn btn-success">OK</button>
            </form>
        </div>
    </div>
</div>