package com.jam.app.converter;

import java.util.List;

public interface GenericConverter<INP, OUT> {
    List<OUT> convert (List<INP> input);
}
