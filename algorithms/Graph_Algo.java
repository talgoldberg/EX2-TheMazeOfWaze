package algorithms;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.text.html.HTMLDocument.Iterator;

import dataStructure.DGraph;
import dataStructure.NodeData;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;
/**
 * This empty class represents the set of graph-theory algorithms
 * which should be implemented as part of Ex2 - Do edit this class.
 * @author 
 *
 */
public class Graph_Algo implements graph_algorithms{

	
	private HashMap<Integer,node_data> nodeCol=new HashMap<Integer,node_data>();
	private HashMap<Integer,edge_data> edgeCol=new HashMap<Integer,edge_data>();
	//private HashMap<Integer,HashMap<Integer,edge_data>> edgeCol=new HashMap<Integer,HashMap<Integer,edge_data>>();
	@Override
	public void init(graph g) {
		
		HashMap<Integer,HashMap<Integer,edge_data>> help3=new HashMap<Integer,HashMap<Integer,edge_data>>();
		for(node_data n: g.getV()) {
			int k=n.getKey(); 
			HashMap<Integer,node_data> help=new HashMap<Integer,node_data>();
			help.put(k, n);
			
			if(g.getNode(k)!=null) {
			for (edge_data e: g.getE(k)) {
				if(edgeCol.containsKey(k)) {
					
				}
				HashMap<Integer,edge_data> help1=new HashMap<Integer,edge_data>();
				help1.put(k, e);
				help3.put(k, help1);
			}
			}
		
			DGraph d=new DGraph(help,help3);
		}
	
		
		
		
		
		
		
	
	       
	}
	
	
	@Override
	public void init(String file_name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(String file_name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isConnected() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double shortestPathDist(int src, int dest) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<node_data> shortestPath(int src, int dest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<node_data> TSP(List<Integer> targets) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public graph copy() {
		// TODO Auto-generated method stub
		return null;
	}

}
