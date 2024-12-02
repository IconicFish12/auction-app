package lelang.app.model;

public class Kategori {
    private long id;
    private String namaKategori;

    public Kategori(long id, String namaKategori) {
        this.namaKategori = namaKategori;
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setID(long id) {
        this.id = id;
    }

    public String getNamaKategori() {
        return namaKategori;
    }

    public void setNamaKategori(String namaKategori) {
        this.namaKategori = namaKategori;
    }

    public void displayData(){
        System.out.println(" =========== Data Kategori ============");
        System.out.println("Data ke -" + id);
        System.out.println("Nama Kategori : " + namaKategori);
    }


}
