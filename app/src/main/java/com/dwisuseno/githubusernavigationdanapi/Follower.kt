package com.dwisuseno.githubusernavigationdanapi

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Follower(
    var avatar: String?,
    var username: String?
) : Parcelable