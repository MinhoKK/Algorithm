package DivideAndConquer;

import java.util.*;
import java.io.*;
import static java.lang.Integer.parseInt;

public class Main_1074_Z_고민호 {
	/**
	 * @author 고민호
	 * 백준_1074_Z
	 * 난이도 S1
	 * 결과 : 통과		메모리 : 14,288KB		시간 : 132ms
	 * 
	 * 접근방식
	 * 1. 사각형의 size가 2가 될때까지 4분면을 나누자!
	 * 2. 종료조건(size=2)를 만족하면 2*2크기의 정사각형을 이중for문으로 탐색하며 num++을 증가시키자
	 * 3. R, C 좌표에 도달하면 현재 num을 출력하자
	 * 
	 * 고려점!!!
	 * 문제 그림과 같이 실제 배열을 생성하여 배열에 값을 넣어 문제 해결 -> 메모리 초과 발생
	 * 아래 주석과 같이 재귀를 통해 size가 2인 모든 사각형을 탐색하여 num을 증가시켜 문제 해결 -> 시간 초과 발생
	 * 
	 * 위의 문제점으로 4개의 사각형으로 나눌때마다 문제에서 주어진 R, C가 포함된 사각형에만 재귀 적용, 나머지 사각형은
	 * size*size 한만큼 count를 해주면 size가 2인 모든 사각형을 탐색 할 필요 X
	 * 
	 */
	
	static int N, R, C;
	static int num;
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = parseInt(st.nextToken());
		R = parseInt(st.nextToken());
		C = parseInt(st.nextToken());
		
		int size= (int) Math.pow(2, N);
		
		Z(size, 0, 0);
	}

	static void Z(int size, int r, int c) {
		// 종료 조건 
		// 재귀함수 실행 조건에 의해 해당 2*2크기의 사각형에는 (R,C)가 존재
		if(size == 2) {
			for(int i=r; i<r+2; i++) {
				for(int j=c; j<c+2; j++) {
					num++;
					if(i==R && j==C) {
						System.out.println(num-1);
						return;
					}
				}
			}
			return;
		}
		
		// 탐색범위 = 사각형 전체길이/2
		int half = size/2;
		
		for(int i=r; i<r+size; i=i+half) {
			for(int j=c; j<c+size; j=j+half) {
				// R, C가 존재하는 사각형이면 재귀를 통해 더 깊이 파고 들기
				if(R >= i && R < i+half && C >= j && C < j+half) {
					Z(half, i, j);
				}
				// R, C가 없는 사각형이면 탐색할 필요 X, size*size만큼 num만 증가시켜주기
				num += half*half;
			}
		}
		
		// 이와같이 모든 방향으로 재귀를 진행하면 시간초과 발생!!!
//		Z(half, r, c);
//		Z(half, r, c+half);
//		Z(half, r+half, c);
//		Z(half, r+half, c+half);
		
	}
}
