package vqt;

import java.util.ArrayList;
import java.util.Random;

public class VQT {
	public static final double MAKE_NEW_ROW=0.1;

	public ArrayList<VQTRow> data=new ArrayList<VQTRow>();

	//質問を受ける
	public Action askSuitableA(Situation sNow) {
		VQTRow rslt=null;
		rslt=nearestRowHavingS(sNow);
		return rslt==null?null:rslt.a_dci;
	}

	//学習を受ける
	public void study(Situation st,Situation stafter, Action a, double r) {
		a.normalize();

		VQTRow nearStRow=null;
		VQTRow nearStAfterRow=null;
		Random rdm=new Random();

		if(rdm.nextDouble()>MAKE_NEW_ROW)nearStRow=nearestRowHavingS(st);
		nearStAfterRow=nearestRowHavingS(stafter);

		//その行に行動、評価値、一つ未来の評価の最大値を教える
		if(nearStRow!=null) {
			nearStRow.study(a, r,
				 nearStAfterRow==null?0:nearStAfterRow.a_arg.maxR() );
			return;
		};

		data.add(new VQTRow(st,a,r,
				nearStAfterRow==null?0:nearStAfterRow.a_arg.maxR()));
	}

	//入力sに最も近いsを含む行を見つける
	private VQTRow nearestRowHavingS(Situation sIn) {
		//sInに最も近いsを含む行を見つける
		VQTRow nearestRow=null;
		double nearestDistance=-1;
			for(VQTRow row:data) {
				if(nearestDistance==-1) {
					nearestDistance=sIn.distance(row.s);
					nearestRow=row;
				};
				if (nearestDistance>sIn.distance(row.s)){
					nearestDistance=sIn.distance(row.s);
					nearestRow=row;
			}
		}
		return nearestRow==null?null:nearestRow;
	}
}
