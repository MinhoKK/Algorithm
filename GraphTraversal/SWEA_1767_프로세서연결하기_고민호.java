package algo_0229;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class SWEA_1767_프로세서연결하기_고민호 {
	/**
	 * @author 고민호
	 * SWEA_1767_프로세서연결하기
	 * 결과 : 통과		메모리 : 22,940KB		실행시간 : 843KB
	 * 
	 * 기존 풀이방법
	 * 1. 재귀를 사용해 코어의 방향에 대한 모든 조합 만들기
	 * 2. 각각의 조합이 완성되면 방향에 맞춰 전선 길이 구하기
	 * -> 모든 조합을 만들어 확인하는 부분에서 시간초과 발생
	 * 
	 * 풀이방법
	 * 1. 조합배열을 통해 조합을 만들지 않고 재귀를 통해 각각 코어에 대해 4방향으로 전선을 그리며 불가능한 경우 종료
	 */

	static class Node{
		int r;
		int c;
		
		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static int T, N;
	static int[][] map;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int result;	// 최소 전선 길이 합
	static int maxConnect;	// 최대 코어 연결 수
	static ArrayList<Node> cores;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		T = parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			N = parseInt(br.readLine());
			
			result = Integer.MAX_VALUE;
			maxConnect = 0;
			cores = new ArrayList<>();
			map = new int[N][N];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = parseInt(st.nextToken());
					//	가장자리에 있지 않은 코어 저장
					if(map[i][j] == 1 && i > 0 && i < N-1 && j > 0 && j < N-1) {
						cores.add(new Node(i, j));
					}
				}
			}
			
			
			makeComb(0, 0, 0);
			sb.append("#").append(t).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
	}

	
	static void makeComb(int depth, int lineLength, int connectCount) {
		// 가장자리에 있지 않은 코어들의 방향이 모두 정해진 경우 
		if(depth == cores.size()) {
			// 현재 방향의 전원 연결 수 > 지금까지의 최대 전원 연결 수
			if(connectCount > maxConnect) {
				maxConnect = connectCount;	// 최대 전원 연결 갱신
				result = lineLength;	// 최소 전선 길이 갱신
			// 현재 방향의 전원 연결 수 = 지금까지의 최대 전원 연결 수
			} else if(connectCount == maxConnect){	
				result = Math.min(result, lineLength);	// 짧은 전선 길이  선택
			}
			return;
		}
		
		Node core = cores.get(depth);	// 현재 코어
		int r = core.r;
		int c = core.c;
		for(int i=0; i<4; i++) {
			int tempr = r;
			int tempc = c;
			int connect = 0;	// 현재 방향의 전원 연결
			int count = 0;	// 현재 방향의 전선 길이
			while(true) {
				tempr += dr[i];
				tempc += dc[i];
				
				// 범위를 벗어나는 경우 = 전원이 연결되는 경우
				if(tempr < 0 || tempc < 0 || tempr >= N || tempc >= N) {
					connect++;	// 전원 연결+1
					break;	// 종료
				}
				
				// 다른 코어 OR 전선에 가로막히는 경우
				if(map[tempr][tempc] == 1) {
					count = 0;	// 전선 길이 0
					break;	// 종료
				}
				
				count++;	// 전선 길이 +1
			}
			
			// 전원 연결된 경우
			if(count > 0) {
				int checkr = core.r;
				int checkc = core.c;
				// 전선(1) 표시
				for(int j=0; j<count; j++) {
					checkr += dr[i];
					checkc += dc[i];
					map[checkr][checkc] = 1;
				}
				makeComb(depth+1, lineLength+count, connectCount+connect);	// 재귀(depth+1 = 다음 코어, 현재 방향으로 그려진 전선 길이 증가, 전원 연결 수 증가)
				checkr = core.r;
				checkc = core.c;
				// 전선 표시 해제
				for(int j=0; j<count; j++) {
					checkr += dr[i];
					checkc += dc[i];
					map[checkr][checkc] = 0;
				}
			// 전선 길이 0 = 전원 연결 X	
			} else {
				makeComb(depth+1, lineLength, connectCount);	// 재귀
			}
			
		}
	}
	
	
}
