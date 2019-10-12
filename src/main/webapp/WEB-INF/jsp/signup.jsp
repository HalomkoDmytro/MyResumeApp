<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>

<div class="card border-primary mb-3 mx-auto" style="width: 40rem;">
    <div class=" text-white bg-primary card-header"><i class="fa fa-user-o" aria-hidden="true"></i> Set your personal data</div>
    <div class="card-body ">
        <p>Please be aware, you can't change your name in future. So provide real name.</p>
        <form>
            <div class="form-group">
                <label for="firstName">First Name</label>
                <input type="text" class="form-control" id="firstName" aria-describedby="emailHelp" placeholder="Enter first name">
            </div>
            <div class="form-group">
                <label for="secondName">Second name</label>
                <input type="text" class="form-control" id="secondName" aria-describedby="emailHelp" placeholder="Enter second name">
            </div>
            <div class="form-group">
                <label for="inputPassword">Password</label>
                <input type="password" class="form-control" id="inputPassword" placeholder="Password">
            </div>
            <div class="form-group">
                <label for="inputPasswordConfirmation">Password Confirmation</label>
                <input type="password" class="form-control" id="inputPasswordConfirmation" placeholder="Repeat password">
            </div>
            <button type="submit" class="btn btn-success">Registration</button>
        </form>
    </div>
</div>
