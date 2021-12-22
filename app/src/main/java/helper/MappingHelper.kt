package helper

import android.database.Cursor
import com.dwisuseno.githubusernavigationdanapi.User
import db.DatabaseContract
import entity.Fav

object MappingHelper {

    fun mapCursorToArrayList(favCursor: Cursor?): ArrayList<User> {
        val favList = ArrayList<User>()
        favCursor?.apply {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow(DatabaseContract.FavColumns._ID))
                val avatars = getString(getColumnIndexOrThrow(DatabaseContract.FavColumns.AVATARS))
                val username = getString(getColumnIndexOrThrow(DatabaseContract.FavColumns.USERNAME))
                favList.add(User(avatars, username))
            }
        }
        return favList
    }
}