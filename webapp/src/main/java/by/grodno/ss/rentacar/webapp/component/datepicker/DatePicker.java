package by.grodno.ss.rentacar.webapp.component.datepicker;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.wicket.datetime.DateConverter;
import org.apache.wicket.datetime.markup.html.form.DateTextField;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.model.IModel;

public class DatePicker extends DateTextField {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final DatePickerOptions pickerOptions;

	public DatePicker(String id, DateConverter converter, DatePickerOptions pickerOptions) {
		super(id, converter);
		this.pickerOptions = pickerOptions;
	}

	public DatePicker(String id, IModel<Date> model, DateConverter converter, DatePickerOptions pickerOptions) {
		super(id, model, converter);
		this.pickerOptions = pickerOptions;
	}

	@Override  //воткнем js-скрипт на страницу для компонента
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);
		response.render(OnDomReadyHeaderItem.forScript(createDatePickerScript()));
	}

	private String createDatePickerScript() {
		StringBuilder sb = new StringBuilder();
		sb.append("$(function() {");
		sb.append("$(\"#").append(pickerOptions.getElementId()).append("\").datetimepicker({");
		sb.append("viewMode: '").append(pickerOptions.getViewMode()).append("',");
		sb.append("format: '").append(pickerOptions.getFormat()).append("',");

		if (pickerOptions.getMinDate() != null) {
			sb.append("minDate: '").append(getDateAsString(pickerOptions.getMinDate())).append("'");
		}

		sb.append("});");
		sb.append("})");
		return sb.toString();
	}

	private String getDateAsString(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("MM.dd.YYYY");
		return format.format(date);
	}

}
