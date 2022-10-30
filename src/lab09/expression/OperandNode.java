package lab09.expression;

/**
 * Operand nodes form the leaves of expression trees. They store doubles.
 * 
 * @author Nathan Sprague
 * @version 10/2016
 *
 */
public class OperandNode extends ExpressionNode {
  private final double number;

  public OperandNode(double number) {
    super(2);
    this.number = number;
  }

  @Override
  public double evaluate() {
    return number;
  }

  @Override
  public String toString() {
    return "" + number;
  }

  @Override
  public String toPostfix() {
    return toString();
  }

  @Override
  public String toPrefix() {
    return toString();
  }

  @Override
  public String toInfix() {
    return toString();
  }
}