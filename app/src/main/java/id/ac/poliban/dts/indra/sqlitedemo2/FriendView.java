package id.ac.poliban.dts.indra.sqlitedemo2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import id.ac.poliban.dts.indra.sqlitedemo2.dao.impl.FriendDaoImplSQLite;
import id.ac.poliban.dts.indra.sqlitedemo2.domain.Friend;

import static id.ac.poliban.dts.indra.sqlitedemo2.MainActivity.EVENT_INSERT;
import static id.ac.poliban.dts.indra.sqlitedemo2.MainActivity.EVENT_UPDATE;
import static id.ac.poliban.dts.indra.sqlitedemo2.MainActivity.EVENT_VIEW;

public class FriendView extends AppCompatActivity {
    private FriendDaoImplSQLite ds;
    private TextView tvId;
    private EditText etId;
    private EditText etName;
    private EditText etAddress;
    private EditText etPhone1;
    private EditText etPhone2;
    private EditText etPhone3;
    private EditText etEmail;
    private EditText etHobbies;
    private Button btSimpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_view);

        ds = new FriendDaoImplSQLite(this);

        tvId = findViewById(R.id.tvId);
        etId = findViewById(R.id.etID);
        etName = findViewById(R.id.etName);
        etAddress = findViewById(R.id.etAddress);
        etPhone1 = findViewById(R.id.etPhone1);
        etPhone2 = findViewById(R.id.etPhone2);
        etPhone3 = findViewById(R.id.etPhone3);
        etEmail = findViewById(R.id.etEmail);
        etHobbies = findViewById(R.id.etHobbies);

        btSimpan = findViewById(R.id.btSimpan);

        btSimpan.setOnClickListener(v -> {
            if (!isValidate()) return;


            if(getIntent().getIntExtra("event", -1)== EVENT_INSERT){
                Friend friend = new Friend(
                        etName.getText().toString(),
                        etAddress.getText().toString(),
                        etPhone1.getText().toString(),
                        etPhone2.getText().toString(),
                        etPhone3.getText().toString(),
                        etEmail.getText().toString(),
                        etHobbies.getText().toString());

                ds.insert(friend);
            }             else if(getIntent().getIntExtra("event", -1)==EVENT_UPDATE) {                 Friend friend = new Friend(                         Integer.parseInt(etId.getText().toString()),                         etName.getText().toString(),                         etAddress.getText().toString(),                         etPhone1.getText().toString(),                         etPhone2.getText().toString(),                         etPhone3.getText().toString(),                         etEmail.getText().toString(),                         etHobbies.getText().toString());

                ds.update(friend);
            }

                onBackPressed();
        });
    }
    @Override     protected void onStart() {
        super.onStart();

        if (getIntent().getExtras() != null && getSupportActionBar() != null)
            if (getIntent().getIntExtra("event", -1) == EVENT_INSERT) {
            setTitle("Tambah Data Teman");
            settingTampilan(EVENT_INSERT);
        }else if (getIntent().getIntExtra("event", -1) == EVENT_UPDATE) {
                setTitle("Update Data Teman");
                settingTampilan(EVENT_UPDATE);
                tampilkanData(getIntent().getIntExtra("id", -1));
            }
        else if (getIntent().getIntExtra("event", -1) == EVENT_VIEW) {
                setTitle("Detail Teman");
                settingTampilan(EVENT_VIEW);
                tampilkanData(getIntent().getIntExtra("id", -1));
            }
    }
    private void settingTampilan(int event) {
        switch (event) {
            case EVENT_INSERT:
                tvId.setVisibility(View.GONE);
                etId.setVisibility(View.GONE);
                etName.setEnabled(true);
                etAddress.setEnabled(true);
                etPhone1.setEnabled(true);
                etPhone2.setEnabled(true);
                etPhone3.setEnabled(true);
                etEmail.setEnabled(true);
                etHobbies.setEnabled(true);
                btSimpan.setVisibility(View.VISIBLE);
                break;

            case EVENT_UPDATE:
                tvId.setVisibility(View.VISIBLE);
                etId.setVisibility(View.VISIBLE);
                etId.setEnabled(false);
                etName.setEnabled(true);
                etAddress.setEnabled(true);
                etPhone1.setEnabled(true);
                etPhone2.setEnabled(true);
                etPhone3.setEnabled(true);
                etEmail.setEnabled(true);
                etHobbies.setEnabled(true);
                btSimpan.setVisibility(View.VISIBLE);
                break;

            case EVENT_VIEW:
                tvId.setVisibility(View.VISIBLE);
                etId.setVisibility(View.VISIBLE);
                etId.setEnabled(false);
                etName.setEnabled(false);
                etAddress.setEnabled(false);
                etPhone1.setEnabled(false);
                etPhone2.setEnabled(false);
                etPhone3.setEnabled(false);
                etEmail.setEnabled(false);
                etHobbies.setEnabled(false);
                btSimpan.setVisibility(View.GONE);
                break;
        }

    }
    private void tampilkanData(int id) {
        Friend friend = ds.getFriendById(id);

        etId.setText(String.valueOf(friend.getId()));
        etName.setText(friend.getName());
        etAddress.setText(friend.getAddress());
        etPhone1.setText(friend.getPhone1());
        etPhone2.setText(friend.getPhone2());
        etPhone3.setText(friend.getPhone3());
        etEmail.setText(friend.getEmail());
        etHobbies.setText(friend.getHobbies());
    }


    private boolean isValidate() {
        EditText[] ets = new EditText[]{
                etName, etEmail
        };

                 for (EditText et : ets) {
                     if (et.getText().toString().isEmpty()) {
                         et.setError("field harus diisi!");
                         return false;
                     }
                 }
                 return true;
    }
}



