package ua.testov.test;


public class CopyProgress implements Runnable {
//	private double load;
	private double size;
	private Action a;

	public CopyProgress() {
		super();
	}

	public CopyProgress(double size,Action a) {
		super();
		this.size = size;
		this.a=a;
	}

	public double getLoad() {
		double load = (a.getCount()/size)*100;
		return load;
	}

	@Override
	public void run() {
		for (; !a.isStop();) {
			System.out.println(getLoad());
			System.out.println();
		}
		System.out.println("Close Progress");
	}

}
