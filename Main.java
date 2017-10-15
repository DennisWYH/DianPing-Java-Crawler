import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Main {
	public static BufferedReader br;
	public static FileWriter fw;
	public static PrintWriter out;
	public static BufferedWriter bw;

	
	public static void retrieveDataFromShop(String urlName){
		try {
			Document doc;			
			doc = Jsoup.connect(urlName).header("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36").get();
			Element basicBody = doc.getElementById("basic-info");
			Element avgPriceTitle = doc.getElementById("avgPriceTitle");
			Elements address = doc.getElementsByClass("expand-info address");
			Element review = doc.getElementById("reviewCount");
			
			String shopName = basicBody.getElementsByClass("shop-name").get(0).childNode(0).toString().trim();
			String shopPrice = avgPriceTitle.html().toString();
			String shopAddress = address.get(0).getElementsByClass("item").html().toString(); 
			String shopReviewNumber = review.html().toString();
			
			
			fw = new FileWriter("/Users/marve/Documents/workspace/JavaWebCrawler- Resturants in Beijing/data.txt");
			bw = new BufferedWriter(fw);
			out = new PrintWriter(bw);
			out.print(shopName);
			out.print(shopPrice);
			out.print(shopAddress);
			out.print(shopReviewNumber);
				
			out.flush();
			out.close();
			bw.close();
			fw.close();
			System.out.println("Data collecting done");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		try {
			FileReader fr = new FileReader("/Users/marve/Documents/workspace/JavaWebCrawler- Resturants in Beijing/waitingList.txt");
			br = new BufferedReader(fr);
			String line;
//			while((line = br.readLine()) != null){
//				String temp = line;
				retrieveDataFromShop("http://wawalove.pl/");
//			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



}
