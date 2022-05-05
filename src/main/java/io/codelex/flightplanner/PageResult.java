package io.codelex.flightplanner;

import java.util.ArrayList;
import java.util.List;

public class PageResult<T> {
    private Long page = 0L;
    private Long totalItems = 0L;
    private List<T> items = new ArrayList<>();

    public PageResult(Long page, Long totalItems, List<T> items) {
        this.page = page;
        this.totalItems = totalItems;
        this.items = items;
    }

    public Long getPage() {
        return page;
    }

    public void setPage(Long page) {
        this.page = page;
    }

    public Long getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(Long totalItems) {
        this.totalItems = totalItems;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}