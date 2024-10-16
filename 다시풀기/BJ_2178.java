package 다시풀기;

import java.util.*;
import java.io.*;
import static java.lang.Integer.parseInt;

public class BJ_2178 {

    static class Node {
        int r;
        int c;
        int count;

        public Node(int r, int c, int count) {
            this.r = r;
            this.c = c;
            this.count = count;
        }
    }

    static int N, M;
    static int[][] map;
    static int[][] visit;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());

        map = new int[N][M];
        visit = new int[N][M];

        for(int i=0; i<N; i++){
            String temp = br.readLine();
            for(int j=0; j<M; j++){
                map[i][j] = temp.charAt(j) - '0';
            }
        }

        bfs(0, 0);
        System.out.println(sb);
    }

    static void bfs(int startR, int startC){
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(startR, startC, 1));
        visit[startR][startC] = 1;

        while(!queue.isEmpty()){
            Node now = queue.poll();

            if(now.r == N-1 && now.c == M-1){
                sb.append(now.count);
                return;
            }

            for(int d=0; d<4; d++){
                int nr = now.r + dr[d];
                int nc = now.c + dc[d];

                if(isIn(nr, nc) && visit[nr][nc] == 0 && map[nr][nc] == 1){
                    visit[nr][nc] = 1;
                    queue.add(new Node(nr, nc, now.count + 1));
                }
            }
        }
    }

    static boolean isIn(int nr, int nc){
        return nr >= 0 && nr < N && nc >= 0 && nc < M;
    }

}
