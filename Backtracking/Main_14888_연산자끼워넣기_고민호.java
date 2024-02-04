package Backtracking;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Main_14888_연산자끼워넣기_고민호 {
	/**
	 * @author 고민호
	 * 백준_14888_연산자끼워넣기
	 * 난이도 S1
	 * 결과 : 통과		메모리 : 15,300KB		시간 : 144ms
	 * 
	 * 풀이방법
	 * 1. 가능한 연산자 조합 만들기
	 * 2. 조합 만들때 재귀 사용하기
	 */

	static int N;	// 연산에 사용되는 숫자의 수
	static int[] num;	// 주어진 숫자 배열
	static int[] operator;	// 연산자 갯수 배열
	static int[] comb;	// 연산자 조합 배열
	static int minResult = Integer.MAX_VALUE;
	static int maxResult = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = parseInt(br.readLine());
		num = new int[N];
		operator = new int[4];	// 연산자는 ('+' '-' '*' '/') 4개
		comb = new int[N-1];	// 사용할 연산자의 수는 주어질 숫자보다 1개 적다
		
		// 숫자 입력
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			num[i] = parseInt(st.nextToken());
		}
		
		// 연산자 갯수 입력
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<4; i++) {
			operator[i] = parseInt(st.nextToken());
		}
		
		makeComb(0);
		
		System.out.println(maxResult);
		System.out.println(minResult);
		
	}
	
	static void makeComb(int depth) {
		// 연산자 조합이 완성된 경우
		if(depth == N-1) {
			// 연산 초기값은 첫 숫자
			int result = num[0];
			// 연산자의 갯수만큼 반복
			for(int i=0; i<N-1; i++) {
				// '+' '-' '*' '/' 순서이기에 0인경우 '+' 1인경우 '-' 2인경우 '*' 3인경우 '/'
				if(comb[i] == 0) {
					result = result + num[i+1];
				} else if(comb[i] == 1) {
					result = result - num[i+1];
				} else if(comb[i] == 2) {
					result = result * num[i+1];
				} else if(comb[i] == 3) {
					result = result / num[i+1];
				}
			}
			
			// 최대, 최소값 구하기
			minResult = Math.min(minResult, result);
			maxResult = Math.max(maxResult, result);
		}
		
		for(int i=0; i<4; i++) {
			// 사용가능한 연산자가 있는 경우
			if(operator[i] != 0) {
				operator[i]--;	// 연산자 사용을 표시하기 위해 -1
				comb[depth] = i;	// 연산자 조합 만들기
				makeComb(depth+1);	// 재귀
				operator[i]++;	// 연산자 사용이 끝났다면 +1
				
			}
		}
	}
}
