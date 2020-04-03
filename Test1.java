给定两个单词（beginWord 和 endWord）和一个字典 wordList，找出所有从 beginWord 到 endWord 的最短转换序列。转换需遵循如下规则：

每次转换只能改变一个字母。
转换过程中的中间单词必须是字典中的单词。
说明:

如果不存在这样的转换序列，返回一个空列表。
所有单词具有相同的长度。
所有单词只由小写字母组成。
字典中不存在重复的单词。
你可以假设 beginWord 和 endWord 是非空的，且二者不相同。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/word-ladder-ii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res=new LinkedList<>();

        Queue<LinkedList<String>> queue=new LinkedList<>();
        Set<String> word=new HashSet<>(wordList);
        if(!word.contains(endWord)) return res;
        Set<String> visit=new HashSet<>();

        LinkedList<String> begin=new LinkedList<>();
        begin.add(beginWord);

        queue.offer(begin);
        visit.add(beginWord);
        boolean isFind=false;

        while(!queue.isEmpty()&&!isFind){
            int size=queue.size();
            Set<String> levelVisit=new HashSet<>();
            while(size--!=0){
                LinkedList<String> subList=queue.poll();
                String s=subList.getLast();
                char[] chars=s.toCharArray();
                for(int i=0;i<chars.length;i++){
                    char c=chars[i];
                    for(char t='a';t<='z';t++){
                        chars[i]=t;
                        String str=new String(chars);
                        chars[i]=c;
                        if(visit.contains(str)){
                            continue;
                        }
                        if(word.contains(str)){
                            LinkedList<String> tmp=new LinkedList<>(subList);
                            tmp.add(str);
                            queue.offer(tmp);
                            levelVisit.add(str);
                            if(str.equals(endWord)){
                                isFind=true;
                                res.add(tmp);
                            }
                        }
                    }
                }
            }
            visit.addAll(levelVisit);
        }
        return res;
    }
}


给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。

说明：

拆分时可以重复使用字典中的单词。
你可以假设字典中没有重复的单词。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/word-break
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        int n=s.length();
        boolean[] dp=new boolean[n+1];
        dp[0]=true;
        for(int i=1;i<=n;i++){
            for(int j=0;j<i;j++){
                if(dp[j]&&wordDict.contains(s.substring(j,i))){
                    dp[i]=true;
                    break;
                }
            }
        }
        return dp[n];
    }
}

给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，在字符串中增加空格来构建一个句子，使得句子中所有的单词都在词典中。返回所有这些可能的句子。

说明：

分隔时可以重复使用字典中的单词。
你可以假设字典中没有重复的单词。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/word-break-ii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class Solution {
    List<String> res=new ArrayList<>();
    public List<String> wordBreak(String s, List<String> wordDict) {
        int n=s.length();
        boolean[] dp=new boolean[n+1];
        dp[0]=true;
        for(int i=1;i<=n;i++){
            for(int j=0;j<i;j++){
                if(dp[j]&&wordDict.contains(s.substring(j,i))){
                    dp[i]=true;
                    break;
                }
            }
        }
       if(!dp[n]) return res;
        Set<String> set=new HashSet<>(wordDict);
        backTrack(s,0,"",set);
        return res;
    }
    private void backTrack(String s,int begin,String tmp,Set<String> set){
        if(begin==s.length()){
            res.add(tmp.substring(0,tmp.length()-1));
            return;
        }
        for(int i=begin;i<s.length();i++){
            String a=s.substring(begin,i+1);
            if(set.contains(a)){
                int len=tmp.length();
                tmp+=a;
                tmp+=" ";
                backTrack(s,i+1,tmp,set);
                tmp=tmp.substring(0,len);
            }
        }
    }
}

