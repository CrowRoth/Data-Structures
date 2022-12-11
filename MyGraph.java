
/**
 * class MyGraph. Will use Matrix to represent a simple weighted directed graph. There is no loop at vertex.
 * No more than one edge from vertex i to another vertex j. The vertices are numbered as 1, 2, ..., n
 * The graph with n vertices is reprented by an (n+1) by (n+1) matrix with row 0 and column 0 unused.
 * If there is an edge from vertex i to vertex j (i != j), then entry on row i column j of the matrix will
 * be 1. If there is no edge between vertex i to vertex j (i != j), then the entry on row i column j of the
 * matrix will be Integer.MAX_VALUE
 *
 * @author Hong Biao Zeng
 * @version Dec 12, 2015
 */
import java.util.*;
public class MyGraph
{
    private int[][] graph;
    private int numberOfVertices;

    /**
     * create a graph with given number of vertices with no edges
     * @param numberOfVertices number of vertices of the graph
     */
    public MyGraph(int numberOfVertices){
        this.numberOfVertices = numberOfVertices;
        graph = new int[numberOfVertices+1][numberOfVertices+1];
    }

    /**
     * create a graph with given matrix representation
     * @param graph The matrix representation on the given graph. Assume column 0 and row 0 are not used
     */
    public MyGraph(int [][] graph){
        this.graph = graph;
        // change any 0 to infinity if the 0 is not on diagonal
        for(int i = 1; i < graph.length; i++){
            for(int j = 1; j < graph.length; j++){
                if(i == j) graph[i][j] = 0;
                else if(i != j && graph[i][j] == 0)
                    graph[i][j] = Integer.MAX_VALUE;
            }
        }
        numberOfVertices = graph.length - 1;
    }

    /**
     * return a String that represent the vertices in order if the BFS algorithm is used to traversal the graph
     * starting from the given vertex. If the startVertex not exists, return an error message
     * @param startVertex The vertex where the traversal starts
     * @return A String that describes the vertices visited in order
     */
    public String bfs(int startVertex){
        // student implement this
        if(startVertex < 1 || startVertex > numberOfVertices) {
            return "Error. No such vertex exists.";
        }

        Queue<Integer> frontierQueue = new LinkedList<Integer>();
        Queue<Integer> discoveredSet = new LinkedList<Integer>();
        frontierQueue.add(startVertex);
        discoveredSet.add(startVertex);
        int adjV[] = new int[this.graph.length];
        for(int i = 1;i < this.graph.length;i++) {
            adjV[i] = 0;
        }

        while(!frontierQueue.isEmpty()) {
            int currentV = frontierQueue.remove();

            for(int j = 1;j < graph.length;j++){
                if(this.graph[currentV][j] > 0 && this.graph[currentV][j] < Integer.MAX_VALUE) {
                    adjV[j] = j;
                }
            }

            for(int i = 1;i < this.graph.length;i++) {
                if (!discoveredSet.contains(adjV[i]) && adjV[i] != 0) {
                    frontierQueue.add(adjV[i]);
                    discoveredSet.add(adjV[i]);
                }
                adjV[i] = 0;
            }
        }

        String result = "";

        while(!discoveredSet.isEmpty()) {
            result += discoveredSet.remove().toString() + " ";
        }

        return result;
    }

    /**
     * return a String that represents the vertices in order if the DFS algorithm is used to traversal the graph
     * starting from the given vertex. If the startVertex not exist, return an error message
     * @param startVertex The vertex where the traversal starts
     * @return An ArrayList of Integer that represents the vertices visited in order
     */
    public String dfs(int startVertex){
        // the student implement this
        if(startVertex < 1 || startVertex > numberOfVertices) {
            return "Error. No such vertex exists.";
        }

        LinkedList<Integer> stack = new LinkedList<Integer>();
        Queue<Integer> visitedSet = new LinkedList<Integer>();
        stack.push(startVertex);
        //int adjV[] = new int[this.graph.length];

        while(!stack.isEmpty()){
            int currentV = stack.pop();
            visitedSet.add(currentV);
            for(int j = 1;j < graph.length;j++){
                if(this.graph[currentV][j] > 0 && this.graph[currentV][j] < Integer.MAX_VALUE && !visitedSet.contains(j)) {
                    stack.push(j);
                    break;
                }
            }
        }

        String result = "";

        while(!visitedSet.isEmpty()) {
            result += visitedSet.remove().toString() + " ";
        }

        return result;

    }
}
