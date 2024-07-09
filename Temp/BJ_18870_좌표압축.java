package Temp;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class BJ_18870_좌표압축 {
    /**
     * @author 고민호
     * 백준_18870_좌표압축
     * 난이도 Silver2
     * 결과 : 통과      메모라ㅣ 264,100KB      시간 : 1,700ms
     *
     * 풀이방법
     * 처음에는 2중반복문으로 현재 값보다 작은 값들의 개수를 구했음
     * -> Xi의 범위가 약 10^18으로 2중 반복문으로 구할시 시간 초과 발생
     * 
     * 1. 한번 순회하면서 각 숫자들이 몇번째로 큰 숫자인지 파악 -> 정렬
     * 2. 같은 숫자는 같은 등급 -> HashMap을 사용하기
     */

    static int count;
    static int[] num;
    static int index;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        count = parseInt(br.readLine());

        num = new int[count];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<count; i++){
            num[i] = parseInt(st.nextToken());
        }

        int[] temp = num.clone();
        HashMap<Integer, Integer> map = new HashMap<>();
        Arrays.sort(temp);

        for(int i=0; i<count; i++){
            if(!map.containsKey(temp[i])){
                map.put(temp[i], index);
                index++;
            }
        }

        for(int value : num){
            sb.append(map.get(value)).append(" ");
        }

        System.out.println(sb);
    }
}
