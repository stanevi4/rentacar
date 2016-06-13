package by.grodno.ss.rentacar.webapp.page.MyBooking;

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

import by.grodno.ss.rentacar.dataaccess.filters.BookingFilter;
import by.grodno.ss.rentacar.datamodel.Booking;
import by.grodno.ss.rentacar.datamodel.Booking_;
import by.grodno.ss.rentacar.datamodel.UserRole;
import by.grodno.ss.rentacar.service.BookingService;
import by.grodno.ss.rentacar.webapp.app.AuthorizedSession;
import by.grodno.ss.rentacar.webapp.page.admin.ReservationsEditPage;
import by.grodno.ss.rentacar.webapp.page.admin.panel.ReservationEditPanel;
import by.grodno.ss.rentacar.webapp.page.admin.panel.ReservationListPanel;
import de.agilecoders.wicket.core.markup.html.bootstrap.components.TooltipBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.navigation.BootstrapPagingNavigator;

public class MyBookingListPanel extends Panel {
	private static final long serialVersionUID = 1L;
	@Inject
	private BookingService bookingService;
	private IModel<String> descDeleteButton = Model.of("Delete item");
	private IModel<String> descEditButton = Model.of("View / edit item");
	
	public MyBookingListPanel(String id) {
		super(id);
	}

	public MyBookingListPanel(String id, IModel<?> model) {
		super(id, model);
	}
	
	@Override
	public void onInitialize() {
		super.onInitialize();
		MyBookingListPanel.this.setOutputMarkupId(true);
		add(new FeedbackPanel("feedback"));

		BookingsDataProvider bookingsDataProvider = new BookingsDataProvider();
		DataView<Booking> dataView = new DataView<Booking>("rows", bookingsDataProvider, 10) {
			private static final long serialVersionUID = 1L;
			@Override
			protected void populateItem(Item<Booking> item) {

				Booking booking = item.getModelObject();
				item.add(new Label("id", booking.getId()));
				item.add(new Label("created", booking.getCreated()));
				//item.add(new Label("client", booking.getClient().getFirstName() +" "+booking.getClient().getLastName()));
				item.add(new Label("car", booking.getCar().getName()));
				item.add(new Label("dateFrom", booking.getDateFrom()));
				item.add(new Label("dateTo", booking.getDateTo())); 
				item.add(new Label("locationFrom", booking.getLocationFrom().getName()));
				//item.add(new Label("locationTo", booking.getLocationTo()));
				item.add(new Label("summ", booking.getSumm()));
				item.add(new Label("reason", booking.getReason()==null ? "" : booking.getReason().getName()));
				//item.add(new Label("damage", booking.getDamage()));
				item.add(new Label("orderStatus", booking.getOrderStatus()));
				addEditButton(MyBookingListPanel.this.getId(), item, booking);
				//addDeleteButton(item, booking);
				item.setOutputMarkupId(true);
			}
		};
		add(dataView);
		BootstrapPagingNavigator pager = new BootstrapPagingNavigator("paging", dataView);
		add(pager);

		add(new OrderByBorder("sort-id", Booking_.id, bookingsDataProvider));
		add(new OrderByBorder("sort-created", Booking_.created, bookingsDataProvider));
		add(new OrderByBorder("sort-dateFrom", Booking_.dateFrom, bookingsDataProvider));
		add(new OrderByBorder("sort-dateTo", Booking_.dateTo, bookingsDataProvider));
		add(new OrderByBorder("sort-locationFrom", Booking_.locationFrom, bookingsDataProvider));
		//add(new OrderByBorder("sort-locationTo", Booking_.locationTo, bookingsDataProvider));
		add(new OrderByBorder("sort-summ", Booking_.summ, bookingsDataProvider));
		add(new OrderByBorder("sort-status", Booking_.orderStatus, bookingsDataProvider));
	}

	private class BookingsDataProvider extends SortableDataProvider<Booking, Serializable> {
		private static final long serialVersionUID = 1L;
		private BookingFilter bookingFilter;

		public BookingsDataProvider() {
			super();
			bookingFilter = new BookingFilter();
			setSort((Serializable) Booking_.created, SortOrder.ASCENDING);
		}

		@Override
		public Iterator<Booking> iterator(long first, long count) {
			Serializable property = getSort().getProperty();
			SortOrder propertySortOrder = getSortState().getPropertySortOrder(property);

			bookingFilter.setSortProperty((SingularAttribute<?, ?>) property);
			bookingFilter.setSortOrder(propertySortOrder.equals(SortOrder.ASCENDING) ? true : false);
			bookingFilter.setLimit((int) count);
			bookingFilter.setOffset((int) first);
			bookingFilter.setFetchCar(true);
			bookingFilter.setFetchClient(true);
			bookingFilter.setFetchLocations(true);
			bookingFilter.setFetchReason(true);
			return bookingService.find(bookingFilter).iterator();
		}

		@Override
		public long size() {
			return bookingService.count(bookingFilter);
		}

		@Override
		public IModel<Booking> model(Booking object) {
			return new Model(object);
		}

	}

	private void addEditButton(String id, Item<Booking> item, Booking booking) {
		AjaxLink<Void> buttonEdit = new AjaxLink<Void>("edit-link") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				//Component newPanel = new ReservationEditPanel(id, booking);
				//MyBookingListPanel.this.replaceWith(newPanel);
				//if (target != null) {
				//	target.add(newPanel);
				//}
			}
		};
		buttonEdit.add(new TooltipBehavior(descEditButton));
		item.add(buttonEdit);
	}

	private void addButtonNew(String id) {
		AjaxLink<Void> buttonNewItem = new AjaxLink<Void>("add-new-item") {
			private static final long serialVersionUID = 1L;
			@Override
			public void onClick(AjaxRequestTarget target) {
				Component newPanel = new ReservationEditPanel(id, new Booking());
				MyBookingListPanel.this.replaceWith(newPanel);
				if (target != null) {
					target.add(newPanel);
				}
			}
		};
		add(buttonNewItem);
	}

}
