package dataStructure;
import java.util.*;
import java.util.Map.Entry;

public class DGraph implements graph{

	
	private HashMap<Integer,node_data> nodeCol=new HashMap<Integer,node_data>();
	private HashMap<Integer,HashMap<Integer,edge_data>> edgeCol=new HashMap<Integer,HashMap<Integer,edge_data>>();
	
	
	public DGraph() {
		
	}
	
	

	
	public DGraph(HashMap<Integer,node_data> n,HashMap<Integer,HashMap<Integer,edge_data>> edgeCol1) {
		
		nodeCol=(HashMap<Integer, node_data>) n;
		edgeCol=(HashMap<Integer,HashMap<Integer,edge_data>>) edgeCol1;
	}
	
	
	@Override
	public node_data getNode(int key) {
		
			return nodeCol.get(key);
		
	}

	@Override
	public edge_data getEdge(int src, int dest) {
		
			return edgeCol.get(src).get(dest);
	
	}

	@Override
	public void addNode(node_data n) {
	
		if(!nodeCol.containsKey(n.getKey()))	
		nodeCol.put(n.getKey(),(node_data) n);
		
	}

	@Override
	public void connect(int src, int dest, double w) {
		
		EdgeData newedge =new EdgeData(src,dest,w);
		if(edgeCol.containsKey(src)) {
			if(!edgeCol.get(src).containsKey(dest)) {
				edgeCol.get(src).put(dest,newedge);
			}
		}
			
		else {
			HashMap<Integer,edge_data> help=new HashMap<Integer,edge_data>();
			help.put(dest, newedge);
			edgeCol.put(src, help);
		}
	
	}
	
	@Override
	public Collection<node_data> getV() {
		
		
		return nodeCol.values();
		
	}

	@Override
	public Collection<edge_data> getE(int node_id) {
		


		return edgeCol.get(node_id).values();
	
	}

	@Override
	public node_data removeNode(int key) {
		for ( Map.Entry<Integer,node_data> e: nodeCol.entrySet()) {
				if(edgeCol.get(e)!=null) {
					if(edgeCol.get(e).get(key)!=null) {
						 edgeCol.get(e).remove(key);
						 
					}
			 	}
		}
		return nodeCol.remove(key);
		
	}
	@Override
	public edge_data removeEdge(int src, int dest) {
		
		if(edgeCol.get(src)!=null) {
		if(edgeCol.get(src).get(dest)!=null) {
			return edgeCol.get(src).remove(dest);
			}
		}
		return null;
	}
	@Override
	public int nodeSize() {
	
		return nodeCol.size();
	}

	@Override
	public int edgeSize() {
	
		return edgeCol.size();
	}

	@Override
	public int getMC() {
		// TODO Auto-generated method stub
		return 0;
	}

}
