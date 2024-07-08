package ReTry;

import java.io.*;
import java.util.*;

public class BJ_7576_토마토 {
    /**
     * @author 고민호
     * 백준_7576_토마토
     * 난이도 G5
     * 결과 : 통과      메모리 : 120,432KB     시간 : 652ms
     *
     */

    static class Node {
        int r;
        int c;
        int day;

        public Node(int r, int c, int day) {
            this.r = r;
            this.c = c;
            this.day = day;
        }
    }

    static int M, N;
    static int[][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static Queue<Node> queue = new LinkedList<>();
    static int result = 0;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1){
                    queue.add(new Node(i, j, 0));
                }
            }
        }

        bfs();

        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(map[i][j] == 0){
                    result = -1;
                }
            }
        }

        System.out.println(result);
    }

    static void bfs(){
        while(!queue.isEmpty()){
            Node out = queue.poll();
            for(int d=0; d<4; d++){
                int nr = out.r + dr[d];
                int nc = out.c + dc[d];
                if(nr >= 0 && nr < N && nc >= 0 && nc < M && map[nr][nc] == 0){
                    map[nr][nc] = 1;
                    queue.add(new Node(nr, nc, out.day+1));
                    result = out.day+1;
                }
            }
        }
    }
}
