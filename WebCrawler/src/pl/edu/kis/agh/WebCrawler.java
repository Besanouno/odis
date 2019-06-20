package pl.edu.kis.agh;

import java.util.LinkedList;

/**
 * Klasa przegladajaca strony w celu pobrania z nich hiperlaczy
 *
 */
public class WebCrawler
{
    private PagesToVisitMemoryImpl pagesToVisit;
    private HyperlinksFinder hyperlinksFinder;


    /**
     * Tworzy obiekt do przeszukiwania stron z domyslnie ustawionymi tablicami w bazie danych
     */
    public WebCrawler()
    {
        pagesToVisit = new PagesToVisitMemoryImpl();
        hyperlinksFinder = new HyperlinksFinder();
    }
//
//    /**
//     * Tworzy obiekt do przeszukiwania stron z podanymi tablicami w bazie danych
//     */
//    public WebCrawler(String tableWithPagesToVisit, String tableWithVisitedPages)
//    {
//        pagesToVisit = new PagesToVisitMemoryImpl(tableWithPagesToVisit, tableWithVisitedPages);
//        hyperlinksFinder = new HyperlinksFinder();
//    }

    private VisitedPages visitedPages = new VisitedPagesMemoryImpl();
    /**
     * Odwiedza strony rozpoczynajac od podanej, wyszukujac linki na niej znajdujace sie, 
     * i otwierajac kazdy kolejny
     * 
     * @param firstPage pierwsza odwiedzana strona
     */
    public void visitPages(String firstPage)
    {
        pagesToVisit.addPage(firstPage);

        try
        {
            while(!pagesToVisit.isEmpty())
            {
                String nextPage = pagesToVisit.getNextPage();
                if (!visitedPages.pageAlreadyVisited(nextPage)) {
                    visitedPages.addVisitedPage(nextPage);

                    String nextPageContent = downloadNextpage(nextPage);

                    LinkedList<String> links = hyperlinksFinder.getLinks(nextPageContent);
                    pagesToVisit.addAFewPages(links);
                }
            }
        }
        catch(DownloaderException e) { 
            e.printStackTrace();
        }
    }

    private String downloadNextpage(String nextPage)
    {
        URLReader urlReader = new URLReader();
        String nextPageContent = "";

          nextPageContent = urlReader.downloadPage(nextPage);

        return nextPageContent;
    }

}