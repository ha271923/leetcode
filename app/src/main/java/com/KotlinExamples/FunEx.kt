package com.KotlinExamples
import java.util.Scanner

class FunEx(test: Int = 0) {
    main(null)
}

fun main(args: Array<String>) {
    // scanInput()
    var inputStr:String? = null
    varEx(inputStr)
    print(sum(3,4))

    person03("John", married = true, age = 24)
    // 顯示： Name: John, Age: 24, Married: Yes

    person03("John", married = true)
    // 顯示： Name: John, Age: 0, Married: Yes
}

fun getHello() = "Hello! Kotlin!"

fun sum(a:Int, b:Int): Int = a+b
fun max(x: Int, y: Int) = if (x > y) x else y
fun max(x: Double, y: Double) = if (x > y) x else y
// 要遵守下面的規定：
//   一個函式只能有一個設定為vararg的參數
//   如果除了vararg參數外還有其它參數，vararg參數必須宣告在所有參數的最後一個
fun average(vararg ns: Int): Int {
    var total = 0

    // 使用迴圈處理陣列的所有元素（合計）
    for (n in ns) {
        total += n
    }

    // 傳回平均值
    return total / ns.size
}

fun scanInput() {
    // Creates an instance which takes input from standard input (keyboard)
    val reader = Scanner(System.`in`)
    print("Enter a number: ")
    // nextInt() reads the next integer from the keyboard
    var integer:Int = reader.nextInt()

    println( average(1, 2, 3) )

    println( average(1, 2, 3, 4, 5, 6) )

    println("You entered: $integer")
}
// 在參數宣告後面設定參數的預設值
fun person03(name: String = "No Name ", age: Int = 0, married: Boolean = false) {
    println("Name: $name, Age: $age, Married: ${if (married) "Yes" else "No"}")
}


fun varEx(inputName: String?): Unit{
    var name: String;
    name = "Hello"
    var name2: String?
    name2 = inputName
    println("name="+name)
    println("name2="+name2)

}