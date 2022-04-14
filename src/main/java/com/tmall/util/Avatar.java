package com.tmall.util;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author R.Yu
 * @date 2022/4/14 18:33
 */
public class Avatar {

    static ArrayList<String> avatarList = new ArrayList<>();

    public static String getAvatar() {
        // avatarList.add("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fnimg.ws.126.net%2F%3Furl%3Dhttp%253A%252F%252Fdingyue.ws.126.net%252F2021%252F0314%252F94ad46dbj00qpy1do0021d200rs00rsg008t008t.jpg%26thumbnail%3D650x2147483647%26quality%3D80%26type%3Djpg&refer=http%3A%2F%2Fnimg.ws.126.net&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1652524369&t=4433cc2343d4971a23f9e88e3a488571");
        avatarList.add("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fup.enterdesk.com%2Fedpic%2Fc9%2F63%2F77%2Fc963777ebd9c8ebf5583b39565cfa1d7.jpg&refer=http%3A%2F%2Fup.enterdesk.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1652524369&t=4fbbed60c31d86943e8c9d197958dfcf");
        avatarList.add("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fup.enterdesk.com%2Fedpic%2F55%2F34%2F61%2F553461a1d8bb07b1026a7eeff17319e0.jpg&refer=http%3A%2F%2Fup.enterdesk.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1652524369&t=1bfadcde107737d8167cbf858bee3e2d");
        avatarList.add("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fup.enterdesk.com%2Fedpic_source%2F9e%2F32%2F9a%2F9e329acc0c79523b0204f6ed7ea1e45e.jpg&refer=http%3A%2F%2Fup.enterdesk.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1652524369&t=a3607fa2226027019a9a06af0287baa9");
        avatarList.add("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fup.enterdesk.com%2Fedpic%2Ffd%2Ff1%2Fda%2Ffdf1dacb8ff0b8f13ed29bcbee42f328.jpeg&refer=http%3A%2F%2Fup.enterdesk.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1652524369&t=9763459460e93821040d5f7887965910");
        avatarList.add("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Ftupian.qqjay.com%2Ftou2%2F2019%2F0919%2F6f3fe0d3afedabefb0e5e91608c44f37.jpg&refer=http%3A%2F%2Ftupian.qqjay.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1652524369&t=2e0db200b057d70ab70d56a60dab27d0");
        avatarList.add("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fup.enterdesk.com%2Fedpic_source%2F9e%2F32%2F9a%2F9e329acc0c79523b0204f6ed7ea1e45e.jpg&refer=http%3A%2F%2Fup.enterdesk.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1652524631&t=27f33def1dccb95eff65a664c829a518");

        return avatarList.get(new Random().nextInt(6));
    }
}
