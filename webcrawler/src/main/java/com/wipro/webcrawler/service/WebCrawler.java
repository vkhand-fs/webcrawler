package com.wipro.webcrawler.service;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingQueue;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wipro.webcrawler.response.SiteElementsContainer;

public class WebCrawler {

	private static final Logger logger = LoggerFactory.getLogger(WebCrawler.class);

	private String domainUrl;
	private ConcurrentHashMap<String, String> urlMap = new ConcurrentHashMap<>();

	private LinkedBlockingQueue<CompletableFuture<SiteElementsContainer>> queue = new LinkedBlockingQueue<>();
	private List<SiteElementsContainer> siteElementsContainerList = new LinkedList<>();

	public WebCrawler(String domainUrl) {
		this.domainUrl = domainUrl;
	}

	public List<SiteElementsContainer> startCrawl(String url) throws InterruptedException, ExecutionException {
		crawl(url);

		Thread.sleep(10000);
		while (!queue.isEmpty()) {
			siteElementsContainerList.add(queue.poll().get());
		}

		return siteElementsContainerList;
	}

	private void crawl(String url) {
		if (!isURLInspected(url)) {
			CompletableFuture<SiteElementsContainer> future = CompletableFuture.supplyAsync(() -> {
				try {
					return process(url);
				} catch (IOException e) {
					logger.warn("Error while processing url {} --> {}", url, e.getMessage());
					return handleException(e);

				}
			}).exceptionally(ex -> {
				logger.error("Unable to process Url {}", ex.getMessage());
				return handleException(ex);
			});

			queue.add(future);
		}
	}

	private SiteElementsContainer process(String url) throws IOException {
		Document document = Jsoup.connect(url).get();
		Elements links = document.select("a[href^=" + domainUrl + "]");
		Elements externalLinks = document.select("a[href~=^((?!" + domainUrl + ").)*$]");
		Elements images = document.getElementsByTag("img");
		SiteElementsContainer siteContainer = new SiteElementsContainer(url, links, externalLinks, images);
		links.forEach(element -> crawl(element.attr("abs:href")));
		return siteContainer;
	}

	private boolean isURLInspected(String key) {
		return urlMap.putIfAbsent(key, " ") != null;
	}

	private SiteElementsContainer handleException(Throwable ex) {
		SiteElementsContainer sec = new SiteElementsContainer();
		sec.setException(ex.getMessage());
		return sec;
	}
}
