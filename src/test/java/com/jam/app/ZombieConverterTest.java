package com.jam.app;

import com.google.common.collect.Lists;
import com.jam.app.converter.ZombieConverter;
import com.jam.app.model.Subject;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ZombieConverterTest {
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    private ZombieConverter testSubject;

    @Before
    public void setUp() {
        testSubject = new ZombieConverter();
    }

    @Test
    public void convert() {
        ArrayList<String> input = Lists.newArrayList("110", "110", "001");

        List<Subject> zombies = testSubject.convert(input);

        assertEquals(1, zombies.get(0).getSubjectId());
        assertEquals(2, zombies.get(0).getKnownZombies().size());
        assertEquals(1, zombies.get(0).getKnownZombies().get(0).getSubjectId());
        assertEquals(2, zombies.get(0).getKnownZombies().get(1).getSubjectId());

        assertEquals(2, zombies.get(1).getSubjectId());
        assertEquals(2, zombies.get(1).getKnownZombies().size());
        assertEquals(1, zombies.get(1).getKnownZombies().get(0).getSubjectId());
        assertEquals(2, zombies.get(1).getKnownZombies().get(1).getSubjectId());

        assertEquals(3, zombies.get(2).getSubjectId());
        assertEquals(1, zombies.get(2).getKnownZombies().size());
        assertEquals(3, zombies.get(2).getKnownZombies().get(0).getSubjectId());
    }

    @Test
    public void convert_WhenNooneIsZombified() {
        ArrayList<String> input = Lists.newArrayList("000", "000", "000");

        List<Subject> zombies = testSubject.convert(input);

        assertEquals(1, zombies.get(0).getSubjectId());
        assertTrue(zombies.get(0).getKnownZombies().isEmpty());

        assertEquals(2, zombies.get(1).getSubjectId());
        assertTrue(zombies.get(0).getKnownZombies().isEmpty());

        assertEquals(3, zombies.get(2).getSubjectId());
        assertTrue(zombies.get(0).getKnownZombies().isEmpty());
    }

    @Test
    public void convert_WhenInvalidCharacterInputPassed_ThenThrowError() {
        ArrayList<String> input = Lists.newArrayList("112", "110", "001");

        try {
            testSubject.convert(input);
            failAssert();
        } catch (RuntimeException e) {
            assertEquals("Unexpected character [2] found. Only 1 and 0 allowed!", e.getMessage());
        }
    }

    @Test
    public void convert_WhenMoreThen300Zombies_ThenThrowError() {
        List mockList = mock(ArrayList.class);
        when(mockList.size()).thenReturn(301);

        try {
            testSubject.convert(mockList);
            failAssert();
        } catch (Exception e) {
            assertEquals("List size be between 1 and 300", e.getMessage());
        }

        verify(mockList).size();
    }

    @Test
    public void convert_WhenListIsEmptyOrNull_ThenThrowError() {
        try {
            testSubject.convert(new ArrayList<>());
            failAssert();
        } catch (Exception e) {
            assertEquals("List size be between 1 and 300", e.getMessage());
        }

        try {
            testSubject.convert(null);
            failAssert();
        } catch (Exception e) {
            assertEquals("List size be between 1 and 300", e.getMessage());
        }
    }

    @Test
    public void convert_WhenRowExceeds300Character_ThenThrowError() {
        try {
            testSubject.convert(Lists.newArrayList(getDummyInputWithLength(301)));
            failAssert();
        } catch (RuntimeException e) {
            assertEquals("Input cannot exceed more than 300 characters", e.getMessage());
        }
    }

    @Test
    public void convert_whenTrailingZeroesDoesNotExist() {
        ArrayList<String> input = Lists.newArrayList("11", "11", "001", "0001");

        List<Subject> zombies = testSubject.convert(input);

        assertEquals(1, zombies.get(0).getSubjectId());
        assertEquals(2, zombies.get(0).getKnownZombies().size());
        assertEquals(1, zombies.get(0).getKnownZombies().get(0).getSubjectId());
        assertEquals(2, zombies.get(0).getKnownZombies().get(1).getSubjectId());

        assertEquals(2, zombies.get(1).getSubjectId());
        assertEquals(2, zombies.get(1).getKnownZombies().size());
        assertEquals(1, zombies.get(1).getKnownZombies().get(0).getSubjectId());
        assertEquals(2, zombies.get(1).getKnownZombies().get(1).getSubjectId());

        assertEquals(3, zombies.get(2).getSubjectId());
        assertEquals(1, zombies.get(2).getKnownZombies().size());
        assertEquals(3, zombies.get(2).getKnownZombies().get(0).getSubjectId());

        assertEquals(4, zombies.get(3).getSubjectId());
        assertEquals(1, zombies.get(3).getKnownZombies().size());
        assertEquals(4, zombies.get(3).getKnownZombies().get(0).getSubjectId());
    }


    private String getDummyInputWithLength(int length) {
        String dummy = "";
        for (int i = 0; i < length; i++) {
            dummy = dummy + "0";
        }
        return dummy;
    }


    private void failAssert() {
        fail("Expected exception not thrown");
    }
}