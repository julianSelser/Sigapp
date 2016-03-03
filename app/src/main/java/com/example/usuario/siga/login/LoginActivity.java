package com.example.usuario.siga.login;

import android.support.v7.app.AppCompatActivity;

import android.os.AsyncTask;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.usuario.siga.R;

/**
 * A login screen that offers login via cip/password
 */
public class LoginActivity extends AppCompatActivity {

    private AutoCompleteTextView mCipView;
    private EditText mPasswordView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mCipView = (AutoCompleteTextView) findViewById(R.id.cip);
        mPasswordView = (EditText) findViewById(R.id.password);

        Button mSignInButton = (Button) findViewById(R.id.sign_in_button);
        mSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

    }

    /**
     * Attempts to sign in or register the account specified by the login form
     * If there are form errors the errors are presented and no actual login attempt is made
     */
    private void attemptLogin() {
        // Reset errors
        mCipView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt
        String cip = mCipView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;

        // Check for a valid password, if the user entered one
        if (TextUtils.isEmpty(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            cancel = true;
        }

        // Check for a valid cip
        if (TextUtils.isEmpty(cip)) {
            mCipView.setError(getString(R.string.error_field_required));
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login
        } else {
            // kick off a background task to perform the user login attempt
        }
    }
}

