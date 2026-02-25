public class PalindromeCheckerApp {


    public static void main(String[] args) {
        System.out.println("=================================");
        System.out.println("     PALINDROME CHECKER APP      ");
        System.out.println("     Version 1.0                 ");
        System.out.println("=================================");

        System.out.println("Application Started Successfully!");
        String word = "madam";
        boolean isPalindrome = true;

        for (int i = 0; i < word.length() / 2; i++) {
            if (word.charAt(i) != word.charAt(word.length() - 1 - i)) {
                isPalindrome = false;
                break;
            }
        }

        if (isPalindrome)
            System.out.println(word + " is a Palindrome");
        else
            System.out.println(word + " is NOT a Palindrome");
    }
}