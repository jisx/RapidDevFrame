package com.fortune.jisx.api.okhttp.parse;

import com.fortune.jisx.model.base.BaseEnum;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * File description.
 *
 * @author jisx
 * @date Created in 2018/1/19
 * @modify By:
 */
public class EnumTypeAdapter extends TypeAdapter {

    private Class mRawType;

    public EnumTypeAdapter(Class rawType) {
        mRawType = rawType;
    }

    @Override
    public void write(JsonWriter out, Object value) throws IOException {
        if (value == null) {
            out.nullValue();
            return;
        }

        //循环所有接口
        for (Type type : mRawType.getGenericInterfaces()) {
            if (type instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) type;
                //循环泛型参数
                for (Type argumentType : parameterizedType.getActualTypeArguments()) {
                    if (String.class.isAssignableFrom((Class<?>) argumentType)) {
                        Object value1 = ((BaseEnum) value).getValue();
                        out.value(value1.toString());
                        return;
                    }
                    if (long.class.isAssignableFrom((Class<?>) argumentType)) {
                        Object value1 = ((BaseEnum) value).getValue();
                        out.value((long) value1);
                        return;
                    }
                    if (double.class.isAssignableFrom((Class<?>) argumentType)) {
                        Object value1 = ((BaseEnum) value).getValue();
                        out.value((double) value1);
                        return;
                    }
                    if (Number.class.isAssignableFrom((Class<?>) argumentType)) {
                        Object value1 = ((BaseEnum) value).getValue();
                        out.value((Number) value1);
                        return;
                    }
                    if (Boolean.class.isAssignableFrom((Class<?>) argumentType)) {
                        Object value1 = ((BaseEnum) value).getValue();
                        out.value((Boolean) value1);
                        return;
                    }
                }
            }
        }

        out.value(value.toString());
    }

    @Override
    public Object read(JsonReader in) throws IOException {
        //循环所有接口
        for (Type type : mRawType.getGenericInterfaces()) {
            if (type instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) type;
                //循环泛型参数
                for (Type argumentType : parameterizedType.getActualTypeArguments()) {
                    for (Object enumValue : mRawType.getEnumConstants()) {
                        if (enumValue instanceof BaseEnum) {
                            Object value = ((BaseEnum) enumValue).getValue();
                            if (value != null) {
                                if (String.class.isAssignableFrom(value.getClass())) {
                                    String s = in.nextString();
                                    if (value.equals(s))
                                        return enumValue;
                                } else if (long.class.isAssignableFrom(value.getClass())) {
                                    long s = in.nextLong();
                                    if (s == (long)value)
                                        return enumValue;
                                } else if (double.class.isAssignableFrom(value.getClass())) {
                                    double s = in.nextDouble();
                                    if (s == (double)value)
                                        return enumValue;
                                }
                                if (Number.class.isAssignableFrom(value.getClass())) {
                                    Number s = in.nextInt();
                                    if (s == value)
                                        return enumValue;
                                }
                                if (Boolean.class.isAssignableFrom((Class<?>) argumentType)) {
                                    boolean s = in.nextBoolean();
                                    if (s && Boolean.parseBoolean(value.toString()))
                                        return enumValue;
                                }

                            }
                        }
                    }

                }
            }
        }

        if (in.hasNext()) {
            return in.nextString();
        }

        return null;
    }
}
