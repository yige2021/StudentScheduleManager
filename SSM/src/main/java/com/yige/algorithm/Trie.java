package com.yige.algorithm;

public class Trie {

    public int[] val = new int[20];
    public int len;

    public boolean isEnd;

    public static int addNode(Trie[] t, String id, int listSize) {
        int i;
        int index = 0;
        int idLen = id.length();

        for (i = 0; i < idLen; i++) {
            int num = Character.getNumericValue(id.charAt(i));

            if (t[index].val[num] == 0) {
                t[index].val[num] = ++listSize;
            }
            index = t[index].val[num];
        }

        t[index].isEnd = true;

        return listSize;
    }

    public static int[] queryNode(Trie[] t, String id) {
        int i;
        int index = 0;
        int idLen = id.length();

        for (i = 0; i < idLen; i++) {
            int num = Character.getNumericValue(id.charAt(i));

            if (t[index].val[num] == 0) {
                return null;
            }

            index = t[index].val[num];
        }

        if (t[index].isEnd == false) {
            return null;
        }

        return t[index].val;
    }
}
