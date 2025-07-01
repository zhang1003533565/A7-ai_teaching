package com.example.aiteaching.dto;

import lombok.Data;
import java.util.List;

@Data
public class PageResponse<T> {
    private List<T> records;
    private long total;
    private long size;
    private long current;
    
    public PageResponse(List<T> records, long total, long size, long current) {
        this.records = records;
        this.total = total;
        this.size = size;
        this.current = current;
    }
} 