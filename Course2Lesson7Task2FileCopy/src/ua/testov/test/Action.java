package ua.testov.test;

public class Action {
	private byte[] buffer;
	private String str;
	private boolean turn = false;
	private boolean stop = false;

	public Action() {
		super();
	}

	public synchronized byte[] getArray() {
		for (; turn == false;) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		byte[] temp = this.buffer;
		System.out.println(str);
		turn = false;
		notifyAll();
		return temp;
	}

	public synchronized void setArray(byte[] buffer, String str) {
		for (; turn == true;) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		this.buffer = buffer;
		this.str = str;
		turn = true;
		notifyAll();
	}

	public boolean isStop() {
		return stop;
	}

	public void setStop(boolean stop) {
		this.stop = stop;
	}
}