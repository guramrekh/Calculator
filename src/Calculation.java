import java.util.Stack;
import java.util.HashMap;
import java.util.Map;

public class Calculation {
    private static Map<Integer, Long> memo = new HashMap<>();

    public double evaluate(String expression) throws Exception {
        expression = expression.replaceAll("\\s", "");
        expression = expression.replaceAll("%", "/100");
        expression = expression.replaceAll("π", "*3.141593");
        expression = expression.replaceAll("÷", "/");
        expression = expression.replaceAll("×", "*");
        expression = expression.replaceAll("mod", "%");

        Stack<Double> values = new Stack<>();
        Stack<Character> ops = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);

            if (Character.isDigit(ch) || ch == '.') {
                StringBuilder sb = new StringBuilder();
                while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i)=='.')) {
                    sb.append(expression.charAt(i++));
                }
                values.push(Double.parseDouble(sb.toString()));
                i--; 
            }
            else if (ch == '(') {
                ops.push(ch);
            }
            else if (ch == ')') {
                while (ops.peek() != '(') {
                    values.push(applyOperator(ops.pop(), values.pop(), values.pop()));
                }
                ops.pop(); 
            }
            else if (ch == '!') {
                double num = values.pop();
                values.push((double) factorial((int)num));
            }
            else if (ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '%') {
                while (!ops.isEmpty() && hasPrecedence(ch, ops.peek())) {
                    values.push(applyOperator(ops.pop(), values.pop(), values.pop()));
                }
                ops.push(ch);
            }
        }
        
        while (!ops.isEmpty()) {
            values.push(applyOperator(ops.pop(), values.pop(), values.pop()));
        }
        
        return values.pop();
    }

    private boolean hasPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')') {
            return false;
        }
        if ((op1 == '*' || op1 == '/' || op1 == '%') && (op2 == '+' || op2 == '-')) {
            return false;
        }
        return true;
    }
    
    private double applyOperator(char op, double b, double a) throws Exception {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '%': return a % b;
            case '/':
                if (b == 0) throw new Exception("Undefined");
                return a / b;
        }
        return 0.0;
    }
    public long factorial(int n) {
        if (n <= 1) {
            return 1;
        }
        if (memo.containsKey(n)) {
            return memo.get(n);
        }
        long result = n * factorial(n - 1);
        memo.put(n, result);
        return result;
    }
    
}
