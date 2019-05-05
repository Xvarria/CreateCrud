package com.xvarria.createcrud.external;

public enum FilterStandardOption {
	ALL_CYCLE(-1,"AllCycle","-- All Cycle --"),
	SELECT_FORM(-2, "SelectForm", "-- Select Form --"),
	SELECT_STATUS(-3, "SelectStatus", "-- Select Status --"),
	ALL_ROLE(-4, "AllRole", "View All"),
	SELECT_DEPARTMENT(-5, "SelectDept", "-- Select Department --"),
	ALL_EMP_STATUS(-6,"ViewAllStatus","View all"),
	SPAN_OF_CONTROL(-7,"InSpanOfControl","Span of Control"),
	OUT_SPAN_OF_CONTROL(-8,"OutSpanOfControl","Out of Span of Control"),
	SELECT_ACTION_KEYWORD(-9,"","-- Select Action Keyword --"),
	SELECT_HR_REPRESENTATIVE(-10,"AllHrRepresentatives","-- Select HR Representative --"),
	NO_HR_REPRESENTATIVE(-11,"NoHrRepresentative","No HR Representative assigned");
	
	
	private long numericId;
	private String value;
	private String label;
	
	private FilterStandardOption(long numericId, String value, String label) {
		this.numericId = numericId;
		this.value = value;
		this.label = label;
	}
	
	public long getNumericId() {
		return numericId;
	}

	public String getValue() {
		return value;
	}

	public String getLabel() {
		return label;
	}

	public FilterStandardOption getFilterStOptionById(long numericId){
		FilterStandardOption status = null;
		for (FilterStandardOption current : FilterStandardOption.values()){
			if (current.getNumericId() == numericId){
				status = current;
				break;
			}
		}
		return status;
	}
	
	public FilterStandardOption getFilterStOptionByValue(String value){
		FilterStandardOption status = null;
		for (FilterStandardOption current : FilterStandardOption.values()){
			if (current.getValue().equals(value)){
				status = current;
				break;
			}
		}
		return status;
	}
}
