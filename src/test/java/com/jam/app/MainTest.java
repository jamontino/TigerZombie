package com.jam.app;

import org.junit.Test;

import java.io.IOException;

public class MainTest {
    @Test
    public void main() throws IOException {
        String[] args = {this.getClass().getResource("/clusterInput/BigCluster.txt").getFile()};
        Main.main(args);
    }
}