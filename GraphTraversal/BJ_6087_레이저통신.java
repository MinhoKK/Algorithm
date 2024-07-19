package GraphTraversal;

import java.io.*;
import java.util.*;

import static java.lang.Integer.parseInt;

public class BJ_6087_레이저통신 {
    /**
     * 11%에서 틀림...
     */

    static class Node implements Comparable<Node>{
        int r;
        int c;
        int count;
        int d;

        public Node(int r, int c, int count, int d) {
            this.r = r;
            this.c = c;
            this.count = count;
            this.d = d;
        }

        @Override
        public int compareTo(Node o) {
            return this.count - o.count;
        }
    }

    static class Laser{
        int r;
        int c;

        public Laser(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int W, H;
    static char[][] map;
    static int[][] visit;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int result;
    static List<Laser> laser = new ArrayList<>();

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        W = parseInt(st.nextToken());
        H = parseInt(st.nextToken());

        map = new char[H][W];
        visit = new int[H][W];

        for(int i=0; i<H; i++){
            String temp = br.readLine();
            for(int j=0; j<W; j++){
                map[i][j] = temp.charAt(j);
                if (map[i][j] == 'C') {
                    laser.add(new Laser(i, j));
                }
            }
        }


        bfs(laser.get(0).r, laser.get(0).c);

        System.out.println(result);
    }

    static void bfs(int startR, int startC){
        PriorityQueue<Node> queue = new PriorityQueue<>();
        //Queue<Node> queue = new LinkedList<>();
        visit[startR][startC] = 1;

        for(int d=0; d<4; d++){
            int nr = startR + dr[d];
            int nc = startC + dc[d];
            if(nr >= 0 && nr < H && nc >= 0 && nc < W && map[nr][nc] == '.'){
                queue.add(new Node(nr, nc, 0, d));
                visit[nr][nc] = 1;
            }
        }


        while(!queue.isEmpty()){
            Node out = queue.poll();
            if(out.r == laser.get(1).r && out.c == laser.get(1).c){
                result = out.count;
                return;
            }

            for(int d=0; d<4; d++){
                int nr = out.r + dr[d];
                int nc = out.c + dc[d];
                if(nr >= 0 && nr < H && nc >= 0 && nc < W && (map[nr][nc] == '.' || map[nr][nc] == 'C') && visit[nr][nc] == 0){
                    if(out.d == d){
                        queue.add(new Node(nr, nc, out.count, d));
                        visit[nr][nc] = 1;
                    } else {
                        queue.add(new Node(nr, nc, out.count+1, d));
                        visit[nr][nc] = 1;
                    }
                }
            }
        }
    }

}
