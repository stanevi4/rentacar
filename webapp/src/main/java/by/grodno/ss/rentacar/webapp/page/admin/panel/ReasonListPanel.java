package by.grodno.ss.rentacar.webapp.page.admin.panel;

import java.io.Serializable;
import java.util.Iterator;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.persistence.metamodel.SingularAttribute;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.OrderByBorder;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import by.grodno.ss.rentacar.dataaccess.filters.ReasonFilter;
import by.grodno.ss.rentacar.datamodel.Reason;
import by.grodno.ss.rentacar.datamodel.Reason_;
import by.grodno.ss.rentacar.service.ReasonService;
import by.grodno.ss.rentacar.webapp.page.admin.ReasonEditPage;
import de.agilecoders.wicket.core.markup.html.bootstrap.components.TooltipBehavior;

public class ReasonListPanel extends Panel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private ReasonService reasonService;
	private IModel<String> descDeleteButton = Model.of("Delete item");
	private IModel<String> descEditButton = Model.of("View / edit item");

	public ReasonListPanel(String id) {
		super(id);
	}

	public ReasonListPanel(String id, IModel<?> model) {
		super(id, model);
	}
	
	@Override
	public void onInitialize() {
		super.onInitialize();
		setOutputMarkupId(true);
		ReasonsDataProvider reasonsDataProvider = new ReasonsDataProvider();
		DataView<Reason> dataView = new DataView<Reason>("rows", reasonsDataProvider, 10) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(Item<Reason> item) {

				Reason reason = item.getModelObject();
				item.add(new Label("id", reason.getId()));
				item.add(new Label("name", reason.getName()));
				addEditButton(ReasonListPanel.this.getId(), item, reason);
				addDeleteButton(item, reason);
			}
		};
		add(dataView);
		add(new PagingNavigator("paging", dataView));

		add(new OrderByBorder("sort-id", Reason_.id, reasonsDataProvider));
		add(new OrderByBorder("sort-name", Reason_.name, reasonsDataProvider));

		addButtonNew(this.getId());
	}
	
	private class ReasonsDataProvider extends SortableDataProvider<Reason, Serializable> {

		private ReasonFilter reasonFilter;

		public ReasonsDataProvider() {
			super();
			reasonFilter = new ReasonFilter();
			setSort((Serializable) Reason_.name, SortOrder.ASCENDING);
		}

		@Override
		public Iterator<Reason> iterator(long first, long count) {
			Serializable property = getSort().getProperty();
			SortOrder propertySortOrder = getSortState().getPropertySortOrder(property);

			reasonFilter.setSortProperty((SingularAttribute<?, ?>) property);
			reasonFilter.setSortOrder(propertySortOrder.equals(SortOrder.ASCENDING) ? true : false);

			reasonFilter.setLimit((int) count);
			reasonFilter.setOffset((int) first);
			return reasonService.find(reasonFilter).iterator();
		}

		@Override
		public long size() {
			return reasonService.count(reasonFilter);
		}

		@Override
		public IModel<Reason> model(Reason object) {
			return new Model(object);
		}

	}
	
	private void addEditButton(String id, Item<Reason> item, Reason reason) {
		AjaxLink<Void> buttonEdit = new AjaxLink<Void>("edit-link") {
			private static final long serialVersionUID = 1L;
			@Override
			public void onClick(AjaxRequestTarget target) {
				Component newPanel = new ReasonEditPanel(id, reason);
				ReasonListPanel.this.replaceWith(newPanel);
				if (target != null) {
					target.add(newPanel);
				}
			}
		};
		buttonEdit.add(new TooltipBehavior(descEditButton));
		item.add(buttonEdit);
	}
	
	private void addDeleteButton(Item<Reason> item, Reason reason) {
		item.add(new Link<Void>("delete-link") {
			@Override
			public void onClick() {
				try {
					reasonService.delete(reason);
				} catch (PersistenceException e) {
					System.out.println("caughth persistance exception");
				}
				setResponsePage(new ReasonEditPage());
			}
		}.add(new TooltipBehavior(descDeleteButton)));
	}

	private void addButtonNew(String id) {
		AjaxLink<Void> buttonNewItem = new AjaxLink<Void>("add-new-item") {
			private static final long serialVersionUID = 1L;
			public void onClick(AjaxRequestTarget target) {
				Component newPanel = new ReasonEditPanel(id, new Reason());
				ReasonListPanel.this.replaceWith(newPanel);
				if (target != null) {
					target.add(newPanel);
				}
			}
		};
		add(buttonNewItem);
	}

}
