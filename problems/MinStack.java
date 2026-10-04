import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Problem: Design a stack that supports push, pop, top, and retrieving the minimum element in O(1) time.
 *
 * Approaches:
 * 1. Two Stacks (Standard & Cleanest): One for values, one for tracking minimums.
 * 2. Node/Pair Encoding: Storing (value, minAtThisPoint) together.
 * 3. Math Difference Encoding (Follow-up): O(1) extra space by storing (val - min).
 */
public class MinStack {

    // Main stack stores all pushed elements
    private final Deque<Integer> stack;
    
    // minStack stores the running minimum corresponding to each level of 'stack'
    private final Deque<Integer> minStack;

    public MinStack() {
        // ALWAYS use ArrayDeque over java.util.Stack
        this.stack = new ArrayDeque<>();
        this.minStack = new ArrayDeque<>();
    }

    /**
     * Push val onto stack in O(1) time.
     * The invariant: minStack.peek() ALWAYS represents the minimum of all
     * elements currently present in stack.
     */
    public void push(int val) {
        stack.push(val);
        
        // If minStack is empty, the current val is unconditionally the minimum.
        // Otherwise, the new minimum is min(val, current minimum).
        if (minStack.isEmpty() || val <= minStack.peek()) {
            minStack.push(val);
        } else {
            // Re-push the current minimum so both stacks remain 1:1 aligned in size
            minStack.push(minStack.peek());
        }
    }

    /**
     * Remove the top element in O(1) time.
     */
    public void pop() {
        if (stack.isEmpty()) {
            throw new IllegalStateException("Cannot pop from an empty stack");
        }
        stack.pop();
        minStack.pop();
    }

    /**
     * Get the top element in O(1) time.
     */
    public int top() {
        if (stack.isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return stack.peek();
    }

    /**
     * Retrieve the minimum element in the stack in O(1) time.
     */
    public int getMin() {
        if (minStack.isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return minStack.peek();
    }

    // --- Smoke Tests & Verification ---
    public static void main(String[] args) {
        System.out.println("Running MinStack verification...");
        MinStack minStack = new MinStack();
        
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        assert minStack.getMin() == -3 : "Expected min to be -3";
        
        minStack.pop();
        assert minStack.top() == 0 : "Expected top to be 0";
        assert minStack.getMin() == -2 : "Expected min to be -2";
        
        System.out.println("✅ All assertions passed successfully!");
    }
}
