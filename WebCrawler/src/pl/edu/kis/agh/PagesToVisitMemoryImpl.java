package pl.edu.kis.agh;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class PagesToVisitMemoryImpl implements DownloadQueue {

    private Set<String> queue = new HashSet<>();
    private VisitedPages visitedPages;

    public PagesToVisitMemoryImpl() {
        this.visitedPages = new VisitedPagesMemoryImpl();
    }

    @Override
    public void addPage(String pageAddress) {
        try {
            if (!visitedPages.pageAlreadyVisited(pageAddress)) {
                queue.add(pageAddress);
            }
        } catch (DownloaderException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addAFewPages(LinkedList<String> pages) {
        queue.addAll(pages);
    }

    @Override
    public boolean isEmpty() throws DownloaderException {
        return queue.isEmpty();
    }

    @Override
    public String getNextPage() throws DownloaderException {
        String next = queue.iterator().next();
        System.out.println(next);
        queue.remove(next);
        visitedPages.addVisitedPage(next);
        return next;
    }
}
