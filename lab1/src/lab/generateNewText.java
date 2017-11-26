package lab;

import java.io.*;
import java.util.*;

/*
 * 功能：根据桥接词生成新文本
 * */
public class generateNewText {
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
    * 功能：求两词中间的桥接词矩阵
    * 参数begin、end：待查询的两个单词
    * */
   public static String[] BridgeList(String begin, String end, String[][] Bridges) {
	   int count = 0;
	   String[] words = new String[Bridges.length];
	   for (int i = 0; i < Bridges.length; i++) {
		   String before = Bridges[i][0];
		   String after = Bridges[i][2];
		   if (before != null) {
			   if (before.equals(begin) && after.equals(end)) {
				   words[count] = Bridges[i][1];
				   count++;
			   }
		   } else {
			break;
		}
	   }
	   String[] BridgeWord = new String[count]; 
	   //将所有找到的桥接词存入要返回的字符串数组中
	   for (int i = 0; i < count; i++) {
		   BridgeWord[i] = words[i];
	   }
	   return BridgeWord;
   }
   
   /*
    * 功能：生成桥接词矩阵
    * 参数vertexs：未删除重复词的按文本内容顺序存储单词的字符串数组
    * */
public static String[][] BridgeCreate(String[] vertexs, String[][] Bridges) {
   String[][] temp = new String[vertexs.length - 2][3];  //生成包括重复桥的桥接词矩阵
   for (int i = 0; i < vertexs.length - 2; i++) {
	   temp[i][0] = vertexs[i];
	   temp[i][1] = vertexs[i + 1];
	   temp[i][2] = vertexs[i + 2];
   }
   String[] temp1 = new String[temp.length];       //删除重复桥
   for (int i = 0; i < temp.length; i++) {
	   temp1[i] = temp[i][0] + " " + temp[i][1] + " " + temp[i][2];
   }
   String[] temp2 = new String[temp.length];
   int count = 0;
   for (int i = 0; i < temp1.length; i++) {
	   String temp3 = temp1[i];
	   if (!Arrays.asList(temp2).contains(temp3)) {
		   temp2[count] = temp3;
		   count++;
	   }
   }
   String[] temp4 = new String[count];
   for (int i = 0; i < count; i++) {
	temp4[i] = temp2[i];
   }
   for (int i = 0; i < temp4.length; i++) {          //生成不包括重复桥的桥接词矩阵
	   String[] temp5 = temp4[i].split(" ");
	   Bridges[i][0] = temp5[0];
	   Bridges[i][1] = temp5[1];
	   Bridges[i][2] = temp5[2];
   }
   return Bridges;
}

/*
 * 功能：不带提示语的查询桥接词
 * 参数word1、word2：待查询的两个单词
 * */
public static String[] QueryBridge(String word1, String word2, String text){
	   String[] v = VerCreate(text);
	   String[][] e = EdgesCreate(text);
	   String[] words = text.split(" ");
	   String[][] Bridges = new String[200][3];
	   Bridges = BridgeCreate(words,Bridges);
	   if (Arrays.asList(v).contains(word1)
			   && !Arrays.asList(v).contains(word2)) {   //待查询的词在图中
		   String[] Bridge = BridgeList(word1,word2,Bridges);
		   return Bridge;
	   }
	   String[] Bridge = new String[0];
	   return Bridge;
}

   public static String generateNewText(String inputText, String text) {
	   inputText = inputText.replaceAll("[^a-zA-Z]+", " ");
	   String[] Text = inputText.split("\\s+");
	   String[] temp = new String[100];
	   int count = 0;
	   for (int i = 0; i < Text.length - 1; i++) {
		   temp[count] = Text[i];
		   count++;
		   String begin = Text[i].toLowerCase();
		   String end = Text[i + 1].toLowerCase();
		   String[] bridge = QueryBridge(begin, end,text);
		   if (bridge.length != 0) {
			   temp[count] = bridge[(int) (Math.random() * bridge.length)];
			   count++;
		   }
	   }
	   temp[count] = Text[Text.length - 1];
	   count++;
	   String NewText = new String();
	   for (int i = 0; i < count; i++) {
		   NewText = NewText + temp[i] + " ";
	   }
	   return NewText;
   }

}
