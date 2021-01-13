package com.rivaldi.githubuserapp.ui.main

import android.view.View
import com.rivaldi.githubuserapp.R
import com.rivaldi.githubuserapp.data.model.User
import com.rivaldi.githubuserapp.databinding.ItemUserBinding
import com.xwray.groupie.databinding.BindableItem

class MainItem(
    private val user: User,
    private val callback: OnClickListener
) : BindableItem<ItemUserBinding>() {

    override fun getLayout() = R.layout.item_user

    override fun bind(viewBinding: ItemUserBinding, position: Int) {
        viewBinding.apply {
            item = user

            val image = rivPhoto
            val name = tvName

            root.setOnClickListener {
                callback.onItemClickListener(image, name, user = user)
            }
        }
    }

    interface OnClickListener {
        fun onItemClickListener(vararg view: View, user: User)
    }
}