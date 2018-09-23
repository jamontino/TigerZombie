package com.jam.app;

import com.google.common.collect.Lists;
import com.jam.app.util.FileUtil;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        ArrayList<String> strings = Lists.newArrayList(args);

        List<String> strings1 = FileUtil.read(strings.get(0));
        ZombieClusterFinder finder = new ZombieClusterFinder();
        System.out.println("Number of zombie clusters: " + finder.getZombieClusterCount(strings1));
    }
}