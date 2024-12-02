package lelang.app.model;

public class Barang {
    private long id, userId, kategoriId;
    private String nama_barang, deskripsiBarang, foto, proses_lelang, status_lelang;
    private int harga_barang;

    // relation
    private Kategori kategori;
    private Masyarakat user;

    public Barang(long id, long userId, long kategoriId, String nama_barang, String deskripsiBarang, int harga_barang, 
                    String foto, String status_lelang, String proses_lelang, Kategori kategori, Masyarakat user) {
        this.id = id;
        this.userId = userId;
        this.kategoriId = kategoriId;
        this.nama_barang = nama_barang;
        this.deskripsiBarang = deskripsiBarang;
        this.foto = foto;
        this.proses_lelang = proses_lelang;
        this.status_lelang = status_lelang;
        this.harga_barang = harga_barang;
    }

    public Barang(long long1, long long2, long long3, String string, String string2, int int1, String string3,
            String string4, String string5, Barang barang) {
        //TODO Auto-generated constructor stub
    }

    public long getId() {
        return id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getKategoriId() {
        return kategoriId;
    }

    public void setKategoriId(long kategoriId) {
        this.kategoriId = kategoriId;
    }

    public String getNama_barang() {
        return nama_barang;
    }

    public void setNama_barang(String nama_barang) {
        this.nama_barang = nama_barang;
    }

    public String getDeskripsiBarang() {
        return deskripsiBarang;
    }

    public void setDeskripsiBarang(String deskripsiBarang) {
        this.deskripsiBarang = deskripsiBarang;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getproses_lelang() {
        return proses_lelang;
    }

    public void setproses_lelang(String proses_lelang) {
        this.proses_lelang = proses_lelang;
    }

    public String getStatus_lelang() {
        return status_lelang;
    }

    public void setStatus_lelang(String status_lelang) {
        this.status_lelang = status_lelang;
    }

    public int getHarga_barang() {
        return harga_barang;
    }

    public void setHarga_barang(int harga_barang) {
        this.harga_barang = harga_barang;
    }

    public String getProses_lelang() {
        return proses_lelang;
    }

    public Kategori getKategori() {
        return kategori;
    }

    public Masyarakat getUser() {
        return user;
    }

    // behavior

    public void displayData(){
        System.out.println(" =========== Data Barang ============");
        System.out.println("Data ke -" + id);
        System.out.println("Nama Barang : " + nama_barang);
        System.out.println("Kategori Barang : " + kategori.getNamaKategori());
        System.out.println("Pemilik Barang : " + user.getNama_lengkap());
        System.out.println("Deskripsi Barang : " + deskripsiBarang);
        System.out.println("Foto Barang : " + foto);
        System.out.println("Harga Barang : " + harga_barang);
        System.out.println("Status Pelelangan : " + status_lelang);
        System.out.println("Status Proses : " + proses_lelang);
    }

}
