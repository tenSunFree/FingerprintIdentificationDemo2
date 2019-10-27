package com.home.fingerprintidentificationdemo2

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.nestia.biometriclib.BiometricPromptManager
import kotlinx.android.synthetic.main.activity_lock.*

class LockActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lock)
        initializeView()
        initializeBiometricLibrary()
    }

    private fun initializeView() {
        Glide.with(this).load(R.drawable.icon_lock)
            .into(image_view_background)
        Glide.with(this).load(R.drawable.icon_fingerprint)
            .into(image_view_fingerprint)
    }

    private fun initializeBiometricLibrary() {
        val mManager = BiometricPromptManager.from(this)
        // 判斷你的設備在系統設置裡面是否設置了指紋
        if (!mManager.hasEnrolledFingerprints())
            Toast.makeText(this@LockActivity, "請先至系統設置指紋", Toast.LENGTH_SHORT).show()
        if (mManager.isBiometricPromptEnable) {
            mManager.authenticate(object : BiometricPromptManager.OnBiometricIdentifyCallback {
                override fun onError(code: Int, reason: String) {
                    Toast.makeText(this@LockActivity, "指紋解鎖失敗次數太多，請30秒後重試。", Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onSucceeded() {
                    startActivity(Intent(this@LockActivity, HomeActivity::class.java))
                    finish()
                }

                override fun onCancel() {
                    finish()
                }

                override fun onUsePassword() {}
                override fun onFailed() {}
            })
        }
    }
}
