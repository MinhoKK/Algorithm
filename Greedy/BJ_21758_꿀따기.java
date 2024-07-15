package Greedy;

import java.io.*;
import java.util.*;

import static java.lang.Integer.parseInt;

public class BJ_21758_꿀따기 {
    /**
     * @author 고민호
     * 백준 21758 꿀따기
     * 난이도 G5
     * 결과 : 통과      메모리 : 24,680KB      시간 : 272ms
     *
     * 풀이방법
     * 1. N의 최대 크기가 100,000으로 벌,벌,벌통 3가지 위치의 조합 100,000C3은 시간초과 발생 예상
     * 2. 결국 각 위치에서의 합을 구하는 문제로 누적합 접근
     * 3. 그리디 문제이기에 우선 정렬을 고민해봤지만 X
     * 4. 벌과 벌통이 특정 위치에 존재하야 한다면 가능한 경우의 수을 구하여 문제 해결
     * 
     * 그리기 꿀팁: 정렬을 고민해보고 정렬로 해결되지 않는다면 해당 문제에 주어진 조건을 만족하는 경우의 수를 고민해보자
     * 해당 문제에서는 벌 벌 벌통의 순서에 따른 경우의 수
     *
     */

    static int N;
    static int[] arr;
    static int[] prefix;
    static int result = Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = parseInt(br.readLine());

        arr = new int[N];
        prefix = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr[i] = parseInt(st.nextToken());
        }

        prefix[0] = arr[0];
        for(int i=0; i<N-1; i++){
            prefix[i+1] = prefix[i] + arr[i+1];
        }

        // 1. 벌 벌 벌통 = 양 끝에 벌과 벌통이 고정인 경우
        for(int i=1; i<N-1; i++){   // i는 가운데 벌이 위치할 수 있는 인덱스
            int sum = 0;

            int bee1 = prefix[N-1] - arr[0] - arr[i];   // 왼쪽 고정 벌의 꿀
            int bee2 = prefix[N-1] - prefix[i];
            sum = bee1+bee2;

            result = Math.max(result, sum);
        }
        
        // 2. 벌통 벌 벌 = 양 끝에 벌통과 벌이 고정인 경우
        for(int i=1; i<N-1; i++){   // i는 가운데 벌이 위치할 수 있는 인덱스
            int sum = 0;

            int bee1 = prefix[N-1] - arr[N-1] - arr[i]; // 오른쪽 고정 벌의 꿀
            int bee2 = prefix[i] - arr[i];
            sum = bee1+bee2;

            result = Math.max(result, sum);
        }
        
        // 3. 벌 벌통 벌 = 양 끝에 벌이 고정인 경우
        for(int i=1; i<N-1; i++){   // i는 가운데 벌통이 위치할 수 있는 인덱스
            int sum = 0;

            int bee1 = prefix[i] - arr[0]; // 왼쪽 고정 벌의 꿀
            int bee2 = prefix[N-2] - prefix[i-1];  // 오른쪽 고정 벌의 꿀
            sum = bee1+bee2;

            result = Math.max(result, sum);
        }

        System.out.println(result);
    }
}
