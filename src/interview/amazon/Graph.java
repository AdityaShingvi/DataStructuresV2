package interview.amazon;
import java.util.Stack;


public class Graph {
	private final static int maxVertices = 4;
	private Vertex VertexList[];
	private static int adjmatrix[][];
	private int vrtexcount;
	private int edgecount;
	private Stack<Integer> thestack;
	
	public Graph()
	{	
		vrtexcount = 0;
		VertexList = new Vertex[maxVertices];
		adjmatrix = new int[maxVertices][maxVertices];
		for(int i=0;i<maxVertices;i++)
		{
			for(int j=0;j<maxVertices;j++)
			{
				adjmatrix[j][i]=0;
			}
		}
		thestack = new Stack<Integer>();
	}
	public void addVertex(int label1)
	{
		VertexList[vrtexcount++] = new Vertex(label1);
	}
	
	public void addedge(int start, int end)
	{
		adjmatrix[start][end] = 1;
		adjmatrix[end][start] = 1;
	}
	
	public void display(int v)
	{
		System.out.println("**" + VertexList[v].label);
	}
	
	public void dfs()
	{
		VertexList[0].visited = true;
		//display(0);
		thestack.push(0);
		while(!thestack.isEmpty()){
			int v = getAddAdjvertex(thestack.peek());
			if(v==-1)
			{
				System.out.println("**" + VertexList[thestack.pop()].label);
				
			}
			else
			{
				VertexList[v].visited = true;
				//display(v);
				if(VertexList[v].label<200){
				thestack.push(v);
				}
				
			}
		}
		
	}
	
	public int getAddAdjvertex(int v)
	{
			for(int j =0;j<vrtexcount;j++){
			if((adjmatrix[v][j]==1 && VertexList[j].visited==false) )
			{				
				return j;
	
			}
		}
		return -1;
	}
	
	public int checkPath(int src,int dest){
		int count = 0;
		thestack.push(src);
		VertexList[src].visited=true;
		while(!thestack.isEmpty()){
			int v = getAddAdjvertex(thestack.peek());
			if(v == dest){
				return 1;
				//return true;
			}
			else if(v==-1){
				thestack.pop();
				}
			else{
				VertexList[v].visited=true;
				thestack.push(v);
			}
		}
		return count;
	}
	
	public int countPath(int src,int dest){
		VertexList[src].visited = true;
		int count = 0;
		thestack.push(src);
		while(! thestack.isEmpty()){
			int v = getAddAdjvertex(thestack.peek());
			if (v == dest){
				count++;
			}
			else if(v == -1){
				thestack.pop();
			}
			else {
				VertexList[v].visited = true;
				thestack.push(v);
			}
		}
		return count;
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Graph graph = new Graph();
		graph.addVertex(10);
		graph.addVertex(20);
		graph.addVertex(30);
		graph.addVertex(40);
		graph.addedge(0,1);
		graph.addedge(0,2);
		graph.addedge(1,2);
		graph.addedge(2,3);
		
		for(int i=0;i<maxVertices;i++)
		{
			for(int j=0;j<maxVertices;j++)
			{
				System.out.print("  "+adjmatrix[j][i]);
			}
			System.out.println();
		}
		//graph.dfs();
		System.out.println("return value: " + graph.checkPath(3, 3));
	}

}
