//Victor Majestic
//This class contains methods that manipulate Strings, play a game of hangman, and search arrays.

import javax.swing.JOptionPane;

public class HW2 extends Object {
  
  //A method that changes a certain amount of characters in a String to a different character.
  public static String replaceFirstK(String s, char a, char b, int i) {
    int index = 0;                                                           //The index of the String.
    int limit = 0;                                                           //Tracks if the amount of characters meant to be replaced has been met.
    StringBuilder builder = new StringBuilder();
    //Replaces String characters that are meant to be replaced, reflected in "builder".
    while (index < s.length()) {
      if (s.charAt(index) == a && limit < i) {
        limit = limit + 1;
        builder.append(b);
      }
      else {
        builder.append(s.charAt(index));
      }
      index = index + 1;
    }
    return builder.toString();
  }
  
  //A method that returns all letters in the alphabet between the two input characters.
  public static String allChars(char a, char b) {
   String alphabet = "abcdefghijklmnopqrstuvwxyz";                           //The alphabet, so multiple characters can be returned in alphabetical order.
   StringBuilder builder = new StringBuilder();
   //Runs through the entire alphabet.
   for (int index = 0; index < alphabet.length(); index = index + 1) {
     if (alphabet.charAt(index) == a) {
       //Adds all characters to StringBuilder that are from a to b, except b.
       while (alphabet.charAt(index) != b) {
         builder.append(alphabet.charAt(index));
         index = index + 1;
       }
       //Adds b to the StringBuilder, so it is inclusive.
       if (alphabet.charAt(index) == b) {
         builder.append(alphabet.charAt(index));
         index = index + 1;
       }
     }
   }
   return builder.toString();
  }
  
  //A method that prints all characters in the first String that are in the second String, and _ if it is not in the second String.
  public static String showCharofString(String s, String t) {
   boolean repeat = false;                                                   //Ensures that no character is appended more than once.
   StringBuilder builder = new StringBuilder();
   //The two for loops run through the entire String j for each character of String i.
   for (int i = 0; i < s.length(); i = i + 1) {
     for (int j = 0; j < t.length(); j = j + 1) {
       //Appends to StringBuilder if the characters of String s and t match, and are not already appended.
       if (s.charAt(i) == t.charAt(j) && repeat == false) {
         builder.append(s.charAt(i));
         repeat = true;
       }
     }
     //Appends a _ if the characters of String s and t are not identical.
     if (repeat == true || (repeat == false && i == 0)) {
       builder.append('_');
     }
     repeat = false;
   }
   return builder.toString();
  }
  
  //A method that implements a hangman game, but it is not complete.
  public static boolean hangman(String s, int m) {
    StringBuilder builder = new StringBuilder();
    int badguess = 0;                                                       //Keeps track of how many bad guesses were made.
    boolean check = false;                                                  //Checks if the letter was guessed already.
    String t;                                                               //The message on the option pane.
    //The loop that runs as long as the game is going on.
    for (int i = 0; i < m; i = i + 1) {
      //Displays the message on the option pane.
      t = JOptionPane.showInputDialog(HW2.showCharofString(s, builder.toString()) + " -- " + (m - badguess) + " guesses left!");
      System.out.println(t);
      //Ensures that the input value is one character. (I believe t is the wrong thing to use here)
      if (t.length() == 0) {
        //Checks if the character has been guessed already.
        for (int j = 0; j < builder.length(); j = j + 1) {
          if (t.equals(builder.charAt(j))) {
            check = true;
          }
        }
        //Adds the input to StringBuilder and increases the bad guess count.
        if (check == false) {
          builder.append(t);
          badguess = badguess + 1;
          }
        //Changes the check flag to the default of false because the if statement has passed.
        if (check == true) {
          check = false;
        }
      }
    }
    //Ends the game if the amount of bad guesses has been exceeded. 
    if (badguess > m) {
      return false;
      }
    return true;
  }
  
  //A method that returns true if there is a sequence of characters in the array that match the String, otherwise it is false.
  public static boolean hiddenString(char[] c, String s) {
    int stringindex = 0;                                                     //Stores the index of the String.
    int stringindexb = 0;                                                    //Stores the index of the String, used for running backwards.
  //Runs through the array from start to finish.
    for (int arrayindex = 0; arrayindex < c.length; arrayindex = arrayindex + 1) {
      //Tracks if there are matching characters in the String, and if so, changes the String index.
      if (stringindex < s.length()) {
        if (c[arrayindex] == s.charAt(stringindex)) {
          stringindex = stringindex + 1;
        }
        //Resets the String index if the characters are not matching.
        else {
          stringindex = 0;
      }
    }
    }
    //Runs through the array from back to front.
    for (int arrayindex = c.length - 1; arrayindex >= 0; arrayindex = arrayindex - 1) {
      //Tracks if there are matching characters in the String, and if so, changes the String index.
      if (stringindexb < s.length()) {
        if (c[arrayindex] == s.charAt(stringindexb)) {
          stringindexb = stringindexb + 1;
        }
        //Resets the String index if the characters are not matching.
        else {
          stringindexb = 0;
        }
      }
    }
    //Returns true if there is a sequence of characters in the array that match the String, otherwise, returns false.
    if (stringindex == s.length() || stringindexb == s.length()) {
      return true;
    }
    return false;
  }
  
  //A method that returns true if there is a pattern of characters in the array that match the String, otherwise it is false.
  public static boolean hiddenString(char[][] c, String s) {
    int stringindex = 0;                                                     //Stores the index of the String.
    int stringindexb = 0;                                                    //Stores the index of the String, used for running backwards.
    //Runs through all characters in the array.
    for (int i = 0; i < c.length; i = i + 1) {
      for (int j = 0; j < c[i].length; j = j + 1) {
        //Tracks if there are matching characters in the String, and if so, changes the String index.
        if (stringindex < s.length() && c[i][j] == s.charAt(stringindex)) {
            stringindex = stringindex + 1;
        }
        //Resets the String index if the characters are not matching.
        else {
          stringindex = 0;
        }
      }
    }
    for (int i = c.length - 1; i >= 0; i = i - 1) {
      for (int j = c[i].length - 1; j >= 0; j = j - 1) {
        //Tracks if there are matching characters in the String, and if so, changes the String index.
        if (stringindexb < s.length() && c[i][j] == s.charAt(stringindexb)) {
          stringindexb = stringindexb + 1;
        }
       //Resets the String index if the characters are not matching.
        else {
          stringindexb = 0;
        }
      }
    }
    //Returns true if there is a sequence of characters in the array that match the String, otherwise, returns false.
    if (stringindex == s.length() || stringindexb == s.length()) {
      return true;
    }
    return false;
  }
}