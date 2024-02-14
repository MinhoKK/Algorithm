package DivideAndConquer;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Main_1992_쿼드트리_고민호 {
	/**
	 * @author 고민호
	 * 백준_1992_쿼드트리
	 * 난이도 S1
	 * 결과 : 통과		메모리 : 14,344KB		시간 : 124ms;
	 * 
	 * 풀이방법
	 * 1. Z문제를 풀고 해당 문제를 풀어 접근방법이 비교적 쉬웠습니다.
	 * 2. 출력시 ()가 생기는 지점은 사각형이 4개로 나뉘어지는 곳!!
	 * 3. 사각형이 모두 동일한 값이 아니면 4개로 나누기 -> 재귀 반복
	 */

	static int N;
	static int[][] map;
	static StringBuilder sb = new StringBuilder();
	
	
	public static void main(String[] args) throws IOException{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer st;
			
			N = parseInt(br.readLine());
			map = new int[N][N];
			
			for(int i=0; i<N; i++) {
				String temp = br.readLine();
				for(int j=0; j<N; j++) {
					map[i][j] = temp.charAt(j) - '0';
				}
			}
			
			func(0, 0, N);
			
			System.out.println(sb);
	}

	static void func(int startR, int startC, int size) {
		// 탐색할 범위 = 사각형 길이/2
		int half = size/2;
		
		if(!checkSame(startR, startC, size)) {
			// ()가 추가되는 시점은 사각형 내부값이 달라 4개로 나뉠때!!
			sb.append("(");
			for(int i=startR; i<startR+size; i=i+half) {
				for(int j=startC; j<startC+size; j=j+half) {
					func(i, j, half);	// for문을 돌며 왼쪽위, 오른쪽위, 왼쪽아래, 오른쪽아래로 func() 재귀
				}
			}
			sb.append(")");
		} else {
			// 내부 값이 모두 동일할 경우 시작값(사각형 내부 왼쪽위값) 출력
			sb.append(map[startR][startC]);
		}
	}
	
	/** 사각형 내부 모두 동일 값 확인 메서드
	 *  동일하면      return true
	 *  동일하지 않다면 return false
	 */
	static boolean checkSame(int startR, int startC, int size) {
		int first = map[startR][startC];
		
		for(int i=startR; i<startR+size; i++) {
			for(int j=startC; j<startC+size; j++) {
				if(map[i][j] != first) {
					return false;
				}
			}
		}
		
		return true;
	}
	
}
