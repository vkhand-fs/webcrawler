package com.wipro.webcrawler;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.wipro.webcrawler.response.SiteElementsContainer;
import com.wipro.webcrawler.service.WebCrawler;

@RunWith(MockitoJUnitRunner.class)
public class WebCrawlerTest {

	@InjectMocks
	private WebCrawler webcrawler;
	
	
	@Test
	public void inValidDomainURLTest() throws InterruptedException, ExecutionException {
		List<SiteElementsContainer> siteMapContainer = webcrawler.startCrawl("xyz/test.html");	
		Assert.assertNotNull(siteMapContainer.get(0).getException());
		Assert.assertNull(siteMapContainer.get(0).getLinks());
		Assert.assertNull(siteMapContainer.get(0).getExternalLinks());
		Assert.assertNull(siteMapContainer.get(0).getImages());
	}
	
	@Test
	public void validDomainURLTest() throws InterruptedException, ExecutionException {
		List<SiteElementsContainer> siteMapContainer = webcrawler.startCrawl("https://wiprodigital.com");	
		Assert.assertNull(siteMapContainer.get(0).getException());
		Assert.assertNotNull(siteMapContainer.get(0).getLinks());
		Assert.assertNotNull(siteMapContainer.get(0).getExternalLinks());
		Assert.assertNotNull(siteMapContainer.get(0).getImages());
	}
	
}
