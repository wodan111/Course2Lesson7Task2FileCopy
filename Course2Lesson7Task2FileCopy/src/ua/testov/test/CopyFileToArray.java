package ua.testov.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class CopyFileToArray implements Runnable {
	private File file;
	private Action a;

	public CopyFileToArray() {
		super();
	}

	public CopyFileToArray(File file, Action a) {
		super();
		this.file = file;
		this.a = a;
	}



	@Override
	public void run() {
		byte[] buffer = new byte[1024 * 1024];
		try (FileInputStream fis = new FileInputStream(file);) {
			for (; (fis.read(buffer)) > 0;) {
				this.a.setArray(buffer);
			}
		} catch (IOException e) {
			e.printStackTrace();;
		}
		a.setStop(true);
	}
}
