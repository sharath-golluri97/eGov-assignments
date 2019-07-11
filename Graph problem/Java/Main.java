import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
// import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.*;

public class Main {

    HashMap<Integer, Integer> indegree = new HashMap<>();
    HashMap<Integer, ArrayList<Integer>> hashMap = new HashMap<>();

    public int trim(String s) {
        char[] st = s.toCharArray();
        int i = 0;
        while (st[i] == ' ') {
            i++;
        }
        int node = 0;
        while (i < st.length) {
            node = node * 10 + (st[i] - '0');
            i++;
        }
        return node;
    }

    public void insert(String fromString, String toString) {
        int from = trim(fromString);
        int to = trim(toString);
        hashMap.get(from).add(to);
        int val = indegree.get(to);
        indegree.replace(to, val, val + 1);
    }

    public void split(String line) {
        char delim = ',';
        char[] l = line.toCharArray();
        int i = 0;
        while (l[i] != delim) {
            i++;
        }
        insert(line.substring(0, i), line.substring(i + 1));
    }

    public void print() {
        Set<Integer> keys = hashMap.keySet();
        for (Integer k : keys) {
            ArrayList<Integer> t = hashMap.get(k);
            for (int i = 0; i < t.size() - 1; i++) {
                System.out.print(t.get(i) + ", ");
            }
            if (t.size() > 0) {
                System.out.println(t.get(t.size() - 1));
            }
        }
    }

    public void dfs(ArrayList<Integer> path) {
        int prerequisite = path.get(path.size() - 1);
        ArrayList<Integer> list = hashMap.get(prerequisite);
        if (list.size() == 0) {
            for (int i = 0; i < path.size() - 1; i++) {
                System.out.print(path.get(i) + " -> ");
            }
            System.out.println(path.get(path.size() - 1));
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            path.add(list.get(i));
            dfs(path);
            path.remove(path.size() - 1);
        }
    }

    Main(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line = br.readLine();
        int nodes = Integer.parseInt(line);
        for (int i = 0; i < nodes; i++) {
            indegree.put(i, 0);
            hashMap.put(i, new ArrayList<Integer>());
        }
        // Set<Integer> k = indegree.keySet();
        // for(int ki : k){
        // System.out.print(ki);
        // System.out.print(" -> ");
        // System.out.println(indegree.get(ki));
        // }

        while ((line = br.readLine()) != null) {
            // System.out.println(line);
            split(line);
        }
        br.close();
        for (int i = 0; i < nodes; i++) {
            if (indegree.get(i) != 0) {
                continue;
            }
            ArrayList<Integer> path = new ArrayList<>();
            path.add(i);
            dfs(path);
        }

    }

    public static void main(String[] args) throws IOException {
        // write your code here
        String filename = "input.txt";
        Main ans = new Main(filename);
        // ans.print();
    }
}