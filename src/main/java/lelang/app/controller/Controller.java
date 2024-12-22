package lelang.app.controller;

public  abstract class Controller {
    
    public abstract void getData();

    public abstract <T> void createData( T entity);

    public abstract <T> void updateData( T entity);

    public abstract void deleteData(long id);
}
