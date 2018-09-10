package vqt;

//行動議論の情報を格納するもの
public class ActionReport {
	public Action a;
	public double r=0;
	public ActionReport(Action a,double r,double aftermaxr) {
		a.normalize();
		this.a=a;
		this.r=r+ActionArguments.ALPHA*aftermaxr;
	}
}
