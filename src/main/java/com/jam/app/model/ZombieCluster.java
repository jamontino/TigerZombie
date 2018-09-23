package com.jam.app.model;

import java.util.Set;

public class ZombieCluster {

    private Set<Subject> zombies;

    public ZombieCluster(Set<Subject> zombies) {
        this.zombies = zombies;
    }

    public Set<Subject> getZombies() {
        return zombies;
    }
}
