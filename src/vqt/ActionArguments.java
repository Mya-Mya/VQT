package vqt;

import java.util.ArrayList;
import java.util.Random;

//行動議論
public class ActionArguments {
	public static final double MAKE_NEW_REPORT=0.15;
	public static final double ALPHA=0.5;
	public static final double GAMMA=0.8;

	public ArrayList<ActionReport> repos=new ArrayList<ActionReport>();
	public ActionArguments() {
	}

	//行動レポートを一掃する
	public void clearRepos() {
		repos.clear();
	}
	//行動を決定させる
	public Action decideA() {
		if(repos.isEmpty()) return null;
		Action aSum=new Action(new double[Action.A_ELEMENTS]);

		//rが最も大きいもの
		//TODO:もっといい方法ないかな
		ActionReport RepoHavingMaxR=null;

		for(ActionReport a:repos) {
			if(RepoHavingMaxR==null)RepoHavingMaxR=a;
			RepoHavingMaxR=a.r>RepoHavingMaxR.r?a:RepoHavingMaxR;
		}
		aSum.normalize();
		return RepoHavingMaxR.a;
	}

	public void study(Action a,double r,double aftermaxr) {
		ActionReport nearRepo=null;
		Random rdm=new Random();

		if(rdm.nextDouble()>MAKE_NEW_REPORT)nearRepo=nearestRepoHavingA(a);
		if(nearRepo!=null) {
			nearRepo.r=nearRepo.r+ALPHA*(r+GAMMA*aftermaxr-nearRepo.r);
			return;
		}

		repos.add(new ActionReport(a, r));
	}

	public double maxR() {
		double max=-1000;//TODO:どうしよう
		for(ActionReport arg:repos) {
			max=max<arg.r?arg.r:max;
		}
		return max;
	}

	//入力aに最も近いaを含む行動議論を見つける
	public ActionReport nearestRepoHavingA(Action aIn) {
		ActionReport nearestRepo=null;
		double nearestDistance=-1;
		for(ActionReport arg:repos) {
			if(nearestDistance==-1)nearestDistance=aIn.distance(arg.a);
			if (nearestDistance>aIn.distance(arg.a)){
				nearestDistance=aIn.distance(arg.a);
				nearestRepo=arg;
			}
		}
	return nearestRepo==null?null:nearestRepo;
	}
}
