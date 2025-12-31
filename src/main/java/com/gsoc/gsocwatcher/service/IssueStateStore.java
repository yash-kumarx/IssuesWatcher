package com.gsoc.gsocwatcher.service;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class IssueStateStore {

    private final Set<String> seenIssueUrls = new HashSet<>();

    public boolean isNew(String url) {
        if (seenIssueUrls.contains(url)) {
            return false;
        }
        seenIssueUrls.add(url);
        return true;
    }
}