package client.stone.hackathon.cmepclient.login

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import client.stone.hackathon.cmepclient.R
import client.stone.hackathon.cmepclient.main.MainActivity
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity(), GoogleApiClient.OnConnectionFailedListener {


    companion object {
        val TAG: String = LoginActivity.javaClass.simpleName
        val RC_SIGN_IN: Int = 1001
    }

    lateinit var gso: GoogleSignInOptions
    lateinit var mGoogleClient: GoogleApiClient
    lateinit var mAuthListener: FirebaseAuth.AuthStateListener
    lateinit var mAuth: FirebaseAuth

    override fun onStart() {
        super.onStart()
        mAuth.addAuthStateListener(mAuthListener)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mAuth = FirebaseAuth.getInstance()
        execute()
        signIn.setOnClickListener { signIn() }
    }

    override fun onStop() {
        super.onStop()
        mAuth.removeAuthStateListener(mAuthListener)
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


    fun firebaseAuthListener() {
        mAuthListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            val user = firebaseAuth.currentUser
            if (user != null) {
                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Error Login", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun execute() {
        firebaseAuthListener()
        gso()
        googleClient()
        mAuth.addAuthStateListener(mAuthListener)
    }


    private fun signIn() {
        val signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleClient)
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, { task ->
                    Log.d(TAG, "signInWithCredential:onComplete:" + task.isSuccessful)
                    if (!task.isSuccessful) {
                        Log.w(TAG, "signInWithCredential", task.exception)
                        Toast.makeText(this, "Authentication failed.", Toast.LENGTH_SHORT).show()
                    }
                })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            if (result.isSuccess) {
                // Google Sign In was successful, authenticate with Firebase
                val account = result.signInAccount
                if (account != null)
                    firebaseAuthWithGoogle(account)
                else
                    Log.e("account", "null")
            }
        }
    }


    override fun onConnectionFailed(p0: ConnectionResult) {

    }

}
