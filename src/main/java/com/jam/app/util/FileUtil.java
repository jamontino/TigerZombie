package com.jam.app.util;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileUtil {
    public static List<String> read(String s) {
        try {
            return FileUtils.readLines(new File(s), "UTF-8");
        } catch (IOException e) {
            throw new RuntimeException("Failed to read file "+s);
        }
    }
}
