package Implementation;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Main_21610_마법사상어와비바라기_고민호 {
	/**
	 * @author 고민호
	 * 백준_21610_마법사상어와비바라기
	 * 난이도 G5
	 * 결과 : 통과		메모리 : 17,292KB		시간 : 212ms
	 * 
	 * 풀이방법
	 * 1. 구현문제로 문제로 주어진 1번기능(구름이동) 2번,3번,4번,5번 기능 메서드로 구현하기
	 * 2. 구름 이동시 배열의 처음과 끝은 이어진 상태 -> 배열을 벗어났다면 이동 크기에서 배열의 크기만큼 더하거나 빼준다
	 *    (해당 규칙을 생각해내는데 시간이 많이들었습니다)
	 * 3. 기능 순서대로 3번에서 구름을 바로 제거하지 않고 4번, 5번을 위해 유지 및 새로운 구름 생성 시 기존 구름 삭제하기
	 */

	static int N, M;	// 배열 크기, 이동 명령 수
	static int[][] map;	// 비의 양이 저장된 배열
	static int[][] cloud;	// 구름 위치
	static int[] dr = {0, -1, -1, -1, 0, 1, 1, 1};	// 8방탐색
	static int[] dc = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int result;
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = parseInt(st.nextToken());
		M = parseInt(st.nextToken());
		
		map = new int[N][N];
		cloud = new int[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = parseInt(st.nextToken());
				// 구름 위치 초기값 설정
				if((i >= N-2 && i < N) && (j >= 0 && j < 2)) {
					cloud[i][j] = 1;
				}
			}
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int d = parseInt(st.nextToken()) - 1;	// 이동 방향
			int s = parseInt(st.nextToken());	// 이동 크기
			
			// 1. 모든 구름 이동
			moveCloud(d, s);
			
			// 2. 구름이 있는 칸 물의 양 +1 
			rain();
			
			// 4. 물복사버그 마법
			copyWater();
			
			// 3. 구름 제거
			// 5. 구름 생성
			newCloud();
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				result += map[i][j];
			}
		}
		System.out.println(result);
	}

	
	/**
	 * 1. 구름 이동 메서드
	 */
	static void moveCloud(int d, int s) {
		int moveR = 0;	// 구름 r 이동 거리
		int moveC = 0;	// 구름 c 이동 거리
		// s번 이동
		for(int j=0; j<s; j++) {
			moveR += dr[d];
			moveC += dc[d];
		}
		
		// 임시 배열 생성
		// 이동한 구름을 인식하지 않기 위해
		int[][] tempCloud = new int[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(cloud[i][j] == 1) {
					// 이동할 위치 구하기
					int tempR = i + moveR%N;	// 이동크기가 N보다 클 경우 N으로 나눈 나머지만큼 이동하는것과 동일
					int tempC = j + moveC%N;
					if(tempR<0) tempR += N;
					if(tempR>=N) tempR -= N;
					if(tempC<0) tempC += N;
					if(tempC>=N) tempC -= N;
					tempCloud[tempR][tempC] = 1;	// 구름 이동 좌표 저장
				}
			}
		}
		// 이동한 구름 적용
		cloud = tempCloud.clone();
		
	}
	
	/**
	 * 2. 구름칸 물의 양 증가 메서드
	 */
	static void rain() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(cloud[i][j] == 1) {
					map[i][j]++;
				}
			}
		}
		
	}
	
	/**
	 * 4. 물복사버그 메서드
	 */
	static void copyWater() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(cloud[i][j] == 1) {
					int waterCount = 0;
					for(int d=0; d<8; d++) {
						// 대각선 방향 (홀수 index)
						if(d%2 == 1) {
							int nr = i+dr[d];
							int nc = j+dc[d];
							// 배열 범위 내
							if(nr >= 0 && nr < N && nc >= 0 && nc < N) {
								if(map[nr][nc] > 0) waterCount++;
							}
						}
					}
					// 대각선에 존재하는 물의 공간만큼 물 증가
					map[i][j] += waterCount;
				}
			}
		}		
	}
	
	/**
	 * 3. 기존 구름 제거 메서드
	 * 5. 새로운 구름 생성 메서드
	 */
	static void newCloud() { 
		// 임시 배열 생성
		// 새로 생긴 구름을 인식하지 않기 위해
		int[][] tempCloud = new int[N][N];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				// 물의 양 2 이상 칸
				if(map[i][j] >= 2) {
					// 기존에 구름인 있던 경우
					if(cloud[i][j] == 1) {
						continue;	// 구름 생성 X
					}
					// 새로운 구름 생성 및 물의 양 감소
					tempCloud[i][j] = 1;
					map[i][j] -= 2;
				}
			}
		}
		// 새로운 구름 적용
		cloud = tempCloud.clone();
	}
}
