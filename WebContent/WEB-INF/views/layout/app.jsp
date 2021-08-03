<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>製品管理システム</title>
<link rel="stylesheet" href="<c:url value='/css/reset.css' />">
<link rel="stylesheet" href="<c:url value='/css/style.css' />">
</head>
<body>
    <div id="wrapper">
        <div id="header">
            <div id="header_menu">
                <h1>
                    <a href="<c:url value='/' />">製品管理システム</a>
                </h1>
                &nbsp;&nbsp;&nbsp;
                <c:if test="${sessionScope.login_user != null}">
                    <c:if test="${sessionScope.login_user.privilege == 1}">
                        <a href="<c:url value='/products/new' />">製品登録</a>&nbsp;
                    </c:if>
                    <a href="<c:url value='/products/index' />">製品一覧</a>&nbsp;
                    <a href="<c:url value='/users/index' />">ユーザー</a>&nbsp;
                    <a href="<c:url value='/logout' />">ログアウト</a>&nbsp;
                </c:if>
            </div>
            <c:if test="${sessionScope.login_user != null}">
                <div id="user_name">
                    <c:out value="${sessionScope.login_user.name}" />
                    &nbsp;さん&nbsp;&nbsp;&nbsp;
                </div>
            </c:if>
        </div>
        <div id="content">${param.content}</div>
        <div id="footer">by Soichiro Sugiyama.</div>
    </div>
</body>
</html>