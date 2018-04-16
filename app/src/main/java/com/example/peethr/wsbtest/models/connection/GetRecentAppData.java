package com.example.peethr.wsbtest.models.connection;

import com.example.peethr.wsbtest.models.data.recentApps.RecentApp;

import java.util.LinkedList;

/**
 * Created by goracy on 11.04.18.
 */

public class GetRecentAppData {

    private String desc ="Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam molestie sapien eleifend libero aliquet molestie.";
    private LinkedList<RecentApp> recentApp = new LinkedList();

    public LinkedList getDataFromInternet(){
        recentApp.add(
                new RecentApp(
                "Jak dojadę",
                desc,
                "http://irecommend.ru/sites/default/files/product-images/839709/QsKwh3B8dDp2GoWo48l7nw.jpg"));
        recentApp.add(
                new RecentApp("Pyszne.pl",
                desc,
                "http://antyweb.pl/wp-content/uploads/2018/01/pyszne-1420x670.jpg"));
        recentApp.add(
                new RecentApp("SKM compas",
                desc,
                "https://static2.s-trojmiasto.pl/zdj/c/9/25/620x0/256801-Przez-trzy-najblizsze-niedziele-pociagi-SKM-beda-kursowac-wedlug-zmienionego__c_0_16_555_334.jpg"));
        recentApp.add(
                new RecentApp("Uber",
                desc,
                "http://mediad.publicbroadcasting.net/p/waer/files/styles/medium/public/201612/uber-serp-logo-f6e7549c89_0.jpg"));


        return recentApp;
    }

}
