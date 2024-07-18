package GraphTraversal;

import java.io.*;
import java.util.*;

import static java.lang.Integer.parseInt;

public class BJ_6087_레이저통신 {

    static class Node{
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
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(startR, startC, 0));
        visit[startR][startC] = 1;

        while(!queue.isEmpty()){
            Node out = queue.poll();
            if(out.r == laser.get(1).r && out.c == laser.get(1).c){
                result = out.count;
                return;
            }

            for(int d=0; d<4; d++){

            }
        }
    }

}
