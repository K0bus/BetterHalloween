package fr.k0bus.betterhalloween;

public class ExceptionClass {
	public static boolean isNumeric(String strNum) {
	    try {
	        @SuppressWarnings("unused")
			double d = Double.parseDouble(strNum);
	    } catch (NumberFormatException nfe) {
	        return false;
		} catch(NullPointerException npe)
		{
			return false;
		}
	    return true;
	}
}
