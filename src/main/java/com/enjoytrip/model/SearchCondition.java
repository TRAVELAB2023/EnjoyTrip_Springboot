package com.enjoytrip.model;

import lombok.Getter;

import java.util.Objects;

@Getter
public class SearchCondition {
    private int sidoCode;
    private int gugunCode;
    private int contentTypeCode;
    private String word;
    private boolean memberLike;

    public SearchCondition(int sidoCode, int gugunCode, int contentTypeCode, String word, boolean memberLike) {
        this.sidoCode = sidoCode;
        this.gugunCode = gugunCode;
        this.contentTypeCode = contentTypeCode;
        this.word = word;
        this.memberLike = memberLike;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchCondition that = (SearchCondition) o;
        return sidoCode == that.sidoCode && gugunCode == that.gugunCode && contentTypeCode == that.contentTypeCode && Objects.equals(word, that.word);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sidoCode, gugunCode, contentTypeCode, word);
    }
}
