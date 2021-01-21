package com.yoond.a0121datause

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_preference.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnResource.setOnClickListener{
            val intent = Intent(this, ResourceReadActivity::class.java)
            startActivity(intent)
        }

        btnSqlite.setOnClickListener{
            val intent = Intent(this, SqliteActivity::class.java)
            startActivity(intent)
        }

        btnPreference.setOnClickListener{
            val intent = Intent(this, PreferenceActivity::class.java)
            startActivity(intent)
        }

    }
}