package com.jam.app.model;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Subject {

    private final Integer subjectId;
    private final List<Subject> knownZombies;

    public Subject(int subjectId, List<Subject> knownZombies) {
        this.subjectId = subjectId;
        this.knownZombies = knownZombies;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public List<Subject> getKnownZombies() {
        return knownZombies;
    }

    @Override
    public String toString() {

        List<Integer> collect = knownZombies.stream().map(zombie -> zombie.getSubjectId()).collect(Collectors.toList());

        return "Subject{" +
                "subjectId=" + subjectId +
                ", knownZombies=" + collect.toString() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject zombie = (Subject) o;
        return subjectId == zombie.subjectId &&
                Objects.equals(knownZombies, zombie.knownZombies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subjectId);
    }
}
