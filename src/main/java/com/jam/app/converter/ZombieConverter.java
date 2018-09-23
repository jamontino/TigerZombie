package com.jam.app.converter;

import com.jam.app.converter.GenericConverter;
import com.jam.app.model.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ZombieConverter implements GenericConverter<String, Subject> {

    private static final String KNOWN_ZOMBIE_MARKER = "1";
    private static final String UNKNOWN_ZOMBIE_MARKER = "0";
    private static final int MAX_ALLOWED_SUBJECTS = 300;
    private static final int MAX_ALLOWED_CHAR_LIMIT = 300;

    public List<Subject> convert(List<String> inputMatrix) {
        if (isEmpty(inputMatrix) || inputMatrix.size() > MAX_ALLOWED_SUBJECTS){
            throw new RuntimeException(String.format("List size be between 1 and %s", MAX_ALLOWED_SUBJECTS));
        }
        validate(inputMatrix);

        List<Subject> subjects = buildSubjectList(inputMatrix);
        setRelationships(subjects, inputMatrix);
        return subjects;
    }

    private void validate(List<String> inputMatrix) {
        inputMatrix.forEach(this::validate);
    }

    private boolean isEmpty(List<String> inputMatrix) {
        return inputMatrix == null || inputMatrix.isEmpty();
    }

    private List<Subject> buildSubjectList(List<String> inputMatrix) {
        return IntStream.range(0, inputMatrix.size())
                .mapToObj(rowId -> new Subject(getSubjectId(rowId), new ArrayList<>()))
                .collect(Collectors.toList());
    }

    private void setRelationships(List<Subject> subjects, List<String> inputMatrix) {
        for (Subject subject : subjects) {
            String rawRelationships = getRawRelationshipsAsString(inputMatrix, subject);

            for (int i = 0; i < rawRelationships.length(); i++) {
                if (KNOWN_ZOMBIE_MARKER.equals(String.valueOf(rawRelationships.charAt(i)))) {
                    subject.getKnownZombies().add(subjects.get(i));
                }
            }
        }
    }

    private String getRawRelationshipsAsString(List<String> inputMatrix, Subject subject) {
        return inputMatrix.get(subject.getSubjectId() - 1);
    }

    private Integer getSubjectId(Integer input) {
        return input + 1;
    }

    private boolean isValidMarker(char marker) {
        return KNOWN_ZOMBIE_MARKER.equals(String.valueOf(marker)) || UNKNOWN_ZOMBIE_MARKER.equals(String.valueOf(marker));
    }

    private void isBinaryOnly(String matrix) {
        for (char marker : matrix.toCharArray()) {
            if (!isValidMarker(marker)) {
                throw new RuntimeException(String.format("Unexpected character [%s] found. Only 1 and 0 allowed!", marker));
            }
        }
    }

    private void validate(String matrix) {
        isBinaryOnly(matrix);
        isValidCharLength(matrix);
    }

    private void isValidCharLength(String matrix) {
        if(matrix.length() > MAX_ALLOWED_CHAR_LIMIT){
            throw new RuntimeException(String.format("Input cannot exceed more than %s characters",MAX_ALLOWED_CHAR_LIMIT));
        }
    }
}
