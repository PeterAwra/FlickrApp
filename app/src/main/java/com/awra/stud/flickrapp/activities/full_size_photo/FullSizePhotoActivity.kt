package com.awra.stud.flickrapp.activities.full_size_photo

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.DocumentsContract
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.awra.stud.flickrapp.App
import com.awra.stud.flickrapp.R
import com.awra.stud.flickrapp.model.Photo
import com.awra.stud.flickrapp.repository.Repository
import com.awra.stud.flickrapp.tools.urlPhoto
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.activity_full_size_photo.*
import java.io.File
import javax.inject.Inject

class FullSizePhotoActivity() : AppCompatActivity() {
    @Inject
    lateinit var adapter: FullPhotoAdapter

    @Inject
    lateinit var repository: Repository<Photo>

    var tempPhoto: Photo? = null

    val pagerSnapHelper = MyPagerSnapHelper()
    val handler = Handler(Looper.getMainLooper())
    var visibleToolbar = true
        set(value) {
            if (field != value)
                if (value) showToolbar() else hideToolbar()
            field = value
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_size_photo)
        setSupportActionBar(toolbar_full_photo)
        App.dagger.inject(this)
        repository.photos().observe(this, Observer { adapter.submitList(it) })
        val startPosition = intent.getIntExtra("POSITION_PHOTO", 0)
        pagerSnapHelper.currentPositionCallback = { changeTitleByPosition(it) }
        pagerSnapHelper.attachToRecyclerView(rv_full_photo)
        rv_full_photo.adapter = adapter
        rv_full_photo.scrollToPosition(startPosition)
        changeTitleByPosition(startPosition)
        rv_full_photo.addOnItemTouchListener(object : RecyclerView.SimpleOnItemTouchListener() {
            var temp: Boolean = false
            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                if (temp && e.action == MotionEvent.ACTION_UP)
                    visibleToolbar = !visibleToolbar
                temp = e.action == MotionEvent.ACTION_DOWN
                return false
            }
        })
        toolbar_full_photo.setNavigationOnClickListener { finish() }
    }

    private fun changeTitleByPosition(position: Int) {
        changeTitle(adapter.getItem(position))
    }

    private fun changeTitle(item: Photo?) {
        tempPhoto = item
        toolbar_full_photo_title?.text = item?.title ?: ""
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
//        handler.postDelayed({ visibleToolbar = false }, 1000)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_fullscreen_photo, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun hideToolbar() {
        toolbar_full_photo.visibility = View.GONE
        rv_full_photo.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LOW_PROFILE or
                    View.SYSTEM_UI_FLAG_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
    }

    private fun showToolbar() {
        toolbar_full_photo.visibility = View.VISIBLE
        rv_full_photo.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        tempPhoto?.let {
            when (item.itemId) {
                R.id.menu_save -> {
                    val pathname = "dddd.jpg"
                    val file = File(pathname)
                    Glide.with(this).asFile().load(it.urlPhoto())
                        .listener(object : RequestListener<File> {
                            override fun onLoadFailed(
                                e: GlideException?,
                                model: Any?,
                                target: Target<File>?,
                                isFirstResource: Boolean
                            ): Boolean {
                                return true
                            }

                            override fun onResourceReady(
                                resource: File?,
                                model: Any?,
                                target: Target<File>?,
                                dataSource: DataSource?,
                                isFirstResource: Boolean
                            ): Boolean {
                                resource?.let { createFile(it) }
                                return true
                            }
                        })
                    createFile(file)
                }
                R.id.menu_share -> {
                    Intent(Intent.ACTION_SEND).apply {
                        type = "text/plain"
                        putExtra(Intent.EXTRA_TEXT, it.urlPhoto())
                        putExtra(Intent.EXTRA_SUBJECT, it.title)
                        startActivity(Intent.createChooser(this, "Share"))
                    }
                }
                else -> {
                }
            }
        }
        return true
    }

    fun createFile(file: File) {
        Intent(Intent.ACTION_CREATE_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "application/jpg"
            putExtra(DocumentsContract.EXTRA_ERROR, Uri.fromFile(file))
            startActivityForResult(this, 25)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }
}

