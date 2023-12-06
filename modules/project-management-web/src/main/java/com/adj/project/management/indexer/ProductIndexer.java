package com.adj.project.management.indexer;

import com.adj.project.management.model.Product;
import com.adj.project.management.service.ProductLocalServiceUtil;
import com.liferay.portal.kernel.search.BaseIndexer;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.util.GetterUtil;

import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, service = Indexer.class)
public class ProductIndexer extends BaseIndexer<Product> {


	@Override
	public String getClassName() {
		// TODO Auto-generated method stub
		return Product.class.getName();
	}

	public ProductIndexer() {
		super();
		setDefaultSelectedFieldNames(
				Field.COMPANY_ID, Field.ENTRY_CLASS_NAME, Field.ENTRY_CLASS_PK,
				Field.GROUP_ID, Field.MODIFIED_DATE);
			setPermissionAware(true);
			setFilterSearch(true);
	}

	@Override
	protected void doDelete(Product product) throws Exception {
		
		deleteDocument(product.getCompanyId(), product.getProductId());

	}

	@Override
	protected Document doGetDocument(Product product) throws Exception {
		Document document = getBaseModelDocument(Product.class.getName(), product);
		
		// Add column to be indexed
		document.addNumber("name", product.getName());
		document.addNumber("productId", product.getProductId());
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
		Product product = ProductLocalServiceUtil.getProduct(classPK);
		doReindex(product);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		
		long companyId = GetterUtil.getLong(ids[0]);
		//reindexEntries(companyId);	
	}

	@Override
	protected void doReindex(Product product) throws Exception {
		Document document = getDocument(product);
		/*indexWriterHelper.updateDocument(
				getSearchEngineId(), product.getCompanyId(), document,
				isCommitImmediately());
	*/
	}
	@Override
	public boolean hasPermission(PermissionChecker permissionChecker, String entryClassName, long entryClassPK,
			String actionId) throws Exception {
		// TODO Auto-generated method stub
		return super.hasPermission(permissionChecker, entryClassName, entryClassPK, actionId);
	}

}
