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

        <a class="navbar-brand" href="${pageContext.request.contextPath }/">instagram_clone</a>

        <input type="text" id="search_data" placeholder="Search" autocomplete="off" class="form-control w-25"/>

        <ul class="navbar-nav">
            <li class="nav-item ml-4">
                <a href="${pageContext.request.contextPath}">
                    <i class="fa fa-2x fa-home text-dark nav-item"></i>
                </a>
            </li>

            <li class="nav-item ml-4">

                <a href="${pageContext.request.contextPath}">
                    <i class="fa fa-2x fa-compass text-dark nav-item"></i>
                </a>
            </li>

            <li class="nav-item ml-4">

                <a href="${pageContext.request.contextPath}">
                    <i class="fa fa-2x fa-heart-o text-dark nav-item"></i>
                </a>
            </li>

            <li class="nav-item ml-4">

                <a href="${pageContext.request.contextPath}/<sec:authentication property="principal.username"/>">
                    <img src="${pageContext.request.contextPath}/images/default.png"
                         class="rounded-circle border border-dark align-middle"
                         width="30px">
                </a>
            </li>
        </ul>

    </div>
</nav>
</sec:authorize>
