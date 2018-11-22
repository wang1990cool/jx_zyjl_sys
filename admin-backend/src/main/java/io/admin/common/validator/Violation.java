package io.admin.common.validator;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class Violation implements Serializable {

    private final String message;

    private final Object bean;

    private final String property;

    private final Object value;
}
