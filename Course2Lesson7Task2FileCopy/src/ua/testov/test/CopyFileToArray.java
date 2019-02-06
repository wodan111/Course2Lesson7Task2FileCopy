package ua.testov.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CopyFileToArray implements Runnable {
	private File in;
	private File out;
	private Action action;

	public CopyFileToArray() {
		super();
	}

	public CopyFileToArray(File in, File out, Action action) {
		super();
		this.in = in;
		this.out = out;
		this.action = action;
	}

	public void copyFileToArr() throws IOException {

		byte[] buffer = new byte[124 * 124];
		int count = 0;

		if (in.exists()==false) {
			this.action.setArray(buffer,"");
			throw new FileNotFoundException();
		} else {
			try (FileInputStream fis = new FileInputStream(in);) {

			double x = 0;
			long y = in.length();
				for (; (count = fis.read(buffer)) > 0;) {
				x=x+(double)count;
			    String str=String.format("%.3f",x/(double)y*100);
					byte[] arrCopy = new byte[count];
					System.arraycopy(buffer, 0, arrCopy, 0, count);
					this.action.setArray(arrCopy,str);
				}
			} catch (IOException e) {
				throw e;
			}
		}	
	}

	@Override
	public void run() {
		Thread thrTwo = new Thread(new SaveInfoToFile(this.out, this.action));
		thrTwo.start();
		try {
			copyFileToArr();
		} catch (IOException e) {
			e.printStackTrace();
		}
		action.setStop(true);
		System.out.println("Close Copy");
	}
}
