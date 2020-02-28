package com.test.tool;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.test.model.Ip;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class ProxyIp {
    public Ip getIP() throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpClientContext context = HttpClientContext.create();
        String url = "http://webapi.http.zhimacangku.com/getip?num=1&type=2&yys=0&port=11&time=1&ts=1&lb=1&sb=0&pb=45&mr=1";
        HttpGet httpget = new HttpGet(url);
        httpget.setHeader("X-Requested-With", "XMLHttpRequest");
        httpget.setHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) C hrome/66.0.3359.139 Safari/537.36");
        CloseableHttpResponse response = null;
        Ip ip = null;
        try {
            response = httpclient.execute(httpget, context);
            HttpEntity entity = response.getEntity(); // 获取返回实体
            String content = EntityUtils.toString(entity, "utf-8");
            JSONObject jsonObject = JSONObject.parseObject(content);
            if (jsonObject.getBoolean("success")) {
                JSONArray data = jsonObject.getJSONArray("data");
                ip = new Ip();
                ip.setIp(jsonObject.getString(""));
                JSONObject ip1 = data.getJSONObject(0);
                ip.setIp(ip1.getString("ip"));
                ip.setPort(ip1.getString("port"));
                System.out.println(ip.getIp());
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return ip;
    }

}
