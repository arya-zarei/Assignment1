public class Matrix //Matrix Class
{
	private int numRows; //private numRows variable
	private int numCols; //private numCols variable
	private double[][] data; //double array data variable
	
	public Matrix(int r, int c) //First matrix object
	{ /** Initializes the variables and gives the value r to the first spot in the array for rows and gives
		c to the second spot in the array for columns**/    
		numRows = r;
		numCols = c;
		data = new double [r][c];
	}
	
	public Matrix(int r, int c, double[] linArr) //second Matrix object, overrides and included linArr 1d array with all the values to create the matrix
	{/** values of numRows, numCols and data must be re-initialized**/
		numRows = r;
		numCols = c;
		data = new double [r][c];
		
		/**for loop to go through the data 2d array with the specified
		rows and columns. Uses count to go through all variables of linArr starting at index 0 and adds
		index 0 at row 0 column 0 and index 1 and row 0 column 1 till the column is finished and resets column index to go through the next row**/
		
		int count = 0;
		for (int i = 0; i < r; i++)
		{
			for(int j= 0; j < c; j++)
			{	
				data[i][j] = linArr[count];
				count++;
			}
		} 
	}
	
	public int getNumRows() // returns the number of rows, getNumRows is used for MarkovChain and other objects throughout Matrix
	{ 
		return numRows;
	}
	
	public int getNumCols() // returns the number of columns, getNumCols is used for MarkovChain and other objects throughout Matrix
	{ 
		return numCols;
	}
	
	public double[][] getData() // returns the values of the data double 2d array
	{
		return data;
	}
	
	public double getElement(int r, int c) //returns the value of data at the specific row and column and is used in MarkovChain
	{
		return data[r][c];
	}
	
	public void setElement(int r, int c, double value) // adds the value of double value to data array
	{
		data[r][c] = value;
	}
	
	public void transpose() /** Transpose rotates the matrix by assigning the rows and columns of the original matrix to the columns and rows of a new matrix**/
	{
	  double [][] transposeData = new double [numCols][numRows]; //transposeData is the temporary 2d array given the number of rows and columns and assigned the transposed matrix
		
	  for(int i = 0; i < numRows; i++) //nested for loop to go through each value of array matrix
      {
         for(int j = 0; j < numCols; j++)
         {
        	 transposeData [j][i] = data[i][j]; //transposedData at column, row is assigned the value of data at row column as the two indexes are swapped, transposing the matrix.
         }
      }
	  data = transposeData; // assigns the value of the temporary transposed matrix to the 2d data array, transposing the data matrix
	  
	  /** uses three variables to swap the values of numRows and numCols **/
	  int tempRows = numRows; //assigning numRows to tempRows as the third variable
	  numRows = numCols; //	assigns numRows to numCols for the transposing
	  numCols = tempRows; // Assigns numRows value to numCols for transposing
	}

	public Matrix multiply(double scalar) //Matrix multiply: multiplies the 2d array matrix by a double scalar (each value in matrix gets individually multiplied by the same number)
	{
		Matrix matrx1 = new Matrix(numRows, numCols); // new matrix value matrx1 
		double[][] tempData = new double [numRows][numCols]; // temporary 2d array for multiplying storage
		
		for(int i = 0; i < numRows ; i++) //nested for loop to iterate through array and multiply each value
		{
			for(int j = 0; j < numCols; j++)
			{
				tempData[i][j] = (data[i][j] * scalar); // multiplies each value in the 2d data matrix by the scalar and stores it in the tempData (temporary 2d array)
			}
		}
		matrx1.data = tempData; //Assigns the values in tempData to the new matrx1 value .data and returns matrx1
		return matrx1;
	}
	
	public Matrix multiply(Matrix other) // new override matrix object that now multiplies two matrixes together
	{
		if (this.numCols != other.numRows) //if number of columns and rows of the two matrixes are not equal,operation can not be done and object is returned as empty
		{
			return null;
		}
		else
		{
			Matrix multiplyMtrx = new Matrix(this.numRows, other.numCols); // new matrix type multiplyMtrx assigned with the number of rows and columns
			
			for (int i = 0; i < multiplyMtrx.numRows; i++) //nested loop to go through each index in the matrix array with a third for loop to multiply the values of the two arrays together
			{
				for (int j = 0; j < multiplyMtrx.numCols; j ++)
				{
					for(int k = 0; k < numCols; k++)
					{
						multiplyMtrx.data [i][j] += this.data[i][k] * other.data[k][j]; // this array index is multiplied to other array index and stored in the new multiplyMtrx.data 
					}
				}
			}
			return multiplyMtrx; // after the for loops have ran the new matrix of the multiplied matrixes is returned
		}
	}
	
	public String toString() // toString object is used to return the 2d array in the form of a matrix
	{
		if ((numRows == 0) && (numCols == 0)) // if array is empty returns "Empty matrix"
		{
			return "Empty matrix";
		}
		
		else
		{
			double decimal; //double decimal variable to store variable in array one at a time
			String S = ""; //String S that will print out matrix
			
			for (int i = 0; i < numRows; i++) //nested loop to add each number in array to the matrix
			{
				for (int j = 0; j < numCols; j++)
				{
					decimal = data[i][j]; //adds element in array as a double 
					S += String.format("%8.3f", decimal); /** turns number into the string with 3 decimal places and creates an 8 space formatting for the element so all 
					numbers are spaced out evenly 
					from the back: 0.001   1.030
					               2.000   3.100 **/
				}
				S += ('\n'); //Creates a new line once the column is done for the new row
			}
			return S; //returns the string S that holds the matrix
		}	
	}
	
}