package space.kreios.ru.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import space.kreios.ru.R
import space.kreios.ru.models.FriendModel
import space.kreios.ru.providers.FriendsProvider
import space.kreios.ru.views.FriendsView


@InjectViewState
class FriendsPresenter: MvpPresenter<FriendsView>() {
    fun loadFriends() {
        viewState.startLoading()
        FriendsProvider(presenter = this).testLoadFriends(hasFriends = true)


        //var TAG = FriendsProvider::testLoadFriends.toString()
        //Log.i(TAG, "onCreate: HELLO")

    }

    fun friendsLoaded(friendsList: ArrayList<FriendModel>) {
        viewState.endLoading()
        if (friendsList.size == 0) {
            viewState.setupEmptyList()
            viewState.showError(textResource = R.string.friends_no_items)
        } else {
            viewState.setupFriendsList(friendsList = friendsList)
        }
    }
}