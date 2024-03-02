package DynamicProgramming;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Main_1010_다리놓기_고민호 {
	/**
	 * @author 고민호
	 * 백준_1010_다리놓기
	 * 난이도 S5
	 * 결과 : 통과		메모리 :14,500KB		시간 : 152ms
	 * 
	 * 풀이방법
	 * 1. 서쪽 사이트 4개, 동쪽 사이트 7개인 경우 7개의 사이트중 4개를 선택하는 경우의 수이다. 즉 7C4와 동일하다.
	 * 2. dp에 파스칼의 삼각형을 이용해 문제풀이
	 */

	static int T;
	static int N, M;
	static int[][] dp;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		dp = new int[31][31];
		
		// 초기값 설정
		for(int i=0; i<31; i++) {
			dp[i][i] = 1;
			dp[i][0] = 1;
		}
		
		// 파스칼 삼각형 공식 사용
		for(int i=1; i<31; i++) {
			for(int j=i-1; j>=1; j--) {
				dp[i][j] =  dp[i-1][j] + dp[i-1][j-1];
			}
		}
		
		
		T = parseInt(br.readLine());
		
		for(int i=0; i<T; i++) {
			st = new StringTokenizer(br.readLine());
			N = parseInt(st.nextToken());
			M = parseInt(st.nextToken());
			
			System.out.println(dp[M][N]);
		}
	}

}
