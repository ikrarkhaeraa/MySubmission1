package com.example.mysubmission1.main.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mysubmission1.R
import com.example.mysubmission1.databinding.ActivityStoryBinding
import com.example.mysubmission1.main.ListStoryAdapter
import com.example.mysubmission1.main.ModelFactory
import com.example.mysubmission1.main.model.StoryModel


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStoryBinding
    private lateinit var rvStory: RecyclerView
    private lateinit var factory: ModelFactory
    private val model: StoryModel by viewModels { factory }
    private var token = ""

    companion object {
        private const val TAG = "MainActivity"
        const val EXTRA_LOGIN = "extra_login"
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityStoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Story App"
        factory = ModelFactory.getInstance(this)

        val layoutManager = LinearLayoutManager(this)
        //binding.listStory.adapter = listStoryAdapter
        binding.listStory.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.listStory.addItemDecoration(itemDecoration)

        rvStory = findViewById(R.id.listStory)
        binding.listStory.setHasFixedSize(true)

        settingAdapter()
        isLogin()
        //goToDetail()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add -> {
                val i = Intent(this, AddStoryActivity::class.java)
                startActivity(i)
                return true
            }
            R.id.logout-> {
                model.userLogout()
                onDestroy()
                return true
            }
            else -> return true
        }
    }

    private fun isLogin() {
        showLoading(true)
        model.getUserSession().observe(this@MainActivity) {
            token = it.token
            if (!it.isLogin) {
                val intent = Intent(this@MainActivity, FirstActivity::class.java)
                startActivity(intent)
            } else {
                showLoading(false)
                model.getStory(token)
            }
        }
    }

    private fun settingAdapter() {
        model.listStory.observe(this@MainActivity) {
                adapter ->
            if (adapter != null) {
                binding.listStory.adapter = ListStoryAdapter(adapter.listStory)
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)
        return true
    }

}