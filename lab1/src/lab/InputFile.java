package lab;

import java.io.*;
import java.util.*;

/*
 * ���ܣ����ļ�����ת��Ϊ�������ַ���
 * ����loc���ļ�λ��
 * ����name���ļ�����
* */

public class InputFile {
	//public static String[][] Bridges = new String[200][3];//�ŽӴʾ���
	public static String[] v = new String[200];//���㼯��
	public static String[][] edges = new String[200][2];//����߼�
	public static int[][] matrix = new int[200][200]; //����ͼ���ڽӾ���
	public static String test; //����Ĳ����ַ���
	public static int[] visited = new int[200];//�����·��������飨�������ã�
	public static int[] path = new int[200];//�����·����¼���飨�������ã� test
	
	public static String InputFile(String loc, String name) {
		  //�����ļ�
		  String str = new String();
		  try {
			  File f = new File(loc, name);
			  InputStream is = new FileInputStream(f);
			  Scanner txt = new Scanner(is, "utf-8");
	          while (txt.hasNextLine()) {
				  String tmp = txt.nextLine();
			      tmp = tmp.replaceAll("[^a-zA-Z]", " ");
			      tmp = tmp.replaceAll("[\\t\\n\\r]", " ");
			      tmp = tmp.toLowerCase();
			      str = str + tmp + " "; //�������з�
			  }
			  str = str.replaceAll(" +", " "); //ɾ������Ŀո�
			  txt.close();
			  is.close();
			  return str;
		  } catch (IOException e) {
		      System.out.println("������Ϸ�·����");
		      return str;
		  }
	} 
}
