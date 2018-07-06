package codeu.model.data;

import java.util.*;

public class Graph {
	public List<Vertex> vertices;
	final static int INFINITY = 2147483647;
	
	
	public boolean hasUnknownDistance(){
		
		boolean flag = false;
		
		for(Vertex v : vertices){
			if(!v.known)
				flag = true;
		}
		return flag;
	}
	
	// sets initial fields for dijkstras 
	public void start(){
		for(Vertex v : vertices){
			v.dist = INFINITY;
			v.known = false;
			v.path = null;
		}
	}
	
	/*
	public void update(Vertex v){
		
		for(Edge e : v.neighbors){
			
			if(!e.end.known){
				int cvw = e.convos;
				
				if((e.start.dist + cvw) < e.end.dist){
					e.end.dist = v.dist + cvw;
					e.end.path = v;
				}
			}
		}
	}
	*/
	
	// Dijkstra algorithim for finding shortest path with convos as weight
	public void updateConvos(Vertex s){
		start();
		s.dist = 0;
		PriorityQueue<Vertex> q = new PriorityQueue<>();
		for(Vertex k : vertices){
			q.add(k);
		}
		
		while(!q.isEmpty()){
			Vertex v = q.remove();
			for(Edge e : v.neighbors){
				if(q.contains(e.end)){
					int cvw = e.convos;
					if((e.start.dist + cvw) < e.end.dist){
						e.end.dist = v.dist + cvw;
						e.end.convos = e.end.dist;
						e.end.friends = e.friends;
						e.end.path = v;
						q.remove(e.end);
						q.add(e.end);
					}
				}
			}
		}
	}
	
	
	// Dijkstra algorithim for finding shortest path with friends as weight
	public void updateFriends(Vertex s){
		
		start();
		s.dist = 0;
		PriorityQueue<Vertex> q = new PriorityQueue<>();
		
		for(Vertex k : vertices){
			q.add(k);
		}
		
		while(!q.isEmpty()){
			Vertex v = q.remove();
			for(Edge e : v.neighbors){
				if(q.contains(e.end)){
					int cvw = e.friends;
					if((e.start.dist + cvw) < e.end.dist){
						e.end.dist = v.dist + cvw;
						e.end.friends = e.end.dist;
						e.end.convos = e.convos;
						e.end.path = v;
						q.remove(e.end);
						q.add(e.end);
					}
				}
			}
		}
		
	}
}
