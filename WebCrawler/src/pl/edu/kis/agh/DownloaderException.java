package pl.edu.kis.agh;

/**
 * Wyjatek, ktory jest rzucany, gdy nastepuje problem polaczenia lub pobrania strony
 * @author marcin
 *
 */
public class DownloaderException extends Exception
{
    private String errorMessage;
    
    /**
     * Tworzy wyjatek, otrzymujac wiadomosc ktora moze poinformowac o 
     * zaistnialej sytuacji
     * 
     * @param error opis powstalego bledu
     */
    public DownloaderException(String error) {
        errorMessage = error;
    }

    /**
     * Zwraca komunikat zawierajacy przyczyne rzucenia wyjatku
     * 
     * @return blad powstaly w programie skutkujacy rzuceniem wyjatku DownloaderException
     */
    public String getErrorMessage() {
        return errorMessage;
    }
}