package de.troido.crpdemo

import android.Manifest
import android.content.ComponentName
import android.content.ServiceConnection
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.IBinder
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import de.troido.crowdroutesdk.service.CrpListener
import de.troido.crowdroutesdk.service.CrpMessage
import de.troido.crowdroutesdk.service.CrpService
import de.troido.crowdroutesdk.service.CrpService.CrpServiceBinder
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

    val serviceConnection = object: ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder: CrpServiceBinder = service as CrpServiceBinder

            val listener = object: CrpListener {
                override val id: Short
                    get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

                override fun onAckDelivered() {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onAckReceived() {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onMessage(msg: CrpMessage) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

            }
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }


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
