/*
QUESTION:- Minimize count of connections required to be rearranged to make all the computers connected

DESCRIPTION:-

Given an integer N, denoting the number of computers connected by cables forming a network and a 2D array connections[][], with each row (i, j) representing a connection between ith and jth computer,
the task is to connect all the computers either directly or indirectly by removing any of the given connections and connecting two disconnected computers.
If it’s not possible to connect all the computers, print -1.
Otherwise, print the minimum number of such operations required.

EXAMPLES:-

Input: N = 4, connections[][] = {{0, 1}, {0, 2}, {1, 2}}
Output: 1
Explanation: Remove the connection between computers 1 and 2 and connect the computers 1 and 3.

Input: N = 5, connections[][] = {{0, 1}, {0, 2}, {3, 4}, {2, 3}}
Output: 0
*/

import java.io.*;
import java.util.*;

class GFG {

	// Function to visit the nodes of a graph
	public static void
	DFS(HashMap<Integer, ArrayList<Integer> > adj, int node,
		boolean visited[])
	{
		// If current node is already visited
		if (visited[node])
			return;

		// If current node is not visited
		visited[node] = true;

		// Recurse for neighbouring nodes
		for (int x : adj.get(node)) {

			// If the node is not visited
			if (visited[x] == false)
				DFS(adj, x, visited);
		}
	}

	// Utility function to check if it is
	// possible to connect all computers or not
	public static int
	makeConnectedUtil(int N, int connections[][], int M)
	{
		// Stores whether a
		// node is visited or not
		boolean visited[] = new boolean[N];

		// Build the adjacency list
		HashMap<Integer, ArrayList<Integer> > adj
			= new HashMap<>();

		// Initialize the adjacency list
		for (int i = 0; i < N; i++) {
			adj.put(i, new ArrayList<Integer>());
		}

		// Stores count of edges
		int edges = 0;

		// Building adjacency list
		// from the given edges
		for (int i = 0; i < M; ++i) {

			// Get neighbours list
			ArrayList<Integer> l1
				= adj.get(connections[i][0]);
			ArrayList<Integer> l2
				= adj.get(connections[i][0]);

			// Add edges
			l1.add(connections[i][1]);
			l2.add(connections[i][0]);

			// Increment count of edges
			edges += 1;
		}

		// Stores count of components
		int components = 0;

		for (int i = 0; i < N; ++i) {

			// If node is not visited
			if (visited[i] == false) {

				// Increment components
				components += 1;

				// Perform DFS
				DFS(adj, i, visited);
			}
		}

		// At least N - 1 edges are required
		if (edges < N - 1)
			return -1;

		// Count redundant edges
		int redundant
			= edges - ((N - 1) - (components - 1));

		// Check if components can be
		// rearranged using redundant edges
		if (redundant >= (components - 1))
			return components - 1;

		return -1;
	}

	// Function to check if it is possible
	// to connect all the computers or not
	public static void
	makeConnected(int N, int connections[][], int M)
	{
		// Stores counmt of minimum
		// operations required
		int minOps = makeConnectedUtil(N, connections, M);

		// Print the minimum number
		// of operations required
		System.out.println(minOps);
	}

	// Driver Code
	public static void main(String[] args)
	{
		// Given number of computers
		int N = 4;

		// Given set of connections
		int connections[][]
			= { { 0, 1 }, { 0, 2 }, { 1, 2 } };
		int M = connections.length;

		// Function call to check if it is
		// possible to connect all computers or not
		makeConnected(N, connections, M);
	}
}