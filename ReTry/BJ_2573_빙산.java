package ReTry;

import java.io.*;
import java.util.*;

import static java.lang.Integer.parseInt;

public class BJ_2573_빙산 {

    static class Node {
        int r;
        int c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int N, M;
    static int[][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int ice;
    static int year;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = parseInt(st.nextToken());
            }
        }


        while (true) {
            year++;

            melt();
            countIce();

            if (ice >= 2) {
                break;
            }

            int check = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] > 0) {
                        check++;
                    }
                }
            }

            if (check == 0) {
                year = 0;
                break;
            }
        }

        System.out.println(year);
    }

    static void melt() {
        int[][] tempMap = copyMap();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] > 0) {
                    int seaCount = 0;

                    for (int d = 0; d < 4; d++) {
                        int nr = i + dr[d];
                        int nc = j + dc[d];
                        if (map[nr][nc] == 0) {
                            seaCount++;
                        }
                    }

                    tempMap[i][j] -= seaCount;
                    if (tempMap[i][j] < 0) {
                        tempMap[i][j] = 0;
                    }
                }
            }
        }

        map = tempMap;
    }

    static void countIce() {
        ice = 0;
        int[][] visit = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] > 0 && visit[i][j] == 0) {
                    ice++;
                    bfs(i, j, visit);
                }
            }
        }
    }

    static void bfs(int r, int c, int[][] visit) {
        Queue<Node> queue = new LinkedList<>();


        queue.add(new Node(r, c));
        visit[r][c] = 1;

        while (!queue.isEmpty()) {
            Node out = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nr = out.r + dr[d];
                int nc = out.c + dc[d];
                if (nr >= 0 && nr < N && nc >= 0 && nc < M && visit[nr][nc] == 0 && map[nr][nc] > 0) {
                    visit[nr][nc] = 1;
                    queue.add(new Node(nr, nc));
                }
            }
        }
    }

    static int[][] copyMap() {
        int[][] temp = new int[N][M];

        for (int i = 0; i < N; i++) {
            temp[i] = map[i].clone();
        }

        return temp;
    }
}
