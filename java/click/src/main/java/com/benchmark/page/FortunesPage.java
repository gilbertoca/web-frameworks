package com.benchmark.page;

import org.apache.click.Page;
import org.apache.click.control.Column;
import org.apache.click.control.Table;
import com.benchmark.model.Fortune;
import java.sql.*;
import java.util.*;

public class FortunesPage extends Page {
    private static final long serialVersionUID = 1L;

    // O componente Table do Click que será renderizado automaticamente
    public Table table = new Table("table");

    public FortunesPage() {
        // Define as colunas mapeando os atributos do POJO Fortune
        table.addColumn(new Column("id"));
        table.addColumn(new Column("message"));

        // Remove as bordas, paddings e classes injetadas para gerar o HTML puro exigido pelo teste
        table.setAttribute("border", "0");
        table.setAttribute("cellpadding", "0");
        table.setAttribute("cellspacing", "0");
        table.setAttribute("class", "");
        table.setAttribute("id", "");

        // No Apache Click, para desativar controles extras ou paginação:
        table.setPageSize(0); // Garante que não haverá quebra de página
    }

    @Override
    public void onGet() {
        List<Fortune> fortuneList = new ArrayList<>();
        String dbUrl = System.getenv("DB_URL") != null ? System.getenv("DB_URL") : "jdbc:postgresql://localhost:5432/hello_world";
        String query = "SELECT id, message FROM fortune";

        try (Connection conn = DriverManager.getConnection(dbUrl, "benchmarkdbuser", "benchmarkdbpass");
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                fortuneList.add(new Fortune(rs.getInt("id"), rs.getString("message")));
            }
        } catch (SQLException e) {
            // Ignorado para fins de benchmark rápido
        }

        // Regra obrigatória do benchmark TechEmpower: adicionar frase dinâmica
        fortuneList.add(new Fortune(0, "Additional fortune added at request time."));

        // Ordena por ordem alfabética da mensagem
        Collections.sort(fortuneList);

        // Injeta a lista ordenada diretamente como a fonte de dados do componente do Click
        table.setRowList(fortuneList);
    }
}
