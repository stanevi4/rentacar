package by.grodno.ss.rentacar.webapp.page.admin.panel;

import java.io.Serializable;
import java.util.Iterator;

import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.persistence.metamodel.SingularAttribute;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.OrderByBorder;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import by.grodno.ss.rentacar.dataaccess.filters.TypeFilter;
import by.grodno.ss.rentacar.datamodel.Currency;
import by.grodno.ss.rentacar.datamodel.Type;
import by.grodno.ss.rentacar.datamodel.Type_;
import by.grodno.ss.rentacar.service.SettingService;
import by.grodno.ss.rentacar.service.TypeService;
import by.grodno.ss.rentacar.webapp.page.admin.TypeEditPage;
import de.agilecoders.wicket.core.markup.html.bootstrap.components.TooltipBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.navigation.BootstrapPagingNavigator;

public class TypeListPanel extends Panel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private TypeService typeService;
	@Inject
	private SettingService settingService;
	
	private IModel<String> descDeleteButton = Model.of("Delete item");
	private IModel<String> descEditButton = Model.of("View / edit item");
	private IModel<String> descPass = Model.of("Number of passengers");
	private IModel<String> descBags = Model.of("Number of bags");
	private IModel<String> descDoors = Model.of("Number of doors");
	private IModel<String> descTrans = Model.of("Transmission type");

	public TypeListPanel(String id) {
		super(id);
	}

	@Override
	public void onInitialize() {
		super.onInitialize();
		setOutputMarkupId(true);
		TypesDataProvider typeDataProvider = new TypesDataProvider();
		DataView<Type> dataView = new DataView<Type>("rows", typeDataProvider, 10) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(Item<Type> item) {

				Type type = item.getModelObject();
				item.add(new Label("id", type.getId()));
				item.add(new Label("name", type.getName()));
				item.add(new Label("pass", type.getNumPassengers()));
				item.add(new Label("bags", type.getNumBags()));
				item.add(new Label("doors", type.getNumDoors()));
				item.add(new Label("trans", type.getTransmissionType()));
				item.add(new Label("price", type.getPricePerHour()));
				addEditButton(TypeListPanel.this.getId(), item, type);
				addDeleteButton(item, type);
				
				//подсказка описание иконок
				item.add(new WebMarkupContainer("descPass").add(new TooltipBehavior(descPass)));
				item.add(new WebMarkupContainer("descBags").add(new TooltipBehavior(descBags)));
				item.add(new WebMarkupContainer("descDoors").add(new TooltipBehavior(descDoors)));
				item.add(new WebMarkupContainer("descTrans").add(new TooltipBehavior(descTrans)));
				addPriceIcon(item);
			}
		};
		add(dataView);
		BootstrapPagingNavigator pager = new BootstrapPagingNavigator("paging", dataView);
		add(pager);

		add(new OrderByBorder("sort-id", Type_.id, typeDataProvider));
		add(new OrderByBorder("sort-name", Type_.name, typeDataProvider));
		add(new OrderByBorder("sort-price", Type_.pricePerHour, typeDataProvider));

		addButtonNew(this.getId());
	}

	private class TypesDataProvider extends SortableDataProvider<Type, Serializable> {

		private TypeFilter typeFilter;

		public TypesDataProvider() {
			super();
			typeFilter = new TypeFilter();
			setSort((Serializable) Type_.name, SortOrder.ASCENDING);
		}

		@Override
		public Iterator<Type> iterator(long first, long count) {
			Serializable property = getSort().getProperty();
			SortOrder propertySortOrder = getSortState().getPropertySortOrder(property);

			typeFilter.setSortProperty((SingularAttribute<?, ?>) property);
			typeFilter.setSortOrder(propertySortOrder.equals(SortOrder.ASCENDING) ? true : false);

			typeFilter.setLimit((int) count);
			typeFilter.setOffset((int) first);
			return typeService.find(typeFilter).iterator();
		}

		@Override
		public long size() {
			return typeService.count(typeFilter);
		}

		@Override
		public IModel<Type> model(Type object) {
			return new Model(object);
		}

	}

	private void addEditButton(String id, Item<Type> item, Type type) {
		AjaxLink<Void> buttonEdit = new AjaxLink<Void>("edit-link") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				Component newPanel = new TypeEditPanel(id, type);
				TypeListPanel.this.replaceWith(newPanel);
				if (target != null) {
					target.add(newPanel);
				}
			}
		};
		buttonEdit.add(new TooltipBehavior(descEditButton));
		item.add(buttonEdit);
	}

	private void addDeleteButton(Item<Type> item, Type type) {
		item.add(new Link<Void>("delete-link") {
			@Override
			public void onClick() {
				try {
					typeService.delete(type);
				} catch (PersistenceException e) {
					System.out.println("caughth persistance exception");
				}
				setResponsePage(new TypeEditPage());
			}
		}.add(new TooltipBehavior(descDeleteButton)));
	}

	private void addButtonNew(String id) {
		AjaxLink<Void> buttonNewItem = new AjaxLink<Void>("add-new-item") {
			private static final long serialVersionUID = 1L;

			public void onClick(AjaxRequestTarget target) {
				Component newPanel = new TypeEditPanel(id, new Type());
				TypeListPanel.this.replaceWith(newPanel);
				if (target != null) {
					target.add(newPanel);
				}
			}
		};
		add(buttonNewItem);
	}
	
	private void addPriceIcon(Item<Type> item) {
		Currency currency = settingService.get().getCurrency();
		WebMarkupContainer i = new WebMarkupContainer("iconPrice");
		if (currency.equals(Currency.USD)){
			i.add(new AttributeModifier("class","fa fa-usd"));
		}
		else if (currency.equals(Currency.EUR)){
			i.add(new AttributeModifier("class","fa fa-eur"));
		}
		else if (currency.equals(Currency.GBR)){
			i.add(new AttributeModifier("class","fa fa-gbp"));
		}
		else {
			i.add(new AttributeModifier("class","fa fa-rub"));
		}
		item.add(i);
	}

}
