package com.aimdek.assignment.search.author;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

import com.aimdek.assignment.model.Author;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.search.spi.model.index.contributor.ModelIndexerWriterContributor;
import com.liferay.portal.search.spi.model.registrar.ModelSearchRegistrarHelper;
import com.liferay.portal.search.spi.model.result.contributor.ModelSummaryContributor;

@Component(immediate = true)
public class AuthorSearchRegistrar {

	@Activate
	protected void activate(BundleContext bundleContext) {
		
		 _serviceRegistration = modelSearchRegistrarHelper.register(
				 Author.class, bundleContext, modelSearchDefinition ->{
					 modelSearchDefinition.setDefaultSelectedFieldNames(
							 Field.ASSET_TAG_NAMES, Field.COMPANY_ID,Field.CONTENT,
							 Field.ENTRY_CLASS_NAME, Field.ENTRY_CLASS_PK,
							 Field.GROUP_ID, Field.MODIFIED_DATE, Field.SCOPE_GROUP_ID,
							 Field.TITLE, Field.UID);
					 
					 modelSearchDefinition.setModelIndexWriteContributor(modelIndexerWriterContributor);
					 
					 modelSearchDefinition.setModelSummaryContributor(modelSummaryContributor);
				 });
		
	}
	@Deactivate
	private void deactivate() {
		_serviceRegistration.unregister(); 
	}
	
	@Reference(target = "(indexer.class.name=com.aimdek.assignment.model.Author)")
	protected ModelIndexerWriterContributor<Author> modelIndexerWriterContributor;
	
	@Reference
	protected ModelSearchRegistrarHelper modelSearchRegistrarHelper;
	
	@Reference(target = "(indexer.class.name=com.aimdek.assignment.model.Author)")
	protected ModelSummaryContributor modelSummaryContributor;
	
	private ServiceRegistration<?> _serviceRegistration;
	
}
