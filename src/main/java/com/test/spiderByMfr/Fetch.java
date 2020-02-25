package com.test.spiderByMfr;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class Fetch {
    public Document noProxyGetDoc(String str) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpClientContext context = HttpClientContext.create();
        HttpGet httpget = new HttpGet(str);
        //Ip ip = getIP();
        //  HttpHost proxy = new HttpHost(ip.getIp(), Integer.parseInt(ip.getPort()));
        RequestConfig defaultRequestConfig = RequestConfig.custom()
                .setConnectTimeout(10000)//设置连接超时时间
                .setSocketTimeout(10000)//设置读取超时时间
                // .setProxy(proxy)
                .build();
        httpget.setConfig(defaultRequestConfig);

        httpget.setHeader("X-Requested-With", "XMLHttpRequest");
        httpget.setHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) C hrome/66.0.3359.139 Safari/537.36");
        CloseableHttpResponse response = httpclient.execute(httpget, context);
        try {
            //System.out.println(response);
            HttpEntity entity = response.getEntity();
            Document document = Jsoup.parse(EntityUtils.toString(entity,"utf-8"));
            //System.out.println(document);
            return document;
        } finally {
            if (response != null)
                response.close();
        }
    }
}
