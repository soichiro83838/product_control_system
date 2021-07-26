<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" /><br />
        </c:forEach>

    </div>
</c:if>
<label for="date">日付</label><br />
<input type="date" name="date" value="<fmt:formatDate value='${part.date}' pattern='yyyy-MM-dd' />" />
<br /><br />

<label for="partname">部品名</label><br />
<input type="text" name="partname" value="${part.partname}" />
<br /><br />

<label for="image">画像</label><br />
<input type="text" name="image" value="${part.image}" />
<br /><br />

<label for="modelnumber">型番</label><br />
<input type="text" name="modelnumber" value="${part.modelnumber}" />
<br /><br />

<label for="manufacture">メーカー</label><br />
<input type="text" name="manufacture" value="${part.manufacture}" />
<br /><br />

<input type="hidden" name="_token" value="${_token}" />
<button type="submit">投稿</button>