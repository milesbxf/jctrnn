package me.mb.jctrnn.core;

/**
 * Contains mathematical utility functions.
 * 
 * @author Miles Bryant
 *
 */
public class MathUtils {

	/**
	 * Standard sigmoid function (1/(1+e^-x)). 
	 * If x is NaN, result is NaN.
	 * If x is +infinity, result = 0.
	 * If x is zero, result = 0.5.
	 * If x is -infinity, result = 1.
	 * 
	 * @param x
	 * @return float sigmoid of x.
	 */
	public static float sigmoid(float x) {
		float result = 1.0f / (1 + (float) Math.exp(-x));
		if(Float.isNaN( result )) {
			throw new AssertionError();
		}
		return result;
	}

}
