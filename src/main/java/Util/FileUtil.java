package Util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

public class FileUtil {
	public static List<String> readFile(String fileName) throws IOException {
		List<String> container=new Vector<>();
		
		try (BufferedReader read= new BufferedReader(new FileReader(fileName));){
			String inputLine=null;
			while((inputLine=read.readLine())!=null) {
				container.add(inputLine);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return container;
		
	}
	//FileUtil.readFile("resource/shuffledChinese.txt");
//	public static void main(String [] args) throws IOException {
//		List<String> test=FileUtil.readFile("resource/shuffledChinese.txt");
//		for(String x:test){
//			System.out.println(x);
//		}
//	}

}
