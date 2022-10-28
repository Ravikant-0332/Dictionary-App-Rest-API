package com.example.dictionaryapp.ApiHandlers;

import com.example.dictionaryapp.models.ApiResponse;

public interface OnFetchDataListener {
    void onFetch(ApiResponse apiResponse, String message);
    void onError(String message);
}
