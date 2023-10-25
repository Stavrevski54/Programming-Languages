public class excercise1 {
	public static void main(String[] args) {
		String word = "Computer";
		System.out.println("Original " + word);
		System.out.println("In small letters: " + word.toLowerCase());
		System.out.println("In capital letters: " + word.toUpperCase());
		System.out.println("Lenght:" + word.length());
		System.out.println("First letter: " + word.charAt(0));
		System.out.println("Lasst letter: " + word.charAt(word.length()-1));
		System.out.println("Middle letter: " + word.charAt(word.length()/2));
		boolean Com = word.startsWith("Com");
		System.out.println("The word starts with " + "Com: " + Com);
		boolean Ion = word.endsWith("ion");
		System.out.println("The word ends with " + "ion: " + Ion);
		String firstTwoLetters = "Co";
		System.out.println("The first two letters are: " + firstTwoLetters);
		System.out.println("Swapping 'e' with 'o' " + word.replace('e', 'o'));
		System.out.println("Swapping 'Comp' with 'Calcula': " + word.replaceAll("Comp", "Calcula"));
	}
}
