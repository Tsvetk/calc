package src;

public class ACalc extends Calculator {
    public double division(double a, double b) {
        return a / b;
    };

    public double multiplication(double a, double b) {
        return a * b;
    };

    public double addition(double a, double b) {
        return a + b;
    };

    public double subtraction(double a, double b) {
        return a - b;
    };

    public String toString(Number n) {
        if (n.act.equals("OR") || n.act.equals("AND") || n.act.equals("NAND") )
        {
            return " : result  : " + Integer.toBinaryString((int)calculate(n));
        }
        else {
            return " : result : " + calculate(n);
        }
    }
    public double or(double a, double b) {
        return (int)a | (int)b;
    };

    public double and(double a, double b) {
        return (int)a & (int)b ;
    };

    public double nand(double a, double b) {
        return ~((int)a & (int)b) ;
    };

    public double calculate(Number num) {
        switch (num.act) {
            case "" :
                return 0;

            case "/":
                return division(num.a, num.b);

            case "*":
                return multiplication(num.a, num.b);

            case "+":
                return addition(num.a, num.b);

            case "-":
                return subtraction(num.a, num.b);

            case "AND":
                return and(num.a, num.b);

            case "OR":
                return or(num.a, num.b);

            case "NAND":
                return nand(num.a, num.b);

            default:
                return 0;

        }
    }
}
