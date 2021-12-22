package db

import android.provider.BaseColumns

internal class DatabaseContract {

    internal class FavColumns : BaseColumns {
        companion object {
            const val TABLE_NAME = "fav"
            const val _ID = "_id"
            const val AVATARS = "avatars"
            const val USERNAME = "username"
        }
    }
}