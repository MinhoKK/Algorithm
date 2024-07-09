package Temp;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class BJ_1043_거짓말 {

    static int N, M;    // 사람의 수, 파티의 수
    static int[] know;
    static int[] parent;


    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());

        know = new int[N+1];
        parent = new int[N+1];

        st = new StringTokenizer(br.readLine());

        int knowCount = 0;
        knowCount = parseInt(st.nextToken());

        // 진실을 아는사람 입력받기
        for(int i=0; i<knowCount; i++){
            st = new StringTokenizer(br.readLine());
            int value =  parseInt(st.nextToken());
            know[value] = 1;
        }
    }

    static int find(int a){

    }
}
