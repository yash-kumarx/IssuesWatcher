package com.gsoc.gsocwatcher.service;

import com.gsoc.gsocwatcher.model.GitHubIssue;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class GitHubService {

    public List<GitHubIssue> fetchIssues(String repo) {

        String url = "https://api.github.com/repos/" + repo + "/issues";

        RestTemplate restTemplate = new RestTemplate();
        List<GitHubIssue> result = new ArrayList<>();

        Object[] issues = restTemplate.getForObject(url, Object[].class);

        for (Object obj : issues) {
            Map issue = (Map) obj;

            // Ignore Pull Requests
            if (issue.containsKey("pull_request")) {
                continue;
            }

            String title = (String) issue.get("title");
            String htmlUrl = (String) issue.get("html_url");

            result.add(new GitHubIssue(title, htmlUrl));
        }

        return result;
    }
}