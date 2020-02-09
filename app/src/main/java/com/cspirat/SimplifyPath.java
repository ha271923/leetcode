package com.cspirat;

import java.util.Stack;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : SimplifyPath
 * Creator : Edward
 * Date : Nov, 2017
 * Description : 71. Simplify Path
 */
public class SimplifyPath {
    public static void main(String[] args) {
        // String input="/home/"; //  Ans: "/home"
        String input="/a/./b/../../c/"; // Ans: "/c"
        System.out.println(simplifyPath(input));
    }

    /**
     * Given an absolute path for a file (Unix-style), simplify it.

     For example,
     path = "/home/", => "/home"
     path = "/a/./b/../../c/", => "/c"

     time : O(n)
     space :O(n)


     * @param path
     * @return
     */
    // Feature requirement:
    // 1. remove last '/' symbol
    // 2. current path symbol "/."
    // 3. path delete symbol "/.."
    // 路徑題, 大多是用stack去解:
    static public String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();

        String[] paths = path.split("/+"); // Tips: split()會把'/'當成區隔符號,把區隔出來的字串們存到path陣列
        // before split = "/a/./b/../../c/"
        //                [0][1] [2] [3] [4]  [5]  [6]
        // after  split = ""+"a"+"."+"b"+".."+".."+"c"
        for (String s : paths) { // LOOP: 過濾出標準字串路徑
            if (s.equals("..")) { // 路徑回上一層
                if (!stack.isEmpty()) {
                    stack.pop(); // 回上一層
                }
            } else if (!s.equals(".") && !s.equals("")) { // 標準路徑字串
                stack.push(s); // 路徑往下一層
            }
            // 其他情況,維持現有路徑
        }

        String res = "";
        while (!stack.isEmpty()) { // LOOP: 路徑全部接起來
            res = "/" + stack.pop() + res;
        }

        if (res.length() == 0) { // 若都沒有路徑, 返回Root
            return "/";
        }
        return res;
    }
}
