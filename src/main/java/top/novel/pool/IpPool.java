package top.novel.pool;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.novel.entity.ip;
import top.novel.mapper.IpMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName IpPool
 * @Description TODO IP池
 * @Author dong
 * @Date 2023/3/9
 * @Version 1.0
 */
@Component
public class IpPool {

    @Autowired
    private IpMapper ipMapper;
    /**
     * 定时维护ip池
     */
    public void maintainIp(){
        List<ip> list=crawlerIp("https://www.kuaidaili.com/free/inha/1/");
        for (ip p: list) {
            ipMapper.insert(p);
        }
    }

    /**
     * 检测ip活跃度
     */
    public List<ip> activeIp(List<ip>list,String reqUrl){
        for (ip i:list) {
            int statusCode = 0;
            try {
                HttpClient httpClient = new HttpClient();
                httpClient.getHostConfiguration().setProxy(i.getIp(), i.getPort());

                // 连接超时时间（默认10秒 10000ms） 单位毫秒（ms）
                int connectionTimeout = 10000;
                // 读取数据超时时间（默认30秒 30000ms） 单位毫秒（ms）
                int soTimeout = 10000;
                httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(connectionTimeout);
                httpClient.getHttpConnectionManager().getParams().setSoTimeout(soTimeout);
                HttpMethod method = new GetMethod(reqUrl);
                statusCode = httpClient.executeMethod(method);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if(statusCode==200){
                i.setState(1);
                i.setLevel(i.getLevel()+1);
            }else{
                i.setState(0);
                i.setLevel(i.getLevel()-1==-1?0:i.getLevel()-1);
            }
            System.out.format("%s:%s-->%s\n", i.getIp(), i.getPort(), statusCode);
        }
        return list;
    }
    /**
     * 检测ip活跃度
     */
    public ip activeIp(ip ipObject,String reqUrl){
        int statusCode = 0;
        try {
            HttpClient httpClient = new HttpClient();
            httpClient.getHostConfiguration().setProxy(ipObject.getIp(), ipObject.getPort());

            // 连接超时时间（默认10秒 10000ms） 单位毫秒（ms）
            int connectionTimeout = 10000;
            // 读取数据超时时间（默认30秒 30000ms） 单位毫秒（ms）
            int soTimeout = 30000;
            httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(connectionTimeout);
            httpClient.getHttpConnectionManager().getParams().setSoTimeout(soTimeout);

            HttpMethod method = new GetMethod(reqUrl);

            statusCode = httpClient.executeMethod(method);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.format("%s:%s-->%s\n", ipObject.getIp(), ipObject.getPort(), statusCode);
        return ipObject;
    }
    /**
     * 爬取ip
     */
    public List<ip> crawlerIp(String ipWebUrl){
        List<ip> list= new ArrayList<>();
        try {
            Document document= Jsoup.connect(ipWebUrl).get();
            Element element = document.select("table").first();;
            Elements els = element.select("tr");
            for (Element el : els) {
                ip ipObject=new ip();
                Elements ele = el.select("td");
                if(ele.size()!=0){
                    ipObject.setIp(ele.get(0).text());
                    ipObject.setPort(Integer.parseInt(ele.get(1).text()));
                    list.add(ipObject);
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return list;
    }


    public static void main(String[] args) {
        IpPool ipPool=new IpPool();
        ipPool.crawlerIp("https://www.kuaidaili.com/free/inha/2/");
    }
}

