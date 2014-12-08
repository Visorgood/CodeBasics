import java.util.HashMap;

public class Roman {
  public static int romanToInt(String roman) {
    HashMap<Character, Integer> romans = new HashMap<Character, Integer>();
    romans.put('I', 1);
    romans.put('V', 5);
    romans.put('X', 10);
    romans.put('L', 50);
    romans.put('C', 100);
    romans.put('D', 500);
    romans.put('M', 1000);

    int result = 0;
    for (int i = 0; i < roman.length(); ++i) {
      char letter = roman.charAt(i);
      int letterValue = romans.get(letter);
      if (i + 1 < roman.length()) {
        char nextLetter = roman.charAt(i + 1);
        if (letter == 'I' && (nextLetter == 'V' || nextLetter == 'X')
         || letter == 'X' && (nextLetter == 'L' || nextLetter == 'C')
         || letter == 'C' && (nextLetter == 'D' || nextLetter == 'M'))
          letterValue = -letterValue;
      }
      result += letterValue;
    }
    return result;
  }
}
