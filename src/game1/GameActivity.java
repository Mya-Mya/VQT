package game1;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import vqt.Action;
import vqt.Situation;
import vqt.VQT;

public class GameActivity {
	ArrayList<Bee>bees=new ArrayList<Bee>();
	public GameActivity() {
		for(int i=0;i<5;i++)
			bees.add(new Bee());
	}

	public void run(Graphics g) {
		Point mouse= MouseInfo.getPointerInfo().getLocation();
		for(Bee bee:bees)bee.run(g, mouse);
	}
}
class Meiro{
	private VQT ai=new VQT();
	public Meiro() {
	}
	private int x=0;
	private int y=0;
	private final int maxX=0;
	private final int maxY=6;
	private final int size=70;
	private int step=0;

	public void run(Graphics g) {
		for(int i=0;i<=maxX;i++) {
			for(int j=0;j<=maxY;j++) {
				g.drawOval(size*i, size*j, 50, 50);
		}}
		g.drawOval(size*x+10, size*y+10, 30, 30);

		double[] dSt=new double[2];
		dSt[0]=x;dSt[1]=y;
		Situation St=new Situation(dSt);

		Action a=ai.askSuitableA(St);
		Random rnd=new Random();
		if(a==null||rnd.nextDouble()<0.3) {
			double[] dA=new double[4];
			for(int i=0;i<4;i++)dA[i]=rnd.nextDouble();
			a=new Action(dA);
			System.out.print("r");
		}

		double max=0;
		int maxholder=0;
		for(int i=0;i<4;i++) {
			if(max<a.content[i]) {
				max=a.content[i];
				maxholder=i;
			}
		}
		if(maxholder==0)y--;
		if(maxholder==1)x++;
		if(maxholder==2)y++;
		if(maxholder==3)x--;

		if(y<0)y=0;
		if(x<0)x=0;
		if(y>maxY)y=maxY;
		if(x>maxX)x=maxX;

		double r=((x==maxX&&y==maxY)?100:-1);

		dSt[0]=x;dSt[1]=y;
		Situation Snew=new Situation(dSt);

		ai.study(St, Snew, a, r);
		
		step++;
		if(x==maxX&&y==maxY) {
			x=0;
			y=0;
			System.out.print("\n"+step);
			step=0;
		}
	}

}

class Bee {
	private VQT ai=new VQT();
	private int bx=0;
	private int by=0;

	Situation st,stAfter=null;
	Action a;
	double r;

	public void run(Graphics g,Point mouse) {
		if(st==null)st=makeS(mouse);
		if(stAfter==null)stAfter=st;

		a=ai.askSuitableA(st);//状況から行動を聞く
		a=decideAction(a);//行動を実行、実際の行動を記録
		r=getR(mouse);//評価を把握
		st=stAfter;
		stAfter=makeS(mouse);
		ai.study(st, stAfter, a, r);//学習

		g.drawOval(bx, by, 10, 10);
	}

	public Situation makeS(Point mouse){
		double[] sv=new double[vqt.Situation.S_ELEMENTS];
		sv[0]=mouse.x-bx;
		sv[1]=mouse.y-by;
		sv[2]=bx-mouse.x;
		sv[3]=by-mouse.y;
		Situation s=new Situation(sv);
		return s;
	}

	public Action decideAction(Action a) {
		Random rdm=new Random();

		if(a==null|rdm.nextDouble()<0.1) {
			a=new Action(new double[Action.A_ELEMENTS]);
			for(int i=0;i<Action.A_ELEMENTS;i++) a.content[i]=rdm.nextDouble();
			a.normalize();
		}
		
		bx+=(a.content[0]-a.content[1])*5;
		by+=(a.content[2]-a.content[3])*5;

		checkPos();
		return a;
	}

	private void checkPos() {
		if(bx<0)bx=MyPanel.W;
		if(bx>MyPanel.W)bx=0;
		if(by<0)by=MyPanel.H;
		if(by>MyPanel.H)by=0;
	}

	public double getR(Point mouse) {
		double dist=0;
		dist=Math.pow(Math.pow(mouse.x-bx, 2)+Math.pow(mouse.y-by, 2),0.5);
		return Math.pow(10000/dist,2);
	}

	private void right() {
		bx=(bx+2);
	}
	private void left() {
		bx=(bx-2);
	}
	private void up() {
		by=(by-2);
	}
	private void down() {
		by=(by+2);
	}
}