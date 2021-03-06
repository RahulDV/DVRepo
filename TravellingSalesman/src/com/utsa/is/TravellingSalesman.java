package com.utsa.is;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

/**
 * This class contains the methods for optimal and approximate solutions to Traveling Salesman problem.
 * Traveling Salesman Problem mainly deals with finding a hamiltonian path with least cost. 
 * I also have included code to my own approximation algorithm which I developed using greedy techniques. 
 * @author Venkat Rahul Dantuluri A040588
 *
 */
public class TravellingSalesman {

	/**
	 * Now starts the main method that basically formulates the input (set of vertices) and calls methods to return optimal solution and approximate solution.
	 * The nomenclature of the methods used should be pretty straight forward to understand which method does what.   
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		
		/**
		 * The first few lines generates the cost matrix of the vertices involved. It is generated by random generation using the number of vertices involved as seed.
		 */
		int numberOfVertices=5;
		int[] vertices = new int[numberOfVertices];
		for(int i=0;i<numberOfVertices;i++){
			vertices[i]=i;
		}
		double[][] costs = generateCostsMatrix(numberOfVertices);
		
		long initialTime = 0;
		long finalTime = 0;
		Path optimalPath = null;
		long avgtime1=0;
		double avgcost1=0.0;
		long avgtime2=0;
		double avgcost2=0.0;
		
		
//				
		for (int i = 0; i < 10; i++) {
			/**
			 *The next few lines of code revolves around computing the path returned by the method that computes the optimal solution. It also computes the execution
			 *time that has elapsed and cost of the optimal path and prints the result. 
			 */
			initialTime = System.nanoTime();
			optimalPath = optimalSolution(costs,vertices);
			finalTime = System.nanoTime();
			avgtime1 = avgtime1 + (finalTime - initialTime);
			avgcost1 = avgcost1 + optimalPath.getPathWeight();
			System.out.println("optimal path: "+optimalPath.getPathId()+"; + optimal cost: "+optimalPath.getPathWeight()+"; + execution time (ns): "+(finalTime-initialTime));

			/**
			 * The next few lines of code contain the method call to the new approxmiation algorithm.
			 */
			optimalPath = null;
			initialTime = System.nanoTime();
			optimalPath = approximateSolution(costs, vertices);
			finalTime = System.nanoTime();
			avgtime2 = avgtime2 + (finalTime - initialTime);
			avgcost2 = avgcost2 + optimalPath.getPathWeight();
			System.out.println("approximate2 path: " + optimalPath.getPathId()
					+ "; + approximate2 cost: " + optimalPath.getPathWeight()
					+ "; + execution time (ns): " + (finalTime - initialTime));
			
			Thread.sleep(1000);
			
			
		}
		System.out.println("optimal avg cost = "+avgcost1/10+" optimal avg runtime ="+avgtime1/10);
		System.out.println("approx2 avg cost = "+avgcost2/10+" approx2 avg runtime ="+avgtime2/10);
		
	}
	
	
	public static Path approximateSolution(double[][] costs, int[] vertices){
		Path optimalPath = new Path();
		double optimalWeight = 0.0;
		Queue<Vertex> mst = generateMST(costs, vertices, new Random().nextInt(vertices.length));
		Vertex v0 = mst.remove();
		StringBuffer sb = new StringBuffer();
		sb.append(v0.getId());
		int root = v0.getId();
		while(!mst.isEmpty()){
			Vertex v = mst.remove();
			sb.append(v.getId());
			optimalWeight = optimalWeight + costs[v0.getId()][v.getId()];
			v0=v;
		}
		optimalWeight = optimalWeight + costs[v0.getId()][root];
		optimalPath.setPathId(sb.toString());
		optimalPath.setPathWeight(optimalWeight);
		return optimalPath;
		
		
	}
	
	static Queue<Vertex> generateMST(double[][] costs, int[] vertices, int root){
		Comparator<Vertex> comparator = new Comparator<Vertex>() {
			
			@Override
			public int compare(Vertex o1, Vertex o2) {
				if(o1.getKey()>o2.getKey()){
					return 1;
				} else if(o1.getKey()<o2.getKey()){
					return -1;
				} else {
					return 0;
				}
			}
		};
		PriorityQueue<Vertex> queue = new PriorityQueue<Vertex>(vertices.length, comparator);
		for(int element:vertices){
			Vertex vertex = new Vertex(element);
			if(element==root){
				vertex.setKey(0);
			}
			queue.add(vertex);
		}
		Queue<Vertex> mst = new LinkedList<Vertex>();
		while(!queue.isEmpty()){
			Vertex vertex = queue.poll();
			mst.add(vertex);
			for(int i=0;i<vertices.length;i++){
				Vertex adjVertex = new Vertex(i);
				Iterator<Vertex> it =queue.iterator();
				while(it.hasNext()){
					Vertex temp = it.next();
					if(i==temp.getId()){
						adjVertex = temp;
						break;
					}
				}
				if(costs[vertex.getId()][adjVertex.getId()]<adjVertex.getKey()){
					if(queue.remove(adjVertex)){
						adjVertex.setKey(costs[vertex.getId()][adjVertex.getId()]);
						adjVertex.setParent(vertex);
						queue.add(adjVertex);
					}
				}
			}
		}
		return mst;
	}
	
	/**
	 * This method gives us the optimal solution to travelling salesman problem
	 * @param costs
	 * @param vertices
	 * @return
	 */
	public static Path optimalSolution(double[][] costs, int[] vertices){
		Comparator<Path> comparator = new Comparator<Path>() {
			
			@Override
			public int compare(Path o1, Path o2) {
				if(o1.getPathWeight()<o2.getPathWeight())
					return -1;
				else if(o1.getPathWeight()>o2.getPathWeight())
					return 1;
				else 
					return 0;
			}
		};
		List<int[]> permutations = findAllPermutations(vertices);
		
		PriorityQueue<Path> priorityQueue = new PriorityQueue<Path>(permutations.size(),comparator);
		for(int[] permutation:permutations){
			int lastElementIndex = permutation.length-1;
			double permutationWeight=0;
			StringBuilder id = new StringBuilder(permutation.length); 
			id.append(permutation[0]);
			for(int i=1;i<=lastElementIndex;i++){
				permutationWeight = permutationWeight+costs[permutation[i-1]][permutation[i]];
				id.append(permutation[i]);
			}
			permutationWeight = permutationWeight + costs[permutation[lastElementIndex]][permutation[0]];
			Path path = new Path();
			path.setPathId(id.toString());
			path.setPathWeight(permutationWeight);
			priorityQueue.add(path);
		}
		return priorityQueue.peek();
	}

	
	static List<int[]> findAllPermutations(int[] vertices){
		List<int[]> permutations = new ArrayList<int[]>();
		if(vertices.length == 1){
			permutations.add(vertices);
			return permutations;
		}
		else {
			for(int vertex: vertices){
				int[] subList = new int[vertices.length-1];
				int index=0;
				for(int item:vertices){
					if(item != vertex){
						subList[index]=item;
						index++;
					}
				}
				List<int[]> subListPermutations = findAllPermutations(subList);
				for(int[] subListPermutation:subListPermutations){
					int[] permutation = new int[vertices.length];
					permutation[0] = vertex;
					index=1;
					for(int element:subListPermutation){
						permutation[index] = element;
						index++;
					}
					permutations.add(permutation);
				}
			}
		}
		return permutations;
	}
	
	static Path approxSolution2(double[][] costs, int[] vertices){
		List<Integer> coveredVertices;
		List<Integer> remainingVertices;
		Path optimalPath = new Path();
		optimalPath.setPathWeight(999);
		for(int i=0;i<vertices.length;i++){
			coveredVertices = new ArrayList<Integer>();
			remainingVertices = new ArrayList<Integer>();
			int coveredVertex=i;
			for (int vertex : vertices) {
				remainingVertices.add(vertex);
			}
			double pathWeight=0;
			coveredVertices.add(coveredVertex);
			while (pathWeight<optimalPath.getPathWeight()&&coveredVertices.size()<vertices.length) {
				remainingVertices.remove(new Integer(coveredVertex));
				double minPath = 1;
				int newCoveredVertex = coveredVertex;
				for (Integer remainingVertex : remainingVertices) {
					if (costs[coveredVertex][remainingVertex.intValue()] < minPath) {
						minPath = costs[coveredVertex][remainingVertex.intValue()];
						newCoveredVertex = remainingVertex;
					}
				}
				pathWeight=pathWeight+minPath;
				coveredVertex=newCoveredVertex;
				coveredVertices.add(coveredVertex);
			}
			if(coveredVertices.size()==vertices.length){
				pathWeight = pathWeight+costs[coveredVertex][i];
				if(pathWeight<optimalPath.getPathWeight()){
					optimalPath.setPathWeight(pathWeight);
					StringBuffer pathId = new StringBuffer();
					for(Integer optimalVertex:coveredVertices){
						pathId.append(optimalVertex);
					}
					optimalPath.setPathId(pathId.toString());
				}
			}
			
		}
		return optimalPath;
		
	}
	
	static double[][] generateCostsMatrix(int length){
		Random random = new Random(length);
		double[][] costs = new double[length][length];
		for(int i=0;i<length;i++){
			for(int j=0;j<length;j++){
				costs[i][j] = random.nextDouble();
				System.out.printf("%f ", costs[i][j]);
			}
			System.out.printf("\n");
		}
		return costs;
	}
	
}
