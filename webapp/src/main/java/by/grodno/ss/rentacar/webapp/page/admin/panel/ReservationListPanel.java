package by.grodno.ss.rentacar.webapp.page.admin.panel;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;

import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.persistence.metamodel.SingularAttribute;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.OrderByBorder;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import by.grodno.ss.rentacar.dataaccess.filters.BookingFilter;
import by.grodno.ss.rentacar.datamodel.Booking;
import by.grodno.ss.rentacar.datamodel.Booking_;
import by.grodno.ss.rentacar.datamodel.OrderStatus;
import by.grodno.ss.rentacar.datamodel.UserRole;
import by.grodno.ss.rentacar.service.BookingService;
import by.grodno.ss.rentacar.webapp.app.AuthorizedSession;
import by.grodno.ss.rentacar.webapp.common.OrderStatusChoiceRenderer;
import by.grodno.ss.rentacar.webapp.page.admin.ReservationsEditPage;
import de.agilecoders.wicket.core.markup.html.bootstrap.components.TooltipBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.navigation.BootstrapPagingNavigator;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.datetime.DatetimePicker;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.datetime.DatetimePickerConfig;

public class ReservationListPanel extends Panel {
	private static final long serialVersionUID = 1L;
	private BookingFilter filter;
	@Inject
	private BookingService bookingService;
	private IModel<String> descDeleteButton = Model.of("Delete item");
	private IModel<String> descEditButton = Model.of("View / edit item");

	public ReservationListPanel(String id, BookingFilter filter) {
		super(id);
		this.filter = filter;
	}

	public ReservationListPanel(String id, IModel<?> model) {
		super(id, model);
	}

	@Override
	public void onInitialize() {
		super.onInitialize();
		ReservationListPanel.this.setOutputMarkupId(true);
		add(new FeedbackPanel("feedback"));
		WebMarkupContainer wmc = new WebMarkupContainer("table");
		wmc.setOutputMarkupId(true);
		
		Form<BookingFilter> form = new Form<BookingFilter>("selections", new CompoundPropertyModel<BookingFilter>(filter));
		
		DatetimePickerConfig dateconfig = configureDateTimepicker();
		DatetimePicker dateFrom = new DatetimePicker("createdFrom", dateconfig);
		form.add(dateFrom);
		
		DatetimePicker dateTo = new DatetimePicker("createdTo", dateconfig);
		form.add(dateTo);
		
		DropDownChoice<OrderStatus> statusDropDown = new DropDownChoice<>("orderStatus", Arrays.asList(OrderStatus.values()),
				OrderStatusChoiceRenderer.INSTANCE);
		statusDropDown.setRequired(false);
		statusDropDown.setNullValid(true);
		statusDropDown.setMarkupId("type");
		
		statusDropDown.add(new AjaxFormComponentUpdatingBehavior("change") {
			private static final long serialVersionUID = 1L;
			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				if (target != null) {
					target.add(wmc);
				}
			}
		});
		form.add(statusDropDown);		
		
		form.add(new AjaxLink<Void>("apply-filter") {
			private static final long serialVersionUID = 1L;
			@Override
			public void onClick(AjaxRequestTarget target) {
				if (target != null) {
					target.add(wmc);
				}
			}
		});
		
		add(form);
		
		BookingsDataProvider bookingsDataProvider = new BookingsDataProvider(filter);
		DataView<Booking> dataView = new DataView<Booking>("rows", bookingsDataProvider, 10) {
			private static final long serialVersionUID = 1L;
			@Override
			protected void populateItem(Item<Booking> item) {

				Booking booking = item.getModelObject();
				item.add(new Label("id", booking.getId()));
				item.add(new Label("created", booking.getCreated()));
				item.add(new Label("client", booking.getClient().getFirstName() +" "+booking.getClient().getLastName()));
				item.add(new Label("car", booking.getCar().getName()));
				item.add(new Label("dateFrom", booking.getDateFrom()));
				//item.add(new Label("dateTo", booking.getDateTo())); //не хватает ширины в таблице
				item.add(new Label("locationFrom", booking.getLocationFrom().getName()));
				//item.add(new Label("locationTo", booking.getLocationTo()));
				item.add(new Label("summ", booking.getSumm()));
				//item.add(new Label("reason", booking.getReason().getName()));
				item.add(new Label("damage", booking.getDamage()));
				item.add(new Label("orderStatus", booking.getOrderStatus()));
				addEditButton(ReservationListPanel.this.getId(), item, booking);
				addDeleteButton(item, booking);
				item.setOutputMarkupId(true);
			}
		};
		wmc.add(dataView);
		BootstrapPagingNavigator pager = new BootstrapPagingNavigator("paging", dataView);
		add(pager);

		wmc.add(new OrderByBorder("sort-id", Booking_.id, bookingsDataProvider));
		wmc.add(new OrderByBorder("sort-created", Booking_.created, bookingsDataProvider));
		wmc.add(new OrderByBorder("sort-dateFrom", Booking_.dateFrom, bookingsDataProvider));
		//add(new OrderByBorder("sort-dateTo", Booking_.dateTo, bookingsDataProvider));
		wmc.add(new OrderByBorder("sort-locationFrom", Booking_.locationFrom, bookingsDataProvider));
		//add(new OrderByBorder("sort-locationTo", Booking_.locationTo, bookingsDataProvider));
		wmc.add(new OrderByBorder("sort-summ", Booking_.summ, bookingsDataProvider));
		wmc.add(new OrderByBorder("sort-status", Booking_.orderStatus, bookingsDataProvider));

		//addButtonNew(this.getId());
		add(wmc);
	}

	private class BookingsDataProvider extends SortableDataProvider<Booking, Serializable> {
		private static final long serialVersionUID = 1L;

		private BookingFilter bookingFilter;

		public BookingsDataProvider(BookingFilter filter) {
			super();
			this.bookingFilter = filter;
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
				Component newPanel = new ReservationEditPanel(id, booking);
				ReservationListPanel.this.replaceWith(newPanel);
				if (target != null) {
					target.add(newPanel);
				}
			}
		};
		buttonEdit.add(new TooltipBehavior(descEditButton));
		item.add(buttonEdit);
	}

	private void addDeleteButton(Item<Booking> item, Booking booking) {
		Link<Void> buttonDelete = new Link<Void>("delete-link") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				try {
					bookingService.delete(booking);
				} catch (PersistenceException e) {
					System.out.println("caughth persistance exception");
				}
				setResponsePage(new ReservationsEditPage());
			}

		};
		buttonDelete.setEnabled(false);
		buttonDelete.add(new TooltipBehavior(descDeleteButton));
		boolean a = (AuthorizedSession.get().isSignedIn()
				&& AuthorizedSession.get().getLoggedUser().getRole().equals(UserRole.ADMIN));
		buttonDelete.setEnabled(a);
		item.add(buttonDelete);
	}

	private void addButtonNew(String id) {
		AjaxLink<Void> buttonNewItem = new AjaxLink<Void>("add-new-item") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				Component newPanel = new ReservationEditPanel(id, new Booking());
				ReservationListPanel.this.replaceWith(newPanel);
				if (target != null) {
					target.add(newPanel);
				}
			}
		};
		add(buttonNewItem);
	}

	private DatetimePickerConfig configureDateTimepicker() {
		DatetimePickerConfig dateconfig = new DatetimePickerConfig();
		dateconfig.withFormat("dd.MM.yyyy HH:mm");
		dateconfig.useSideBySide(true);
		dateconfig.useLocale(AuthorizedSession.get().getLocale().getLanguage());
		dateconfig.withMinuteStepping(10);
		return dateconfig;
	}
}
