package lab09.expression;

/**
 * Abstract base class for nodes in a binary tree representing an arithmetic
 * expression.
 * 
 * @author Nathan Sprague
 * @version 10/2016
 */
public abstract class ExpressionNode {
  private final int precedence;

  public ExpressionNode(int precedence) {
    this.precedence = precedence;
  }

  public int precedence() {
    return precedence;
  }

  /**
   * Return the value of the expression tree rooted at this node.
   */
  public abstract double evaluate();

  /**
   * Return a string containing the postfix representation of the expression
   * tree rooted at this node.
   * 
   * For example, the following tree:
   * 
   * <pre>
   *            *
   *           / \
   *          +  2.0
   *         / \
   *       4.0  1.0
   * </pre>
   * 
   * would evaluate to "4.0 1.0 + 2.0 *"
   */
  public abstract String toPostfix();

  /**
   * Return a string containing the prefix representation of the expression
   * tree rooted at this node.
   * 
   * For example, the following tree:
   * 
   * <pre>
   *            *
   *           / \
   *          +  2.0
   *         / \
   *       4.0  1.0
   * </pre>
   * 
   * 
   * would evaluate to "* + 4.0 1.0 2.0"
   */
  public abstract String toPrefix();

  /**
   * Return a string containing the infix representation of the expression
   * tree rooted at this node. Parentheses are inserted to match the tree's
   * association.
   * 
   * For example, the following tree:
   * 
   * <pre>
   *            *
   *           / \
   *          +  2.0
   *         / \
   *       4.0  1.0
   * </pre>
   * 
   * would evaluate to "(4.0 + 1.0) * 2.0"
   */
  public abstract String toInfix();
}
