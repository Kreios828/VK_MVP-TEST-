package space.kreios.ru.activities

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.github.rahatarmanahmed.cpv.CircularProgressView
import space.kreios.ru.R
import space.kreios.ru.adapters.FriendsAdapter
import space.kreios.ru.models.FriendModel
import space.kreios.ru.presenters.FriendsPresenter
import space.kreios.ru.views.FriendsView


class FriendsActivity : MvpAppCompatActivity(), FriendsView {

    @InjectPresenter
    lateinit var friendsPresenter: FriendsPresenter

    private lateinit var mRvFriends: RecyclerView
    private lateinit var mTxtNoItems: TextView
    private lateinit var mCpvWait: CircularProgressView
    private lateinit var mAdapter: FriendsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friends)

        mRvFriends = findViewById(R.id.recycler_friends)
        mTxtNoItems = findViewById(R.id.txt_fiends_no_items)
        mCpvWait = findViewById(R.id.cpv_friends)

        friendsPresenter.loadFriends()
        mAdapter = FriendsAdapter()

        mRvFriends.adapter = mAdapter
        mRvFriends.layoutManager = LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)
        mRvFriends.setHasFixedSize(true)


        //Toast.makeText(this,friendsPresenter.toString(),Toast.LENGTH_LONG).show()
        //var TAG = setupFriendsList().toString()
        //Log.i(TAG, "onCreate: HELLO")
    }

    override fun showError(textResource: Int) {
        mTxtNoItems.text = getString(textResource)
    }

    override fun setupEmptyList() {
        mRvFriends.visibility = View.GONE
        mTxtNoItems.visibility = View.VISIBLE
    }

    override fun setupFriendsList(friendsList: ArrayList<FriendModel>) {
        mRvFriends.visibility - View.VISIBLE
        mTxtNoItems.visibility = View.GONE

        mAdapter.setupFriends(friendList = friendsList)
    }

    override fun startLoading() {
        mRvFriends.visibility = View.GONE
        mTxtNoItems.visibility = View.GONE
        mCpvWait.visibility = View.VISIBLE
    }

    override fun endLoading() {
        mCpvWait.visibility = View.GONE
    }
}