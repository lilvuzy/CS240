package lab09.expression;

/**
 * Operator nodes are the internal nodes of a binary expression tree.
 * 
 * @author ???
 * @version ???
 */
public class OperatorNode extends ExpressionNode {
  private final Operator operator;
  private final ExpressionNode left;
  private final ExpressionNode right;

  public OperatorNode(Operator operator, ExpressionNode left, ExpressionNode right) {
    super(operator.precedence());
    this.operator = operator;
    this.left = left;
    this.right = right;
  }

  /**
   * Evaluate the expression rooted at this node and return the result.
   */
  @Override
  public double evaluate() {
    // TODO
    return 0.0;
  }

  @Override
  public String toPostfix() {
    // TODO: See the description in the superclass.
    if (operator == null) {
      return "";
    }
    return left.toPostfix() + " " +   right.toPostfix() +  " " +  operator.symbol();

  }

  @Override
  public String toPrefix() {
    // TODO: See the description in the superclass.
    if (operator == null) {
      return "";
    }
    return  operator.symbol() +  " " +  left.toPrefix() +  " " +  right.toPrefix();
  }


  @Override
  public String toInfix() {
    // TODO: See the description in the superclass.
    if (operator == null) {
      return "";
    }
    if (operator.precedence() == right.precedence()) {
      return left.toInfix() + " " +  operator.symbol() + " (" + right.toInfix() + ")";
    } else if (operator.precedence() < right.precedence() && right.precedence() != 2) {
      return "(" + left.toInfix() + " " +  operator.symbol() + " " + right.toInfix() + ")";
    }
    return left.toInfix() + " " +  operator.symbol() + " " + right.toInfix();
  }
}
