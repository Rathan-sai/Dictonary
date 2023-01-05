package com.example.helloworld;

import java.util.HashMap;
import java.util.Scanner;

public class autoCompletionTrie {
    Trie getNewNode(){
        Trie node = new Trie();
        node.endOfWord = false;
        return node;
    }
    void insert(Trie root, String word, String meaning){
        if(root == null)
            getNewNode();

        Trie temp = root;
        for(int i=0;i<word.length();i++){
            char c = word.charAt(i);
            if(temp.children.containsKey(c)){
                temp.children.get(c);
            }
            temp.children.put(c, getNewNode());
        }
        temp.endOfWord = true;
        temp.meaning = meaning;
    }
    String getWordMeaning(Trie root, String word){
        if(root == null)
            return "";
//        System.out.println("map");
        Trie temp = root;
        for (int i=0;i<word.length()-1;i++){
            temp = temp.children.get(word.charAt(i));
            System.out.println(temp);
            if(temp == null)
                return "afrqwer";
            System.out.println("map");
        }
        if(temp.endOfWord) {
            System.out.println("map");
            System.out.println(temp.meaning);
            return temp.meaning;
        }
        return "10";
    }

    public static void main(String[] args) {
        Trie root = new Trie();

        // Let's build the dictionary

        autoCompletionTrie obj = new autoCompletionTrie();

        Scanner sc = new Scanner(System.in);
        obj.insert(root, "Math", "A subject that deals in numbers");
        obj.insert(root, "map", "Diagrammatic representation of a particular area");
        obj.insert(root, "schedule", "A plan designed to execute a particular task");
        obj.insert(root, "book", "A written or printed work consisting of pages");
        System.out.println("Enter the word to be searched: ");
        String str = sc.next();
        System.out.println(obj.getWordMeaning(root, str));
        System.out.println("map");
    }
}



class Trie{
    public boolean endOfWord = false;
    public String meaning;
    public HashMap<Character, Trie> children = new HashMap<>();
}
