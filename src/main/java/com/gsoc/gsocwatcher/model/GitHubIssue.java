package com.gsoc.gsocwatcher.model;

public class GitHubIssue {

    private String title;
    private String htmlUrl;

    public GitHubIssue(String title, String htmlUrl) {
        this.title = title;
        this.htmlUrl = htmlUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }
}