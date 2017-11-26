package lab;

import java.io.*;
import java.util.*;

/*
 * ���ܣ������ŽӴ��������ı�
 * */
public class generateNewText {
	public static String[][] Bridges = new String[200][3];//�ŽӴʾ���
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
	   

   
   /*
    * ���ܣ��������м���ŽӴʾ���
    * ����begin��end������ѯ����������
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
	   //�������ҵ����ŽӴʴ���Ҫ���ص��ַ���������
	   for (int i = 0; i < count; i++) {
		   BridgeWord[i] = words[i];
	   }
	   return BridgeWord;
   }
   
   /*
    * ���ܣ������ŽӴʾ���
    * ����vertexs��δɾ���ظ��ʵİ��ı�����˳��洢���ʵ��ַ�������
    * */
public static String[][] BridgeCreate(String[] vertexs, String[][] Bridges) {
   String[][] temp = new String[vertexs.length - 2][3];  //���ɰ����ظ��ŵ��ŽӴʾ���
   for (int i = 0; i < vertexs.length - 2; i++) {
	   temp[i][0] = vertexs[i];
	   temp[i][1] = vertexs[i + 1];
	   temp[i][2] = vertexs[i + 2];
   }
   String[] temp1 = new String[temp.length];       //ɾ���ظ���
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
   for (int i = 0; i < temp4.length; i++) {          //���ɲ������ظ��ŵ��ŽӴʾ���
	   String[] temp5 = temp4[i].split(" ");
	   Bridges[i][0] = temp5[0];
	   Bridges[i][1] = temp5[1];
	   Bridges[i][2] = temp5[2];
   }
   return Bridges;
}

/*
 * ���ܣ�������ʾ��Ĳ�ѯ�ŽӴ�
 * ����word1��word2������ѯ����������
 * */
public static String[] QueryBridge(String word1, String word2, String text){
	   String[] v = VerCreate(text);
	   String[][] e = EdgesCreate(text);
	   String[] words = text.split(" ");
	   String[][] Bridges = new String[200][3];
	   Bridges = BridgeCreate(words,Bridges);
	   if (Arrays.asList(v).contains(word1)
			   && !Arrays.asList(v).contains(word2)) {   //����ѯ�Ĵ���ͼ��
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
