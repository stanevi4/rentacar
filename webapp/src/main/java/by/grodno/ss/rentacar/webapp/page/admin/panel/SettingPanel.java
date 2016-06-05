package by.grodno.ss.rentacar.webapp.page.admin.panel;

import java.util.Arrays;

import javax.inject.Inject;

import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import by.grodno.ss.rentacar.datamodel.Currency;
import by.grodno.ss.rentacar.datamodel.Setting;
import by.grodno.ss.rentacar.service.SettingService;
import by.grodno.ss.rentacar.webapp.common.CurrencyChoiceRenderer;
import by.grodno.ss.rentacar.webapp.page.admin.SettingEditPage;
import de.agilecoders.wicket.core.markup.html.bootstrap.components.TooltipBehavior;

public class SettingPanel extends Panel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private SettingService settingService;
	private Setting setting;
	private IModel<String> descMinBookingLength = Model.of("Минимальное время для бронирования");
	private IModel<String> descSaveButton 		= Model.of("Сохранить опции");

	public SettingPanel(String id) {
		super(id);
		this.setting = settingService.get();
		if (setting==null){
			setting = new Setting();
		}
	}

	public SettingPanel(String id, IModel<?> model) {
		super(id, model);
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		Form<Setting> form = new Form<Setting>("form-options", new CompoundPropertyModel<Setting>(setting));
		form.add(new FeedbackPanel("feedback"));

		NumberTextField<Integer> minBookingLength = new NumberTextField<Integer>("minBookingLength");
		minBookingLength.setRequired(true);
		//minBookingLength.setMinimum(0).setMaximum(1000);
		minBookingLength.add(new TooltipBehavior(descMinBookingLength));
		form.add(minBookingLength);
		
		NumberTextField<Integer> carWhilePending = new NumberTextField<Integer>("carWhilePending");
		carWhilePending.setRequired(true);
		//carWhilePending.setMinimum(0).setMaximum(1000);
		//minBookingLength.add(new CoverTooltipBehavior(descPrice, null));
		form.add(carWhilePending);
		
		NumberTextField<Integer> depositPayment = new NumberTextField<Integer>("depositPayment");
		depositPayment.setRequired(true);
		//depositPayment.setMinimum(0).setMaximum(100);
		//minBookingLength.add(new CoverTooltipBehavior(descPrice, null));
		form.add(depositPayment);
		
		DropDownChoice<Currency> currencyDropDown = new DropDownChoice<>("currency", Arrays.asList(Currency.values()), CurrencyChoiceRenderer.INSTANCE);
		currencyDropDown.setRequired(true);
        form.add(currencyDropDown);
		
		form.add(new SubmitLink("save-options") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit() {
				settingService.saveOrUpdate(setting);
				setResponsePage(new SettingEditPage());
			}
		}.add(new TooltipBehavior(descSaveButton)));

		add(form);

	}

}
