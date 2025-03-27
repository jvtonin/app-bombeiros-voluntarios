package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class TelaCadastro2 extends AppCompatActivity {

    private EditText edtHoraChamado, edtHoraSaida, edtHoraChegada, edtRetornoBase, edtViaturas, edtDataOcorrencia;
    private String nome, cpf, dataNascimento, sexo, local, cidade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro2);

        edtDataOcorrencia = findViewById(R.id.edtDataOcorrencia);
        edtHoraChamado = findViewById(R.id.edtHoraChamado);
        edtHoraSaida = findViewById(R.id.edtHoraSaida);
        edtHoraChegada = findViewById(R.id.edtHoraChegada);
        edtRetornoBase = findViewById(R.id.edtRetornoBase);
        edtViaturas = findViewById(R.id.edtViaturas);


        Intent intent = getIntent();
        edtDataOcorrencia.setText(intent.getStringExtra("data_ocorrencia"));
        edtHoraChamado.setText(intent.getStringExtra("hora_chamado"));
        edtHoraSaida.setText(intent.getStringExtra("hora_saida"));
        edtHoraChegada.setText(intent.getStringExtra("hora_chegada"));
        edtRetornoBase.setText(intent.getStringExtra("retorno_base"));
        edtViaturas.setText(intent.getStringExtra("viaturas"));
    }

    public void processarBotaoAvancar(View v) {
        String dataOcorrencia = edtDataOcorrencia.getText().toString().trim();
        String horaChamado = edtHoraChamado.getText().toString().trim();
        String horaSaida = edtHoraSaida.getText().toString().trim();
        String horaChegada = edtHoraChegada.getText().toString().trim();
        String retornoBase = edtRetornoBase.getText().toString().trim();
        String viaturas = edtViaturas.getText().toString().trim();

        if (dataOcorrencia.isEmpty() || horaChamado.isEmpty() || horaSaida.isEmpty() || horaChegada.isEmpty() ||
                retornoBase.isEmpty() ||  viaturas.isEmpty()) {
            Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(this, TelaCadastro3.class);

        intent.putExtra("registroId", getIntent().getStringExtra("registroId"));
        intent.putExtra("nome", getIntent().getStringExtra("nome"));
        intent.putExtra("cpf", getIntent().getStringExtra("cpf"));
        intent.putExtra("data_nascimento", getIntent().getStringExtra("data_nascimento"));
        intent.putExtra("sexo", getIntent().getStringExtra("sexo"));
        intent.putExtra("local", getIntent().getStringExtra("local"));
        intent.putExtra("cidade", getIntent().getStringExtra("cidade"));

        intent.putExtra("data_ocorrencia", dataOcorrencia);
        intent.putExtra("hora_chamado", horaChamado);
        intent.putExtra("hora_saida", horaSaida);
        intent.putExtra("hora_chegada", horaChegada);
        intent.putExtra("retorno_base", retornoBase);
        intent.putExtra("viaturas", viaturas);

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
