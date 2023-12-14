
import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author Devesh
 * Class that explores a way to the end of the array
 * two methods used the  main method and the exploreRecursive method
 * no datafields, constructors, or member variables;
 * The class contains a static variable which is used to 
 * keep track of the number of solutions through the recursive calls and backtracking. 
 */

public class WayFinder 
{
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Please provide an array of non-negative integers as command line arguments.");
            System.exit(1);
        }

        int[] puzzle = new int[args.length];
        for (int i = 0; i < args.length; i++) {
            try {
                puzzle[i] = Integer.parseInt(args[i]);
                if (puzzle[i] < 0 || puzzle[i] > 99) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                System.err.println("Invalid input. All values must be non-negative integers in the range of 0 to 99 inclusive.");
                System.exit(1);
            }
        }

        if (puzzle[puzzle.length - 1] != 0) {
            System.err.println("The last value in the array must be zero.");
            System.exit(1);
        }
        List<List<String>> solutions = new ArrayList<>();
        findSolutions(puzzle, 0, new ArrayList<>(), solutions, new boolean[puzzle.length]);

        if (solutions.isEmpty()) {
            System.out.println("No solutions found.");
        } else {
            for (List<String> solution : solutions) {
                System.out.println(formatSolutions(puzzle,solution));
            }
            System.out.println("There are " + solutions.size() + " ways through the puzzle.");
        }
    }
    private static void findSolutions(int[] puzzle, int index, List<String> currentPath, List<List<String>> solutions, boolean[] visited) {
       if(index==puzzle.length-1) 
       {
    	   solutions.add(new ArrayList<>(currentPath));
    	   return;
       }
       int distance=puzzle[index];
       if(distance>0) 
       {
    	   int rightIndex=distance+index;
    	   if(rightIndex<puzzle.length && !visited[rightIndex]) 
    	   {
    		   visited[rightIndex]=true;
    		   currentPath.add(index+"R");
    		   findSolutions(puzzle,rightIndex,currentPath,solutions,visited);
    		   visited[rightIndex]=false;
    		   currentPath.remove(currentPath.size()-1);
    	   }
    	   int leftIndex=index-distance;
    	   if(leftIndex>=0 && !visited[leftIndex] ) 
    	   {
    		   visited[leftIndex]=true;
    		   currentPath.add(index+"L");
    		   findSolutions(puzzle,leftIndex,currentPath,solutions,visited);
    		   visited[leftIndex]=false;
    		   currentPath.remove(currentPath.size()-1);
    	   }
       }
 
    }
    private static String formatSolutions(int [] puzzle, List<String> solution) 
    {
    	StringBuilder ans= new StringBuilder();
    	int index;
    	int flag=0;
    	for(int i=0;i<solution.size();i++) 
    	{
    		ans.append("[ ");
    		if(solution.get(i).contains("R")) 
    		{
    			index=Integer.parseInt(solution.get(i).split("R")[0]);
    			flag=1;
    		}else 
    		{
    			index=Integer.parseInt(solution.get(i).split("L")[0]);
    			flag=-1;
    		}
    		for(int j=0;j<puzzle.length-1;j++) 
    		{
    			if(j==index) 
    			{
    				if(flag==1) 
    				{
    					ans.append(puzzle[j]).append("R, ");
    				}else 
    				{
    					ans.append(puzzle[j]).append("L, ");
    				}
    				
    			}
    			ans.append(puzzle[j]).append(" , ");
    		}
    		ans.append("0 ]\n");
    	}
    	return ans.toString();
    }
   


	}
	



