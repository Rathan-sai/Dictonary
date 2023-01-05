package com.example.helloworld;

import javafx.beans.property.SimpleStringProperty;

import java.util.*;
public class AutocompleteTrieMap {


    private SimpleStringProperty word;

    public String getWord() {
        return word.get();
    }

    public void setWord(String word) {
        this.word.set(word);
    }

    public SimpleStringProperty wordProperty() {
        return word;
    }

    public static List<String> search(String word){
        Trie t = new Trie();
        return t.autocomplete(word);
    }

    static class TrieNode {
        char data;
        HashMap<Character, TrieNode> children = new HashMap<>();
        boolean isEnd = false;

        //Constructor, Time O(1), Space O(1)
        TrieNode(char c) {
            this.data = c;
        }
    }

    static class Trie {

        TrieNode root = new TrieNode(' ');

        //Add a word to trie, Iteration, Time O(s), Space O(s), s is word length
        void insert(String word) {
            TrieNode node = root;
            for (char ch : word.toCharArray()) {
                if (!node.children.containsKey(ch))
                    node.children.put(ch, new TrieNode(ch));
                node = node.children.get(ch);
            }
            node.isEnd = true;
        }

        //find all word with given prefix
        //Time O(n), Space O(n), n is number of nodes involved (include prefix and branches)
        List<String> autocomplete(String prefix) {
            List<String> res = new LinkedList<String>();
            TrieNode node = root;
            for (char ch : prefix.toCharArray()) {
                if (node.children.containsKey(ch))
                    node = node.children.get(ch);
                else
                    return res;
            }
            helper(node, res, prefix.substring(0, prefix.length()-1));
            return res;
        }

        // recursive function called by autocomplete
        //Time O(n), Space O(n), n is number of nodes in branches
        void helper(TrieNode node, List<String> res, String prefix) {
            if (node.isEnd)
                res.add(prefix + node.data);
            for (Character ch : node.children.keySet())
                helper(node.children.get(ch), res, prefix + node.data);
        }
    }


    public static void main(String[] args) {
        Trie t = new Trie();
        t.insert("amazon");
        t.insert("amazon prime");
        t.insert("amazing");
        t.insert("amazing spider man");
        t.insert("amazed");
        t.insert("apple");

        System.out.println(t.autocomplete("a"));
    }
}


