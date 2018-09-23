package com.jam.app.converter;

import com.google.common.collect.Lists;
import com.jam.app.converter.ZombieClusterConverter;
import com.jam.app.model.Subject;
import com.jam.app.model.ZombieCluster;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ZombieClusterConverterTest {

    private ZombieClusterConverter testSubject;

    @Before
    public void setUp() {
        testSubject = new ZombieClusterConverter();
    }

    @Test
    public void convert() {
        Subject zombie1 = new Subject(1, new ArrayList<>());
        Subject zombie2 = new Subject(2, new ArrayList<>());
        Subject zombie3 = new Subject(3, new ArrayList<>());

        zombie1.getKnownZombies().addAll(Lists.newArrayList(zombie1, zombie2));
        zombie2.getKnownZombies().addAll(Lists.newArrayList(zombie1, zombie2));
        zombie3.getKnownZombies().addAll(Lists.newArrayList(zombie3));

        List<ZombieCluster> clusters = testSubject.convert(Lists.newArrayList(zombie1, zombie2, zombie3));

        assertEquals(2, clusters.size());

        assertTrue(clusters.get(0).getZombies().containsAll(Lists.newArrayList(zombie1, zombie2)));
        assertTrue(clusters.get(1).getZombies().containsAll(Lists.newArrayList(zombie3)));
    }

    @Test
    public void convert_WhenNooneKnowsAnyZombies() {
        Subject zombie1 = new Subject(1, new ArrayList<>());
        Subject zombie2 = new Subject(2, new ArrayList<>());
        Subject zombie3 = new Subject(3, new ArrayList<>());

        List<ZombieCluster> clusters = testSubject.convert(Lists.newArrayList(zombie1, zombie2, zombie3));

        assertEquals(0, clusters.size());
    }
}