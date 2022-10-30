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
    if (left == null && right == null) {
      return operator.symbol();
    }
    return left.toPostfix() + right.toPostfix();

  }

  @Override
  public String toPrefix() {
    // TODO: See the description in the superclass.
    return null;
  }

  @Override
  public String toInfix() {
    // TODO: See the description in the superclass.
    return null;
  }
}
