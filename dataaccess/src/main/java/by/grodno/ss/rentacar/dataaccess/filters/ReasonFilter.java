package by.grodno.ss.rentacar.dataaccess.filters;

public class ReasonFilter extends AbstractFilter {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;

	public ReasonFilter() {

	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
