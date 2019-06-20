package pl.edu.kis.agh;

/**
 * Interfejs do klasy, ktora pobiera zawartosc strony
 *
 */
public interface WWWPageDownloader {

	/**
	 * Pobiera zawartosc strony podanej jako argument i zwraca ja w postaci obiektu String
	 * @param pageAddress adres strony ktorej zawartosc ma byc pobrana
	 * @return zawartosc strony
	 * @throws DownloaderException wyjatek powstaly 
	 */
    String downloadPage(String pageAddress);

}
