package test;

import org.junit.Assert;
import org.junit.Test;

import pl.edu.kis.agh.DownloaderException;

public class DownloaderExceptionTest {

	@Test
    public void test()
    {
        DownloaderException exception = new DownloaderException("Brak polaczenia");
        Assert.assertEquals(exception.getErrorMessage(), "Brak polaczenia");
    }

	@Test
    public void test2()
    {
        DownloaderException exception = new DownloaderException("Podana strona nie zostala znaleziona");
        Assert.assertEquals(exception.getErrorMessage(), "Podana strona nie zostala znaleziona");
    }

}
