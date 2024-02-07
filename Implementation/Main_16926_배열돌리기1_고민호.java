package Implementation;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Main_16926_배열돌리기1_고민호 {
	/**
	 * @author 고민호
	 * 백준_16926_배열돌리기1
	 * 난이도 S1
	 * 결과 : 통과		메모리 : 31256KB		시간 704ms
	 * 
	 * 풀이방법
	 * 1. 각 테투리별로 rotate 메서드 생성하기
	 * 2. rotate 시 시작좌표와 종료좌표 전달
	 */

	static int N, M, R;	// 행길이, 열길이, 회전 수
	static int[][] arr;	// 배열
	static int next;	// 다음 rotate 초기값
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = parseInt(st.nextToken());
		M = parseInt(st.nextToken());
		R = parseInt(st.nextToken());

		arr = new int[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				arr[i][j] = parseInt(st.nextToken());
			}
		}
		
		// 시작, 종료 좌표 초기화
		int startR = 0;
		int endR = N-1;
		int startC = 0;
		int endC = M-1;
		
		// 종료 횟수를  확인하기 위한 변수
		int count = 0;
		int rotateCount = Math.min(N, M)/2;	// 회전해야할 테두리는 가로, 세로 중 짧은 길이/2
		
		// 총 회전 R번 
		while(count < R) {
			// 내부 테두리 회전 수
			for(int i=0; i<rotateCount; i++) {
				rotate(startR+i, endR-i, startC+i, endC-i);	// 시작좌표는 +1, 종료좌표는 -1
			}
			// 총 회전수 +1
			count++;
		}

		// 출력
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				sb.append(arr[i][j]).append(" ");
				
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
	
	/** 회전 메서드 */
	static void rotate(int startR, int endR, int startC, int endC) {
		rotate1(startR, startC, endR);
		rotate2(endR, startC, endC);
		rotate3(startR, endR, endC);
		rotate4(startR, startC, endC);
	}
	
	/** 왼쪽 변 이동 메서드 (열은 고정, 행이 이동하는 상태)*/
	static void rotate1(int startR, int startC, int endR) {
		
		int temp = 0;
		int before = arr[startR][startC];	// 옮길 초기값
		
		for(int i=startR; i<endR; i++) {
			temp = arr[i+1][startC];	// 다음칸의 값이 사라지지 않도록 임시저장
			arr[i+1][startC] = before; // 다음칸으로 값 옮기기
			before = temp; 	// 다음 옮길 값은 임시저장한 값
		}
		
		next = before; // 다음 변 이동에서의 초기값
	}
	
	/** 아래 변 이동 메서드 */
	static void rotate2(int endR, int startC, int endC) {
		int temp = 0;
		int before = next;	// 이전 변 이동에서의 마지막 값
		
		for(int i=startC; i<endC; i++) {
			temp = arr[endR][i+1];	// 다음칸의 값이 사라지지 않도록 임시저장
			arr[endR][i+1] = before;	// 다음칸으로 값 옮기기
			before = temp;	// 다음 옮길 값은 임시저장한 값
		}
		
		next = before; // 다음 변 이동에서의 초기값
	}
	
	/** 오른쪽 변 이동 메서드 */
	static void rotate3(int startR, int endR, int endC) {
		int temp = 0;
		int before = next;
		
		for(int i=endR; i>startR; i--) {
			temp = arr[i-1][endC];
			arr[i-1][endC] = before;
			before = temp;
		}
		
		next = before;
	}
	
	/** 위쪽 변 이동 메서드 */
	static void rotate4(int startR, int startC, int endC) {
		int temp = 0;
		int before = next;
		
		for(int i=endC; i>startC; i--) {
			temp = arr[startR][i-1];
			arr[startR][i-1] = before;
			before = temp;
		}
	}

}
