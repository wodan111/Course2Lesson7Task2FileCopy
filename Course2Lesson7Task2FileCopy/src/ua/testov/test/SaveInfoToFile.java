package ua.testov.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class SaveInfoToFile implements Runnable {
	private File file;
	private Action a;

	public SaveInfoToFile() {
		super();
	}

	public SaveInfoToFile(File file, Action a) {
		super();
		this.file = file;
		this.a = a;
	}


	@Override
	public void run() {
		for (; !a.isStop();) {
			try (FileOutputStream fos = new FileOutputStream(file, true)) {
				fos.write(a.getArray());
			} catch (IOException e) {
				e.printStackTrace();;
			}
		}
	}
}
