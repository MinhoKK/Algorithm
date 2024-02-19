package Implementation;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class D3_1873_상호의배틀필드_고민호 {
	/**
	 * @author 고민호
	 * SWEA_1873_상호의배틀필드
	 * 난이도 D3
	 * 결과 : 통과		메모리 : 18,608KB		시간 : 106ms
	 * 
	 * 풀이방법
	 * 1. 게임 맵을 입력받을때 탱크는 1회 입력되며 이때 탱크의 좌표와 방향을 변수로 입력받기
	 * 2. 이후 U,D,L,R 명령이 주어지면 우선적으로 방향을 바꾸고, 나아갈 수 있으면 나아가기
	 * 3. S가 주어지면 현재 바라보는 방향을 확인하고 앞에 있는것이 벽돌인지 강철인지 확인하기
	 */

	static int T, H, W, N;	// 테스트케이스, 높이, 너비, 입력 수
	static char[][] map;
	static int tankR, tankC, tankDir;	// 탱크 좌표, 탱크가 바라보는 방향
	static int[] dr = {-1, 1, 0, 0};	// 위, 아래, 왼쪽, 오른쪽
	static int[] dc = {0, 0, -1, 1};
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		T = parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			H = parseInt(st.nextToken());
			W = parseInt(st.nextToken());
			
			map = new char[H][W];
			for(int i=0; i<H; i++) {
				String temp = br.readLine();
				for(int j=0; j<W; j++) {
					map[i][j] = temp.charAt(j);
					// 탱크가 위를 바라보는 경우
					if(map[i][j] == '^') {
						// 좌표 입력받기
						tankR = i;	
						tankC = j;
						tankDir = 0;	// 탱크 방향 설정
					// 탱크가 아래를 바라보는 경우
					} else if(map[i][j] == 'v') {
						// 좌표 입력받기
						tankR = i;
						tankC = j;
						tankDir = 1;	// 탱크 방향 설정
					// 탱크가 왼쪽을 바라보는 경우
					} else if(map[i][j] == '<') {
						// 좌표 입력받기
						tankR = i;
						tankC = j;
						tankDir = 2;	// 탱크 방향 설정
					// 탱크가 오른쪽를 바라보는 경우
					} else if(map[i][j] == '>') {
						// 좌표 입력받기
						tankR = i;
						tankC = j;
						tankDir = 3;	// 탱크 방향 설정
					}
				}
			}
			
			N = parseInt(br.readLine());
			
			String temp = br.readLine();
			for(int i=0; i<N; i++) {
				char order = temp.charAt(i);
				
				// 입력되는 명령에 따라 함수 수행
				switch(order) {
					case 'U':
						doU();
						break;
					case 'D':
						doD();
						break;
					case 'L':
						doL();
						break;
					case 'R':
						doR();
						break;
					case 'S':
						doS();
						break;
				}
				
			}
			
			sb.append("#").append(t).append(" ");
			
			for(int i=0; i<H; i++) {
				for(int j=0; j<W; j++) {
					sb.append(map[i][j]);
				}
				sb.append("\n");
			}
		}
		System.out.println(sb);
	}
	
	static void doU() {
		tankDir = 0;	// 탱크 방향 위로 설정
		map[tankR][tankC] = '^';	// 지도 에서 탱크가 바라보는 방향 위로 수정
		int nr = tankR + dr[tankDir];
		int nc = tankC + dc[tankDir];
		// 탱크가 나아가는 곳이 지도 안이면서 평지인 경우
		if(nr >= 0 && nr < H && nc >= 0 && nc < W && map[nr][nc] == '.') {
			map[tankR][tankC] = '.';	// 현재 탱크 위치 평지로 바꾸기
			tankR = nr;
			tankC = nc;
			map[tankR][tankC] = '^';	// 나아가는 곳 탱크로 바꾸기
			
		}
	}
	
	static void doD() {
		tankDir = 1;
		map[tankR][tankC] = 'v';
		int nr = tankR + dr[tankDir];
		int nc = tankC + dc[tankDir];
		if(nr >= 0 && nr < H && nc >= 0 && nc < W && map[nr][nc] == '.') {
			map[tankR][tankC] = '.';
			tankR = nr;
			tankC = nc;
			map[tankR][tankC] = 'v';
		}
	}
	
	static void doL() {
		tankDir = 2;
		map[tankR][tankC] = '<';
		int nr = tankR + dr[tankDir];
		int nc = tankC + dc[tankDir];
		if(nr >= 0 && nr < H && nc >= 0 && nc < W && map[nr][nc] == '.') {
			map[tankR][tankC] = '.';
			tankR = nr;
			tankC = nc;
			map[tankR][tankC] = '<';
		}
	}
	
	static void doR() {
		tankDir = 3;
		map[tankR][tankC] = '>';
		int nr = tankR + dr[tankDir];
		int nc = tankC + dc[tankDir];
		if(nr >= 0 && nr < H && nc >= 0 && nc < W && map[nr][nc] == '.') {
			map[tankR][tankC] = '.';
			tankR = nr;
			tankC = nc;
			map[tankR][tankC] = '>';
		}
	}
	
	static void doS() {
		if(tankDir == 0) {	// 상
			for(int i=tankR-1; i>=0; i--) {
				if(map[i][tankC] == '*') {	// 벽돌인 경우
					map[i][tankC] = '.';	// 평지로 바뀜
					return;
				} else if(map[i][tankC] == '#') {	// 강철인 경우 바로 종료
					return;
				}
			}
		} else if (tankDir == 1) {	// 하
			for(int i=tankR+1; i<H; i++) {
				if(map[i][tankC] == '*') {
					map[i][tankC] = '.';	// 평지로 바뀜
					return;
				} else if(map[i][tankC] == '#') {
					return;
				}
			}
		} else if (tankDir == 2) {	// 좌
			for(int i=tankC-1; i>=0; i--) {
				if(map[tankR][i] == '*') {
					map[tankR][i] = '.';	// 평지로 바뀜
					return;
				} else if(map[tankR][i] == '#') {
					return;
				}
			}
		} else if (tankDir == 3) {	// 우
			for(int i=tankC+1; i<W; i++) {
				if(map[tankR][i] == '*') {
					map[tankR][i] = '.';	// 평지로 바뀜
					return;
				} else if(map[tankR][i] == '#') {
					return;
				}
			}
		}
	}

}
