package lab;

import java.io.*;
import java.util.*;

/*
 * 功能：将文件内容转化为待处理字符串
 * 参数loc：文件位置
 * 参数name：文件名称
* */

public class InputFile {
	//public static String[][] Bridges = new String[200][3];//桥接词矩阵
	public static String[] v = new String[200];//顶点集合
	public static String[][] edges = new String[200][2];//有向边集
	public static int[][] matrix = new int[200][200]; //有向图的邻接矩阵
	public static String test; //输入的测试字符串
	public static int[] visited = new int[200];//求最短路径标记数组（函数复用）
	public static int[] path = new int[200];//求最短路径记录数组（函数复用） test
	
	public static String InputFile(String loc, String name) {
		  //输入文件
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
			      str = str + tmp + " "; //消除换行符
			  }
			  str = str.replaceAll(" +", " "); //删除多余的空格
			  txt.close();
			  is.close();
			  return str;
		  } catch (IOException e) {
		      System.out.println("请输入合法路径！");
		      return str;
		  }
	} 
}
