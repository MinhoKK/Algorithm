package Implementation;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Main_17406_배열돌리기4_고민호 {
	/**
	 * @author 고민호
	 * 백준_17406_배열돌리기4
	 * 난이도 G4
	 * 결과 : 통과		메모리 : 30,396KB		시간 : 300ms
	 * 
	 * 풀이방법
	 * 1. 연산 정보가 주어지면 가능한 연산 순서 조합 만들기 -> 재귀
	 * 2. 배열 돌리기 -> 구현
	 * 3. 조합별 최솟값을 구하기 위해 원본 배열 보존 -> 임시 배열 생성(원본 배열 깊은 복사)
	 */
	static int N, M, K;	// 배열 크기, 연산 개수
	static int[][] arr;	// 원본배열
	static int[][] rotateInfo;	// 연산 정보
	static int[] comb;	// 연산 순서 조합
	static int[] visit;	// 조합 생성 시 사용 여부 
	static int result = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = parseInt(st.nextToken());
		M = parseInt(st.nextToken());
		K = parseInt(st.nextToken());
		
		arr = new int[N][M];
		rotateInfo = new int[K][3];	// [][0]=r,  [][1]=c,  [][2]=s
		comb = new int[K];
		visit = new int[K];
		
		// 원본 배열 값 입력
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				arr[i][j] = parseInt(st.nextToken());
			}
		}
		
		// 연산 정보 입력 (r, c, s)
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<3; j++) {
				rotateInfo[i][j] = parseInt(st.nextToken());
			}
		}
		
		// 연산 조합 생성, 배열 돌리기, 최소값 구하기
		makeComb(0);
		
		System.out.println(result);
		
	}
	
	static void makeComb(int depth) {
		// 조합이 완성된 경우
		if(depth == K) {
			// 원본 배열 보존을 위한 깊은 복사
			int[][] temp = new int[N][M];
			for(int i=0; i<N; i++) {
				temp[i] = arr[i].clone();
			}
			// 배열 돌리기
			for(int value : comb) {
				int r = rotateInfo[value][0];
				int c = rotateInfo[value][1];
				int s = rotateInfo[value][2];
				temp = rotate(r, c, s, temp);
			}
			
			// 연산 조합 별 배열값 비교 -> 최소값 구하기
			result = Math.min(result, checkMin(temp));
			return;
		}
		
		// 연산 조합 만들기
		for(int i=0; i<K; i++) {
			if(visit[i] != 1) {
				visit[i] = 1;
				comb[depth] = i;
				makeComb(depth+1);
				visit[i] = 0;
			}
		}
	}

	/** 배열 돌리기 메서드 */
	static int[][] rotate(int r, int c, int s, int[][] temp) {
		int startR = r-s-1;
		int startC = c-s-1;
		int endR = r+s-1;
		int endC = c+s-1;
		
		int turn = Math.min(endR-startR, endC-startC)/2;
		
		for(int t=0; t<turn; t++) {
			// 상단 우측 값 저장
			int tempValue = temp[startR+t][endC-t];
			
			// 위쪽
			for(int i=endC-t; i>startC+t; i--) {
				temp[startR+t][i] = temp[startR+t][i-1];
			}
			
			// 왼쪽
			for(int i=startR+t; i<endR-t; i++) {
				temp[i][startC+t] = temp[i+1][startC+t];
			}
			
			// 아래쪽
			for(int i=startC+t; i<endC-t; i++) {
				temp[endR-t][i] = temp[endR-t][i+1];
			}
			
			// 오른쪽
			for(int i=endR-t; i>startR+t; i--) {
				temp[i][endC-t] = temp[i-1][endC-t];
			}
			
			temp[startR+1+t][endC-t] = tempValue;
		}
		
		return temp;
	}
	
	/** 배열값 구하기 메서드*/
	static int checkMin(int[][] temp) {
		int min = Integer.MAX_VALUE;
		
		for(int i=0; i<N; i++) {
			int sum = 0;
			for(int j=0; j<M; j++) {
				sum +=  temp[i][j];
			}
			min = Math.min(min, sum);
		}
		
		return min;
	}
}
