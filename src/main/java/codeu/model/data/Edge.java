package codeu.model.data;

public class Edge {
	
	// data fields for edge class 
	public Vertex start;
	public Vertex end;
	public int convos;
	public int friends;
	
	public Edge(Vertex start, Vertex end, int convos, int friends){
		this.start = start;
		this.end = end;
		this.convos = convos;
		this.friends = friends;
	}
	
	// for error checking 
	public String toString(){
		return "Edge from: " + start.name + " to: " + end.name + " costs: $" + convos + " takes: " + friends + " minutes";
	}
}
