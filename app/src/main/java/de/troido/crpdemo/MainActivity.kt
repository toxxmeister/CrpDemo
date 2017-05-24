package de.troido.crpdemo

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import de.troido.crowdroutesdk.service.CrpService
import de.troido.crpdemo.util.check
import de.troido.crpdemo.util.longToast
import de.troido.crpdemo.util.startService

private val PERMISSIONS = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.BLUETOOTH,
        Manifest.permission.BLUETOOTH_ADMIN,
        Manifest.permission.RECEIVE_BOOT_COMPLETED
)

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        PERMISSIONS.filterNot(this::check).let {
            if (it.isEmpty()) {
                start()
            } else {
                ActivityCompat.requestPermissions(this, it.toTypedArray(), 123)
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<out String>,
                                            grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
            start()
        } else {
            longToast(R.string.permission_fail)
            finishAndRemoveTask()
        }
    }

    private fun start() = startService<CrpService>()
}
