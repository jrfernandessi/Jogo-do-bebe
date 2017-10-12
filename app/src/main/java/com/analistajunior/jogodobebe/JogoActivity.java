package com.analistajunior.jogodobebe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;



public class JogoActivity extends AppCompatActivity {
    private TextView resultadoTextView;
    private TextView mensagemTextView;
    private ImageView botao;
    private ImageView novo;
    private EditText jogadaEditText;
    private boolean flag;
    private int menor = 0, maior = 100;
    private int jogada;
    private int sorteio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo);

        resultadoTextView = (TextView) findViewById(R.id.resultado_text_view);
        mensagemTextView = (TextView) findViewById(R.id.mensage_text_view);
        botao = (ImageView) findViewById(R.id.jogada_button);
        novo = (ImageView) findViewById(R.id.novo_button);
        jogadaEditText = (EditText) findViewById(R.id.jogada_edit_text);
        Random random = new Random();
        sorteio = random.nextInt(99)+1;
        flag = true;


        resultadoTextView.setText("De "+menor+" a "+maior);
        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!jogadaEditText.getText().toString().equals("")){
                    esconderTeclado(JogoActivity.this);
                    jogar();
                }else{
                    Toast.makeText(getApplicationContext(), "Digite um número", Toast.LENGTH_SHORT).show();
                }


            }
        });

        jogadaEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if(keyEvent.getAction() == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_ENTER){
                    manterTeclado(JogoActivity.this);
                    jogar();

                }
                return false;
            }
        });

    }

    public void novoSorteio(){
        Intent intent = new Intent(JogoActivity.this, JogoActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);

    }

    public void manterTeclado(Activity activity){
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInputFromInputMethod(activity.getCurrentFocus().getWindowToken(), 0);
    }

    public void esconderTeclado(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }

    public void jogar(){
        jogada = Integer.parseInt(jogadaEditText.getText().toString());
        if (jogada <= menor || jogada >= maior) {
            mensagemTextView.setText("Jogador bebe, digitou número fora do intervalo");
            flag = false;
        }
        if (jogada == sorteio) {
            mensagemTextView.setText("Jogador bebe, acertou o número");
            flag = false;
        }
        if (jogada < sorteio) {
            menor = jogada;
        }
        if (jogada > sorteio) {
            maior = jogada;
        }
        if (menor + 1 == sorteio && maior - 1 == sorteio) {
            mensagemTextView.setText("Sem possibilidades todos bebem");
            flag = false;
        }
        if (flag) {
            resultadoTextView.setText("De " + menor + " a " + maior);
        }
        jogadaEditText.setText(null);
        if (!flag) {
            resultadoTextView.setText("O número sorteado foi: " + sorteio);
            botao.setVisibility(View.INVISIBLE);
            novo.setVisibility(View.VISIBLE);
            jogadaEditText.setVisibility(View.INVISIBLE);
            novo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    novoSorteio();
                }
            });

        }
    }
}
