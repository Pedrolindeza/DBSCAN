package pedro.dbscan;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DBSCAN {
	
	private static int DISTANCE;
	private static double minkowskiM;
	private static double radius;
	private static double minp;
	private static List<Point> dataSet = new ArrayList<Point>();
	private static List<List<Point>> clusters = new ArrayList<List<Point>>(); 
	
	public static void main(String[] args){
		
		//choose the distance metric
		if(args.length == 3){
			setDISTANCE(1);
		}
		if(args.length == 4){
			setDISTANCE(Integer.parseInt(args[3]));
		}
		if(args.length == 5){
			setDISTANCE(Integer.parseInt(args[3]));
			setMinkowskiM(Double.parseDouble(args[4]));
		}
		
		radius = Double.parseDouble(args[1]);
		minp = Double.parseDouble(args[2]);	
			
			
		long startTime = System.currentTimeMillis();
		try {
			dataSet = Utils.getPointsList("datasets/" + args[0]);
		} catch (IOException e){
			e.printStackTrace();
		}
		long readTime = System.currentTimeMillis() - startTime;
		
		
		radius = Double.parseDouble(args[1]);
		minp = Double.parseDouble(args[2]);
		
		applyDBSCAN();
		display();
		
		long execTime = System.currentTimeMillis() - startTime;
		long totalTime = readTime + execTime;
		
		System.out.println( "\n\nRead time (ms) " + readTime);
		System.out.println( "DBSCAN time (ms) " + execTime);
		System.out.println( "Total time (ms) " + totalTime + "\n");
	}
	
	private static void display(){
		
		System.out.println( "Clusters " + clusters.size());
		
		System.out.println( "Attributes " + dataSet.get(0).getPointSize());
		
		System.out.println( "Points " + dataSet.size() );
		
		for(List<Point> cluster : clusters){
			
			System.out.println();
			
			for(Point p : cluster){
				
				System.out.print( (clusters.indexOf(cluster)+1) + " " + p.getID() + " ");
				
				for (double e: p.getPointValues()){
					System.out.print(e + " ");
				}
				
				System.out.println();
			}
					
				
			
		}
		
		System.out.println("\nNoise points");
		int count = 0;
		for( Point a : dataSet) 
			if (a.isClassed() == false){
				count++; 
				System.out.print(a.getID() + " ");
				for (double e: a.getPointValues()){
					System.out.print(e + " ");
				}
				System.out.println();
			}
		if(count == 0) System.out.print("None");
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

	public static int getDISTANCE() {
		return DISTANCE;
	}

	public static void setDISTANCE(int dISTANCE) {
		DISTANCE = dISTANCE;
	}

	public static double getMinkowskiM() {
		return minkowskiM;
	}

	public static void setMinkowskiM(double d) {
		DBSCAN.minkowskiM = d;
	}

}
