<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!-- HEADER -->
<jsp:include page="../common/header.jsp"/>

<%-- if user is not logged in, redirect--%>
<c:if test="${pageContext.request.userPrincipal.name != null}">
    <meta http-equiv="Refresh" content="0; url=${pageContext.request.contextPath }">
</c:if>

<!-- MAIN CONTENT OF THE PAGE -->
<div class="container mt-5" style="max-width: 400px">
    <div class="card card-body">
        <c:if test="${pageContext.request.userPrincipal.name == null}">
            <h3 class="pt-3 pb-4 text-center font-weight-bolder">instagram_clone</h3>
            <c:if test="${error == 'true'}">
                <div class="alert alert-danger" role="alert" id="success-alert">Wrong username or
                    password
                </div>
            </c:if>
            <form action="${pageContext.request.contextPath }/login-processing"
                  method="POST">
                <div class="form-group">
                    <input
                            type="text" class="form-control" id="exampleInputEmail1"
                            aria-describedby="emailHelp" placeholder="Username"
                            value="Admin"
                            name="username">
                </div>
                <div class="form-group">
                    <input
                            type="password" class="form-control" id="exampleInputPassword1"
                            placeholder="Password" name="password" value="secret">
                </div>
                <button type="submit"
                        class="btn btn-primary btn-sm btn-block">Log in
                </button>
            </form>
        </c:if>
    </div>

    <div class="card card-body mt-3 border-danger">
        <div class="card-text text-center">
            Don't have an account? <a href="/accounts/emailsignup">Sign Up</a>
        </div>
    </div>
</div>

<!-- FOOTER -->
<jsp:include page="../common/footer.jsp"/>