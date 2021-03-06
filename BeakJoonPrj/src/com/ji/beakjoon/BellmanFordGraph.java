package com.ji.beakjoon;

public class BellmanFordGraph {
	// κ·Έλ?? κ°?μ€μΉλ₯? ????΄? ?΄??€
	class Edge {
		int src, dest, weight;

		Edge() {
			src = dest = weight = 0;
		}
	};

	int V, E;
	Edge edge[];

	// V, Eκ°? ?? κ·Έλ? ??±
	BellmanFordGraph(int v, int e) {
		// TODO Auto-generated constructor stub
		V = v;
		E = e;
		edge = new Edge[e];
		for (int i = 0; i < e; ++i) {
			edge[i] = new Edge();
		}
	}

	// λ²¨λ§?¬? ?κ³ λ¦¬μ¦μ ?΄?©??¬ src?? ?€λ₯? ? ? ?€κΉμ?? μ΅λ¨κ±°λ¦¬λ₯? μ°Ύλ λ©μΈ κΈ°λ₯
	// ?? λ¬΄κ² μ£ΌκΈ° κ°μ?
	void BellmanFord(BellmanFordGraph graph, int src) {
		int V = graph.V, E = graph.E;
		int dist[] = new int[V];
		// 1?¨κ³? : src?? ?€λ₯? ? ? ?Όλ‘? λͺ¨λ  ? ?  μ΄κΈ°?
		for (int i = 0; i < V; ++i) {
			dist[i] = Integer.MAX_VALUE;
		}
		dist[src] = 0;
		// 2?¨κ³? : λͺ¨λ  edge|V|-1?,
		// src?? ?€λ₯? ? ? ?€κΉμ?? κ°λ¨? μ΅λ¨κ²½λ‘? |V|-1 ? ? κ°?μ§? ? ??€.
		for (int i = 1; i < V; ++i) {
			for (int j = 0; j < E; ++j) {
				int u = graph.edge[j].src;
				int v = graph.edge[j].dest;
				int weight = graph.edge[j].weight;
				if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
					dist[v] = dist[u] + weight;
				}
			}
		}
		// 3?¨κ³? : λΉ? κ°?μ€μΉ ?¬?΄?΄ ? κ²?
		// λ§μ½ ? μ§§μ? κΈΈμ κ°?μ§λ€λ©? ???΄ ?΄λ£¨μ΄μ§λ€
		for (int j = 0; j < E; ++j) {
			int u = graph.edge[j].src;
			int v = graph.edge[j].dest;
			int weight = graph.edge[j].weight;
			if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
				System.out.println("Bellman-Ford Graph contains negative weight cycle");
			}
		}
		printArr(dist, V);
	}

	// λ°°μ΄ μΆλ ₯
	void printArr(int dist[], int V) {
		System.out.println("Vertex Distance from Source");
		for (int i = 0; i < V; ++i) {
			System.out.println(i + "\t\t" + dist[i]);
		}
	}

	public static void main(String[] args) {

		int V = 5; // κ·Έλ?? ? ? ?
		int E = 8; // κ·Έλ?? ? ? ?

		BellmanFordGraph graph = new BellmanFordGraph(V, E);

		// add edge 0-1
		graph.edge[0].src = 0;
		graph.edge[0].dest = 1;
		graph.edge[0].weight = -1;

		// add edge 0-2
		graph.edge[1].src = 0;
		graph.edge[1].dest = 2;
		graph.edge[1].weight = 4;

		// add edge 1-2
		graph.edge[2].src = 1;
		graph.edge[2].dest = 2;
		graph.edge[2].weight = 3;

		// add edge 1-3
		graph.edge[3].src = 1;
		graph.edge[3].dest = 3;
		graph.edge[3].weight = 2;

		// add edge 1-4
		graph.edge[4].src = 1;
		graph.edge[4].dest = 4;
		graph.edge[4].weight = 2;

		// add edge 3-2
		graph.edge[5].src = 3;
		graph.edge[5].dest = 2;
		graph.edge[5].weight = 5;

		// add edge 3-1
		graph.edge[6].src = 3;
		graph.edge[6].dest = 1;
		graph.edge[6].weight = 1;

		// add edge 4-3
		graph.edge[7].src = 4;
		graph.edge[7].dest = 3;
		graph.edge[7].weight = -3;

		graph.BellmanFord(graph, 0);
	}

}