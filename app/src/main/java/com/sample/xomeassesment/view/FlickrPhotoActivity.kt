package com.sample.xomeassesment.view

import android.os.Build
import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.observe
import com.sample.xomeassesment.R
import com.sample.xomeassesment.databinding.ActivityMainBinding
import com.sample.xomeassesment.isNetworkAvailable
import com.sample.xomeassesment.viewmodel.FlickrPhotoViewModel

class FlickrPhotoActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var flickrPhotoViewModel: FlickrPhotoViewModel = FlickrPhotoViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewModel = flickrPhotoViewModel
        binding.titleSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                getData(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun getData(name: String) {
        if (isNetworkAvailable(this)) {
            flickrPhotoViewModel.getSearchImages(name).observe(
                this,
                {
                    it.onSuccess {
                        it?.photos?.photo?.let {
                            binding?.rvImages?.adapter = FlickrPhotoAdapter(it)
                        }
                    }
                    it.onFailure {
                        Toast.makeText(this, R.string.no_data_found, Toast.LENGTH_SHORT).show()
                    }
                }
            )
        } else {
            Toast.makeText(this, R.string.network_issue, Toast.LENGTH_SHORT).show()
        }
    }
}
