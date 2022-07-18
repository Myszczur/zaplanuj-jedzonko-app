<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="row dashboard-nowrap">
    <ul class="nav flex-column long-bg">
        <li class="nav-item">
            <a class="nav-link" href="/app/dashboard">
                <span>Pulpit</span>
                <i class="fas fa-angle-right"></i>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/app/recipe/list">
                <span>Przepisy</span>
                <i class="fas fa-angle-right"></i>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/app/plan/list">
                <span>Plany</span>
                <i class="fas fa-angle-right"></i>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/app/useredit">
                <span>Edytuj dane</span>
                <i class="fas fa-angle-right"></i>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link disabled" href="/app/passwordedit">
                <span>Zmień hasło</span>
                <i class="fas fa-angle-right"></i>
            </a>
        </li>
        <c:if test="${superAdmin =='1'}">
            <li class="nav-item">
                <a class="nav-link" href="/app/userlist">
                    <span>Użytkownicy</span>
                    <i class="fas fa-angle-right"></i>
                </a>
            </li>
        </c:if>
    </ul>