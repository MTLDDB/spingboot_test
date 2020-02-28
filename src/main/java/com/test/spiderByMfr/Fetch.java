package com.test.spiderByMfr;

import com.test.model.Ip;
import com.test.tool.ProxyIp;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
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
    private Ip ip;
    {
        try {
            ip = new ProxyIp().getIP();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Document noProxyGetDoc(String str) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpClientContext context = HttpClientContext.create();
        HttpGet httpget = new HttpGet(str);
        CloseableHttpResponse response=null;
        Document document=null;
        try {
        HttpHost proxy = new HttpHost(ip.getIp(), Integer.parseInt(ip.getPort()));
        RequestConfig defaultRequestConfig = RequestConfig.custom()
                .setConnectTimeout(20000)//设置连接超时时间
                .setSocketTimeout(20000)//设置读取超时时间
                .setProxy(proxy)
                .build();
        httpget.setConfig(defaultRequestConfig);
        httpget.setHeader("X-Requested-With", "XMLHttpRequest");
        httpget.setHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) C hrome/66.0.3359.139 Safari/537.36");
        response = httpclient.execute(httpget, context);
        HttpEntity entity = response.getEntity();
        document = Jsoup.parse(EntityUtils.toString(entity,"utf-8"));
        }catch (Exception e){
            System.out.println(e+str);
        }finally {
            if (response != null)
                response.close();
        }
        return document;
    }
}
