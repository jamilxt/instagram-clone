<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!-- GLOBAL HEADER -->
<jsp:include page="common/header.jsp"/>


<!-- COURSE COVER IMAGE -->

<!-- MAIN CONTENT OF THE PAGE -->
<div class="container">
    <div class="row">
        <div class="col-3 pt-5"><img
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
                    <div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                        <div class="modal-dialog modal-dialog-centered" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLongTitle">Modal title</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    ...
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                    <button type="button" class="btn btn-primary">Save changes</button>
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
</div>


<!-- GLOBAL FOOTER -->
<jsp:include page="common/footer.jsp"/>