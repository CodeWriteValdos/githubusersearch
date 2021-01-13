package com.rivaldi.githubuserapp.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.rivaldi.githubuserapp.R
import com.rivaldi.githubuserapp.base.BaseActivity
import com.rivaldi.githubuserapp.data.model.Resource
import com.rivaldi.githubuserapp.data.model.User
import com.rivaldi.githubuserapp.data.model.UserDetail
import com.rivaldi.githubuserapp.databinding.ActivityDetailBinding
import com.rivaldi.githubuserapp.ui.ViewModelFactory
import com.rivaldi.githubuserapp.util.ext.observe
import com.rivaldi.githubuserapp.util.ext.toGone
import com.rivaldi.githubuserapp.util.ext.toVisible
import com.rivaldi.githubuserapp.util.ext.toast
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject


class DetailActivity : BaseActivity() {

    private lateinit var menuItem: MenuItem

    companion object {
        private const val EXTRA_USER = "user"

        fun newIntent(context: Context, user: User): Intent {
            return Intent(context, DetailActivity::class.java).apply {
                putExtra(EXTRA_USER, user)
            }
        }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        initView()
        observe()
    }

    private fun initViewModel() {
        val binding: ActivityDetailBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_detail)
        viewModel = ViewModelProvider(this, viewModelFactory).get(DetailViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }

    private fun initView() {
        intent.getParcelableExtra<User>(EXTRA_USER)?.let {
            initToolbar(it.login)
            if (viewModel.user.value == null) {
                viewModel.setUser(it)
                viewModel.getDetail(it.login)
            }
        }
    }

    private fun initToolbar(username: String) {
        toolbar.title = username
        setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.setHomeAsUpIndicator(R.drawable.ic_back)
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowHomeEnabled(true)
        }
    }

    private fun observe() {
        observe(viewModel.userDetailResponse, ::updateUI)
    }

    private fun updateUI(source: Resource<UserDetail>) {
        showLoading(false)
        when (source) {
            is Resource.Loading -> showLoading(true)
            is Resource.Success -> {
                source.data?.let {
                    viewModel.setUserDetail(it)
                }
            }
            is Resource.DataError -> {
                showError(source.error?.description.toString())
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            loading.startShimmer()
            loading.toVisible()
            linDetail.toGone()
        } else {
            loading.stopShimmer()
            loading.toGone()
            linDetail.toVisible()
        }
    }

    private fun showError(message: String) {
        toast(message)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
