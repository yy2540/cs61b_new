public class Palindrome {
    /**
     * Given a String, wordToDeque should return a Deque where the characters
     * appear in the same order as in the String.
     *
     * @param word
     * @return
     */
    public Deque<Character> wordToDeque(String word) {
        //return null;
        Deque<Character> cd = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            Character c = word.charAt(i);
            cd.addLast(c);
        }
        return cd;
    }


    /**
     * The isPalindrome method should return true if the given word is a palindrome, and false otherwise.
     * A palindrome is defined as a word that is the same whether it is read forwards or backwards.
     * For example “a”, “racecar”, and “noon” are all palindromes. “horse”, “rancor”, and “aaaaab” are not palindromes.
     * Any word of length 1 or 0 is a palindrome.
     *
     * @param word
     * @return
     */
    public boolean isPalindrome(String word) {
        return isPalindromeHelper(word, 0, word.length() - 1);
    }

    private boolean isPalindromeHelper(String word, int start, int end) {
        if (word.equals("") || start == end) {    //basis case
            return true;
        }
        //recursive case
        if (word.charAt(start) != word.charAt(end)) {
            return false;
        }
        return isPalindromeHelper(word, start + 1, end - 1);
    }

    /**
     * The method will return true if the word is a palindrome according to the character comparison test
     * provided by the CharacterComparator passed in as argument cc.
     *
     * @param word
     * @param cc
     * @return
     */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        for (int i = 0; i < word.length() / 2; i++) {
            if (!cc.equalChars(word.charAt(i), word.charAt(word.length() - i - 1))) {
                return false;
            }
        }
        return true;
    }
}