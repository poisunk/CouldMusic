package com.example.couldmusic.page.main.activity

import com.example.couldmusic.base.BaseActivity
import android.os.Bundle
import com.example.couldmusic.R
import com.example.couldmusic.page.main.fragment.MainFragment
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import android.content.Intent
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import com.example.couldmusic.page.main.activity.MainActivity

class MainActivity : BaseActivity() {

    private val fragmentManager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addFragment(MainFragment(), "MainFragment")
    }

    /**
     * 获取权限
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @SuppressLint("MissingSuperCall")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            1 -> if (grantResults.size <= 0 || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "权限不够获取不到音乐，程序将退出", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // 获取当前回退栈中的Fragment个数
            val backStackEntryCount = fragmentManager.backStackEntryCount
            // 回退栈中至少有多个fragment,栈底部是首页
            if (backStackEntryCount > 1) {
                // 立即回退一步
                fragmentManager.popBackStackImmediate()
            } else {
                //回退栈中只剩一个时,退出应用
                finish()
            }
        }
        return true
    }

    private fun addFragment(fragment: Fragment, tag: String) {
        val transaction = fragmentManager.beginTransaction()
        transaction.add(R.id.included_interface, fragment, tag)
        transaction.addToBackStack(tag)
        transaction.commit()
    }

    companion object {

        fun startMainActivity(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }
}