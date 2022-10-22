package it.spaghettisource.certgen.ui.swing.component.calendar.ui.strategy;

public enum DisplayStrategyType {

	MONTH("month"),MONTH_TEST_DAY("monthTestDay"),MONTH_TEST_WEEK("monthTestWeek");
	
	DisplayStrategyType(String i18n){
		this.i18n = i18n;
	}
	
	private String i18n;

	public String getI18n() {
		return i18n;
	}
}
