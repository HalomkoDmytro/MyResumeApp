<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>

<div class="card border-primary mb-3 mx-auto" style="width: 40rem;">
    <div class=" text-white bg-primary card-header"><i class="fa fa-unlock-alt" aria-hidden="true"></i> New password for account</div>
    <div class="card-body ">
        <p>Please enter your new password</p>
        <form>
            <div class="form-group">
                <label for="inputPassword">Password</label>
                <input type="password" class="form-control" id="inputPassword" placeholder="Password">
            </div>
            <div class="form-group">
                <label for="inputPasswordConfirmation">Password Confirmation</label>
                <input type="password" class="form-control" id="inputPasswordConfirmation" placeholder="Repeat password">
            </div>
            <hr>
            <button type="submit" class="btn btn-primary text-center"><p>Update password</p></button>
        </form>
    </div>
</div>
