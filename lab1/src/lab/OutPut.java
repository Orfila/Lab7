package lab;

import java.io.*;
import java.util.*;

public class OutPut {
	//public static String[][] Bridges = new String[200][3];//桥接词矩阵
	public static String[] v = new String[200];//顶点集合
	public static String[][] edges = new String[200][2];//有向边集
	public static int[][] matrix = new int[200][200]; //有向图的邻接矩阵
	public static String test; //输入的测试字符串
	public static int[] visited = new int[200];//求最短路径标记数组（函数复用）
	public static int[] path = new int[200];//求最短路径记录数组（函数复用） test
	
	public static void functionChoose(String args[]){
		   System.out.println("欢迎使用！");
		   Scanner tmp = new Scanner(System.in);
		   //导入文本
		   while (true) {
			   System.out.println("请先导入文本！");
			   String loc = new String();
			   String name = new String();
			   System.out.println("请输入文件地址：");
			   loc = tmp.nextLine();
			   System.out.println("请输入文件名称：");
			   name = tmp.nextLine();
			   test = lab.InputFile.InputFile(loc, name);
			   if (!test.equals("")) {
				   System.out.println("文件导入成功！");
				   break;
			   }
		   }
		   while (true) {
			   System.out.println("以下是功能列表：");
			   System.out.println("1.生成图像");
			   System.out.println("2.查桥接词");
			   System.out.println("3.生成新句");
			   System.out.println("4.最短路径(两个单词)");
			   System.out.println("5.随机游走");
			   System.out.println("0.退出程序");
			   System.out.println("====================");
			   System.out.println("请输入功能序号：");

			   int num = tmp.nextInt();
			   switch (num) {
			   case 1:
				   System.out.println("欢迎使用生成图像功能！");
				   showDirectedGraph s1 = new showDirectedGraph();
				   v = s1.VerCreate(test); //顶点集合
				   edges = s1.EdgesCreate(test); //边集集合
				   String[][] edgeweight = s1.EdgeWeight(edges); //计算边的权值
				   Arrays.sort(v);
				   matrix = s1.Matrix(edgeweight, v); //邻接矩阵
				   s1.showDirectedGraph(edgeweight);
				   System.out.println("图像生成成功！");
				   break;

			   case 2:
				   System.out.println("欢迎使用查询桥接词功能！");
				   System.out.println("请输入需要查询桥接词的两个单词(两个单词之间用换行符分隔）：");
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
				   System.out.println("欢迎使用生成新句子功能！");
				   System.out.println("请输入文本：");
				   String sentence = new String();
				   tmp.nextLine();
				   sentence = tmp.nextLine();
				   String[] newword = test.split(" ");
				   String NewText = lab.generateNewText.generateNewText(sentence, test);
				   System.out.println("生成的新文本为：");
				   System.out.println(NewText);
				   break;

			   case 4:
				   System.out.println("欢迎使用查询最短路径（两个单词）功能！");
				   System.out.println("请输入需要查询的单词：");
				   String word1 = new String();
				   String word2 = new String();
				   word1 = tmp.nextLine();
				   word2 = tmp.nextLine();
				   String res = new String();
				   res = lab.calcShortestPath.calcShortestPath(test,word1, word2);
				   System.out.println(res);
				   break;
			   case 5:
				   System.out.println("欢迎使用随机游走功能！");
				   System.out.println("请1继续随机游走！输入其他结束随机游走");
				   String tp2 = new String();
				   tmp.nextLine();
				   while (tmp.nextInt() == 1) {
						tp2 += lab.randomWalk.randomWalk();
						tp2 += "\t\n";
						lab.randomWalk.WriteIn("random.txt", tp2);
				   }
				   break;
			   case 0:
				   System.out.println("感谢使用，再见！");
				   System.exit(0);
				   break;
			   default:
				   tmp.nextLine(); //消除回车换行
				   System.out.println("请输入正确序号！");
				   break;
			   }
		   }
	   //tmp.close();
	   }

}
