package by.grodno.ss.rentacar.webapp.page.admin.panel;

import javax.inject.Inject;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.validation.validator.PatternValidator;
import org.apache.wicket.validation.validator.StringValidator;

import by.grodno.ss.rentacar.datamodel.Reason;
import by.grodno.ss.rentacar.service.ReasonService;
import de.agilecoders.wicket.core.markup.html.bootstrap.components.TooltipBehavior;

public class ReasonEditPanel extends Panel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private ReasonService reasonService;
	private Reason reason;
	private IModel<String> descName = Model.of("Enter reason of refuse description");

	public ReasonEditPanel(String id, Reason reason) {
		super(id);
		this.reason = reason;

	}

	public ReasonEditPanel(String id, IModel<?> model, Reason reason) {
		super(id, model);
		this.reason = reason;
	}

	@Override
	public void onInitialize() {
		super.onInitialize();
		ReasonEditPanel.this.setOutputMarkupId(true);

		Form<Reason> form = new Form<Reason>("form", new CompoundPropertyModel<Reason>(reason));

		TextField<String> name = new TextField<String>("name");
		name.setRequired(true);
		name.add(StringValidator.maximumLength(100));
		name.add(StringValidator.minimumLength(2));
		name.add(new PatternValidator("[A-Za-z0-9 /-]+"));
		name.add(new TooltipBehavior(descName));
		form.add(name);

		form.add(new SubmitLink("save") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit() {
				reasonService.saveOrUpdate(reason);
				info("Reason was saved");
			}
		});

		add(form);

		add(new AjaxLink<Void>("back") {
			private static final long serialVersionUID = 1L;

			public void onClick(AjaxRequestTarget target) {
				Component newPanel = new ReasonListPanel(ReasonEditPanel.this.getId());
				ReasonEditPanel.this.replaceWith(newPanel);
				if (target != null) {
					target.add(newPanel);
				}
			}
		});
	}

}
