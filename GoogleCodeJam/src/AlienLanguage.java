import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class AlienLanguage {
  private final String problemName;

  public AlienLanguage() {
    this.problemName = "AlienLanguage";
  }

  public void solve() throws IOException {
    String[] inputs = new String[] {problemName + "-small", problemName + "-large"};
    for (String input : inputs) {
      System.out.println(input);
      FileWriter fw = new FileWriter(input + ".out");
      List<String> lines = Files.readAllLines(Paths.get(input + ".in"), Charset.defaultCharset());
      String[] firstLine = lines.get(0).split(" ");
      int L = Integer.parseInt(firstLine[0]);
      int D = Integer.parseInt(firstLine[1]);
      int N = Integer.parseInt(firstLine[2]);
      PrefixTree trie = new PrefixTree();
      for (int i = 0; i < D; ++i) {
        trie.insert(lines.get(1 + i), 0);
      }
      String[] words = new String[N];
      for (int i = 0; i < N; ++i) {
        words[i] = lines.get(1 + D + i);
      }
      for (int i = 0; i < N; ++i) {
        String output = solveProblemInstance(words[i], i, L, D, trie);
        System.out.print(output);
        fw.write(output);
      }
      fw.close();
      System.out.println();
    }
  }

  private String solveProblemInstance(String word, int i, int L, int D, PrefixTree trie) {
    StringBuilder sb = new StringBuilder();
    int K = solveProblemInstance(word, 0, sb, L, D, trie);
    return String.format("Case #%d: %d%n", i + 1, K);
  }
  
  private int solveProblemInstance(String word, int j, StringBuilder sb, int L, int D, PrefixTree trie) {
    if (sb.length() == L) {
      return (trie.get(sb.toString()) > -1 ? 1 : 0);
    } else if (!trie.isPrefixOfKey((sb.toString()))) {
      return 0;
    }
    int K = 0;
    char c = word.charAt(j);
    if (c != '(') {
      sb.append(c);
      K += solveProblemInstance(word, j + 1, sb, L, D, trie);
      sb.setLength(sb.length() - 1);
    } else {
      int openBracketIndex = j;
      int closeBracketIndex = word.indexOf(')', openBracketIndex);
      for (int s = openBracketIndex + 1; s < closeBracketIndex; ++s) {
        sb.append(word.charAt(s));
        K += solveProblemInstance(word, closeBracketIndex + 1, sb, L, D, trie);
        sb.setLength(sb.length() - 1);
      }
    }
    return K;
  }
}

/*

Problem

After years of study, scientists at Google Labs have discovered an alien language transmitted from a faraway planet.
The alien language is very unique in that every word consists of exactly L lowercase letters.
Also, there are exactly D words in this language.

Once the dictionary of all the words in the alien language was built, the next breakthrough was to discover that the aliens have been transmitting messages to Earth for the past decade.
Unfortunately, these signals are weakened due to the distance between our two planets and some of the words may be misinterpreted.
In order to help them decipher these messages, the scientists have asked you to devise an algorithm that will determine the number of possible interpretations for a given pattern.

A pattern consists of exactly L tokens.
Each token is either a single lowercase letter (the scientists are very sure that this is the letter) or a group of unique lowercase letters surrounded by parenthesis ( and ).
For example: (ab)d(dc) means the first letter is either a or b, the second letter is definitely d and the last letter is either d or c.
Therefore, the pattern (ab)d(dc) can stand for either one of these 4 possibilities: add, adc, bdd, bdc.

Input

The first line of input contains 3 integers, L, D and N separated by a space.
D lines follow, each containing one word of length L.
These are the words that are known to exist in the alien language.
N test cases then follow, each on its own line and each consisting of a pattern as described above.
You may assume that all known words provided are unique.

Output

For each test case, output

Case #X: K
where X is the test case number, starting from 1, and K indicates how many words in the alien language match the pattern.

Limits

Small dataset

1 <= L <= 10
1 <= D <= 25
1 <= N <= 10

Large dataset

1 <= L <= 15
1 <= D <= 5000
1 <= N <= 500

Sample

Input 

3 5 4
abc
bca
dac
dbc
cba
(ab)(bc)(ca)
abc
(abc)(abc)(abc)
(zyx)bc

Output

Case #1: 2
Case #2: 1
Case #3: 3
Case #4: 0

*/
