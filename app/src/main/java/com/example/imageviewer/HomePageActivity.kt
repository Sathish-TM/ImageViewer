package com.example.imageviewer

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.database.Cursor
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar


class HomePageActivity : AppCompatActivity() {

    // define the declaration
    private var imageViewer: RecyclerView? = null;
    private val REQUEST_WRITE_PERMISSION = 786


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)
        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        imageViewer = findViewById(R.id.imageViewer) as RecyclerView

        imageViewer!!.setLayoutManager(
            StaggeredGridLayoutManager(
                2,
                StaggeredGridLayoutManager.VERTICAL
            )
        )

        setupPermissions()

        // set the coding for the recyclerview adapter
    }

    private fun setupPermissions() {
        val permission = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )

        if (permission != PackageManager.PERMISSION_GRANTED) {
            Log.i("TAG", "Permission to record denied")
            makeRequest()
        }else{
            val imagelist: java.util.ArrayList<String> = fetchGalleryImages(this)!!
            val adapter = ImageAdapter(this, imagelist)
            imageViewer!!.setAdapter(adapter)
            val decoration = SpacesItemDecoration(20)
            imageViewer!!.addItemDecoration(decoration)
        }
    }

    private fun makeRequest() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
            REQUEST_WRITE_PERMISSION
        )
    }

    fun fetchGalleryImages(context: Activity): ArrayList<String>? {
        val galleryImageUrls: ArrayList<String>
        val columns = arrayOf(
            MediaStore.Images.Media.DATA,
            MediaStore.Images.Media._ID
        ) //get all columns of type images
        val orderBy = MediaStore.Images.Media.DATE_TAKEN //order data by date
        val imagecursor: Cursor = context.managedQuery(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns, null,
            null, "$orderBy DESC"
        ) //get all data in Cursor by sorting in DESC order
        galleryImageUrls = ArrayList()
        for (i in 0 until imagecursor.getCount()) {
            imagecursor.moveToPosition(i)
            val dataColumnIndex: Int =
                imagecursor.getColumnIndex(MediaStore.Images.Media.DATA) //get column index
            galleryImageUrls.add(imagecursor.getString(dataColumnIndex)) //get Image from column index
        }
        Log.e("fatch in", "images")
        return galleryImageUrls
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            REQUEST_WRITE_PERMISSION -> {

                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {

                    Log.i("TAG", "Permission has been denied by user")
                } else {
                    Log.i("TAG", "Permission has been granted by user")
                }
            }
        }
    }
}