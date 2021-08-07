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
        <h2>ユーザー 一覧</h2>
        <table id="user_list">
            <tbody>
                <tr>
                    <th>メールアドレス</th>
                    <th>氏名</th>
                    <th>権限</th>
                    <th>登録日時</th>
                    <c:if test="${sessionScope.login_user.privilege == 1}">
                        <th>削除</th>
                    </c:if>
                </tr>
                <c:forEach var="user" items="${users}" varStatus="status">
                    <tr class="row${status.count % 2}">
                    <c:choose>
                            <c:when test="${user.delete_flag == 1}">
                            </c:when>
                            <c:otherwise>
                        <td><c:out value="${user.email}" /></td>
                        <td><c:out value="${user.name}" /></td>
                        <td><c:choose>
                                        <c:when test="${user.privilege == 1}">工場長</c:when>
                                        <c:otherwise>一般</c:otherwise>
                                    </c:choose></td>
                                     <td><c:out value="${user.date}" /></td>
                                <c:if test="${sessionScope.login_user.privilege == 1}">
                                    <td><a
                                        href="<c:url value='/users/destroy?id=${user.id}' />">このユーザーを削除する</a>
                                </c:if>
                               </c:otherwise>
                        </c:choose>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <div id="pagination">
            （全 ${users_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((users_count - 1) / 15) + 1}"
                step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/users/index?page=${i}' />"><c:out
                                value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
    </c:param>
</c:import>