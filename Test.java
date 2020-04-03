给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。

'?' 可以匹配任何单个字符。
'*' 可以匹配任意字符串（包括空字符串）。
两个字符串完全匹配才算匹配成功。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/wildcard-matching
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class Solution {
    public boolean isMatch(String s, String p) {
        int m=s.length();
        int n=p.length();
        boolean[][] dp=new boolean[m+1][n+1];
        dp[0][0]=true;
        for(int i=1;i<=n;i++){
            dp[0][i]=p.charAt(i-1)=='*'&&dp[0][i-1];
        }
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(s.charAt(i)==p.charAt(j)||p.charAt(j)=='?'){
                    dp[i+1][j+1]=dp[i][j];
                }else if(p.charAt(j)=='*'){
                    dp[i+1][j+1]=dp[i+1][j]||dp[i][j+1];
                }
            }
        }
        return dp[m][n];
    }
}

给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：

每次转换只能改变一个字母。
转换过程中的中间单词必须是字典中的单词。
说明:

如果不存在这样的转换序列，返回 0。
所有单词具有相同的长度。
所有单词只由小写字母组成。
字典中不存在重复的单词。
你可以假设 beginWord 和 endWord 是非空的，且二者不相同

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/word-ladder
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if(!wordList.contains(endWord)){
            return 0;
        }
        int size=wordList.size();
        Queue<String> queue=new LinkedList<>();
        boolean[] isVisited=new boolean[size];
        queue.offer(beginWord);
        int res=1;
        while(!queue.isEmpty()){
            res++;
            int num=queue.size();
            while(num--!=0){
                String s=queue.poll();
                for(int i=0;i<size;i++){
                    if(isVisited[i]){
                        continue;
                    }
                    String t=wordList.get(i);
                    if(isValid(s,t)){
                        if(t.equals(endWord)){
                            return res;
                        }
                        queue.offer(t);
                        isVisited[i]=true;
                    }
                }
            }
        }
        return 0;
    }
    private boolean isValid(String a,String b){
        int count=0;
        for(int i=0;i<a.length();i++){
            if(a.charAt(i)!=b.charAt(i)){
                count++;
            }
            if(count>1){
                return false;
            }
        }
        return count==1;
    }
}

class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> set=new HashSet<>(wordList);
        if(!set.contains(endWord)){
            return 0;
        }
        int size=wordList.size();
        Queue<String> queue=new LinkedList<>();
        Set<String> isVisited=new HashSet<>();
        queue.offer(beginWord);
        isVisited.add(beginWord);
        int res=1;
        while(!queue.isEmpty()){
            res++;
            int num=queue.size();
            while(num--!=0){
                String s=queue.poll();
                char[] chars=s.toCharArray();
                for(int i=0;i<chars.length;i++){
                    char c=chars[i];
                    for(char t='a';t<='z';t++){
                        chars[i]=t;
                        String m=new String(chars);
                        chars[i]=c;
                        if(isVisited.contains(m)){
                            continue;
                        }
                        if(set.contains(m)){
                            if(m.equals(endWord)){
                                return res;
                            }
                            queue.offer(m);
                            isVisited.add(m);
                        }
                        
                        
                    }
                }
            }
        }
        return 0;
    }
}


class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> set=new HashSet<>(wordList);
        if(!set.contains(endWord)){
            return 0;
        }
        Queue<String> queue1=new LinkedList<>();
        Queue<String> queue2=new LinkedList<>();

        Set<String> isVisited1=new HashSet<>();
        Set<String> isVisited2=new HashSet<>();

        queue1.offer(beginWord);
        queue2.offer(endWord);

        isVisited1.add(beginWord);
        isVisited2.add(endWord);
        int res=1;
        while(!queue1.isEmpty()&&!queue2.isEmpty()){
            res++;
            if(queue1.size()>queue2.size()){
                Queue<String> tmp=queue1;
                queue1=queue2;
                queue2=tmp;

                Set<String> t=isVisited1;
                isVisited1=isVisited2;
                isVisited2=t;
            }
            int size=queue1.size();
            while(size--!=0){
                String s=queue1.poll();
                char[] chars=s.toCharArray();
                for(int i=0;i<chars.length;i++){
                    char c=chars[i];
                    for(char t='a';t<='z';t++){
                        chars[i]=t;
                        String m=new String(chars);
                        chars[i]=c;
                        if(isVisited1.contains(m)){
                            continue;
                        }
                        if(isVisited2.contains(m)){
                            return res;
                        }
                        if(set.contains(m)){
                            queue1.offer(m);
                            isVisited1.add(m);
                        }
                        
                        
                    }
                }
            }
        }
        return 0;
    }
}


