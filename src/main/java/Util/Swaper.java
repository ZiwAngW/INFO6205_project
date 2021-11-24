package Util;

public class Swaper {
	
	public static void swap(String[] list, int i, int j) {
        String t = list[i];
        list[i]=list[j];
        list[j]=t;
	}

}
