<%@page import="com.liferay.portal.kernel.log.LogFactoryUtil"%>
<%@page import="com.liferay.portal.kernel.log.Log"%>
<%@page import="com.liferay.portal.kernel.search.Field"%>
<%@page import="com.liferay.portal.kernel.util.GetterUtil"%>
<%@page import="com.liferay.portal.kernel.search.Document"%>
<%@page import="java.util.List"%>
<%@page import="com.liferay.portal.kernel.search.Hits"%>
<%@page import="com.liferay.portal.kernel.search.Indexer"%>
<%@page import="com.liferay.portal.kernel.search.SearchContext"%>
<%@page import="com.liferay.portal.kernel.exception.SystemException"%>
<%@page import="com.liferay.petra.executor.PortalExecutorConfig"%>
<%@page import="com.liferay.portal.kernel.exception.PortalException"%>
<%@page import="com.aimdek.assignment.service.AuthorLocalServiceUtil"%>
<%@page import="com.aimdek.assignment.service.AuthorLocalService"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.liferay.portal.kernel.search.IndexerRegistryUtil"%>
<%@page import="com.liferay.portal.kernel.search.SearchContextFactory"%>
<%@page import="com.aimdek.assignment.model.Author" %>
<%@page import="com.liferay.portal.kernel.util.ListUtil" %>
<%@ include file="/init.jsp" %>


<portlet:renderURL var="addAuthorURL">
	<portlet:param name="mvcRenderCommandName" value="/author/add/edit" />
	<portlet:param name="authorId" value="-1" />
</portlet:renderURL>

<portlet:renderURL var="viewAuthorsURL">
	<portlet:param name="mvcRenderCommandName" value="/" />
</portlet:renderURL>

<aui:form action="${viewAuthorsURL}" name="fm" cssClass="mt-2">

	<div class="row">	
		<div class="col-md-8">
			<aui:input inlineLabel="left" label="" name="keywords" placeholder="search-authors" size="256" />
		</div>
		
		<div class="col-md-4">
			<aui:button type="submit" value="search" />
		</div>
	</div>

</aui:form>


<% 
	Log log = LogFactoryUtil.getLog(this.getClass());
	SearchContext searchContext = SearchContextFactory.getInstance(request);
	String keywords = (String)request.getParameter("keywords");
	searchContext.setKeywords(keywords);
	searchContext.setAttribute("paginationType","more");
	log.info("search keywords : " + keywords);
	Indexer<Author> indexer = IndexerRegistryUtil.getIndexer(Author.class);
	Hits hits = indexer.search(searchContext);
	log.info("hits count : " + hits.getLength());
	List<Author> authors = new ArrayList<Author>();
	
	for(int i = 0; i < hits.getDocs().length; i++){
		Document doc = hits.doc(i);
		
		long authorId = GetterUtil.getLong(doc.get(Field.ENTRY_CLASS_PK));
		
		Author author = null;
		
		try{
			author = AuthorLocalServiceUtil.getAuthor(authorId);
		}
		catch(PortalException pe){
		}
		catch(SystemException se){
		}
		
		authors.add(author);
	}
%>

<c:if test="${keywords.length()!=0}">
	<liferay-ui:header backURL="viewAuthorsURL" title="back" />
</c:if>

<c:if test="${canViewAddButton && keywords.length()==0}">
	<aui:button cssClass="mt-4" name="addAuthor" href="${addEditRender}" value="Add Author" />
</c:if>

<liferay-portlet:renderURL varImpl="iteratorURL" />
<liferay-ui:search-container delta="4" iteratorURL="<%=iteratorURL%>" total="<%=authors.size()%>" var="searchContainer" >
	<liferay-ui:search-container-results>
	<%
		List<Author> authorList = ListUtil.subList(authors,searchContainer.getStart(),searchContainer.getEnd());
		log.info("authors size : " + ListUtil.subList(authors,searchContainer.getStart(),searchContainer.getEnd()).size());
		searchContainer.setResults(authorList);
		searchContainer.setTotal(authors.size());
		log.info("searchcontainer results : " + searchContainer.getResults().size());
	%>
	</liferay-ui:search-container-results>
	<liferay-ui:search-container-row className="com.aimdek.assignment.model.Author" keyProperty="authorId" modelVar="authorrrrr">
		
		<liferay-ui:search-container-column-text name="Author Id" property="authorId"/>
		
		<liferay-ui:search-container-column-text name="Author Code" property="authorCode"/>
		
		<liferay-ui:search-container-column-text name="Author Name" property="authorName"/>
		
		<liferay-ui:search-container-column-text name="Author Birthday" property="authorBirthDay"/>
		
	</liferay-ui:search-container-row>
	<liferay-ui:search-iterator searchContainer="<%=searchContainer%>" />
</liferay-ui:search-container>
<br>
<aui:button name="addAuthor" href="${addAuthorURL}" value="Add Author"/>

<br/>
<br>
<table>
	<tr>
		<th><liferay-ui:message key="author.id" /></th>
		<th><liferay-ui:message key="author.code" /></th>
		<th><liferay-ui:message key="author.name" /></th>
		<th><liferay-ui:message key="actions" /></th>
	</tr>
	<c:forEach items="${authors}" var="author">
		<tr>
			<td>${author.authorId}</td>
			<td>${author.authorCode}</td>
			<td>${author.authorName}</td>
			<td>
			
				
				<liferay-ui:icon-menu direction="Left-side" icon="" markupView="lexicon" message="actions" showWhenSingleIcon="<%= true %>">
					<portlet:renderURL var="editAuthorURL">
						<portlet:param name="mvcRenderCommandName" value="/author/add/edit" />
						<portlet:param name="authorId" value="${author.authorId}" />
					</portlet:renderURL>
					<liferay-ui:icon message="edit" url="<%= editAuthorURL%>" />
					
					<portlet:actionURL name="/author/delete" var="deleteAuthorURL" >
						<portlet:param name="redirect" value="${viewAuthorsURL}" />
						<portlet:param name="authorId" value="${author.authorId}" />
					</portlet:actionURL>
					<liferay-ui:icon message="delete" url="<%= deleteAuthorURL%>" />	
				</liferay-ui:icon-menu>
			</td>
		</tr>
	</c:forEach>
</table> 