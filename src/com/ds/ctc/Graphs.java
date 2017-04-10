package com.ds.ctc;

import java.util.Iterator;
import java.util.LinkedList;

public class Graphs {

	
	public static void main(String[] args) {
		// Create a graph given in the above diagram
        Graph g = new Graph(4);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);
        
        System.out.println(g.bfs(0, 3));
        System.out.println(g.bfs(2, 1));
        System.out.println(g.bfs(1, 3));
        System.out.println(g.bfs(3, 1));
 
        System.out.println(g.dfs(0, 3));
        System.out.println(g.dfs(2, 1));
        System.out.println(g.dfs(1, 3));
        System.out.println(g.dfs(3, 1));

	}
}

class Graph {
	private int noOfVertices;
	private LinkedList<Integer>[] adjacent;

	public Graph(int vertices) {
		this.noOfVertices = vertices;
		adjacent = new LinkedList[vertices];
		for(int i=0;i<adjacent.length;i++) {
			adjacent[i] = new LinkedList<Integer>();
		}
	}
	
	public void addEdge(int src, int dest) {
		adjacent[src].add(dest);
	}

	// does a path exists bfs or search in bfs
	public boolean bfs(int source, int destination) {
		LinkedList<Integer> queue = new LinkedList<Integer>();
		boolean[] visited = new boolean[noOfVertices];
		
		visited[source] = true;
		queue.add(source);
		
		Iterator<Integer> it;
		while (!queue.isEmpty()) {
			int currentNode = queue.remove();
			it = adjacent[currentNode].iterator();
			
			while(it.hasNext()) {
				int currentPath = it.next(); 
				if(currentPath == destination) {
					return true;
				}
				if(!visited[currentPath]) {
					visited[currentPath] = true;
					queue.add(currentPath);
				}
			}
		}
		return false;
	}
	
	public boolean dfs(int source, int destination) {
		return dfs(source, destination, new boolean[noOfVertices]);
	}
	
	// does a path exists dfs or search in dfs
	public boolean dfs(int source, int destination, boolean[] visited) {
		Iterator<Integer> it;
	
		visited[source] = true;
		it = adjacent[source].iterator();
		
		while(it.hasNext()) {
			int currentNode = it.next();
			
			if (currentNode == destination) {
				return true;
			}
			if (!visited[currentNode]) {
				if(dfs(currentNode, destination, visited))
					return true;
			}
		}
		return false;
	}
}