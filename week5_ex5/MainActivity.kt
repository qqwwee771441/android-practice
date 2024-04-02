package com.example.week55

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.example.week55.databinding.ActivityMainBinding
import androidx.viewpager2.widget.ViewPager2


class MainActivity : AppCompatActivity() {
    lateinit var toggle : ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        var binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //TODO : viewpager 추가
        binding.viewpager.adapter = MyPagerAdapter()
        binding.viewpager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        //drawer 추가
        val drawer = findViewById<DrawerLayout>(R.id.drawer)

        //TODO : ActionBarDrawerToggle 추가
        toggle = ActionBarDrawerToggle(this, binding.drawer, R.string.drawer)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toggle.syncState()
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){return true}
        return super.onOptionsItemSelected(item)
    }
}
