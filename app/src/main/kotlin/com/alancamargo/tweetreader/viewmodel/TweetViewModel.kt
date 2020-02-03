package com.alancamargo.tweetreader.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alancamargo.tweetreader.model.Tweet
import com.alancamargo.tweetreader.repository.TweetRepository
import kotlinx.coroutines.launch

class TweetViewModel(private val repository: TweetRepository) : ViewModel() {

    private val tweetsLiveData = MutableLiveData<List<Tweet>>()

    fun getTweets(maxId: Long? = null, sinceId: Long? = null): LiveData<List<Tweet>> {
        return tweetsLiveData.apply {
            viewModelScope.launch {
                val tweets = repository.getTweets(maxId, sinceId)
                postValue(tweets)
            }
        }
    }

}