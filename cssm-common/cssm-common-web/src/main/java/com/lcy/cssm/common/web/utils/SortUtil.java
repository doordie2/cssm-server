package com.lcy.cssm.common.web.utils;

import org.apache.commons.lang3.StringUtils;

public class SortUtil {

    //sort:排序项  order:升序或降序
    public static final String backSort(String sort, String order){
        if(sort == null||order==null){
            return null;
        }
        String sortWithOrder = "";
        if(sort.equalsIgnoreCase("createTime")){
            sortWithOrder = "create_time";
        }
        if(sort.equalsIgnoreCase("flowerNum")){
            sortWithOrder = "flower_num";
        }
        if(sort.equalsIgnoreCase("pageViews")){
            sortWithOrder = "page_views";
        }
        if(sort.equalsIgnoreCase("likeNum")){
            sortWithOrder = "like_num";
        }
        if(sort.equalsIgnoreCase("commentNum")){
            sortWithOrder = "comment_num";
        }


        if(StringUtils.isNotBlank(sortWithOrder)&&order.equalsIgnoreCase("asc")){
            sortWithOrder += " ASC";
        }
        else if(StringUtils.isNotBlank(sortWithOrder)&&order.equalsIgnoreCase("desc")){
            sortWithOrder += " DESC";
        }

        return sortWithOrder;
    }
}
