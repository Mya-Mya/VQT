package vqt;

//行動の情報を格納するもの
public class Action extends Vector {
	public static final int A_ELEMENTS=4;

	public Action(double[] def) {
		super(def);
		content=new double[A_ELEMENTS];
		for(int i=0;i<A_ELEMENTS;i++)
			content[i]=def[i];
	}

	public double distance(Action a) {return super.distance(a);}

}
