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
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import by.grodno.ss.rentacar.dataaccess.filters.UserFilter;
import by.grodno.ss.rentacar.datamodel.UserCredentials;
import by.grodno.ss.rentacar.datamodel.UserCredentials_;
import by.grodno.ss.rentacar.datamodel.UserProfile;
import by.grodno.ss.rentacar.datamodel.UserProfile_;
import by.grodno.ss.rentacar.datamodel.UserRole;
import by.grodno.ss.rentacar.service.UserService;
import by.grodno.ss.rentacar.webapp.app.AuthorizedSession;
import by.grodno.ss.rentacar.webapp.common.UserRoleChoiceRenderer;
import by.grodno.ss.rentacar.webapp.page.admin.UsersEditPage;
import de.agilecoders.wicket.core.markup.html.bootstrap.components.TooltipBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.navigation.BootstrapPagingNavigator;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.datetime.DatetimePicker;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.datetime.DatetimePickerConfig;

public class UserListPanel extends Panel {
	private static final long serialVersionUID = 1L;

	@Inject
	private UserService userService;
	private UserFilter filter;
	private IModel<String> descDeleteButton = Model.of("Delete item");
	private IModel<String> descEditButton = Model.of("View / edit item");

	public UserListPanel(String id, UserFilter filter) {
		super(id);
		this.filter = filter;
	}

	public UserListPanel(String id, IModel<?> model) {
		super(id, model);
	}

	public void onInitialize() {
		super.onInitialize();
		setOutputMarkupId(true);
			
		WebMarkupContainer wmc = new WebMarkupContainer("table");
		wmc.setOutputMarkupId(true);
		
		Form<UserFilter> form = new Form<UserFilter>("selections", new CompoundPropertyModel<UserFilter>(filter));
		
		DatetimePickerConfig dateconfig = configureDateTimepicker();
		DatetimePicker dateFrom = new DatetimePicker("createdFrom", dateconfig);
		form.add(dateFrom);
		
		DatetimePicker dateTo = new DatetimePicker("createdTo", dateconfig);
		form.add(dateTo);
		
		DropDownChoice<UserRole> roleDropDown = new DropDownChoice<UserRole>("role", Arrays.asList(UserRole.values()),
				UserRoleChoiceRenderer.INSTANCE);
		roleDropDown.setRequired(false);
		roleDropDown.setNullValid(true);
		
		roleDropDown.add(new AjaxFormComponentUpdatingBehavior("change") {
			private static final long serialVersionUID = 1L;
			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				if (target != null) {
					target.add(wmc);
				}
			}
		});
		form.add(roleDropDown);		
		
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
		
		UsersDataProvider usersDataProvider = new UsersDataProvider(filter);
		DataView<UserProfile> dataView = new DataView<UserProfile>("rows", usersDataProvider, 10) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(Item<UserProfile> item) {

				UserProfile user = item.getModelObject();
				item.add(new Label("email", user.getUserCredentials().getEmail()));
				item.add(new Label("created", user.getCreated()));
				item.add(new Label("firstName", user.getFirstName()));
				item.add(new Label("lastName", user.getLastName()));
				item.add(new Label("phone", user.getPhoneNumber()));
				item.add(new Label("role", user.getUserCredentials().getRole()));
				addEditButton(UserListPanel.this.getId(), item, user);
				addDeleteButton(item, user);
			}
		};
		wmc.add(dataView);
		BootstrapPagingNavigator pager = new BootstrapPagingNavigator("paging", dataView);
		add(pager);
		
		wmc.add(new OrderByBorder("sort-email", UserCredentials_.email, usersDataProvider));
		wmc.add(new OrderByBorder("sort-created", UserProfile_.created, usersDataProvider));
		wmc.add(new OrderByBorder("sort-first-name", UserProfile_.firstName, usersDataProvider));
		wmc.add(new OrderByBorder("sort-last-name", UserProfile_.lastName, usersDataProvider));
		wmc.add(new OrderByBorder("sort-role", UserCredentials_.role, usersDataProvider));

		addButtonNew(this.getId());
		
		add(wmc);
	}

	private class UsersDataProvider extends SortableDataProvider<UserProfile, Serializable> {
		private static final long serialVersionUID = 1L;
		private UserFilter userFilter;

		public UsersDataProvider(UserFilter filter) {
			super();
			this.userFilter = filter;
			setSort((Serializable) UserProfile_.created, SortOrder.DESCENDING);
		}

		@Override
		public Iterator<UserProfile> iterator(long first, long count) {
			Serializable property = getSort().getProperty();
			SortOrder propertySortOrder = getSortState().getPropertySortOrder(property);

			userFilter.setSortProperty((SingularAttribute<?, ?>) property);
			userFilter.setSortOrder(propertySortOrder.equals(SortOrder.ASCENDING) ? true : false);
			userFilter.setLimit((int) count);
			userFilter.setOffset((int) first);
			userFilter.setFetchCredentials(true);

			return userService.find(userFilter).iterator();
		}

		@Override
		public long size() {
			return userService.count(userFilter);
		}

		@Override
		public IModel<UserProfile> model(UserProfile object) {
			return new Model(object);
		}

	}

	private void addEditButton(String id, Item<UserProfile> item, UserProfile user) {
		AjaxLink<Void> buttonEdit = new AjaxLink<Void>("edit-link") {
			private static final long serialVersionUID = 1L;
			@Override
			public void onClick(AjaxRequestTarget target) {
				Component newPanel = new UserEditPanel(id, user, user.getUserCredentials(), filter);
				UserListPanel.this.replaceWith(newPanel);
				if (target != null) {
					target.add(newPanel);
				}
			}
		};
		buttonEdit.add(new TooltipBehavior(descEditButton));
		item.add(buttonEdit);
	}

	boolean a = (AuthorizedSession.get().isSignedIn() && AuthorizedSession.get().getLoggedUser().getRole().equals(UserRole.ADMIN));
	private void addDeleteButton(Item<UserProfile> item, UserProfile user) {
		item.add(new Link<Void>("delete-link") {
			private static final long serialVersionUID = 1L;
			@Override
			public void onClick() {
				try {
					userService.delete(user.getId());
				} catch (PersistenceException e) {
					error("can't delete user!");
				}
				setResponsePage(new UsersEditPage());
			}
		}.add(new TooltipBehavior(descDeleteButton)).setVisible(a));
	}

	private void addButtonNew(String id) {
		AjaxLink<Void> buttonNewItem = new AjaxLink<Void>("add-new-item") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				Component newPanel = new UserEditPanel(id, new UserProfile(), new UserCredentials(), filter);
				UserListPanel.this.replaceWith(newPanel);
				if (target != null) {
					target.add(newPanel);
				}
			}
		};
		buttonNewItem.setVisible(a);
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
