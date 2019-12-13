package com.atguigu.gmall0715.mock.util;

import java.util.Random;

/**
 * Created by DaDi on 2019/12/13
 */

public class RandomNum {
    public static final  int getRandInt(int fromNum,int toNum){
        return   fromNum+ new Random().nextInt(toNum-fromNum+1);
    }
}
