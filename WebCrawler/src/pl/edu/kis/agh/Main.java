package pl.edu.kis.agh;

/**
 * Created by marcin on 06.12.15.
 */

public class Main
{
/*
 * DROP TABLE visited_pages;DROP TABLE pages_to_visit;
   CREATE TABLE visited_pages (URL CLOB); CREATE TABLE pages_to_visit (ID INT auto_increment, URL CLOB);
 */

    public static void main(String args[])
    {
        start("https://www.wykop.pl");
        start("https://businessinsider.com.pl/");
        start("http://www.gazeta.pl/");
        start("https://www.onet.pl");
        start("https://www.ceneo.pl/");
    }

    private static void start(String address) {
        new Thread(() -> {
            WebCrawler webCrawler = new WebCrawler();
            webCrawler.visitPages(address);
        }).start();
    }
}
