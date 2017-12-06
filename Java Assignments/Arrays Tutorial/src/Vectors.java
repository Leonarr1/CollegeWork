import java.util.Arrays;

public class Vectors 
{
	static double[] vector = {4.00, 4.00, 2.00};
	static double[] vector2 = {2.00, 3.00, 5.00};
	
	public static void main(String[] args)
	{
		System.out.println("Magnitude of the vector: " + magnitude(vector));
		System.out.println("Dot Product of the vector: " + dotProduct(vector));
		System.out.println("Vector Sum of the two vectors: " + Arrays.toString(vectorSum(vector, vector2)));
	}
	public static double magnitude(double[] vector)
	{
	int index =0;
	double sum = 0;
	double magnitude = 0;
	while(index < vector.length)
		{
			magnitude = (vector[index])*(vector[index]);
			sum = sum + magnitude;
			index++;
		}
		return Math.sqrt(sum);
	}
	public static double dotProduct(double[] vector)
	{
		int index = 0;
		double dotProduct = 1;
		while (index < vector.length)
		{
			dotProduct = dotProduct * vector[index];
			index++;
		}
		return dotProduct;
	}
	public static double[] vectorSum(double[] vector1, double[] vector2)
	{
		int index = 0;
		double[] vectorSum = {0, 0, 0};
		while (index < vector.length)
		{
			vectorSum[index] = vector1[index] + vector2[index];
			index++;
		}	
		return vectorSum;
	}
}
