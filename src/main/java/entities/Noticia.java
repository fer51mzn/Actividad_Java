package entities;

public class Noticia {
    private String titulo;
    private String texto;
    private String url;

    public Noticia(String titulo, String texto, String url) {
        this.titulo = titulo;
        this.texto = texto;
        this.url = url;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getTexto() {
        return texto;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "Noticia{" +
                "titulo='" + titulo + '\'' +
                ", texto='" + texto + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
