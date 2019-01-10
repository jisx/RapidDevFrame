package com.rapid.sql.greendao;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

/**
 * Created by jisx on 2017/6/20.
 */
@Entity
public class UserEntity {

    @Id
    Long id;
    String name;
    int age;


}
