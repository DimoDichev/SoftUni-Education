package implementations;

import interfaces.Solvable;

import java.util.Arrays;

public class BalancedParentheses implements Solvable {
    private final int DEFAULT_CAPACITY = 8;
    private char[] memory;
    private int size;
    private String parentheses;

    public BalancedParentheses(String parentheses) {
        this.parentheses = parentheses;
        this.memory = new char[DEFAULT_CAPACITY];
        memory[0] = ' ';
        this.size = 0;
    }

    @Override
    public Boolean solve() {
        for (int i = 0; i < this.parentheses.length(); i++) {
            char current = this.parentheses.charAt(i);

            if (this.memory[0] == ' ' && (current == ')' || current == '}' || current == ']')) {
                return false;
            }

            if (current == '(' || current == '{' || current == '[') {
                if (this.size == this.memory.length) grow();
                this.memory[size] = current;
                this.size++;
            } else if (this.memory[size - 1] == '(' && current == ')' ||
                    this.memory[size - 1] == '{' && current == '}' ||
                    this.memory[size - 1] == '[' && current == ']') {
                this.memory[size - 1] = ' ';
                this.size--;
            } else {
                return false;
            }
        }

        return size == 0;
    }

    private void grow() {
        char[] newMemory = new char[this.memory.length * 2];
        for (int i = 0; i < this.memory.length; i++) {
            newMemory[i] = this.memory[i];
        }
        memory = newMemory;
    }
}
