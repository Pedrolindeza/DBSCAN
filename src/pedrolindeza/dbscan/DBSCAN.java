package pedrolindeza.dbscan;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DBSCAN {
	
	private double radius;
	private double minp;
	private static List<Point> dataSet = new ArrayList<Point>();
	
	public static void main (String [] args){
		
		if(args.length != 3){
			return;
		}
		
		try {
			dataSet = Utils.getPointsList("datasets/" + args[0]);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		System.out.println(dataSet.size());
		
	}
	
	/**
	* Extract all the points in the text and stored in the pointsList
	* @ Throws IOException
	
	private static void display () (
			int index = 1;
			for (Iterator it = resultList.iterator(); it.hasNext ();){
				List lst = it.next ();
				if (lst.isEmpty ()) (
						continue;
				) 
				System.out.println ("----- s "+ index +" a cluster -----");
	for (Iterator it1 = lst.iterator (); it1.hasNext ();){
	Point p = it1.next ();
	System.out.println (p.print ());
	)
	index + +;
	)
	) */
}
