package vqt;

//状況の情報を格納するもの
public class Situation extends Vector {
	public static final int S_ELEMENTS=4;
	
	public Situation(double[] def) {
		super(def);
		content=new double[S_ELEMENTS];
		for(int i=0;i<S_ELEMENTS;i++)
			content[i]=def[i];
	}

	public double distance(Situation a) {return super.distance(a);}
}
