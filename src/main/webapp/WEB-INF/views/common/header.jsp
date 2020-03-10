<%@ taglib prefix="sec"
           uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>${ pageTitle }</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath }/css/bootstrap.min.css"/>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath }/css/main.css"/>

    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"/>

    <script
            src="https://code.jquery.com/jquery-3.4.1.min.js"
            integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
            crossorigin="anonymous"></script>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.css"/>

    <script
            src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>
    <script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
</head>
<body class="bg-light">

<sec:authorize access="isAuthenticated()">
<nav class="navbar navbar-expand navbar-light bg-white border-bottom">
    <div class="container">

        <a class="navbar-brand pl-3" href="${pageContext.request.contextPath }/">instagram_clone</a>

        <input type="text" id="search_data" placeholder="Search" autocomplete="off" class="form-control w-25"/>

        <ul class="navbar-nav">

            <li class="nav-item ml-3">
                    <%--                <a class="btn btn-outline-light" href="${pageContext.request.contextPath}/">--%>
                <button type="button" class="btn btn-outline-light" data-toggle="modal"
                        data-target="#imageUpload">
                    <i class="fa fa-1x fa-plus text-dark nav-item"></i>
                </button>
                    <%--                </a>--%>
            </li>

            <!-- Modal -->
            <div class="modal fade" id="imageUpload" tabindex="-1" role="dialog"
                 aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLongTitle">Post New Image</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                                <%--                            <p><input type="file" accept="image/*" name="image" id="file" onchange="loadFile(event)"--%>
                                <%--                                      style="display: none;"></p>--%>
                                <%--                            <p>--%>
                                <%--                                <label for="file" style="cursor: pointer;">--%>
                                <%--                                    <img id="output" class="card-img"/>--%>
                                <%--                                </label>--%>
                                <%--                            </p>--%>

                                <%--                            <script>--%>
                                <%--                                var loadFile = function (event) {--%>
                                <%--                                    var image = document.getElementById('output');--%>
                                <%--                                    image.src = URL.createObjectURL(event.target.files[0]);--%>
                                <%--                                };--%>
                                <%--                            </script>--%>
                        </div>
                    </div>
                </div>
            </div>

            <li class="nav-item ml-3">
                <a class="btn btn-outline-light" href="${pageContext.request.contextPath}/">
                    <i class="fa fa-1x fa-home text-dark nav-item"></i>
                </a>
            </li>

            <li class="nav-item ml-3">

                <a class="btn btn-outline-light" href="${pageContext.request.contextPath}/explore">
                    <i class="fa fa-1x fa-compass text-dark nav-item"></i>
                </a>
            </li>

            <li class="nav-item ml-3">

                <div class="dropdown">
                    <a class="btn btn-outline-light" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown"
                       aria-haspopup="true" aria-expanded="false">
                        <i class="fa fa-1x fa-heart-o text-dark nav-item"></i>
                    </a>

                    <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">


                    </div>
                </div>

                    <%--                <a href="${pageContext.request.contextPath}">--%>
                    <%--                    <i class="fa fa-2x fa-heart-o text-dark nav-item"></i>--%>
                    <%--                </a>--%>
            </li>

            <li class="nav-item ml-3">

                <a href="${pageContext.request.contextPath}/<sec:authentication property="principal.username"/>"
                   class="btn btn-outline-light">
                    <img src="${pageContext.request.contextPath}/images/${authUser.propic}"
                         class="rounded-circle border border-dark align-middle"
                         width="26px">
                </a>
            </li>
        </ul>

    </div>
</nav>
</sec:authorize>
