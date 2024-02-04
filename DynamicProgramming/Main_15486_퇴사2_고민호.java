package DynamicProgramming;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Main_15486_퇴사2_고민호 {
	/**
	 * @author 고민호
	 * 백준_15486_퇴사2
	 * 난이도 G5
	 * 결과 : 통과		메모리 : 316,180KB		시간 : 720ms
	 * 
	 * 풀이방법
	 * 1. DP를 통해 문제풀기
	 * 2. 얻을 수 있는 최대 금액을 구하기 위한 점화식 찾기
	 * 3. N번째 날에 Pn=1일 경우를 위해 탐색 범위는 N+1 -> 상담 소요기간, 금액, dp 배열 크기 N+1
	 */

	static int N;	// 남은 일수
	static int[] time;	// 상담 소요 기간
	static int[] pay;	// 상담 금액
	static int[] dp;	// 최대 금액 저장
	static int maxPay = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = parseInt(br.readLine());
		time = new int[N+1];
		pay = new int[N+1];
		dp = new int[N+1];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			time[i] = parseInt(st.nextToken());
			pay[i] = parseInt(st.nextToken());
		}
		
		for(int i=0; i<N+1; i++) {
			// i날에 받을 수 있는 금액(dp[i])은 i날 이전에 정해짐
			maxPay = Math.max(maxPay, dp[i]);	// 최대 금액 수정 (현재 최대 금액 OR i날 받을 수 있는 금액)
			
			// nextDay는 i날 일하고 종료되는 날 = 금액이 수정되는 날
			int nextDay = i + time[i];
			if(nextDay < N+1) {
				// nextDay에 받을 수 있는 최대 금액 = i날 이전에 정해진 최대 금액 OR 현재까지의 최대금액 + i날 일해서 받는 금액
				dp[nextDay] = Math.max(dp[nextDay], maxPay + pay[i]);
			}
		}
		
		System.out.println(maxPay);
	}
}
