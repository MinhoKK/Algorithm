package ReTry;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class BJ_15686_치킨배달 {

    static class Node{
        int r;
        int c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int N, M;
    static int[][] map;
    static int[] comb;
    static List<Node> chicken = new ArrayList<>();
    static List<Node> house = new ArrayList<>();

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());

        map = new int[N][N];
        comb = new int[M];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                map[i][j] = parseInt(st.nextToken());
                if(map[i][j] == 1){
                    house.add(new Node(i, j));
                } else if(map[i][j] == 2){
                    chicken.add(new Node(i, j));
                }
            }
        }


    }
}
