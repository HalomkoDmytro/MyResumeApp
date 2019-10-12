<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>

<div class="card border-primary mb-3 mx-auto" style="width: 40rem;">
    <div class=" text-white bg-primary card-header"><i class="fa fa-user-o" aria-hidden="true"></i> Set your personal data</div>
    <div class="card-body ">
        <form>
            <div class="form-group">
                <label for="inputLogin">Login</label>
                <input type="text" class="form-control" id="inputLogin" placeholder="UID or Email or Phone">
            </div>
            <div class="form-group">
                <label for="inputPassword">Password</label>
                <input type="password" class="form-control" id="inputPassword" placeholder="Password">
            </div>
            <div class="form-group form-check">
                <input type="checkbox" class="form-check-input" id="rememberMe">
                <label class="form-check-label" for="rememberMe">Remember Me</label>
            </div>
            <button type="submit" class="btn btn-success">Sign In</button>
        </form>
        <hr>
        <div class="text-center">
            <a href="#" >Restore asses</a>
        </div>
    </div>
</div>