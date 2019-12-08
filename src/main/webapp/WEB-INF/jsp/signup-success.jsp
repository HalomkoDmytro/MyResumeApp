<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>

<div class="card border-primary mb-3 mx-auto" style="width: 40rem;">
    <div class=" text-white bg-primary card-header"><i class="fa fa-thumbs-o-up" aria-hidden="true"></i> SignUp success
    </div>
    <div class="card-body ">
        <p>After finishing registration your profile will be accessible by link</p>
        <p>Your UID: ${profile.uid}. You may use this UID to enter your personal office.</p>
        <hr>
        <div class="text-center">
            <form action="/edit/edit-personal-info" method="get">
                <button type="submit" class="btn btn-success">Complete registration</button>
            </form>
        </div>
    </div>
</div>