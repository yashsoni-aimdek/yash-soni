package com.aimdek.assignment.search.author;

import java.util.Locale;

import org.osgi.service.component.annotations.Component;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.search.spi.model.result.contributor.ModelSummaryContributor;

@Component(
		immediate = true,
		property = "indexer.class.name=com.aimdek.assignment.model.Author",
		service = ModelSummaryContributor.class
		)
public class AuthorModelSummaryContributor implements ModelSummaryContributor{

	@Override
	public Summary getSummary(Document document, Locale locale, String snippet) {
		
		Summary summary = createSummary(document);
		
		summary.setMaxContentLength(200);
		
		return summary;
	}
	
	private Summary createSummary(Document document) {
		String prefix = Field.SNIPPET + StringPool.UNDERLINE;
		
		String title = document.get(prefix + Field.TITLE, Field.TITLE);
		
		return new Summary(title, StringPool.BLANK);
	}

}
