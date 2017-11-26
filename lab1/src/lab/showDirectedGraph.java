package lab;

import java.io.*;
import java.util.*;

/*
 * 功能：利用插件Graphviz展示有向图
 * */
public class showDirectedGraph {
	//public static String[][] Bridges = new String[200][3];//桥接词矩阵
	public static String[] v = new String[200];//顶点集合
	public static String[][] edges = new String[200][2];//有向边集
	public static int[][] matrix = new int[200][200]; //有向图的邻接矩阵
	public static String test; //输入的测试字符串
	public static int[] visited = new int[200];//求最短路径标记数组（函数复用）
	public static int[] path = new int[200];//求最短路径记录数组（函数复用） test
	
	 /*
	   * 功能：创建边组
	   * 参数txt：处理好后文本
	   * */
	  public static String[][] EdgesCreate(String txt) {
		  String[] ntxt = txt.split(" "); //字符串转换为字符串组
		  int len = ntxt.length;
		  String[][] Edges = new String[len-1][2]; // NOPMD by dell on 17-10-21 下午3:20
		  for (int i = 0; i < len - 1; i++) {
			  Edges[i][0] = ntxt[i];
			  Edges[i][1] = ntxt[i + 1];
		  }
		  return Edges;
	  }
	   /*
	   * 功能：创建点组
	   * 参数txt：处理好后的文本
	   * */
	  public static String[] VerCreate(String txt) {
		   String[] ntxt = txt.split(" "); //字符串转换为字符串组
		   String[] tmpver = new String[ntxt.length];
		   int con = 0;
		   for (int i = 0; i < ntxt.length; i++) {
			   String tmp = ntxt[i];
			   if (!Arrays.asList(tmpver).contains(tmp)) {    //删除重复点
				   tmpver[con] = tmp;
				   con++;
			   }
		   }
		   String[] Vertexs = new String[con];
		   for (int i = 0; i < con; i++) {
			Vertexs[i] = tmpver[i];
		}
		   return Vertexs;
	   }
	   /*
	    * 功能：计算边的权值，并删除重复边
	    * 参数Edges：处理好的边组
	    * */
	   public static String[][] EdgeWeight(String[][] Edges) {
		   String[] tmpedge = new String[Edges.length];         //将边的两个顶点和为一个字符串
		   for (int i = 0; i < Edges.length; i++) {
			   tmpedge[i] = Edges[i][0] + " " + Edges[i][1];
		   }
		   String[] tmpedge1 = new String[tmpedge.length]; //删除重复的字符串（即删除重复边）
		   int count = 0;
		   for (int i = 0; i < tmpedge.length; i++) {
			   String tmpedge2 = tmpedge[i];
			   if (!Arrays.asList(tmpedge1).contains(tmpedge2)) {
				   tmpedge1[count] = tmpedge2;
				   count++;
			   }
		   }
		   String[] tmp = new String[count];
		   for (int i = 0; i < count; i++) {
			tmp[i] = tmpedge1[i];
		}
		   int[] weight = CountWeight(tmpedge, tmp); //计算边的权值
		   String[][] EdgesWeight = new String[tmp.length][3];
		   for (int i = 0; i < tmp.length; i++) {
			   String[] tran = tmp[i].split(" ");
			   EdgesWeight[i][0] = tran[0];
			   EdgesWeight[i][1] = tran[1];
			   EdgesWeight[i][2] = Integer.toString(weight[i]);
		   }
		   return EdgesWeight;
	   }
	   /*
	    * 功能：计算边的权值
	    * 参数Edges：将一条边的两个节点合为一个字符串的字符串数组（包含重复边）
	    * 参数word：将一条边的两个节点合为一个字符串的字符串数组（不包含重复边）
	    * */
	   public static int[] CountWeight(String[] Edges, String[] word){
		   Map<String, Integer> link = new HashMap<String, Integer>();
		   int[] weight = new int[word.length];
		   for (String i : Edges) {
			   if (link.containsKey(i)) {
				   int count = link.get(i);
				   link.put(i, ++count);
			   } else {
				link.put(i, 1);
			}
		   }
		   for (int i = 0; i < word.length; i++) {
			   weight[i] = link.get(word[i]);
		   }
		   return weight;
	   }
	   /*
	    * 功能：求图的邻接矩阵
	    * 参数edges：边组
	    * 参数vertexs:顶点组
	    * */
	   public static int[][] Matrix(String[][] edges, String[] vertexs) {
		   int[][] matrix = new int[vertexs.length][vertexs.length];
		   int x, y;
		   for (int i = 0; i < vertexs.length; i++) { //初始化邻接矩阵
			   for (int j = 0; j < vertexs.length; j++) {
				   matrix[i][j] = Integer.MAX_VALUE;
			   }
		   }
		   for (int i = 0; i < edges.length; i++) {
			   x = Arrays.binarySearch(vertexs, edges[i][0]);
			   //利用二分查找查找节点对应序号
			   y = Arrays.binarySearch(vertexs, edges[i][1]);
			   matrix[x][y] = Integer.parseInt(edges[i][2]);
		   }
		   return matrix;
	   }
	   
	   public static void showDirectedGraph(String[][] Edgesweight) {
		      GraphViz gv = new GraphViz();
		      int n = Edgesweight.length;
		      gv.addln(gv.start_graph());
		      for (int i = 0; i < n; i++) {
		    	  gv.addln(Edgesweight[i][0] + "->" + Edgesweight[i][1]
		    		+ " [ label = \" " + Edgesweight[i][2] + "\" ] ;");
		      }
		      gv.addln(gv.end_graph());
		      String type = "png";
		      File out = new File("C:\\test\\out." + type);    // Windows
		      gv.writeGraphToFile(gv.getGraph(gv.getDotSource(), type), out);
		}

}
