package com.example.bloqueadordechamadas;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class BlackListRepositorio {
    private SQLiteDatabase conexao;

    public BlackListRepositorio(SQLiteDatabase conexao) {
        this.conexao = conexao;
    }
    public void inserir(String numero) {
        ContentValues  contentValues = new ContentValues();
        contentValues.put("numero", numero);
        conexao.insertOrThrow("black_list", null, contentValues);
    }

    public void remover(int id) {
        String[] parametros = new String[1];
        parametros[0] = String.valueOf(id);
        conexao.delete("black_list", "id = ?", parametros);
    }

    public TelefoneBanco buscarNumero(String numero) {

        TelefoneBanco telefone = new TelefoneBanco();

        StringBuilder sql = new StringBuilder();
        sql.append("select * ");
        sql.append(" from black_list ");
        sql.append(" where numero = ?");

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(numero);
        Cursor resultado = conexao.rawQuery(sql.toString(), parametros);
        if(resultado.getCount() >  0){
            resultado.moveToFirst();
            telefone.id = resultado.getInt(resultado.getColumnIndexOrThrow("id"));
            telefone.number = resultado.getString(resultado.getColumnIndexOrThrow("numero"));
            return telefone;
        }
        return null;
    }

    public List<TelefoneBanco> buscarTodosNumeros() {
        List<TelefoneBanco> numeros = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append("select * ");
        sql.append(" from black_list");

        Cursor resultado = conexao.rawQuery(sql.toString(), null);
        if(resultado.getCount() >  0){
            resultado.moveToFirst();

            do {
                TelefoneBanco telefone = new TelefoneBanco();
                telefone.id = resultado.getInt(resultado.getColumnIndexOrThrow("id"));
                telefone.number = resultado.getString(resultado.getColumnIndexOrThrow("numero"));
                numeros.add(telefone);
            } while (resultado.moveToNext());
        }


        return numeros;
    }
}
