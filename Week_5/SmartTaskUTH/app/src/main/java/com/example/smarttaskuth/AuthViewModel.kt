package com.example.smarttaskuth // ⚠️ Đảm bảo dòng này chính xác

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

// Trạng thái của việc đăng nhập
data class SignInState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val error: String? = null
)

class AuthViewModel : ViewModel() {

    private val auth: FirebaseAuth = Firebase.auth

    private val _signInState = MutableStateFlow(SignInState())
    val signInState = _signInState.asStateFlow()

    private lateinit var googleSignInClient: GoogleSignInClient

    // Hàm này sẽ được gọi từ Composable
    fun getGoogleSignInLauncher(
        activity: Activity,
        onResult: (Boolean, String?) -> Unit
    ): ActivityResultLauncher<Intent> {
        return (activity as androidx.activity.ComponentActivity).registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                try {
                    val account = task.getResult(ApiException::class.java)!!
                    firebaseAuthWithGoogle(account.idToken!!, onResult)
                } catch (e: ApiException) {
                    onResult(false, "Google Sign In Failed: ${e.message}")
                }
            } else {
                onResult(false, "Google Sign In Canceled")
            }
        }
    }

    // Hàm này sẽ được gọi từ Composable
    fun signInWithGoogle(context: Context, launcher: ActivityResultLauncher<Intent>) {
        _signInState.value = SignInState(isLoading = true)

        // Cấu hình Google Sign-In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(context.getString(R.string.default_web_client_id)) // Lấy từ file values/strings.xml
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(context, gso)

        launcher.launch(googleSignInClient.signInIntent)
    }

    private fun firebaseAuthWithGoogle(idToken: String, onResult: (Boolean, String?) -> Unit) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        viewModelScope.launch {
            try {
                auth.signInWithCredential(credential).await()
                _signInState.value = SignInState(isSuccess = true)
                onResult(true, null)
            } catch (e: Exception) {
                _signInState.value = SignInState(error = e.message)
                onResult(false, e.message)
            }
        }
    }

    fun signOut(context: Context, onSignedOut: () -> Unit) {
        viewModelScope.launch {
            try {
                auth.signOut()
                // Cần client gso để đăng xuất khỏi Google
                val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(context.getString(R.string.[[default_web_client_id]]))
                    .requestEmail()
                    .build()
                GoogleSignIn.getClient(context, gso).signOut().await()

                onSignedOut()
            } catch (e: Exception) {
                // Xử lý lỗi nếu muốn
            }
        }
    }
}