package com.necessarysense.crazywords;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class PasswordGenerator {
  private static final String[] SPECIAL_CHARACTERS =
    new String[] { "@", "!", "#", "$", "%", "^", "&", "*", "-", "+", ":", ";", "<", ">", "?" };

  private static List<String> readAllWords(char let) throws IOException, URISyntaxException {
    try (BufferedReader reader = Files.newBufferedReader(Paths.get(PasswordGenerator.class.getClassLoader().getResource("easydict/"+let+".words.txt").toURI()))) {
      return reader.lines().collect(Collectors.toList());
    }
  }

  private static String chooseWord(Random r) throws IOException, URISyntaxException {
    final int diff = 'z' - 'a';
    final char let = (char)('a' + r.nextInt(diff));
    final List<String> words = readAllWords(let);
    final int i = r.nextInt(words.size());
    return words.get(i);
  }

  private static String chooseNumber(Random r) {
    final int i = r.nextInt(1000);
    return Integer.toString(i);
  }

  private static String chooseSpecialCharacter(Random r) {
    final int i = r.nextInt(SPECIAL_CHARACTERS.length);
    return SPECIAL_CHARACTERS[i];
  }

  public static void main(String[] args) throws Exception {
    Random r = new Random();
    StringBuilder pw = new StringBuilder();
    pw.append(chooseWord(r));
    pw.append(chooseNumber(r));
    pw.append(chooseWord(r));
    pw.append(chooseSpecialCharacter(r));
    pw.append(chooseWord(r));
    System.out.println(pw.toString());
  }
}
