package com.test.spiderByMfr;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Paser {
    public List<String> getListUrl(Document document) {
        List<String> urlList = new ArrayList<>();
        Elements div = document.select("div[id=productIndexList]");
        if (div != null) {
            Elements lis = div.first().select("li");
            for (Element li : lis) {
                String url = "https://www.digikey.com" + li.select("a").attr("href")
                        .replace("|", "%7C").replace(",", "%2C");
                urlList.add(url);
            }
        }
        return urlList;
    }
    public List<String> getCategoryListUrl(Document document) {
        List<String> urlList = new ArrayList<>();
        Elements div = document.select("div[class=product-categories-row]");
        if (div != null) {
            Elements lis = div.first().select("li");
            for (Element li : lis) {
                String url = "https://www.digikey.com" + li.select("a").attr("href")
                        .replace("|", "%7C").replace(",", "%2C");
                urlList.add(url);
            }
        }
        return urlList;
    }
    public List<String> getPageListUrl(Document document, String baseUrl) {
        List<String> urls = new ArrayList<>();
        int num = 0;
        String pattern = "(/(\\d+))";
        Pattern p = Pattern.compile(pattern);
        Matcher matcher = p.matcher(baseUrl);
        matcher.find();
        String flag = matcher.group(2);
        Element span = document.getElementById("matching-records-count");
        String numString = span.text().replace(",", "");
        num = Integer.valueOf(numString);
        System.out.println(num);
        int pageSize = num / 100 + 1;
        for (int i = 1; i <=pageSize; i++) {
            String url = baseUrl + "%2C-8%7C" + flag + "&quantity=0&ColumnSort=0&page=" + i + "&pageSize=100";
            urls.add(url);
        }
        return urls;
    }

    public boolean judgeType(Document document) {
        Element table = document.getElementById("productTable");
        if(table!=null)
            return true;
        else
            return false;
    }
}
