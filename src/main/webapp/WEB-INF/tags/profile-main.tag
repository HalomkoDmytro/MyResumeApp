<%@ tag language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>

<div class="card border-primary mb-3">
    <img src="../imgages/TrevorEvans.jpg" class="card-img-top"/>
    <div class="card-body ">
        <h2 class="text-center">${profile.firstName}&nbsp;${profile.lastName}</h2>
        <h6 class="text-center">Location</h6>
        <h6 class="text-center">
            <strong>Age: </strong>28, <strong>Birthday: </strong> ${profile.birthDay}
        </h6>
    </div>

    <ul class="list-group list-group-flush" >
        <li class="list-group-item"><i class="fa fa-envelope" aria-hidden="false"></i> <a><i class="fab fa-affiliatetheme"></i>email&nbsp;</a>${profile.email}</li>
        <li class="list-group-item"><i class="fa fa-phone" aria-hidden="true"></i> <a>phone&nbsp;</a>${profile.phone}</li>
        <li class="list-group-item"><i class="fa fa-skype" aria-hidden="true"></i> <a>skype&nbsp;</a>${contacts.skype}</li>
        <li class="list-group-item"><i class="fa fa-github" aria-hidden="true"></i> <a>git&nbsp;</a>${contacts.github}</li>
        <li class="list-group-item"><i class="fa fa-paper-plane" aria-hidden="true"></i> <a>telegram&nbsp;</a>${contacts.telegram}</li>
        <li class="list-group-item"><i class="fa fa-facebook-official" aria-hidden="true"></i> <a>facebook&nbsp;</a>${contacts.facebook}</li>
    </ul>
</div>