
public class BreakeCaeserCipher {

	public static void main(String[] args) {

		String text = "Lipiri";
		char[] alphabetLowLetters = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
				'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
		char[] alphabetBigLetters = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
				'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
		char mostUsedSymbol = FindMostUsedSymbol(text);
		int indexOfMostUsedSymbol = FindSymbolIndex(mostUsedSymbol, alphabetLowLetters);

		String originalText = "";

		char[] mostCommon = { 'e', 't', 'h' };

		for (int i = 0; i < mostCommon.length; i++) {
			int step = FindStep(mostCommon[i], indexOfMostUsedSymbol, alphabetLowLetters);

			if (step < 0) {
				originalText = BrakeCipherNegative(text, step, alphabetLowLetters, alphabetBigLetters);
			} else {
				originalText = BrakeCipherPositive(text, step, alphabetLowLetters, alphabetBigLetters);
			}
			System.out.println(originalText);
		}

	}

	static char FindMostUsedSymbol(String word) {

		word.toLowerCase();
		char[] wordCharArray = word.toCharArray();
		char mostUsedSymbol = ' ';
		int max = 0;

		for (int i = 0; i < wordCharArray.length; i++) {
			int count = 0;
			for (int j = 0; j < wordCharArray.length - i; j++) {
				if (wordCharArray[i] == wordCharArray[j]) {
					count++;
				}

				if (max < count) {
					max = count;
					mostUsedSymbol = wordCharArray[i];
				}
			}
		}

		return mostUsedSymbol;
	}

	static String BrakeCipherNegative(String text, int step, char[] alphabetLowLetters, char[] alphabetBigLetters) {

		char[] textToCharArray = text.toCharArray();
		String originalText = "";

		for (int i = 0; i < textToCharArray.length; i++) {
			for (int j = 0; j < alphabetLowLetters.length; j++) {
				if (j + step <= 0) {
					if (textToCharArray[i] == alphabetLowLetters[j]) {
						originalText += alphabetLowLetters[alphabetLowLetters.length - (j + step)];
						break;
					} else if (textToCharArray[i] == alphabetBigLetters[j]) {
						originalText += alphabetBigLetters[alphabetBigLetters.length - (j + step)];
						break;
					}
				} else {
					if (textToCharArray[i] == alphabetLowLetters[j]) {
						originalText += alphabetLowLetters[j + step];
						break;
					} else if (textToCharArray[i] == alphabetBigLetters[j]) {
						originalText += alphabetBigLetters[j + step];
						break;
					}
				}
			}
		}

		return originalText;

	}

	static String BrakeCipherPositive(String text, int step, char[] alphabetLowLetters, char[] alphabetBigLetters) {

		char[] textToCharArray = text.toCharArray();
		String originalText = "";

		for (int i = 0; i < textToCharArray.length; i++) {
			for (int j = 0; j < alphabetLowLetters.length; j++) {
				if (j + step >= alphabetLowLetters.length) {
					if (textToCharArray[i] == alphabetLowLetters[j]) {
						originalText += alphabetLowLetters[j + step - alphabetBigLetters.length];
						break;
					} else if (textToCharArray[i] == alphabetBigLetters[j]) {
						originalText += alphabetBigLetters[j + step - alphabetBigLetters.length];
						break;
					}
				} else {
					if (textToCharArray[i] == alphabetLowLetters[j]) {
						originalText += alphabetLowLetters[j + step];
						break;
					} else if (textToCharArray[i] == alphabetBigLetters[j]) {
						originalText += alphabetBigLetters[j + step];
						break;
					}
				}
			}

		}
		return originalText;
	}

	static int FindSymbolIndex(char symbol, char[] alphabet) {

		int index = 0;

		for (int j = 0; j < alphabet.length; j++) {
			if (alphabet[j] == symbol) {
				index = j;
				break;
			}
		}

		return index;
	}

	static int FindStep(char key, int indexOfMostUsedSymbol, char[] alphabetLowLetters) {

		int indexKey = FindSymbolIndex(key, alphabetLowLetters);

		return indexKey - indexOfMostUsedSymbol;
	}

}
