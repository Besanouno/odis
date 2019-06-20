package pl.edu.kis.agh;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownHostException;

import javax.net.ssl.SSLSocketFactory;


public class URLReader implements WWWPageDownloader {
	private Socket socket;

	@Override
	public String downloadPage(String nextPage) {
		String content = "";
		try {
			connect(nextPage);
			content = getContent(socket.getInputStream());
			socket.close();
		} catch (UnknownHostException e) {
		} catch (IOException e) {
		} catch (DownloaderException e) {
		}

		return content;
	}

	private void connect(String pageAddress) throws UnknownHostException, IOException, DownloaderException {
		URL page = stringToURL(pageAddress);
		String host = page.getHost();
		String path = page.getPath();
		int port = getPort(pageAddress);

		if (port == 443) {
			SSLSocketFactory ssl = (SSLSocketFactory) SSLSocketFactory.getDefault();
			socket = ssl.createSocket(host, port);
		} else {
			socket = new Socket(host, port);
		}
		socket.setSoTimeout(5000);
		socket.setKeepAlive(false);
		sendRequest(host, path, port);
	}

	private int getPort(String page) {
		String[] linkParts = page.split("://");
		String protocol = linkParts[0];
		switch (protocol) {
		case "http":
			return 80;
		case "https":
			return 443;
		default:
			return -1;
		}
	}

	private String getContent(InputStream input) throws IOException {
		StringBuilder content = new StringBuilder();
		String newLine = null;
		BufferedReader bf = new BufferedReader(new InputStreamReader(input));
		removeServerResponseFromContent(bf);
		try {

			while ((newLine = bf.readLine()) != null) {
				content.append(newLine).append("\n");
			}
		} catch (SocketTimeoutException e) {
		}
		return content.toString();
	}

	private void removeServerResponseFromContent(BufferedReader bufferedReader) throws IOException {
		String newLine = bufferedReader.readLine();
		try {

			while (newLine != null && !newLine.equals("")) {
				newLine = bufferedReader.readLine();
			}

		} catch (SocketTimeoutException e) {
		}
	}

	private void sendRequest(String address, String zasob, int port) throws IOException {
		OutputStream out = socket.getOutputStream();
		PrintWriter writer = new PrintWriter(new OutputStreamWriter(out, "UTF-8"));
		writer.println("GET /" + zasob + " HTTP/1.1");
		writer.println("Host: " + address);
		writer.println();
		writer.flush();
	}
 
	public static URL stringToURL(String urlAddress) throws DownloaderException {
		try {
			return new URL(urlAddress);
		} catch (MalformedURLException exception) {
			StringBuilder error = new StringBuilder();
			error.append(String.valueOf(urlAddress))
				 .append(" ")
				 .append("Podana strona nie zostala znaleziona");
			throw new DownloaderException(error.toString());
		}
	}

}