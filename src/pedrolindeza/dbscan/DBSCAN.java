package pedrolindeza.dbscan;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DBSCAN {
	
	private static double radius;
	private static double minp;
	private static List<Point> dataSet = new ArrayList<Point>();
	private static List<List<Point>> clusters = new ArrayList<List<Point>>(); 
	
	public static void main(String[] args){
		
		if(args.length != 3){
			return;
		}
		
		try {
			dataSet = Utils.getPointsList("datasets/" + args[0]);
		} catch (IOException e){
			e.printStackTrace();
		}
		
		radius = Double.parseDouble(args[1]);
		minp = Double.parseDouble(args[2]);
		
		applyDBSCAN();
		display();

	}
	
	private static void display(){
		for(List<Point> cluster : clusters){
			System.out.println("\nCluster " + (clusters.indexOf(cluster)+1) + " contains ID's");
			
			for(Point p : cluster)
				System.out.print(" " + p.getID());
			
		}
		System.out.println("\nNoise points");
		for( Point a : dataSet) if (a.isClassed() == false) System.out.print(" "+ a.getID());
		System.out.println("\n");
	}
	
	
	private static void applyDBSCAN(){
		
		List<List<Point>> cores = new ArrayList<List<Point>>(); 
		
		for (Iterator<Point> it = dataSet.iterator(); it.hasNext ();){	
			
			Point p = it.next();
				
			List<Point> core = new ArrayList<Point>();
			core = Utils.findCore(dataSet, p, radius, minp);

			if (core != null){
				cores.add(core);
			}
	
		}
		
		System.out.println("\nCore Points:" + cores.size());
		
		//join cores
		while(cores.size() > 0){
			
			List<Point> coreA = cores.get(0);
			int size = cores.size();
			
			int aux=1;
			for(int i = 1 ; i < size; i++){
				
				List<Point> coreB = cores.get(aux);
				if (coreA == coreB) continue;
				if (Utils.mergeCores(coreA,coreB)) cores.remove(coreB);
				else aux++;
				
			}
			clusters.add(coreA);
			cores.remove(coreA);
		}
		
	}

}
