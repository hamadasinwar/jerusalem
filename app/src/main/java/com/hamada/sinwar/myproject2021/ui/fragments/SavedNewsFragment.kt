package com.hamada.sinwar.myproject2021.ui.fragments

import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.hamada.sinwar.myproject2021.ui.NewsActivity
import com.hamada.sinwar.myproject2021.ui.NewsViewModel
import com.hamada.sinwar.myproject2021.R
import com.hamada.sinwar.myproject2021.adapters.NewsAdapter
import kotlinx.android.synthetic.main.fragment_saved_news.*

class SavedNewsFragment : Fragment(R.layout.fragment_saved_news), NewsAdapter.OnClickItem {

    lateinit var viewModel: NewsViewModel
    lateinit var newsAdapter:NewsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsActivity).viewModel
        setupRecyclerView()

        val itemTouchHelperCallBack = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val article = newsAdapter.differ.currentList[position]
                viewModel.deleteArticle(article)
                Snackbar.make(view, "Article deleted Successfully", Snackbar.LENGTH_LONG).apply {
                    setAction("Undo"){
                        viewModel.saveArticle(article)
                    }
                    show()
                }
            }
        }

        ItemTouchHelper(itemTouchHelperCallBack).attachToRecyclerView(rvSavedNews)

        viewModel.getSavedNews().observe(viewLifecycleOwner, { articles ->
            newsAdapter.differ.submitList(articles)
        })
    }

    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter(activity as Activity,this)
        rvSavedNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
    override fun onArticleClicked(position: Int) {
        val bundle = Bundle().apply {
            putSerializable("article", newsAdapter.differ.currentList[position])
        }
        findNavController().navigate(R.id.action_savedNewsFragment_to_articleFragment, bundle)
    }
}