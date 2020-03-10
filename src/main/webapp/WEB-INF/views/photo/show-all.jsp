<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!-- HEADER -->
<jsp:include page="../common/header.jsp"/>

<!-- MAIN CONTENT OF THE PAGE -->
<div class="container">

    <h3 class="mt-3 mb-3">${ pageTitle }
        <a href="${pageContext.request.contextPath }/photo/add"
           class="btn btn-primary float-right">Add Photo</a>
    </h3>

    <table class="table table-bordered">
        <thead class="thead-light">
        <tr>
            <th>ID</th>
            <th>Photo</th>
            <th>Caption</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${ photos.content }" var="photo">
        <tr>
            <th>${ photo.photoId } </th>
            <th>
                <img class="img-responsive" width="60px"
                     src="${pageContext.request.contextPath }/images/${photo.url}">
            </th>
            <th>${ photo.caption }</th>
            </c:forEach>
        </tbody>
    </table>

    <h6 class="text-muted text-right mb-3">Total Pages: ${photos.totalPages}</h6>

    <nav aria-label="Page navigation example">
        <ul class="pagination justify-content-end">
            <li class="page-item <c:if test="${photos.first}">disabled</c:if>">
                <a class="page-link" href="?page=${photos.number - 1}" tabindex="-1">Previous</a>
            </li>
            <c:forEach begin="${0}" end="${photos.totalPages - 1}" var="p">
                <li class="page-item  <c:if test="${photos.number == p}">active</c:if>">
                    <a class="page-link" href=" ?page=${p}">${p+1}</a>
                </li>
            </c:forEach>
            <li class=" page-item <c:if test="${photos.last}">disabled</c:if>">
                <a class="page-link" href="?page=${photos.number + 1}">Next</a>
            </li>
        </ul>
    </nav>
</div>

<!-- FOOTER -->
<jsp:include page="../common/footer.jsp"/>