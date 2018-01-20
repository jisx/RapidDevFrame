package com.fortune.jisx.api.okhttp.parse;

import com.fortune.jisx.model.base.BaseEnum;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;

/**
 * File description.
 *
 * @author jisx
 * @date Created in 2018/1/19
 * @modify By:
 */
public class EnumTypeAdapterFactory implements TypeAdapterFactory {

    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        if (type.getRawType().isEnum()) {
            //是实现了BaseEnum类
            if (BaseEnum.class.isAssignableFrom(type.getRawType())) {
                return new EnumTypeAdapter(type.getRawType());
            }
        }

        return null;
    }

}
