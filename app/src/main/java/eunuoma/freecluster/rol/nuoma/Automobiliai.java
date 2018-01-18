package eunuoma.freecluster.rol.nuoma;

/**
 * Created by moksleivis on 2018-01-18.
 */

public class Automobiliai {
    private String id;
    private String data;
    private String vartotojas;
    private String vardas;
    private String pavarde;
    private String asmenskodas;

    public Automobiliai(String id, String data, String vartotojas, String vardas, String pavarde, String asmenskodas) {
        this.id = id;
        this.data = data;
        this.vartotojas = vartotojas;
        this.vardas = vardas;
        this.pavarde = pavarde;
        this.asmenskodas = asmenskodas;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getVartotojas() {
        return vartotojas;
    }

    public void setVartotojas(String vartotojas) {
        this.vartotojas = vartotojas;
    }

    public String getVardas() {
        return vardas;
    }

    public void setVardas(String vardas) {
        this.vardas = vardas;
    }

    public String getPavarde() {
        return pavarde;
    }

    public void setPavarde(String pavarde) {
        this.pavarde = pavarde;
    }

    public String getAsmenskodas() {
        return asmenskodas;
    }

    public void setAsmenskodas(String asmenskodas) {
        this.asmenskodas = asmenskodas;
    }
}
