<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
    <head>
        <meta charset="UTF-8">
        <title>品質管理システム</title>
        <link rel="stylesheet" href="<c:url value='/css/reset.css' />">
        <link rel="stylesheet" href="<c:url value='/css/style.css' />">
    </head>
    <body>
        <div id="wrapper">
            <div id="header">
                <div id="header_menu">
                    <h1><a href="<c:url value='/' />">品質管理システム</a></h1>&nbsp;&nbsp;&nbsp;
                    <c:if test="${sessionScope.login_user != null}">
                        <a href="<c:url value='/users/index' />">ユーザー詳細</a>&nbsp;
                    </c:if>
                </div>
                <c:if test="${sessionScope.login_user != null}">
                    <div id="user_name">
                        <c:out value="${sessionScope.login_user.name}" />&nbsp;さん&nbsp;&nbsp;&nbsp;
                    </div>
                </c:if>
            </div>
            <div id="content">
                ${param.content}
                 <c:if test="${sessionScope.login_user != null}">
             <center><a href="<c:url value='/parts/create' />">部品登録</a></center>&nbsp;
             <br /><br />
             <center><a href="<c:url value='/parts/index' />">部品一覧</a></center>&nbsp;
              <br /><br />
              <center><a href="<c:url value='/logout' />">ログアウト</a></center>
              <br /><br />
              </c:if>
            </div>
            <div id="footer">
                by Soichiro Sugiyama.
            </div>
        </div>
    </body>
</html>