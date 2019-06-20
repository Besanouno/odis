package pl.edu.kis.agh;

/**
 * Interfejs klasy przechowujacej odwiedzone strony
 * 
 */
public interface VisitedPages
{

	/**
	 * Zwraca informacje czy podana strona zostala juz odwiedzona 
	 * @param pageAddress sprawdzana strona 
	 * @return true jesli byla juz odwiedzona, false w przeciwnym wypadku
	 * @throws DownloaderException wyjatek powstajacy przy bledzie polaczenia z baza
	 */
    boolean pageAlreadyVisited(String pageAddress) throws DownloaderException;

    void addVisitedPage(String pageAddress);
}