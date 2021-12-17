package com.dwisuseno.githubusernavigationdanapi

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Following(
    var avatar: String?,
    var username: String?
) : Parcelable
