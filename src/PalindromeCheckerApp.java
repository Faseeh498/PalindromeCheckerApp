import java.util.Scanner;
import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Deque;
import java.util.ArrayDeque;

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
        Stack<Character> stack = new Stack<>();
        for (char ch : input.toCharArray()) stack.push(ch);
        for (char ch : input.toCharArray())
            if (ch != stack.pop()) return false;
        return true;
    }

    // ================= UC6 - Queue + Stack =================
    public static boolean queueStackCheck(String input) {
        Queue<Character> queue = new LinkedList<>();
        Stack<Character> stack = new Stack<>();
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
        Deque<Character> deque = new ArrayDeque<>();
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
        while (fast != null && fast.next != null) { slow = slow.next; fast = fast.next.next; }
        Node prev = null;
        while (slow != null) { Node next = slow.next; slow.next = prev; prev = slow; slow = next; }
        Node first = head, second = prev;
        while (second != null) { if (first.data != second.data) return false; first = first.next; second = second.next; }
        return true;
    }

    // ================= UC9 - Recursive =================
    public static boolean recursiveCheck(String str, int start, int end) {
        if (start >= end) return true;
        if (str.charAt(start) != str.charAt(end)) return false;
        return recursiveCheck(str, start + 1, end - 1);
    }

    // ================= UC10 - Case & Space Ignored =================
    public static boolean normalizedCheck(String input) {
        String clean = input.replaceAll("\\s+", "").toLowerCase();
        int left = 0, right = clean.length() - 1;
        while (left < right) {
            if (clean.charAt(left) != clean.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }

    // ================= UC11 - OOP Service =================
    static class PalindromeService {
        public boolean checkPalindrome(String input) {
            int left = 0, right = input.length() - 1;
            while (left < right) {
                if (input.charAt(left) != input.charAt(right)) return false;
                left++;
                right--;
            }
            return true;
        }
    }

    // ================= UC12 - Strategy Pattern =================
    interface PalindromeStrategy { boolean check(String input); }

    static class ReverseStrategy implements PalindromeStrategy {
        @Override public boolean check(String input) {
            String reversed = "";
            for (int i = input.length() - 1; i >= 0; i--) reversed += input.charAt(i);
            return input.equals(reversed);
        }
    }

    static class ArrayStrategy implements PalindromeStrategy {
        @Override public boolean check(String input) {
            char[] arr = input.toCharArray();
            int left = 0, right = arr.length - 1;
            while (left < right) { if (arr[left] != arr[right]) return false; left++; right--; }
            return true;
        }
    }

    static class StackStrategy implements PalindromeStrategy {
        @Override public boolean check(String input) {
            Stack<Character> stack = new Stack<>();
            for (char ch : input.toCharArray()) stack.push(ch);
            for (char ch : input.toCharArray()) if (ch != stack.pop()) return false;
            return true;
        }
    }

    static class PalindromeContext {
        private PalindromeStrategy strategy;
        public void setStrategy(PalindromeStrategy strategy) { this.strategy = strategy; }
        public boolean executeStrategy(String input) { if (strategy == null) return false; return strategy.check(input); }
    }

    // ================= UC13 - Performance Comparison =================
    public static void performanceTest(String input) {
        System.out.println("\n=== UC13: Performance Test ===");
        String[] methods = {"Reverse", "Array", "Stack", "Queue+Stack", "Deque", "LinkedList", "Recursive", "Normalized", "OOP Service", "Strategy-Array"};
        Runnable[] runnables = {
                () -> reverseCheck(input),
                () -> arrayCheck(input),
                () -> stackCheck(input),
                () -> queueStackCheck(input),
                () -> dequeCheck(input),
                () -> linkedListCheck(input),
                () -> recursiveCheck(input, 0, input.length()-1),
                () -> normalizedCheck(input),
                () -> new PalindromeService().checkPalindrome(input),
                () -> new ArrayStrategy().check(input)
        };

        for (int i=0;i<methods.length;i++){
            long start = System.nanoTime();
            runnables[i].run();
            long end = System.nanoTime();
            System.out.printf("%-20s : %d ns%n", methods[i], (end-start));
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PalindromeService service = new PalindromeService();
        PalindromeContext context = new PalindromeContext();

        System.out.println("=================================");
        System.out.println("     PALINDROME CHECKER APP      ");
        System.out.println("     Version 1.0                 ");
        System.out.println("=================================");
        System.out.println("Application Started Successfully!\n");

        System.out.print("Enter a word or phrase: ");
        String input = sc.nextLine();

        while (true) {
            System.out.println("\nSelect method to check palindrome:");
            System.out.println("1. UC3 - Reverse String");
            System.out.println("2. UC4 - Character Array");
            System.out.println("3. UC5 - Stack");
            System.out.println("4. UC6 - Queue + Stack");
            System.out.println("5. UC7 - Deque");
            System.out.println("6. UC8 - Linked List");
            System.out.println("7. UC9 - Recursive");
            System.out.println("8. UC10 - Case & Space Ignored");
            System.out.println("9. UC11 - OOP Service");
            System.out.println("10. UC12 - Strategy Pattern (Reverse)");
            System.out.println("11. UC12 - Strategy Pattern (Array)");
            System.out.println("12. UC12 - Strategy Pattern (Stack)");
            System.out.println("13. UC13 - Performance Test");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            if (choice == 0) break;

            boolean result = false;
            switch (choice) {
                case 1 -> result = reverseCheck(input);
                case 2 -> result = arrayCheck(input);
                case 3 -> result = stackCheck(input);
                case 4 -> result = queueStackCheck(input);
                case 5 -> result = dequeCheck(input);
                case 6 -> result = linkedListCheck(input);
                case 7 -> result = recursiveCheck(input, 0, input.length() - 1);
                case 8 -> result = normalizedCheck(input);
                case 9 -> result = service.checkPalindrome(input);
                case 10 -> { context.setStrategy(new ReverseStrategy()); result = context.executeStrategy(input); }
                case 11 -> { context.setStrategy(new ArrayStrategy()); result = context.executeStrategy(input); }
                case 12 -> { context.setStrategy(new StackStrategy()); result = context.executeStrategy(input); }
                case 13 -> { performanceTest(input); continue; }
                default -> System.out.println("Invalid choice! Try again.");
            }

            if (choice >= 1 && choice <= 12)
                System.out.println(input + (result ? " is a Palindrome" : " is NOT a Palindrome"));
        }

        System.out.println("Exiting Palindrome Checker App. Goodbye!");
        sc.close();
    }
}