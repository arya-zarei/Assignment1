public class MarkovChain //Class MarkovChain 
{
	private Vector stateVector; //private Vector stateVector
	private Matrix transitionMatrix; //private Matrix transitionMatrix
	
	public MarkovChain(Vector sVector, Matrix tMatrix) //MarkovChain object
	{
		stateVector = sVector; //Initializes stateVector with value of sVector
		transitionMatrix = tMatrix; // Initializes transitionMatrix with value of tMatrix
	}
	
	public boolean isValid() /** isValid checks if the transition matrix and stateVector are valid. First Comparing the number of columns between stateVector and the rows and columns of
	transitionMatrix. Second it checks the sum of stateVector to make sure it is about 1. Third is makes sure that in each row the sum of columns in transitionMatrix are about 1.**/
	{
		
		if (transitionMatrix.getNumRows() != transitionMatrix.getNumCols() || stateVector.getNumCols() != stateVector.getNumCols())
		{
			return false; // returns false if the number of columns and rows are not equal (they should be 2 as it is square matrix) or returns false is columns in stateVector are not equal
		}
		
		double sum = 0; //double sum variable for stateVector
		
		for (int i = 0; i < stateVector.getNumCols(); i++) // goes through each value of stateVector(1 row) and adds each value to sum 
		{
			sum += stateVector.getElement(i); //uses getElement from vector class to find value
		}
		
		if (sum < 0.99 || sum > 1.01) // if statement for sum is less than 0.99 or greater than 1.01 return false
		{
			return false;
		}
		
		double secondsum = 0; // second sum double for sum of transition matrix
		
		for (int j = 0; j < transitionMatrix.getNumRows(); j++) //nested loop to go through rows and columns of transition matrix
		{
			for (int k = 0; k < transitionMatrix.getNumCols(); k++)
			{
				secondsum += transitionMatrix.getElement(j, k); // add transition matrix value of columns to secondSum
			}
			if (secondsum < 0.99 || secondsum > 1.01) //if statement for sum of row 
			{
				return false; //if row less than 0.99 or greater than 1.01 return false
			}
			secondsum = 0; //secondSum = 0 to check the new row
		}
		return true; //if nothing false is returned in loops, true is returned for isValid() object
	} 
	
	public Matrix computeProbabilityMatrix(int numSteps) //computeProbabilityMatrix object 
	{
		if (isValid() == false) //checks if isValid is false first
		{
			return null; //if isValid() if false returns object as empty
		}
		
		Matrix tempTransition = transitionMatrix; //tempTransition is temporary matrix that equals transitionMatrix
		int num2 = numSteps -1; // numSteps - 1 for loop to compute probability
		
		for (int i = 0; i < num2; i++) //for loop goes through numSteps - 1 times
		{
			tempTransition = tempTransition.multiply(transitionMatrix); /** temporary matrix equals transitionMatrix multiplied by itself using Matrix multiply (Matrix other) 
			object from Matrix class for each run in the loop for number of steps the transitionMatrix multiplies itself by itself another time **/
		}
		
		
		return stateVector.multiply(tempTransition); //returns stateVector multiplied by transitionMatrix's temporary matrix (to keep transitionMtrix as the original value)
	}
}