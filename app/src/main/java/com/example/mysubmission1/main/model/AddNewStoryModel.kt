package com.example.mysubmission1.main.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mysubmission1.main.response.AddNewStoryResponse
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody

class AddNewStoryModel(private val dataSource: DataSource) : ViewModel() {
    val upload: LiveData<AddNewStoryResponse> = dataSource.add

    fun uploadStory(token: String, file: MultipartBody.Part, description: RequestBody) {
        viewModelScope.launch {
            dataSource.uploadStory(token, file, description)
        }
    }

    fun getUserSession(): LiveData<UserSession> {
        return dataSource.getUserSession()
    }
}