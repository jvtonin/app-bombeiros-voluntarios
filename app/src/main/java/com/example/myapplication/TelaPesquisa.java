package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class TelaPesquisa extends AppCompatActivity {

    private ListView listViewRegistros;
    private ArrayList<String> listaExibida;
    private HashMap<String, String> registroIdMap;
    private ArrayAdapter<String> adapter;
    private FirebaseDatabase database;
    private EditText etBuscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_pesquisa);

        FirebaseApp.initializeApp(this);
        database = FirebaseDatabase.getInstance();

        listViewRegistros = findViewById(R.id.listRegstro);
        etBuscar = findViewById(R.id.etBuscar);

        listaExibida = new ArrayList<>();
        registroIdMap = new HashMap<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaExibida);
        listViewRegistros.setAdapter(adapter);

        buscarRegistros();

        etBuscar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                filterList(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        listViewRegistros.setOnItemClickListener((parent, view, position, id) -> {
            String itemExibido = listaExibida.get(position);
            String registroId = registroIdMap.get(itemExibido);

            if (registroId != null) {
                showOptionsDialog(registroId, itemExibido);
            }
        });
    }

    private void buscarRegistros() {
        DatabaseReference ref = database.getReference("registros");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                listaExibida.clear();
                registroIdMap.clear();

                int contador = 1;
                for (DataSnapshot registroSnapshot : snapshot.getChildren()) {
                    String registroId = registroSnapshot.getKey();
                    Registro registro = registroSnapshot.getValue(Registro.class);

                    if (registro != null) {
                        String tipoOcorrencia = registro.getTipoOcorrencia();
                        String dataOcorrencia = registro.getDataOcorrencia();

                        String itemExibido = contador + " | Data: " + dataOcorrencia + " | Tipo: " + tipoOcorrencia;

                        listaExibida.add(itemExibido);
                        registroIdMap.put(itemExibido, registroId);
                        contador++;
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(TelaPesquisa.this, "Erro ao buscar registros", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void filterList(String query) {
        if (query.isEmpty()) {
            listaExibida.clear();
            listaExibida.addAll(registroIdMap.keySet());
        } else {
            ArrayList<String> filteredList = new ArrayList<>();
            for (String item : registroIdMap.keySet()) {
                if (item.toLowerCase().contains(query.toLowerCase())) {
                    filteredList.add(item);
                }
            }
            listaExibida.clear();
            listaExibida.addAll(filteredList);
        }
        adapter.notifyDataSetChanged();
    }

    private void showOptionsDialog(String registroId, String itemExibido) {
        AlertDialog.Builder builder = new AlertDialog.Builder(TelaPesquisa.this);
        builder.setTitle("Escolha uma opção")
                .setItems(new CharSequence[] {"Editar", "Excluir"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            editarRegistro(registroId);
                        } else if (which == 1) {
                            excluirRegistro(registroId);
                        }
                    }
                })
                .show();
    }

    private void editarRegistro(String registroId) {
        DatabaseReference ref = database.getReference("registros").child(registroId);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Registro registro = snapshot.getValue(Registro.class);
                if (registro != null) {
                    Intent intent = new Intent(TelaPesquisa.this, TelaCadastro1.class);

                    intent.putExtra("registroId", registroId);
                    intent.putExtra("nome", registro.getNome());
                    intent.putExtra("cpf", registro.getCpf());
                    intent.putExtra("data_nascimento", registro.getDataNascimento());
                    intent.putExtra("sexo", registro.getSexo());
                    intent.putExtra("local", registro.getLocal());
                    intent.putExtra("cidade", registro.getCidade());

                    intent.putExtra("data_ocorrencia", registro.getDataOcorrencia());
                    intent.putExtra("hora_chamado", registro.getHoraChamado());
                    intent.putExtra("hora_saida", registro.getHoraSaida());
                    intent.putExtra("hora_chegada", registro.getHoraChegada());
                    intent.putExtra("retorno_base", registro.getRetornoBase());
                    intent.putExtra("viaturas", registro.getViaturas());

                    intent.putExtra("bombeiro1", registro.getBombeiro1());
                    intent.putExtra("bombeiro2", registro.getBombeiro2());
                    intent.putExtra("bombeiro3", registro.getBombeiro3());
                    intent.putExtra("cov", registro.getCov());
                    intent.putExtra("tipo_ocorrencia", registro.getTipoOcorrencia());
                    intent.putExtra("relato", registro.getRelato());

                    startActivity(intent);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(TelaPesquisa.this, "Erro ao buscar dados!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void excluirRegistro(String registroId) {
        DatabaseReference ref = database.getReference("registros").child(registroId);
        ref.removeValue()
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(TelaPesquisa.this, "Registro excluído com sucesso!", Toast.LENGTH_SHORT).show();
                    buscarRegistros();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(TelaPesquisa.this, "Erro ao excluir registro", Toast.LENGTH_SHORT).show();
                });
    }

    public void processarBotaoVoltar(View v) {
        finish();
    }
}
