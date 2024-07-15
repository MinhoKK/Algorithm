package GraphTraversal;

import java.io.*;
import java.util.*;

import static java.lang.Integer.parseInt;

public class BJ_1520_내리막길 {
    /**
     * @author 고민호
     * 백준 1520 내리막기
     * 난이도 G3
     * 결과 : 통과      메모리 : 37,112KB      시간 : 396ms
     *
     * 풀이방법
     * 단순 BFS로 경우의 수를 찾으면 메모리 초과 발생..
     * 1. 높이를 내림차순으로 정렬 -> 높이가 낮은 좌표는 이미 queue에 추가되어 있는 상태에서 높은 높이를 통한 경우의 수를 구해서 낮은 좌표까지의 경우의 수와 더하기 위함
     * 2. visit 배열의 값은 해당 좌표까지의 경우의 수
     */

    static class Node implements Comparable<Node>{
        int r;
        int c;
        int value;

        public Node(int r, int c, int value) {
            this.r = r;
            this.c = c;
            this.value = value;
        }

        // value 내림차순
        @Override
        public int compareTo(Node o){
            return o.value - this.value;
        }

    }

    static int M, N;    // 세로, 가로
    static int[][] map;
    static int[][] visit;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = parseInt(st.nextToken());
        N = parseInt(st.nextToken());

        map = new int[M][N];
        visit = new int[M][N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = parseInt(st.nextToken());
            }
        }

        bfs();
        System.out.println(visit[M-1][N-1]);
    }


    static void bfs() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(0, 0, map[0][0]));
        visit[0][0] = 1;

        while(!pq.isEmpty()){
            Node out = pq.poll();
            for(int d=0; d<4; d++){
                int nr = out.r + dr[d];
                int nc = out.c + dc[d];
                if(nr >= 0 && nr < M && nc >= 0 && nc < N && map[out.r][out.c] > map[nr][nc]){  // 다음 좌표의 값이 현재 좌표의 값보다 작은 경우
                    // 처음 방문한 경우
                    if(visit[nr][nc] == 0){
                        visit[nr][nc] = visit[out.r][out.c];    // visit[x][y] 는 x,y까지 올수있는 경우의 수로 처음 방문이라면 현재 좌표까지의 방문의 수와 동일
                        pq.add(new Node(nr, nc, map[nr][nc]));  // queue 추가
                    // 방문한적 있는 경우    
                    } else if(visit[nr][nc] > 0){
                        visit[nr][nc] += visit[out.r][out.c];   // 다음 방문할 곳까지 가는 경우의 수 = 현재 지점까지의 경우의 수 + 다음 좌표까지의 경우의 수
                                                                // 그리고 visit > 0 이라면 이미 queue에 들어있기에 add하지 않음
                    }

                }
            }
        }

    }
}
