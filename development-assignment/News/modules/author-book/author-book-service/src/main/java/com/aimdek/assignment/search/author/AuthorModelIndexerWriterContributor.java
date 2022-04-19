package com.aimdek.assignment.search.author;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.aimdek.assignment.model.Author;
import com.aimdek.assignment.service.AuthorLocalService;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.search.batch.BatchIndexingActionable;
import com.liferay.portal.search.batch.DynamicQueryBatchIndexingActionableFactory;
import com.liferay.portal.search.spi.model.index.contributor.ModelIndexerWriterContributor;
import com.liferay.portal.search.spi.model.index.contributor.helper.ModelIndexerWriterDocumentHelper;

@Component(immediate = true, property = "indexer.class.name=com.aimdek.assignment.model.Author", service = ModelIndexerWriterContributor.class)
public class AuthorModelIndexerWriterContributor implements ModelIndexerWriterContributor<Author>{

	@Override
	public void customize(BatchIndexingActionable batchIndexingActionable,
			ModelIndexerWriterDocumentHelper modelIndexerWriterDocumentHelper) {
		
		batchIndexingActionable.setPerformActionMethod((Author author)->{
			Document document = modelIndexerWriterDocumentHelper.getDocument(author);
			
			batchIndexingActionable.addDocuments(document);
		});
		
	}

	@Override
	public BatchIndexingActionable getBatchIndexingActionable() {

		return dynamicQueryBatchIndexingActionableFactory.getBatchIndexingActionable(authorLocalService.getIndexableActionableDynamicQuery());
	}

	@Override
	public long getCompanyId(Author author) {
		return author.getCompanyId();		
	}
	
	@Reference
	protected DynamicQueryBatchIndexingActionableFactory dynamicQueryBatchIndexingActionableFactory;
	
	@Reference
	protected AuthorLocalService authorLocalService;

}
