package Simulation;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Main_17144_미세먼지안녕_고민호 {
	/**
	 * @author 고민호
	 * 백준_17144_미세먼지안녕
	 * 난이도 G4
	 * 결과 : 통과		메모리 : 39,340KB		시간 : 356ms
	 * 
	 * 풀이방법
	 * 1. 배열돌리기를 사용한 간단한 시뮬레이션 문제
	 */

	static class Node{
		int r;
		int c;
		
		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static int R, C, T;
	static int[][] map;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static ArrayList<Node> machine = new ArrayList<>();
	static int result;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = parseInt(st.nextToken());
		C = parseInt(st.nextToken());
		T = parseInt(st.nextToken());
		
		map = new int[R][C];
		for(int i=0; i<R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<C; j++) {
				map[i][j] = parseInt(st.nextToken());
				if(map[i][j] == -1) {	// 미세먼지 위치 정보 저장
					machine.add(new Node(i, j));	// 0 = 위쪽, 1 = 아래쪽
				}
			}
		}
		
		for(int i=0; i<T; i++) {
			expand();
			up();
			down();
		}
		
		// 남아있는 미세먼지 양 구하기
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(map[i][j] > 0) {
					result += map[i][j];
				}
			}
		}
		System.out.println(result);
	}

	static void expand() {
		int[][] tempMap = copyMap();	// 현재 배열에 영향을 주지않기 위해 임시배열 생성
		
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(map[i][j] > 0) {
					int expandAmount = map[i][j]/5;	// 미세먼지 확산 양
					int expandCount = 0;	// 확산 횟수
					// 상하좌우 탐색
					for(int d=0; d<4; d++) {
						int nr = i + dr[d];
						int nc = j + dc[d];
						if(nr >= 0 && nr < R && nc >= 0 && nc < C && map[nr][nc] >= 0) {	// 범위 내, 공기청정기가 아닌 경우
							tempMap[nr][nc] += expandAmount;	// 탐색 좌표에 확산 양만큼 더해주기
							expandCount++;	// 확산 횟수 1증가
						}
					}
					tempMap[i][j] -= expandAmount*expandCount;	// 현재 좌표의 미세먼지 양 - 확산 횟수 * 확산 양
				}
			}
		}
		map = tempMap;	// 현재 배열에 반영
	}
	
	/** 배열 돌리기 메서드 */
	static void up() {
		int startR = machine.get(0).r;
		int startC = machine.get(0).c;
		
		
		for(int i=startR; i>0; i--) {
			map[i][startC] = map[i-1][startC];
		}
		
		for(int i=startC; i<C-1; i++) {
			map[0][i] = map[0][i+1];
		}
		
		for(int i=0; i<startR; i++) {
			map[i][C-1] = map[i+1][C-1];
		}
		
		for(int i=C-1; i>startC; i--) {
			map[startR][i] = map[startR][i-1];
		}
		
		map[startR][startC] = -1;
		map[startR][startC+1] = 0;
	}
	
	/** 배열 돌리기 메서드 */
	static void down() {
		int startR = machine.get(1).r;
		int startC = machine.get(1).c;
		
		for(int i=startR; i<R-1; i++) {
			map[i][startC] = map[i+1][startC];
		}
		
		for(int i=startC; i<C-1; i++) {
			map[R-1][i] = map[R-1][i+1];
		}

		for(int i=R-1; i>startR; i--) {
			map[i][C-1] = map[i-1][C-1];
		}
		
		for(int i=C-1; i>startC; i--) {
			map[startR][i] = map[startR][i-1];
		}
		
		map[startR][startC] = -1;
		map[startR][startC+1] = 0;
	}
	
	
	static int[][] copyMap(){
		int[][] tempArr = new int[R][C];
		for(int i=0; i<R; i++) {
			tempArr[i] = map[i].clone();
		}
		return tempArr;
	}
}
