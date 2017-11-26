package lab;

import java.io.*;
import java.util.*;

/*
 * ���ܣ����ò��Graphvizչʾ����ͼ
 * */
public class showDirectedGraph {
	//public static String[][] Bridges = new String[200][3];//�ŽӴʾ���
	public static String[] v = new String[200];//���㼯��
	public static String[][] edges = new String[200][2];//����߼�
	public static int[][] matrix = new int[200][200]; //����ͼ���ڽӾ���
	public static String test; //����Ĳ����ַ���
	public static int[] visited = new int[200];//�����·��������飨�������ã�
	public static int[] path = new int[200];//�����·����¼���飨�������ã� test
	
	 /*
	   * ���ܣ���������
	   * ����txt������ú��ı�
	   * */
	  public static String[][] EdgesCreate(String txt) {
		  String[] ntxt = txt.split(" "); //�ַ���ת��Ϊ�ַ�����
		  int len = ntxt.length;
		  String[][] Edges = new String[len-1][2]; // NOPMD by dell on 17-10-21 ����3:20
		  for (int i = 0; i < len - 1; i++) {
			  Edges[i][0] = ntxt[i];
			  Edges[i][1] = ntxt[i + 1];
		  }
		  return Edges;
	  }
	   /*
	   * ���ܣ���������
	   * ����txt������ú���ı�
	   * */
	  public static String[] VerCreate(String txt) {
		   String[] ntxt = txt.split(" "); //�ַ���ת��Ϊ�ַ�����
		   String[] tmpver = new String[ntxt.length];
		   int con = 0;
		   for (int i = 0; i < ntxt.length; i++) {
			   String tmp = ntxt[i];
			   if (!Arrays.asList(tmpver).contains(tmp)) {    //ɾ���ظ���
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
	    * ���ܣ�����ߵ�Ȩֵ����ɾ���ظ���
	    * ����Edges������õı���
	    * */
	   public static String[][] EdgeWeight(String[][] Edges) {
		   String[] tmpedge = new String[Edges.length];         //���ߵ����������Ϊһ���ַ���
		   for (int i = 0; i < Edges.length; i++) {
			   tmpedge[i] = Edges[i][0] + " " + Edges[i][1];
		   }
		   String[] tmpedge1 = new String[tmpedge.length]; //ɾ���ظ����ַ�������ɾ���ظ��ߣ�
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
		   int[] weight = CountWeight(tmpedge, tmp); //����ߵ�Ȩֵ
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
	    * ���ܣ�����ߵ�Ȩֵ
	    * ����Edges����һ���ߵ������ڵ��Ϊһ���ַ������ַ������飨�����ظ��ߣ�
	    * ����word����һ���ߵ������ڵ��Ϊһ���ַ������ַ������飨�������ظ��ߣ�
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
	    * ���ܣ���ͼ���ڽӾ���
	    * ����edges������
	    * ����vertexs:������
	    * */
	   public static int[][] Matrix(String[][] edges, String[] vertexs) {
		   int[][] matrix = new int[vertexs.length][vertexs.length];
		   int x, y;
		   for (int i = 0; i < vertexs.length; i++) { //��ʼ���ڽӾ���
			   for (int j = 0; j < vertexs.length; j++) {
				   matrix[i][j] = Integer.MAX_VALUE;
			   }
		   }
		   for (int i = 0; i < edges.length; i++) {
			   x = Arrays.binarySearch(vertexs, edges[i][0]);
			   //���ö��ֲ��Ҳ��ҽڵ��Ӧ���
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
