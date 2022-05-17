<%@page import="com.aimdek.assignment.model.Book"%>
<%@page import="java.util.stream.Collectors"%>
<%@page import="com.liferay.portal.kernel.log.LogFactoryUtil"%>
<%@page import="com.liferay.portal.kernel.log.Log"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="java.util.List"%>
<%@page import="com.aimdek.assignment.service.AuthorLocalService"%>
<%@page import="com.liferay.portal.kernel.service.LayoutLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.service.LayoutLocalService"%>
<%@page import="com.liferay.portal.kernel.theme.ThemeDisplay"%>
<%@page import="com.liferay.portal.kernel.util.WebKeys"%>
<%@page import="com.aimdek.assignment.model.Author"%>
<%@ include file="init.jsp" %>

<link rel="stylesheet" href="css/multi-select.min.css">

<portlet:actionURL name="/author/add/edit" var="addAuthorURL"/>

<portlet:renderURL var="viewAuthorsURL">
	<portlet:param name="mvcRenderCommandName" value="/" />
</portlet:renderURL>

<% 
	Log log = LogFactoryUtil.getLog(this.getClass());
	ThemeDisplay td = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

	String friendyURL = "/books";
	
	String pName = "com_news_book_BookWebPortlet";
	
	List<String> bookList =(List<String>) request.getAttribute("authorBooks");
	long pid = LayoutLocalServiceUtil.getFriendlyURLLayout(td.getScopeGroupId(), true, friendyURL).getPlid();
	
%>

<liferay-ui:error key="author-code-required" message="author-code-required" />
<liferay-ui:error key="author-name-required" message="author-name-required" />

<aui:form action="${addAuthorURL}" method="post">
	<aui:model-context bean="${author}" model="<%=Author.class%>"/>
	<aui:input type="hidden" name="authorId"/>
	<aui:input type="text" name="authorCode" label="Author-code-Label"/>
	<aui:input type="text" name="authorName" label="Author-Name"/>
	
	<div>
		<div>
		 	<aui:select id="bookNameId" name="bookId" multiple="true">
		
			</aui:select>
		</div>
		<div>
			<c:forEach items="${allBooks}" var="book">
				<liferay-portlet:renderURL var="redirectToBook" plid="<%=pid%>" portletName="<%=pName%>">
					<liferay-portlet:param name="mvcRenderCommandName" value="/book/add/edit" />
					<liferay-portlet:param name="bookId" value="${book.bookId}" />
					<liferay-portlet:param name="authorId" value="${author.authorId}" />
					<liferay-portlet:param name="fromAuthor" value="true" />
				</liferay-portlet:renderURL>
				
				<aui:a href="${redirectToBook}">${book.bookName}</aui:a><br>
			</c:forEach>
		</div>
	</div>
	

<aui:input type="submit" name="Submit" value="Submit"/>
	<aui:button name="Cancel" href="${viewAuthorsURL}" value="Cancel"/>
</aui:form>

 <script type="text/javascript" >	

$(document).ready(function() {
	Liferay.Service(
			  '/news.book/get-all-book',
			  function(obj) {
			    console.log(obj);
			    
			    var list = $("#<portlet:namespace/>bookNameId");
			    var bookList = '<%= bookList%>';
			    for (let key of obj) {
					let opt = new Option(key.bookName, key.bookId);	
			    	if(bookList.includes(key.bookName)){
						opt.selected = "true";
					    list.append(opt);
			    	} else {
			    		list.append(opt);
			    	}
			    }
			    
			  }
			);
});
</script>