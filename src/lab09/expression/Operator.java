package lab09.expression;

/**
 * Enumerated type representing possible binary operators.
 * 
 * @author Nathan Sprague
 * @version 10/2016
 *
 */
public enum Operator {
  ADD("+", 0),
  SUBTRACT("-", 0),
  MULTIPLY("*", 1),
  DIVIDE("/", 1);

  private final String symbol;
  private final int precedence;

  Operator(String symbol, int precedence) {
    this.symbol = symbol;
    this.precedence = precedence;
  }

  /**
   * Return the Operator enum that corresponds to the string representation of
   * the operator.
   * 
   * @param operator Should be one of "+", "-", "*", "/"
   * @return The corresponding Operator Enum
   * @throws IllegalArgumentException if the string does not represent a valid
   *         operator
   */
  public static Operator parseOperator(String operator) {
    for (Operator op : Operator.values()) {
      if (op.symbol.equals(operator)) {
        return op;
      }
    }
    throw new IllegalArgumentException("Unrecognized operator");
  }

  /**
   * Return the string representation of this operator.
   */
  public String symbol() {
    return symbol;
  }

  public int precedence() {
    return precedence;
  }
}