package interview.amazon;


import java.util.LinkedList;
import java.util.Queue;


public class BFS_Graph {

	public final static int maxVertices = 6;
	public int vertexcount;
	public Vertex vertexlist[];
	public static int adjmatrix[][];
	private Queue<Integer> thequeue;
	public BFS_Graph()
	{
		vertexcount = 0;
		vertexlist = new Vertex[maxVertices];
		adjmatrix = new int[maxVertices][maxVertices];
		for(int x=0;x<maxVertices;x++)
		{
			for(int y=0;y<maxVertices;y++)
			{
				adjmatrix[y][x] = 0;
			}
		}
		thequeue = new LinkedList<Integer>();
	}
	
	public void addVertex(int lab)
	{
		vertexlist[vertexcount++] = new Vertex(lab);
	}
	
	public void addedge(int start,int end){
		adjmatrix[start][end] = 1;
		adjmatrix[end][start] = 1;
	}
	
	public void display(int v)
	{
		System.out.println(vertexlist[v].label);
	}
    public void bfs()
    {
    	vertexlist[0].visited = true;
    	display(0);
    	thequeue.add(0);
    	int v2;
    	while(!thequeue.isEmpty())
    	{
    		int v1 = thequeue.remove();// in dfs inly it ll be peeped 
    		
    			while((v2=getadjvertex(v1))!=-1)
    			{
    				vertexlist[v2].visited = true;
    				display(v2);
    				thequeue.add(v2);
    			}
    				
    		}
    	}
    	
   
    public int getadjvertex(int v)
    {
    	for(int j=0;j<vertexcount;j++)
    	{
    		if(adjmatrix[v][j]==1 && vertexlist[j].visited==false)
    		{
    			return j;
    		}
    	}
    	return -1;
    }
	public static void main(String[] args) {
		
		BFS_Graph graph = new BFS_Graph();
		graph.addVertex(10);
		graph.addVertex(20);
		graph.addVertex(30);
		graph.addVertex(200);
		graph.addVertex(40);
		graph.addVertex(300);
		graph.addedge(0,1);
		graph.addedge(0,5);
		graph.addedge(1,5);
		graph.addedge(1,2);
		graph.addedge(2,3);
		graph.addedge(2,4);
		graph.addedge(2,5);
		graph.addedge(3,4);
		graph.addedge(4,5);
		for(int i=0;i<maxVertices;i++)
		{
			for(int j=0;j<maxVertices;j++)
			{
				System.out.print("  "+adjmatrix[j][i]);
			}
			System.out.println();
		}
		graph.bfs();
		

	}

}
