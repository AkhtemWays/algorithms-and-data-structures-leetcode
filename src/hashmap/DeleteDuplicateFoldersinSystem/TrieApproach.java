package hashmap.DeleteDuplicateFoldersinSystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Node
{
    String s; // Value of node (folder name)
    String encoded; // Encoded subtree including and below current node
    Map<String, Node> children; // N-ary tree so children taken as hashmap
    Node()
    {
        this.encoded = "";
        this.s = "";
        this.children = new HashMap<>();
    }
    Node(String s)
    {
        this.encoded = "";
        this.s = s;
        this.children = new HashMap<>();
    }
}
public class TrieApproach
{
    Map<String, Integer> freq; // Store frequencies of encoded subtrees
    public List<List<String>> deleteDuplicateFolder(List<List<String>> paths)
    {
        Node root = new Node(); // root node of trie
        for(List<String> path : paths)
            addPath(root, path); // Add all paths to the trie
        freq = new HashMap<>();
        buildHash(root); // Function to find frequency of all subtrees
        List<List<String>> ans = new ArrayList<>(); // Store the required paths
        for(List<String> path : paths)
            if(isValid(root, path)) // Check if the path is not duplicate
                ans.add(path);
        return ans;
    }
    void addPath(Node root, List<String> path)
    {
        // Simple Trie insertion
        for(String s : path)
        {
            if(!root.children.containsKey(s))
                root.children.put(s, new Node(s));
            root = root.children.get(s);
        }
    }
    StringBuilder buildHash(Node root)
    {
        // encode the subtree root as: "root(child1)(child2)(child3)..." but recursively
        StringBuilder ret = new StringBuilder(root.s); // To return
        for(String s : root.children.keySet()) // Recursively solve for all children
            ret = ret.append("(").append(buildHash(root.children.get(s))).append(")"); // "root(child1)(child2)(child3)..."
        String x = new String(new StringBuilder("#").append(ret.substring(root.s.length()))); // replace first value by # beacuse a->b and c->b are duplicate folders (see Example 1)

        // x has 1st value replaced by # but ret has nothing replaced

        if(ret.length()==root.s.length())               ///  Fixed a bug here
            x = root.s;                                 ///  Leaf node frequency should not be increased
        else                                            ///  See Example 5, Withot this fix, path a->z and b->z got removed
            freq.put(x, freq.getOrDefault(x, 0) + 1);   // Increment frequency of current subtree
        root.encoded = x;                               // Store the encoded subtree in root node
        return ret;  // return string of the form "root(child1)(child2)(child3)..."
    }
    boolean isValid(Node root, List<String> path)
    {
        for(int i = 0; i < path.size(); i++)
        {
            root = root.children.get(path.get(i));
            if(freq.getOrDefault(root.encoded, 1) != 1) // There are multiple occurences of this subtree so return invalid path
                return false;
        }
        return true;
    }
}
