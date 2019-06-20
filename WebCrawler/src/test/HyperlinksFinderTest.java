package test;

import java.util.LinkedList;
import org.junit.Assert;
import org.junit.Test;

import pl.edu.kis.agh.*;

public class HyperlinksFinderTest
{
	@Test
    public void test()
    {
        HyperlinksFinder hyperlinkFinder = new HyperlinksFinder();
        Assert.assertEquals(new LinkedList<String>(), hyperlinkFinder.getLinks(""));
    }

	@Test
    public void test1()
    {
        HyperlinksFinder hyperlinkFinder = new HyperlinksFinder();
        URLReader urlReader = new URLReader();
        
        String content = urlReader.downloadPage("http://student.agh.edu.pl/~mbasista/linki.html");

        LinkedList<String> foundLinks = hyperlinkFinder.getLinks(content);
        Assert.assertEquals(3, foundLinks.size());
    }
}