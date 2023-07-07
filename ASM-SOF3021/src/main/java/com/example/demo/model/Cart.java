package com.example.demo.model;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<Integer, Integer> sanPhamTrongGio = new HashMap<>();

    public Map<Integer, Integer> getSanPhamTrongGio() {
        return sanPhamTrongGio;
    }

    public void setSanPhamTrongGio(Map<Integer, Integer> sanPhamTrongGio) {
        this.sanPhamTrongGio = sanPhamTrongGio;
    }
}
