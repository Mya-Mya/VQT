package vqt;

import java.util.Random;

//VQTの1行は状況, 行動議論, 決定行動の3列
public class VQTRow {
	public Situation s;
	public ActionArguments a_arg;
	public Action a_dci;

	public VQTRow(Situation s,Action a,double r, double aftermaxr) {
		this.s=s;
		this.a_arg=new ActionArguments();
		this.study(a, r,aftermaxr);
	}

	public void study(Action a, double r,double aftermaxr) {
		//行動議論に行動と評価値を与える
		a_arg.study(a, r,aftermaxr);

		//決定行動を行動議論集から計算する
		a_dci=a_arg.decideA();

		//メモリ掃除
		Random rdm=new Random();
		if(rdm.nextDouble()<0.01) {
			a_arg.clearRepos();
			a_arg.study(a, r,0.0);
		}
	}

}
