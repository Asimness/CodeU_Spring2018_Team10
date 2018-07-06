package codeu.model.data;

import java.util.*;

public class Vertex implements Comparable<Vertex>{
	
	// data fields for vertex 
	public String name;
	public boolean known;
	public int dist;
	public int convos;
	public int friends;
	public Vertex path;
	public List<Edge> neighbors = new ArrayList<>();
	
	
	public Vertex(String name, int convos, int friends, List<Edge> neighbors){
		this.name = name;
		this.convos = convos;
		this.friends = friends;
		this.neighbors = neighbors;
	}
	
	public Vertex(String name){
		this.name = name;
	}
	
	// checks for equality of vertices by name
	public boolean equals(Object v1) {
		// TODO Auto-generated method stub
		Vertex v = (Vertex) v1;
		if(this.name.equals(v.name)){
			return true;
		}else{
			return false;
		}
	}
	
	// returns name of the vertex 
	public String toString(){
		return name;
	}

	// compares vertices by dist
	@Override
	public int compareTo(Vertex v) {
		// TODO Auto-generated method stub
		if(dist < v.dist){
			return -1;
		}else if(dist > v.dist){
			return 1;
		}else{
			return 0;
		}
	}
	
	public String getName() {return name;}
	
	public void setName(String name) {this.name = name;}
	
	public int getConvos() {return convos;}
	
	public void setConvos(int convos) {this.convos = convos;}
	
	public int getFriends() {return friends;}
	
	public void setFriends(int friends) {this.friends = friends;}
	
	public List<Edge> getNeighbors() {return neighbors;}
	
	public void setNeighbors(List<Edge> neighbors) {this.neighbors = neighbors;}
}
