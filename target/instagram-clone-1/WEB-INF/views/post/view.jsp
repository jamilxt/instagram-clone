<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!-- HEADER -->
<jsp:include page="../common/header.jsp"/>

<!-- MAIN CONTENT OF THE PAGE -->
<div class="container mt-4">

    <%--    ${postId}--%>

    <div class="card">
        <div class="card-body p-0">
            <div class="row">

                <div class="col-8 pr-0">
                    <div id="post_1_carouselExampleIndicators" class="carousel slide" data-ride="false"
                         data-wrap="false">
                        <ol class="carousel-indicators text-primary">
                            <li data-target="#post_1_carouselExampleIndicators" data-slide-to="0" class="active"></li>
                            <li data-target="#post_1_carouselExampleIndicators" data-slide-to="1"></li>
                            <li data-target="#post_1_carouselExampleIndicators" data-slide-to="2"></li>
                        </ol>
                        <div class="carousel-inner" style="height: 400px;">
                            <div class="carousel-item active">
                                <img class="d-block w-100 h-100" src="/images/course_cover.jpg" alt="First slide">
                            </div>
                            <div class="carousel-item">
                                <img class="d-block w-100  h-100" src="/images/img1.jpg" alt="Second slide">
                            </div>
                            <div class="carousel-item">
                                <img class="d-block w-100  h-100" src="/images/img2.jpg" alt="Third slide">
                            </div>
                        </div>
                        <a class="carousel-control-prev" href="#post_1_carouselExampleIndicators" role="button"
                           data-slide="prev">
                            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                            <span class="sr-only">Previous</span>
                        </a>
                        <a class="carousel-control-next" href="#post_1_carouselExampleIndicators" role="button"
                           data-slide="next">
                            <span class="carousel-control-next-icon" aria-hidden="true"></span>
                            <span class="sr-only">Next</span>
                        </a>
                    </div>

                    <div class="card-footer bg-white">

                        <a class="btn btn-outline-light" href="${pageContext.request.contextPath}/">
                            <%--                            <i class="fa fa-1x fa-heart text-muted nav-item"></i>--%>
                            <i class="fa fa-1x fa-heart text-danger nav-item"></i> <span class="text-dark ml-1">1</span>
                        </a>

                        <a class="btn btn-outline-light" href="${pageContext.request.contextPath}/">
                            <i class="fa fa-1x fa-comment text-muted nav-item"></i>
                        </a>

                        <a class="btn btn-outline-light" href="${pageContext.request.contextPath}/">
                            <i class="fa fa-1x fa-share text-muted nav-item"></i>
                        </a>

                        <a class="btn btn-outline-light float-right" href="${pageContext.request.contextPath}/">
                            <i class="fa fa-1x fa-bookmark text-muted nav-item"></i>
                            <%--                            <i class="fa fa-1x fa-bookmark text-primary nav-item"></i>--%>
                        </a>

                    </div>
                </div>
                <div class="col-4 m-0 pl-0 border-left">
                    <div class="card-header bg-white pr-0">
                        <a href="/jamilxt"
                           class="font-weight-bold text-dark">
                            <img src="${pageContext.request.contextPath}/images/${authUser.propic}"
                                 class="rounded-circle border border-light align-middle mr-3"
                                 width="30px" height="30px">
                        </a>
                        <a href="${pageContext.request.contextPath}/<sec:authentication property="principal.username" />"
                           class="font-weight-bold text-dark">
                            ${authUser.username}
                        </a>

                        <!-- Post Options -->
                        <div class="modal fade" id="post_1_options" tabindex="-1" role="dialog"
                             aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                            <div class="modal-dialog modal-dialog-centered" role="document">
                                <div class="modal-content">
                                    <div class="list-group text-center" style="font-size: 16px">
                                        <a href="/accounts/edit"
                                           class="list-group-item list-group-item-action text-danger font-weight-bold">
                                            Report Inappropriate
                                        </a>
                                        <a href="/accounts/edit"
                                           class="list-group-item list-group-item-action text-danger font-weight-bold">
                                            Unfollow
                                        </a>
                                        <a href="/accounts/edit" class="list-group-item list-group-item-action">
                                            Go to post
                                        </a>
                                        <a href="/logout" class="list-group-item list-group-item-action">
                                            Share
                                        </a>
                                        <a href="/logout" class="list-group-item list-group-item-action">
                                            Copy Link
                                        </a>
                                        <a href="/logout" class="list-group-item list-group-item-action">
                                            Share
                                        </a>
                                        <button class="list-group-item list-group-item-action" data-dismiss="modal">
                                            Cancel
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <button type="button" class="btn btn-outline float-right" data-toggle="modal"
                                data-target="#post_1_options">
                            <i class="fa fa-1x fa-ellipsis-h text-muted nav-item"></i>
                        </button>
                    </div>

                    <div class="addScroll">

                        <%-- Caption --%>
                        <div class="row card-body pb-0">
                            <div class="col-1 mr-3">
                                <a href="/jamilxt"
                                   class="font-weight-bold text-dark">
                                    <img src="${pageContext.request.contextPath}/images/${authUser.propic}"
                                         class="rounded-circle border border-light align-middle"
                                         width="30px" height="30px">
                                </a>
                            </div>
                            <div class="col">
                                <a href="${pageContext.request.contextPath}/<sec:authentication property="principal.username" />"
                                   class="font-weight-bold text-dark">
                                    ${authUser.username}
                                </a> Caption
                                <h6 class="small text-muted mt-2">1h</h6>
                            </div>
                        </div>

                        <%-- Comments --%>
                        <div class="row card-body pb-0">
                            <div class="col-1 mr-3">
                                <a href="/jamilxt"
                                   class="font-weight-bold text-dark">
                                    <img src="${pageContext.request.contextPath}/images/${authUser.propic}"
                                         class="rounded-circle border border-light align-middle"
                                         width="30px" height="30px">
                                </a>
                            </div>
                            <div class="col">
                                <a href="/jamilxt"
                                   class="font-weight-bold text-dark">
                                    jamilxt
                                </a> Comment 1
                                <h6 class="small text-muted mt-2">1h</h6>
                            </div>
                        </div>

                    </div>

                    <%--  Add Comment --%>
                    <div class="card-footer bg-white">
                        <div class="row">
                            <div class="col-9">
                        <textarea placeholder="Add a comment" class="form-text form-control border-0 small pl-0"
                                  rows="1"></textarea>
                            </div>
                            <div class="col-3">
                                <input type="submit" value="Post" class="btn btn-light text-center">
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>

    <h6 class="text-secondary font-weight-bold mt-5">More posts from <a href="/jamilxt">jamilxt</a></h6>

    <div class="row">
        <div class="col-4"> gallery from profile page
        </div>
    </div>

</div>


<!-- FOOTER -->
<jsp:include page="../common/footer.jsp"/>