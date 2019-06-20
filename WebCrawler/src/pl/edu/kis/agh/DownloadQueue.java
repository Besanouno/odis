package pl.edu.kis.agh;

import java.util.LinkedList;

/**
 * Interfejs dla klasy operujacej na zbiorze linkow, ktore beda maja zostac odwiedzone.
 * Przechowuje te linki i zwraca je pojedynczo
 *
 */
public interface DownloadQueue
{
	/**
	 * Dodaje pojedyncza strone do bazy, sprawdzajac uprzednio czy, taka strona nie jest juz w zbiorze
	 * 
	 * @param pageAddress adres strony ktory ma zostac dodany do bazy 
	 */
    void addPage(String pageAddress);

    /**
     * Dodaje liste stron do bazy, sprawdzajac czy kazda z nich nie znajduje sie jeszcze w bazie 
     * 
     * @param lista stron majacych zostac dodanych do bazys
     */
    void addAFewPages(LinkedList<String> pages);

    /**
     * Zwraca informacje czy lista jest pusta 
     * 
     * @return true, jesli lista jest pusta, false w przeciwnym wypadku
     * @throws DownloaderException wyjatek powstajacy gdy powstal problem z polaczeniem z baza
     */
    boolean isEmpty()
            throws DownloaderException;

    /**
     * Zwraca adres pierwszej strony z listy, oraz usuwa ja z listy
     * 
     * @return adres nowej strony
     * @throws DownloaderException wyjatek powstajacy w przypadku bledu polaczenia z baza
     */
    String getNextPage()
            throws DownloaderException;
}