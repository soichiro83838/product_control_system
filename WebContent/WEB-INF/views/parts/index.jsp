<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>部品 一覧</h2>
        <table id="part_list">
            <tbody>
                <tr>
                    <th class="part_partname">部品名</th>
                    <th class="part_image">画像</th>
                    <th class="part_quality_statement">品質状況</th>
                    <th class="part_date">品質入力日時</th>
                    <th class="part_action">操作</th>
                </tr>
                <c:forEach var="part" items="${parts}" varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td class="part_partname"><c:out value="${part.name}" /></td>
                        <td class="part_image"><c:out value="${part.image}" /></td>
                        <td class="part_quality_statement">${part.quality_statement}</td>
                        <td class="part_date"><fmt:formatDate value='${part.part_date}' pattern='yyyy-MM-dd' /></td>
                        <td class="part_action"><a
                            href="<c:url value='/parts/show?id=${part.id}' />">詳細を見る</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <div id="pagination">
            （全 ${parts_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((parts_count - 1) / 15) + 1}"
                step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/parts/index?page=${i}' />"><c:out
                                value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
    </c:param>
</c:import>