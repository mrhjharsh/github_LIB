package com.example.gitapi

import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.example.gitapi.ui.theme.GitApiTheme
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var imgurl: String = ""
        var username: String = ""

        var usernamess: TextView = findViewById(R.id.usernames)
        val recyclerview = findViewById<RecyclerView>(R.id.rv)
        val et = findViewById<EditText>(R.id.et)
        val btn = findViewById<Button>(R.id.btn)

        recyclerview.layoutManager = LinearLayoutManager(this)
        btn.setOnClickListener {
            if (et.text.isBlank()) {
                Toast.makeText(this, "Enter Valid Repository", Toast.LENGTH_LONG).show()
            } else {
                usernamess.visibility = View.VISIBLE
                val queue = Volley.newRequestQueue(this)
                val url = "https://api.github.com/users/" + et.text + "/repos"
                val stringRequest = StringRequest(
                    Request.Method.GET, url,
                    { response ->
                        var ja = JSONArray(response)

                        for (i in 0..ja.length() - 1) {
                            var jo = ja.getJSONObject(i)
                            var owner = jo.getJSONObject("owner")
                            database.Title.add(jo.getString("name").toString())
                            database.Created_date.add(jo.getString("created_at").toString())
                            database.closed_date.add(jo.getString("pushed_at").toString())
                            imgurl = owner.getString("avatar_url").toString()
                            username = owner.getString("login").toString()
                        }
                        var imageView: ImageView = findViewById(R.id.img)
                        usernamess.text = username
                        Glide.with(this).load(imgurl).into(imageView)
                        var c = CustomAdapter(this)
                        recyclerview.adapter = c
                    },
                    {
                        Toast.makeText(this, "Enter Valid Repository", Toast.LENGTH_LONG).show()
                    })
                queue.add(stringRequest)
            }

        }


    }

}