package vqt;

public class Vector {
	public double[] content;

	public Vector(double[] def) {
	}

	public double distance(Vector a) {
		if(a.content.length!=this.content.length)return -1;
		if(a.content.length==0|this.content.length==0)return -2;
		int length=this.content.length;
		double dst=0;
		for(int i=0;i<length;i++) {
			dst+=Math.pow(a.content[i]-this.content[i], 2);
		}
		return Math.pow(dst, 0.5);
	}

	public void normalize() {
		double siz=getSize();
		double ext=1/siz;
		for(int i=0;i<content.length;i++) {
			content[i]=content[i]*ext;
		}
	}

	public double getSize() {
		double siz=0;
		for(int i=0;i<content.length;i++) {
			siz+=Math.pow(content[i], 2);
		}
		return Math.pow(siz, 0.5);
	}
}
