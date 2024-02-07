package DataStructure;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Main_13335_트럭_고민호 {

	static class Truck{
		int weight;
		int time;
		
		public Truck(int weight, int time) {
			this.weight = weight;
			this.time = time;
		}

	}
	
	static int N, W, L; // 트럭의 수, 다리 길이, 최대하중
	static List<Truck> list = new ArrayList<>();
	static Queue<Truck> bridge = new LinkedList<>();
	static int result;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = parseInt(st.nextToken());
		W = parseInt(st.nextToken());
		L = parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			list.add(new Truck(parseInt(st.nextToken()), 0));
		}
		
		int next = 0;
		bridge.add(list.get(next++));
		
		int tNum = 1;
		int tWeight = bridge.peek().weight;
		
		while(!bridge.isEmpty()) {
			Truck firstTruck = bridge.peek();
			firstTruck.time++;
			if(firstTruck.time == W) {
				tNum -= 1;
				tWeight -= firstTruck.weight;
				bridge.poll();
				System.out.println(bridge);
			}
			
			if(tNum < W && tWeight + list.get(next).weight <= L) {
				tNum += 1;
				tWeight += list.get(next).weight;
				bridge.add(list.get(next++));
				System.out.println(bridge);
			}
			
		}
		
		for(Truck truck : list) {
			result += truck.time;
		}
		
		System.out.println(result + 1);
		
	}
}
