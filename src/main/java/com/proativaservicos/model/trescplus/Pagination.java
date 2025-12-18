package com.proativaservicos.model.trescplus;

import com.google.gson.annotations.SerializedName;

public class Pagination {

    @SerializedName("total")
    private int total;
    @SerializedName("count")
    private int count;
    @SerializedName("per_page")
    private int perPage;

    @SerializedName("current_page")
    private int currentPage;

    @SerializedName("total_pages")
    private int totalPages;

    @SerializedName("links")
    private Links links;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public class Links{
        public String next;

        public String getNext() {
            return next;
        }

        public void setNext(String next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return "Links{" +
                    "next='" + next + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Pagination{" +
                "total=" + total +
                ", count=" + count +
                ", perPage=" + perPage +
                ", currentPage=" + currentPage +
                ", totalPages=" + totalPages +
                ", links=" + links +
                '}';
    }
}
