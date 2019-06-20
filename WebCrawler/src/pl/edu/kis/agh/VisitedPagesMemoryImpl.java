package pl.edu.kis.agh;

import java.util.HashSet;
import java.util.Set;

public class VisitedPagesMemoryImpl implements VisitedPages {

    private Set<String> visitedPages = new HashSet<>();
    private int counter = 0;

    @Override
    public boolean pageAlreadyVisited(String pageAddress) throws DownloaderException {
        return visitedPages.contains(pageAddress);
    }

    @Override
    public void addVisitedPage(String pageAddress) {
        if (counter == 500) {
            visitedPages.clear();
            counter = 0;
        }
        visitedPages.add(pageAddress);
        counter++;
    }
}
