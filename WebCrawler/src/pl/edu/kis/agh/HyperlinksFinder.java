package pl.edu.kis.agh;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Klasa sluzaca do znajdowania linkow w tekscie
 *
 */
public class HyperlinksFinder
        implements WWWPageFinder
    {

	@Override
    public LinkedList<String> getLinks(String content)
    {
        LinkedList<String> foundLinks = new LinkedList<String>();
        String regex = "<[aA][^>]+[hH][rR][eE][fF]\\s*=\\s*['\"]http([^'\"]+)['\"]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content); 
        
        while(matcher.find()) {
        	String foundFragment = matcher.group();
        	foundLinks.add(isolateLink(foundFragment));
        }

        return foundLinks;
    }

    private String isolateLink(String regexResult)
    {
        String parts[] = regexResult.split("\"");
        return parts[parts.length - 1];
    }
}
