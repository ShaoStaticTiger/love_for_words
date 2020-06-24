package com.sjh.love_for_words.bean;

import lombok.*;


import java.io.Serializable;
import java.sql.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Words implements Serializable {
    String word;
    String chinese;
    int totalTimes;
    Date lastTime;
    int accuracy;
    int wrongTimes;
}
