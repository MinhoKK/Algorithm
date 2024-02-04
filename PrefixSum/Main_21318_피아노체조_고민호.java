package PrefixSum;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Main_21318_피아노체조_고민호 {
	/**
	 * @author 고민호
	 * 백준_피아노체조_21318
	 * 난이도 S1
	 * 결과 : 통과		메모리 : 62,356KB		시간 : 724ms
	 * 
	 * 풀이방법
	 * 1. 악보의 개수는 1 ~ 100,000으로 Q(1 ~ 100,000)번 완전 탐색할 경우 시간복잡도 O(N^2)일때 최악의 경우 100억으로 10초이상 소요
	 * 2. 1번의 이유로 난이도를 받을때 실수하는 곡의 수에대한 누적합을 구하기
	 */
	
	static int N, Q;	// 악보의 개수, 질문의 개수
	static int[] hard;	// 악보의 난이도 배열
	static int[] preSum;	// 실수 누적합 배열
	static StringBuilder sb = new StringBuilder();
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = parseInt(br.readLine());
		
		// 곡의 번호가 1로 시작, 누적합 계산시 편의상 각 배열의 크기 +1
		hard = new int[N+1];
		preSum = new int[N+1];
		
		// 곡 난이도 입력
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			hard[i] = parseInt(st.nextToken());
		}
		
		// N번째는 실수를 않기에 N 이전까지 실수 누적합 구하기
		for(int i=1; i<N; i++) {
			int isMistake = hard[i] > hard[i+1] ? 1 : 0;	// 현재 곡의 난이도가 다음 곡의 난이도 보다 클경우 실수 횟수 1, 그렇지 않다면 0
			preSum[i] = preSum[i-1] + isMistake;	// 실수 누적은 이전까지의 실수 횟수 + 현재 곡의 실수 여부
		}
		
		Q = parseInt(br.readLine());
		
		for(int i=0; i<Q; i++) {
			st = new StringTokenizer(br.readLine());
			
			int start = parseInt(st.nextToken());	// x번 악보 -> 실수 누적 횟수 탐색 시작 위치
			int end = parseInt(st.nextToken());	// y번 악보 -> 실수 누적 횟수 탐색 종료 위치
			
			int mistakeNum = preSum[end - 1] - preSum[start - 1];	// 구하고자 하는 실수 횟수
			
			sb.append(mistakeNum).append("\n");
		}
		System.out.println(sb);
	}
}
