import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
public class Main{
    
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        List<Integer> list=new ArrayList<>();
        int i=0;
        while(sc.hasNextInt()){
            i++;
            list.add(sc.nextInt());
        }
        Collections.sort(list);
        System.out.println(list.get((i-1)/2));
    }
}


import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int[] arr=new int[4];
        for(int i=0;i<4;i++){
            arr[i]=sc.nextInt();
        }
        int a=(arr[0]+arr[2])/2;
        int b=(arr[1]+arr[3])/2;
        int c1=b-arr[1];
        int c2=arr[3]-b;
        int d=a-b;
        if(c1!=c2||d!=arr[0]||a<0||b<0||c1<0||c2<0){
            System.out.println("No");
        }else{
            System.out.printf("%d %d %d",a,b,c1);
            System.out.println();
        }
        
    }
}


public class Main{
    
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        List<Integer> list=new ArrayList<>();
        while(sc.hasNextInt()){
            list.add(sc.nextInt());
        }
        int res=0;
        int count=-2;
        for(int i:list){
            if(i==res){
                count++;
            }else{
                count--;
            }
            if(count<0){
                res=i;
                count=1;
            }
        }
        System.out.println(res);
    }
}

实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。
class Trie {
    private static class Node{
        private Node[] next;
        private boolean isEnd;
        public Node(){
            next=new Node[26];
        }
    }
    /** Initialize your data structure here. */
    private Node root;
    public Trie() {
        root=new Node();
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        Node cur=root;
        for(int i=0;i<word.length();i++){
            int c=word.charAt(i)-'a';
            if(cur.next[c]==null){
                cur.next[c]=new Node();
            }
            cur=cur.next[c];
        }
        cur.isEnd=true;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Node cur=root;
        for(int i=0;i<word.length();i++){
            int c=word.charAt(i)-'a';
            if(cur.next[c]==null){
                return false;
            }
            cur=cur.next[c];
        }
        return cur.isEnd;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Node cur=root;
        for(int i=0;i<prefix.length();i++){
            int c=prefix.charAt(i)-'a';
            if(cur.next[c]==null){
                return false;
            }
            cur=cur.next[c];
        }
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
 
设计一个支持以下两种操作的数据结构：

void addWord(word)
bool search(word)
search(word) 可以搜索文字或正则表达式字符串，字符串只包含字母 . 或 a-z 。 . 可以表示任何一个字母。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/add-and-search-word-data-structure-design
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

class WordDictionary {
    private static class Node{
        private Node[] next;
        private boolean isEnd;
        private int num;
        public Node(){
            next=new Node[26];
        }
    }
    /** Initialize your data structure here. */
    private Node root;
    public WordDictionary() {
        root=new Node();
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        Node cur=root;
        cur.num++;
        for(int i=0;i<word.length();i++){
            int index=word.charAt(i)-'a';
            if(cur.next[index]==null){
                cur.next[index]=new Node();
            }
            cur=cur.next[index];
            cur.num++;
        }
        cur.isEnd=true;
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return backTrack(word,root);
    }
    private boolean backTrack(String word,Node root){
        Node cur=root;
        for(int i=0;i<word.length();i++){
            char c=word.charAt(i);
            if(c=='.'){
                for(int j=0;j<26;j++){
                    if(cur.next[j]!=null){
                        if(backTrack(word.substring(i+1),cur.next[j])){
                            return true;
                        }
                    }
                }
                return false;
            }
            if(cur.next[c-'a']==null){
                    return false;
            }
            cur=cur.next[c-'a'];
        }
        return cur.isEnd;
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
 
 