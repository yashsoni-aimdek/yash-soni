package com.aimdek.assignment.search.author;

import java.util.Locale;

import org.osgi.service.component.annotations.Component;

import com.aimdek.assignment.model.Author;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.search.spi.model.index.contributor.ModelDocumentContributor;

@Component(immediate = true, property = "indexer.class.name=com.aimdek.assignment.model.Author", service = ModelDocumentContributor.class)
public class AuthorModelDocumentContributor implements ModelDocumentContributor<Author>{

	@Override
	public void contribute(Document document, Author author) {
		try {
			document.addDate(Field.MODIFIED_DATE, author.getModifiedDate());
			
			Locale defaultLocale = PortalUtil.getSiteDefaultLocale(author.getGroupId());
			
			String localizedTitle = LocalizationUtil.getLocalizedName(Field.TITLE, defaultLocale.toString());
			
			document.addText(localizedTitle, author.getAuthorName());
			document.addText("authorCode", author.getAuthorCode());
			document.addText("authorName", author.getAuthorName());
			document.addDate("authorBirthday", author.getAuthorBirthDay());
			document.addNumber("authorId", author.getAuthorId());
		}
		catch(PortalException pe) {
			if(LOG.isWarnEnabled()) {
				LOG.warn("Unable to index author " + author.getAuthorId(), pe);
			}
		}
		
	}
	
	private static final Log LOG = LogFactoryUtil.getLog(AuthorModelDocumentContributor.class);
	
}
