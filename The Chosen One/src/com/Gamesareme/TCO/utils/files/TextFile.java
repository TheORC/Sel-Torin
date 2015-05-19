/**
 * 
 */
package com.Gamesareme.TCO.utils.files;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.Gamesareme.TCO.libs.Reference;

/**
 * @author Oliver
 *
 */
public class TextFile {
	
	private static String line;
	
	public static String readFile(String path){
		BufferedReader file;
		try {
			file = new BufferedReader(new FileReader(Reference.RESCOURCE_LOCAION + path));
			line = file.readLine();
			file.close();
			System.out.println("Reading file");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return line;
	}

	public static void writeFile(String path, String text){
		try {
			File file = new File(Reference.RESCOURCE_LOCAION + path);
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(text);
			bw.close();
			System.out.println("Writing file");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static File getFile(String path){
		System.out.println("Getting file");

		File file = new File(Reference.RESCOURCE_LOCAION + path);
		return file;
	}
}
