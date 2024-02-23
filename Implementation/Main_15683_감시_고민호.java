package Implementation;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Main_15683_감시_고민호 {
	/**
	 * @author 고민호
	 * 백준_15683_감시
	 * 난이도 G4
	 * 결과 : 통과		메모리 : 74,788KB		시간 : 476ms
	 * 
	 * 풀이방법
	 * 1. 1~5가 입력되면 CCTV의 좌표와 번호를 저장하는 리스트에 담기
	 * 2. 각 CCTV가 바라보는 방향 조합 만들기
	 * 3. CCTV의 바라보는 방향에 맞춰 열심히 구현하기
	 * 3-1. 1번  CCTV는 4가지방향, 2번 CCTV는 2가지방향, 3번 CCTV는 4가지방향, 4번 CCTV는 4가지방향, 5번 CCTV는 1가지방향을 가질 수 있음
	 * 3-2. up, down, left, right 방향으로 '0'을 '#'으로 바꿔주는 메서드를 구현
	 * 3-3. CCTV마다 방향에 따라 up, down, left, right 메세드를 적용
	 * 
	 * 코드 최적화해서 다시 풀어보기!!
	 * 무작정 구현했지만 다시 4방탐색 상하좌우로..
	 */
	
	static class Node{
		int r;
		int c;
		int num;	// CCTV 번호
		
		public Node(int r, int c, int num) {
			this.r = r;
			this.c = c;
			this.num = num;
		}
	}

	static int N, M;
	static int[][] map;
	static int[][] tempMap;
	static int[] comb;
	static ArrayList<Node> cctvs = new ArrayList<>();
	static int result = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = parseInt(st.nextToken());
		M = parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = parseInt(st.nextToken());
				if(map[i][j] > 0 && map[i][j] < 6) {
					cctvs.add(new Node(i, j, map[i][j]));	// CCTV 정보 리스트에 저장하기
				}
			}
		}
		
		comb = new int[cctvs.size()];	// CCTV 방향 조합 배열 크기는 CCTV 개수만큼
		makeComb(0);
		System.out.println(result);
	}
	
	/** CCTV 방향 조합 생성 메서드 */
	static void makeComb(int depth) {
		if(depth == cctvs.size()) {
			tempMap = copyArr();	// 조합이 완성되면 CCTV들의 방향에 따른 사각지대 수를 구하기 위해 임시 배열 생성
			
			for(int i=0; i<comb.length; i++) {
				check(cctvs.get(i), comb[i]);	// cctv의 좌표값을 넘겨주기 위해 Node class 와 방향을 파라미터로 넘겨준다.
			}
			
			// 해당 방향 조합으로 남은 사각지대 수
			int count = countZero();
			result = Math.min(result, count);	// 이전 최소값과 현재 조합으로 남은 사각지대 수를 비교하여 최소값 갱신
			
			return;
		}
		
		if(cctvs.get(depth).num == 1) {	// cctv의 번호가 1인경우
			for(int i=0; i<4; i++) {
				comb[depth] = i;	// 4가지 방향 가능
				makeComb(depth+1);
			}
		} else if(cctvs.get(depth).num == 2) {	// cctv의 번호가 2인경우
			for(int i=0; i<2; i++) {
				comb[depth] = i;	// 2가지 방향 가능
				makeComb(depth+1);
			}
		} else if(cctvs.get(depth).num == 3) {	// cctv의 번호가 3인경우
			for(int i=0; i<4; i++) {
				comb[depth] = i;	// 4가지 방향 가능
				makeComb(depth+1);
			}
		} else if(cctvs.get(depth).num == 4) {	// cctv의 번호가 4인경우
			for(int i=0; i<4; i++) {
				comb[depth] = i;	// 4가지 방향 가능
				makeComb(depth+1);
			}
		} else if(cctvs.get(depth).num == 5) {	// cctv의 번호가 5인경우
			comb[depth] = 0;	// 1가지 방향 가능
			makeComb(depth+1);
		}
	}
	
	/** CCTV 방향에 따라 빈칸(0)을 '#'으로 변경하는 메서드 */
	static void check(Node cctv, int dir) {
		int num = cctv.num;
		
		// 1번 cctv인 경우
		if(num == 1) {
			if(dir == 0) {
				up(cctv.r, cctv.c);
			} else if(dir == 1) {
				left(cctv.r, cctv.c);
			} else if(dir == 2) {
				down(cctv.r, cctv.c);
			} else if(dir == 3) {
				right(cctv.r, cctv.c);
			}
			// 2번 cctv인 경우
		} else if(num == 2) {
			if(dir == 0) {
				up(cctv.r, cctv.c);
				down(cctv.r, cctv.c);
			} else if(dir == 1) {
				left(cctv.r, cctv.c);
				right(cctv.r, cctv.c);
			}
			// 3번 cctv인 경우
		} else if(num == 3) {
			if(dir == 0) {
				up(cctv.r, cctv.c);
				left(cctv.r, cctv.c);
			} else if(dir == 1) {
				left(cctv.r, cctv.c);
				down(cctv.r, cctv.c);
			} else if(dir == 2) {
				down(cctv.r, cctv.c);
				right(cctv.r, cctv.c);
			} else if(dir == 3) {
				right(cctv.r, cctv.c);
				up(cctv.r, cctv.c);
			}
			// 4번 cctv인 경우
		} else if(num == 4) {
			if(dir == 0) {
				up(cctv.r, cctv.c);
				left(cctv.r, cctv.c);
				right(cctv.r, cctv.c);
			} else if(dir == 1) {
				up(cctv.r, cctv.c);
				left(cctv.r, cctv.c);
				down(cctv.r, cctv.c);
			} else if(dir == 2) {
				left(cctv.r, cctv.c);
				down(cctv.r, cctv.c);
				right(cctv.r, cctv.c);
			} else if(dir == 3) {
				down(cctv.r, cctv.c);
				right(cctv.r, cctv.c);
				up(cctv.r, cctv.c);
			}
			// 5번 cctv인 경우
		} else if(num == 5) {
			up(cctv.r, cctv.c);
			left(cctv.r, cctv.c);
			down(cctv.r, cctv.c);
			right(cctv.r, cctv.c);
		}
	}
	
	/** CCTV 기준 위를 감시하는 메서드 */
	static void up(int start, int c) {
		int count = 0;
		for(int i=start-1; i>=0; i--) {
			if(map[i][c] == 6) break;	// 벽(6)을 만나는 경우 종료
			if(map[i][c] == 0) tempMap[i][c] = 9;	// # 대신 9로 바꿔주기
		}
	}
	
	/** CCTV 기준 아래를 감시하는 메서드 */
	static void down(int start, int c) {
		int count = 0;
		for(int i=start+1; i<N; i++) {
			if(map[i][c] == 6) break;	// 벽(6)을 만나는 경우 종료
			if(map[i][c] == 0) tempMap[i][c] = 9;	// # 대신 9로 바꿔주기
		}
	}
	
	/** CCTV 기준 왼쪽을 감시하는 메서드 */
	static void left(int r, int start) {
		int count = 0;
		for(int i=start-1; i>=0; i--) {
			if(map[r][i] == 6) break;	// 벽(6)을 만나는 경우 종료
			if(map[r][i] == 0) tempMap[r][i] = 9;	// # 대신 9로 바꿔주기
		}
	}
	
	/** CCTV 기준 오른쪽을 감시하는 메서드 */
	static void right(int r, int start) {
		int count = 0;
		for(int i=start+1; i<M; i++) {
			if(map[r][i] == 6) break;	// 벽(6)을 만나는 경우 종료
			if(map[r][i] == 0) tempMap[r][i] = 9;	// # 대신 9로 바꿔주기
		}
	}
	
	/** 2차원 배열 깊은 복사 메서드 */
	static int[][] copyArr(){
		int[][] tempArr = new int[N][M];
		for(int i=0; i<N; i++) {
			tempArr[i] = map[i].clone();
		}
		return tempArr;
	}
	
	/** 빈칸 개수를 반환하는 메서드 */
	static int countZero() {
		int count = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(tempMap[i][j] == 0) count++;
			}
		}
		return count;
	}
}
