package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TelaCadastro1 extends AppCompatActivity {

    private EditText edtNome, edtCpf, edtDataNascimento, edtSexo, edtLocal, edtCidade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro1);

        edtNome = findViewById(R.id.edtNome);
        edtCpf = findViewById(R.id.edtCpf);
        edtDataNascimento = findViewById(R.id.edtDataNascimento);
        edtSexo = findViewById(R.id.edtSexo);
        edtLocal = findViewById(R.id.edtLocal);
        edtCidade = findViewById(R.id.edtCidade);

        Intent intent = getIntent();
        String registroId = intent.getStringExtra("registroId");

        if (registroId != null) {
            edtNome.setText(intent.getStringExtra("nome"));
            edtCpf.setText(intent.getStringExtra("cpf"));
            edtDataNascimento.setText(intent.getStringExtra("data_nascimento"));
            edtSexo.setText(intent.getStringExtra("sexo"));
            edtLocal.setText(intent.getStringExtra("local"));
            edtCidade.setText(intent.getStringExtra("cidade"));
        }
    }

    public void processarBotaoAvancar(View v) {
        String nome = edtNome.getText().toString().trim();
        String cpf = edtCpf.getText().toString().trim();
        String dataNascimento = edtDataNascimento.getText().toString().trim();
        String sexo = edtSexo.getText().toString().trim();
        String local = edtLocal.getText().toString().trim();
        String cidade = edtCidade.getText().toString().trim();

        if (nome.isEmpty() || cpf.isEmpty() || dataNascimento.isEmpty() || sexo.isEmpty() ||
                local.isEmpty() || cidade.isEmpty()) {
            Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(this, TelaCadastro2.class);
        intent.putExtra("registroId", getIntent().getStringExtra("registroId"));
        intent.putExtra("nome", nome);
        intent.putExtra("cpf", cpf);
        intent.putExtra("data_nascimento", dataNascimento);
        intent.putExtra("sexo", sexo);
        intent.putExtra("local", local);
        intent.putExtra("cidade", cidade);

        intent.putExtra("data_ocorrencia", getIntent().getStringExtra("data_ocorrencia"));
        intent.putExtra("hora_chamado", getIntent().getStringExtra("hora_chamado"));
        intent.putExtra("hora_saida", getIntent().getStringExtra("hora_saida"));
        intent.putExtra("hora_chegada", getIntent().getStringExtra("hora_chegada"));
        intent.putExtra("retorno_base", getIntent().getStringExtra("retorno_base"));
        intent.putExtra("viaturas", getIntent().getStringExtra("viaturas"));

        intent.putExtra("bombeiro1", getIntent().getStringExtra("bombeiro1"));
        intent.putExtra("bombeiro2", getIntent().getStringExtra("bombeiro2"));
        intent.putExtra("bombeiro3", getIntent().getStringExtra("bombeiro3"));
        intent.putExtra("cov", getIntent().getStringExtra("cov"));
        intent.putExtra("tipo_ocorrencia", getIntent().getStringExtra("tipo_ocorrencia"));
        intent.putExtra("relato", getIntent().getStringExtra("relato"));

        startActivity(intent);
    }

    public void processarBotaoVoltar(View v) {
        finish();
    }
}
