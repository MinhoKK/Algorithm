package DataStructure;

import java.io.*;
import java.util.*;

public class BJ_10799_쇠막대기 {
    /**
     * @author 고민호
     * 백준 10799 쇠막대기
     * Silver 2
     *
     * 풀이방법
     * 1. Stack 자료구조를 사용한 문제
     * 2. '(' -> push, ')' -> add
     */

    static String list;
    static Stack<Character> stack = new Stack<>();
    static int result;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        list = br.readLine();

        for(int i=0; i<list.length(); i++){
            char now = list.charAt(i);

            if(now == '('){
                stack.add(now);
            } else if(now == ')'){
                stack.pop();

                if(list.charAt(i-1) == '('){
                    result += stack.size();
                } else {
                    result++;
                }
            }
        }

        System.out.println(result);
    }
}
