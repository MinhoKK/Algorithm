package Combination;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class 모의SW역량테스트_4012_요리사_고민호 {
	/**
	 * @author 고민호
	 * 백준_4012_요리사
	 * 결과 : 통과		메모리 : 51,824KB		시간 : 292ms
	 * 
	 * 풀이방식
	 * 1. 재료 조합 구하기
	 * 2. 재료 수 크기의 1차원 배열 만들기 -> 조합에 포함된 음식은 1, 포함안된 음식은 0으로 표시
	 * 3. 1로 표시된 재료들의 음식값 구하기
	 * 4. 0으로 표시된 재료들의 음식값 구하기
	 * 5. 3, 4번의 최소값 구하기
	 */

	static int T;
	static int N;
	static int[][] food;	// 재로 시너지 표
	static int[] comb;	// 재료 조합
	static int[] checkFood;	// 두 음식에 사용된 재료 확인
	static int result;	// 두 음식값의 차
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		T = parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			N = parseInt(br.readLine());
			food = new int[N][N];
			comb = new int[N/2];	// 재료 조합에 사용하는 재료는 전체의 절반
			result = Integer.MAX_VALUE;
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					food[i][j] = parseInt(st.nextToken());
				}
			}
			
			makeComb(0, 0);
			System.out.println("#" + t + " " + result);
		}
	}
	
	static void makeComb(int depth, int start) {
		// 재료 조합 완성된 경우
		if(depth == N/2) {
			checkFood = new int[N];
			for(int foodIndex : comb) {
				checkFood[foodIndex] = 1;	// 고른 재료는 1, 안고른 재료는 0 -> 1로 만든 음식, 0으로 만든 음식
			}
			
			ArrayList<Integer> tempComb1 = new ArrayList<>();	// 1번 재료들을 담을 임시 리스트
			ArrayList<Integer> tempComb2 = new ArrayList<>();	// 0번 재료들을 담을 임시 리스트
			for(int i=0; i<checkFood.length; i++) {
				if(checkFood[i] == 1) {
					tempComb1.add(i);
				} else {
					tempComb2.add(i);
				}
			}
			
			// 1번에 사용된 재료들의 시너지 합 구하기
			int sum1 = 0;
			for(int i=0; i<tempComb1.size(); i++) {
				for(int j=0; j<tempComb1.size(); j++) {
					sum1 += food[tempComb1.get(i)][tempComb1.get(j)];
				}
			}
			// 0번에 사용된 재료들의 시너지 합 구하기
			int sum2 = 0;
			for(int i=0; i<tempComb2.size(); i++) {
				for(int j=0; j<tempComb2.size(); j++) {
					sum2 += food[tempComb2.get(i)][tempComb2.get(j)];
				}
			}
			
			// 두 음식의 시너지 합 차이의 최소값 갱신
			result = Math.min(result, Math.abs(sum1 - sum2));
			return;
		}
		
		// 재료 조합 만들기
		for(int i=start; i<N; i++) {
			comb[depth] = i;
			makeComb(depth+1, i+1);
		}
	}
	
}
