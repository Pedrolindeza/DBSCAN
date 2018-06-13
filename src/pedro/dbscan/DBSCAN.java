package pedro.dbscan;

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
			System.out.println("\nMissing Arguments!\n");
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
		System.out.println("\n\nNoise points");
		int count = 0;
		for( Point a : dataSet) if (a.isClassed() == false){count++; System.out.print(" "+ a.getID());}
		if(count == 0) System.out.print("None");
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
			
			int i = 1;
			
			while(i < cores.size() ){
				
				List<Point> coreB = cores.get(i);
				 
				if (Utils.mergeCores(coreA,coreB)) {
					cores.remove(i);
					i=1;
				}
				else{ i++; }
				
			}
			cores.remove(0);
			clusters.add(coreA);
		}
		
	}

}
