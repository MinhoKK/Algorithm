package GraphTraversal;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Main_17471_게리맨더링_고민호 {
	/**
	 * @author 고민호
	 * 백준_17471_게리맨더링
	 * 난이도 G4
	 * 결과 : 통과		메모리 : 14,596KB		시간 : 136ms
	 * 소요시간 : 63분
	 * 
	 * 풀이방법
	 * 1. N개의 구역이 주어지면 1 ~ N/2개 구역의 조합을 만들자! 
	 * -> 예를 들어 6개의 구역이 주어지면 1개의 조합을 구하는 것이 곧 5개 구역의 조합을 구하는 것과 동일하기에 N/2개의 구역 조합까지만.
	 * 2. 조합이 완성되면 조합을 만들며 comb 배열에 포함되지 않은 구역들은 visit배열에 방문 표시가 안되어 있는것을 활용!
	 * 3. comb에 포함된 구역, visit 처리되지 않은 구역 두개의 선거구가 나뉘어 지면 BFS를 통해 모두 연결되어 있는지 확인하기!
	 * 4. 연결되지 않았다면 return, 연결되었다면 인원수의 차이 구하기
	 */

	static int N;
	static int[] peopleNum;	// 구역 별 인구 수
	static int[] comb;	// 구역 조합
	static int[] visit;	// 구역 방문 표시
	static ArrayList<ArrayList<Integer>> list = new ArrayList<>();	// 구역 연결 리스트
	static int result = Integer.MAX_VALUE;	// 결과값
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = parseInt(br.readLine());
		peopleNum = new int[N];
		visit = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			peopleNum[i] = parseInt(st.nextToken());
		}
		
		for(int i=0; i<N; i++) {
			list.add(new ArrayList<>());
		}
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int near = parseInt(st.nextToken());
			for(int j=0; j<near; j++) {
				list.get(i).add(parseInt(st.nextToken()) - 1);
			}
		}
		
		// 1 ~ N/2개까지의  조합 만들기
		for(int i=1; i<=N/2; i++) {
			comb = new int[i];	// comb배열 크기는 조합할 구역의 개수만큼
			dfs(0, 0, i);
		}
		
		// result가 바뀌지 않고 초기값인 경우 -> 모든 조합으로도 두 선거구를 나누지 못해 갱신하지 못했다는 의미
		if(result == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(result);
		}
		
	}
	
	static void dfs(int start, int depth, int count) {
		if(depth == count) {
			
			int v = 0;	//  comb에 포함되지 않은 구역
			for(int i=0; i<visit.length; i++) {
				if(visit[i] == 0) {	// 방문처리되지 않은 구역 하나 뽑기
					v = i;
					break;
				}
			}
			
			if(!checkLink1(comb[0])) return;	// 첫번째 선거구에 포함된 구역 중 하나를 파라미터로 넘겨줌
			if(!checkLink2(v)) return;	// 두번째 선거구에 포함된 구역 중 하나를 파라미터로 넘겨줌
			
			// 위 조건에서 return 되지않았다는 것은 각 선거구에 포함된 구역이 모두 연결됐다는 의미
			count();	// 두 선거구 총 인원수의 차
			
			return;
		}
		
		
		for(int i=start; i<N; i++) {
			visit[i] = 1;
			comb[depth] = i;
			dfs(i+1, depth+1, count);
			visit[i] = 0;
		}
	}

	/** comb 배열에 포함된 구역 연결 확인 메서드 (BFS) */
	static boolean checkLink1(int start) {
		Queue<Integer> queue = new LinkedList<>();
		int[] check = new int[N];	// 연결 확인 배열
		
		queue.add(start);
		check[start] = 1;
		
		while(!queue.isEmpty()) {
			int out = queue.poll();
			for(int linkNode : list.get(out)) {	// 구역과 연결된 구역 확인
				// 확인한 구역이 방문한적 없고, 첫번째 선거구인 경우
				if(check[linkNode] == 0 && visit[linkNode] == 1) {
					check[linkNode] = 1;	// 연결 표시
					queue.add(linkNode);
				}
			}
		}
		
		for(int i=0; i<comb.length; i++) {
			// 조합 배열(첫번째 선거구)에 들어간 구역이 연결 표시가 안된경우
			if(check[comb[i]] != 1) {
				return false; // 선거구에 포함된 구역이 전부 연결안됌 -> 종료
			}
		}
		return true;
	}
	
	/** comb 배열에 포함되지 않은 구역 연결 확인 메서드 (BFS)
	 *  -> visit 처리 되지 않은 구역 연결 확인 메서드
	 */
	static boolean checkLink2(int start) {
		Queue<Integer> queue = new LinkedList<>();
		int[] check = new int[N];	// 연결 확인 배열
		
		queue.add(start);
		check[start] = 1;
		
		while(!queue.isEmpty()) {
			int out = queue.poll();
			for(int linkNode : list.get(out)) {	// 구역과 연결된 구역 확인
				// 확인한 구역이 방문한적 없고, 두번째 선거구인 경우
				if(check[linkNode] == 0 && visit[linkNode] == 0) {
					check[linkNode] = 1;	// 연결 표시
					queue.add(linkNode);
				}
			}
		}
		
		for(int i=0; i<visit.length; i++) {
			// 방문 표시 안된 구역(두번째 선거구)
			if(visit[i] == 0) {
				// 연결 표시가 안된 경우
				if(check[i] != 1) {
					return false;	//선거구에 포함된 구역이 전부 연결안됌 -> 종료
				}
			}
		}
		return true;
	}
	
	static void count() {
		int a = 0;
		int b = 0;
		// 첫번째 선거구에 포함된 구역 인원수 합
		for(int c : comb) {
			a += peopleNum[c];
		}
		// 두번째 선거구에 포함된 구역 인원수 합
		for(int i=0; i<visit.length; i++) {
			if(visit[i] == 0) {
				b += peopleNum[i];
			}
		}
		
		result = Math.min(result, Math.abs(a-b));	// 최소값 갱신
	}
}
