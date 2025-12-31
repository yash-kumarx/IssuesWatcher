package com.gsoc.gsocwatcher.scheduler;

import com.gsoc.gsocwatcher.model.GitHubIssue;
import com.gsoc.gsocwatcher.service.EmailService;
import com.gsoc.gsocwatcher.service.GitHubService;
import com.gsoc.gsocwatcher.service.IssueStateStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class IssueScheduler {

    private final GitHubService gitHubService;
    private final IssueStateStore stateStore;
    private final EmailService emailService;

    @Value("${gsoc.repos}")
    private String repos;

    public IssueScheduler(
            GitHubService gitHubService,
            IssueStateStore stateStore,
            EmailService emailService
    ) {
        this.gitHubService = gitHubService;
        this.stateStore = stateStore;
        this.emailService = emailService;
    }

    @Scheduled(fixedRateString = "${scheduler.interval-ms}")
    public void checkIssues() {

        List<String> repoList = Arrays.asList(repos.split(","));

        for (String repo : repoList) {

            List<GitHubIssue> issues = gitHubService.fetchIssues(repo.trim());

            for (GitHubIssue issue : issues) {

                if (!stateStore.isNew(issue.getHtmlUrl())) {
                    continue;
                }

                String subject = "🚀 New GSoC Issue [" + repo + "]";
                String body =
                        "Repository: " + repo + "\n\n" +
                        "Title: " + issue.getTitle() + "\n\n" +
                        "Issue URL:\n" +
                        issue.getHtmlUrl();

                emailService.sendEmail(subject, body);

                System.out.println("New Issue Found");
                System.out.println("Repo : " + repo);
                System.out.println("Title: " + issue.getTitle());
                System.out.println("URL  : " + issue.getHtmlUrl());
                System.out.println("----------------------------------");
            }
        }
    }
}