package com.benchmark.page;

import org.apache.click.Page;

public class IndexPage extends Page {

    private static final long serialVersionUID = 1L;

    @Override
    public void onGet() {
        // Define o cabeçalho HTTP de texto puro para o benchmark performar no máximo
        getContext().getResponse().setContentType("text/plain");

        // Evita que o Click tente renderizar um arquivo index.htm físico no disco
        setTemplate(null);

        try {
            getContext().getResponse().getWriter().write("Hello World from Apache Click!");
        } catch (Exception e) {
            // Ignorado para fins de benchmark rápido
        }
    }
}
