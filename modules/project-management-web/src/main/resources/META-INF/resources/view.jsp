<%@page import="java.util.List"%>
<%@page import="com.liferay.portal.kernel.util.LocaleUtil"%>
<%@page import="com.liferay.portal.workflow.kaleo.service.KaleoDefinitionLocalServiceUtil"%>
<%@page import="com.liferay.portal.workflow.kaleo.model.KaleoDefinition"%>

<%@ include file="/init.jsp" %>
<portlet:actionURL name="saveProduct" var="addProductURL">
 <portlet:param name="mvcActionCommand" value="saveProduct" /> 
</portlet:actionURL>

 <portlet:renderURL var="productListUrl" > 
 <portlet:param name="mvcRenderCommandName" value="productList"/>
 <portlet:param name="page" value="view"/>
 </portlet:renderURL>



<%
List<KaleoDefinition> kaleoDefinitions = KaleoDefinitionLocalServiceUtil.getKaleoDefinitions(-1, -1);

kaleoDefinitions.get(0).getTitle(LocaleUtil.getDefault());
%>


<aui:form action="<%= addProductURL %>" name="<portlet:namespace />fm">
        <aui:fieldset>
            <aui:input name="name"></aui:input>
             <aui:select name="workflowId">
            <% 
            for(KaleoDefinition k:kaleoDefinitions){
            	
            %>
            <aui:option value="<%=k.getKaleoDefinitionId() %>"><%=k.getTitle(LocaleUtil.getDefault()) %></aui:option>
           <%
           }
           %> 
          
          </aui:select>
        </aui:fieldset>

        <aui:button-row>
            <aui:button type="submit"></aui:button>
            <aui:button type="cancel" onClick="<%= productListUrl.toString() %>"></aui:button>
      
        </aui:button-row>
</aui:form>