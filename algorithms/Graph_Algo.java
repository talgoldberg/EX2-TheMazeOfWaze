package algorithms;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.PriorityQueue;

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
	private HashMap<Integer,edge_data> edgeCol1=new HashMap<Integer,edge_data>();
	private HashMap<Integer,HashMap<Integer,edge_data>> edgeCol=new HashMap<Integer,HashMap<Integer,edge_data>>();
	private DGraph d=new DGraph();

	@Override
	public void init(graph g) {

		for(node_data n: g.getV()) {
			int k=n.getKey(); 
			nodeCol.put(k, n);

			if(g.getNode(k)!=null &&g.getE(k)!=null) 
			{
				for (edge_data e: g.getE(k)) {
					if(edgeCol.containsKey(k)) {
						edgeCol.get(k).put(e.getDest(),e);
					}

					edgeCol1.put(k, e);
					edgeCol.put(k, edgeCol1);
				}
			}

		}

		d=new DGraph(nodeCol,edgeCol);	
	}
	@Override
	public void init(String file_name) {

		try{
			FileInputStream myFile = new FileInputStream(file_name);
			ObjectInputStream ois = new ObjectInputStream(myFile);
			d= (DGraph) ois.readObject();
			ois.close();
		}catch(Exception error) {
			error.printStackTrace();
		}

	}

	@Override
	public void save(String file_name) {

		try{
			FileOutputStream myFile = new FileOutputStream(file_name);
			ObjectOutputStream oos = new ObjectOutputStream(myFile);
			oos.writeObject(d);
			oos.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public boolean isConnected() {

		if((d.nodeSize()>1 && d.edgeSize()==0) || (d.nodeSize()>d.edgeSize()) )
			return false;

		if(d.nodeSize()==1&&d.edgeSize()==0)
			return true; 
		
		putTag0(d);
		
		boolean ans=false;
		for(node_data n: d.getV()) {
				
			n.setTag(1);
			chaek(d.getE(n.getKey()));
			
			
		}
		if(chaekTag1(d)==ans)
			return ans;
		else
			ans=true;
		
		return ans;

	}
	private void chaek(Collection<edge_data> edge) {
		
		for (edge_data e:edge) {
			
			node_data help=nodeCol.get(e.getDest());
			if(help.getTag()==0)
				help.setTag(1);
			
			if(help.getTag()==1)
				return;
		
			chaek(d.getE(help.getKey()));
		}	
		
	
	
	}
	private void putTag0(graph g) {
		
		for(node_data n : g.getV()) {
			int k=n.getKey(); 
			g.getNode(k).setTag(0);
		}
	}
	private boolean chaekTag1(graph g) {
		for(node_data n : g.getV()) {
			if(g.getNode(n.getKey()).getTag()!=1)
				return false;
		}
		return true;
		}
	private void initGraph(int src)

	{

		for(Entry<Integer, node_data> entry : nodeCol.entrySet()) 

		{

			entry.getValue().setTag(-1);//Tag contains the predecessor`s id

			entry.getValue().setInfo("FALSE");//info contains boolean visited or not

			if(entry.getValue().getKey()==src)

				entry.getValue().setWeight(0);//set src vertex`s weight to 0

			else

				entry.getValue().setWeight(Double.MAX_VALUE);//setting all Nodes weight to infinity

		}

	}
	@Override
	public double shortestPathDist(int src, int dest)//dijkstra algorithm O(V*E)

	{

		node_data current;

		PriorityQueue<node_data> q=new PriorityQueue<>(nodeCol.size(),new Vertex_Comperator());

		initGraph(src);

		q.addAll(nodeCol.values());

		while(!q.isEmpty())

		{

			current=q.remove();

			if(edgeCol.containsKey(current.getKey()))

			{

				HashMap<Integer,edge_data> map=edgeCol.get(current.getKey());

				for(edge_data edge:map.values())//iterate over all edges going out from current vertex

				{

					node_data dst=nodeCol.get(edge.getDest());

					if(dst.getInfo().equals("FALSE")) //we skip dst vertex if visited already 

					{

						if(current.getWeight()+edge.getWeight()<dst.getWeight())

						{

							dst.setWeight(current.getWeight()+edge.getWeight());

							dst.setTag(current.getKey());//set dst predcessor to be current vertex

						}

					}

				}

			}

			current.setInfo("TRUE");

		}

		return nodeCol.get(dest).getWeight();

	}

	@Override
	public List<node_data> shortestPath(int src, int dest) 

	{
		List<node_data> ans=new ArrayList<>();

		if(shortestPathDist(src,dest)==Double.MAX_VALUE)

		{

			ans.add(nodeCol.get(src));
			return ans;

		}



		node_data runner=nodeCol.get(dest);

		while(runner.getKey()!=src)//make us stop after adding drc vertex to the List

		{

			ans.add(new NodeData(runner.getLocation(),runner.getKey(),runner.getWeight()));

			runner=nodeCol.get(runner.getTag());

		}

		ans.add(nodeCol.get(src));
		Collections.reverse(ans);
		return ans;

	}

	@Override
	public List<node_data> TSP(List<Integer> targets) 
	{

		List<node_data> TSP = new LinkedList<node_data>();

		Iterator<Integer> itr=targets.iterator();

		int src=itr.next();

		TSP.add(0,d.getNode(src));

		while(itr.hasNext()) {

			int dest=itr.next();
			List<node_data> nodePath = new LinkedList<node_data>(shortestPath(src,dest));

			nodePath.remove(0);

			TSP.addAll(nodePath);
			src=dest;

		}

		return TSP;

	}

	@Override
	public graph copy() {
		graph g=new DGraph(nodeCol,edgeCol);
		return g;
		
		
	}
	private class Vertex_Comperator implements Comparator<node_data> 

	{

		public Vertex_Comperator()

		{



		}



		@Override

		public int compare(node_data v2,node_data v1)

		{

			if(v1.getWeight()-v2.getWeight()>0)

				return -1;

			else return 1;

		}

	}

}
