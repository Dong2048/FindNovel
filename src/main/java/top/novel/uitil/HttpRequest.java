package top.novel.uitil;

import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

public class HttpRequest {
	private static String fu="";
	private static String newstatisticUUID="";
	private static String _csrfToken="";

	/**
	 * @param url
	 * @param param
	 * @return post请求
	 */
	public static String sendPost(String url, String param) {
		 PrintWriter out = null;
	     BufferedReader in = null;
	     String result = "";
	     try {
	    	 // 创建SSLContext对象，并使用我们指定的信任管理器初始化     
	    	 TrustManager[] tm = { new MyX509TrustManager() }; 
	    	 SSLContext sslContext = SSLContext.getInstance("SSL");
	    	 sslContext.init(null, tm, new java.security.SecureRandom());
	    	 
	    	// 从上述SSLContext对象中得到SSLSocketFactory对象     
	         SSLSocketFactory ssf = sslContext.getSocketFactory(); 
	    	// 打开和URL之间的连接
	    	URL realUrl = new URL(url);
	    	HttpsURLConnection conn = (HttpsURLConnection) realUrl.openConnection();
	    	conn.setSSLSocketFactory(ssf);
	    	// 设置通用的请求属性
	    	conn.setRequestProperty("accept", "*/*");
	    	conn.setRequestProperty("connection", "Keep-Alive");
	    	conn.setRequestProperty("content-Type", "application/json");
	    	conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
	    	// 发送POST请求必须设置如下两行
	    	conn.setDoOutput(true);
	    	conn.setDoInput(true);
	    	// 获取URLConnection对象对应的输出流
	    	out = new PrintWriter(conn.getOutputStream());
	    	// 发送请求参数
	    	out.print(param);
	    	// flush输出流的缓冲
	    	out.flush();
	    	// 定义BufferedReader输入流来读取URL的响应
	    	in = new BufferedReader(
	    	new InputStreamReader(conn.getInputStream()));
	    	String line;
	    	 while ((line = in.readLine()) != null) {
	    		 result += line;
	    	}
	    	System.out.println("-----result-----"+result);
	    	} catch (Exception e) {
	    		e.printStackTrace();	
	    	} finally {// 使用finally块来关闭输出流、输入流
                try {
                    if (out != null) {
                        out.close();
                    }
                    if (in != null) {
                        in.close();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
	     	return result;
	   }

	/**
	 *
	 * @param url
	 * @param proxy 代理
	 * @param cookie
	 * @return get请求
	 */
	public static String sendGet(String url,Proxy proxy,String [] cookie) {
		InputStream is = null;
		BufferedReader br = null;
		String result = "";
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL");
			sslContext.init(null, tm, new java.security.SecureRandom());

			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			// 打开和URL之间的连接
			URL realUrl = new URL(url+cookie[2]+"&gender=male&pageNum=2&orderBy=&catId=21&subCatId=");
			HttpsURLConnection conn = (HttpsURLConnection) realUrl.openConnection(proxy);
			conn.setSSLSocketFactory(ssf);
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("content-Type", "application/json");
			conn.setRequestProperty("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.15; rv:104.0) Gecko/20100101 Firefox/104.0");
			conn.connect();
			// 通过connection连接，获取输入流
			if (conn.getResponseCode() == 200) {
				is = conn.getInputStream();
				Map<String, List<String>> cookieList=conn.getHeaderFields();
				// 封装输入流is，并指定字符集
				br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				// 存放数据
				StringBuffer sbf = new StringBuffer();
				String temp = null;
				while ((temp = br.readLine()) != null) {
					sbf.append(temp);
					sbf.append("\r\n");
				}
				result = sbf.toString();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {// 使用finally块来关闭输出流、输入流
			try {
				if (is != null) {
					is.close();
				}
				if (br != null) {
					br.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}
	/**
	 *
	 * @param url
	 * @param proxy 代理
	 * @return 获取c响应ookie
	 */
	public static Map<String, List<String>> getCookie(String url,Proxy proxy) {
		InputStream is = null;
		BufferedReader br = null;
		String result = "";
		Map<String, List<String>> cookieList = null;
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = {new MyX509TrustManager()};
			SSLContext sslContext = SSLContext.getInstance("SSL");
			sslContext.init(null, tm, new java.security.SecureRandom());

			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			// 打开和URL之间的连接
			URL realUrl = new URL(url);
			HttpsURLConnection conn = (HttpsURLConnection) realUrl.openConnection(proxy);
			conn.setSSLSocketFactory(ssf);
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("content-Type", "application/json");
			conn.setRequestProperty("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.15; rv:104.0) Gecko/20100101 Firefox/104.0");
			conn.connect();
			// 通过connection连接，获取输入流
			if (conn.getResponseCode() == 200) {

				cookieList = conn.getHeaderFields();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cookieList;
	}

	public static void main(String[] args) {
		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("104.223.135.178", 10000));
		Map<String, List<String>> cookieList = getCookie("https://m.qidian.com/category/catid21/",proxy);
		if(null!=cookieList){
			fu=cookieList.get("set-cookie").get(0).substring(0,cookieList.get("set-cookie").get(0).indexOf(";"));
			newstatisticUUID=cookieList.get("set-cookie").get(1).substring(0,cookieList.get("set-cookie").get(1).indexOf(";"));
			_csrfToken=cookieList.get("set-cookie").get(2).substring(0,cookieList.get("set-cookie").get(2).indexOf(";"));
		}
		String [] cookie = {fu,newstatisticUUID,_csrfToken};
		String s = sendGet("https://m.qidian.com/majax/category/list?",proxy,cookie);
		JSONObject jsonObject = JSONObject.parseObject(s);
		System.out.println(jsonObject);
	}
}

