import java.util.Random;

/*
 *  Q-Learning Algorithm 
 *  Author: Paulo Fernandes
 *  
 * */
public class Q_algorithm {
	
public static int[][] algorithm(int[][] Q, int[][] R, double gama, int state_goal)
{
	
	//Select a random initial state.
	//row and column are the same for Q and R
	//row = states
	//column = actions
	int row = Q.length - 1;
	int column = Q[row].length - 1;
	
	Random r = new Random();
	int initial_state = r.nextInt(row);
	
	

	//Do While the goal state hasn't been reached.	
	boolean reached_goal = false;
	
	//yep, it is a TRUE while, VIDALOKA
	while(true)
	{
		  
		
		
		//Select one among all POSSIBLE actions for the current state.
		int[] array_of_possible_actions = new int[column +1]; 
		int max_of_possible_actions = 0;
		
		for (int i = 0; i <= column; i++) 
		{
			if (R[initial_state][i] != -1)
			{
				//if R[initial_state][i] != -1, there`s an action, save this action in a matrix (almost like a queue)
				array_of_possible_actions[max_of_possible_actions] = /*R[initial_state][i];*/ i;
				++max_of_possible_actions;
			}
		}
		
		
		
		//If there`s any possible actions
		if (max_of_possible_actions > 0)
		{
			//Here, array_of_possible_actions has all the possible actions of initial_state
			//Lets take one of it
			int current_state = array_of_possible_actions[r.nextInt(max_of_possible_actions)];
			
			//Using this possible action, consider going to the next state.
			//Lets find all the possible states we could go from the next state
			//The random_action is the new state for now
			int[] new_array_of_possible_actions = new int[column+1];
			int new_max_of_possible_actions = 0;
			for (int i = 0; i <= column; i++)
			{
				if (R[current_state][i] != -1)
				{
					//if R[random_action][i] != -1, there`s an action, save this action in a matrix (almost like a queue)
					new_array_of_possible_actions[new_max_of_possible_actions] = /*R[current_state][i];*/ i;
					++new_max_of_possible_actions;
				}
			}
			
			//TODO this is probably too cautelous but every vertice should have at least one edge
			if (new_max_of_possible_actions > 0) 
			{
				//Here, we have all the possible actions, if the actual action were the state
				//Get maximum Q value for this next state based on all possible actions.
				int max = 0; 
				for (int i = 0; i < new_max_of_possible_actions; i++) 
				{
					int next_action = new_array_of_possible_actions[i];
					if (Q[current_state][next_action] > max)
					{
						max = Q[current_state][next_action];
					}
				}
				//Here, we are supposed to have the max(Q)
				//Compute: Q(state, action) = R(state, action) + Gamma * Max[Q(next state, all actions)]
				int  q_value = (int) ( R[initial_state][current_state] + gama*max);
				Q[initial_state][current_state] = q_value;
				
				//base case 
				if(initial_state == state_goal )
				{
					return Q;
				}
				
				//Set the next state as the current state.
				initial_state = current_state;
				
				
			}
		}
	}
}


	public static void main(String[] args)
	{
		
		/*	  
		 *	algorithm(Q, R, gama, goal) 
		 * */
		
		
		final double gamma = 0.8; 
		final int Q_SIZE = 6;
		
		//TODO Set Matrix R
		   int R[][] = new int[][] {
			 {-1, -1, -1, -1, 0, -1}, 
             {-1, -1, -1, 0, -1, 100}, 
             {-1, -1, -1, 0, -1, -1}, 
             {-1, 0, 0, -1, 0, -1}, 
             {0, -1, -1, 0, -1, 100}, 
             {-1, 0, -1, -1, 0, 100}};
		
		//TODO Initialize matrix Q to zero.
		int Q[][] = new int[Q_SIZE][Q_SIZE];
		int goal = 5;
		
		for (int i = 0; i < 1000; i++) {
		
			int[][] result = algorithm(Q, R, gamma, goal);
			
		      for(int j = 0; j < Q_SIZE; j++)
		      {
		          for(int k = 0; k < Q_SIZE; k++)
		          {
		              System.out.print(result[j][k] + ",\t");
		          }
		          System.out.print("\n");
		      }
		      System.out.print("\n");
		}
	}
}
