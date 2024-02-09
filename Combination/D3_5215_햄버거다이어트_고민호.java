package Combination;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class D3_5215_햄버거다이어트_고민호 {
	/**
	 * @author 고민호
	 * Swea_5215_햄버거다이어트
	 * 난이도 D3
	 * 결과 	메모리 : 21,804KB		시간: 891ms
	 */
	static int T;
	static int N, L;	// 재료 수, 제한 칼로리
	static int[][] foodInfo; // 재료 정보
	static int[] comb;	// 재료 조합
	static int result;	//재료 조합의 맛 최대값
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		T = parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			
			N = parseInt(st.nextToken());
			L = parseInt(st.nextToken());
			result = Integer.MIN_VALUE;
			
			foodInfo = new int[N][2];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				foodInfo[i][0] = parseInt(st.nextToken());	// 재료 점수
				foodInfo[i][1] = parseInt(st.nextToken());	// 재료 칼로리
			}
			
			// 음식 조합은 1~N개 까지 가능
			for(int i=1; i<=N; i++) {
				comb = new int[i];
				dfs(i, 0, 0);
			}
			
			sb.append("#").append(t).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
	}
	
	static void dfs(int count, int depth, int start) {
		if(depth == count) {
			int kcal = 0;
			int point = 0;
			for(int index : comb) {
				// 현재 칼로리와 다음 재료의 칼로리 합이 L보다 작은 경우
				if(kcal + foodInfo[index][1] <= L) {
					point += foodInfo[index][0];	// 재료 점수 누적
					kcal += foodInfo[index][1]; 	// 재료 칼로리 누적
				}
			}
			result = Math.max(result, point);	// 재료 점수 최대값 갱신
			return;
		}
		
		for(int i=start; i<N; i++) {
			comb[depth] = i;
			dfs(count, depth+1, i+1);
		}
	}

}
