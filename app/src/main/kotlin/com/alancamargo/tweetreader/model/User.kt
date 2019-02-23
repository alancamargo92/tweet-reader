package com.alancamargo.tweetreader.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
open class User(
    open val id: Long,
    open val name: String,
    open val screenName: String,
    open val location: String,
    open val description: String,
    open val followersCount: Int,
    open val creationDate: String,
    open val profilePictureUrl: String,
    open val profileBannerUrl: String
) : Parcelable