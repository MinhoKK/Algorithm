package Greedy;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class BJ_13164_행복유치원 {
    /**
     * @author 고민호
     * 백준 13164 행복유치원
     * 난이도 G5
     * 결과 : 통과      메모리 : 52,520KB      시간 : 628ms
     *
     * 풀이방법
     * 이 문제의 핵심은 정확한 구간을 구하는 것이 아닌 최소 비용을 구하는 것이다.
     * 즉 각 구간의 차이를 정리하고 필요한 구간의 최소값을 구하면된다.
     * 1. 각 구간의 차이값을 가지는 배열 생성 그리고 오름차순 정리
     * 2. 필요한 구간만큼 값 더하기
     */

    static int N, K;    // 원생 수, 조 개수
    static int[] people;
    static int[] sub;
    static int result;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = parseInt(st.nextToken());
        K = parseInt(st.nextToken());

        people = new int[N];
        sub = new int[N-1];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            people[i] = parseInt(st.nextToken());
        }

        for(int i=1; i<N; i++){
            sub[i-1] = people[i] - people[i-1];
        }

        Arrays.sort(sub);

        for(int i=0; i<sub.length - (K - 1); i++){
            result += sub[i];
        }

        System.out.println(result);
    }
}
