package com.zao.utils;

import java.util.Random;

/**
 * @author : zw
 * @email : zsky@live.com
 * @motto : To be, or not to be.
 * @date : 2019/5/10 8:57
 */
public class RandomUtils {
    //生成随机数
    public static String getCard(int n){
        Random rand=new Random();//生成随机数
        String cardNnumer= "";
        cardNnumer += rand.nextInt(9) + 1;
        for(int a=1;a < n;a++){
            cardNnumer+=rand.nextInt(10);//生成6位数字
        }
        return cardNnumer;
    }
}
