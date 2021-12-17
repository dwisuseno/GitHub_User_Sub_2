package com.dwisuseno.githubusernavigationdanapi

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    var avatars: String?,
    var username: String?
) : Parcelable