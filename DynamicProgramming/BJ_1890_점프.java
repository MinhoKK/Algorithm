package DynamicProgramming;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class BJ_1890_점프 {

    static class Node {
        int r;
        int c;
        int value;

        public Node(int r, int c, int value) {
            this.r = r;
            this.c = c;
            this.value = value;
        }
    }

    static int N;
    static int[][]  map;
    static int[][] visit;
    static int[] dr = {1, 0};  // 하, 우
    static int[] dc = {0, 1};

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = parseInt(br.readLine());

        map = new int[N][N];
        visit = new int[N][N];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                map[i][j] = parseInt(st.nextToken());
            }
        }

        bfs();

        System.out.println(visit[N-1][N-1]);

    }

    static void bfs(){
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(0, 0, map[0][0]));
        visit[0][0] = 1;

        while(!queue.isEmpty()){
            Node out = queue.poll();
            if(out.r == N-1 && out.c == N-1){
                continue;
            }
            for(int d=0; d<2; d++){
                int nr = out.r + (dr[d]*out.value);
                int nc = out.c + (dc[d]*out.value);

                if(nr >= 0 && nr < N && nc >= 0 && nc < N){
                    visit[nr][nc]++;
                    queue.add(new Node(nr, nc, map[nr][nc]));
                }
            }

        }
    }
}
