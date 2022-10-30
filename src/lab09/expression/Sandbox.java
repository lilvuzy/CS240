package lab09.expression;

/**
 * Simple tests for expression trees.
 * 
 * @author Nathan Sprague
 * @version 10/2016
 */
public class Sandbox {
  /**
   * Execute the test methods.
   */
  public static void main(String[] args) {
    testExpressionTree();
    testPrefixParser();
  }

  /**
   * Build a simple expression tree and test its operations.
   */
  public static void testExpressionTree() {
    // Build the expression ((7.0 * 2.0) - (8.0 / 2.0))
    ExpressionNode tree = new OperatorNode(
      Operator.SUBTRACT,
      new OperatorNode(
        Operator.MULTIPLY,
        new OperandNode(7.0),
        new OperandNode(2.0)
      ),
      new OperatorNode(
        Operator.DIVIDE,
        new OperandNode(8.0),
        new OperandNode(2.0)
      )
    );

    System.out.println("Expression Evaluates To: " + tree.evaluate());
    System.out.println("Infix Representation:    " + tree.toInfix());
    System.out.println("Prefix Representation:   " + tree.toPrefix());
    System.out.println("Postfix Representation:  " + tree.toPostfix());
  }

  /**
   * Test the prefix parser on a sample input.
   */
  public static void testPrefixParser() {
    ExpressionNode tree = PrefixParser.parseExpression("- * 7.0 2.0 / 8.0 2.0");

    System.out.println("Expression Evaluates To: " + tree.evaluate());
    System.out.println("Infix Representation:    " + tree.toInfix());
    System.out.println("Prefix Representation:   " + tree.toPrefix());
    System.out.println("Postfix Representation:  " + tree.toPostfix());
  }
}