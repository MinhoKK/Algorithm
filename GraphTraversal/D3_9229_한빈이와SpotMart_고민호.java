package GraphTraversal;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class D3_9229_한빈이와SpotMart_고민호 {
	/**
	 * @author 고민호
	 * Swea_9229_한빈이와SpotMart
	 * 난이도 D3
	 * 결과 : 통과		메모리 : 25,424kb		시간 : 171ms
	 * 
	 * 풀이방식
	 * 1. 들고 다닐수 있는 과자의 조합 만들기(조합의 크기는 2)
	 * 2. 조합에 포함된 과자의 무게 합 구하기
	 * 3. 무게 합이 M보다 작거나 같으면 현재 최대값과 비교하여 갱신하기
	 */
	static int T, N, M;	// 테스트케이스 수, 과자봉지 수, 과자봉지 무게
	static int[] snack;	// 과자
	static int[] comb;	// 과자 조합
	static int result;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		T = parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			N = parseInt(st.nextToken());
			M = parseInt(st.nextToken());
			
			snack = new int[N];
			comb = new int[2];	// 들수있는 과자봉지는 2개
			result = Integer.MIN_VALUE;
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				snack[i] = parseInt(st.nextToken());
			}
			
			makeComb(0, 0);
			// 조건에 만족하는 과자 조합이 생성되지 않은경우 -1 출력
			if(result == Integer.MIN_VALUE) {
				sb.append("#").append(t).append(" ").append(-1).append("\n");
				continue;
			}
					
			sb.append("#").append(t).append(" ").append(result).append("\n");
		}
		
		System.out.println(sb);
		
	}
	
	static void makeComb(int depth, int start) {
		// 과자가 2개 조합
		if(depth == 2) {
			int sum = 0;
			// 과자 조합의 무게 합 구하기
			for(int index : comb) {
				sum += snack[index];
			}
			// 무게합이 M보다 작은 경우 최대값 비교 및 갱신
			if(sum <= M)
				result = Math.max(result, sum);
			
			return;
		}
		
		// 과자 조합 만들기
		for(int i=start; i<N; i++) {
			comb[depth] = i;
			makeComb(depth+1, i+1);
		}
	}

}
