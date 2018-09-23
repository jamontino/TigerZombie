package com.jam.app.converter;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.jam.app.converter.GenericConverter;
import com.jam.app.model.Subject;
import com.jam.app.model.ZombieCluster;

import java.util.List;
import java.util.stream.Collectors;

public class ZombieClusterConverter implements GenericConverter<Subject,ZombieCluster> {

    @Override
    public List<ZombieCluster> convert(List<Subject> subjects) {
        List<Subject> zombies = getZombifiedSubjects(subjects);
        if (zombies.isEmpty()) {
            return Lists.newArrayList();
        }

        List<ZombieCluster> clusters = Lists.newArrayList(new ZombieCluster(Sets.newHashSet()));
        for (Subject zombie : zombies) {
            addZombieToCluster(clusters, zombie);
        }

        return clusters;
    }

    private void addZombieToCluster(List<ZombieCluster> clusters, Subject zombie) {
        if (clusters.get(0).getZombies().isEmpty()){
            clusters.get(0).getZombies().addAll(zombie.getKnownZombies());
        } else {
            if (!isKnownInExistingClusters(clusters, zombie)){
                clusters.add(new ZombieCluster(Sets.newHashSet(zombie.getKnownZombies())));
            }
        }
    }

    private boolean isKnownInExistingClusters(List<ZombieCluster> clusters, Subject zombie) {
        boolean known = false;

        for (ZombieCluster cluster : clusters) {
            for (Subject knownZombie : zombie.getKnownZombies()) {
                if (isZombieKnownInCluster(knownZombie, cluster)){
                    cluster.getZombies().addAll(zombie.getKnownZombies());
                    known = true;
                }
            }
        }
        return known;
    }

    private boolean isZombieKnownInCluster(Subject knownZombiePatient, ZombieCluster cluster) {
        return cluster.getZombies().contains(knownZombiePatient);
    }

    private List<Subject> getZombifiedSubjects(List<Subject> zombies) {
        return zombies.stream().filter(this::doesPatientKnowAnyZombies).collect(Collectors.toList());
    }

    private boolean doesPatientKnowAnyZombies(Subject zombie) {
        return !zombie.getKnownZombies().isEmpty();
    }
}
