public class PalindromeCheckerApp {

    // ================= UC3 - Reverse String Method =================
    public static boolean reverseCheck(String input) {

        String reversed = "";

        for (int i = input.length() - 1; i >= 0; i--) {
            reversed += input.charAt(i);
        }

        return input.equals(reversed);
    }

    // ================= UC4 - Character Array Two Pointer =================
    public static boolean arrayCheck(String input) {

        char[] arr = input.toCharArray();

        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            if (arr[left] != arr[right])
                return false;

            left++;
            right--;
        }

        return true;
    }

    public static void main(String[] args) {

        // ================= UC1 - Welcome Message =================
        System.out.println("=================================");
        System.out.println("     PALINDROME CHECKER APP      ");
        System.out.println("     Version 1.0                 ");
        System.out.println("=================================");
        System.out.println("Application Started Successfully!\n");

        String word = "madam";

        // ================= UC2 - Hardcoded Check =================
        boolean isPalindrome = true;

        for (int i = 0; i < word.length() / 2; i++) {
            if (word.charAt(i) != word.charAt(word.length() - 1 - i)) {
                isPalindrome = false;
                break;
            }
        }

        System.out.println(word + (isPalindrome ?
                " is a Palindrome (UC2)" :
                " is NOT a Palindrome (UC2)"));

        // ================= UC3 - Reverse Method =================
        System.out.println(word + (reverseCheck(word) ?
                " is a Palindrome (UC3 - Reverse)" :
                " is NOT a Palindrome (UC3 - Reverse)"));

        // ================= UC4 - Array Method =================
        System.out.println(word + (arrayCheck(word) ?
                " is a Palindrome (UC4 - Array Two Pointer)" :
                " is NOT a Palindrome (UC4 - Array Two Pointer)"));
    }
}