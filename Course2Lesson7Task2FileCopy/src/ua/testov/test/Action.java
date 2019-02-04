package ua.testov.test;

public class Action {
	private byte[] buffer;
	private double count;
	private int turn = 1;
	private boolean stop = false;

	public Action() {
		super();
	}

	public synchronized byte[] getArray() {
		byte[] temp = null;
		if (turn == 3) {
			temp = this.buffer;
		} else {
			try {
				for (;;) {
					wait();
				}
			} catch (InterruptedException e) {
			}
		}
		turn = 1;
		notifyAll();
		return temp;
	}

	public synchronized double getCount() {
		double temp = 0;
		if (turn == 2) {
			temp = this.count;
		} else {
			try {
				for (;;) {
					wait();
				}
			} catch (InterruptedException e) {
			}
		}
		turn = 3;
		notifyAll();
		return temp;
	}

	public synchronized void setArray(byte[] buffer, double count) {
		if (turn == 1) {
			this.buffer = buffer;
			this.count = count;
		} else {
			try {
				for (;;) {
					wait();
				}
			} catch (InterruptedException e) {
			}

		}
		turn = 2;
		notifyAll();
	}

	public boolean isStop() {
		return stop;
	}

	public void setStop(boolean stop) {
		this.stop = stop;
	}
}