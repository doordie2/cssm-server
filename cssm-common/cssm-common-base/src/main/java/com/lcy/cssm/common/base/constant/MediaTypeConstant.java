package com.lcy.cssm.common.base.constant;

import java.util.HashSet;

public interface MediaTypeConstant {

    HashSet<String> AUDIO = new HashSet(){{
        add("mp3");
    }};

    HashSet<String> VIDEO = new HashSet(){{
        add("mp4");
    }};


}
