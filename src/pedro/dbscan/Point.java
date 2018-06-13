package pedro.dbscan;

public class Point {
	private String id;
	private double[] values = null ;
	private boolean isCore;
	private boolean isClassed;

	public boolean isClassed() {
		return isClassed;
	}
	
	public void setID(String Id){
		this.id = Id;
	}
	
	public String getID(){
		return this.id;
	}

	public void setClassed(boolean isClassed) {
		this.isClassed = isClassed;
	}

	public double[] getPointValues(){
		return this.values;
	}
	
	public int getPointSize(){
		return this.values.length;
	}
	
	public double getPointValue(int pos) {
		return this.values[pos];
	}


	public void setPointValue(int pos, double value) {
		this.values[pos] = value;
	}


	public boolean isCore() {
		return isCore;
	}


	public void setCore(boolean isCore) {
		this.isCore = isCore;
		this.isClassed = true;
	}
	
	public Point (double[] values){
			this.values = values;
	}
	
	public Point (String str){
		
		String[] p = str.split (",");
		
		setID(p[0]);
		setCore(false);
		setClassed(false);
		
		this.values = new double[p.length-1];
		
		for ( int i=1; i < p.length; i++){
			this.values[i-1] = Double.parseDouble( p[i] );
		}
	}
	
}
