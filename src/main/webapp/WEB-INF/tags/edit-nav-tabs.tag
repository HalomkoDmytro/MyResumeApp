<%@ tag language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>

<ul class="nav nav-tabs card-header-tabs">
    <li class="nav-item">
        <a class="nav-link ${tabName == 'editGeneral' ? 'active' : ''}" href="/edit/edit-personal-info">General</a>
    </li>
    <li class="nav-item">
        <a class="nav-link ${tabName == 'contacts' ? 'active' : ''}" href="/edit/contacts">Contacts</a>
    </li>
    <li class="nav-item">
        <a class="nav-link ${tabName == 'editSkill' ? 'active' : ''}" href="/edit/skills">Technical skills</a>
    </li>
    <li class="nav-item">
        <a class="nav-link ${tabName == 'experience' ? 'active' : ''}" href="/edit/practical-experience">Experiences</a>
    </li>
    <li class="nav-item">
        <a class="nav-link ${tabName == 'certificates' ? 'active' : ''}" href="/edit/certificates">Certificates</a>
    </li>
    <li class="nav-item">
        <a class="nav-link ${tabName == 'courses' ? 'active' : ''}" href="/edit/courses">Curses</a>
    </li>
    <li class="nav-item">
        <a class="nav-link ${tabName == 'education' ? 'active' : ''}" href="/edit/education">Education</a>
    </li>
    <li class="nav-item">
        <a class="nav-link ${tabName == 'languages' ? 'active' : ''}" href="/edit/languages">Languages</a>
    </li>
    <li class="nav-item">
        <a class="nav-link ${tabName == 'hobbies' ? 'active' : ''}" href="/edit/hobbies">Hobbies</a>
    </li>
    <li class="nav-item">
        <a class="nav-link ${tabName == 'additionalInfo' ? 'active' : ''}" href="/edit/info">Additional</a>
    </li>
</ul>