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
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import by.grodno.ss.rentacar.dataaccess.filters.OrderFilter;
import by.grodno.ss.rentacar.datamodel.Order;
import by.grodno.ss.rentacar.datamodel.Order_;
import by.grodno.ss.rentacar.service.OrderService;
import by.grodno.ss.rentacar.webapp.page.admin.ReservationsEditPage;
import de.agilecoders.wicket.core.markup.html.bootstrap.components.TooltipBehavior;

public class ReservationListPanel extends Panel {
	private static final long serialVersionUID = 1L;
	@Inject
	private OrderService orderService;
	private IModel<String> descDeleteButton = Model.of("Delete item");
	private IModel<String> descEditButton = Model.of("View / edit item");

	public ReservationListPanel(String id) {
		super(id);
	}

	public ReservationListPanel(String id, IModel<?> model) {
		super(id, model);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void onInitialize() {
		super.onInitialize();
		setOutputMarkupId(true);
		add(new FeedbackPanel("feedback"));

		OrdersDataProvider ordersDataProvider = new OrdersDataProvider();
		DataView<Order> dataView = new DataView<Order>("rows", ordersDataProvider, 10) {
			private static final long serialVersionUID = 1L;
			@Override
			protected void populateItem(Item<Order> item) {

				Order order = item.getModelObject();
				item.add(new Label("id", order.getId()));
				item.add(new Label("created", order.getCreated()));
				item.add(new Label("client", order.getClient()));
				item.add(new Label("car", order.getCar()));
				item.add(new Label("dateFrom", order.getDateFrom()));
				item.add(new Label("dateTo", order.getDateTo()));
				item.add(new Label("locationFrom", order.getLocationFrom()));
				item.add(new Label("locationTo", order.getLocationTo()));
				item.add(new Label("summ", order.getSumm()));
				item.add(new Label("reason", order.getReason()));
				item.add(new Label("damage", order.getDamage()));
				item.add(new Label("orderStatus", order.getOrderStatus()));
				addEditButton(ReservationListPanel.this.getId(), item, order);
				addDeleteButton(item, order);
			}
		};
		add(dataView);
		add(new PagingNavigator("paging", dataView));

		add(new OrderByBorder("sort-id", Order_.id, ordersDataProvider));
//		add(new OrderByBorder("sort-created", Order_.created, ordersDataProvider));
//		add(new OrderByBorder("sort-dateFrom", Order_.dateFrom, ordersDataProvider));
//		add(new OrderByBorder("sort-dateTo", Order_.dateTo, ordersDataProvider));
//		add(new OrderByBorder("sort-locationFrom", Order_.locationFrom, ordersDataProvider));
//		add(new OrderByBorder("sort-locationTo", Order_.locationTo, ordersDataProvider));
//		add(new OrderByBorder("sort-summ", Order_.summ, ordersDataProvider));
//		add(new OrderByBorder("sort-status", Order_.orderStatus, ordersDataProvider));

		addButtonNew(this.getId());
	}

	private class OrdersDataProvider extends SortableDataProvider<Order, Serializable> {
		private static final long serialVersionUID = 1L;
		private OrderFilter orderFilter;

		public OrdersDataProvider() {
			super();
			orderFilter = new OrderFilter();
			setSort((Serializable) Order_.id, SortOrder.ASCENDING);
		}

		@Override
		public Iterator<Order> iterator(long first, long count) {
			Serializable property = getSort().getProperty();
			SortOrder propertySortOrder = getSortState().getPropertySortOrder(property);

			orderFilter.setSortProperty((SingularAttribute<?, ?>) property);
			orderFilter.setSortOrder(propertySortOrder.equals(SortOrder.ASCENDING) ? true : false);
			orderFilter.setLimit((int) count);
			orderFilter.setOffset((int) first);
//			orderFilter.setFetchCar(true);
//			orderFilter.setFetchClient(true);
//			orderFilter.setFetchLocations(true);
//			orderFilter.setFetchReason(true);
			return orderService.find(orderFilter).iterator();
		}

		@Override
		public long size() {
			return orderService.count(orderFilter);
		}

		@Override
		public IModel<Order> model(Order object) {
			return new Model(object);
		}

	}

	private void addEditButton(String id, Item<Order> item, Order order) {
		AjaxLink<Void> buttonEdit = new AjaxLink<Void>("edit-link") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				Component newPanel = new ReservationEditPanel(id, order);
				ReservationListPanel.this.replaceWith(newPanel);
				if (target != null) {
					target.add(newPanel);
				}
			}
		};
		buttonEdit.add(new TooltipBehavior(descEditButton));
		item.add(buttonEdit);
	}

	private void addDeleteButton(Item<Order> item, Order order) {
		item.add(new Link<Void>("delete-link") {
			private static final long serialVersionUID = 1L;
			@Override
			public void onClick() {
				try {
					orderService.delete(order);
				} catch (PersistenceException e) {
					System.out.println("caughth persistance exception");
				}
				setResponsePage(new ReservationsEditPage());
			}
		}.add(new TooltipBehavior(descDeleteButton)));
	}

	private void addButtonNew(String id) {
		AjaxLink<Void> buttonNewItem = new AjaxLink<Void>("add-new-item") {
			private static final long serialVersionUID = 1L;
			@Override
			public void onClick(AjaxRequestTarget target) {
				Component newPanel = new ReservationEditPanel(id, new Order());
				ReservationListPanel.this.replaceWith(newPanel);
				if (target != null) {
					target.add(newPanel);
				}
			}
		};
		add(buttonNewItem);
	}
}
