package com.yoond.a0121datause

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_preference.*

class PreferenceActivity : AppCompatActivity() {
    // 지연생성을 이용해서 환경 설정 객체 생성
    val preference by lazy {getSharedPreferences("PreferenceActivity", Context.MODE_PRIVATE)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preference)

        saveButton.setOnClickListener{
            preference.edit().putString("namefield", nameField.text.toString()).apply()
            preference.edit().putBoolean(
                    "pushCheckBoxField", pushCheckBox.isChecked).apply()
        }

        loadButton.setOnClickListener{
            nameField.setText(
                    preference.getString("nameField", ""))
            pushCheckBox.isChecked = preference.getBoolean("pushCheckBoxField", false)
        }
    }
}