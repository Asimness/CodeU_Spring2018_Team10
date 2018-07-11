package codeu.model.data;

import java.util.*;

import net.bytebuddy.asm.Advice.This;

import java.io.*;

public class TestGraph {
	final static int INFINITY = 2147483647; 
	
	private List<String> data = new ArrayList<>();
	private ArrayList<String> query = new ArrayList<>();
	
	public TestGraph(List<String> data, ArrayList<String> query) {
		this.data = data;
		this.query = query;
		System.out.println(this.data);
		System.out.println(this.query);
	}
	
	public String setUp() {
		List<Vertex> vertices = new ArrayList<>();
		
		try {
			
			String from, to;
			int cost, time;
			
			// creates the vertices and edges
			for(int k = 0; k < data.size(); k++){
				StringTokenizer st = new StringTokenizer(data.get(k), "|", false);
				while(st.hasMoreTokens()){
					
					from = st.nextToken();
					to = st.nextToken();
					cost = Integer.parseInt(st.nextToken());
					time = Integer.parseInt(st.nextToken());
					
					Vertex i = new Vertex(from);
					
					Vertex j = new Vertex(to);
				
					
					Edge e = new Edge(i, j, cost, time);
					
					// checks to see if the vertex already exists 
					if(vertices.contains(i)){
							for(Vertex v : vertices){
								if(v.equals(i))
									e.start = v;
							}
					}else{
						vertices.add(i);
					}
					
					// checks to see if the vertex already exists 
					if(vertices.contains(j)){
						for(Vertex v : vertices){
							if(v.equals(j))
								e.end = v;
						}
					}else{
						vertices.add(j);
					}
					
					for(Vertex v : vertices){
						if (v.equals(i)){
							v.neighbors.add(e);
						}
					}
					
					
					
				}
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			System.out.println(e1);
			e1.printStackTrace();
		}
		
		// creates the graph
		Graph g = new Graph();
		g.vertices = vertices;
		String output = "";
		int legs = 0;
		int cost = 0;
		int time = 0;
		
		// reads in from the query file 
		try{
			
			// used to find parameters to give to the dijkstra method 
			for(int i = 0; i < query.size(); i++){
				String start, end, weight;
				output = "";
				legs = 0;
				cost = 0;
				time = 0;
				StringTokenizer st = new StringTokenizer(query.get(i), "|", false);
				
				// used to parse the query data
				while(st.hasMoreTokens()){
					start = st.nextToken();
					end = st.nextToken();
					weight = st.nextToken();
					
					Vertex s = new Vertex(start);
					
					Vertex e = new Vertex(end);
					
					if(g.vertices.contains(s) && g.vertices.contains(e)){
						if(g.vertices.contains(s)){
							for(Vertex v : g.vertices){
								if (v.equals(s))
									// calls dijkstra on correct vertices 
									Dijkstra(g,v,weight);
							}
						}
						
						// checks to see shortest path in time or cost
						if(g.vertices.contains(e)){
							for(Vertex v : g.vertices){
								if (v.equals(e)){
									output += printPath1(v, output);
									if(weight.equals("C")){
										cost = v.dist;
										time = getTime(v, time);
									}else{
										time = v.dist;
										cost = getCost(v, cost);
									}
									
									if(cost == INFINITY || time == INFINITY) {
										output = "No Network Between These Users.";
										return output;
									}
									
									// creates string to write to the output file 
									StringTokenizer st2 = new StringTokenizer(output, "|", false);
									ArrayList<String> numOfConvos = new ArrayList<>();
									while(st2.hasMoreTokens()){
										numOfConvos.add(st2.nextToken());
									}
									legs = numOfConvos.size() - 1;
									StringBuilder osb = new StringBuilder(output);
									StringBuilder tb = new StringBuilder(output);
									tb.insert(0, "There are " + legs + " edges between " + numOfConvos.get(0) + " and " + numOfConvos.get(numOfConvos.size() - 1) + " (");
									tb.append("). ");
									tb.append("Conversation cost is " + cost + ". Friends cost is " + time);
									//osb.insert(0, legs + "|");
									//osb.append("|" + cost + "|" + time);
									//output = osb.toString();
									output = tb.toString();
								}
							}
						}
					}else{
						// no flight possible
						output = "No Network Between These Users.";
						return output;
					}
					// writes to the output file
					if(i == query.size() - 1){
						//System.out.print(output);
						return output;
					}else{
						//System.out.println(output);
						return output;
					}
				}
			}
			
		}catch(Exception e){
			System.out.println(e);
		}
		
		return "";
	}

	// calls the dijkstra algorithim in the graph class
	public static void Dijkstra(Graph g, Vertex s, String weight){
		
		if(weight.equals("C"))
			g.updateConvos(s);
		else
			g.updateFriends(s);
		
	}
	
	// returns string of the path 
	public static String printPath1(Vertex v, String s){
		if(v.path != null){
			s = printPath1(v.path, s);
			s += "|";
		}
		s += v.name;
		return s;
	}
	
	// return int of the time of the path
	public static int getTime(Vertex v, int time){
		if(v.path != null){
			time = getTime(v.path, time);
			time += v.friends;
		}
		return time;
	}
	
	// return int of the cost of the path
	public static int getCost(Vertex v, int cost){
		if(v.path != null){
			cost = getCost(v.path, cost);
			cost += v.convos;
		}
		return cost;
	}
	
	
}
