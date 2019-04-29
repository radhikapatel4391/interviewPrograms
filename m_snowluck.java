import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.lang.Math;

public class m_snowluck {
	static HashMap<Integer,ArrayList<Integer>> trees;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int count = 0;
		while (sc.hasNextInt()) {
			//Read the data......
			count++;
			int n = sc.nextInt();
			if(n==0)
				return;
			trees = new HashMap<Integer,ArrayList<Integer>>(n-1);
			for(int i=1;i<n;i++)
			{
				trees.put(i,new ArrayList<Integer>());
				for(int j=0;j<4;j++)
				{
					trees.get(i).add(sc.nextInt());
				}
			}
			int[][] mtable = new int[n+1][n-1];
			for(int r=0;r<mtable.length;r++)
				for(int c=0;c<mtable[0].length;c++)
					mtable[r][c]=0; 
			//for h=0 to h=n find the min distance.	.......		
			System.out.print("Case "+count+":");			
			for(int ng=n;ng>-1;ng--)
			{
				 findMinDistance(1,ng,mtable,n);			
			}
			//print answers....
			for (int k=0;k<n+1;k++)
			{	
				int ans = mtable[k][0];
				if (ans >= 5000)
					System.out.print(" " + "X");
				else
					System.out.print(" " + ans);					

			}
			System.out.println();
			
		}
		sc.close();
	}
	public static int findMinDistance(int start,int g,int[][] mtable,int dest)
	{
//Task 0 Base cases........
		//reach destination with low g so not possible...
		if(g<0 || (start==dest && g!=0))//here 5000 means infinite value no way exist 
			return 5000;
		if(start==dest && g==0) //reach destination with zero green(H) g.
			return 0;
				//mtable[x][y]==5000 means infinite not way exist X
				//mtable[x][y]==0 don't know initial Value
				//mtable[x][y]==-1 in current queue of dfs...help to avoid cycles..
		
//Task 1 check in memory table...		
		if(mtable[g][start-1]!=0 && mtable[g][start-1]!=-1) //value exist in table so return it.
			return mtable[g][start-1];
					
		//-1 means currently in queue, avoid loop			
		if(mtable[g][start-1]==-1)
			return 5000;
		
//Task 2 Find the solution					
		mtable[g][start-1] = -1; //set it in queue
		
		ArrayList<Integer> posi = trees.get(start);
		int greenD = findMinDistance(posi.get(2),g-1,mtable,dest); //green line
		int redD = findMinDistance(posi.get(0),g,mtable,dest);     //red line
		int rd = posi.get(1);
		int gd = posi.get(3);
		int m = Math.min(greenD+gd,redD+rd);
		
//Task 3 set in memory table		
	    mtable[g][start-1] =m;
		//System.out.println("at: "+(start)+" ,ng "+g+" , "+posi+" , "+(redD)+" , "+(greenD));
		return mtable[g][start-1];
	}

}