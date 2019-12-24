package dataStructure;

import java.util.HashMap;

import utils.Point3D;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		

		Point3D p=new Point3D(1,2,3);
		NodeData n=new NodeData(1,p,2,"tal",0);
		EdgeData e=new EdgeData(2,2,4);
	
	
		DGraph d=new DGraph();
		d.addNode(n);
		d.getNode(1);
		d.connect(1, 1, 1);
		d.removeNode(n.getKey());
	}

}
