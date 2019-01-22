package ua.testov.test;

import java.io.File;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		File workFile = new File("test.docx");
		File copyFile = new File(".", "(copy) " + workFile.getName());
		try {
			copyFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Action action = new Action();
		CopyFileToArray copy = new CopyFileToArray(workFile, action);
		SaveInfoToFile paste = new SaveInfoToFile(copyFile, action);
		Thread thrOne = new Thread(copy);
		Thread thrTwo = new Thread(paste);
		thrOne.start();
		thrTwo.start();
	}
}
