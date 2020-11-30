package com.wipro.webcrawler;

import java.util.List;
import java.util.concurrent.ExecutionException;

import com.wipro.webcrawler.response.SiteElementsContainer;
import com.wipro.webcrawler.service.WebCrawler;

public class WebCrawlerApplication {

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		if (args.length != 1) {
			System.out.println("Please provide a domain url as an argument");
			System.exit(0);
		}

		String domainUrl = args[0];
		List<SiteElementsContainer> siteContainer = new WebCrawler(domainUrl).startCrawl(domainUrl);
		siteContainer.forEach(container -> System.out.println("=== Crawling " +domainUrl+" ===\n\n" + container));
		
	}

}
