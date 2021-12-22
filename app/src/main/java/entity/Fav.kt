package entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Fav(
    var id: Int = 0,
    var avatars: String? = null,
    var username: String? = null
) : Parcelable
