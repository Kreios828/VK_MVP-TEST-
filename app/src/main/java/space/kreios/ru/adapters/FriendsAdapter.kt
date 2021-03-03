package space.kreios.ru.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import space.kreios.ru.R
import space.kreios.ru.models.FriendModel


class FriendsAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var mFriendsList:ArrayList<FriendModel> = ArrayList()

    fun setupFriends(friendList: ArrayList<FriendModel>) {
        mFriendsList.clear()
        mFriendsList.addAll(friendList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.cell_friend, parent, false)
        return FriendsViewHolder(itemView = itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is FriendsViewHolder) {
            holder.bind(friendModel =  mFriendsList[position])
        }
    }

    override fun getItemCount(): Int {
        return mFriendsList.count()
    }

    class FriendsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private var mCivAvatar: CircleImageView = itemView.findViewById(R.id.friend_civ_avatar)
        private var mTxtUsername: TextView = itemView.findViewById(R.id.friend_txt_username)
        private var mTxtCity: TextView = itemView.findViewById(R.id.friend_txt_city)
        private var mImgOnline: View = itemView.findViewById(R.id.friend_img_online)


        @SuppressLint("SetTextI18n")
        fun bind(friendModel: FriendModel) {
            friendModel.avatar?.let {
                url -> Picasso.with(itemView.context).load(url).into(mCivAvatar)
            }


            mTxtUsername.text = "${friendModel._name} ${friendModel.surname}"
            mTxtCity.text = itemView.context.getString(R.string.friend_no_city)
            friendModel.city?.let { city -> mTxtCity.text = city }

           if (friendModel.isOnline) {
               mImgOnline.visibility - View.VISIBLE
           } else {
               mImgOnline.visibility = View.GONE
           }
        }
    }
}