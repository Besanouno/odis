package pl.edu.kis.agh;

/**
 * Interfejs dla obiektow operujacych na bazie danych, zawierajacy podstawowe funkcje operujace na polaczeniu z baza
 *
 */
public interface DatabaseConnector
{

	/**
	 * Otwiera polaczenie z baza danych pozwalajac na operowanie na niej
	 * 
	 * @return informacja, czy operacja sie powiodla
	 */
    boolean openConnection();

    /**
     * Zamyka polaczenie z baza danych
     * 
     * @return informacja, czy operacja sie powiodla
     */
    boolean closeConnection();

    /**
     * Zwraca informacje o tym, czy polaczenie z baza danych jest aktywne i gotowe do uzycia
     * 
     * @return informacja o polaczeniu z baza
     */
    boolean isConnected();
}