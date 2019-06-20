package pl.edu.kis.agh;

import java.util.LinkedList;

/**
 * Interfejs dla klasy znajdujacej linki w tekscie
 *
 */
public interface WWWPageFinder
{
	/**
	 * Znajduje linki w tekscie i umieszcza je na liscie, ktora zwraca
	 * @param pageContent tekst do przeszukania
	 * @return lista linkow znalezionych
	 */
    LinkedList<String> getLinks(String pageContent);
}
