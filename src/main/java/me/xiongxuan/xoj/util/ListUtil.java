package me.xiongxuan.xoj.util;

import java.util.LinkedHashSet;
import java.util.List;

public class ListUtil {

    private static void removeDuplicate(List<String> list) {
        LinkedHashSet<String> set = new LinkedHashSet<String>(list.size());
        set.addAll(list);
        list.clear();
        list.addAll(set);
    }

}
