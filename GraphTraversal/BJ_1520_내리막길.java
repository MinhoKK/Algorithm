package GraphTraversal;

import java.io.*;
import java.util.*;

import static java.lang.Integer.parseInt;

public class BJ_1520_내리막길 {
    /**
     *
     * 풀이방법
     * 단순 BFS로 경우의 수를 찾으면 메모리 초과 발생..
     * visit한 곳을 다시 방문하면 count 증가하고 그 분기는 삭제로 해결해보기
     */

    static class Node {
        int r;
        int c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int M, N;    // 세로, 가로
    static int[][] map;
    static int[][] visit;
    static int result;
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


        System.out.println(result);

    }


    static void bfs() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(0, 0));

        while (!queue.isEmpty()) {
            Node out = queue.poll();
            if (out.r == M - 1 && out.c == N - 1) {
                result++;
            }

            for (int d = 0; d < 4; d++) {
                int nr = out.r + dr[d];
                int nc = out.c + dc[d];
                if (nr >= 0 && nr < M && nc >= 0 && nc < N && map[out.r][out.c] > map[nr][nc]) {
                    if(visit[nr][nc] == 0){
                        visit[nr][nc] = 1;
                        queue.add(new Node(nr, nc));
                    } else {
                        result++;
                    }



                }
            }
        }
    }
}
