package lelang.app.model;

public class Penawaran {
    private long id, barangId, userId;
    private int harga_penawaran;
    
    public Penawaran(long id, long barangId, long userId, int harga_penawaran) {
        this.id = id;
        this.barangId = barangId;
        this.userId = userId;
        this.harga_penawaran = harga_penawaran;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getBarangId() {
        return barangId;
    }

    public void setBarangId(long barangId) {
        this.barangId = barangId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getHarga_penawaran() {
        return harga_penawaran;
    }

    public void setHarga_penawaran(int harga_penawaran) {
        this.harga_penawaran = harga_penawaran;
    }

    
}
