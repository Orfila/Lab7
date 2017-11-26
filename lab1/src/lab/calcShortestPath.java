package lab;

import java.io.*;
import java.util.*;

/*
 * 功能：求两个单词间的最短路径
 * */
public class calcShortestPath {
	public static String[][] Bridges = new String[200][3];//桥接词矩阵
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
	   
	   
	   /*
	    * 功能：求两个单词间的最短路径
	    * 参数word1&word2：所要求的单词
	    * */
	   public static String calcShortestPath(String text,String word1, String word2){

		   int p1 , p2 , i , j ,k;
		   String NewText = new String();
		   p1 = 0;
		   p2 = 0;
		   String[] ver = VerCreate(text);
		   String[][] e = EdgesCreate(text);//边集集合
		   String[][] EdgeWeight = EdgeWeight(e);//计算边的权值
		   Arrays.sort(ver);
		   int[][] Matrix = Matrix(EdgeWeight,ver);//邻接矩阵
		   
		   p1 = Arrays.binarySearch(ver, word1);
		   p2 = Arrays.binarySearch(ver, word2);
		   if(p1 < 0 | p2 < 0){
			   NewText = "请输入正确的单词！";
			   return NewText;
		   }

		   int n = Matrix.length;
		   path = new int[n];
		   visited = new int[n];
		   int[] weight = new int[n];
		   
		   for(i = 0;i < n; i++)
			   weight[i] = Matrix[p1][i];
		   
			for (i = 0; i < n; i++) {
				if (weight[i] < Integer.MAX_VALUE) {
					path[i] = p1;
				}
			}
			
			for (i = 0; i < n; i++)
				visited[i] = 0;
			
			visited[p1] = 1;		
			weight[p1] = 0;
			
			for (i = 0; i < n; i++) {
				int min = Integer.MAX_VALUE;
				k = p1;
				for (j = 0; j < n; j++) {
					if (visited[j] == 0 && weight[j] < min) {
						min = weight[j];
						k = j;
					}
				}
				visited[k] = 1; //将顶点k加入
				for (j = 0; j < n; j++) { //以顶点k为中间值计算新的权值
					if (visited[j] == 0 && Matrix[k][j] != Integer.MAX_VALUE && weight[k] + Matrix[k][j] < weight[j]) {
						weight[j] = weight[k] + Matrix[k][j];
						path[j] = k;
					}
				}
			}
			
	        
			i = p2;		
			String[] str = new String[Matrix.length];
			j = 0;
			int l = 0;
			if (i == p1) {
				NewText = "请输入两个不同的单词，感谢！";
				return NewText;
			} else if (visited[i] == 0) {
				NewText = ver[p1] + " 和 " + ver[i] + " 不可达！";
				return NewText;			
			} else {
				k = i;
				while (k != p1) {
					str[j] = ver[k];
					j++;
					k = path[k];
				}
				str[j] = ver[k];
				l = j;
				NewText += ver[p1] + "和" + ver[i] + "最短路径为:\n";
				while (j != 0) {
					NewText += str[j] + "->";
					j--;
				}
				NewText += str[0] ;
			}
			NewText += " 路径长度为  ";
			NewText += l;
			return NewText;
		   	   
	   }

}
