package com.lcy.cssm.common.base.util;

public class SortUtil {

    public static void swap(int[] arr,int i,int j){
        int c;
        c=arr[i];
        arr[i]=arr[j];
        arr[j]=c;
    }

    public static void selectsort(int[] arr){
        for(int i=0;i<arr.length-1;i++){        //控制排序的轮数同时指定下标范围
            for(int j=i+1;j<arr.length;j++){     //被比较数的下表范围
                if(arr[i]>arr[j]){
                    //交换
                    swap(arr,i,j);
                }
            }
        }
    }

    public static int[] StringToInt(String[] arrs){
        int[] ints = new int[arrs.length];
        for(int i=0;i<arrs.length;i++){
            ints[i] = Integer.parseInt(arrs[i]);
        }
        return ints;
    }

    public static String[] intToString(int[] arrs){
        String[] strings=new String[arrs.length];
        for(int i=0;i<arrs.length;i++){
            strings[i]=String.valueOf(arrs[i]);
        }
        return strings;
    }
}
