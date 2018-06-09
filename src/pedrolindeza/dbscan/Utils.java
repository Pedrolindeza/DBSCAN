package pedrolindeza.dbscan;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Utils {
	/**
	* Test the distance between the two points
	* @ Param p point
	* @ Param q point
	* @ Return returns the distance between two points
	*/
	public static double getDistance (Point p, Point q){
		
		int sizeP = p.getPointSize();
		
		double sum = 0;
		for(int i = 0; i < sizeP; i++){ 
			
			double aux = p.getPointValue(i)-q.getPointValue(i);
			sum += aux * aux;
		}
		
		double distance = Math.sqrt (sum);
	
		return distance;
	}
	
	 /**
	 * Check the given point 
	 * @ Param lst The list storage point
	 * @ Param p the point to be tested
	 * @ Param e radius
	 * @ Param minp density threshold
	 * @ Return a temporary storage point visited
	 */
	public static List<Point> findCore(List<Point> lst, Point p, double radius, double minp){
		
		int count = 0;
		List<Point> tmpLst = new ArrayList<Point>();
		
		for (Iterator<Point> it = lst.iterator(); it.hasNext();){
			Point q = it.next();
			if (getDistance (p, q) <= radius){
				count++;
				if (!tmpLst.contains(q)){
					tmpLst.add(q);
				}
			}
		}
		
		if (count >= minp){
			p.setCore(true);
			setListClassed(tmpLst);
			return tmpLst;
		}
		return null;
	}
	
	public static void setListClassed(List<Point> lst){
		for (Iterator<Point> it = lst.iterator (); it.hasNext ();){
			Point p = it.next ();
			if (! p.isClassed () ){
				p.setClassed (true);
			}
		}
	}
	
	public static boolean mergeCores(List<Point> a, List<Point> b){
		
		boolean merge = false;
		
		for (int index = 0; index < b.size(); index++){
				if (a.contains(b.get(index))) {
						merge = true;
						break;
				}
		}
		if (merge){
			for (int index = 0; index < b.size(); index++){
				if (!a.contains (b.get (index))) {
					a.add(b.get (index));
				}
			}
		}
		
		return merge;
	}

	
	public static List<Point> getPointsList(String txtPath) throws IOException{
		
		List<Point> lst = new ArrayList<Point> ();
		
		BufferedReader br = new BufferedReader (new FileReader (txtPath));
		
		String str = "";
	
		while ( (str = br.readLine () ) != null && str !=""){
			lst.add (new Point (str));
		}	
		br.close ();
		return lst;
	}
		
}


