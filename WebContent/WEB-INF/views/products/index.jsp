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
        <h2>製品 一覧</h2>
        <table id="product_list">
            <tbody>
                <tr>
                    <th class="product_productname">製品名</th>
                    <th class="product_image">画像</th>
                    <th class="qualities_quality_statement">品質状況</th>
                    <th class="qualities_date">品質入力日時</th>
                    <th class="product_action">操作</th>
                </tr>
                <c:forEach var="product" items="${products}" varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td class="product_productname"><c:out
                                value="${product.productname}" /></td>
                        <td class="product_image"><img
                            src="https://test2195.s3.us-east-2.amazonaws.com/uploads/${product.image}"
                            style="width: 60%" alt="投稿画像"></td>
                        <td class="qualities_quality_statement">${qualities.quality_statement}</td>
                        <td class="qualities_date"><fmt:formatDate
                                value='${qualities.date}' pattern='yyyy-MM-dd' /></td>
                        <td class="product_action"><a
                            href="<c:url value='/products/show?id=${product.id}' />">詳細を見る</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <div id="pagination">
            （全 ${products_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((products_count - 1) / 15) + 1}"
                step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/products/index?page=${i}' />"><c:out
                                value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
    </c:param>
</c:import>