package com.jam.app.converter;

import com.jam.app.ZombieClusterFinder;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class ZombieClusterFinderTest {

    private static final String RESOURCE_PATH = "/clusterInput/";
    private ZombieClusterFinder testSubject;

    @Before
    public void setUp(){
        testSubject = new ZombieClusterFinder();
    }

    @Test
    public void getZombieClusterCount_WhenZeroCluster() throws IOException, URISyntaxException {
        String inputFile = "NoZombies.txt";
        List<String> strings = getInputFile(inputFile);

        int actual = testSubject.getZombieClusterCount(strings);

        Assert.assertEquals(0,actual);
    }

    @Test
    public void getZombieClusterCount_WhenOneClusterFound() throws IOException, URISyntaxException {
        String inputFile = "SimpleCluster.txt";
        List<String> input = getInputFile(inputFile);

        int actual = testSubject.getZombieClusterCount(input);

        Assert.assertEquals(2,actual);
    }

    @Test
    public void getZombieClusterCount_WhenBigClusterPassed() throws IOException, URISyntaxException {
        String inputFile = "BigCluster.txt";
        List<String> input = getInputFile(inputFile);

        int actual = testSubject.getZombieClusterCount(input);

        Assert.assertEquals(5,actual);
    }

    @Test
    public void getZombieClusterCount_WhenStaggeredClusterWithUninfectedPassed() throws IOException, URISyntaxException {
        String inputFile = "StaggeredClusterWithHumans.txt";
        List<String> input = getInputFile(inputFile);

        int actual = testSubject.getZombieClusterCount(input);

        Assert.assertEquals(1,actual);
    }

    @Test
    public void getZombieClusterCount_WhenInitialSubjectsAreNotLinked() throws IOException, URISyntaxException {
        String inputFile = "OddSetup.txt";
        List<String> input = getInputFile(inputFile);

        int actual = testSubject.getZombieClusterCount(input);

        Assert.assertEquals(2,actual);
    }

    private List<String> getInputFile(String inputFile) throws URISyntaxException, IOException {
        File file = new File(this.getClass().getResource(RESOURCE_PATH + inputFile).toURI());
        return FileUtils.readLines(file, "UTF-8");
    }
}