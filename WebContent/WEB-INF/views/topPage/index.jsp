<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <br />
        <br />
        <center>
            <h2>製品管理システムへようこそ</h2>
        </center>&nbsp;
        <c:if test="${sessionScope.login_user == null}">
            <center>
                <a href="<c:url value='/login' />">ログイン</a>
            </center>&nbsp;
            <br />
            <br />
            <center>
                <a href="<c:url value='/users/new' />">ユーザー登録</a>
            </center>&nbsp;
            <br />
            <br />
        </c:if>
    </c:param>
</c:import>