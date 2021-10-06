package com.tapu.urlshortener.dto;

public class UrlResponseDto {
    private String url;
    private String shortenedUrl;

    public UrlResponseDto(String url, String shortenedUrl) {
        this.url = url;
        this.shortenedUrl = shortenedUrl;
    }

    public UrlResponseDto() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getShortenedUrl() {
        return shortenedUrl;
    }

    public void setShortenedUrl(String shortenedUrl) {
        this.shortenedUrl = shortenedUrl;
    }

    @Override
    public String toString() {
        return "UrlResponseDto{" +
                "url='" + url + '\'' +
                ", shortenedUrl='" + shortenedUrl + '\'' +
                '}';
    }
}
