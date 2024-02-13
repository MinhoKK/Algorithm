package Greedy;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Main_16435_스네이크버드_고민호 {
	/**
	 * @author 고민호
	 * 백준_16435_스네이크버드
	 * 난이도 S5
	 * 결과 : 통과		메모리 : 14,384KB		시간 : 132ms
	 * 
	 * 풀이방법
	 * 1. 그리디 알고리즘으로 문제 해결을 위해 주어진 과일 높이 오름차순 정렬
	 * 2. 끝까지 탐색 완료 하거나 과일의 높이가 스네이크버드의 높이보다 클 경우 종료
	 */

	static int N, L;	// 과일의 개수, 스네이크버드 초기 길이
	static int[] food;	// 과일 높이
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = parseInt(st.nextToken());
		L = parseInt(st.nextToken());
		
		food = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			food[i] = parseInt(st.nextToken());
		}
		
		// 그리디 알고리즘 적용을 위한 과일 높이 오름차순 정렬
		Arrays.sort(food);
		
		for(int i=0; i<N; i++) {
			// 현재 과일의 높이가 스네이크버드 길이보다 긴 경우 종료
			// 현재 과일의 뒤에는 정렬로 인해 더 큰 과일만 존재하기 때문
			if(food[i] > L) break;
			// 과일을 먹은 경우 스네이크버드 길이 증가
			L++;
		}
		
		System.out.println(L);
	}

}
