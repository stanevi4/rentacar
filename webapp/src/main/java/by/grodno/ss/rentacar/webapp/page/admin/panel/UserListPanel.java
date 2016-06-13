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

import by.grodno.ss.rentacar.dataaccess.filters.UserFilter;
import by.grodno.ss.rentacar.datamodel.Car;
import by.grodno.ss.rentacar.datamodel.Car_;
import by.grodno.ss.rentacar.datamodel.UserCredentials_;
import by.grodno.ss.rentacar.datamodel.UserProfile;
import by.grodno.ss.rentacar.datamodel.UserProfile_;
import by.grodno.ss.rentacar.service.UserService;
import by.grodno.ss.rentacar.webapp.page.admin.CarsEditPage;
import de.agilecoders.wicket.core.markup.html.bootstrap.components.TooltipBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.navigation.BootstrapPagingNavigator;

public class UserListPanel extends Panel {
	private static final long serialVersionUID = 1L;

	@Inject
	private UserService userService;
	private IModel<String> descDeleteButton = Model.of("Delete item");
	private IModel<String> descEditButton = Model.of("View / edit item");

	public UserListPanel(String id) {
		super(id);
	}

	public UserListPanel(String id, IModel<?> model) {
		super(id, model);
	}

	public void onInitialize() {
		super.onInitialize();
		setOutputMarkupId(true);
		
		UsersDataProvider usersDataProvider = new UsersDataProvider();
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
		add(dataView);
		BootstrapPagingNavigator pager = new BootstrapPagingNavigator("paging", dataView);
		add(pager);
		
		add(new OrderByBorder("sort-email", UserCredentials_.email, usersDataProvider));
		add(new OrderByBorder("sort-created", UserProfile_.created, usersDataProvider));
		add(new OrderByBorder("sort-first-name", UserProfile_.firstName, usersDataProvider));
		add(new OrderByBorder("sort-last-name", UserProfile_.lastName, usersDataProvider));
		add(new OrderByBorder("sort-role", UserCredentials_.role, usersDataProvider));

		addButtonNew(this.getId());
	}

	private class UsersDataProvider extends SortableDataProvider<UserProfile, Serializable> {
		private static final long serialVersionUID = 1L;
		private UserFilter userFilter;

		public UsersDataProvider() {
			super();
			userFilter = new UserFilter();
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
				Component newPanel = new UserEditPanel(id);
				UserListPanel.this.replaceWith(newPanel);
				if (target != null) {
					target.add(newPanel);
				}
			}
		};
		buttonEdit.add(new TooltipBehavior(descEditButton));
		item.add(buttonEdit);
	}

	private void addDeleteButton(Item<UserProfile> item, UserProfile user) {
		item.add(new Link<Void>("delete-link") {
			private static final long serialVersionUID = 1L;
			@Override
			public void onClick() {
				try {
					//userService.delete(user);
				} catch (PersistenceException e) {
					System.out.println("caughth persistance exception");
				}
				setResponsePage(new CarsEditPage());
			}
		}.add(new TooltipBehavior(descDeleteButton)));
	}

	private void addButtonNew(String id) {
		AjaxLink<Void> buttonNewItem = new AjaxLink<Void>("add-new-item") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				Component newPanel = new CarEditPanel(id, new Car());
				UserListPanel.this.replaceWith(newPanel);
				if (target != null) {
					target.add(newPanel);
				}
			}
		};
		add(buttonNewItem);
	}
}
