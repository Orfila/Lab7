package lab;

import java.io.*;
import java.util.*;

public class OutPut {
	//public static String[][] Bridges = new String[200][3];//�ŽӴʾ���
	public static String[] v = new String[200];//���㼯��
	public static String[][] edges = new String[200][2];//����߼�
	public static int[][] matrix = new int[200][200]; //����ͼ���ڽӾ���
	public static String test; //����Ĳ����ַ���
	public static int[] visited = new int[200];//�����·��������飨�������ã�
	public static int[] path = new int[200];//�����·����¼���飨�������ã� test
	
	public static void functionChoose(String args[]){
		   System.out.println("��ӭʹ�ã�");
		   Scanner tmp = new Scanner(System.in);
		   //�����ı�
		   while (true) {
			   System.out.println("���ȵ����ı���");
			   String loc = new String();
			   String name = new String();
			   System.out.println("�������ļ���ַ��");
			   loc = tmp.nextLine();
			   System.out.println("�������ļ����ƣ�");
			   name = tmp.nextLine();
			   test = lab.InputFile.InputFile(loc, name);
			   if (!test.equals("")) {
				   System.out.println("�ļ�����ɹ���");
				   break;
			   }
		   }
		   while (true) {
			   System.out.println("�����ǹ����б�");
			   System.out.println("1.����ͼ��");
			   System.out.println("2.���ŽӴ�");
			   System.out.println("3.�����¾�");
			   System.out.println("4.���·��(��������)");
			   System.out.println("5.�������");
			   System.out.println("0.�˳�����");
			   System.out.println("====================");
			   System.out.println("�����빦����ţ�");

			   int num = tmp.nextInt();
			   switch (num) {
			   case 1:
				   System.out.println("��ӭʹ������ͼ���ܣ�");
				   showDirectedGraph s1 = new showDirectedGraph();
				   v = s1.VerCreate(test); //���㼯��
				   edges = s1.EdgesCreate(test); //�߼�����
				   String[][] edgeweight = s1.EdgeWeight(edges); //����ߵ�Ȩֵ
				   Arrays.sort(v);
				   matrix = s1.Matrix(edgeweight, v); //�ڽӾ���
				   s1.showDirectedGraph(edgeweight);
				   System.out.println("ͼ�����ɳɹ���");
				   break;

			   case 2:
				   System.out.println("��ӭʹ�ò�ѯ�ŽӴʹ��ܣ�");
				   System.out.println("��������Ҫ��ѯ�ŽӴʵ���������(��������֮���û��з��ָ�����");
				   String begin = new String();
				   String end = new String();
				   tmp.nextLine();
				   begin = tmp.nextLine();
				   end = tmp.nextLine();
				   String bridge = lab.queryBridgeWords.queryBridgeWords(begin, end, test);
				   if (bridge.equals("")) {
					   System.out.println("No bridge words from "
						+ "\"" + begin + "\"" + " to "
						+ "\"" + end + "\"" + "!");
				   }
				   else if (!bridge.equals("") && !bridge.equals(" ")) {
					   System.out.print("The bridge words from "
						+ "\"" + begin + "\"" + " to "
						+ "\"" + end + "\"" + " are:");
					   String[] bridgeWords = bridge.split(" ");
					   if (bridgeWords.length == 1) {
					   System.out.println(bridgeWords[0] + ".");
					   } else if (bridgeWords.length == 2) {
						   System.out.println(bridgeWords[0]
						+ " and " + bridgeWords[1] + ".");
					   } else {
						   for(int i = 0; i < bridgeWords.length-1; i++){
					   System.out.print(bridgeWords[i] + ",");
						   }
					   System.out.println("and "
						 + bridgeWords[bridgeWords.length-1] + ".");
					   }
				   }
				   break;
			   case 3:
				   System.out.println("��ӭʹ�������¾��ӹ��ܣ�");
				   System.out.println("�������ı���");
				   String sentence = new String();
				   tmp.nextLine();
				   sentence = tmp.nextLine();
				   String[] newword = test.split(" ");
				   String NewText = lab.generateNewText.generateNewText(sentence, test);
				   System.out.println("���ɵ����ı�Ϊ��");
				   System.out.println(NewText);
				   break;

			   case 4:
				   System.out.println("��ӭʹ�ò�ѯ���·�����������ʣ����ܣ�");
				   System.out.println("��������Ҫ��ѯ�ĵ��ʣ�");
				   String word1 = new String();
				   String word2 = new String();
				   word1 = tmp.nextLine();
				   word2 = tmp.nextLine();
				   String res = new String();
				   res = lab.calcShortestPath.calcShortestPath(test,word1, word2);
				   System.out.println(res);
				   break;
			   case 5:
				   System.out.println("��ӭʹ��������߹��ܣ�");
				   System.out.println("��1����������ߣ��������������������");
				   String tp2 = new String();
				   tmp.nextLine();
				   while (tmp.nextInt() == 1) {
						tp2 += lab.randomWalk.randomWalk();
						tp2 += "\t\n";
						lab.randomWalk.WriteIn("random.txt", tp2);
				   }
				   break;
			   case 0:
				   System.out.println("��лʹ�ã��ټ���");
				   System.exit(0);
				   break;
			   default:
				   tmp.nextLine(); //�����س�����
				   System.out.println("��������ȷ��ţ�");
				   break;
			   }
		   }
	   //tmp.close();
	   }

}
