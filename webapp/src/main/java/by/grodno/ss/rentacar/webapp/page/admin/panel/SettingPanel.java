package by.grodno.ss.rentacar.webapp.page.admin.panel;

import java.util.Arrays;

import javax.inject.Inject;

import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

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
	private IModel<String> descCurrency 		= Model.of("Выбор валюты");
	private IModel<String> descCarWhilePending 	= Model.of("Промежуток времени");
	private IModel<String> descCarBetweenPending= Model.of("Промежуток времени между новым и старым заказом");
	private IModel<String> descDepositPayment 	= Model.of("Процент предоплаты");
	
	public SettingPanel(String id) {
		super(id);
		this.setting = settingService.get();
	}

	public SettingPanel(String id, IModel<?> model) {
		super(id, model);
		this.setting = settingService.get();
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		Form<Setting> form = new Form<Setting>("form-options", new CompoundPropertyModel<Setting>(setting));

		NumberTextField<Integer> minBookingLength = new NumberTextField<Integer>("minBookingLength");
		minBookingLength.setRequired(true);
		minBookingLength.setMinimum(0).setMaximum(1000);
		minBookingLength.add(new TooltipBehavior(descMinBookingLength));
		form.add(minBookingLength);
		
		NumberTextField<Integer> carWhilePending = new NumberTextField<Integer>("carWhilePending");
		carWhilePending.setRequired(true);
		carWhilePending.setMinimum(0).setMaximum(1000);
		carWhilePending.add(new TooltipBehavior(descCarWhilePending));
		form.add(carWhilePending);
		
		NumberTextField<Integer> carBetweenPending = new NumberTextField<Integer>("carBetweenPending");
		carBetweenPending.setRequired(true);
		carBetweenPending.setMinimum(0).setMaximum(1000);
		carBetweenPending.add(new TooltipBehavior(descCarBetweenPending));
		form.add(carBetweenPending);
		
		NumberTextField<Integer> depositPayment = new NumberTextField<Integer>("depositPayment");
		depositPayment.setRequired(true);
		depositPayment.setMinimum(0).setMaximum(100);
		depositPayment.add(new TooltipBehavior(descDepositPayment));
		form.add(depositPayment);
		
		DropDownChoice<Currency> currencyDropDown = new DropDownChoice<>("currency", Arrays.asList(Currency.values()), CurrencyChoiceRenderer.INSTANCE);
		currencyDropDown.setRequired(true);
		currencyDropDown.add(new TooltipBehavior(descCurrency));
        form.add(currencyDropDown);
		
		form.add(new SubmitLink("save-options") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit() {
				settingService.saveOrUpdate(setting);
				PageParameters pageParameters = new PageParameters();
				pageParameters.add("msg", "Settings was updated");
				setResponsePage(new SettingEditPage(pageParameters));
			}
		}.add(new TooltipBehavior(descSaveButton)));

		add(form);

	}

}
