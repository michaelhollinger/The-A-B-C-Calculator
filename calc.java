/* Michael Hollinger - 3392988
COP 3503C - Computer Science II
3/4/2018
*/

import java.util.*;
import java.io.*;

public class calc
{
	public static ArrayList<problem> cases = new ArrayList<>();

	//class representing any arbitrary case.
	public static class problem
	{
		int addA, mulB, divC, target;

		problem(int a, int b, int c, int d)
		{
			addA = a;
			mulB = b;
			divC = c;
			target = d;
		}
	}

	//stores a given index of the queue during BFS.
	public static class result
	{
		int num, steps;

		result(int a, int b)
		{
			num = a;
			steps = b;
		}
	}

	public static void main(String[] args)
	{
		Scanner stdin = new Scanner(System.in);
		int numCases = stdin.nextInt();
		stdin.nextLine();

		while(stdin.hasNextLine())
		{
			int a = stdin.nextInt();
			int b = stdin.nextInt();
			int c = stdin.nextInt();
			int d = stdin.nextInt();
			cases.add(new problem(a, b, c, d));
		}
		
		for(int i = 0; i < numCases; i++)
		{
			solve(cases.get(i));
		}
	}

	//BFS implementation
	public static void solve(problem curProblem)
		{

			//initialize the queue. Initial state is always 0.
			LinkedList<result> queue = new LinkedList<result>();
			queue.add(new result(0, 0));
			
			//prevents duplicates.
			boolean[] seen = new boolean[1000000];
			Arrays.fill(seen, false);
			
			//flag to denote whether or not a solution exists.
			boolean found = false;


			while(queue.size() != 0)
			{
				//returns null if head of queue is empty so that no exception is thrown.
				result curResult = queue.poll();
				int curNum = curResult.num;

				//if the solution exists, assert the flag and print out its steps, respectively.
				if(curNum == curProblem.target)
				{
					found = true;
					System.out.println(curResult.steps);
				}

				//initialize the three possible states branching from a given node.
				int addNum = (curNum + curProblem.addA) % 1000000;
				int mulNum = (curNum * curProblem.mulB) % 1000000;
				int divNum = (curNum / curProblem.divC) % 1000000;

				//if any of these conditions are met, mark the int as seen and add it to the queue.
				if(!seen[addNum])
				{
					seen[addNum] = true;
					queue.add(new result(addNum, curResult.steps + 1));
				}

				if(!seen[mulNum])
				{
					seen[mulNum] = true;
					queue.add(new result(mulNum, curResult.steps + 1));
				}

				if(!seen[divNum])
				{
					seen[divNum] = true;
					queue.add(new result(divNum, curResult.steps + 1));
				}
			}

			//if no result exists.
			if(queue.size() == 0 && !found)
				{
					System.out.println("-1");
				}
		}	
}