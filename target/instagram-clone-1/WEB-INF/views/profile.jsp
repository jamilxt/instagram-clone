<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!-- GLOBAL HEADER -->
<jsp:include page="common/header.jsp"/>
<style>
    .nav-pills .nav-link {
        border-radius: 0;
        color: #a0a0a0;
        padding-top: 15px;
        margin-left: 30px;
        margin-right: 30px;
        font-size: small;
    }

    .nav-pills .nav-link.active {
        color: black;
        border-top: 2px solid;
        background: none;
    }
</style>

<!-- COURSE COVER IMAGE -->

<!-- MAIN CONTENT OF THE PAGE -->
<div class="container">
    <div class="row">
        <div class="col-3 pt-5 text-center"><img
                src="${pageContext.request.contextPath}/images/default.png"
                width="150px" height="150px"
                alt="" class="rounded-circle"></div>
        <div class="col-9 pt-5">
            <div>
                <h4><sec:authentication property="principal.username"/> <a
                        href="${pageContext.request.contextPath}/accounts/edit"
                        class="btn btn-sm btn-outline-secondary ml-3">Edit Profile</a>
                    <button type="button" class="btn btn-light ml-2" data-toggle="modal"
                            data-target="#exampleModalCenter">
                        <i class="fa fa-1x fa-cog text-dark nav-item"></i>
                    </button>

                    <!-- Modal -->
                    <div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog"
                         aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                        <div class="modal-dialog modal-dialog-centered" role="document">
                            <div class="modal-content">
                                <div class="list-group text-center" style="font-size: 16px">
                                    <a href="/accounts/edit" class="list-group-item list-group-item-action">
                                        Change Password
                                    </a>
                                    <a href="#" class="list-group-item list-group-item-action">
                                        Report a Problem
                                    </a>
                                    <a href="/logout" class="list-group-item list-group-item-action">
                                        Logout
                                    </a>
                                    <button class="list-group-item list-group-item-action" data-dismiss="modal">
                                        Cancel
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </h4>
            </div>
            <div class="d-flex">
                <div class="pr-5"><strong>153</strong> posts</div>
                <div class="pr-5"><strong>23k</strong> followers</div>
                <div class="pr-5"><strong>212</strong> following</div>
            </div>
            <div class="pt-4 font-weight-bold">freeCodeCamp.org</div>
            <div>We're a global community of millions of people learning to code together. We're an open source,
                donor-supported, 501(c)(3) nonprofit.
            </div>
            <div><a href="#">www.freecodecamp.org</a></div>
        </div>
    </div>


    <nav class="mt-5 border-top">
        <div class="nav nav-pills justify-content-center" id="nav-tab" role="tablist">
            <a class="nav-item nav-link active" id="nav-home-tab" data-toggle="tab" href="#nav-home" role="tab"
               aria-controls="nav-home" aria-selected="true">POSTS</a>
            <a class="nav-item nav-link" id="nav-profile-tab" data-toggle="tab" href="#nav-profile" role="tab"
               aria-controls="nav-profile" aria-selected="false">IGTV</a>
            <a class="nav-item nav-link" id="nav-contact-tab" data-toggle="tab" href="#nav-contact" role="tab"
               aria-controls="nav-contact" aria-selected="false">SAVED</a>
            <a class="nav-item nav-link" id="nav-about-tab" data-toggle="tab" href="#nav-about" role="tab"
               aria-controls="nav-about" aria-selected="false">TAGGED</a>
        </div>
    </nav>

    <div class="tab-content py-3 px-3 px-sm-0" id="nav-tabContent">
        <div class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
            POST
        </div>
        <div class="tab-pane fade" id="nav-profile" role="tabpanel" aria-labelledby="nav-profile-tab">
            IGTV
        </div>
        <div class="tab-pane fade" id="nav-contact" role="tabpanel" aria-labelledby="nav-contact-tab">
            SAVED
        </div>
        <div class="tab-pane fade" id="nav-about" role="tabpanel" aria-labelledby="nav-about-tab">
            TAGGED
        </div>
    </div>
</div>


<!-- GLOBAL FOOTER -->
<jsp:include page="common/footer.jsp"/>