public class PalindromeCheckerApp {

    // ================= UC3 - Reverse String Method =================
    public static boolean reverseCheck(String input) {
        String reversed = "";
        for (int i = input.length() - 1; i >= 0; i--) reversed += input.charAt(i);
        return input.equals(reversed);
    }

    // ================= UC4 - Character Array =================
    public static boolean arrayCheck(String input) {
        char[] arr = input.toCharArray();
        int left = 0, right = arr.length - 1;
        while (left < right) {
            if (arr[left] != arr[right]) return false;
            left++;
            right--;
        }
        return true;
    }

    // ================= UC5 - Stack =================
    public static boolean stackCheck(String input) {
        java.util.Stack<Character> stack = new java.util.Stack<>();
        for (char ch : input.toCharArray()) stack.push(ch);
        for (char ch : input.toCharArray())
            if (ch != stack.pop()) return false;
        return true;
    }

    // ================= UC6 - Queue + Stack =================
    public static boolean queueStackCheck(String input) {
        java.util.Queue<Character> queue = new java.util.LinkedList<>();
        java.util.Stack<Character> stack = new java.util.Stack<>();
        for (char ch : input.toCharArray()) {
            queue.add(ch);
            stack.push(ch);
        }
        while (!queue.isEmpty())
            if (!queue.remove().equals(stack.pop())) return false;
        return true;
    }

    // ================= UC7 - Deque =================
    public static boolean dequeCheck(String input) {
        java.util.Deque<Character> deque = new java.util.ArrayDeque<>();
        for (char ch : input.toCharArray()) deque.add(ch);
        while (deque.size() > 1)
            if (!deque.removeFirst().equals(deque.removeLast())) return false;
        return true;
    }

    // ================= UC8 - Linked List =================
    static class Node {
        char data;
        Node next;
        Node(char data) { this.data = data; }
    }

    public static boolean linkedListCheck(String input) {
        Node head = null, tail = null;
        for (char ch : input.toCharArray()) {
            Node newNode = new Node(ch);
            if (head == null) head = tail = newNode;
            else { tail.next = newNode; tail = newNode; }
        }
        Node slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        Node prev = null;
        while (slow != null) {
            Node next = slow.next;
            slow.next = prev;
            prev = slow;
            slow = next;
        }
        Node first = head, second = prev;
        while (second != null) {
            if (first.data != second.data) return false;
            first = first.next;
            second = second.next;
        }
        return true;
    }

    // ================= UC9 - Recursive =================
    public static boolean recursiveCheck(String str, int start, int end) {
        if (start >= end) return true;
        if (str.charAt(start) != str.charAt(end)) return false;
        return recursiveCheck(str, start + 1, end - 1);
    }

    public static void main(String[] args) {
        System.out.println("=================================");
        System.out.println("     PALINDROME CHECKER APP      ");
        System.out.println("     Version 1.0                 ");
        System.out.println("=================================");
        System.out.println("Application Started Successfully!\n");

        String word = "madam";

        // UC2
        boolean isPalindrome = true;
        for (int i = 0; i < word.length() / 2; i++)
            if (word.charAt(i) != word.charAt(word.length() - 1 - i)) {
                isPalindrome = false;
                break;
            }
        System.out.println(word + (isPalindrome ? " is a Palindrome (UC2)" : " is NOT a Palindrome (UC2)"));

        // UC3
        System.out.println(word + (reverseCheck(word) ? " is a Palindrome (UC3 - Reverse)" : " is NOT a Palindrome (UC3 - Reverse)"));

        // UC4
        System.out.println(word + (arrayCheck(word) ? " is a Palindrome (UC4 - Array)" : " is NOT a Palindrome (UC4 - Array)"));

        // UC5
        System.out.println(word + (stackCheck(word) ? " is a Palindrome (UC5 - Stack)" : " is NOT a Palindrome (UC5 - Stack)"));

        // UC6
        System.out.println(word + (queueStackCheck(word) ? " is a Palindrome (UC6 - Queue + Stack)" : " is NOT a Palindrome (UC6 - Queue + Stack)"));

        // UC7
        System.out.println(word + (dequeCheck(word) ? " is a Palindrome (UC7 - Deque)" : " is NOT a Palindrome (UC7 - Deque)"));

        // UC8
        System.out.println(word + (linkedListCheck(word) ? " is a Palindrome (UC8 - Linked List)" : " is NOT a Palindrome (UC8 - Linked List)"));

        // UC9
        System.out.println(word + (recursiveCheck(word, 0, word.length() - 1) ? " is a Palindrome (UC9 - Recursion)" : " is NOT a Palindrome (UC9 - Recursion)"));
    }
}