package DynamicProgramming;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Main_1149_RGB거리_고민호 {
	/**
	 * @author 고민호
	 * 백준_1149_고민호
	 * 난이도 S1
	 * 결과 : 통과		메모리 : 14,608KB		시간 : 140ms
	 * 
	 * 풀이방법
	 * 1. 경우의 수 3가지 선정 (빨간집, 초록집, 파란집)
	 * 2. 현재 집의 색이 R이라면 이전 집의 색 G, B 중 최소값 선택하기
	 * 3. 2번을 반복하며 마지막 집까지 최소비용 선택하기
	 */
	
	static int N;
	static int[][] cost;	// 각각의 집을 칠하는 비용
	static int[][] dp;
	static int result;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = parseInt(br.readLine());
		cost = new int[N][3];	// 0 = R, 1 = G, 2 = B
		dp = new int[N][3];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<3; j++) {
				cost[i][j] = parseInt(st.nextToken());
			}
		}
		
		// R G B 각각의 색으로 시작하는 경우 비용 초기화
		dp[0][0] = cost[0][0];
		dp[0][1] = cost[0][1];
		dp[0][2] = cost[0][2];
		
		for(int i=1; i<=N-1; i++) {
			dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + cost[i][0];	// 현재 집의 색이 R인경우, 이전 집의 색은 G B 중 비용이 적은것을 선택
			dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + cost[i][1];	// 현재 집의 색이 G인경우, 이전 집의 색은 R B 중 비용이 적은것을 선택
			dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + cost[i][2];	// 현재 집의 색이 B인경우, 이전 집의 색은 R G 중 비용이 적은것을 선택
		}
		
		result = Math.min(dp[N-1][0], dp[N-1][1]);
		result = Math.min(result, dp[N-1][2]);
		System.out.println(result);	// 모든 집을 칠하는 최소 비용 = 마지막 집이 빨강인 경우의 최소비용, 마지막 집이 초록인 경우의 최소비용, 마지막 집이 파란인 경우의 최소비용 중에 최소비용
	}

}
