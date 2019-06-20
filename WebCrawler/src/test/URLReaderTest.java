package test;

import static org.junit.Assert.*;

import org.junit.Test;

import pl.edu.kis.agh.URLReader;

public class URLReaderTest {

	@Test
	public void test() {
		URLReader urlReader = new URLReader();
		String content = urlReader.downloadPage("http://student.agh.edu.pl/~mbasista/alamakota.html");
		StringBuilder correctContent = new StringBuilder();
		correctContent.append("<html>\n")
					  .append("<body>\n")
					  .append("Ala ma kota\n")
					  .append("</body>\n")
					  .append("</html>\n");
				   
				  
				  
		assertTrue(content.equals(correctContent.toString()));

	}

}
