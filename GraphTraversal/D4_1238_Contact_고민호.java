package algo_0222;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class D4_1238_Contact_고민호 {
	/**
	 * @author 고민호
	 * SWEA_1238_Contact
	 * 난이도 D4
	 * 결과 : 통과		메모리 : 19,904KB		시간 : 122ms
	 *
	 * 풀이방법
	 * 1. 입력이 주어지면 단방향 연결해주기
	 * 2. BFS를 통해 시작 노드에서 연결된 노드를 찾아 탐색하기
	 * 3. 시작 노드의 time=0 이며 시작노드와 연결되고, 방문한적이 없는 경우 방문한다. 이때 연결된 노드의 time은 이전 노드 time+1
	 * 4. 크기 100의 각 노드의 도착 시간을 저장하는 배열을 만들어 BFS를 통해 노드에 방문하면 도착 시간을 해당 배열에 기록
	 * 5. 4번에서 만든 배열에 BFS가 종료될때의 시간이 적힌 배열들을 뽑아 가장 큰 수를 찾는다.   
	 */
	static class Node{
		int num;	// 노드 번호
		int time;	// 방문 시간
		
		public Node(int num, int time) {
			this.num = num;
			this.time = time;
		}
	}

	static int N, S;	// 데이터 길이, 시작점
	static ArrayList<ArrayList<Integer>> list;	// 노드
	static int[] time;	// 방문 시간 배열
	static int max;	// 방문이 끝나는 시간
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for(int t=1; t<=10; t++) {
			st = new StringTokenizer(br.readLine());
			N = parseInt(st.nextToken());
			S = parseInt(st.nextToken());
			
			list = new ArrayList<>();
			for(int i=0; i<=100; i++) {	// 연락 인원은 최대 100명 고정 -> 노드 100개 고정
				list.add(new ArrayList<>());
			}
			
			st = new StringTokenizer(br.readLine());
			// N개의 데이터가 쌍으로 묶이기에 N/2만큼 반복하여 데이터 받기
			for(int i=0; i<N/2; i++) {
				int from = parseInt(st.nextToken());
				int to = parseInt(st.nextToken());
				list.get(from).add(to);
			}
			
			time = new int[101];	// 연락 인원은 최대 100명 고정 -> 노드 100개 고정
			max = 0;
			bfs(S);
			
			ArrayList<Integer> temp = new ArrayList<>();
			
			// 방문 시간 배열에서 최대 시간을 가진 index 확인하기
			// index가 곧 노드 번호
			for(int i=0; i<=100; i++) {
				if(time[i] == max) {
					temp.add(i);
				}
			}
			// 최대 시간을 가진 노드들 오름차순 및 가장 뒤에 있는(번호가 가장 큰)번호 출력
			Collections.sort(temp);
			sb.append("#").append(t).append(" ").append(temp.get(temp.size()-1)).append("\n");
		}
		System.out.println(sb);
	}

	
	static void bfs(int start) {
		Queue<Node> queue = new LinkedList<>();
		queue.add(new Node(start, 0));	// Queue에 Node 추가, 이때  시작 Node의 방문 시간은 0.
		time[start] = 0;	// 방문 시간 표시
		
		while(!queue.isEmpty()) {
			Node out = queue.poll();
			// 노드와 연결된 모든 노드 탐색
			for(int linkNode : list.get(out.num)) {
				if(time[linkNode] == 0) {	// 방문 시간 0 의미 = 방문한 적이 없다 -> 방문한적 없으면 탐색하기
					time[linkNode] = out.time+1;	// 탐색한 노드의 방문 시간은 영향을 준 (이전)노드의 시간 +1
					queue.add(new Node(linkNode, out.time+1));	// queue에 추가하기
					max = out.time+1;	// 가장 늦은 시간 갱신
				}
			}
		}
	}
}
