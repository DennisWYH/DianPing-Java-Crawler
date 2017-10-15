import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class retrieveURL {
	public static Set<String> storage = new HashSet<String>();
	public static String regex1;
	public static String regex2;
	public static String regex3;
	public static int counter = 0;
	public static BufferedWriter bw;
	private static String newURL;
	

	public static String getNewURL() {
		return newURL;
	}

	public static void setNewURL(String newURL) {
		retrieveURL.newURL = newURL;
	}
	

	public static void filterShopLink(String urlName){
		regex1 = "(http://www.dianping.com/shop/)(\\d*)";
		
		Pattern pattern = Pattern.compile(regex1);
		Matcher matcher = pattern.matcher(urlName);
		
		if(matcher.matches()){
			if(matcher.matches()){
				if(storage.contains(urlName)){
				}
				else{
					storage.add(urlName);			
				}
			}
		}
		else{				
		}
	}
	
	public static void writeIntoFile(Set<String> storage){
		int counter = 0;
		FileWriter fw;
		PrintWriter out;
		try {
		
			fw = new FileWriter("/Users/marve/Documents/workspace/JavaWebCrawler- Resturants in Beijing/waitingList.txt");
			bw = new BufferedWriter(fw);
			out = new PrintWriter(bw);
			
			
			Iterator<String> iter = storage.iterator();
			while(iter.hasNext()){
				String temp = iter.next().toString();
				out.println(temp);
				out.flush();
				counter +=1;
			}
			out.println(counter);
			out.flush();
			out.close();
			bw.close();
			fw.close();
			System.out.println("Done!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void getAllLink(String urlName){
		Document doc;
		try {
			doc = Jsoup.connect(urlName).
			        header("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36"
			        		).get();
			Elements links = doc.select("a[href]");
			for(Element link : links){
				boolean isWhitespace = link.attr("abs:href").matches("^\\s*$");
				if(isWhitespace){
					//do nothing
				}
				else{
					URL url = new URL(link.attr("abs:href"));
					//filter links only keep shop links.
					filterShopLink(url.toString());
				}
			}
			//write the urls in storage set into a local file.
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void testPrint(Set<String> storage){
		Iterator<String> iter = storage.iterator();
		while(iter.hasNext()){
			String temp = iter.next().toString();
			System.out.println(temp);
			counter+=1;
		}
		
	}
	
	public static void main(String[] args){
		String urlModel = "http://www.dianping.com/search/category/2/10/p1";
		for(int i = 1; i < 50; i++){
			setNewURL(urlModel+i);
			getAllLink(newURL);
		}
		writeIntoFile(storage);
		
	}

	
}
