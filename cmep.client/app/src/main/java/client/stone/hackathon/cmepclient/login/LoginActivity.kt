package client.stone.hackathon.cmepclient.login

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import client.stone.hackathon.cmepclient.R
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.GoogleApiClient


class LoginActivity : AppCompatActivity() {

    lateinit var gso: GoogleSignInOptions
    lateinit var mGoogleClient: GoogleApiClient
    override fun onStart() {
        super.onStart()
        execute()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun gso() {
        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
    }


    fun googleClient() {
        mGoogleClient = GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build()
    }

    fun execute() {
        gso()
        googleClient()
    }


}
