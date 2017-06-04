
public class Comparator {

	public static final String atilde = "atilde";
	public static final String etilde = "etilde";
	public static final String itilde = "itilde";
	public static final String otilde = "otilde";
	public static final String utilde = "utilde";
	public static final String enhe = "enhe";
	
	public static final String[] atildeChars = new String[] {"á", "\\\\u00E1", "\\\\u00e1", "Á", "\\\\u00C1", "\\\\u00c1"};
	public static final String[] etildeChars = new String[] {"é", "\\\\u00E9", "\\\\u00e9", "É", "\\\\u00C9", "\\\\u00c9"};
	public static final String[] itildeChars = new String[] {"í", "\\\\u00ED", "\\\\u00Ed", "\\\\u00eD", "\\\\u00ed", "Í", "\\\\u00CD", "\\\\u00Cd", "\\\\u00cD", "\\\\u00cd"};
	public static final String[] otildeChars = new String[] {"ó", "\\\\u00F3", "\\\\u00f3", "Ó", "\\\\u00D3", "\\\\u00d3"};
	public static final String[] utildeChars = new String[] {"ú", "\\\\u00FA", "\\\\u00Fa", "\\\\u00fA", "\\\\u00fa", "Ú", "\\\\u00DA", "\\\\u00Da", "\\\\u00dA", "\\\\u00da"};
	public static final String[] enheChars = new String[] {"ñ", "\\\\u00F1", "\\\\u00f1", "Ñ", "\\\\u00D1", "\\\\u00d1"};
	
	public static boolean equals(String s1, String s2){
		return normalize(s1).equals(normalize(s2));
	}
	
	public static String normalize(String string){
		string = string.toLowerCase();
		for(String str : atildeChars){
			string = string.replaceAll(str, atilde);
		}
		for(String str : etildeChars){
			string = string.replaceAll(str, etilde);
		}
		for(String str : itildeChars){
			string = string.replaceAll(str, itilde);
		}
		for(String str : otildeChars){
			string = string.replaceAll(str, otilde);
		}
		for(String str : utildeChars){
			string = string.replaceAll(str, utilde);
		}
		for(String str : enheChars){
			string = string.replaceAll(str, enhe);
		}
		return string.replaceAll("\\s", "");
	}
}
