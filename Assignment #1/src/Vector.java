public class Vector extends Matrix //Vector class which extends Matrix Class
{
	public Vector(int c) // vector object returns of r(c) as 1 using super
	{
		super(1, c);
	}
	
	public Vector(int c, double[] linArr) //override vector object returns r(c) as 1 as well as 1d array linArr using super
	{
		super(1,c, linArr);
	}

	public double getElement(int c) //uses super to get the value of c and row 0 and returns the element
	{
		return super.getElement(0, c);
	}
}