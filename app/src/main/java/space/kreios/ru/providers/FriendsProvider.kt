package space.kreios.ru.providers

import android.os.Handler
import space.kreios.ru.models.FriendModel
import space.kreios.ru.presenters.FriendsPresenter


class FriendsProvider(var presenter: FriendsPresenter) {
    fun testLoadFriends(hasFriends: Boolean) {
        Handler().postDelayed({
         val friendsList: ArrayList<FriendModel> = ArrayList()
            if (hasFriends) {
                val friend1 = FriendModel(_name = "Ivan", surname = "Petrov", city = "Domsk", avatar = "https://i1.sndcdn.com/avatars-000012942467-aq7wl9-t500x500.jpg", isOnline = true)
                val friend2 = FriendModel(_name = "Alex", surname = "Tower", city = "Tomsk", avatar = "https://i1.sndcdn.com/avatars-000012942467-aq7wl9-t500x500.jpg", isOnline = false)
                val friend3 = FriendModel(_name = "Serg", surname = "Valakas", city = "Moscow", avatar = "https://i1.sndcdn.com/avatars-000012942467-aq7wl9-t500x500.jpg", isOnline = true)

             friendsList.add(friend1)
             friendsList.add(friend2)
             friendsList.add(friend3)
            }

            presenter.friendsLoaded(friendsList = friendsList)
        }, 500)
    }
}