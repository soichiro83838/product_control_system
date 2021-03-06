<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" />
            <br />
        </c:forEach>
    </div>
</c:if>
<label for="name">名前</label>
<br />
<input type="text" name="name" value="${user.name}" />
<br />
<br />

<label for="email">メールアドレス</label>
<br />
<input type="text" name="email" value="${user.email}" />
<br />
<br />

<label for="password">パスワード</label>
<br />
<input type="password" name="password" />
<br />
<br />

<label for="privilege">権限</label>
<br />
<select name="privilege">
    <option value="0" <c:if test="${user.privilege == 0}"> selected</c:if>>一般</option>
    <option value="1" <c:if test="${user.privilege == 1}"> selected</c:if>>工場長</option>
</select>
<br />
<br />

<input type="hidden" name="_token" value="${_token}" />
<button type="submit">登録</button>
