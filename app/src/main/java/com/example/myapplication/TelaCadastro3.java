package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TelaCadastro3 extends AppCompatActivity {

    private EditText edtBombeiro1, edtBombeiro2, edtBombeiro3, edtCov, edtTipoOcorrencia, edtRelato;
    private FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro3);

        FirebaseApp.initializeApp(this);
        database = FirebaseDatabase.getInstance();

        edtBombeiro1 = findViewById(R.id.edtBombeiro1);
        edtBombeiro2 = findViewById(R.id.edtBombeiro2);
        edtBombeiro3 = findViewById(R.id.edtBombeiro3);
        edtCov = findViewById(R.id.edtCov);
        edtTipoOcorrencia = findViewById(R.id.edtTipoOcorrencia);
        edtRelato = findViewById(R.id.edtRelato);

        Intent intent = getIntent();
        edtBombeiro1.setText(intent.getStringExtra("bombeiro1"));
        edtBombeiro2.setText(intent.getStringExtra("bombeiro2"));
        edtBombeiro3.setText(intent.getStringExtra("bombeiro3"));
        edtCov.setText(intent.getStringExtra("cov"));
        edtTipoOcorrencia.setText(intent.getStringExtra("tipo_ocorrencia"));
        edtRelato.setText(intent.getStringExtra("relato"));
    }

    public void processarBotaoCadastrar(View v) {
        Intent intent = getIntent();
        String registroId = intent.getStringExtra("registroId");

        String nome = intent.getStringExtra("nome");
        String cpf = intent.getStringExtra("cpf");
        String dataNascimento = intent.getStringExtra("data_nascimento");
        String sexo = intent.getStringExtra("sexo");
        String local = intent.getStringExtra("local");
        String cidade = intent.getStringExtra("cidade");
        String dataOcorrencia = intent.getStringExtra("data_ocorrencia");
        String horaChamado = intent.getStringExtra("hora_chamado");
        String horaSaida = intent.getStringExtra("hora_saida");
        String horaChegada = intent.getStringExtra("hora_chegada");
        String retornoBase = intent.getStringExtra("retorno_base");
        String viaturas = intent.getStringExtra("viaturas");

        String bombeiro1 = edtBombeiro1.getText().toString().trim();
        String bombeiro2 = edtBombeiro2.getText().toString().trim();
        String bombeiro3 = edtBombeiro3.getText().toString().trim();
        String cov = edtCov.getText().toString().trim();
        String tipoOcorrencia = edtTipoOcorrencia.getText().toString().trim();
        String relato = edtRelato.getText().toString().trim();

        if (bombeiro1.isEmpty() || bombeiro2.isEmpty() || bombeiro3.isEmpty() || tipoOcorrencia.isEmpty() || relato.isEmpty()) {
            Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show();
            return;
        }

        Registro registro = new Registro(
                nome, cpf, dataNascimento, sexo, local, cidade,
                dataOcorrencia, horaChamado, horaSaida, horaChegada,
                retornoBase, viaturas, cov, bombeiro1, bombeiro2,
                bombeiro3, tipoOcorrencia, relato
        );

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("registros");

        if (registroId != null) {
            ref.child(registroId).setValue(registro).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(this, "Registro atualizado com sucesso!", Toast.LENGTH_SHORT).show();
                    Intent it = new Intent(getBaseContext(), MainActivity.class);
                    it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(it);
                    finish();
                } else {
                    Toast.makeText(this, "Erro ao atualizar o registro.", Toast.LENGTH_SHORT).show();
                }
            });
        } else {

            DatabaseReference newRef = ref.push();
            newRef.setValue(registro).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(this, "Registro salvo com sucesso!", Toast.LENGTH_SHORT).show();
                    Intent it = new Intent(getBaseContext(), MainActivity.class);
                    it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(it);
                    finish();
                } else {
                    Exception e = task.getException();
                    Toast.makeText(this, "Erro ao salvar registro: " + (e != null ? e.getMessage() : "Desconhecido"), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void processarBotaoVoltar(View v) {
        finish();
    }
}





