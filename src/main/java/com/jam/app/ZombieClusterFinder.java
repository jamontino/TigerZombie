package com.jam.app;

import com.jam.app.converter.ZombieClusterConverter;
import com.jam.app.converter.ZombieConverter;
import com.jam.app.model.Subject;
import com.jam.app.model.ZombieCluster;

import java.util.List;

public class ZombieClusterFinder {

    private final ZombieConverter zombieConverter = new ZombieConverter();
    private final ZombieClusterConverter clusterConverter = new ZombieClusterConverter();

    public int getZombieClusterCount(List<String> inputMatrix) {
        return getZombieCluster(inputMatrix).size();
    }

    private List<ZombieCluster> getZombieCluster(List<String> inputMatrix) {

        List<Subject> subjects = zombieConverter.convert(inputMatrix);
        List<ZombieCluster> clusters = clusterConverter.convert(subjects);

        return clusters;
    }
}
