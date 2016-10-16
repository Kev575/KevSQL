package kev575.kevsql.components;

/**
 * SELECT Operator<br>"SELECT * FROM test WHERE uniqueid " + {@link MathOperator#EQUALS} + " 'mycooluniqueid'"
 * @author Kev575
 */
public enum MathOperator {
	
	EQUALS("="), NOT_EQUALS("!="), NOT_EQUALS_GL("<>"), GREATER(">"),
	LESS("<"), GREATER_OR_EQUALS(">="), LESS_OR_EQUALS("<="),
	BETWEEN("BETWEEN"), LIKE("LIKE"), IN("IN");
	
	private final String operator;
	
	private MathOperator(String operator) {
		this.operator = operator;
	}
	
	public String toOperator() {
		return operator;
	}
}
