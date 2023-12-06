<%@page import="com.liferay.portal.kernel.util.LocaleUtil"%>
<%@page import="com.liferay.portal.workflow.kaleo.service.KaleoDefinitionLocalServiceUtil"%>
<%@page import="com.adj.project.management.model.Product"%>
<%@page import="java.util.List"%>
<%@page import="com.adj.project.management.service.ProductLocalServiceUtil"%>
<%@include file="/init.jsp" %>
 <portlet:renderURL var="productForm" >
  <portlet:param name="mvcRenderCommandName" value="productList"/>
 <portlet:param name="page" value="product_list"/>
 </portlet:renderURL>
<%
List<Product> products=ProductLocalServiceUtil.getProducts(-1, -1);

%>
<aui:button name="AddProduct" value="Add Product" onClick="<%= productForm.toString() %>"></aui:button>
<table class="table table-striped">

<thead>
    <tr>
     
      <th scope="col">Name</th>
      <th scope="col">Workflow Name</th>
   
    </tr>
  </thead>
  <tbody>
<%
for(Product product:products){
	
%>
<tr>
<td><%=product.getName() %></td>
<td><%=KaleoDefinitionLocalServiceUtil.getKaleoDefinition(product.getWorkflowId()).getTitle(LocaleUtil.getDefault())%></td>
</tr>

<%} %>
 </tbody>
</table>