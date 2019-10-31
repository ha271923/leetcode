package com.basic;

public class JavaLambda {
    /**
     *
     * 以下是lambda表達式的重要特徵:
     *   可選類型聲明：不需要聲明參數type，編譯器可以統一識別參數值。
     *   可選的參數圓括號：一個參數無需定義圓括號，但多個參數才需要定義圓括號()。
     *   可選的大括號：如果主體包含了一個語句，就不需要使用大括號。
     *   可選的返回關鍵字：如果主體只有一個表達式返回值則編譯器會自動返回值，大括號需要指定明表達式返回了一個數值。
     *   Ex:
     *   // 1.不需要參數,返回值為5
     *        () -> 5
     *   // 2.接收一個參數(數字類型),返回其2倍的值
     *        x -> 2 * x
     *   // 3.接受2個參數(數字),並返回他們的差值
     *        ( x , y ) -> x – y
     *   // 4.接收2個int型整數,返回他們的和
     *        ( int x , int y ) -> x + y
     *   // 5.接受一個string對象,並在控制台打印,不返回任何值(看起來像是返回void)
     *        ( String s ) -> System.out.print(s)
     */
    public static void main(String args[]){
        JavaLambda lambdaMath = new JavaLambda();

        ////////////////////////////////////////////////////////
        // 类型声明: with int type
        MathOp ADD = (int a, int b) -> a + b;

        // 不用类型声明: without int type
        MathOp SUB = (a, b) -> a - b;

        // 大括号中的返回语句: return with return keyword
        MathOp MUL = (int a, int b) -> { return a * b; };

        // 没有大括号及返回语句: return without return keyword
        MathOp DIV = (int a, int b) -> a / b;

        // Lambda function as param
        System.out.println("10 + 5 = " + lambdaMath.algorithm(10, 5, ADD));
        System.out.println("10 - 5 = " + lambdaMath.algorithm(10, 5, SUB));
        System.out.println("10 x 5 = " + lambdaMath.algorithm(10, 5, MUL));
        System.out.println("10 / 5 = " + lambdaMath.algorithm(10, 5, DIV));

        ////////////////////////////////////////////////////////
        // 不用括号
        APIs api1 = message ->
                System.out.println("api1: " + message);

        // 用括号
        APIs api2 = (message) ->
                System.out.println("api2: " + message);

        api1.printMsg("message");
        api2.printMsg("(message)");

        ////////////////////////////////////////////////////////
        final int num = 1; // Lambda 表达式的局部变量可以不用声明为 final，但是必须不可被后面的代码修改（即隐性的具有 final 的语义）
        Converter<Integer, String> s = (param) ->
                System.out.println(String.valueOf(param + num));
        s.convert(2);  // 输出结果为 3
    }

    private int algorithm(int a, int b, MathOp mathOp){
        return mathOp.OpCode(a, b);
    }
    interface MathOp {
        int OpCode(int a, int b);
    }

    interface APIs {
        void printMsg(String message);
    }

    public interface Converter<T1, T2> {
        void convert(int i);
    }




}