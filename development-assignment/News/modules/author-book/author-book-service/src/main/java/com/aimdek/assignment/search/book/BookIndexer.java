package com.aimdek.assignment.search.book;

import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.aimdek.assignment.model.Book;
import com.aimdek.assignment.service.BookLocalService;
import com.aimdek.assignment.service.BookLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery.AddCriteriaMethod;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery.AddOrderCriteriaMethod;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery.PerformActionMethod;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery.PerformCountMethod;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.BaseIndexer;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.IndexWriterHelper;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.transaction.TransactionConfig;
import com.liferay.portal.kernel.util.GetterUtil;

@Component(immediate = true, service = Indexer.class)
public class BookIndexer extends BaseIndexer<Book>{

	public static final String CLASS_NAME  = Book.class.getName();
	
	public BookIndexer() {
		
		setDefaultSelectedFieldNames(
				Field.COMPANY_ID, Field.ENTRY_CLASS_NAME, Field.ENTRY_CLASS_PK,
				Field.GROUP_ID, Field.MODIFIED_DATE);
		setFilterSearch(false);
			setPermissionAware(false);
		
	}

	@Override
	public String getClassName() {

		return CLASS_NAME;
	}

	@Override
	protected void doDelete(Book book) throws Exception {
		
		deleteDocument(book.getCompanyId(), book.getBookId());
		
	}

	@Override
	protected Document doGetDocument(Book book) throws Exception {
		
		Document document = getBaseModelDocument(Book.class.getName(), book);
		
		document.addNumber("bookId", book.getBookId());
		document.addText("bookCode", book.getBookCode());
		document.addNumber("bookName", book.getBookName());
		
		return document;
	}

	@Override
	protected Summary doGetSummary(Document document, Locale locale, String snippet, PortletRequest portletRequest,
			PortletResponse portletResponse) throws Exception {
		
		Summary summary = createSummary(document);
		summary.setMaxContentLength(200);
		return summary;
	}

	@Override
	protected void doReindex(String className, long classPK) throws Exception {
		
		Book book = BookLocalServiceUtil.getBook(classPK);
		doReindex(book);
		
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		
		long companyId = GetterUtil.getLong(ids[0]);
		reindexBooks(companyId);
		
	}

	@Override
	protected void doReindex(Book book) throws Exception {
		Document document = getDocument(book);
		
		
		indexWriterHelper.updateDocument(
				getSearchEngineId(), book.getCompanyId(), document, 
				isCommitImmediately());
	}
	
	private void reindexBooks(long companyId) throws PortalException {
		
		final IndexableActionableDynamicQuery indexableActionableDynamicQuery = 
				bookLocalService.getIndexableActionableDynamicQuery();
		
		indexableActionableDynamicQuery.setCompanyId(companyId);
		
		indexableActionableDynamicQuery.setPerformActionMethod(
				new ActionableDynamicQuery.PerformActionMethod<Book>() {
					
					@Override
					public void performAction(Book entry) {
						
						try {
							Document document = getDocument(entry);
							indexableActionableDynamicQuery.addDocuments(document);
						}
						catch(PortalException pe) {
							if(_log.isWarnEnabled()) {
								_log.warn(
										"Unable to index Book" + entry.getBookId(),
										pe);
							}
						}
						
					}
					
				});
	}
	
	private static final Log _log = LogFactoryUtil.getLog(BookIndexer.class);
	
	@Reference
	private IndexWriterHelper indexWriterHelper;
	@Reference 
	private BookLocalService bookLocalService;
}
