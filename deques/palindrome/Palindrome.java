package deques.palindrome;

import deques.Deque;
import deques.LinkedDeque;

public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        LinkedDeque<Character> dequeOut = new LinkedDeque();
        char[] letters = word.toCharArray();
        for (char c : letters) {
            dequeOut.addLast(c);
        }
        return dequeOut;
    }
    private static boolean isPalindromeHelper(Deque<Character> deque) {
        if (deque.size() <= 1) {
            return true;
        }
        if (deque.removeLast() == deque.removeFirst()) {
            if (deque.size() >= 2) {
                return isPalindromeHelper(deque);
            }
            else {
                return true;
            }
        }
        else {
            return false;
        }
    }

    private static boolean isPalindromeHelper(Deque<Character> deque, CharacterComparator cc) {
        if (deque.size() <= 1) {
            return true;
        }
        if (cc.equalChars(deque.removeLast(), deque.removeFirst())) {
            if (deque.size() >= 2) {
                return isPalindromeHelper(deque, cc);
            }
            else {
                return true;
            }
        }
        else {
            return false;
        }
    }

    public boolean isPalindrome(String word) {
        Deque<Character> dequeOut = wordToDeque(word);
        return isPalindromeHelper(dequeOut);
        /*
        char[] letters = word.toCharArray();
        for (int i = 0; i < word.length(); i++){
            if (dequeOut.removeLast()!=letters[i]) {
                return false;
            }
        }

        return true; */
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> dequeOut = wordToDeque(word);
        return isPalindromeHelper(dequeOut, cc);
        // throw new UnsupportedOperationException("Not implemented yet.");
    }
}
