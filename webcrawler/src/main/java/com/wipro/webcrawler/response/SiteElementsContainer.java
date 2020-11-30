package com.wipro.webcrawler.response;

import org.jsoup.select.Elements;

public class SiteElementsContainer {

	private String exception;
	private String parentUrl;
	private Elements links;
	private Elements externalLinks;
	private Elements images;

	public SiteElementsContainer() {

	}

	public SiteElementsContainer(String parentUrl, Elements links, Elements externalLinks, Elements images) {
		super();
		this.parentUrl = parentUrl;
		this.links = links;
		this.externalLinks = externalLinks;
		this.images = images;
	}

	public String getParentUrl() {
		return parentUrl;
	}

	public void setParentUrl(String parentUrl) {
		this.parentUrl = parentUrl;
	}

	public Elements getLinks() {
		return links;
	}

	public void setLinks(Elements links) {
		this.links = links;
	}

	public Elements getExternalLinks() {
		return externalLinks;
	}

	public void setExternalLinks(Elements externalLinks) {
		this.externalLinks = externalLinks;
	}

	public Elements getImages() {
		return images;
	}

	public void setImages(Elements images) {
		this.images = images;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	@Override
	public String toString() {
		return "=== Site Elements for Url: " + parentUrl + " ===" + "\n\n===links===\n" + links
				+ "\n\n===externalLinks===\n" + externalLinks + "\n\n===images===\n" + images + "]";
	}

}
