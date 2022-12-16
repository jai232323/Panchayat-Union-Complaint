package com.example.puc.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.puc.R;
import com.example.puc.activity.OfficerEmailActivity.BeemakulamActivity;
import com.example.puc.activity.OfficerEmailActivity.DevasathanamActivity;
import com.example.puc.activity.OfficerEmailActivity.EchangalActivity;
import com.example.puc.activity.OfficerEmailActivity.ElayanagaramActivity;
import com.example.puc.activity.OfficerEmailActivity.GirisamuthiramActivity;
import com.example.puc.activity.OfficerEmailActivity.GollakuppamActivity;
import com.example.puc.activity.OfficerEmailActivity.GovindapuramActivity;
import com.example.puc.activity.OfficerEmailActivity.JaffarabadActivity;
import com.example.puc.activity.OfficerEmailActivity.KothakottaiActivity;
import com.example.puc.activity.OfficerEmailActivity.MadhanancheriActivity;
import com.example.puc.activity.OfficerEmailActivity.MarimanikuppamActivity;
import com.example.puc.activity.OfficerEmailActivity.MitturActivity;
import com.example.puc.activity.OfficerEmailActivity.NaickanurActivity;
import com.example.puc.activity.OfficerEmailActivity.NarasingapuramActivity;
import com.example.puc.activity.OfficerEmailActivity.NekkanamalaiActivity;
import com.example.puc.activity.OfficerEmailActivity.NimmiyambattuActivity;
import com.example.puc.activity.OfficerEmailActivity.PallipattuActivity;
import com.example.puc.activity.OfficerEmailActivity.PeriyakurumbatheruActivity;
import com.example.puc.activity.OfficerEmailActivity.PethaveppambattuActivity;
import com.example.puc.activity.OfficerEmailActivity.PoongulamActivity;
import com.example.puc.activity.OfficerEmailActivity.ReddiurActivity;
import com.example.puc.activity.OfficerEmailActivity.SammanthikuppamActivity;
import com.example.puc.activity.OfficerEmailActivity.ValayambattuActivity;
import com.example.puc.activity.OfficerEmailActivity.VallipattuActivity;
import com.example.puc.activity.OfficerEmailActivity.VelathigamanibendaActivity;
import com.example.puc.activity.OfficerEmailActivity.VellakuttaiActivity;
import com.example.puc.activity.OfficerEmailActivity.VijilapuramActivity;

public class OfficersActivity extends AppCompatActivity {

    //Initialize Variables
    EditText OfficerEmail,OfficerPassword;
    Button OfficerLoginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_officers);

        //Assign Variable
        OfficerEmail = findViewById(R.id.OfficerEmail);
        OfficerPassword = findViewById(R.id.OfficerPassword);
        OfficerLoginBtn = findViewById(R.id.OfficerLoginBtn);

        OfficerLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = OfficerEmail.getText().toString();
                String Password = OfficerPassword.getText().toString();

                //Email
                if (Email.isEmpty()) {
                    OfficerEmail.setError("Required Email");
                    OfficerEmail.requestFocus();
                }
                //Password
                else if (Password.isEmpty()) {
                    OfficerPassword.setError("Required Password");
                    OfficerPassword.requestFocus();
                } else {

                    Beemakulam(Email, Password);
                    Devasathanam(Email, Password);
                    Echangal(Email, Password);
                    Elayanagaram(Email, Password);
                    Girisamuthiram(Email, Password);
                    Gollakuppam(Email, Password);
                    Govindapuram(Email, Password);
                    Jaffarabad(Email, Password);
                    Kothakottai(Email, Password);
                    Madhanancheri(Email, Password);
                    Marimanikuppam(Email, Password);
                    Mittur(Email, Password);
                    Naickanur(Email, Password);
                    Narasingapuram(Email, Password);
                    Nekkanamalai(Email, Password);
                    Nimmiyambattu(Email, Password);
                    Pallipattu(Email, Password);
                    Periyakurumbatheru(Email, Password);
                    Pethaveppambattu(Email, Password);
                    Poongulam(Email, Password);
                    Reddiur(Email, Password);
                    Sammanthikuppam(Email, Password);
                    Valayambattu(Email, Password);
                    Vallipattu(Email, Password);
                    Velathigamanibenda(Email, Password);
                    Vellakuttai(Email, Password);
                    Vijilapuram(Email, Password);
                }
            }
        });
    }



    private void Vijilapuram(String email, String password) {
        if (email.equals("Vijilapuram@gmail.com") && password.equals("Vijilapuram123")) {
            Intent intent = new Intent(OfficersActivity.this, VijilapuramActivity.class);
            startActivity(intent);

            finish();
        } else {
            Toast.makeText(OfficersActivity.this, "Something Wrong", Toast.LENGTH_SHORT).show();
        }
    }

    private void Vellakuttai(String email, String password) {
        if (email.equals("Vellakuttai@gmail.com") && password.equals("Vellakuttai123")) {
            Intent intent = new Intent(OfficersActivity.this, VellakuttaiActivity.class);
            startActivity(intent);

            finish();
        } else {
            Toast.makeText(OfficersActivity.this, "Something Wrong", Toast.LENGTH_SHORT).show();
        }
    }

    private void Velathigamanibenda(String email, String password) {
        if (email.equals("Velathigamanibenda@gmail.com") && password.equals("Velathigamanibenda123")) {
            Intent intent = new Intent(OfficersActivity.this, VelathigamanibendaActivity.class);
            startActivity(intent);

            finish();
        } else {
            Toast.makeText(OfficersActivity.this, "Something Wrong", Toast.LENGTH_SHORT).show();
        }
    }

    private void Vallipattu(String email, String password) {
        if (email.equals("Vallipattu@gmail.com") && password.equals("Vallipattu123")) {
            Intent intent = new Intent(OfficersActivity.this, VallipattuActivity.class);
            startActivity(intent);

            finish();
        } else {
            Toast.makeText(OfficersActivity.this, "Something Wrong", Toast.LENGTH_SHORT).show();
        }
    }

    private void Valayambattu(String email, String password) {
        if (email.equals("Valayambattu@gmail.com") && password.equals("Valayambattu123")) {
            Intent intent = new Intent(OfficersActivity.this, ValayambattuActivity.class);
            startActivity(intent);

            finish();
        } else {
            Toast.makeText(OfficersActivity.this, "Something Wrong", Toast.LENGTH_SHORT).show();
        }
    }

    private void Sammanthikuppam(String email, String password) {
        if (email.equals("Sammanthikuppam@gmail.com") && password.equals("Sammanthikuppam123")) {
            Intent intent = new Intent(OfficersActivity.this, SammanthikuppamActivity.class);
            startActivity(intent);

            finish();
        } else {
            Toast.makeText(OfficersActivity.this, "Something Wrong", Toast.LENGTH_SHORT).show();
        }
    }

    private void Reddiur(String email, String password) {
        if (email.equals("Reddiur@gmail.com") && password.equals("Reddiur123")) {
            Intent intent = new Intent(OfficersActivity.this, ReddiurActivity.class);
            startActivity(intent);

            finish();
        } else {
            Toast.makeText(OfficersActivity.this, "Something Wrong", Toast.LENGTH_SHORT).show();
        }
    }

    private void Poongulam(String email, String password) {
        if (email.equals("Poongulam@gmail.com") && password.equals("Poongulam123")) {
            Intent intent = new Intent(OfficersActivity.this, PoongulamActivity.class);
            startActivity(intent);

            finish();
        } else {
            Toast.makeText(OfficersActivity.this, "Something Wrong", Toast.LENGTH_SHORT).show();
        }
    }

    private void Pethaveppambattu(String email, String password) {
        if (email.equals("Pethaveppambattu@gmail.com") && password.equals("Pethaveppambattu123")) {
            Intent intent = new Intent(OfficersActivity.this, PethaveppambattuActivity.class);
            startActivity(intent);

            finish();
        } else {
            Toast.makeText(OfficersActivity.this, "Something Wrong", Toast.LENGTH_SHORT).show();
        }
    }

    private void Periyakurumbatheru(String email, String password) {
        if (email.equals("Periyakurumbatheru@gmail.com") && password.equals("Periyakurumbatheru123")) {
            Intent intent = new Intent(OfficersActivity.this, PeriyakurumbatheruActivity.class);
            startActivity(intent);

            finish();
        } else {
            Toast.makeText(OfficersActivity.this, "Something Wrong", Toast.LENGTH_SHORT).show();
        }
    }

    private void Pallipattu(String email, String password) {
        if (email.equals("Pallipattu@gmail.com") && password.equals("Pallipattu123")) {
            Intent intent = new Intent(OfficersActivity.this, PallipattuActivity.class);
            startActivity(intent);

            finish();
        } else {
            Toast.makeText(OfficersActivity.this, "Something Wrong", Toast.LENGTH_SHORT).show();
        }
    }

    private void Nimmiyambattu(String email, String password) {
        if (email.equals("Nimmiyambattu@gmail.com") && password.equals("Nimmiyambattu123")) {
            Intent intent = new Intent(OfficersActivity.this, NimmiyambattuActivity.class);
            startActivity(intent);

            finish();
        } else {
            Toast.makeText(OfficersActivity.this, "Something Wrong", Toast.LENGTH_SHORT).show();
        }
    }

    private void Nekkanamalai(String email, String password) {
        if (email.equals("Nekkanamalai@gmail.com") && password.equals("Nekkanamalai123")) {
            Intent intent = new Intent(OfficersActivity.this, NekkanamalaiActivity.class);
            startActivity(intent);

            finish();
        } else {
            Toast.makeText(OfficersActivity.this, "Something Wrong", Toast.LENGTH_SHORT).show();
        }
    }

    private void Narasingapuram(String email, String password) {
        if (email.equals("Narasingapuram@gmail.com") && password.equals("Narasingapuram123")) {
            Intent intent = new Intent(OfficersActivity.this, NarasingapuramActivity.class);
            startActivity(intent);

            finish();
        } else {
            Toast.makeText(OfficersActivity.this, "Something Wrong", Toast.LENGTH_SHORT).show();
        }
    }

    private void Naickanur(String email, String password) {
        if (email.equals("Naickanur@gmail.com") && password.equals("Naickanur123")) {
            Intent intent = new Intent(OfficersActivity.this, NaickanurActivity.class);
            startActivity(intent);

            finish();
        } else {
            Toast.makeText(OfficersActivity.this, "Something Wrong", Toast.LENGTH_SHORT).show();
        }
    }

    private void Mittur(String email, String password) {
        if (email.equals("Mittur@gmail.com") && password.equals("Mittur123")) {
            Intent intent = new Intent(OfficersActivity.this, MitturActivity.class);
            startActivity(intent);

            finish();
        } else {
            Toast.makeText(OfficersActivity.this, "Something Wrong", Toast.LENGTH_SHORT).show();
        }
    }

    private void Marimanikuppam(String email, String password) {
        if (email.equals("Marimanikuppam@gmail.com") && password.equals("Marimanikuppam123")) {
            Intent intent = new Intent(OfficersActivity.this, MarimanikuppamActivity.class);
            startActivity(intent);

            finish();
        } else {
            Toast.makeText(OfficersActivity.this, "Something Wrong", Toast.LENGTH_SHORT).show();
        }
    }

    private void Madhanancheri(String email, String password) {
        if (email.equals("Madhanancheri@gmail.com") && password.equals("Madhanancheri123")) {
            Intent intent = new Intent(OfficersActivity.this, MadhanancheriActivity.class);
            startActivity(intent);

            finish();
        } else {
            Toast.makeText(OfficersActivity.this, "Something Wrong", Toast.LENGTH_SHORT).show();
        }
    }

    private void Kothakottai(String email, String password) {
        if (email.equals("Kothakottai@gmail.com") && password.equals("Kothakottai123")) {
            Intent intent = new Intent(OfficersActivity.this, KothakottaiActivity.class);
            startActivity(intent);

            finish();
        } else {
            Toast.makeText(OfficersActivity.this, "Something Wrong", Toast.LENGTH_SHORT).show();
        }
    }

    private void Jaffarabad(String email, String password) {
        if (email.equals("Jaffarabad@gmail.com") && password.equals("JaffarabadActivity123")) {
            Intent intent = new Intent(OfficersActivity.this, JaffarabadActivity.class);
            startActivity(intent);

            finish();
        } else {
            Toast.makeText(OfficersActivity.this, "Something Wrong", Toast.LENGTH_SHORT).show();
        }
    }

    private void Govindapuram(String email, String password) {
        if (email.equals("Govindapuram@gmail.com") && password.equals("Govindapuram123")) {
            Intent intent = new Intent(OfficersActivity.this, GovindapuramActivity.class);
            startActivity(intent);

            finish();
        } else {
            Toast.makeText(OfficersActivity.this, "Something Wrong", Toast.LENGTH_SHORT).show();
        }
    }

    private void Gollakuppam(String email, String password) {
        if (email.equals("Gollakuppam@gmail.com") && password.equals("Gollakuppam123")) {
            Intent intent = new Intent(OfficersActivity.this, GollakuppamActivity.class);
            startActivity(intent);

            finish();
        } else {
            Toast.makeText(OfficersActivity.this, "Something Wrong", Toast.LENGTH_SHORT).show();
        }
    }

    private void Girisamuthiram(String email, String password) {
        if (email.equals("Girisamuthiram@gmail.com") && password.equals("Girisamuthiram123")) {
            Intent intent = new Intent(OfficersActivity.this, GirisamuthiramActivity.class);
            startActivity(intent);

            finish();
        } else {
            Toast.makeText(OfficersActivity.this, "Something Wrong", Toast.LENGTH_SHORT).show();
        }
    }

    private void Elayanagaram(String email, String password) {
        if (email.equals("Elayanagaram@gmail.com") && password.equals("Elayanagaram123")) {
            Intent intent = new Intent(OfficersActivity.this, ElayanagaramActivity.class);
            startActivity(intent);

            finish();
        } else {
            Toast.makeText(OfficersActivity.this, "Something Wrong", Toast.LENGTH_SHORT).show();
        }
    }

    private void Echangal(String email, String password) {
        if (email.equals("Echangal@gmail.com") && password.equals("Echangal123")) {
            Intent intent = new Intent(OfficersActivity.this, EchangalActivity.class);
            startActivity(intent);

            finish();
        } else {
            Toast.makeText(OfficersActivity.this, "Something Wrong", Toast.LENGTH_SHORT).show();
        }
    }

    private void Devasathanam(String Email, String Password) {
        //Devasathanam
        if (Email.equals("Devasathanam@gmail.com") && Password.equals("Devasathanam123")) {
            Intent intent = new Intent(OfficersActivity.this, DevasathanamActivity.class);
            startActivity(intent);

            finish();
        } else {
            Toast.makeText(OfficersActivity.this, "Something Wrong", Toast.LENGTH_SHORT).show();
        }
    }

    private void Beemakulam(String Email, String Password) {
        if (Email.equals("Beemakulam@gmail.com") && Password.equals("Beemakulam123")) {
            Intent intent = new Intent(OfficersActivity.this, BeemakulamActivity.class);
            startActivity(intent);

            finish();
        } else {
            Toast.makeText(OfficersActivity.this, "Something Wrong", Toast.LENGTH_SHORT).show();
        }
    }
}